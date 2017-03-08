package com.luck.model;
import java.awt.*;
import java.util.Map;
import com.luck.interfaces.*;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;
public class BadFlower implements Dangerous
{
	private int locaX=0;
	private int locaY=0;
	private int step=-5;
	private Pipe pipe=null;
	private final static int size=3;
	public static int[] f=new int[36*size];
	static 
	{
		for(int i=0;i<36;i++)
		for(int j=0;j<size;j++)
		{
			f[i*size+j]=i;
		}
	}
	private int[] sizeX={45,48,48,50,57,59,56,46,44,46,48,51,54,55,58,59,62,55,53,45,46,49,51,49,55,54,48,46,45,45,47,51,53,46,40,45};
	private int[] sizeY={11,15,18,19,20,21,30,33,38,43,49,46,46,46,55,59,67,58,58,57,53,51,48,38,34,31,32,32,31,27,22,19,14,10,6,5};
	private Control control;
	/**
	 * 
	 * @param locaX 横坐标
	 * @param locaY 纵坐标
	 * @param pipe 食人花所依附的管道
	 */
	public BadFlower(int locaX,int locaY,Pipe pipe)
	{
		this.locaX=locaX;
		this.locaY=locaY;
		this.pipe=pipe;
	}
	/**
	 * 
	 * @param control 设置管家类，获取管家类的引用
	 */
	public void setControl(Control control)
	{
		this.control = control;
	}
	/**
	 * 
	 * @return 返回位置横坐标
	 */
	public int getLocaX() 
	{
		return locaX;
	}
	/**
	 * 
	 * @param locaX 设置位置横坐标
	 */
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	/**
	 * 绘画方法
	 */
	public void paint(Graphics g) 
	{
		if(pipe!=null&&control.getMario().getWithFlint()==pipe&&control.getMario().getcrashType()==CrashType.PIPE_U)
		{
			return;
		}
		Color c=g.getColor();
		if(step>=0&&step<35*(size)-1) 
		{
			kill(control.getMario());
			g.drawImage(ImageTool.badflowerImages[f[step]], locaX+((71-sizeX[f[step]])>>1), locaY-sizeY[f[step]], null);
		}
		if(step<35*(size+1))step++;else step=-500;
	}
	/**
	 * 获取外接矩形
	 */
	public Rectangle getRec()
	{
		if(step<0||step>=35*(size)-1)return new Rectangle(locaX,locaY,0,0);
		return new Rectangle(locaX+((71-sizeX[f[step]])>>1),locaY-sizeY[f[step]],sizeX[f[step]],sizeY[f[step]]);
	}
	/**
	 * 尝试杀死马里奥或者被子弹杀死
	 */
	public boolean kill(Kill kill)
	{
		if(this.getRec().intersects(kill.getRec()))
		{
			if(kill.getType()==1)
			{
				Mario mario=(Mario)kill;
				if(mario.isNoEnemy())
				{
					SoundTool.play(SoundTool.killSound);
					control.getDangerous().remove(this);
					return true;
				}
				if(this.getRec().intersects(mario.getRec()))
				{
					if(mario.isNoEn())return true;
					mario.setNoEn();
					if(mario.isBig())
					{
						mario.setBig(false);
					}else control.die();
					return true;
				}
			}else
			if(kill.getType()==2)
			{
				SoundTool.play(SoundTool.killSound);
				control.getDangerous().remove(this);
				return true;
			}
		}
		return false;
	}
	public void moveL() 
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getDangerous().remove(this);	
	}
	public void moveR() 
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getDangerous().remove(this);	
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public int getType()
	{
		return -1;
	}
	public void changeDirection() 
	{
		
	}
	public boolean isDownDie()
	{
		return false;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		saveModels.put(count, new Model(locaX, locaY,count+1,Main.BADFLOWER));
		count++;
		saveModels.put(count, new Model(locaX, locaY, -1, Main.PIPE));
		return count+1;
	}
}
