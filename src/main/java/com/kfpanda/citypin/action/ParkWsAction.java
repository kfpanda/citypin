package com.kfpanda.citypin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kfpanda.citypin.bean.ParkInfo;
import com.kfpanda.citypin.biz.ParkBiz;

@Controller("secretWsAction")
@RequestMapping("/park")
public class ParkWsAction extends BaseAction{
	private final Logger logger = Logger.getLogger(ParkWsAction.class);
	
	@Resource(name="parkBizImpl")
	private ParkBiz parkBiz;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public @ResponseBody Object parkFind(
            @RequestParam(value = "lat0") Double lat0,
            @RequestParam(value = "lat1") Double lat1,
            @RequestParam(value = "lng0") Double lng0,
            @RequestParam(value = "lng1") Double lng1) {
		
		List<ParkInfo> parks = this.parkBiz.findParkInfos(lat0, lat1, lng0, lng1);
//		if(StringUtils.isBlank(callback)){
			return this.getResult(parks);
//		}else{
//			return new JSONPObject(callback, this.getResult(parks));
//		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Object parkFind(@RequestParam(value = "lat") Double lat,
            @RequestParam(value = "lng") Double lng,
            @RequestParam(value = "pname") String pName,
            @RequestParam(value = "recid") String recId,
            @RequestParam(value = "devid") String devId,
            @RequestParam(value = "address") String address) {
		
		ParkInfo parkInfo = new ParkInfo();
		parkInfo.setAddress(address);
		parkInfo.setLat(lat);
		parkInfo.setLng(lng);
		parkInfo.setPName(pName);
		parkInfo.setRecId(recId);
		parkInfo.setDevId(devId);
		this.parkBiz.upsertParkInfo(parkInfo);
		return this.getResult(null);
	}
	
	/*@Resource(name="secretBizImpl")
	private SecretBiz secretBiz;
	
	@RequestMapping(value = "/svr", method = RequestMethod.GET)
	public void checkAuth(@RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr, HttpServletResponse response) {
		boolean authPass = secretBiz.checkSignature(signature, timestamp, nonce);
		if(authPass){
			//权限验证通过
			try {
				response.getWriter().write(echostr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/svr", method = RequestMethod.POST)
    public void answerQuestion(HttpServletRequest request, HttpServletResponse response) {
		
		//解析消息参数 到实体对象
		MsgParam msgParam = null;
		try {
			msgParam = (MsgParam)JAXBUtils.unmarshal( 
					request.getInputStream(), MsgParam.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//test
		JAXBUtils.marshal(msgParam, System.out);
		
		Object msgResult = secretBiz.replyMsg(msgParam);
		
		JAXBUtils.marshal(msgResult, System.out);
		try {
			JAXBUtils.marshal(msgResult, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }*/
}