package com.luck.interfaces;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luck.main.Model;
/**
 * 所有的可被碰撞的硬物都必须实现该接口
 * @author 王镜鑫
 *
 */
public interface Flint
{
	/**
	 * 获取外接矩形
	 * @return 外接矩形
	 */
	public Rectangle getRec();//获取外接矩形
	/**
	 * 
	 * @return 返回硬物的唯一种类标识。
	 */
	public int getFlag();//获取硬物类型
	/**
	 * 获取与硬物的碰撞类型
	 * @param down 表示传进去的对象的上下运动状态
	 * @param direction 表示传进去的对象的左右运动状态
	 * @param rec1 硬物的外接矩形
	 * @param rec2 可移动物的外接矩形
	 * @return 返回碰撞类型
	 */
	public int getCrashType(int down,int direction,Rectangle rec1,Rectangle rec2);//获取碰撞类型
	/**
	 * 
	 * @return 硬物的横坐标
	 */
	public int getLocaX();
	/**
	 * 
	 * @return 硬物上方包含的可生长物中原来存在现在不存在的可生长物的集合
	 */
	public Set<Growable> getDeletes();
	/**
	 * 设置横坐标
	 * @param locaX 传进去的横坐标
	 */
	public void setLocaX(int locaX);
	/**
	 * 左移
	 */
	public void moveL();
	/**
	 * 右移
	 */
	public void moveR();
	/**
	 * 画出本对象
	 * @param g 画板上下文
	 */
	public void paint(Graphics g);
	/**
	 * 
	 * @return 当前对象是否正在移动
	 */
	public boolean getMoving();
	/**
	 *  添加所包含的可生长物
	 * @param growable  被添加的可生长物
	 */
	public void setGrowable(Growable growable); 
	/**
	 * 
	 * @return 返回当前包含的可生长物的集合
	 */
	public List<Growable> getGrowable();
	/**
	 * 
	 * @return 返回是否在画板中可视
	 */
	public boolean canPaint();
	public int save(int count, Map<Integer, Model> saveModels);
}
