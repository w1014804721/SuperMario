package com.luck.interfaces;
import java.awt.Rectangle;
/**
 * 
 * @author 王镜鑫
 *所有可以互相攻击的类都要实现该接口
 */
public interface Kill 
{
	/**
	 * 
	 * @return 返回外接矩形
	 */
	public Rectangle getRec();
	/**
	 * 
	 * @return 返回该攻击型类的类型
	 */
	public int getType();
	/**
	 * 
	 * @return 返回运动方向
	 */
	public int getDirection();
}
 