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
 * 加入一个画Piece的工具方法，该方法可以进行重用。
 * 
 *
 */
public class ImageUtil {
	//小块总量
	public final static int COLOR_SIZE=7;
	//方块总量
	public final static int SQUARE_SIZE=7;
	
    
	//读取图片
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
	//在界面上画一个Piece对象
	public static void paintPiece(Graphics g, Piece piece) {
		System.out.println("//hi");
		if (piece == null) return;
		for (int i = 0; i < piece.getSquares().size(); i++) {
			Square s = piece.getSquares().get(i);
			//得到各个小方块后，将这些小方块画到界面中
			g.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);
			System.out.println(s.getImage()+""+s.getBeginX()+s.getBeginY());
		}
	}

}
