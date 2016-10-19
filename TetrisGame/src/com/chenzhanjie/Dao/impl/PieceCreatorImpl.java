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
 * ��PieceCreator�ӿ����ṩһ��createPiece����������PieceCreatorImpl���ṩʵ�֣��÷������ڴ���Piece����
 * С����ͼƬҪ������󷽿���״Ҫ���
 * 
 *
 */
public class PieceCreatorImpl implements IPieceCreator {
	private Map<Integer, Image> images = new HashMap<Integer, Image>();
	
		@Override
	public Piece createPiece(int x, int y) {
		System.out.println("createPiece");	
		//����õ�һ�ŷ���ͼƬ
		Random r= new Random();
		Image image =getImage(r.nextInt(ImageUtil.COLOR_SIZE));
		Piece piece = initPiece(image);
		//���ô󷽿�ĳ�ʼλ��x,y
		piece.setSquaresXLocation(x);
		piece.setSquaresYLocation(y);
		return piece;
	}
	//��map�еõ�ͼƬ�������map��û�д���ͼƬ����, �򴴽�	
	private Image getImage(int key) {
		if (this.images.get(key) == null) {
			Image s = ImageUtil.getImage("images/square" + key + ".jpg");
			this.images.put(key, s);
		}
		return this.images.get(key);
	}
	//��ʼ��һ��Piece����
	private Piece initPiece(Image image) {
		System.out.println("initPiece");
		Piece piece =null;
		Random r= new Random();
		int pieceType =r.nextInt(ImageUtil.SQUARE_SIZE);
		//��ʼ��Piece��������������󷽿�
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
