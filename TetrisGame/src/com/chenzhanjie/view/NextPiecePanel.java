package com.chenzhanjie.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Util.ImageUtil;
/**
 * 用于显示下一个方块
 * 
 *
 */
public class NextPiecePanel extends JPanel {
	private MainFrame mainFrame;
	
	public NextPiecePanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(null);
		this.setBackground(Color.gray);
	}

	
	@Override
	public void paint(Graphics g) {
		//画下一个的大方块
		Piece nextPiece = this.mainFrame.getNextPiece();
		System.out.println("//画下一个的大方块1");
		ImageUtil.paintPiece(g, nextPiece);
		System.out.println("//画下一个的大方块2");
		System.out.println(nextPiece);
	}

}
