package com.chenzhanjie.Dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
/**
 * 田字方块
 * 
 *
 */
public class Piece0 extends Piece {
	public Piece0(Image image) {
		System.out.println("Piece0");
		//创建各个小方块，一个集合为一种变化
		List<Square> squares = new ArrayList<Square>();
		squares.add(new Square(image, 0, 0));
		System.out.println("++");
		squares.add(new Square(image, 0, SQUARE_BORDER));
		squares.add(new Square(image, SQUARE_BORDER, 0));
		squares.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER));
		//加入到变化中
		List<List<Square>> changes = new ArrayList<List<Square>>();
		changes.add(squares);
		super.setChanges(changes);
		System.out.println("+");
		super.setSquares(squares);
		System.out.println("Piece000");
	}

}
