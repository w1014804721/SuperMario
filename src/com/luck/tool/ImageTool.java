package com.luck.tool;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JFrame;
import com.luck.main.Control;
/**
 * 
 * @author 王镜鑫
 * 该类用于加载程序所用到的所有的图片
 */
public class ImageTool extends JFrame
{ 
	private static final int loca=Integer.MAX_VALUE;
	public static final int NUMBER_SIZE[]={27,21,27,27,28,26,27,28,27,27};
	public static final int CLOCK_SIZE[]={28,24,30,31,24,31,27,29,30,28};
	public static final int MONEY_SIZE[]={28,25,25,27,23,29,27,32,28,29};
	public static final Toolkit tk = Toolkit.getDefaultToolkit();
	public static final Image hole=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/hole.png"));
	public static final Image HOLE_ICO=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/ico/hole.png"));
	public static final Image gameEnd=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/end.png"));
	public static final Image winB=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/winB.png"));
	public static final Image winS=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/winS.png"));
	public static final Image flag=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/flag.png"));
	public static final Image _final=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/final.png"));
	public static final Image tower=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/tower.png"));
	public static final Image back=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/background.png"));
	public static final Image ground=ImageTool.tk.getImage(Control.class.getClassLoader().getResource("images/ground.png"));
	public static final Image PIPE_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/p.png"));
	public static final Image PIPE_WITH_FLOWER_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/pwf.png"));
	public static final Image MONEY_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/m.png"));
	public static final Image MONSTER_L_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/mol.png"));
	public static final Image MONSTER_R_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/mor.png"));
	public static final Image TURTLE_L_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/tl.png"));
	public static final Image TURTLE_R_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/tr.png"));
	public static final Image WALL_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/w.png"));
	public static final Image WALL_WITH_STAR_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/ws.png"));
	public static final Image WALL_WITH_MUSHROOM_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/wm.png"));
	public static final Image WALL_WITH_FLOWER_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/wf.png"));
	public static final Image WALL_WITH_MONEY_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/wmo.png"));
	public static final Image lEFT_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/l.png"));
	public static final Image RIGHT_ICO=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/r.png"));
	public static final Image CLEAR=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/ico/clear.png"));
	public static final Image black=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/black.jpg"));
	public static final Image over=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/over.png"));
	public static final HashMap<Integer,Image> score=new HashMap<Integer, Image>();
	static
		{
			score.put(100,ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/score/100.png")));
			score.put(200,ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/score/200.png")));
			score.put(400,ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/score/400.png")));
			score.put(1000,ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/score/1000.png")));
			score.put(4000,ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/score/4000.png")));
		};
	public static final Image[] clock=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/w9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/clock/clock.png")),
		};
	public static final Image[] money=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/money/money.png")),
		};
	public static final Image[] life=
		{
			null,
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/l1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/l2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/l3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/l4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/l5.png")),
		};
	public static final Image[] showLife=
		{
			null,
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/s1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/s2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/s3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/s4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/life/s5.png")),
		};
	public static final Image[] number=
		{
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/0.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/1.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/2.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/3.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/4.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/5.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/6.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/7.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/8.png")),
				ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/number/9.png"))
		};
	public static final Image[] badflowerImages=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/11.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/12.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/13.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/14.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/15.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/16.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/17.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/18.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/19.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/20.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/21.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/22.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/23.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/24.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/25.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/26.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/27.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/28.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/29.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/30.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/31.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/32.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/33.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/34.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/badflower/35.png")),
		};
	public static final Image flowerImage[]=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/flower/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/flower/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/flower/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/flower/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/flower/4.png")),
		};
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓大玛丽奥↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public static final Image BIG_STAND_L=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/stand.png"));
	public static final Image BIG_STAND_R=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/stand.png"));
	public static final Image BIG_JUMPING_L=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/jump.png"));
	public static final Image BIG_JUMPING_R=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/jump.png"));
	public static final Image[] BIG_RUNNING_IMAGES_L=
			{
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/0.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/1.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/1.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/3.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/4.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/5.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/6.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/7.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/8.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/9.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/10.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/10.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/11.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/12.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/12.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/13.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/14.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/15.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/16.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/17.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/18.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/19.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/20.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioL/21.png")),
			};
	public static final Image[] BIG_RUNNING_IMAGES_R=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/11.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/12.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/12.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/13.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/14.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/15.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/16.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/17.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/18.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/19.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/20.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/bigmario/marioR/21.png")),
		};
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑大马里奥↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓小玛丽奥↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public static final Image SMALL_STAND_L=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/stand.png"));
	public static final Image SMALL_STAND_R=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/stand.png"));
	public static final Image SMALL_JUMPING_L=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/jump.png"));
	public static final Image SMALL_JUMPING_R=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/jump.png"));
	public static final Image[] SMALL_RUNNING_IMAGES_L=
			{
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/0.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/1.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/1.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/2.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/3.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/4.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/5.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/6.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/7.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/8.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/9.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/10.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/10.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/11.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/12.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/12.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/13.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/14.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/15.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/16.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/17.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/18.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/19.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/20.png")),
		ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioL/21.png")),
			};
	public static final Image[] SMALL_RUNNING_IMAGES_R=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/11.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/12.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/12.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/13.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/14.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/15.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/16.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/17.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/18.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/19.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/20.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mario/smallmario/marioR/21.png")),
		};
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑小玛丽奥↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	public static final Image moneyImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/money.png"));
	public static final Image monsterLImage[]=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterL/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterL/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterL/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterL/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterL/4.png")),
		};
	public static final Image monsterRImage[]=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterR/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterR/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/monsterR/4.png")),
		};
	public static final Image dieMonster=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/dead.png"));
	public static final Image mushroomImage[]=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mushroom/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mushroom/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mushroom/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mushroom/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/mushroom/4.png")),
		};
	public static final Image pipeImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/pipe.png"));
	public static final Image starImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/star.png"));
	public static final Image normalWallImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/normalwall.png"));//普通的wall
	public static final Image abnormalWallImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/abnormalwall.png"));//含有包含物的wall
	public static final Image afterabnormalWallImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/afterabnormalwall.png"));//含有包含物的被撞击之后的wall
	public static final Image[] badWall=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/左上.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/右上.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/右下.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/wall/左下.png"))
		};
	public static final Image[] tortoiseL=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseL/11.png"))
		};
	public static final Image[] tortoiseR=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/10.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/tortoiseR/11.png"))
		};
	public static final Image[] tortoiseRoll=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/4.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/5.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/6.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/7.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/8.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/9.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/tortoise/roll/10.png")),
		};
	public static final Image welcomeImage=ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/welcome.png"));//含有包含物的被撞击之后的wall
	public static final Image[] updie=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/updie/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/updie/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/monster/updie/2.png"))
		};
	public static final Image[] fireL=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireL/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireL/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireL/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireL/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireL/4.png"))
		};
	public static final Image[] fireR=
		{
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireR/0.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireR/1.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireR/2.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireR/3.png")),
			ImageTool.tk.getImage(ImageTool.class.getClassLoader().getResource("images/fire/fireR/4.png"))
		};
	/**
	 * 
	 * @param g 画板上下文
	 * 因为是延迟加载所以在一开始先把所有的图片加载出来
	 */ 
	public static void loadit(Graphics g)
	{
		System.out.println("load");
		for(int i=0;i<badflowerImages.length;i++)
		g.drawImage(badflowerImages[i], loca, loca, null);
		for(int i=0;i<flowerImage.length;i++)
		g.drawImage(flowerImage[i], loca, loca, null);
		g.drawImage(BIG_STAND_L, loca, loca, null);
		g.drawImage(BIG_STAND_R, loca, loca, null);
		g.drawImage(BIG_JUMPING_L, loca, loca, null);
		g.drawImage(BIG_JUMPING_R, loca, loca, null);
		for(int i=0;i<BIG_RUNNING_IMAGES_L.length;i++)
		g.drawImage(BIG_RUNNING_IMAGES_L[i], loca, loca, null);
		for(int i=0;i<BIG_RUNNING_IMAGES_R.length;i++)
		g.drawImage(BIG_RUNNING_IMAGES_R[i], loca, loca, null);
		g.drawImage(moneyImage, loca, loca, null);
		for(int i=0;i<monsterLImage.length;i++)
		g.drawImage(monsterLImage[i], loca, loca, null);
		for(int i=0;i<monsterRImage.length;i++)
		g.drawImage(monsterRImage[i], loca, loca, null);
		g.drawImage(dieMonster, loca, loca, null);
		for(int i=0;i<mushroomImage.length;i++)
		g.drawImage(mushroomImage[i], loca, loca, null);
		g.drawImage(welcomeImage, loca, loca, null);
		g.drawImage(pipeImage, loca, loca, null);
		g.drawImage(starImage, loca, loca, null);
		g.drawImage(normalWallImage, loca, loca, null);
		g.drawImage(abnormalWallImage, loca, loca, null);
		g.drawImage(afterabnormalWallImage, loca, loca, null);
		for(int i=0;i<badWall.length;i++)
		g.drawImage(badWall[i], loca, loca, null);
		for(int i=0;i<SMALL_RUNNING_IMAGES_L.length;i++)
		g.drawImage(SMALL_RUNNING_IMAGES_L[i],loca,loca, null);
		for(int i=0;i<SMALL_RUNNING_IMAGES_R.length;i++)
		g.drawImage(SMALL_RUNNING_IMAGES_R[i],loca,loca, null);
		g.drawImage(SMALL_JUMPING_L, loca, loca, null);
		g.drawImage(SMALL_JUMPING_R, loca, loca, null);
		g.drawImage(SMALL_STAND_L, loca, loca, null);
		g.drawImage(SMALL_STAND_R, loca, loca, null);
		for(int i=0;i<tortoiseR.length;i++)
		g.drawImage(tortoiseR[i],loca,loca, null);
		for(int i=0;i<tortoiseL.length;i++)
		g.drawImage(tortoiseL[i],loca,loca,null);
		for(int i=0;i<tortoiseRoll.length;i++)
		g.drawImage(tortoiseRoll[i],loca,loca, null);
		for(int i=0;i<updie.length;i++)
		g.drawImage(updie[i],loca,loca,null);
		for(int i=0;i<fireL.length;i++)
		g.drawImage(fireL[i],loca,loca,null);
		for(int i=0;i<fireR.length;i++)
		g.drawImage(fireR[i],loca,loca,null);
		for(int i=0;i<number.length;i++)
		g.drawImage(number[i], loca, loca, null);
		for(int i=1;i<life.length;i++)
		g.drawImage(life[i], loca, loca, null);
		for(int i=1;i<clock.length;i++)
		g.drawImage(clock[i], loca, loca, null);
		for(int i=1;i<money.length;i++)
		g.drawImage(money[i], loca, loca, null);
		for(Entry<Integer,Image> oneScore:score.entrySet())
		g.drawImage(oneScore.getValue(), loca, loca, null);
		g.drawImage(_final, loca, loca, null);
		g.drawImage(tower, loca, loca, null);
		g.drawImage(winS, loca, loca, null);
		g.drawImage(winB, loca, loca, null);
		g.drawImage(flag, loca, loca, null);
		g.drawImage(gameEnd, loca, loca, null);
		g.drawImage(black, loca, loca, null);
		g.drawImage(over, loca, loca, null);
		g.drawImage(hole, loca, loca, null);
		g.drawImage(ground, loca, loca, null);
		g.drawImage(back, loca, loca, null);
		
	}
}
