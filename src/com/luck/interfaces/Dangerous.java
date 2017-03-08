package com.luck.interfaces;
import java.awt.*;
import java.util.Map;

import com.luck.main.Model;
/**
 * 
 * @author 王镜鑫
 * 所有可杀死马里奥的类都要实现该接口
 *
 */
public interface Dangerous  
{
	/**
	 * 绘画方法
	 * @param g 画板上下文
	 */
	public void paint(Graphics g);
	/**
	 * 获取对象的外接矩形
	 * @return 对象的外接矩形
	 */
	public Rectangle getRec();
	/**
	 * 
	 * @param kill 攻击对象
	 * @return 返回是否杀死kill
	 */
	public boolean kill(Kill kill);
	/**
	 * 全局左移，产生全局移动现象
	 */
	public void moveL();
	/**
	 * 全局右移，产生全局移动现象
	 */
	public void moveR(); 
	/**
	 * 
	 * @return 判断是否在面板上以判断是否需要被绘画
	 */
	public boolean canPaint();
	/**
	 * 
	 * @return 返回纵坐标
	 */
	public int getLocaY();
	/**
	 *  
	 * @return 获取类型
	 */
	public int getType();
	/**
	 * 改变运动方向
	 */
	public void changeDirection();
	public boolean isDownDie();
	public int save(int count, Map<Integer, Model> saveModels);
}
