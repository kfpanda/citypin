package com.kfpanda.citypin.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseAction {
    
    protected ResultDTO getResult(){
        return this.getResult(1, null, null);
    }
    
    protected ResultDTO getResult(int result){
        return this.getResult(result, null, null);
    }
    
	protected ResultDTO getResult(Object data){
		return this.getResult(data, null);
	}
	
	protected ResultDTO getResult(Object data, String msg){
		return this.getResult(1, data, msg);
	}
	
	protected ResultDTO getResult(int result, String msg){
        return this.getResult(result, null, msg);
    }
	
	protected ResultDTO getResult(int result, Object data, String msg){
		return new ResultDTO(result, data, msg);
	}
	
	protected ResultDTO getResult(Object data, int totalPage, long totalElem){
        return this.getResult(1, data, null, totalPage, totalElem);
    }
	
	protected ResultDTO getResult(int result, Object data, String msg, int totalPage, long totalElem){
	    Page page = new Page(totalPage, totalElem);
        return new ResultDTO(result, data, msg, page);
    }
	
    public List<String> newList(String[] objList){
        List<String> list = new ArrayList<String>();
        for(String obj : objList){
            list.add(obj);
        }
        return list;
    }
    
    public String getAuthAccount(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth == null){
    		return null;
    	}
    	return auth.getName();
    }
}