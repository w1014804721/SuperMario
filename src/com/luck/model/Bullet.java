package com.luck.model;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.interfaces.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
public class Bullet implements Kill,Moveable
{
	private int locaX;
	private int locaY;
	private Bullet bullet=this;
	private int step=0;
	public Flint withFlint=null;
	private double downSpeed=1;
	private int crashType=CrashType.WALL_U;
	private static final int maxStep=4; 
	private Thread stepThread=null;
	public static final int[] sizeX={36,33,35,35,37};
	public static final int[] sizeY={36,34,34,33,36};
	private Control control;
	private int direction=-1;//方向 
	private int speed=17;
	private Down downThread=null;
	private boolean down=false;//是否正在下降
	private boolean downDie=false;
	public boolean isDownDie() 
	{
		return downDie;
	}
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public Bullet(int locaX, int locaY, Control control,int direction) 
	{
		this.locaX = locaX;
		this.locaY = locaY;
		this.control = control;
		this.direction=direction;
		stepThread=new StepThread();
		stepThread.start();
		new Down().start();
	}
	public int getLocaX()
	{
		return locaX;
	}
	public void setLocaX(int locaX)
	{
		this.locaX=locaX;
	}
	public void move()//子弹移动
	{
		if(direction==-1)locaX-=speed;else
		if(direction==1)locaX+=speed;
	}
	private class StepThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				if(step+1>maxStep)step=0;else step++;
				try
				{
					sleep(20);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	public void hit(List<Dangerous> dangerous,List<Flint> flints,Mario mario)//子弹攻击
	{
		for(int i=0;i<dangerous.size();i++)
		{
			Dangerous d=dangerous.get(i);
			if(d.kill(this))
			{
				mario.getBullets().remove(this);
				if(stepThread!=null)
				{
					stepThread.stop();
					stepThread=null;
				}
				break;
			}
		}
		if(getCrashType()&&(crashType==CrashType.PIPE_L||crashType==CrashType.PIPE_R||crashType==CrashType.WALL_L||crashType==CrashType.WALL_R))
		{
			mario.getBullets().remove(this);
			if(stepThread!=null)
			{
				stepThread.stop();
				stepThread=null;
			}
		}
	}
	public Rectangle getRec()
	{
		return new Rectangle(locaX,locaY-sizeY[step],sizeX[step],sizeY[step]);
	}
	public void paint(Graphics g)
	{
		move();
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		Color c=g.getColor();
		if(direction==-1)
		g.drawImage(ImageTool.fireL[step], locaX, locaY-sizeY[step], null);else
		g.drawImage(ImageTool.fireR[step], locaX, locaY-sizeY[step], null);	
		g.setColor(c);
	}
	public void moveL() 
	{
		setLocaX(getLocaX()-control.getMario().getSpeed());
		if(getLocaX()<=-800||getLocaX()>1600)
		{
			control.getMario().getBullets().remove(this);
			if(stepThread!=null)
			{
				stepThread.stop();
				stepThread=null;
			}
		}
	}
	public void moveR() 
	{
		setLocaX(getLocaX()+control.getMario().getSpeed());
		if(getLocaX()<=-800||getLocaX()>1600)
		{
			control.getMario().getBullets().remove(this);
			if(stepThread!=null)
			{
				stepThread.stop();
				stepThread=null;
			}
		}
	}
	public int getType() 
	{
		return 2;
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
			Rectangle rec=null;
			while(true)//没有落地面上一直在循环	
			{
				rec=getRec();
				int site=locaY;
				if(rec.y+rec.height>=control.getCutLine())break;
				if(getCrashType()&&crashType==CrashType.NO_CRASH)//多线程重要判断应该靠紧
				{
					down=true;
					if(locaY+site>=control.getCutLine())break;
					double count=1;
					for(int i=site;i<=control.getCutLine();i+=(count+=0.1))
					{
						locaY=i;
						downSpeed=count;
						if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U))//如果在某个硬物的上方、启动二类下落线程且退出本线程
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
					if(!bullet.isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(bullet);
					}
					if(!bullet.isDownDie())downSpeed=1;
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
	public boolean getCrashType()//获取碰撞类型
	{
		List<Flint> flints=control.getFlints();
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
					withFlint=flint;
					if(withFlint!=null)crashType=withFlint.getCrashType(this.getDown(),this.direction,withFlint.getRec(),this.getRec());else crashType=CrashType.NO_CRASH;//调用硬物的获取碰撞类型方法
					return true;
				}
			}
			withFlint=null;
			crashType=CrashType.NO_CRASH;//没有发现碰撞物则碰撞类型为0
			return true;
		}
		}catch(Exception e)
		{
			return true;
		}
	}
	public int getDown() 
	{
		if(down)return-1;else return 0;
	}
	public int getDirection() 
	{
		return direction;
	}
	public void clearSpeed() 
	{
		this.speed=0;
	}
}
