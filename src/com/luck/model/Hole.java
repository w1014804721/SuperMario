package com.luck.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Map;

import com.luck.interfaces.Moveable;
import com.luck.main.Control;
import com.luck.main.Main;
import com.luck.main.Model;
import com.luck.tool.ImageTool;

public class Hole
{
	private int locaX=0;
	private Control control=null;
	private final int size=167;
	public Hole(int locaX)
	{
		this.locaX=locaX;
	}
	public void setControl(Control control)
	{
		this.control=control;
	}
	public int getLocaX() 
	{
		return locaX;
	}
	public void moveL()
	{
		locaX=(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public void moveR()
	{
		locaX=(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getGrowables().remove(this);	
	}
	public void paint(Graphics g)
	{
		g.drawImage(ImageTool.hole,locaX,control.getCutLine(),null);
	}
	public void DownDie(Moveable moveable)
	{
		Rectangle rec=moveable.getRec();
		int x=rec.x;
		int x2=x+rec.width;
		int y=rec.y+rec.height;
		if(x>locaX+28&&x2<locaX+size-30&&y+10>control.getCutLine())
		{
			moveable.setDownDie(true);
			moveable.clearSpeed();
		}
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		saveModels.put(count, new Model(locaX,1, -1, Main.HOLE));
		return count+1;
	}
}
