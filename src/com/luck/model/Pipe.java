package com.luck.model;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luck.interfaces.*;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
public class Pipe implements Flint//实现硬物接口
{
	private int locaX=0;
	private int sizeX=71;
	private int sizeY=147;
	public static final int flag=1;/*作为辨别是哪一种硬物的标志*/
	private Control control=null;
	private boolean bad=false;
	private List<Growable> growables=new ArrayList<Growable>();//包含的可生长物
	public void setControl(Control control)
	{
		this.control = control;
	}
	public Pipe(int locaX,boolean bad)
	{
		this.locaX=locaX;
		this.bad=bad;
	}
	public void setGrowable(Growable growable) 
	{ 
		this.growables.add(growable);
	}
	public int getLocaX() 
	{
		return locaX;
	}
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	public int getFlag()
	{
		return flag;
	}
	public void paint(Graphics g)//画出管子
	{
		Color c=g.getColor();
		g.setColor(Color.black);
		g.drawImage(ImageTool.pipeImage, locaX, control.getCutLine()-sizeY, null);
		g.setColor(c);
	}
	public Rectangle getRec()//获取外接矩形
	{
		return new Rectangle(locaX,control.getCutLine()-sizeY,sizeX,sizeY);
	}
	public void moveL()
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getFlints().remove(this);
	}
	public void moveR()
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800)control.getFlints().remove(this);
	}
	public boolean getMoving() 
	{
		return false;
	}
	public int getCrashType(int down,int direction,Rectangle rec1,Rectangle rec2)//rec1为桶，rec2为Mario
	{
		//rec1是硬物。rec2是移动物
				int rec1X=(rec1.x+rec1.x+rec1.width)>>1;
				int rec1Y=(rec1.y+rec1.y+rec1.height)>>1;
				int rec2X=(rec2.x+rec2.x+rec2.width)>>1;
				int rec2Y=(rec2.y+rec2.y+rec2.height)>>1;
				int width=rec1.width+rec2.width;
				int hight=rec1.height+rec2.height;
				if(rec2Y>rec1Y)
				{
					if(rec1X>rec2X)
					{
						if((rec1X-rec2X)/((double)width)>=(rec2Y-rec1Y)/(double)hight)return CrashType.PIPE_L;else return CrashType.NO_CRASH;
					}else
					{
						if((rec2X-rec1X)/((double)width)>=(rec2Y-rec1Y)/(double)hight)return CrashType.PIPE_R;else return CrashType.NO_CRASH;
					}
				}else
				{
					if(rec1X>rec2X)
					{
						if((rec1X-rec2X)/((double)width)>=(rec1Y-rec2Y)/(double)hight)return CrashType.PIPE_L;else return CrashType.PIPE_U; 
					}else
					{
						if((rec2X-rec1X)/((double)width)>=(rec1Y-rec2Y)/(double)hight)return CrashType.PIPE_R;else return CrashType.PIPE_U; 
					}
				}
	}
	public List<Growable> getGrowable() 
	{
		return growables;
	}
	public Set<Growable> getDeletes() 
	{
		return new HashSet<Growable>();
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		if(!bad)
		{
			saveModels.put(count,new Model(locaX, 0, -1, Main.PIPE) );
			count++;
		}
		return count;
	}
}
