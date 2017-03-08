package com.luck.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;


import java.util.Map;

import com.luck.interfaces.Dangerous;
import com.luck.interfaces.Flint;
import com.luck.interfaces.Kill;
import com.luck.interfaces.Moveable;
import com.luck.main.Control;
import com.luck.main.Main;
import com.luck.main.Model;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;

public class Turtle implements Dangerous,Kill,Moveable
{
	private int status=1;//保存乌龟状态，0是正常的行走，1是龟壳停下，2是龟壳跑动
	private boolean die=false;
	private int locaX=0;
	private Turtle turtle=this;
	private boolean down=false;//是否正在下降
	private int crashType=CrashType.WALL_U;
	private Down downThread=null;
	private Flint withFlint=null;
	private Thread countThread=null;
	private Thread walk=null;
	private Thread run=null;
	private int locaY=0;
	private double downSpeed=1;
	private int count=0;
	private int rollStep=0;
	private boolean upDie=false;
	private static int maxSize=11;
	private static int maxRollSize=10; 
	public static final int rollX[]={41,45,42,43,45,44,42,43,44,44,38};
	public static final int rollY[]={43,43,39,42,39,43,42,41,43,42,42};
	public static final int sizeX[]={49,50,54,53,50,50,48,49,52,54,53,51};
	public static final int sizeY[]={67,64,64,64,66,65,67,67,66,65,66,64};
	private int step=0;
	private int[] speed={1,0,7};
	private boolean alive=true;
	private Control control;
	private int direction=1;
	private boolean canChangeStatus=true;
	private boolean downDie=false;
	public boolean isDownDie() 
	{
		return downDie;
	}
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	public Turtle(int locaX, int locaY,int direction) 
	{
		this.locaX = locaX;
		this.locaY = locaY;
		this.direction=direction;
	}
	public void setControl(Control control)
	{
		this.control = control;
		setStatus(0);
		new Down().start();
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		if(status==0)//走
		{
			if(run!=null)
			{
				run.stop();
				run=null;
			}
			walk=new stepTheard();
			walk.start();
//			control.getThreads().add(walk);
		}else
		if(status==1)//停
		{
			if(run!=null)
			{
				run.stop();
				run=null;
			}
			if(walk!=null)
			{
				walk.stop();
				walk=null;
			}
		}else
		if(status==2)//跑
		{
			if(walk!=null)
			{
				walk.stop();
				walk=null;
			}
			run=new rollThread();
			run.start();
		}
		this.status = status;
	}
	private class statusThread extends Thread 
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			canChangeStatus=false;
			try 
			{
				sleep(300);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			canChangeStatus=true;
		}
	}
	private class countThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				count++;
				try 
				{
					sleep(50);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	private class stepTheard extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				try 
				{
					sleep(100);
		//			System.out.println(step);
					if(step+1<=maxSize)step++;else step=0;
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	private class rollThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				try 
				{
					sleep(25);
					if(rollStep+1<=maxRollSize)rollStep++;else rollStep=0;
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	public void paint(Graphics g)
	{
		Color c=g.getColor();
	//	g.drawRect(rec.x, rec.y, rec.width, rec.height);
		if(upDie)
		{
			if(countThread==null)
			{
				countThread=new countThread();
				countThread.start();
			}
			if(count<7)
			{
				locaX+=3;
				locaY-=3;
				g.drawImage(ImageTool.tortoiseRoll[3],locaX, locaY,null);
			}else
			if(count>=7&&count<=200)
			{
				locaX+=3;
				locaY+=6;
				g.drawImage(ImageTool.tortoiseRoll[3],locaX, locaY,null);
			}else
			{
				countThread.stop();
				countThread=null;
				control.getDangerous().remove(this);
			}
			return;
		}
		if(direction==1)
		{
			if(status==0)g.drawImage(ImageTool.tortoiseR[step], locaX, locaY, null);else
			if(status==1)g.drawImage(ImageTool.tortoiseRoll[9], locaX, locaY+22,null);else
			if(status==2)g.drawImage(ImageTool.tortoiseRoll[rollStep], locaX, locaY+22,null);
		}
		if(direction==-1)
		{
			if(status==0)g.drawImage(ImageTool.tortoiseL[step], locaX, locaY, null);else
			if(status==1)g.drawImage(ImageTool.tortoiseRoll[9], locaX, locaY+22,null);else
			if(status==2)g.drawImage(ImageTool.tortoiseRoll[rollStep], locaX, locaY+22,null);
		}
		move();
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		if(!isDownDie())
		{
			kill(control.getMario());
			for(int i=0;i<control.getKills().size();i++)
			{
				kill(control.getKills().get(i));
			}
		}
		g.setColor(c);
	}
	public Rectangle getRec()
	{		
		if(status==0)return new Rectangle(locaX, locaY, sizeX[step], sizeY[step]);
		if(status==1)return new Rectangle(locaX, locaY, rollX[9], rollY[9]+22);
		return new Rectangle(locaX, locaY, rollX[rollStep], rollY[rollStep]);
	}
	public void move() 
	{
		if(direction==1)locaX+=speed[status];else locaX-=speed[status];
		if(status!=2)
		for(int i=0;i<control.getDangerous().size();i++)
		{
			Dangerous d=control.getDangerous().get(i);
			if(d!=this&&d.getRec().intersects(this.getRec()))
			{
				changeDirection();
				d.changeDirection();
				step=0;
			}
		}
		if(getCrashType()&&(crashType==CrashType.WALL_L||crashType==CrashType.WALL_R||crashType==CrashType.PIPE_L||crashType==CrashType.PIPE_R||crashType==CrashType.WUWL||crashType==CrashType.WUWR))
		{
			this.direction=-direction;
			if(direction==1)
			locaX+=2;else locaX-=2;
		}
	}
	private class Dieing extends Thread
	{
		Turtle turtle=null;
		public Dieing(Turtle turtle)
		{
			control.getMario().jump();
			this.turtle=turtle;
		}
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			die=true;
			try 
			{
				sleep(1000);
			} catch (InterruptedException e) 
			{
			}
			control.getDangerous().remove(turtle);
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
			Rectangle rec=null;
			while(true)//没有落地面上一直在循环
			{
				rec=getRec();
				if(rec.y+rec.height>=control.getCutLine())break;
				int site=locaY;
			//	System.out.println(crashType);
				if(getCrashType()&&crashType==CrashType.NO_CRASH)//多线程重要判断应该靠紧
				{
					double count=1;
					for(int i=site;i<=control.getCutLine()-sizeY[3];i+=(count+=0.1))
					{
						locaY=i;
						if(!isDownDie())downSpeed=count;
						if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//如果在某个硬物的上方、启动二类下落线程且退出本线程
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
					if(!turtle.isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(turtle);
					}
					if(!turtle.isDownDie())downSpeed=1;
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
	public boolean kill(Kill kill)
	{
		if(upDie)return false;
		if(this.getRec().intersects(kill.getRec()))
		{
			if(kill.getType()==1)
			{
				Mario mario=(Mario)kill;
				if(mario.isNoEn())return false;
				if(mario.isNoEnemy())
				{
					SoundTool.play(SoundTool.killSound);
					control.addScore(locaX, locaY, 200);
					upDie=true;
					return true;
				}
				if(canChangeStatus&&status==1)
				{
					this.direction=mario.getDirection();
					new statusThread().start();
					setStatus(2);
					control.getKills().add(this);
					return true;
				}
				if(mario.getDown()==-1)//下落状态
				{
					SoundTool.play(SoundTool.killSound);
					if(canChangeStatus&&status==0)
					{
						new statusThread().start();
						setStatus(1);
						control.addScore(locaX, locaY, 200);
						control.getMario().jump();
						return true;
					}else
					if(canChangeStatus&&status==2)
					{
						new statusThread().start();
						setStatus(1);
						control.getMario().jump();
						return true;
					}
					return true;
				}
				else if(canChangeStatus)
				{
					mario.setNoEn();
					if(mario.isBig())
					{
						mario.setBig(false);
					}else control.die();
					return true;
				}
			}else
			if((kill.getType()==2)||(kill.getType()==3&&kill!=this&&((Turtle)kill).getStatus()==2))
			{			
				if(kill.getType()==2)
				control.addScore(locaX, locaY, 100);else
				control.addScore(locaX, locaY, 400);
				SoundTool.play(SoundTool.hite);
				upDie=true;
				return true;
			}
		}
		return false;
	}
	public int getLocaX() 
	{
		return locaX;
	}
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
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
	public int getType()
	{
		return 3;
	}
	public int getDown() 
	{
		if(down)return-1;else return 0;
	}
	public boolean getCrashType() 
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
				Rectangle rec1=null;
				try 
				{
					rec1=flint.getRec();
				} catch (Exception e) 
				{
					return true;
				}
				Rectangle rec2=this.getRec();
				if(rec1.intersects(rec2))
				{
					if(withFlint==flint)continue;
					withFlint=flint;
					if(withFlint!=null)
					{
						int x=withFlint.getCrashType(this.getDown(),direction,withFlint.getRec(),this.getRec());//调用硬物的获取碰撞类型方
						if(type[x]!=true)
						{
						//	System.out.println(x);
							sumType+=x;
							type[x]=true;
						}
					}
				}
			}
			crashType=sumType;
	//		if(sumType!=32)System.out.println(sumType);
			if(crashType==0)withFlint=null;
			return true;
		}
	}catch(Exception e)
	{
		return true;
	}
	}
	public int getDirection() 
	{
		return direction;
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public void clearSpeed() 
	{
		this.speed[0]=0;
		this.speed[1]=0;
		this.speed[2]=0;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public void changeDirection() 
	{
		if(getStatus()==2) return;
		direction=-direction;
		if(direction==1)
		locaX+=2;else locaX-=2;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		saveModels.put(count, new Model(locaX, locaY,-1,direction==1?Main.TURTLE_R:Main.TURTLE_L));
		return count+1;
	}
}
