package com.chenzhanjie.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Util.ImageUtil;
/**
 * ������ʾ��һ������
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
		//����һ���Ĵ󷽿�
		Piece nextPiece = this.mainFrame.getNextPiece();
		System.out.println("//����һ���Ĵ󷽿�1");
		ImageUtil.paintPiece(g, nextPiece);
		System.out.println("//����һ���Ĵ󷽿�2");
		System.out.println(nextPiece);
	}

}
