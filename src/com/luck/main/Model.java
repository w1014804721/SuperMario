package com.luck.main;

import java.awt.Rectangle;

public class Model
{
	private int x;
	private int y;
	private int type;
	private int sizeX;
	private boolean add=true;
	private int sizeY;
	private int link=-1;
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public int getType() 
	{
		return type;
	}
	public int getSizeX() 
	{
		return sizeX;
	}
	public boolean isAdd() 
	{
		return add;
	}
	public int getSizeY() 
	{
		return sizeY;
	}
	public int getLink() 
	{
		return link;
	}
	public void setAdd(boolean add) 
	{
		this.add = add;
	}
	public Model(int x,int y,int link,int type)
	{
		this.x=x;
		this.y=y;
		this.type=type;
		this.link=link;
		this.sizeX=Main.SIZE_X[this.type];
		this.sizeY=Main.SIZE_Y[this.type];
	}
	public Rectangle getRec()
	{
		return new Rectangle(x, y, sizeX, sizeY);
	}
}