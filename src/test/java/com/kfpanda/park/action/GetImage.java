package com.kfpanda.park.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.UUID;

import com.util.common.HttpRequest;

public class GetImage {
	
	private static String path = "F:\\p_img\\";
	
	/** 
     * 测试 
     * @param args 
     */  
    public static String imgSave(String url) {
        ByteBuffer buff = HttpRequest.sendGetRequest(url, null);
        byte[] btImg = buff.array();
        if(null != btImg && btImg.length > 0){
            String fileName = UUID.randomUUID() + ".jpg";
            writeImageToDisk(btImg, fileName);
            return fileName;
        }else{
            System.out.println("没有从该连接获得内容:" + url);
        }
        return null;
    }
    
    /** 
     * 将图片写入到磁盘 
     * @param img 图片数据流 
     * @param fileName 文件保存时的名称 
     */  
    public static void writeImageToDisk(byte[] img, String fileName){  
        try {
            File file = new File(path + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * 从输入流中获取数据 
     * @param inStream 输入流 
     * @return 
     * @throws Exception 
     */  
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
