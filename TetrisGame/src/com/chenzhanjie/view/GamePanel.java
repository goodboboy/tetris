package com.chenzhanjie.view;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Dao.Square;
import com.chenzhanjie.Util.ImageUtil;

/**
 * 方块堆砌界面使用一个GamePanel来实现
 * GamePanel继承于JPanel。GamePanel需要重写父类的paint方法
 * 
 *
 */
public class GamePanel extends JPanel {
	private MainFrame mainFrame;
	static Image background=null;
	
	static{
	File filebg= new File("images/background.jpg");
	try {
		background=ImageIO.read(filebg);
	} catch (IOException e) {
		e.printStackTrace();
	}
	};
	public GamePanel() {
		
	}

	public GamePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void paint(Graphics g) {
		//画背景
		g.drawImage(this.background, 0, 0, this.getWidth(),this.getHeight() , null);
		System.out.println("//画背景");
		
		//画当前运动的大方块
		Piece currentPiece = this.mainFrame.getCurrentPiece();
		System.out.println("//画当前运动的大方块1");
		ImageUtil.paintPiece(g, currentPiece);
		System.out.println("//画当前运动的大方块2");
		System.out.println(currentPiece);
		//绘画小方块的二维数组
		Square[][] squares = this.mainFrame.getSquares();
		if (squares == null) return;
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				Square s = squares[i][j];
				if (s != null) {
					g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), this);
				}
			}
		}
	}
	
	

}
