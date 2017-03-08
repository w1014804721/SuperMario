package com.luck.interfaces;
import java.awt.*;
import java.util.Map;

import com.luck.main.Model;
import com.luck.model.*;
/**
 * 
 * @author 王镜鑫
 *所有的可生长物都必须实现该接口
 */
public interface Growable
{
	/**
	 * 设置需要被画出来
	 */
	public void setNeedDraw();//设置需要被画出来
	public void paint(Graphics g);
	/**
	 * 
	 * @return 返回是否需要被画出来
	 */
	public boolean isNeedDraw();//查看是否需要画出来
	/**
	 * 该可生长物被马里奥吃
	 * @param mario 可以去吃他的马里奥
	 */
	public void eaten(Mario mario);//被马里奥吃
	/**
	 * 
	 * @return 获取横坐标
	 */
	public int getLocaX();
	/**
	 * 
	 * @return 获取纵坐标
	 */
	public int getLocaY();
	public void setLocaX(int locaX);
	public Rectangle getRec();
	/**
	 * 
	 * @return 获取外接矩形
	 */
	public int getFlag();//获取是哪一种被吃的
	public void setLocaY(int locaY);
	public int getSizeY();
	public void moveL(); 
	public void moveR();
	/**
	 * 
	 * @param uping 是否正在被顶上去
	 */
	public void setUping(boolean uping);
	public int getType();
	public boolean canPaint();
	public int save(int count, Map<Integer, Model> saveModels);
}
