package com.luck.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 
 * @author 王镜鑫
 * 这是一个获取配置文件信息的类。
 *
 */
public class Property 
{
	public static Properties pro=null;
	static
	{
		if(pro==null)pro=new Properties();
	}
	/**
	 * 
	 * @param name 配置文件名
	 * @return 用该文件名初始化的一个properties类
	 */
	public static Properties getPro(String name)
	{
		if(pro==null)pro=new Properties();
		InputStream in=Property.class.getResourceAsStream(name);
		System.out.println(name);
		try
		{
			pro.load(in);
		} catch (IOException e)  
		{
			e.printStackTrace();
		}
		return pro;
	}
}
