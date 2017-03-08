package com.luck.model;
import com.luck.tool.*;

import java.awt.*;
import java.util.Map;

import com.luck.interfaces.Growable;
import com.luck.main.*;
public class Money
{
	private int locaX=0;
	private int locaY=0;
	public static final int sizeX=52;
	public static final int sizeY=50;
	private Control control=null;
	/**
	 * 
	 * @param locaX 横坐标
	 * @param locaY 纵坐标
	 */
 	public Money(int locaX,int locaY)
	{
		this.locaX=locaX;
		this.locaY=locaY; 
	}
 	/**
 	 * 
 	 * @param control 管家类的引用
 	 */
 	public void setControl(Control control)
	{
		this.control = control;
	}
 	public void paint(Graphics g)
 	{
 		eaten(control.getMario());
 		g.drawImage(ImageTool.moneyImage, locaX, locaY, null);
 		
 	}
 	/**
 	 * 
 	 * @param mario 尝试被马里奥吃掉
 	 */
	private void eaten(Mario mario)
	{
		if(mario.getRec().intersects(this.getRec()))
		{
			control.addMoneys();
			SoundTool.play(SoundTool.eatMoneySound);
			control.getMoneys().remove(this);
		}
	}
	/**
	 * 
	 * @return 获取外接矩形
	 */
	private Rectangle getRec() 
	{
		return new Rectangle(locaX, locaY, sizeX, sizeY);
	}
	public void moveR() 
	{
		this.locaX+=control.getMario().getSpeed();
	}
	public void moveL() 
	{
		this.locaX-=control.getMario().getSpeed();
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		saveModels.put(count, new Model(locaX, locaY, -1, Main.MONEY));
		return count++;
	}
}
