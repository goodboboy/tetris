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
 * �����������ʹ��һ��GamePanel��ʵ��
 * GamePanel�̳���JPanel��GamePanel��Ҫ��д�����paint����
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
		//������
		g.drawImage(this.background, 0, 0, this.getWidth(),this.getHeight() , null);
		System.out.println("//������");
		
		//����ǰ�˶��Ĵ󷽿�
		Piece currentPiece = this.mainFrame.getCurrentPiece();
		System.out.println("//����ǰ�˶��Ĵ󷽿�1");
		ImageUtil.paintPiece(g, currentPiece);
		System.out.println("//����ǰ�˶��Ĵ󷽿�2");
		System.out.println(currentPiece);
		//�滭С����Ķ�ά����
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
