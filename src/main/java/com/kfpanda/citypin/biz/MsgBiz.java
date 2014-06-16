package com.kfpanda.citypin.biz;

import java.util.List;

import com.kfpanda.citypin.bean.Message;

public interface MsgBiz {
	
	public List<Message> msgSearch(String account);
}
