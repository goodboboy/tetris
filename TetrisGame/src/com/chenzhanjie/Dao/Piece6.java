package com.chenzhanjie.Dao;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * 反Z字方块
 * 
 *
 */
public class Piece6 extends Piece {
	public Piece6(Image image) {
		System.out.println("Piece0");
		//第一种变化
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, 0, 0));
		System.out.println("++");
		squares0.add(new Square(image, SQUARE_BORDER, 0));
		squares0.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER));
		squares0.add(new Square(image, SQUARE_BORDER*2, SQUARE_BORDER));
		//第二种变化
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, 0,SQUARE_BORDER*2));
		squares1.add(new Square(image, 0, SQUARE_BORDER));
		squares1.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER));
		squares1.add(new Square(image, SQUARE_BORDER, 0));
		
		List<List<Square>> changes = new ArrayList<List<Square>>();
		changes.add(squares0);
		changes.add(squares1);
		super.setChanges(changes);
		System.out.println("+");

		//随机获得变化
		super.setSquares(getDefault());
		System.out.println("Piece000");
	}

}
