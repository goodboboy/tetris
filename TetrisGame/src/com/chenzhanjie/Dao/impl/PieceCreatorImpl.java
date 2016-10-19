package com.chenzhanjie.Dao.impl;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.chenzhanjie.Dao.IPieceCreator;
import com.chenzhanjie.Dao.Piece;
import com.chenzhanjie.Dao.Piece0;
import com.chenzhanjie.Dao.Piece1;
import com.chenzhanjie.Dao.Piece2;
import com.chenzhanjie.Dao.Piece3;
import com.chenzhanjie.Dao.Piece4;
import com.chenzhanjie.Dao.Piece5;
import com.chenzhanjie.Dao.Piece6;
import com.chenzhanjie.Util.ImageUtil;
/**
 * 在PieceCreator接口中提供一个createPiece方法，并在PieceCreatorImpl中提供实现，该方法用于创建Piece对象。
 * 小方块图片要随机，大方块形状要随机
 * 
 *
 */
public class PieceCreatorImpl implements IPieceCreator {
	private Map<Integer, Image> images = new HashMap<Integer, Image>();
	
		@Override
	public Piece createPiece(int x, int y) {
		System.out.println("createPiece");	
		//随机得到一张方块图片
		Random r= new Random();
		Image image =getImage(r.nextInt(ImageUtil.COLOR_SIZE));
		Piece piece = initPiece(image);
		//设置大方块的初始位置x,y
		piece.setSquaresXLocation(x);
		piece.setSquaresYLocation(y);
		return piece;
	}
	//从map中得到图片对象，如果map中没有存在图片对象, 则创建	
	private Image getImage(int key) {
		if (this.images.get(key) == null) {
			Image s = ImageUtil.getImage("images/square" + key + ".jpg");
			this.images.put(key, s);
		}
		return this.images.get(key);
	}
	//初始化一个Piece对象
	private Piece initPiece(Image image) {
		System.out.println("initPiece");
		Piece piece =null;
		Random r= new Random();
		int pieceType =r.nextInt(ImageUtil.SQUARE_SIZE);
		//初始化Piece，随机创建各个大方块
		if (pieceType == 0) {
			piece = new Piece0(image);
		} else if (pieceType == 1) {
		    piece = new Piece1(image);
		} else if(pieceType ==2){
			piece = new Piece2(image);
		} else if (pieceType ==3){
			piece =new Piece3(image);
		} else if(pieceType ==4){
			piece =new Piece4(image);
		} else if(pieceType ==5){
			piece =new Piece5(image);
		}else if(pieceType ==6){
			piece =new Piece6(image);
		}
		return piece;
	}

}
