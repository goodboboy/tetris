package com.chenzhanjie.Util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Dao.Square;
/**
 * ����һ����Piece�Ĺ��߷������÷������Խ������á�
 * 
 *
 */
public class ImageUtil {
	//С������
	public final static int COLOR_SIZE=7;
	//��������
	public final static int SQUARE_SIZE=7;
	
    
	//��ȡͼƬ
	public static Image getImage(String string) {
		Image i=null;
		try {
			File a= new File(string);
			 i=ImageIO.read(a);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i;
	}
	//�ڽ����ϻ�һ��Piece����
	public static void paintPiece(Graphics g, Piece piece) {
		System.out.println("//hi");
		if (piece == null) return;
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//�õ�����С����󣬽���ЩС���黭��������
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
			System.out.println(s.getImage()+""+s.getBeginX()+s.getBeginY());
		}
	}

}
