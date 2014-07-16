package com.kfpanda.citypin.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kfpanda.citypin.bean.Message;
import com.kfpanda.citypin.biz.MsgBiz;
import com.kfpanda.citypin.mapper.MessageMapper;

@Repository
public class MsgBizImpl implements MsgBiz{
	private static final Logger logger = LoggerFactory.getLogger(MsgBizImpl.class);
	
	@Resource(name="messageMapper")
	private MessageMapper msgMapper;
	
	public List<Message> msgSearch(String account) {
		return msgMapper.findMsgs(account);
	}

}
