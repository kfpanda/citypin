package com.util.security.token;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import sun.misc.BASE64Encoder;

@Repository
public class RestTokenRepositoryImpl{
	private static final Logger logger = LoggerFactory.getLogger(RestTokenRepositoryImpl.class);
	private final static Map<String, RestToken> tokenMap = new HashMap<String, RestToken>();
	private final String SALT_KEY = "rest_key";
    private final Integer INTERVAL_TIME = 3600000; //1小时
    private final String SEPARATE = "_";
	public synchronized String createToken(String ip, Object principal, Collection<GrantedAuthority> authorities) {
		String tokenValue = generateTokenData(ip, ((User)principal).getUsername());
		if(tokenValue == null){
			return null;
		}
		removeUserTokens( ((User)principal).getUsername() );
//		RestToken token = tokenMap.get(tokenValue);
//		tokenMap.remove(tokenValue);  //移除已有的token
		RestToken token = new RestToken(principal, ip, 
        		tokenValue, System.currentTimeMillis() + INTERVAL_TIME, authorities);
        tokenMap.put(tokenValue, token);
        logger.debug("create tokenValue({}) for ip({})", tokenValue, ip);
        return tokenValue;
    }
	
	public synchronized RestToken getTokenForSeries(String restToken, String ip) {
        RestToken token = tokenMap.get(restToken);
        if(token != null && token.getIp().equals(ip) 
        		&& token.getExpireTime() > System.currentTimeMillis()){
        	//更新token
        	updateToken(token.getPrincipal(), token.getIp(), 
        			token.getTokenValue(), token.getAuthorities());
        	return token;
        }
        return null;
        /*if(!token.getIp().equals(ip)){
        	//非法token
        	return null;
        }
        if(token.getExpireTime() < System.currentTimeMillis()){
        	//token 失效
        	return null;
        }
        return token;*/
    }
	
	public synchronized void updateToken(Object principal, String ip, 
			String tokenValue, Collection<GrantedAuthority> authorities) {
        RestToken token = new RestToken(principal, ip, 
        		tokenValue, System.currentTimeMillis() + INTERVAL_TIME, authorities);
        
        tokenMap.put(tokenValue, token);
    }

    public synchronized void removeUserTokens(String userName) {
        Iterator<String> itr = tokenMap.keySet().iterator();
        
        while (itr.hasNext()) {
            String tokenValue = itr.next();
            RestToken token = tokenMap.get(tokenValue);
            if (userName.equals(((User)token.getPrincipal()).getUsername())) {
                itr.remove();
            }
        }
    }
    
    protected String generateTokenData(String account, String ip) {
    	StringBuffer buf = new StringBuffer();
    	buf.append(ip).append(SEPARATE).append(account).append(SEPARATE).append(SALT_KEY);
    	buf.append(SEPARATE).append(System.currentTimeMillis());
    	try {
			return encoderByMd5(buf.toString());
		} catch (NoSuchAlgorithmException e) {
			logger.error("generate token data failure", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("generate token data failure", e);
		}
    	return null;
    }
    
    /** 
     * 利用MD5进行加密
 　　  * @param str   待加密的字符串
 　　  * @return   加密后的字符串
 　　  * @throws NoSuchAlgorithmException 没有这种产生消息摘要的算法
 　　  * @throws UnsupportedEncodingException
 　　 */
	public String encoderByMd5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newStr;
	}
}
