package com.luck.model;
import java.awt.*;
import java.util.Map;

import com.luck.interfaces.*;
import com.luck.main.*;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;
public class Flower implements Growable
{
	public static final int flagNumber=1;
	private int locaX;
	private int locaY;
	public static final int sizeX=40;
	public static final int[] sizeY={10,20,30,40,50};
	private int drawstep=0;
	private boolean needDraw=false;
	private Control control;
	private boolean uping;
	public Flower(int locaX,int locaY)
	{
		this.locaX=locaX; 
		this.locaY=locaY;
	}
	public void setControl(Control control)
	{
		this.control = control;
	}
	public int getLocaX() 
	{
		return locaX;
	}
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	public void setLocaY(int locaY)
	{
		this.locaY=locaY;
	}
	public void setNeedDraw()  
	{
		needDraw=true;
	}
	public int getSizeX() 
	{
		return sizeX;
	}
	public int getSizeY() 
	{
		return sizeY[drawstep];
	}
	public int getSizeY_M() 
	{
		return sizeY[4];
	}
	public int getFlag()
	{
		return flagNumber;
	}
	public boolean isNeedDraw()//是否需要画出来
	{
		return needDraw;
	}
	public void setNeedDraw(boolean needDraw) 
	{
		this.needDraw = needDraw;
	}
	public void eaten(Mario mario)//被马里奥吃掉
	{
		if(getRec().intersects(mario.getRec()))
		{
			mario.canAtack(true);
			control.addScore(locaX, locaY, 1000);
			SoundTool.play(SoundTool.eatSound);
			control.getGrowables().remove(this);
		}
	}
	public Rectangle getRec()
	{
		return new Rectangle(locaX+5,locaY,sizeX,sizeY[drawstep]);
	}
	public void paint(Graphics g)
	{
		if(!needDraw)return;
		eaten(control.getMario());
		Color c=g.getColor();
		g.drawImage(ImageTool.flowerImage[drawstep],locaX,locaY, null);
		if(drawstep<4)
		drawstep++;
		g.setColor(c);
	}
	public void moveL()
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public void moveR()
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public void setUping(boolean uping) 
	{
		this.uping=uping;
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int getType() 
	{
		return 6;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		if(!isNeedDraw())
		{
			saveModels.put(count,new Model(locaX,locaY,count+1, Main.FLOWER));
			return Main.WALL_WITH_FLOWER;
		}else 
		{
			saveModels.put(count, new Model(locaX, locaY, -1, Main.FLOWER));
			System.out.println("#flower");
			return count+1;
		}
	}
}
