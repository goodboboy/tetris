package com.chenzhanjie.Dao;

import java.awt.Image;
/**
 * С���������Ҫ�����������ԣ��ֱ��Ǹ÷����ͼƬ����ʼ������Ϳ�ʼ�����ꡣ
 * 
 *
 */
public class Square {
	
	//�����ͼƬ
	private Image image;
	//��ʼ������
	private int beginX;
	//��ʼ������
	private int beginY;
	
	public boolean equals(Square a) {
		if(beginX==a.beginX&&beginY==a.beginY){
		return true;
		}
		else{
			return false;
		}
	}
	

	public Square(Image image, int beginX, int beginY) {
		this.image = image;
		this.beginX = beginX;
		this.beginY = beginY;
	}
	
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getBeginX() {
		return beginX;
	}
	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}
	public int getBeginY() {
		return beginY;
	}
	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

}
