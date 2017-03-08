package com.luck.model;
import java.awt.*;

import com.luck.interfaces.*;
import com.luck.tool.*;

import java.util.List;
import java.util.Map;

import com.luck.main.*;
public class Monster implements Dangerous,Moveable
{
	private boolean dieing=false;
	private Thread countThread=null;
	private boolean die=false;
	private int locaX=0;
	private double downSpeed=1;
	private int locaY=0;
	private Monster monster=this;
	private Down downThread=null;
	private int crashType=CrashType.WALL_U;
	private int count=0;
	public Flint withFlint=null;
	private boolean down=false;//是否正在下降
	private boolean upDie=false; 
	public static final int sizeX=52;
	public static final int sizeY[]={57,58,61,58,58};
	private int step=0;
	private int speed=1;
	private boolean alive=true;
	private Control control;
	private int direction=1;
	private boolean downDie=false;
	public boolean isDownDie() 
	{
		return downDie;
	}
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	public Monster(int locaX, int locaY,int direction) 
	{
		this.locaX = locaX;
		this.locaY = locaY;
		this.direction=direction;
	}
	public void setControl(Control control)
	{
		this.control = control;
		new stepTheard().start();
		new Down().start();
	}
	private class stepTheard extends Thread
	{
		public void run()
		{
			while(true)
			{
				try 
				{
					sleep(100);
					if(step+1>=5)step=0;else
					step++;
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	private class countThread extends Thread
	{
		public void run()
		{
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
	public void paint(Graphics g)
	{
		Color c=g.getColor();
		if(die)
		{
			g.drawImage(ImageTool.dieMonster, locaX, locaY+40,null);
			return;
		}
		if(upDie)
		{
			if(countThread==null)
			{
				countThread=new countThread();
				countThread.start();
			}
			if(count<7)
			{
				if(direction==1)locaX+=3;else locaX-=3;;
				locaY-=3;
				g.drawImage(ImageTool.updie[0],locaX, locaY-53,null);
			}else
			if(count>=7&&count<=10)
			{
				if(direction==1)locaX+=3;else locaX-=3;
				locaY+=6;
				g.drawImage(ImageTool.updie[1],locaX, locaY-54,null);
			}else
			if(count>=10&&count<=200)
			{
				if(direction==1)locaX+=3;else locaX-=3;
				locaY+=6;
				g.drawImage(ImageTool.updie[2],locaX, locaY-54,null);
			}else
			{
				countThread.stop();
				countThread=null;
				control.getDangerous().remove(this);
			}
			return;
		}
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		if(direction==1&&step<5)
		g.drawImage(ImageTool.monsterRImage[step], locaX, locaY, null);
		if(direction==-1&&step<5)
		g.drawImage(ImageTool.monsterLImage[step], locaX, locaY, null);
		move();
		if (!isDownDie())
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
		if(step>=5)step=0;
		return new Rectangle(locaX, locaY, sizeX, sizeY[step]);
	}
	public void move() 
	{
		if(direction==1)locaX+=speed;else locaX-=speed;
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
			changeDirection();
			step=0;
		}
	}
	private class Dieing extends Thread
	{
		Monster monster=null;
		public Dieing(Monster monster)
		{
			control.getMario().jump();
			this.monster=monster;
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
			control.getDangerous().remove(monster);
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
				if(mario.getDown()==-1)
				{
					SoundTool.play(SoundTool.killSound);
					control.addScore(locaX, locaY, 200);
					new Dieing(this).start();
					return true;
				}
				else 
				{
					mario.setNoEn();
					if(mario.isBig())
					{
						mario.setBig(false);
					}else control.die();
					return true;
				}
			}else
			if(kill.getType()==2||(kill.getType()==3&&((Turtle)kill).getStatus()==2))
			{
				if(kill.getType()==2)
				control.addScore(locaX, locaY, 100);else
				control.addScore(locaX, locaY, 400);
				SoundTool.play(SoundTool.hite);
				direction=kill.getDirection();
				upDie=true;
				return true;
			}else
			if(kill.getType()==3&&((Turtle)kill).getStatus()==1)
			{
				changeDirection();
				return false;
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
					if(!monster.isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(monster);
					}
					if(!monster.isDownDie())downSpeed=1;
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
	private class getCrash extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				getCrashType();
				if(crashType==CrashType.NO_CRASH&&downThread==null&&!down&&locaY!=control.getCutLine())
				{
					new Down().start();
				}
				try 
				{
					sleep(10);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
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
							sumType+=x;
							type[x]=true;
						}
					}
				}
			}
			crashType=sumType;
		//	System.out.println(sumType);
			if(crashType==0)withFlint=null;
			return true;
		}
	}catch(Exception e)
	{
		return true;
	}
	}
	public boolean canPaint()
	{
		if(locaX<=1366&&locaX>=-100)return true;
		return false;
	}
	public void clearSpeed() 
	{
		this.speed=0;
	}
	public int getLocaY() 
	{
		return locaY;
	}
	public int getType()
	{
		return 4;
	}
	public void changeDirection() 
	{
		direction=-direction;
		if(direction==1)
		locaX+=2;else locaX-=2;
	}
	public int save(int count, Map<Integer, Model> saveModels) 
	{
		saveModels.put(count, new Model(locaX, locaY,-1,direction==1?Main.MONSTER_R:Main.MONSTER_L));
		return count+1;
	}
}
