package com.luck.model;
import com.luck.interfaces.Flint;
import com.luck.interfaces.Moveable;
import com.luck.main.*;
import com.luck.tool.CrashType;
import com.luck.tool.ImageTool;
import com.luck.tool.SoundTool;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.luck.interfaces.*;
public class Mario implements Moveable,Kill
{
	private boolean winSound=false;
	private Down downThread=null;
	private Thread upThread=null;
	private int locaX=0;
	private int locaY=0;
	private boolean speedUp=false;
	private int pressCount=0;
	private int few=5;
	private double downSpeed=0;
	private JumpThread jumpThread=null;
	private boolean noEn=false;
	private static double Bbei=1.2;
	private static double Sbei=0.9;
	private static final int MAX_SIZE=24;
	private static final int[] BIG_sizeX={(int)(56*Bbei),(int)(58*Bbei),(int)(58*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(53*Bbei),(int)(48*Bbei),(int)(43*Bbei),(int)(44*Bbei),(int)(49*Bbei),(int)(55*Bbei),(int)(59*Bbei),(int)(56*Bbei),(int)(55*Bbei),(int)(43*Bbei),(int)(38*Bbei),(int)(35*Bbei),(int)(37*Bbei),(int)(35*Bbei),(int)(39*Bbei),(int)(40*Bbei),(int)(48*Bbei),(int)(53*Bbei),(int)(54*Bbei)};
	private static final int[] BIG_sizeY={(int)(81*Bbei),(int)(78*Bbei),(int)(78*Bbei),(int)(76*Bbei),(int)(76*Bbei),(int)(76*Bbei),(int)(79*Bbei),(int)(76*Bbei),(int)(80*Bbei),(int)(80*Bbei),(int)(80*Bbei),(int)(81*Bbei),(int)(82*Bbei),(int)(81*Bbei),(int)(79*Bbei),(int)(77*Bbei),(int)(79*Bbei),(int)(79*Bbei),(int)(76*Bbei),(int)(75*Bbei),(int)(80*Bbei),(int)(83*Bbei),(int)(83*Bbei),(int)(82*Bbei),(int)(81*Bbei)};
	private static final int BIG_sizeX_STAND=(int)(55*Bbei);
	private static final int BIG_sizeY_STAND=(int)(74*Bbei);
	private static final int BIG_sizeX_JUMPING_L=43;
	private static final int BIG_sizeY_JUMPING_L=92;
	private static final int BIG_sizeX_JUMPING_R=(int)(47*Bbei);
	private static final int BIG_sizeY_JUMPING_R=(int)(73*Bbei);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final int[] SMALL_sizeX={(int)(56*Sbei),(int)(58*Sbei),(int)(58*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(53*Sbei),(int)(48*Sbei),(int)(43*Sbei),(int)(44*Sbei),(int)(49*Sbei),(int)(55*Sbei),(int)(59*Sbei),(int)(56*Sbei),(int)(55*Sbei),(int)(43*Sbei),(int)(38*Sbei),(int)(35*Sbei),(int)(37*Sbei),(int)(35*Sbei),(int)(39*Sbei),(int)(40*Sbei),(int)(48*Sbei),(int)(53*Sbei),(int)(54*Sbei)};
	private static final int[] SMALL_sizeY={(int)(81*Sbei),(int)(78*Sbei),(int)(78*Sbei),(int)(76*Sbei),(int)(76*Sbei),(int)(76*Sbei),(int)(79*Sbei),(int)(76*Sbei),(int)(80*Sbei),(int)(80*Sbei),(int)(80*Sbei),(int)(81*Sbei),(int)(82*Sbei),(int)(81*Sbei),(int)(79*Sbei),(int)(77*Sbei),(int)(79*Sbei),(int)(79*Sbei),(int)(76*Sbei),(int)(75*Sbei),(int)(80*Sbei),(int)(83*Sbei),(int)(83*Sbei),(int)(82*Sbei),(int)(81*Sbei)};
	private static final int SMALL_sizeX_STAND=(int)(55*Sbei);
	private static final int SMALL_sizeY_STAND=(int)(74*Sbei);
	private static final int SMALL_sizeX_JUMPING_L=37;
	private static final int SMALL_sizeY_JUMPING_L=80;
	private static final int SMALL_sizeX_JUMPING_R=(int)(47*Sbei);
	private static final int SMALL_sizeY_JUMPING_R=(int)(73*Sbei);
	private int step=0;
	private boolean canWork=true;
	private boolean pressA=false;//向左
	private boolean pressD=false;//向右
	private Control control=null;
	private boolean big=false;//是否变大了/*************************************************/
	private boolean canAtack=false;//是否可以攻击
	public void setCanAtack(boolean canAtack)
	{
		this.canAtack = canAtack;
	}
	public boolean isCanAtack() 
	{
		return canAtack;
	}
	private boolean noEnemy=false;//是否无敌
	private int speed=1;//速度
	private int jumpHight=250;//跳得高度
	private Flint withFlint=null;//触碰到的硬物
	private int down=0;//运动状况1表示向上 0表示不动 -1表示向下
	private int crashType=0;//碰撞的类型
	private int direction=1;//脸方向 1表示向右 -1表示向左
	private List<Bullet> bullets=new ArrayList<Bullet>();//子弹
	private Set<Bullet> deleteBullet=new HashSet<Bullet>();
	private boolean downDie=false;
	/**
	 *  返回是否是降落状态
	 */
	public boolean isDownDie() 
	{
		return downDie;
	}
	/**
	 * 死亡一条生命
	 */
	public void setDownDie(boolean downDie) 
	{
		this.downDie = downDie;
	}
	/**
	 * 
	 * @param locaX 横坐标
	 * @param locaY 纵坐标
	 */
	public Mario(int locaX, int locaY)
	{
		this.locaX=locaX;
		this.locaY=locaY;
	}
	/**
	 * 
	 * @return 是否为保护状态
	 */
	public boolean isNoEn() 
	{
		return noEn;
	}
	/**
	 * 
	 * @param control 管家类的引用
	 */
	public void setControl(Control control)
	{
		this.control = control;
		new stepThread().start();
	}
	/**
	 * 
	 * @return 返回是否可以跳起来
	 */
	public boolean isCanWork()
	{
		return canWork;
	}
	/**
	 * 
	 * @param canWork 设置是否可以跳起来
	 */
	public void setCanWork(boolean canWork) 
	{
		this.canWork = canWork;
	}
	/**
	 * 
	 * @return 获取正在碰撞的硬物
	 */
	public Flint getWithFlint() 
	{
		return withFlint;
	}
	/**
	 *  获取现在的方向
	 */
	public int getDirection()
	{
		return direction;
	}
	/**
	 *  
	 * @return 是否是无敌状态
	 */
	public boolean isNoEnemy() 
	{
		return noEnemy;
	}
	/**
	 * 
	 * @param noEnemy 设置为无敌状态
	 */
	public void setNoEnemy(boolean noEnemy) 
	{
		this.noEnemy = noEnemy;
	}
	/**
	 * 
	 * @return 返回现在的碰撞类型
	 */
	public int getcrashType() 
	{
		return crashType;
	}
	/**
	 * 
	 * @param big 是否为大马里奥
	 */
	public void setBig(boolean big)
	{
		this.big=big;
	}
	/**
	 * 
	 * @return 返回是否为大马里奥
	 */
	public boolean isBig() 
	{
		return big;
	}
	/**
	 *  
	 * @return 获取子弹集合
	 */
	public List<Bullet> getBullets()
	{
		return bullets;
	}
	/**
	 * 
	 * @return 返回速度
	 */
	public int getSpeed() 
	{
		return speed;
	}
	/**
	 *  
	 * @param speed 设置速度
	 */
	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}
	/**
	 *  返回运动状态
	 */
	public int getDown() 
	{
		return down;
	}
	/**
	 * 获取横坐标
	 */
	public int getLocaX() 
	{
		return locaX;
	}
	/**
	 * 设置横坐标
	 */
	public void setLocaX(int locaX) 
	{
		this.locaX = locaX;
	}
	/**
	 * 
	 * @return 返回纵坐标
	 */
	public int getLocaY() 
	{
		return locaY;
	}
	/**
	 * 
	 * @param locaY 设置纵坐标
	 */
	public void setLocaY(int locaY) 
	{
		this.locaY = locaY;
	}
	/**
	 * 
	 * @return 获取横向的大小
	 */
	public int getSizeX() 
	{
		if(pressA||pressD)
		{
			if(step>MAX_SIZE)return isBig()?BIG_sizeX[0]:SMALL_sizeX[0];
			return isBig()?BIG_sizeX[step]:SMALL_sizeX[step];
		}
		if(down==0)return isBig()?BIG_sizeX_STAND:SMALL_sizeX_STAND;
		if(down!=0&&direction==1)return isBig()?BIG_sizeX_JUMPING_R:SMALL_sizeX_JUMPING_R;
		return isBig()?BIG_sizeX_JUMPING_L:SMALL_sizeX_JUMPING_L;
	}
	/**
	 * 
	 * @return 获取纵向的大小
	 */
	public int getSizeY() 
	{
		if(pressA||pressD)
		{
			if(step>MAX_SIZE)return isBig()?BIG_sizeY[0]:SMALL_sizeY[0];
			return isBig()?BIG_sizeY[step]:SMALL_sizeY[step];
		}
		if(down==0)return isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND;
		if(down!=0&&direction==1)return isBig()?BIG_sizeY_JUMPING_R:SMALL_sizeY_JUMPING_R;
		return isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L;
	}
	private class SpeedUpThread extends Thread 
	{
		public void run()
		{
			try 
			{
				sleep(500);
				speedUp=false;
				speed=1;
				System.out.println(speed);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	private class getCrash extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				getCrashType();
		//		System.out.println(crashType+"  "+downThread+" "+down);
			/*	if(crashType==CrashType.NO_CRASH&&downThread==null&&down==0&&locaY!=control.getCutLine())
				{
					System.out.println(crashType+"  "+downThread+" "+down);
					new Down().start();
				}*/
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
	private class stepThread extends Thread
	{
		public void run()
		{
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				try 
				{
					if(pressA||pressD)
					{
						if(step+1>MAX_SIZE)step=0;else step++;
					}
					sleep(30);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 设置为无敌状态
	 */
	public void setNoEn()
	{
		new NoEnThread().start();
	}
	private class NoEnThread extends Thread
	{
		public void run()
		{
			noEn=true;
			try 
			{
				sleep(2000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			noEn=false;
		}
	}
	/**
	 *  绘画
	 */
	public void paint(Graphics g)//绘制Mario的图片
	{
	//	System.out.println(crashType);
	//	System.out.println(downDie);
		move();//先移动
		Color c=g.getColor();
		if (locaY>1000) 
		{
			locaY=-1000;
			locaX=-500;
			control.die();
		}
		g.setColor(Color.black);
//		g.drawRect(this.getLocaX(), this.getLocaY()-this.getSizeY(), this.getSizeX(), this.getSizeY());
		if(downDie)
		{
			downSpeed+=0.15;
			locaY+=downSpeed;
		}
		for(int i=0;i<bullets.size();i++)//绘制子弹
		{
			Bullet b=bullets.get(i);
			b.hit(control.getDangerous(), control.getFlints(),this);//子弹打到桶或者怪物
			for(int j=0;j<control.getHoles().size();j++)
			{
				if(control.getHoles().get(j).canPaint())
				control.getHoles().get(j).DownDie((Moveable)b);
			}
			if(b.getLocaY()>750)deleteBullet.add(b);
			if(b!=null)b.paint(g);
		}
		bullets.removeAll(deleteBullet);
		deleteBullet.clear();
		if(isNoEn()||noEnemy)
		{
			int p=(int)(100*Math.random());
			if(p<50&&!control.isWin())return;
		}
		if(down!=0)//跳起来
		{
			if(direction==1)g.drawImage(isBig()?ImageTool.BIG_JUMPING_R:ImageTool.SMALL_JUMPING_R, locaX, locaY-(isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L), null);else
			if(direction==-1)g.drawImage(isBig()?ImageTool.BIG_JUMPING_L:ImageTool.SMALL_JUMPING_L, locaX, locaY-(isBig()?BIG_sizeY_JUMPING_L:SMALL_sizeY_JUMPING_L), null);
		}else
		if(down==0)//没跳
		{
			if((pressA&&pressD)||(!pressA&&!pressD))
			{
				if(direction==1)g.drawImage(isBig()?ImageTool.BIG_STAND_R:ImageTool.SMALL_STAND_R, locaX, locaY-(isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND), null);else
				if(direction==-1)g.drawImage(isBig()?ImageTool.BIG_STAND_L:ImageTool.SMALL_STAND_L, locaX, locaY-(isBig()?BIG_sizeY_STAND:SMALL_sizeY_STAND),null);
			}else
			if(pressD)//向右走着
			{	if(step>MAX_SIZE)step=0;
				g.drawImage(isBig()?ImageTool.BIG_RUNNING_IMAGES_R[step]:ImageTool.SMALL_RUNNING_IMAGES_R[step], locaX, locaY-(isBig()?BIG_sizeY[step]:SMALL_sizeY[step]), null);
			}else
			if(pressA)//向左走着
			{
				if(step>MAX_SIZE)step=0;
				g.drawImage(isBig()?ImageTool.BIG_RUNNING_IMAGES_L[step]:ImageTool.SMALL_RUNNING_IMAGES_L[step], locaX, locaY-(isBig()?BIG_sizeY[step]:SMALL_sizeY[step]),null);
			}
			g.setColor(c);
		}
		g.setColor(c);
	}
	/**
	 * 
	 * @param e 键盘事件
	 *  处理按下键盘事件
	 */
	public void disposeKey(KeyEvent e)//处理键盘按下事件
	{
		int key=e.getKeyCode();
		if(key==e.VK_J)
		{
			if(upThread!=null)
			{
				upThread.stop();
				speedUp=true;
			}
		}
		if(key==e.VK_A||key==e.VK_D)
		{
			if(key==KeyEvent.VK_A)
			{
				pressA=true;
				direction=-1;
			}else
			{
				pressCount++;
				pressD=true;
				direction=1;
			}
		}else
		if(key==e.VK_K&&down==0&&!isDownDie())new JumpThread().start();//按下跳则调用jump方法
	}
	/**
	 * 跳起来
	 */
	public void jump()
	{
		new LJumpThread().start();
	}
	private class LJumpThread extends Thread
	{
		public void run()
		{
			try
			{
				if(downThread!=null)
				{
					downThread.stop();
				}
				int n=60;
				int site=locaY;
				for(int i=site;i>=site-n;i--)//向上跳的时候改变状态
				{
					if(direction==1&&((i&1)==1))locaX++;else locaX--;
					locaY=i;
					sleep(2);
					down=1;
				}
				int now=site;
				site=locaY;
				for(int i=site;i<=control.getCutLine();i++)//向下跳的时候改变状态
				{
					if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U))//如果在某个pipe的上方、启动二类下落线程
					{
						down=0;
						new Down().start();
						return;
					}
					locaY=i;
					sleep(2);
					down=-1;
				}
				down=0;
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	/**
	 * 
	 * @param e 键盘事件
	 *  处理释放键盘事件
	 */
	public void releaseKey(KeyEvent e)//处理键盘释放事件 
	{
		int key=e.getKeyCode();
		if(key==e.VK_A||key==e.VK_D)pressCount=0;
		if(key==e.VK_A)pressA=false;else 
		if(key==e.VK_D)pressD=false;
		if(key==e.VK_J)
		{
			if(upThread!=null)
			{
				upThread.stop();
				upThread=new SpeedUpThread();
				upThread.start();
			}
		}
		if(key==e.VK_J&&canAtack)this.attach();//释放j并且可以攻击则攻击
	}
	private void attach()//攻击 
	{
		SoundTool.play(SoundTool.fireSound);
		bullets.add(new Bullet(locaX+(getSizeX()>>1), locaY-(getSizeY()>>1), this.control, direction));//添加子弹 
	}
	/**
	 *  移动方法
	 */
	public void move()//移动方法 
	{
		if(pressA^pressD)
		{
			pressCount++;
			if(pressCount<20)speed=2;
			if(pressCount>20&&pressCount<40)speed=3;
			if(pressCount>40)speed=4;
			if(speedUp)speed=5;
			
		}
		if(getCrashType()&&((crashType==CrashType.WALL_L||crashType==CrashType.PIPE_L||crashType==CrashType.WUWL))&&direction==1)
		{
			locaX-=3;
			return;
		}else
		if(getCrashType()&&((crashType==CrashType.WALL_R||crashType==CrashType.PIPE_R||crashType==CrashType.WUWR))&&direction==-1)
		{
			locaX+=3;//碰撞到硬物被弹回来
			return;
		}
		if(pressA&&pressD)return;//ad键都按的时候不能移动
		if(pressA)
		{
			if(locaX<=100&&control.getGroundLocaX()<0)control.allMoveR();else //走到左边界还能继续往左走
			if(locaX-speed>0)locaX=locaX-speed;
		}else
		if(pressD)
		{
			if(control.getFinal_line()+control.getGroundLocaX()<612||locaX+speed<(control.getSizeX()>>1))locaX=locaX+speed;else control.allMoveL();//走到中间场景移动产生人物移动假象
		}
	}
	private class Down extends Thread//二类下落线程   //控制最终下落的线程如果在正常下落时由于碰撞被打断则启动该线程监视是否需要再次落下
	{
		public void run()
		{
			if(down==1)return;
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			if(downThread!=null)
			{
				downThread.stop();
			}
			downThread=this;
		//	System.out.println("enter down!");
		//	while(down!=-1&&locaY!=control.getCutLine())//没有落地面上一直在循环
			while(locaY<control.getCutLine())
			{
				boolean flag=false;//
				int site=locaY;
		//		System.out.println(crashType+"  "+down+"  "+canWork);
				if((getCrashType()&&(crashType==CrashType.NO_CRASH)&&(down!=1))||canWork==false)//多线程重要判断应该靠紧
				{
					flag=true;//已经下落
					double count=1;
					for(int i=site;i<=control.getCutLine();i+=(count+=0.1))
					{
						down=-1;
						locaY=i;
						downSpeed=count;
				//		System.out.println(i+"  "+control.getCutLine());
						if(getCrashType()&&(crashType==CrashType.WALL_U||crashType==CrashType.PIPE_U||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//如果在某个硬物的上方、启动二类下落线程且退出本线程
						{
							down=0;//落道硬物上面则运动状态为0
							downSpeed=0;
							downThread=null;
							new Down().start();
							setCanWork(true);
							return;
						}
						try 
						{
							sleep(10);
						} catch (InterruptedException e) 
						{
							down=0;
							downThread=null;
							downSpeed=1;
							setCanWork(true);
							new Down().start();
						}
					}
					locaY=control.getCutLine();
				}
				if(flag)//在该线程中如果已经下落则跳出 
				{
					if(!control.getMario().isDownDie())
					for(int j=0;j<control.getHoles().size();j++)
					{
						if(control.getHoles().get(j).canPaint())
						control.getHoles().get(j).DownDie(control.getMario());
					}
					if(!control.getMario().isDownDie())downSpeed=1;
					down=0;
					downThread=null;
					setCanWork(true);
					return;
				}
			}
		}
	}
	private class JumpThread extends Thread//跳的线程
	{
		private int n=jumpHight;
		public void run()
		{
			if(down==-1)return;
			if(downThread!=null)
			{
				downThread.stop();
				downThread=null;
			}
			if(jumpThread!=null)
			{
				jumpThread.stop();
				jumpThread=null;
			}
			jumpThread=this;
			//while(!Control.isALL_START()){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			SoundTool.play(SoundTool.jumpSound);
			try
			{
				int site=locaY;
				double count=Math.sqrt((2*0.085*n));
				for(int i=site;count>0;i-=(count-=0.1))//向上跳的时候改变状态
				{
					if(getCrashType()&&(crashType==CrashType.WALL_D||crashType==CrashType.WUWL||crashType==CrashType.WUWR))//如果发现从下撞击了硬物则跳出向上的过程改为向下
					{
						down=-1;
						break;
					}
					locaY=i;
					sleep(10);
					down=1;
				}
				new Down().start();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			down=0;
		}
	}
	public Rectangle getRec()
	{
		return new Rectangle(locaX-few,locaY-getSizeY()-few,getSizeX()+2*few,getSizeY()+2*few);
	}
	public boolean getCrashType()//获取碰撞类型
	{
		List<Flint> flints=control.getFlints();
		Rectangle rec2=this.getRec();
		Rectangle rec1=null;
		boolean[] type=new boolean[65];
		int sumType=0;
		for(int i=0;i<flints.size();i++)//判断是否碰撞到pipe
		{
			if(flints.get(i)!=null)rec1=flints.get(i).getRec();
			if(rec1!=null&&rec1.intersects(rec2))
			{
				withFlint=flints.get(i);
				try 
				{
					if(withFlint!=null)
					{
						int x=withFlint.getCrashType(this.getDown(),direction,withFlint.getRec(),this.getRec());//调用硬物的获取碰撞类型方法
						if(type[x]!=true)
						{
							sumType+=x;
							type[x]=true;
						}
					}
				} catch (Exception e) 
				{
					continue;
				}
			}
		}
		crashType=sumType;
		return true;
	}
	/**
	 * 
	 * @param b 设置是否可以攻击
	 */
	public void canAtack(boolean b)// 是否可以攻击
	{
		this.canAtack=b;
	}
	/**
	 * 获取类型
	 */
	public int getType() 
	{
		return 1;
	}
	/**
	 *  下落
	 */
	public void down() 
	{
	//	System.out.println("down"+downThread);
		if(downThread==null)new Down().start();
	}
	/**
	 * 胜利状态绘画
	 * @param g 画板上下文
	 * @param loca 位置
	 */
	public void paintWin(Graphics g,int loca) 
	{
		if(jumpThread!=null)
		{
			jumpThread.stop();
			jumpThread=null;
		}
		if(downThread!=null)
		{
			downThread.stop();
			downThread=null;
		}
		speed=5;
		setNoEnemy(true);
		
		if(locaY<=control.getCutLine()-40)
		{
			locaY++;
			if(isBig())g.drawImage(ImageTool.winB, loca+10, locaY-111, null);else
			g.drawImage(ImageTool.winS, loca+17, locaY-83, null);	
			g.drawImage(ImageTool.flag, loca-45, locaY-72, null);
		}else
		{
			locaY=control.getCutLine();
			pressD=true;
			down=0;
			g.drawImage(ImageTool.flag, loca-45, control.getCutLine()-114, null);
			paint(g);
		}
	}
	/**
	 *  初始化
	 */
	public void recover() 
	{
		setLocaX(0);
		setLocaY(655);
		downDie=false;
		downSpeed=1;
		speedUp=false;
		speed=1;
		pressA=false;
		pressD=false;
		setBig(false);
		setNoEnemy(false);
		setCanWork(true);
		canAtack=false;
		setSpeed(4);
		withFlint=null;
		down=0;
		direction=1;

	}
	/**
	 * 
	 * @param b 设置是保护状态
	 */
	public void NoEnemy(boolean b)
	{
		this.noEn=b;
	}
	/**
	 *  碰到深洞速度为0
	 */
	public void clearSpeed() 
	{
		this.speed=0;
	}
}
