package com.chenzhanjie.Dao;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
/**
 * ��L�ַ���
 *
 *
 */
public class Piece4 extends Piece {
	public Piece4(Image image) {
		System.out.println("Piece0");
		//��һ�ֱ仯
		List<Square> squares0 = new ArrayList<Square>();
		squares0.add(new Square(image, 0, 0));
		System.out.println("++");
		squares0.add(new Square(image,SQUARE_BORDER, 0));
		squares0.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER));
		squares0.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER*2));
		//�ڶ��ֱ仯
		List<Square> squares1 = new ArrayList<Square>();
		squares1.add(new Square(image, 0, SQUARE_BORDER));
		squares1.add(new Square(image, SQUARE_BORDER, SQUARE_BORDER));
		squares1.add(new Square(image, SQUARE_BORDER*2, SQUARE_BORDER));
		squares1.add(new Square(image, SQUARE_BORDER*2, 0));
		//�����ֱ仯
		List<Square> squares2 = new ArrayList<Square>();
		squares2.add(new Square(image, 0, 0));
		squares2.add(new Square(image, 0, SQUARE_BORDER));
		squares2.add(new Square(image, 0,SQUARE_BORDER*2));
		squares2.add(new Square(image, SQUARE_BORDER,SQUARE_BORDER*2));
		//�����ֱ仯
		List<Square> squares3 = new ArrayList<Square>();
		squares3.add(new Square(image, 0, 0));
		squares3.add(new Square(image, 0, SQUARE_BORDER));
		squares3.add(new Square(image, SQUARE_BORDER, 0));
		squares3.add(new Square(image, SQUARE_BORDER*2, 0));
		
		List<List<Square>> changes = new ArrayList<List<Square>>();
		changes.add(squares0);
		changes.add(squares1);
		changes.add(squares2);
		changes.add(squares3);
		super.setChanges(changes);
		System.out.println("+");
		//�����ñ仯
		super.setSquares(getDefault());
		System.out.println("Piece000");
	}

}
