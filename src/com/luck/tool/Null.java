package com.luck.tool;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Map;

import com.luck.interfaces.Growable;
import com.luck.main.Model;
import com.luck.model.Mario;
/**
 * 
 * @author 王镜鑫
 * 这个类包含了growable的一个空实现，用于传给包含金币的砖块
 */
public class Null implements Growable 
{

	public void setNeedDraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public boolean isNeedDraw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eaten(Mario mario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLocaX() {
		return 0;
	}

	@Override
	public int getLocaY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLocaX(int locaX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFlag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLocaY(int locaY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSizeY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveR() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUping(boolean uping) {
		// TODO Auto-generated method stub
		
	}
	public boolean canPaint()
	{
		return false;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(int count, Map<Integer, Model> saveModels) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
