package com.luck.main;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.luck.tool.ImageTool;
/**
 * 
 * @author 王镜鑫
 * 地图编辑器。实现拖动图标进行地图编辑，本编辑器根据最后图标的位置生成xml文件通过IOC实现在启动游戏时进行对象的实例化
 */
public class Main extends JFrame 
{
	private static boolean default_map=false;
	private static int step=1;
	private static int final_line=-1;
	public static StringBuffer xml=null;
	/**
	 * 生成的xml文件的头部以及对辅助的NULL类的初始化
	 */
	public static final String head=
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
			"<beans xmlns=\"http://www.springframework.org/schema/beans\"\n"+
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
				"xmlns:oxm=\"http://www.springframework.org/schema/oxm\"\n"+
				"xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n"+ 
				"http://www.springframework.org/schema/beans/spring-beans.xsd \n"+
				"http://www.springframework.org/schema/oxm \n"+
				"http://www.springframework.org/schema/oxm/spring-oxm.xsd\">\n"+
				"<bean id=\"null\" class=\"com.luck.tool.Null\" />\n";
	/**
	 * bean头部 
	 */
	public static final String beanHead="<bean id=\"";
	/**
	 * bean尾部
	 */
	public static final String beanTail="</bean>\n";
	/**
	 * 全局的<beans>头部
	 */
	public static final String tall="</beans>\n";
	public static boolean isDefault_map() 
	{
		return default_map;
	}

	/**
	 * hole类的classpath
	 */
	public static final String holePath="\" class=\"com.luck.model.Hole\">\n";
	/**
	 * badflower类的classpath
	 */
	public static final String badFlowerPath="\" class=\"com.luck.model.BadFlower\">\n";
	/**
	 * flower类的classpath
	 */
	public static final String flowerPath="\" class=\"com.luck.model.Flower\">\n";
	/**
	 * money类的classpath
	 */
	public static final String moneyPath="\" class=\"com.luck.model.Money\">\n";
	/**
	 * monster类的classpath
	 */
	public static final String monsterPath="\" class=\"com.luck.model.Monster\">\n";
	/**
	 * mushroom类的classpath
	 */
	public static final String mushroonPath="\" class=\"com.luck.model.Mushroom\">\n";
	/**
	 * pipe类的classpath
	 */
	public static final String pipePath="\" class=\"com.luck.model.Pipe\">\n";
	/**
	 * star类的classpath
	 */
	public static final String starPath="\" class=\"com.luck.model.Star\">\n";
	/**
	 * turtle类的classpath
	 */
	public static final String turtlePath="\" class=\"com.luck.model.Turtle\">\n";
	/**
	 * wall类的classpath
	 */
	public static final String wallPath="\" class=\"com.luck.model.Wall\">\n";
	/**
	 * 构造方法的辅助字符串
	 */
	public static final String conVHead0="<constructor-arg index=\"0\" value=\"";
	public static final String conVHead1="<constructor-arg index=\"1\" value=\"";
	public static final String conVHead2="<constructor-arg index=\"2\" value=\"";
	public static final String conVHead3="<constructor-arg index=\"3\" value=\"";
	public static final String conVHead4="<constructor-arg index=\"4\" value=\"";
	public static final String conVHead5="<constructor-arg index=\"5\" value=\"";
	public static final String conRHead0="<constructor-arg index=\"0\" ref=\"";
	public static final String conRHead1="<constructor-arg index=\"1\" ref=\"";
	public static final String conRHead2="<constructor-arg index=\"2\" ref=\"";
	public static final String conRHead3="<constructor-arg index=\"3\" ref=\"";
	public static final String conRHead4="<constructor-arg index=\"4\" ref=\"";
	public static final String conRHead5="<constructor-arg index=\"5\" ref=\"";
	public static final String conTail="\"/>\n";
	private int mouseLocaX=0;
	private int mouseLocaY=0;
	private Image backImage=null;
	private Graphics gback=null;
	private static int length=0;
	/**
	 * 不同类型的实物的大小横坐标
	 */
	public static int[] SIZE_X={0,71,71,50,50,50,50,50,50,50,0,0,55,50,50,50,49,49,50,32,167};
	/**
	 * 不同类型的实物大小的纵坐标
	 */
	public static int[] SIZE_Y={0,147,147,50,54,50,50,50,50,50,0,0,58
		,50,50,50,67,67,54,35,73};
	/**
	 * 什么都没有点击
	 */
	public static final int NO=0;
	/**
	 * 普通管道
	 */
	public static final int PIPE=1;
	/**
	 * 带有食人花的管道
	 */
	public static final int PIPE_WITH_FLOWER=2;
	/**
	 * 金币
	 */
	public static final int MONEY=3;
	/**
	 * 左方向的板栗仔
	 */
	public static final int MONSTER_L=4;
	/**
	 * 右方向的板栗仔
	 */
	public static final int MONSTER_R=18;
	/**
	 * 砖块
	 */
	public static final int WALL=5;
	/**
	 * 含有星星的砖块
	 */
	public static final int WALL_WITH_STAR=6;
	/**
	 * 带有蘑菇的砖块
	 */
	public static final int WALL_WITH_MUSHROOM=7;
	/**
	 * 带有花的砖块
	 */
	public static final int WALL_WITH_FLOWER=8;
	/**
	 * 带有金币的砖块
	 */
	public static final int WALL_WITH_MONEY=9;
	/**
	 * 食人花
	 */
	public static final int BADFLOWER=12;
	/**
	 * 子弹花
	 */
	public static final int FLOWER=13;
	/**
	 * 星星
	 */
	public static final int STAR=14;
	/**
	 * 蘑菇
	 */
	public static final int MUSHROOM=15;
	/**
	 * 左方向乌龟
	 */
	public static final int TURTLE_L=16;
	/**
	 * 右方向乌龟
	 */
	public static final int TURTLE_R=17;
	/**
	 * 橡皮线
	 */
	public static final int CLEAR=19;
	/**
	 * 深洞
	 */
	public static final int HOLE=20;
	///////////////////////////////////////////////////////////////////////////////////////
	public static final ImageIcon PIPE_ICO=new ImageIcon(ImageTool.PIPE_ICO);
	public static final ImageIcon PIPE_WITH_FLOWER_ICO=new ImageIcon(ImageTool.PIPE_WITH_FLOWER_ICO);
	public static final ImageIcon MONEY_ICO=new ImageIcon(ImageTool.MONEY_ICO);
	public static final ImageIcon MONSTER_L_ICO=new ImageIcon(ImageTool.MONSTER_L_ICO);
	public static final ImageIcon MONSTER_R_ICO=new ImageIcon(ImageTool.MONSTER_R_ICO);
	public static final ImageIcon TURTLE_L_ICO=new ImageIcon(ImageTool.TURTLE_L_ICO);
	public static final ImageIcon TURTLE_R_ICO=new ImageIcon(ImageTool.TURTLE_R_ICO);
	public static final ImageIcon WALL_ICO=new ImageIcon(ImageTool.WALL_ICO);
	public static final ImageIcon WALL_WITH_STAR_ICO=new ImageIcon(ImageTool.WALL_WITH_STAR_ICO);
	public static final ImageIcon WALL_WITH_MUSHROOM_ICO=new ImageIcon(ImageTool.WALL_WITH_MUSHROOM_ICO);
	public static final ImageIcon WALL_WITH_FLOWER_ICO=new ImageIcon(ImageTool.WALL_WITH_FLOWER_ICO);
	public static final ImageIcon WALL_WITH_MONEY_ICO=new ImageIcon(ImageTool.WALL_WITH_MONEY_ICO);
	public static final ImageIcon lEFT_ICO=new ImageIcon(ImageTool.lEFT_ICO);
	public static final ImageIcon RIGHT_ICO=new ImageIcon(ImageTool.RIGHT_ICO);
	public static final ImageIcon CLEAE=new ImageIcon(ImageTool.CLEAR);
	public static final ImageIcon HOLE_ICO=new ImageIcon(ImageTool.HOLE_ICO);
	/**
	 * 鼠标的状态
	 */
	public static int mouseStatus=0;
	private static int locaX=0;
	private static int locaY=0;
	private static int sizeX=1366;
	private static int sizeY=728;
	private static int cutLine=(int)(sizeY*0.90);//地面分割线
	private static int count=-1;
	private static boolean incr=false;
	private static boolean desc=false;
	private static Map<Integer, Model> models=new HashMap<Integer, Model>();
	/**
	 * 地图编辑器驱动方法，初始化地图编辑器
	 */
	public static void setDefault_map(boolean default_map)
	{
		Main.default_map = default_map;
	}
	public void launch()
	{
		setBounds(locaX, locaY, sizeX, sizeY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		MouseWork mouseWork=new MouseWork();
		addMouseListener(mouseWork);
		addMouseMotionListener(mouseWork);
		JButton pipe=new JButton(PIPE_ICO);
		pipe.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=PIPE;
			}
		});
		JButton pipeWithFlower=new JButton(PIPE_WITH_FLOWER_ICO);
		pipeWithFlower.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=PIPE_WITH_FLOWER;
			}
		});
		JButton money=new JButton(MONEY_ICO);
		money.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=MONEY;
			}
		});
		JButton monsterl=new JButton(MONSTER_L_ICO);
		monsterl.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=MONSTER_L;
			}
		});
		JButton monsterr=new JButton(MONSTER_R_ICO);
		monsterr.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=MONSTER_R;
			}
		});
		JButton turtlel=new JButton(TURTLE_L_ICO);
		turtlel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=TURTLE_L;
			}
		});
		JButton turtler=new JButton(TURTLE_R_ICO);
		turtler.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=TURTLE_R;
			}
		});
		JButton wall=new JButton(WALL_ICO);
		wall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=WALL;
			}
		});
		JButton wallWithStar=new JButton(WALL_WITH_STAR_ICO);
		wallWithStar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=WALL_WITH_STAR;
			}
		});
		JButton wallWithMushroom=new JButton(WALL_WITH_MUSHROOM_ICO);
		wallWithMushroom.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=WALL_WITH_MUSHROOM;
			}
		});
		JButton wallWithFlower=new JButton(WALL_WITH_FLOWER_ICO);
		wallWithFlower.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=WALL_WITH_FLOWER;
			}
		});
		JButton wallWithMoney=new JButton(WALL_WITH_MONEY_ICO);
		wallWithMoney.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=WALL_WITH_MONEY;
			}
		});
		JButton left=new JButton(lEFT_ICO);
		left.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				incr=true;
				new Incr().start();
			}
			public void mouseReleased(MouseEvent e) 
			{
				incr=false;
			}
		});
		JButton right=new JButton(RIGHT_ICO);
		right.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				desc=true;
				new Desc().start();
			}
			public void mouseReleased(MouseEvent e) 
			{
				desc=false;
			}
		});
		JButton hole=new JButton(HOLE_ICO);
		hole.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=HOLE;
			}
		});
		JButton load=new JButton("载入存档！");
		load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					FileInputStream test=new FileInputStream(new File("bin/save.xml"));
					test.read();
				} catch (FileNotFoundException e1) 
				{
					JDialog warning=new JDialog();
					warning.setTitle("警告！！！");
					warning.setBounds(580, 300, 50, 50);
					JButton bu=new JButton("没有存档！！！");
					bu.addActionListener(e2 -> warning.setVisible(false));
					warning.add(bu);
					warning.pack();
					warning.setVisible(true);
					return;
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				new Control().work(1024);
			}
		});
		JButton complete=new JButton("开始游戏！");
		complete.addActionListener(new ActionListener() 
		{
			private BufferedWriter bw;
			public void actionPerformed(ActionEvent e) 
			{
				if(!empty())
				{
					parse(models,0);
					try
					{
						bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bin/game.properties")));
						bw.write("ALL_STEP="+step);
						bw.flush();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}else
				{
					try
					{
						if(step!=1)
						{
							bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bin/game.properties")));
							bw.write("ALL_STEP="+(step-1));
							bw.flush();
						}
						else
						{
							if(step==1)
							{
								default_map=true;
							}
						}
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				
				setVisible(false);
				new Control().work(0);
			}

			private boolean empty()
			{
				for(Entry<Integer, Model> model:models.entrySet())
				{
					if(model.getValue().isAdd())return false;
				}
				return true;
			}
		});
		JButton clear=new JButton(CLEAE);
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mouseStatus=CLEAR;
			}
		});
		JButton next=new JButton("下一关!");
		next.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				parse(models,0);
				models.clear();
				step++;
				final_line=-1;
				xml=new StringBuffer();
			}
		});
		setLayout(new FlowLayout());
		add(left);
		add(wallWithMoney);
		add(wallWithFlower);
		add(wallWithMushroom);
		add(wallWithStar);
		add(wall);
		add(monsterl);
		add(monsterr);
		add(turtlel);
		add(turtler);
		add(money);
		add(pipeWithFlower);
		add(pipe);
		add(hole);
		add(right);
		add(clear);
		add(next);
		add(load);
		add(complete);
		new PaintThread().start();
		setVisible(true);
	}
	private class Desc extends Thread
	{
		public void run()
		{
			while(desc)
			{
				length+=10;
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
	private class Incr extends Thread
	{
		public void run()
		{
			while(incr)
			{
				length-=10;
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
	private class PaintThread extends Thread
	{
		public void run()
		{
			while(true)
			{
				repaint();
				try
				{
					sleep(24);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	private class MouseWork extends MouseAdapter
	{
		public synchronized void mouseClicked(MouseEvent e) 
		{
			int x=mouseLocaX+length;
			int y=mouseLocaY;
			switch (mouseStatus)
			{
			case PIPE:
				count++;
				models.put(count, new Model(x,cutLine-SIZE_Y[PIPE],-1,PIPE));
				mouseStatus=NO;
				break;
			case PIPE_WITH_FLOWER:
				count++;
				models.put(count, new Model(x, cutLine-SIZE_Y[PIPE], count+1, PIPE_WITH_FLOWER));
				count++;
				models.put(count, new Model(x,cutLine-SIZE_Y[PIPE], count-1, BADFLOWER));
				mouseStatus=NO;
				break;
			case MONEY:
				count++;
				models.put(count, new Model(x, y,-1,MONEY));
				mouseStatus=NO;
				break;
			case MONSTER_L:
				count++;
				models.put(count, new Model(x, y,-1,MONSTER_L));
				mouseStatus=NO;
				break;
			case MONSTER_R:
				count++;
				models.put(count, new Model(x, y,-1,MONSTER_R));
				mouseStatus=NO;
				break;
			case TURTLE_L:
				count++;
				models.put(count, new Model(x, y,-1,TURTLE_L));
				mouseStatus=NO;
				break;
			case TURTLE_R:
				count++;
				models.put(count, new Model(x, y,-1,TURTLE_R));
				mouseStatus=NO;
				break;
			case WALL:
				count++;
				models.put(count, new Model(x, y,-1,WALL));
				mouseStatus=NO;
				break;
			case WALL_WITH_STAR:
				count++;
				models.put(count, new Model(x-((SIZE_X[WALL]-SIZE_X[STAR])>>1), y+SIZE_Y[STAR], count+1, WALL_WITH_STAR));
				count++;
				models.put(count, new Model(x, y, count-1, STAR));
				mouseStatus=NO;
				break;
			case WALL_WITH_MUSHROOM:
				count++;
				models.put(count, new Model(x-((SIZE_X[WALL]-SIZE_X[MUSHROOM])>>1), y+SIZE_Y[MUSHROOM], count+1, WALL_WITH_MUSHROOM));
				count++;
				models.put(count, new Model(x, y, count-1, MUSHROOM));
				mouseStatus=NO;
				break;
			case WALL_WITH_FLOWER:
				count++;
				models.put(count, new Model(x-((SIZE_X[WALL]-SIZE_X[FLOWER])>>1), y+SIZE_Y[FLOWER], count+1, WALL_WITH_FLOWER));
				count++;
				models.put(count, new Model(x, y, count-1, FLOWER));
				mouseStatus=NO;
				break;
			case WALL_WITH_MONEY:
				count++;
				models.put(count, new Model(x-((SIZE_X[WALL]-SIZE_X[MONEY])>>1), y+SIZE_Y[MONEY], -2, WALL_WITH_MONEY));
				mouseStatus=NO;
				break;
			case CLEAR:
				mouseStatus=NO;
				clear(get(e.getX()+length),get(e.getY()));
				break;
			case HOLE:
				count++;
				models.put(count, new Model(x, cutLine, -1, HOLE));
				mouseStatus=NO;
				break;
			}
		}
		private synchronized void clear(int x,int y) 
		{
			for(Entry<Integer, Model> model:models.entrySet())
			{
				System.out.println(model);
				if(!model.getValue().isAdd())continue;
				if(model.getValue().getRec().contains(x, y))
				{
					model.getValue().setAdd(false);
					if(models.get(model.getValue().getLink())!=null)models.get(model.getValue().getLink()).setAdd(false);
				}
			}
		}
		public void mouseMoved(MouseEvent e) 
		{
			mouseLocaX=get(e.getX());
			mouseLocaY=get(e.getY());
		}
		private int get(int x) 
		{
			return x-x%10;
		}
	}
	/**
	 * 绘画面板方法
	 */
	public void paint(Graphics g)
	{
		if(backImage==null)
		{
			backImage=this.createImage(sizeX,sizeY);
			gback=backImage.getGraphics();
		}
		super.paint(gback);
		char[] number=String.valueOf(step).toCharArray();
		for(int i=0;i<number.length;i++)
		{
			gback.drawImage(ImageTool.number[number[i]-48],10+(i==0?0:ImageTool.NUMBER_SIZE[number[i-1]-48]),40,null);
		}
		for(int i=0;i<((length%1588)+10);i++)
		{
			gback.drawImage(ImageTool.ground,i*1588-length, cutLine, null);
		}
		Map<Integer, Model> mo=models;
		for(Entry<Integer, Model> model:mo.entrySet())
		{
			if(!model.getValue().isAdd())continue;
			int x=model.getValue().getX();
			int y=model.getValue().getY();
			int type=model.getValue().getType();
			switch (type)
			{
			case PIPE:
				gback.drawImage(ImageTool.pipeImage,x-length,cutLine-SIZE_Y[PIPE],null);
				break;
			case PIPE_WITH_FLOWER:
				gback.drawImage(ImageTool.badflowerImages[17],x-length+((SIZE_X[PIPE]-SIZE_X[BADFLOWER])>>1),cutLine-SIZE_Y[PIPE]-SIZE_Y[BADFLOWER],null);
				gback.drawImage(ImageTool.pipeImage, x-length, cutLine-SIZE_Y[PIPE], null);
				break;
			case MONEY:
				gback.drawImage(ImageTool.moneyImage,x-length,y,null);
				break;
			case MONSTER_L:
				gback.drawImage(ImageTool.monsterLImage[4], x-length, y, null);
				break;
			case MONSTER_R:
				gback.drawImage(ImageTool.monsterRImage[4], x-length, y, null);
				break;
			case TURTLE_L:
				gback.drawImage(ImageTool.tortoiseL[0], x-length, y, null);
				break;
			case TURTLE_R:
				gback.drawImage(ImageTool.tortoiseR[0], x-length, y, null);
				break;
			case WALL:
				gback.drawImage(ImageTool.normalWallImage, x-length, y, null);
				break;
			case WALL_WITH_STAR:
			//	gback.drawImage(ImageTool.starImage, x-length, y, null);
				gback.drawImage(ImageTool.abnormalWallImage, x-length,y , null);
				break;
			case WALL_WITH_MUSHROOM:
				gback.drawImage(ImageTool.mushroomImage[4], x-length, y-SIZE_Y[MUSHROOM], null);
				gback.drawImage(ImageTool.abnormalWallImage, x-length,y, null);
				break;
			case WALL_WITH_FLOWER:
				gback.drawImage(ImageTool.flowerImage[4], x-length, y-SIZE_Y[FLOWER], null);
				gback.drawImage(ImageTool.abnormalWallImage, x-length,y , null);
				break;
			case WALL_WITH_MONEY:
				gback.drawImage(ImageTool.moneyImage, x-length, y-SIZE_Y[MONEY], null);
				gback.drawImage(ImageTool.abnormalWallImage, x-length,y , null);
				break;
			case STAR:
				gback.drawImage(ImageTool.starImage, x-length, y, null);
				break;
			case HOLE:
				gback.drawImage(ImageTool.hole, x-length, cutLine, null);
				break;
			}
		}
		switch (mouseStatus)
		{
		case PIPE:
			gback.drawImage(ImageTool.pipeImage, mouseLocaX, mouseLocaY, null);
			break;
		case PIPE_WITH_FLOWER:
			gback.drawImage(ImageTool.badflowerImages[17],mouseLocaX+((SIZE_X[PIPE]-SIZE_X[BADFLOWER])>>1),mouseLocaY,null);
			gback.drawImage(ImageTool.pipeImage, mouseLocaX, mouseLocaY+SIZE_Y[BADFLOWER], null);
			break;
		case MONEY:
			gback.drawImage(ImageTool.moneyImage,mouseLocaX,mouseLocaY,null);
			break;
		case MONSTER_L:
			gback.drawImage(ImageTool.monsterLImage[4], mouseLocaX, mouseLocaY, null);
			break;
		case MONSTER_R:
			gback.drawImage(ImageTool.monsterRImage[4], mouseLocaX, mouseLocaY, null);
			break;
		case TURTLE_L:
			gback.drawImage(ImageTool.tortoiseL[0], mouseLocaX, mouseLocaY, null);
			break;
		case TURTLE_R:
			gback.drawImage(ImageTool.tortoiseR[0], mouseLocaX, mouseLocaY, null);
			break;
		case WALL:
			gback.drawImage(ImageTool.normalWallImage, mouseLocaX, mouseLocaY, null);
			break;
		case WALL_WITH_STAR:
			gback.drawImage(ImageTool.starImage, mouseLocaX, mouseLocaY, null);
			gback.drawImage(ImageTool.abnormalWallImage, mouseLocaX-((SIZE_X[WALL]-SIZE_X[STAR])>>1),mouseLocaY+SIZE_Y[STAR] , null);
			break;
		case WALL_WITH_MUSHROOM:
			gback.drawImage(ImageTool.mushroomImage[4], mouseLocaX, mouseLocaY, null);
			gback.drawImage(ImageTool.abnormalWallImage, mouseLocaX-((SIZE_X[WALL]-SIZE_X[MUSHROOM])>>1),mouseLocaY+SIZE_Y[MUSHROOM] , null);
			break;
		case WALL_WITH_FLOWER:
			gback.drawImage(ImageTool.flowerImage[4], mouseLocaX, mouseLocaY, null);
			gback.drawImage(ImageTool.abnormalWallImage, mouseLocaX-((SIZE_X[WALL]-SIZE_X[FLOWER])>>1),mouseLocaY+SIZE_Y[FLOWER] , null);
			break;
		case WALL_WITH_MONEY:
			gback.drawImage(ImageTool.moneyImage, mouseLocaX, mouseLocaY, null);
			gback.drawImage(ImageTool.abnormalWallImage, mouseLocaX-((SIZE_X[WALL]-SIZE_X[MONEY])>>1),mouseLocaY+SIZE_Y[MONEY] , null);
			break;
		case CLEAR:
			gback.drawImage(ImageTool.CLEAR, mouseLocaX-SIZE_X[CLEAR]/2,mouseLocaY-SIZE_Y[CLEAR]/2,null );
			break;
		case HOLE:
			gback.drawImage(ImageTool.hole, mouseLocaX, mouseLocaY, null);
			break;
		}
		g.drawImage(backImage, 0, 0, null);
	}
	/**
	 * 解析已经编辑的地图
	 * @return 
	 */
	public static void parse(Map<Integer, Model> models,int ii)
	{
		int	badflower=0;
		int flower=0;
		int _money=0;
		int monster=0;
		int mushroom=0;
		int	_pipe=0;
		int star=0;
		int turtle=0; 
		int _wall=0;
		int _hole=0;
		StringBuffer mushroomNeedDraw=new StringBuffer();
		StringBuffer starNeedDraw=new StringBuffer();
		StringBuffer flowerNeedDraw=new StringBuffer();
		StringBuffer after=new StringBuffer();
		xml=new StringBuffer();
		xml.append(head);
		for(Entry<Integer,Model> model:models.entrySet())
		{
			if(!model.getValue().isAdd())continue;
			int linkx=0;
			int linky=0;
			int x=model.getValue().getX();
			int y=model.getValue().getY();
			int type=model.getValue().getType();
			int link=model.getValue().getLink();
			Model linkModel=models.get(link);
			if(linkModel!=null)
			{
				linkx=linkModel.getX();
				linky=linkModel.getY();
				linkModel.setAdd(false);
			}
			final_line=Math.max(Math.max(final_line,linkx),x);
			String name=null;
			String linkname=null;
			switch (type) 
			{
			case PIPE:
				_pipe++;
				name="pipe"+_pipe;
				xml.append(beanHead+name+pipePath+conVHead0+x+conTail+conVHead1+"false"+conTail+beanTail);
				break;
			case PIPE_WITH_FLOWER:
				_pipe++;
				name="pipe"+_pipe;
				xml.append(beanHead+name+pipePath+conVHead0+x+conTail+conVHead1+"true"+conTail+beanTail);
				badflower++;
				linkname="badflower"+badflower;
				xml.append(beanHead+linkname+badFlowerPath+conVHead0+linkx+conTail+conVHead1+linky+conTail+conRHead2+name+conTail+beanTail);
				break;
			case MONEY:
				_money++;
				name="money"+_money;
				xml.append(beanHead+name+moneyPath+conVHead0+x+conTail+conVHead1+y+conTail+beanTail);
				break;
			case MONSTER_L:
				monster++;
				name="monster"+monster;
				xml.append(beanHead+name+monsterPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"-1"+conTail+beanTail);
				break;
			case MONSTER_R:
				monster++;
				name="monster"+monster;
				xml.append(beanHead+name+monsterPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"1"+conTail+beanTail);
				break;
			case TURTLE_L:
				turtle++;
				name="turtle"+turtle;
				xml.append(beanHead+name+turtlePath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"-1"+conTail+beanTail);
				break;
			case TURTLE_R:
				turtle++;
				name="turtle"+turtle;
				xml.append(beanHead+name+turtlePath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"1"+conTail+beanTail);
				break;
			case WALL:
				_wall++;
				name="wall"+_wall;
				if(link==-2)after.append(","+_wall);
				xml.append(beanHead+name+wallPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"true"+conTail+conRHead3+"null"+conTail+beanTail);
				break;
			case WALL_WITH_STAR:
				_wall++;
				name="wall"+_wall;
				star++;
				linkname="star"+star;
				xml.append(beanHead+name+wallPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"false"+conTail+conRHead3+linkname+conTail+beanTail);
				xml.append(beanHead+linkname+starPath+conVHead0+linkx+conTail+conVHead1+linky+conTail+beanTail);
				break;
			case WALL_WITH_MUSHROOM:
				_wall++;
				name="wall"+_wall;
				mushroom++;
				linkname="mushroom"+mushroom;
				xml.append(beanHead+name+wallPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"false"+conTail+conRHead3+linkname+conTail+beanTail);
				xml.append(beanHead+linkname+mushroonPath+conVHead0+linkx+conTail+conVHead1+linky+conTail+beanTail);
				break;
			case WALL_WITH_FLOWER:
				_wall++;
				name="wall"+_wall;
				flower++;
				linkname="flower"+flower;
				xml.append(beanHead+name+wallPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"false"+conTail+conRHead3+linkname+conTail+beanTail);
				xml.append(beanHead+linkname+flowerPath+conVHead0+linkx+conTail+conVHead1+linky+conTail+beanTail);
				break;
			case WALL_WITH_MONEY:
				_wall++;
				name="wall"+_wall;
				xml.append(beanHead+name+wallPath+conVHead0+x+conTail+conVHead1+y+conTail+conVHead2+"false"+conTail+conRHead3+"null"+conTail+beanTail);
				break;
			case HOLE:
				_hole++;
				name="hole"+_hole;
				xml.append(beanHead+name+holePath+conVHead0+x+conTail+beanTail);
				break;
			case BADFLOWER:
				badflower++;
				name="badflower"+badflower;
				_pipe++;
				linkname="pipe"+_pipe;
				xml.append(beanHead+name+badFlowerPath+conVHead0+x+conTail+conVHead1+y+conTail+conRHead2+linkname+conTail+beanTail);
				xml.append(beanHead+linkname+pipePath+conVHead0+linkx+conTail+conVHead1+"true"+conTail+beanTail);
				break;
			case MUSHROOM:
				if(link!=-1)
				{
					linkModel.setAdd(true);
					break;
				}
				mushroom++;
				mushroomNeedDraw.append(","+mushroom);
				name="mushroom"+mushroom;
				xml.append(beanHead+name+mushroonPath+conVHead0+x+conTail+conVHead1+y+conTail+beanTail);
				break;
			case STAR:
				if(link!=-1)
				{
					linkModel.setAdd(true);
					break;
				}
				star++;
				starNeedDraw.append(","+star);
				name="star"+star;
				xml.append(beanHead+name+starPath+conVHead0+x+conTail+conVHead1+y+conTail+beanTail);
				break;
			case FLOWER:
				if(link!=-1)
				{
					linkModel.setAdd(true);
					break;
				}
				flower++;
				flowerNeedDraw.append(","+flower);
				System.out.println("132456");
				name="flower"+flower;
				xml.append(beanHead+name+flowerPath+conVHead0+x+conTail+conVHead1+y+conTail+beanTail);
				break;
			}
		}
		xml.append(tall);
		if(ii==2)
		{
			try
			{
				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("bin/save.xml"))));
				bw.write(xml.toString());
				bw.flush();
				bw.close();
				bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("bin/save_number.properties"))));
				String pro=
						"badflower="+badflower+"\n"+
						"flower="+flower+"\n"+
						"money="+_money+"\n"+
						"monster="+monster+"\n"+
						"mushroom="+mushroom+"\n"+
						"pipe="+_pipe+"\n"+
						"star="+star+"\n"+
						"turtle="+turtle+"\n"+
						"wall="+_wall+"\n"+
						"hole="+_hole+"\n"+
						"mushroomNeedDraw="+mushroomNeedDraw.toString()+","+"\n"+
						"starNeedDraw="+starNeedDraw.toString()+","+"\n"+
						"flowerNeedDraw="+flowerNeedDraw.toString()+","+"\n"+
						"after="+after.toString()+","+"\n";
				bw.write(pro);
				bw.flush();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else
		try
		{
			System.out.println(step);
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("bin/beans.map"+step+".xml"))));
			bw.write(xml.toString());
			bw.flush();
			bw.close();
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("bin/map"+step+".properties"))));
			String pro=
					"length="+length+"\n"+
					"final_line="+(final_line+400)+"\n"+
					"badflower="+badflower+"\n"+
					"flower="+flower+"\n"+
					"money="+_money+"\n"+
					"monster="+monster+"\n"+
					"mushroom="+mushroom+"\n"+
					"pipe="+_pipe+"\n"+
					"star="+star+"\n"+
					"turtle="+turtle+"\n"+
					"wall="+_wall+"\n"+
					"hole="+_hole+"\n"+
					"";
			bw.write(pro);
			bw.flush();
			bw.close();
			System.out.println("输出成功");
		} catch (FileNotFoundException e) 
		{
			System.out.println("输出失败");
			e.printStackTrace();
		} catch (IOException e) 
		{
			System.out.println("输出失败");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		new Main().launch();
	}
}