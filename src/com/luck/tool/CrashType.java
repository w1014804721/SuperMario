package com.luck.tool;
/**
 * 
 * @author 王镜鑫
 * 包含了所有的碰撞类型的类
 */
public class CrashType 
{
	public static final double POINT=0.05;
	/**
	 * 从左边碰撞pipe
	 */
	public static final int PIPE_L=1;//从左边碰撞pipe
	/**
	 * 从右边碰撞pipe
	 */
	public static final int PIPE_R=2;//从右边碰撞pipe
	/**
	 * 从上面碰撞pipe
	 */
	public static final int PIPE_U=4;//从上面碰撞pipe
	/**
	 * 从左边碰撞wall
	 */
	public static final int WALL_L=8;//从左边碰撞wall
	/**
	 * 从右边碰撞wall
	 */
	public static final int WALL_R=16;//从右边碰撞wall
	/**
	 * 从上面碰撞walle
	 */
	public static final int WALL_U=32;//从上面碰撞wall
	/**
	 * 从下面碰撞wall
	 */
	public static final int WALL_D=64;//从下面碰撞wall
	/**
	 * 从上边左边碰撞wall
	 */
	public static final int WUWL=40;//
	/**
	 * 从上边右边碰撞wall
	 */
	public static final int WUWR=48;//
	/**
	 * 没有碰撞
	 */
	public static final int NO_CRASH=0;//没有碰撞
}
 