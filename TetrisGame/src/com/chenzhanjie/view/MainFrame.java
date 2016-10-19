package com.chenzhanjie.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Dao.Square;
import com.chenzhanjie.Dao.TetrisTask;
import com.chenzhanjie.Dao.impl.PieceCreatorImpl;
import com.chenzhanjie.Util.IconUtil;
import com.chenzhanjie.Util.ImageUtil;

import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;


import java.awt.Color;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
/**
 * 整个游戏界面使用一个MainFrame的类来实现
 * 整个游戏界面包括一个GamePanel和另外的一个JPanel和NextPiecePanel，
 * 该JPanel代表工具界面，包括分数的计算、当前游戏级别的显示、游戏的操作
 * NextPiecePanel显示下一个大方块。
 * 包含多种方法。
 * 
 *
 */
public class MainFrame extends JFrame {
	//级别
	 private JLabel levelTextLabel = new JLabel("级别");
	 private JLabel levelLabel = new JLabel();
	 private Box levelTextBox = Box.createHorizontalBox();
	 private Box levelBox = Box.createHorizontalBox();
	 //分数
	 private Box scoreTextBox = Box.createHorizontalBox();
	 private Box scoreBox = Box.createHorizontalBox();
	 private JLabel scoreTextLabel = new JLabel("分数");
	 private JLabel scoreLabel = new JLabel();
	 //下一个
	 private Box nextTextBox = Box.createHorizontalBox();
	 private JLabel nextTextLabel = new JLabel("下一个");
	 //继续
	 private Box resumeBox = Box.createHorizontalBox();
	 private JLabel resumeLabel = new JLabel();
	   //暂停
	 private Box pauseBox = Box.createHorizontalBox();
	 private JLabel pauseLabel = new JLabel();
	 //开始
	 private Box startBox = Box.createHorizontalBox();
	 private JLabel startLabel = new JLabel(); 

	 //工具类
	 private JPanel toolPanel = new JPanel();
	 //下一个
	 private NextPiecePanel nextpanel;
	//游戏
	 private GamePanel gamepanel;
	//暂停的标识, true为暂停
	private boolean pauseFlag = false;

	 
	//当前正在运动的对象
	private Piece currentPiece;
	//下一个大方块对象
	private Piece nextPiece;
	public NextPiecePanel getNextpanel() {
		return nextpanel;
	}

	public void setNextpanel(NextPiecePanel nextpanel) {
		this.nextpanel = nextpanel;
	}

	//下一个Piece的位置
	private final static int NEXT_X = 25;
	private final static int NEXT_Y = 15;
	//当前Piece的开始X座标
	private final static int BEGIN_X = Piece.SQUARE_BORDER * 6;
	//当前Piece的开始Y座标
	private final static int BEGIN_Y = 0;
	
	private  int currentLevel;

	//定时器
	private TetrisTask tetrisTask;
	private Timer timer;
	//界面二维数组
	private Square[][] squares;
	
	PieceCreatorImpl creator=new PieceCreatorImpl();
	//分数
	private int score=90;
	
	

	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public void setCurrentPiece(Piece currentPiece) {
		this.currentPiece = currentPiece;
	}
	
	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public Piece getNextPiece() {
		return nextPiece;
	}

	public GamePanel getGamepanel() {
		return gamepanel;
	}

	public void setGamepanel(GamePanel gamepanel) {
		this.gamepanel = gamepanel;
	}

	public void setNextPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}

	//创建下一个
	private void createNextPiece() {
		System.out.println("下一个");
		this.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);
		this.nextpanel.repaint();
		this.repaint();
		
	}
	//开始游戏
	public void start() {
		System.out.println("开始");
		this.pauseFlag = false;
		//创建下一个大方块
		createNextPiece();
		//创建当前运动的大方块
		this.currentPiece = this.creator.createPiece(BEGIN_X, BEGIN_Y);
		System.out.println("//初始化定时器 ");
		
		//初始化定时器 
		this.timer=new Timer();
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		System.out.println("时间时间时间："+tetrisTask);
		System.out.println("时间是都少："+timer);
		this.timer.schedule(this.tetrisTask, 0, time);
		
		
	}
	

	//按键盘上时触发的方法
	public void change() {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		this.currentPiece.change();
		int minx=this.currentPiece.getMinXLocation();
		//左边越界
		if(minx<0){
			this.currentPiece.setSquaresXLocation(-minx);
		}
		//判断转换后右边是否越界
		int maxX = this.currentPiece.getMaxXLocation();
		int gamePanelWidth = this.gamepanel.getWidth();
		//右边越界
		if (maxX > gamePanelWidth) {
			//左移超过GamePanel宽的部分
			this.currentPiece.setSquaresXLocation(-(maxX - gamePanelWidth));
		}
		this.gamepanel.repaint();
	}
	//按键盘左, 参数为距离(一格)
	public void left(int size) {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		//判断是否已经在最左边边界
		if (this.currentPiece.getMinXLocation() <= 0) return;
		//判断左边是否有障碍
		if (this.isleft())  return;
		//得出移动距离
		int distance = -(Piece.SQUARE_BORDER * size);
		this.currentPiece.setSquaresXLocation(distance);
		this.gamepanel.repaint();
	}
	//按键盘右, 参数为距离(一格)
	public void right(int size) {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		//判断是否超过GamePanel的宽
		if (this.currentPiece.getMaxXLocation() >= this.gamepanel.getWidth()) return;
		//判断右边是否有障碍
		if (this.isright())  return;
		int distance = Piece.SQUARE_BORDER * size;
		this.currentPiece.setSquaresXLocation(distance);
		this.gamepanel.repaint();
	}
	//下加速
	public void down() {
		if (this.pauseFlag) return;
		if (isButtom()||isBlock()) return;
		if (this.currentPiece == null) return;
		int distance = Piece.SQUARE_BORDER;
		//调用Piece的方法，改变纵坐标的值
		this.currentPiece.setSquaresYLocation(distance);
		this.gamepanel.repaint();
	}
	//判断是否到界面最底部
	public boolean isButtom() {
		return this.currentPiece.getMaxYLocation() >= this.gamepanel.getHeight();
	}
	//判断当前的Piece下降是否遇到障碍, 返回true表示遇到障碍, 返回false表示没有遇到
	public boolean isBlock() {
		List<Square> squares = this.currentPiece.getSquares();
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			//需要拿一个Square的最大Y座标
			Square block = getSquare(s.getBeginX(), s.getBeginY() + Piece.SQUARE_BORDER);
			//block非空表示遇到障碍
			if (block != null) return true;
		}
		return false;
	}
	//判断当前的Piece向左是否遇到障碍, 返回true表示遇到障碍, 返回false表示没有遇到
		public boolean isleft() {
			List<Square> squares = this.currentPiece.getSquares();
			for (int i = 0; i < squares.size(); i++) {
				Square s = squares.get(i);
				//需要拿一个Square的最小X座标
				Square block = getSquare(s.getBeginX()-Piece.SQUARE_BORDER, s.getBeginY() );
				//block非空表示遇到障碍
				if (block != null) return true;
			}
			return false;
		}
		//判断当前的Piece向右是否遇到障碍, 返回true表示遇到障碍, 返回false表示没有遇到
		public boolean isright() {
			List<Square> squares = this.currentPiece.getSquares();
			for (int i = 0; i < squares.size(); i++) {
				Square s = squares.get(i);
				//需要拿一个Square的最大x座标
				Square block = getSquare(s.getBeginX()+Piece.SQUARE_BORDER, s.getBeginY() );
				//block非空表示遇到障碍
				if (block != null) return true;
			}
			return false;
		}
	//根据开始座标在当前界面数组中查找相应的Square
	private Square getSquare(int beginX, int beginY) {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//与参数的开始座标相同并且图片不为空
				if (s.getImage() != null && (s.getBeginX() == beginX) && (s.getBeginY() == beginY)) {
					return s;
					}
			}
		}
			return null;
	}
	

	//初始化界面二维数组
	private void initSquares() {
		//得到宽可以存放的方块个数
		int xSize = this.gamepanel.getWidth()/Piece.SQUARE_BORDER;
		//得到高可以存放的方块个数
		int ySize = this.gamepanel.getHeight()/Piece.SQUARE_BORDER;
		//构造界面的二维数组
		this.squares = new Square[xSize][ySize];
		for(int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j] = new Square(null,Piece.SQUARE_BORDER * i, 
						Piece.SQUARE_BORDER * j);
			} 
		}
	}
	//将Piece中所有的Square都加入到二维数组中
	public void appendToSquares() {
		List<Square> squares = this.currentPiece.getSquares();
		//遍历Piece中的Square
		for(Square square : squares) {
			for(int i = 0; i < this.squares.length; i++) {
				for (int j = 0; j < this.squares[i].length; j++) {
					//得到当前界面中的Square
					Square s = this.squares[i][j];
					//判断Square是否一致
					if (s.equals(square)) this.squares[i][j] = square;
				}
			}
		}
		this.gamepanel.repaint();
	}

	//一个Piece对象完成下降
	public void finishDown() {
		//将当前的Piece设置为下一个Piece
		this.currentPiece = this.nextPiece;
		//设置新的Piece座标
		this.currentPiece.setSquaresXLocation(-NEXT_X);
		this.currentPiece.setSquaresXLocation(BEGIN_X);
		this.currentPiece.setSquaresYLocation(-NEXT_Y);
		this.currentPiece.setSquaresYLocation(BEGIN_Y);
		//创建下一个Piece
		createNextPiece();
	}
	//得到可以清理行集合
	public void cleanRows() {
		//使用一个集合保存被删除的行的索引
		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int j = 0; j < this.squares[0].length; j++) {
			int k = 0;
			for (int i = 0; i < this.squares.length; i++) {
				Square s = this.squares[i][j];
				//如果该格有图片, 则k+1
				if (s.getImage() != null) k++;
			}
			//如果整行都有图片, 则消除
			if (k == this.squares.length) {
				//再次对该行进行遍历, 设置该行所有格的图片为null
				for (int i = 0; i < this.squares.length; i++) {
					Square s = this.squares[i][j];
					s.setImage(null);
				}
				rowIndexs.add(j);
			}
		}
		//处理悬浮的Square
		handleDown(rowIndexs);
		
	}
	//处理行消除后其他Square的"下降", 参数为被删除的行的索引集合
	private void handleDown(List<Integer> rowIndexs) {
		if (rowIndexs.size() == 0) return;
		//从被删除的行中拿出索引最小的行
		int minCleanRow = rowIndexs.get(0);
		int cleanRowSize = rowIndexs.size();
		//处理下降的Square
		for (int j = this.squares[0].length - 1; j >= 0; j--) {
			if (j < minCleanRow) {
				//遍历上面的行, 即悬浮的行
				for (int i = 0; i < this.squares.length; i++) {
					Square s = this.squares[i][j];
					if (s.getImage() != null) {
						//得到下降前的图片
						Image image = s.getImage();
						s.setImage(null);
						//得到下降后对应的Square对象, 数组的二维值要加上消除行的行数
						Square sdown = this.squares[i][j + cleanRowSize];
						sdown.setImage(image);
					}
				}
			}
		}
		//分数
		addScore(cleanRowSize);
	}
	//加入分数
	private void addScore(int x) {
		//加分
		this.score += 10*x;
		this.scoreLabel.setText(String.valueOf(score));
		//如果可以被100整除, 则加一级
		if ((this.score % 100) == 0) {
			this.currentLevel += 1;
			this.levelLabel.setText(String.valueOf(this.currentLevel));
			//重新设置定时器
			this.timer.cancel();
			this.timer = new Timer();
			this.tetrisTask = new TetrisTask(this);
			//设置方块下降速度
			int time = 1000 / this.currentLevel;
			timer.schedule(this.tetrisTask, 0, time);
		}
	}
	//暂停游戏
	public void pause() {
		this.pauseFlag = true;
		if (this.timer != null) this.timer.cancel();
		this.timer = null;
	}
	//继续游戏
	public void resume() {
		if (!this.pauseFlag) return;
		this.timer = new Timer();
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
	}
	//判断是否失败, true为失败, false反之
	public boolean isLost() {
		for (int i = 0; i < this.squares.length; i++) {
			Square s = this.squares[i][0];
			if (s.getImage() != null) return true;
		}
		return false;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		  this.currentLevel = 1;

		  this.gamepanel = new GamePanel(this);
		  
		  this.nextpanel=new NextPiecePanel(this);
		  
		  BoxLayout toolPanelLayout = new BoxLayout(this.toolPanel, BoxLayout.Y_AXIS); 
		  this.toolPanel.setLayout(toolPanelLayout);
		  this.toolPanel.setBorder(new EtchedBorder());
		  this.toolPanel.setBackground(Color.gray);
		  //分数
		  this.scoreTextBox.add(this.scoreTextLabel);
		  this.scoreLabel.setText(String.valueOf(this.score));
		  this.scoreBox.add(this.scoreLabel);
		  //级别
		  this.levelTextBox.add(this.levelTextLabel);
		  this.levelLabel.setText(String.valueOf(this.currentLevel));
		  this.levelBox.add(this.levelLabel);
		  //继续按钮
		  this.resumeLabel.setIcon(IconUtil.RESUME);
		  this.resumeLabel.setPreferredSize(new Dimension(3, 25));
		  this.resumeBox.add(this.resumeLabel);
		  //暂停按钮
		  this.pauseLabel.setIcon(IconUtil.PAUSE);
		  this.pauseLabel.setPreferredSize(new Dimension(3, 25));
		  this.pauseBox.add(this.pauseLabel);
		  //开始
		  this.startLabel.setIcon(IconUtil.START);
		  this.startLabel.setPreferredSize(new Dimension(3, 25));
		  this.startBox.add(this.startLabel);
		  //下一个
		  this.nextTextBox.add(this.nextTextLabel);
		  

		  this.toolPanel.add(Box.createVerticalStrut(10));
		  this.toolPanel.add(scoreTextBox);
		  this.toolPanel.add(Box.createVerticalStrut(10));
		  this.toolPanel.add(scoreBox);
		  this.toolPanel.add(Box.createVerticalStrut(10));
		  this.toolPanel.add(levelTextBox);
		  this.toolPanel.add(Box.createVerticalStrut(10));
		  this.toolPanel.add(levelBox);
		  this.toolPanel.add(Box.createVerticalStrut(15));
		  this.toolPanel.add(this.resumeBox);
		  this.toolPanel.add(Box.createVerticalStrut(15));
		  this.toolPanel.add(this.pauseBox);
		  this.toolPanel.add(Box.createVerticalStrut(15));
		  this.toolPanel.add(this.startBox);
		  this.toolPanel.add(Box.createVerticalStrut(30));
		  this.toolPanel.add(this.nextTextBox);
		 
		  this.nextpanel.setPreferredSize(new Dimension(80,80 ));
		  this.toolPanel.add(this.nextpanel);

		  this.toolPanel.add(Box.createHorizontalStrut(99));
		  
		  this.add(this.gamepanel, BorderLayout.CENTER);
		  this.add(this.toolPanel, BorderLayout.EAST);
		  this.setPreferredSize(new Dimension(349, 396));
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.setLocation(350, 200);
		  this.setResizable(false);
		  this.setTitle("俄罗斯方块");
		  this.pack();
		  
		  
		  this.resumeLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					resumeLabel.setIcon(IconUtil.RESUME_ON);
				}
				public void mouseExited(MouseEvent e) {
					resumeLabel.setIcon(IconUtil.RESUME);
				}
				public void mouseClicked(MouseEvent e) {
					//继续
					resume();
				}
			});
		  
		  this.pauseLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					pauseLabel.setIcon(IconUtil.PAUSE_ON);
				}
				public void mouseExited(MouseEvent e) {
					pauseLabel.setIcon(IconUtil.PAUSE);
				}
				public void mouseClicked(MouseEvent e) {
					//暂停
					pause();
				}
			});
		  
		  this.startLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					startLabel.setIcon(IconUtil.START_ON);
				}
				public void mouseExited(MouseEvent e) {
					startLabel.setIcon(IconUtil.START);
				}
				public void mouseClicked(MouseEvent e) {
					//初始化二维界面
					initSquares();
					//开始方法
					start();
				}
			});

		
		//添加键盘监听器
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//键盘的上
				if (e.getKeyCode() == 38) change();
				//左
				if (e.getKeyCode() == 37) left(1);
				//右
				if (e.getKeyCode() == 39) right(1);
				//下
				if (e.getKeyCode() == 40) down();
			}
		});
	}
}
