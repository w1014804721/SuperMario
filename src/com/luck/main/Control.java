package com.luck.main;
import javax.swing.*;

import com.luck.interfaces.*;		

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import com.luck.interfaces.Growable;
import com.luck.model.*;
import com.luck.tool.ApplicationUtil;
import com.luck.tool.ImageTool;
import com.luck.tool.Property;
import com.luck.tool.SoundTool;
/**
 * 管家类，控制其他的模型类
 * @author luck
 */
public class Control extends JFrame
{   
	private int single=0;
	Map<Integer, Model> saveModels=new HashMap<Integer, Model>();
	/**
	 * 胜利声音播放状态
	 */
	private boolean winSound=false;
	/**
	 * 所有线程及对象是否准备就绪
	 */
	private boolean ALL_START=false;
	/**
	 * 全局的计时器线程
	 */
	private Thread AlltimeThread=null;
	/**
	 * 全局计时器是否正在计时
	 */
	private boolean timeStart=true;
	/**
	 * 全局的剩余时间
	 */
	private int time=500;
	private boolean allover=false;
	private int _money=0;
	/**
	 * 是否死掉一命
	 */
	private boolean overOne=false;
	/**
	 * 是否通过所有关卡
	 */
	private boolean allWin=false;
	public boolean isAllWin() 
	{
		return allWin;
	}
	public void setAllWin(boolean allWin) 
	{
		this.allWin = allWin;
	}
	/**
	 * 是否生命用完
	 */
	private boolean over=false;
	/**
	 * 生命条数
	 */
	private int life=5;
	/**
	 * 当前的关卡标识
	 */
	private static int STEP=1;
	/**
	 * 所得分数
	 */
	private int score=0;
	/**
	 * 所有的关卡数
	 */
	private static int ALL_STEP=0;
	/**
	 * 当前地图长度
	 */
	private int length=0;
	/**
	 * 配置文件
	 */
	Properties pro=null;
	/**
	 * spring容器上下文
	 */
	ApplicationUtil au=null;
	/**
	 * 是否胜利一关
	 */
	private boolean win=false;
	public boolean isWin() 
	{
		return win;
	}
	public void setWin(boolean win) 
	{
		this.win = win;
	}
	/**
	 * 终点线
	 */
	private int final_line=0;
	/**
	 * 是否在欢迎界面
	 */
	private boolean welcome=true;
	/**
	 * 双缓冲背景图片上下文
	 */
	private Graphics gback=null;
	private int locaX=0;
	/**
	 * 即将被删除的可生长物的集合
	 */
	private Set<Growable> gro=new HashSet<Growable>();
	/**
	 * 即将被删除的危险物的集合
	 */
	private Set<Dangerous> dan=new HashSet<Dangerous>();
	private int locaY=0;
	/**
	 * 是否被完全加载
	 */
	private boolean load=false;
	/**
	 * 当前的走过的长度
	 */
	private int groundLocaX=0;
	/**
	 * 双缓冲背景图片
	 */
	private Image backGroundImage=null;
	private int sizeX=1366;
	private int sizeY=728;
	/**
	 * 地平线
	 */
	private int cutLine=(int)(sizeY*0.90);//地面分割线
	/**
	 * 马里奥实例
	 */
	private Mario mario=null;//游戏主角
	/**
	 * 被显示在屏幕上的所得分数的显示线程集合
	 */
	private List<ScoreThread> scopeList=new ArrayList<ScoreThread>();
	/**
	 * 硬物容器，包含所有的硬物实例
	 */
	private List<Flint> flints=new ArrayList<Flint>();
	/**
	 * 可生长物容器，包含所有的可生长物的实例
	 */
	private List<Growable> growables=new ArrayList<Growable>();
	/**
	 * 金币容器，包含所有的金币的实例。
	 */
	private List<Money> moneys=new ArrayList<Money>();
	/**
	 * 危险物容器，包含所有的危险物的实例
	 */
	private List<Dangerous> dangerous=new ArrayList<Dangerous>();
	/**
	 * 可互相攻击物容器，包含所有的可互相攻击物。
	 */
	private List<Kill> kills=new ArrayList<Kill>();
	/**
	 * 洞容器，包含所有的洞实例
	 */
	private List<Hole> holes=new ArrayList<Hole>();
	public List<Hole> getHoles() 
	{
		return holes;
	}
	/**
	 * 获取金币
	 */
	public void addMoneys()
	{
		_money++;
	}
	/**
	 * 死亡一条生命
	 */
	public void die()
	{
		ALL_START=false;
		new Die().start();
	}
	/**
	 * 
	 * @return 返回终点线
	 */
	public int getFinal_line() 
	{
		return final_line;
	}
	/**
	 * 
	 * @return 返回已走的长度
	 */
	public int getGroundLocaX() 
	{
		return groundLocaX;
	}
	/**
	 * 
	 * @return 返回攻击型物的集合
	 */
	public List<Kill> getKills() 
	{
		return kills;
	}
	/**
	 * 
	 * @param kills 设置可攻击物的集合
	 */
	public void setKills(List<Kill> kills) 
	{
		this.kills = kills;
	}
	/**
	 * 
	 * @return 获取金钱的集合
	 */
	public List<Money> getMoneys()
	{
		return moneys;
	}
	/**
	 * 
	 * @param moneys 金钱的集合
	 */
	public void setMoneys(List<Money> moneys) {
		this.moneys = moneys;
	}
	/**
	 * 
	 * @return 获取可生长物的集合
	 */
	public List<Growable> getGrowables() 
	{
		return growables;
	}
	/**
	 * 
	 * @return 返回硬物的集合
	 */
	public List<Flint> getFlints()
	{
		return flints;
	}
	/**
	 * 
	 * @return 返回马里奥
	 */
	public Mario getMario() 
	{
		return mario;
	}
	/**
	 * 
	 * @return 返回窗体横向大小
	 */
	public int getSizeX()
	{
		return sizeX;
	}
	/**
	 * 
	 * @return 返回窗体纵向大小
	 */
	public int getSizeY() 
	{
		return sizeY;
	}
	/**
	 * 
	 * @return 获取地平线
	 */
	public int getCutLine() 
	{
		return cutLine;
	}
	/**
	 * 
	 * @return 返回危险物的集合
	 */
	public List<Dangerous> getDangerous()
	{
		return dangerous;
	}
	/**
	 * 
	 * @param locaX 分数显示的横坐标
	 * @param locaY 分数显示的纵坐标
	 * @param type 分数的类型
	 */
	public void addScore(int locaX,int locaY,int type)
	{
		score+=type;
		ScoreThread st=new ScoreThread(locaX, locaY, type);
		st.start();
		scopeList.add(st);
	}
	/**
	 * 
	 * @author 王镜鑫
	 *该类是在马里奥死亡一条生命之后播放音乐的线程类
	 */
	private class Die extends Thread
	{
		public void run()
		{
			mario.getBullets().clear();
			SoundTool.over();
			SoundTool.play(SoundTool.die1Sound);
			try 
			{
				sleep(500);
				SoundTool.play(SoundTool.die2Sound);
				sleep(2000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			new DieTime().start();
		}
	}
	/**
	 * 
	 * @author 王镜鑫
	 *该类是在绘画分数图片的线程类
	 */
	private class ScoreThread extends Thread 
	{
		public int locaX;
		public int locaY;
		public int type;
		public ScoreThread(int locaX,int locaY,int type)
		{
			this.locaX=locaX;
			this.locaY=locaY;
			this.type=type;
		}
		public void run()
		{
			for(int i=0;i<50;i++)
			{
				this.locaY--;
				try 
				{
					sleep(20);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			synchronized (scopeList) 
			{
				scopeList.remove(this);
			}
		}
	}
	/**
	 * 主要驱动方法，设置全局面板的一些属性
	 */
	public void work(int single)
	{
		if(single==1024)
		{
			Main.setDefault_map(Boolean.parseBoolean(Property.getPro("/save_pro.properties").getProperty("status")));
			ALL_STEP=Integer.parseInt(Property.getPro("/save_pro.properties").getProperty("all_step"));
		}else
		if(Main.isDefault_map())
		{
			ALL_STEP=Integer.parseInt(Property.getPro("/game_default.properties").getProperty("ALL_STEP"));
		}else
		{
			pro=Property.getPro("/game.properties");
			ALL_STEP=Integer.parseInt(pro.getProperty("ALL_STEP"));
		}
		setBounds(locaX, locaY, sizeX, sizeY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		addKeyListener(new keyWork());//键盘事件监听
		addModels(single);//加入实物模型d
		new paintThread().start();//启动重画线程
		setVisible(true);
	}
	/**
	 * 
	 * @author 王镜鑫
	 * 全局计时的线程每一秒time减一
	 *
	 */
	private class Time extends Thread
	{
		public void run()
		{
			while(timeStart)
			{
				time--;
				if(time<=0)
				{
					die();
					return;
				}
				try 
				{
					sleep(1000);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 初始化地图并向容器中添加实例的方法
	 */
	private void addModels(int single) //添加模型类
	{
		if(STEP>ALL_STEP)
		{
			allWin=true;
			return;
		}
		length=0; 
		load=false;
		welcome=true;
		win=false;
		winSound=false;
		groundLocaX=0;
		flints.clear();
		scopeList.clear();
		growables.clear();
		moneys.clear();
		dangerous.clear();
		kills.clear();
		holes.clear();
		if(single==1024)
		{
			au=new ApplicationUtil(single);
			pro=Property.getPro("/save_pro.properties");
			groundLocaX=Integer.parseInt(pro.getProperty("groundLocaX"));
			life=Integer.parseInt(pro.getProperty("life"));
			time=Integer.parseInt(pro.getProperty("time"));
			score=Integer.parseInt(pro.getProperty("score"));
			_money=Integer.parseInt(pro.getProperty("money"));
			STEP=Integer.parseInt(pro.getProperty("step"));
			pro=Property.getPro("/save_number.properties");
			String mushroomNeedDraw=pro.getProperty("mushroomNeedDraw");
			String starNeedDraw=pro.getProperty("starNeedDraw");
			String flowerNeedDraw=pro.getProperty("flowerNeedDraw");
			String after=pro.getProperty("after");
			length=Integer.parseInt(pro.getProperty("length"));
			final_line=Integer.parseInt(pro.getProperty("final_line"));
			int badflowers=Integer.parseInt(pro.getProperty("badflower"));
			int	flower=Integer.parseInt(pro.getProperty("flower"));
			int	money=Integer.parseInt(pro.getProperty("money"));
			int monster=Integer.parseInt(pro.getProperty("monster"));
			int	mushroom=Integer.parseInt(pro.getProperty("mushroom"));
			int	pipe=Integer.parseInt(pro.getProperty("pipe"));
			int	star=Integer.parseInt(pro.getProperty("star"));
			int	turtle=Integer.parseInt(pro.getProperty("turtle"));
			int	wall=Integer.parseInt(pro.getProperty("wall")); 
			int hole=Integer.parseInt(pro.getProperty("hole"));
			for(int i=1;i<=hole;i++)
			{
				Hole _hole=(Hole)au.getContext().getBean("hole"+i);
				_hole.setControl(this);
				holes.add(_hole);
			}
			for(int i=1;i<=wall;i++)
			{
				Wall _wall=(Wall)au.getContext().getBean("wall"+i);
				if(after.indexOf(","+i+",")!=-1)_wall.setSta(Wall.statement.afternormal);
				_wall.setControl(this); 
				flints.add(_wall);
			}
			for(int i=1;i<=pipe;i++)
			{
				Pipe _pipe=(Pipe)au.getContext().getBean("pipe"+i);
				_pipe.setControl(this);
				flints.add(_pipe);
			}
			for(int i=1;i<=mushroom;i++)
			{
				Mushroom _mushroom=(Mushroom)au.getContext().getBean("mushroom"+i);
				_mushroom.setControl(this);
				if(mushroomNeedDraw.indexOf(","+i+",")!=-1)_mushroom.setNeedDraw();
				growables.add(_mushroom);
			}
			for(int i=1;i<=flower;i++)
			{
				Flower _flower=(Flower)au.getContext().getBean("flower"+i);
				_flower.setControl(this);
				if(flowerNeedDraw.indexOf(","+i+",")!=-1)_flower.setNeedDraw();
				growables.add(_flower);
			}
			for(int i=1;i<=star;i++)
			{
				Star _star=(Star)au.getContext().getBean("star"+i);
				_star.setControl(this);
				if(starNeedDraw.indexOf(","+i+",")!=-1)_star.setNeedDraw();
				growables.add(_star);
			}
			for(int i=1;i<=money;i++)
			{
				Money _money=(Money)au.getContext().getBean("money"+i);
				_money.setControl(this);
				moneys.add(_money);
			}
			for(int i=1;i<=badflowers;i++)
			{
				BadFlower _badFlower=(BadFlower)au.getContext().getBean("badflower"+i);
				_badFlower.setControl(this);
				dangerous.add(_badFlower);
			}
			for(int i=1;i<=monster;i++)
			{
				Monster _monster=(Monster)au.getContext().getBean("monster"+i);
				_monster.setControl(this);
				dangerous.add(_monster);
			}
			for(int i=1;i<=turtle;i++)
			{
				Turtle _turtle=(Turtle)au.getContext().getBean("turtle"+i);
				_turtle.setControl(this);
				dangerous.add(_turtle);
			}
			new timeThread().start();
			winSound=false;
		}else
		{
			if(Main.isDefault_map())
			{
				pro=Property.getPro("/default.properties");
				au=new ApplicationUtil(-1);
			}else
			{
				pro=Property.getPro("/map"+STEP+".properties");
				au=new ApplicationUtil(STEP);
			}
			length=Integer.parseInt(pro.getProperty("length"));
			final_line=Integer.parseInt(pro.getProperty("final_line"));
			int badflowers=Integer.parseInt(pro.getProperty("badflower"));
			int	flower=Integer.parseInt(pro.getProperty("flower"));
			int	money=Integer.parseInt(pro.getProperty("money"));
			int monster=Integer.parseInt(pro.getProperty("monster"));
			int	mushroom=Integer.parseInt(pro.getProperty("mushroom"));
			int	pipe=Integer.parseInt(pro.getProperty("pipe"));
			int	star=Integer.parseInt(pro.getProperty("star"));
			int	turtle=Integer.parseInt(pro.getProperty("turtle"));
			int	wall=Integer.parseInt(pro.getProperty("wall")); 
			int hole=Integer.parseInt(pro.getProperty("hole"));
			for(int i=1;i<=hole;i++)
			{
				Hole _hole=(Hole)au.getContext().getBean("hole"+i);
				_hole.setControl(this);
				holes.add(_hole);
			}
			for(int i=1;i<=wall;i++)
			{
				Wall _wall=(Wall)au.getContext().getBean("wall"+i);
				_wall.setControl(this); 
				flints.add(_wall);
			}
			for(int i=1;i<=pipe;i++)
			{
				Pipe _pipe=(Pipe)au.getContext().getBean("pipe"+i);
				_pipe.setControl(this);
				flints.add(_pipe);
			}
			for(int i=1;i<=mushroom;i++)
			{
				Mushroom _mushroom=(Mushroom)au.getContext().getBean("mushroom"+i);
				_mushroom.setControl(this);
				growables.add(_mushroom);
			}
			for(int i=1;i<=flower;i++)
			{
				Flower _flower=(Flower)au.getContext().getBean("flower"+i);
				_flower.setControl(this);
				growables.add(_flower);
			}
			for(int i=1;i<=star;i++)
			{
				Star _star=(Star)au.getContext().getBean("star"+i);
				_star.setControl(this);
				growables.add(_star);
			}
			for(int i=1;i<=money;i++)
			{
				Money _money=(Money)au.getContext().getBean("money"+i);
				_money.setControl(this);
				moneys.add(_money);
			}
			for(int i=1;i<=badflowers;i++)
			{
				BadFlower _badFlower=(BadFlower)au.getContext().getBean("badflower"+i);
				_badFlower.setControl(this);
				dangerous.add(_badFlower);
			}
			for(int i=1;i<=monster;i++)
			{
				Monster _monster=(Monster)au.getContext().getBean("monster"+i);
				_monster.setControl(this);
				dangerous.add(_monster);
			}
			for(int i=1;i<=turtle;i++)
			{
				Turtle _turtle=(Turtle)au.getContext().getBean("turtle"+i);
				_turtle.setControl(this);
				dangerous.add(_turtle);
			}
			new timeThread().start();
			winSound=false;
		}
		if(single==1024)
		{
			mario=new Mario(Integer.parseInt(Property.getPro("/save_pro.properties").getProperty("locaX")), Integer.parseInt(Property.getPro("/save_pro.properties").getProperty("locaY")));
			mario.setBig(Boolean.parseBoolean(Property.getPro("/save_pro.properties").getProperty("isBig")));
			mario.canAtack(Boolean.parseBoolean(Property.getPro("/save_pro.properties").getProperty("canAtack")));
		}else
		mario=new Mario(0, 655);
		mario.setControl(this);
		if(mario.getLocaY()!=655)mario.down();
	}
	/**
	 * 
	 * @author 王镜鑫
	 * 重画面板的线程，每隔15毫秒进行一次重画
	 */
	private class paintThread extends Thread//重画的线程
	{
		public void run()
		{
			repaint();
	//		while(!ALL_START){try{sleep(Control.TIME);} catch (InterruptedException e){}}
			while(true)
			{
				repaint();
				try 
				{
					sleep(15);
				}catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @author 王镜鑫
	 * 处理键盘事件，包括按下键盘和释放键盘
	 */
	private class keyWork extends KeyAdapter//键盘事件
	{
		public void keyPressed(KeyEvent e) //按下键盘
		{
			if(e.getKeyCode()==e.VK_L)
			{
				save();
			}else
			mario.disposeKey(e);
		}
		public void keyReleased(KeyEvent e) //释放键盘
		{
			mario.releaseKey(e);
		}
	}
	/**
	 * 全局所有实例向左移动
	 */
	public void allMoveL() //人物走到中间位置后，让场景内的其他的模型向左移动产生主角移动的效果
	{
		for(int i=0;i<scopeList.size();i++)
		{
			scopeList.get(i).locaY-=mario.getSpeed();
		}
		for(int i=0;i<mario.getBullets().size();i++)
		{
			mario.getBullets().get(i).moveL();
		}
		for(int i=0;i<dangerous.size();i++)
		{
			dangerous.get(i).moveL();;
		}
		for(int i=0;i<flints.size();i++)
		{
			flints.get(i).moveL();
		}
		for(int i=0;i<growables.size();i++)
		{
			growables.get(i).moveL();
		}
		for(int i=0;i<moneys.size();i++)
		{
			moneys.get(i).moveL();
		}
		for(int i=0;i<holes.size();i++)
		{
			holes.get(i).moveL();
		}
		groundLocaX-=mario.getSpeed();
	}
	/**
	 * 全局所有实例向右移动
	 */
	public void allMoveR() //人物走到最左边位置后，让场景内的其他的模型向右移动产生主角移动的效果
	{
		for(int i=0;i<scopeList.size();i++)
		{
			scopeList.get(i).locaY-=mario.getSpeed();
		}
		for(int i=0;i<mario.getBullets().size();i++)
		{
			mario.getBullets().get(i).moveR();
		}
		for(int i=0;i<dangerous.size();i++)
		{
			dangerous.get(i).moveR();
		}
		for(int i=0;i<flints.size();i++)
		{
			flints.get(i).moveR();
		}
		for(int i=0;i<growables.size();i++)
		{
			growables.get(i).moveR();
		}
		for(int i=0;i<moneys.size();i++)
		{
			moneys.get(i).moveR();
		}
		for(int i=0;i<holes.size();i++)
		{
			holes.get(i).moveR();
		}
		groundLocaX+=mario.getSpeed();
		
	}
	/**
	 * 
	 * @author 王镜鑫
	 * 展现欢迎界面等待全局的实例准备
	 */
	private class timeThread extends Thread
	{
		public void run()
		{
			welcome=true;
			try 
			{
				sleep(3000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			welcome=false;
			time=500;
			if(AlltimeThread!=null)
			{
				AlltimeThread.stop();
			}
			AlltimeThread=new Time();
			AlltimeThread.start();
			ALL_START=true;
			SoundTool.back();
		}
	}
	/**
	 * 
	 * @author 王镜鑫
	 * 死亡之后准备新的实例或者展现游戏结束界面
	 */
	private class DieTime extends Thread
	{
		public void run()
		{
			life--;
			ALL_START=false;
			overOne=true;
			if(life<=0)
			{
				over=true;
				return;
			}
			_money=0;
			score=0;
			time=500;
			mario.recover();
			addModels(0);
			try 
			{
				sleep(3000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			overOne=false;
		}
	}
	/**
	 * 画出容器中的实例
	 */
	public void paint(Graphics g)//绘制图形界面
	{
		if(backGroundImage==null)
		{
			backGroundImage=this.createImage(sizeX,sizeY);//构造缓冲背景图片
			gback=backGroundImage.getGraphics();//获取缓冲图片的画笔gback
			super.paint(gback);
			if(!load)
			{
				System.out.println("lo");
				ImageTool.loadit(gback);
				load=true;
			}
		}
		if(mario.getLocaX()>=1210&&!welcome)
		{
			STEP++;
			addModels(0);
			mario.recover();
			return;
		}
		super.paint(gback);
		if(over)
		{
			if(!allover)
			{
				allover=true;
				SoundTool.clear();
				SoundTool.play(SoundTool.allWin);
			}
			g.drawImage(ImageTool.over,0,0,null);
			return;
		}
		if(overOne)
		{
			gback.drawImage(ImageTool.black,0,0,null);
			gback.drawImage(ImageTool.life[life], (sizeX>>1)-64, (sizeY>>1)-23,null);
			g.drawImage(backGroundImage,0,0,null);
			return;
		}
		if(allWin)
		{
			g.drawImage(ImageTool.gameEnd, 0,0, null);
			return;
		}
		if(getFinal_line()+getGroundLocaX()<=656&&!win)
		{
			win=true;
			if(!winSound)
			{
				SoundTool.clear();
				SoundTool.over();
				SoundTool.play(SoundTool.win);
				winSound=true;
			}
			addScore(mario.getLocaX(), mario.getLocaY(), 4000);
		}
		if(welcome)
		{
			gback.drawImage(ImageTool.welcomeImage,0,0,null);
			g.drawImage(backGroundImage, 0, 0, null);
			return;
		}
		if(!ALL_START)return;
		for(int i=0;i<((length%2666)+10);i++)
		{
			gback.drawImage(ImageTool.back,groundLocaX+i*2666,0, null);
		}
		for(int i=0;i<((length%1588)+10);i++)
		{
			gback.drawImage(ImageTool.ground,groundLocaX+i*1588, cutLine, null);
		}
		Color c=gback.getColor();
		for(int i=0;i<holes.size();i++)
		{
			if(holes.get(i).canPaint())
			holes.get(i).paint(gback);
		}
		for(int i=0;i<dangerous.size();i++)//绘制怪物
		{
			if(dangerous.get(i).getLocaY()>750)
			dan.add(dangerous.get(i));
			if(dangerous.get(i).canPaint())
			{
				if(dangerous.get(i).getType()!=-1)
				if(!((Moveable)dangerous.get(i)).isDownDie())
				for(int j=0;j<holes.size();j++)
				{
					if(holes.get(j).canPaint())
					holes.get(j).DownDie((Moveable)dangerous.get(i));
				}
				dangerous.get(i).paint(gback);	
			}
		}
		dangerous.removeAll(dan);
		for(int i=0;i<flints.size();i++)
		{
			if(flints.get(i).canPaint())
			flints.get(i).paint(gback);
		}
		for(int i=0;i<growables.size();i++)
		{
			if(growables.get(i).getLocaY()>750)
			gro.add(growables.get(i));
			if(growables.get(i).canPaint())
			{
				if(growables.get(i).getType()==3)
				for(int j=0;j<holes.size();j++)
				{
					if(holes.get(j).canPaint())
					holes.get(j).DownDie((Moveable)growables.get(i));
				}
				growables.get(i).paint(gback);
			}
		}
		growables.removeAll(gro);
		for(int i=0;i<moneys.size();i++)
		{
			if(moneys.get(i).canPaint())
			moneys.get(i).paint(gback);
		}
		if(win)
		{
			gback.drawImage(ImageTool._final, final_line+groundLocaX, cutLine-451,null);
			gback.drawImage(ImageTool.tower, final_line+groundLocaX+500, cutLine-342,null);
			mario.paintWin(gback,final_line+groundLocaX);
		}else
		{
			for(int i=0;i<holes.size();i++)
			{
				if(holes.get(i).canPaint())
				holes.get(i).DownDie(mario);
			}
			mario.paint(gback);//绘制马里奥
			gback.drawImage(ImageTool.flag,  final_line+groundLocaX-45,cutLine-421, null);
			gback.drawImage(ImageTool._final, final_line+groundLocaX, cutLine-451,null);
			gback.drawImage(ImageTool.tower, final_line+groundLocaX+500, cutLine-342,null);
		}
		if(life>=0)
		gback.drawImage(ImageTool.showLife[life],10, 40, null);
		gback.drawImage(ImageTool.money[10], 150, 50, null);
		char[] money=String.valueOf(_money).toCharArray();
		int length=5;
		for(int i=0;i<money.length;i++)
		{
			length+=(i==0?0:ImageTool.MONEY_SIZE[money[i-1]-48]);
			gback.drawImage(ImageTool.money[money[i]-48],190+length,60,null);
		}
		char[] number=String.valueOf(STEP).toCharArray();
		for(int i=0;i<number.length;i++)
		{
			gback.drawImage(ImageTool.number[number[i]-48],650+(i==0?0:ImageTool.NUMBER_SIZE[number[i-1]-48]),40,null);
		}
		gback.drawImage(ImageTool.clock[10],1215, 40, null);
		char[] clock=String.valueOf(time).toCharArray();
		length=5;
		for(int i=0;i<clock.length;i++)
		{
			length+=(i==0?0:ImageTool.CLOCK_SIZE[clock[i-1]-48]);
			gback.drawImage(ImageTool.clock[clock[i]-48],1250+length,40,null);
		}
		length=5;
		char[] scores=String.format("%8d",score).replaceAll(" ","0").toCharArray();
		for(int i=0;i<scores.length;i++)
		{
			length+=(i==0?0:ImageTool.CLOCK_SIZE[scores[i-1]-48]);
			gback.drawImage(ImageTool.clock[scores[i]-48],900+length,40,null);
		}
		synchronized (scopeList) 
		{
			for(ScoreThread score:scopeList)
			{
				gback.drawImage(ImageTool.score.get(score.type), score.locaX, score.locaY,null);
			}
		}
		gback.setColor(c);
		g.drawImage(backGroundImage,0,0, null);
	}
	private void save() 
	{
		int count=0;
		for(int i=0;i<flints.size();i++)
		{
			count=flints.get(i).save(count,saveModels);
		}
		for(int i=0;i<growables.size();i++)
		{
			if(growables.get(i).isNeedDraw())
			{
				count=growables.get(i).save(count, saveModels);
			}
		}
		for(int i=0;i<dangerous.size();i++)
		{
			count=dangerous.get(i).save(count,saveModels);
		}
		for(int i=0;i<holes.size();i++)
		{
			count=holes.get(i).save(count,saveModels);
		}
		for(int i=0;i<moneys.size();i++)
		{
			count=moneys.get(i).save(count,saveModels);
		}
		try 
		{
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("bin/save_pro.properties"))));
			String pro=
					"life="+life+"\n"+
					"time="+time+"\n"+
					"score="+score+"\n"+
					"money="+_money+"\n"+
					"step="+STEP+"\n"+
					"all_step="+ALL_STEP+"\n"+
					"length="+length+"\n"+
					"isBig="+mario.isBig()+"\n"+
					"canAtack="+mario.isCanAtack()+"\n"+
					"locaX="+mario.getLocaX()+"\n"+
					"locaY="+mario.getLocaY()+"\n"+
					"length="+length+"\n"+
					"final_line="+final_line+"\n"+
					"groundLocaX="+groundLocaX+"\n"+
					"status="+Main.isDefault_map()+"\n";
			bw.write(pro);
			bw.flush();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		Main.parse(saveModels, 2);
	}
}
