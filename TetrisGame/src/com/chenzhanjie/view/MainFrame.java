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
 * ������Ϸ����ʹ��һ��MainFrame������ʵ��
 * ������Ϸ�������һ��GamePanel�������һ��JPanel��NextPiecePanel��
 * ��JPanel�����߽��棬���������ļ��㡢��ǰ��Ϸ�������ʾ����Ϸ�Ĳ���
 * NextPiecePanel��ʾ��һ���󷽿顣
 * �������ַ�����
 * 
 *
 */
public class MainFrame extends JFrame {
	//����
	 private JLabel levelTextLabel = new JLabel("����");
	 private JLabel levelLabel = new JLabel();
	 private Box levelTextBox = Box.createHorizontalBox();
	 private Box levelBox = Box.createHorizontalBox();
	 //����
	 private Box scoreTextBox = Box.createHorizontalBox();
	 private Box scoreBox = Box.createHorizontalBox();
	 private JLabel scoreTextLabel = new JLabel("����");
	 private JLabel scoreLabel = new JLabel();
	 //��һ��
	 private Box nextTextBox = Box.createHorizontalBox();
	 private JLabel nextTextLabel = new JLabel("��һ��");
	 //����
	 private Box resumeBox = Box.createHorizontalBox();
	 private JLabel resumeLabel = new JLabel();
	   //��ͣ
	 private Box pauseBox = Box.createHorizontalBox();
	 private JLabel pauseLabel = new JLabel();
	 //��ʼ
	 private Box startBox = Box.createHorizontalBox();
	 private JLabel startLabel = new JLabel(); 

	 //������
	 private JPanel toolPanel = new JPanel();
	 //��һ��
	 private NextPiecePanel nextpanel;
	//��Ϸ
	 private GamePanel gamepanel;
	//��ͣ�ı�ʶ, trueΪ��ͣ
	private boolean pauseFlag = false;

	 
	//��ǰ�����˶��Ķ���
	private Piece currentPiece;
	//��һ���󷽿����
	private Piece nextPiece;
	public NextPiecePanel getNextpanel() {
		return nextpanel;
	}

	public void setNextpanel(NextPiecePanel nextpanel) {
		this.nextpanel = nextpanel;
	}

	//��һ��Piece��λ��
	private final static int NEXT_X = 25;
	private final static int NEXT_Y = 15;
	//��ǰPiece�Ŀ�ʼX����
	private final static int BEGIN_X = Piece.SQUARE_BORDER * 6;
	//��ǰPiece�Ŀ�ʼY����
	private final static int BEGIN_Y = 0;
	
	private  int currentLevel;

	//��ʱ��
	private TetrisTask tetrisTask;
	private Timer timer;
	//�����ά����
	private Square[][] squares;
	
	PieceCreatorImpl creator=new PieceCreatorImpl();
	//����
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

	//������һ��
	private void createNextPiece() {
		System.out.println("��һ��");
		this.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);
		this.nextpanel.repaint();
		this.repaint();
		
	}
	//��ʼ��Ϸ
	public void start() {
		System.out.println("��ʼ");
		this.pauseFlag = false;
		//������һ���󷽿�
		createNextPiece();
		//������ǰ�˶��Ĵ󷽿�
		this.currentPiece = this.creator.createPiece(BEGIN_X, BEGIN_Y);
		System.out.println("//��ʼ����ʱ�� ");
		
		//��ʼ����ʱ�� 
		this.timer=new Timer();
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		System.out.println("ʱ��ʱ��ʱ�䣺"+tetrisTask);
		System.out.println("ʱ���Ƕ��٣�"+timer);
		this.timer.schedule(this.tetrisTask, 0, time);
		
		
	}
	

	//��������ʱ�����ķ���
	public void change() {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		this.currentPiece.change();
		int minx=this.currentPiece.getMinXLocation();
		//���Խ��
		if(minx<0){
			this.currentPiece.setSquaresXLocation(-minx);
		}
		//�ж�ת�����ұ��Ƿ�Խ��
		int maxX = this.currentPiece.getMaxXLocation();
		int gamePanelWidth = this.gamepanel.getWidth();
		//�ұ�Խ��
		if (maxX > gamePanelWidth) {
			//���Ƴ���GamePanel��Ĳ���
			this.currentPiece.setSquaresXLocation(-(maxX - gamePanelWidth));
		}
		this.gamepanel.repaint();
	}
	//��������, ����Ϊ����(һ��)
	public void left(int size) {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		//�ж��Ƿ��Ѿ�������߽߱�
		if (this.currentPiece.getMinXLocation() <= 0) return;
		//�ж�����Ƿ����ϰ�
		if (this.isleft())  return;
		//�ó��ƶ�����
		int distance = -(Piece.SQUARE_BORDER * size);
		this.currentPiece.setSquaresXLocation(distance);
		this.gamepanel.repaint();
	}
	//��������, ����Ϊ����(һ��)
	public void right(int size) {
		if (this.pauseFlag) return;
		if (this.currentPiece == null) return;
		//�ж��Ƿ񳬹�GamePanel�Ŀ�
		if (this.currentPiece.getMaxXLocation() >= this.gamepanel.getWidth()) return;
		//�ж��ұ��Ƿ����ϰ�
		if (this.isright())  return;
		int distance = Piece.SQUARE_BORDER * size;
		this.currentPiece.setSquaresXLocation(distance);
		this.gamepanel.repaint();
	}
	//�¼���
	public void down() {
		if (this.pauseFlag) return;
		if (isButtom()||isBlock()) return;
		if (this.currentPiece == null) return;
		int distance = Piece.SQUARE_BORDER;
		//����Piece�ķ������ı��������ֵ
		this.currentPiece.setSquaresYLocation(distance);
		this.gamepanel.repaint();
	}
	//�ж��Ƿ񵽽�����ײ�
	public boolean isButtom() {
		return this.currentPiece.getMaxYLocation() >= this.gamepanel.getHeight();
	}
	//�жϵ�ǰ��Piece�½��Ƿ������ϰ�, ����true��ʾ�����ϰ�, ����false��ʾû������
	public boolean isBlock() {
		List<Square> squares = this.currentPiece.getSquares();
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			//��Ҫ��һ��Square�����Y����
			Square block = getSquare(s.getBeginX(), s.getBeginY() + Piece.SQUARE_BORDER);
			//block�ǿձ�ʾ�����ϰ�
			if (block != null) return true;
		}
		return false;
	}
	//�жϵ�ǰ��Piece�����Ƿ������ϰ�, ����true��ʾ�����ϰ�, ����false��ʾû������
		public boolean isleft() {
			List<Square> squares = this.currentPiece.getSquares();
			for (int i = 0; i < squares.size(); i++) {
				Square s = squares.get(i);
				//��Ҫ��һ��Square����СX����
				Square block = getSquare(s.getBeginX()-Piece.SQUARE_BORDER, s.getBeginY() );
				//block�ǿձ�ʾ�����ϰ�
				if (block != null) return true;
			}
			return false;
		}
		//�жϵ�ǰ��Piece�����Ƿ������ϰ�, ����true��ʾ�����ϰ�, ����false��ʾû������
		public boolean isright() {
			List<Square> squares = this.currentPiece.getSquares();
			for (int i = 0; i < squares.size(); i++) {
				Square s = squares.get(i);
				//��Ҫ��һ��Square�����x����
				Square block = getSquare(s.getBeginX()+Piece.SQUARE_BORDER, s.getBeginY() );
				//block�ǿձ�ʾ�����ϰ�
				if (block != null) return true;
			}
			return false;
		}
	//���ݿ�ʼ�����ڵ�ǰ���������в�����Ӧ��Square
	private Square getSquare(int beginX, int beginY) {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				Square s = this.squares[i][j];
				//������Ŀ�ʼ������ͬ����ͼƬ��Ϊ��
				if (s.getImage() != null && (s.getBeginX() == beginX) && (s.getBeginY() == beginY)) {
					return s;
					}
			}
		}
			return null;
	}
	

	//��ʼ�������ά����
	private void initSquares() {
		//�õ�����Դ�ŵķ������
		int xSize = this.gamepanel.getWidth()/Piece.SQUARE_BORDER;
		//�õ��߿��Դ�ŵķ������
		int ySize = this.gamepanel.getHeight()/Piece.SQUARE_BORDER;
		//�������Ķ�ά����
		this.squares = new Square[xSize][ySize];
		for(int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j] = new Square(null,Piece.SQUARE_BORDER * i, 
						Piece.SQUARE_BORDER * j);
			} 
		}
	}
	//��Piece�����е�Square�����뵽��ά������
	public void appendToSquares() {
		List<Square> squares = this.currentPiece.getSquares();
		//����Piece�е�Square
		for(Square square : squares) {
			for(int i = 0; i < this.squares.length; i++) {
				for (int j = 0; j < this.squares[i].length; j++) {
					//�õ���ǰ�����е�Square
					Square s = this.squares[i][j];
					//�ж�Square�Ƿ�һ��
					if (s.equals(square)) this.squares[i][j] = square;
				}
			}
		}
		this.gamepanel.repaint();
	}

	//һ��Piece��������½�
	public void finishDown() {
		//����ǰ��Piece����Ϊ��һ��Piece
		this.currentPiece = this.nextPiece;
		//�����µ�Piece����
		this.currentPiece.setSquaresXLocation(-NEXT_X);
		this.currentPiece.setSquaresXLocation(BEGIN_X);
		this.currentPiece.setSquaresYLocation(-NEXT_Y);
		this.currentPiece.setSquaresYLocation(BEGIN_Y);
		//������һ��Piece
		createNextPiece();
	}
	//�õ����������м���
	public void cleanRows() {
		//ʹ��һ�����ϱ��汻ɾ�����е�����
		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int j = 0; j < this.squares[0].length; j++) {
			int k = 0;
			for (int i = 0; i < this.squares.length; i++) {
				Square s = this.squares[i][j];
				//����ø���ͼƬ, ��k+1
				if (s.getImage() != null) k++;
			}
			//������ж���ͼƬ, ������
			if (k == this.squares.length) {
				//�ٴζԸ��н��б���, ���ø������и��ͼƬΪnull
				for (int i = 0; i < this.squares.length; i++) {
					Square s = this.squares[i][j];
					s.setImage(null);
				}
				rowIndexs.add(j);
			}
		}
		//����������Square
		handleDown(rowIndexs);
		
	}
	//����������������Square��"�½�", ����Ϊ��ɾ�����е���������
	private void handleDown(List<Integer> rowIndexs) {
		if (rowIndexs.size() == 0) return;
		//�ӱ�ɾ���������ó�������С����
		int minCleanRow = rowIndexs.get(0);
		int cleanRowSize = rowIndexs.size();
		//�����½���Square
		for (int j = this.squares[0].length - 1; j >= 0; j--) {
			if (j < minCleanRow) {
				//�����������, ����������
				for (int i = 0; i < this.squares.length; i++) {
					Square s = this.squares[i][j];
					if (s.getImage() != null) {
						//�õ��½�ǰ��ͼƬ
						Image image = s.getImage();
						s.setImage(null);
						//�õ��½����Ӧ��Square����, ����Ķ�άֵҪ���������е�����
						Square sdown = this.squares[i][j + cleanRowSize];
						sdown.setImage(image);
					}
				}
			}
		}
		//����
		addScore(cleanRowSize);
	}
	//�������
	private void addScore(int x) {
		//�ӷ�
		this.score += 10*x;
		this.scoreLabel.setText(String.valueOf(score));
		//������Ա�100����, ���һ��
		if ((this.score % 100) == 0) {
			this.currentLevel += 1;
			this.levelLabel.setText(String.valueOf(this.currentLevel));
			//�������ö�ʱ��
			this.timer.cancel();
			this.timer = new Timer();
			this.tetrisTask = new TetrisTask(this);
			//���÷����½��ٶ�
			int time = 1000 / this.currentLevel;
			timer.schedule(this.tetrisTask, 0, time);
		}
	}
	//��ͣ��Ϸ
	public void pause() {
		this.pauseFlag = true;
		if (this.timer != null) this.timer.cancel();
		this.timer = null;
	}
	//������Ϸ
	public void resume() {
		if (!this.pauseFlag) return;
		this.timer = new Timer();
		this.tetrisTask = new TetrisTask(this);
		int time = 1000 / this.currentLevel;
		timer.schedule(this.tetrisTask, 0, time);
		this.pauseFlag = false;
	}
	//�ж��Ƿ�ʧ��, trueΪʧ��, false��֮
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
		  //����
		  this.scoreTextBox.add(this.scoreTextLabel);
		  this.scoreLabel.setText(String.valueOf(this.score));
		  this.scoreBox.add(this.scoreLabel);
		  //����
		  this.levelTextBox.add(this.levelTextLabel);
		  this.levelLabel.setText(String.valueOf(this.currentLevel));
		  this.levelBox.add(this.levelLabel);
		  //������ť
		  this.resumeLabel.setIcon(IconUtil.RESUME);
		  this.resumeLabel.setPreferredSize(new Dimension(3, 25));
		  this.resumeBox.add(this.resumeLabel);
		  //��ͣ��ť
		  this.pauseLabel.setIcon(IconUtil.PAUSE);
		  this.pauseLabel.setPreferredSize(new Dimension(3, 25));
		  this.pauseBox.add(this.pauseLabel);
		  //��ʼ
		  this.startLabel.setIcon(IconUtil.START);
		  this.startLabel.setPreferredSize(new Dimension(3, 25));
		  this.startBox.add(this.startLabel);
		  //��һ��
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
		  this.setTitle("����˹����");
		  this.pack();
		  
		  
		  this.resumeLabel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					resumeLabel.setIcon(IconUtil.RESUME_ON);
				}
				public void mouseExited(MouseEvent e) {
					resumeLabel.setIcon(IconUtil.RESUME);
				}
				public void mouseClicked(MouseEvent e) {
					//����
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
					//��ͣ
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
					//��ʼ����ά����
					initSquares();
					//��ʼ����
					start();
				}
			});

		
		//��Ӽ��̼�����
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				//���̵���
				if (e.getKeyCode() == 38) change();
				//��
				if (e.getKeyCode() == 37) left(1);
				//��
				if (e.getKeyCode() == 39) right(1);
				//��
				if (e.getKeyCode() == 40) down();
			}
		});
	}
}
