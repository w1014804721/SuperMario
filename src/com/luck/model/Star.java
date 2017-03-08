package com.luck.model;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.luck.interfaces.*;
import com.luck.main.Control;
import com.luck.main.Main;
import com.luck.main.Model;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;
public class Star implements Growable,Moveable
{
	public static final int flagNumber=3;
	private Down downThread=null;
	private int locaX;
	private Star star=this;
	private int locaY;
	private double downSpeed=1;
	private int direction=1;
	private int speed=1;
	private boolean uping=false;//是否随着砖移动
	private boolean moving=false;//是否正在移动
	public static final int sizeX=50;
	public Flint withFlint=null; 
	public static final int sizeY=50;
	private int drawstep=0;
	private boolean down=false;//是否正在下降
	private boolean needDraw=false;
	private int crashType=CrashType.WALL_U;
	private Control control;
	private boolean downDie=false;
	public boolean isDownDie() 
	{
		return downDie;
	}
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	public Star(int locaX,int locaY)
	{
		this.locaX=locaX;
		this.locaY=locaY;
	}
	public void setControl(Control control)
	{
		this.control = control;
	}
	public int getLocaY() 
	{
		return locaY;
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
		return sizeY;
	}
	public int getFlag()
	{
		return flagNumber;
	}
	public boolean isNeedDraw()
	{
		return needDraw;
	}
	public boolean isUping() 
	{
		return uping;
	}
	public void setUping(boolean uping) 
	{
		this.uping = uping;
	}
	private class TimeThread extends Thread
	{
		public void run()
		{
			try
			{
				sleep(20000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			if(!control.isWin()&&!control.isAllWin())SoundTool.back();
			control.getMario().setNoEnemy(false);
		}
	}
	public void eaten(Mario mario)
	{
		if(getRec().intersects(mario.getRec()))
		{
			mario.setNoEnemy(true);
			control.addScore(locaX, locaY, 1000);
			SoundTool.play(SoundTool.eatSound);
			SoundTool.over();
			new TimeThread().start();
			SoundTool.play(SoundTool.noEnemy);
			control.getGrowables().remove(this);
		}
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
	public Rectangle getRec()
	{
		return new Rectangle(locaX,locaY,sizeX,sizeY);
	}
	public void paint(Graphics g)
	{
		Color c=g.getColor();
		if(!needDraw)return;
		eaten(control.getMario());
		move();
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		g.drawImage(ImageTool.starImage,locaX,locaY, null);
		if(drawstep<4)drawstep++;
		if(drawstep>=4&&!moving)
		{
			new Down().start();
			moving=true;
		}
		g.setColor(c);
	}
	public boolean getCrashType()//获取碰撞类型
	{
		List<Flint> flints=control.getFlints();
		boolean[] type=new boolean[65];
		int sumType=0;
		try
		{
		synchronized (flints)
		{
			for(Flint flint:flints)//判断是否碰撞到pipe
			{
				Rectangle rec1=flint.getRec();
				Rectangle rec2=this.getRec();
				if(rec1.intersects(rec2))
				{
					if(withFlint==flint)continue;
					if(withFlint!=null&&withFlint!=flint)
					{
						if(withFlint!=null&&withFlint.getGrowable().contains(this))
						try
						{
							if(withFlint!=null&&withFlint.getDeletes()!=null)withFlint.getDeletes().add(this);
						}catch (Exception e)
						{
							e.printStackTrace();
							return true;
						}
					}
					withFlint=flint;
					if(flint!=null)
					{
						int x=flint.getCrashType(this.getDown(),direction,flint.getRec(),this.getRec());//调用硬物的获取碰撞类型方法
						if(x==CrashType.WALL_U)
						{
							if(withFlint!=null&&!withFlint.getGrowable().contains(this))withFlint.setGrowable(this);
						}
						if(type[x]!=true)
						{
							sumType+=x;
							type[x]=true;
						}
					}
				}
			}
			crashType=sumType;
			if(crashType==0)withFlint=null;
			if(withFlint!=null&&withFlint.getGrowable().contains(this))
			if(withFlint!=null&&withFlint.getDeletes()!=null)withFlint.getDeletes().add(this);
		}
		}catch (Exception e) 
		{
			e.printStackTrace();
		}finally
		{
			return true;
		}
	}
	private class Down extends Thread//二类下落线程   //控制最终下落的线程如果在正常下落时由于碰撞被打断则启动该线程监视是否需要再次落下
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			if(downThread!=null&&downThread!=this)
			{
				downThread.stop();
				downThread=null;
			}
			downThread=this;
			while(true)//没有落地面上一直在循环
			{
				int site=locaY;
				if(locaY+sizeY>=control.getCutLine())break;
			//	System.out.println("-"+crashType);
				if(getCrashType()&&crashType==CrashType.NO_CRASH)//多线程重要判断应该靠紧
				{
			//		System.out.println("*"+crashType);
					down=true;
					double count=1;
					for(int i=site;i<=control.getCutLine()-sizeY;i+=(count+=0.1))
					{
	//					System.out.println(i);
						locaY=i;				
						if(!isDownDie())downSpeed=count;
						if(getCrashType()&&crashType!=CrashType.NO_CRASH)//如果在某个硬物的上方、启动二类下落线程且退出本线程
						{
							down=false;
							new Down().start();
							return;
						}
						try 
						{
							sleep(10);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					if(!star.isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(star);
					}
					if(!star.isDownDie())downSpeed=1;
				}
				if(down)//在该线程中如果已经下落则跳出 
				{
					down=false;
					downThread=null;
					return;
				}
			}
		}
	}
	public int getDown()
	{
		if(down)return-1;else return 0;
	}
	public void move()//移动方法 
	{
		if(direction==1)locaX+=speed;else locaX-=speed;
		if(getCrashType()&&(crashType==CrashType.WALL_L||crashType==CrashType.WALL_R||crashType==CrashType.PIPE_L||crashType==CrashType.PIPE_R||crashType==CrashType.WUWL||crashType==CrashType.WUWR))
		{
			direction=-direction;
			if(direction==1)
			locaX+=2;else locaX-=2;
		}
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public int getType() 
	{
		return 3;
	}
	public void clearSpeed() 
	{
		this.speed=0;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		if(!isNeedDraw())
		{
			saveModels.put(count,new Model(locaX,locaY,count+1, Main.STAR));
			return Main.WALL_WITH_STAR;
		}else 
		{
			saveModels.put(count, new Model(locaX, locaY, -1, Main.STAR));
			System.out.println("#star");
			return count+1;
		}
	}
}
