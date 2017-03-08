package com.luck.interfaces;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * 所有可移动类都要实现该接口
 * @author 王镜鑫
 *
 */
public interface Moveable 
{
	/**
	 * 
	 * @return 返回上下的运动状态
	 */
	public int getDown();
	/**
	 * 可移动物进行移动
	 */
	public void move();
	/**
	 * 
	 * @return 返回外接矩形
	 */
	public Rectangle getRec();
	public void paint(Graphics g);
	/**
	 * 
	 * @return 返回该可移动物和flint的碰撞类型
	 */
	public boolean getCrashType();
	/**
	 * 
	 * @return 返回纵坐标
	 */
	public int getLocaX();
	/**
	 * 
	 * @param downDie 设置该可移动物死亡
	 */
	public void setDownDie(boolean downDie);
	/**
	 * 
	 * @return 返回该可移动物是否已死
	 */
	public boolean isDownDie();
	/**
	 * 
	 * @param locaX 设置横坐标
	 */
	public void setLocaX(int locaX);
	/**
	 * 在下落到hole中之后把速度设成0
	 */
	public void clearSpeed();
}
 