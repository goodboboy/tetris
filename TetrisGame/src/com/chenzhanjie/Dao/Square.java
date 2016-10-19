package com.chenzhanjie.Dao;

import java.awt.Image;
/**
 * 小方块对象需要包括三个属性，分别是该方块的图片、开始横坐标和开始纵坐标。
 * 
 *
 */
public class Square {
	
	//方块的图片
	private Image image;
	//开始横坐标
	private int beginX;
	//开始纵坐标
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
