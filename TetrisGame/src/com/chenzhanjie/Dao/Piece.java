package com.chenzhanjie.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 大方块对象包括多个小方块，而这些小方块的不同位置都使得大方块产生不
 * 同的变化，每一个大方块都有多种变化。新建一个Piece对象来表示一个大方块
 * 
 *
 */
public class Piece {
	    //该大方块所包含的小方块
		private List<Square> squares;
		//该大方块的变化
		protected List<List<Square>> changes = new ArrayList<List<Square>>();
		//当前变化的索引（在changes集合中的索引）
		protected int currentIndex;
		//图片每个小块的边长
		public final static int SQUARE_BORDER = 16;

		
		public List<List<Square>> getChanges() {
			return changes;
		}

		public void setChanges(List<List<Square>> changes) {
			this.changes = changes;
		}

		public void setSquares(List<Square> squares2) {
			squares=squares2;
			
		}
		
        public List<Square> getSquares() {
			
			return squares;
		}
        
		public List<Square> getDefault(){
			//从changes集合中随机得到其中一种变化
			Random r=new Random();
			int defaultChange = r.nextInt(changes.size());
			//设置当前变化的索引
			this.currentIndex = defaultChange;
			return changes.get(defaultChange);
		}
		
		//让Piece对象中的所有Square对象的x座标都加上参数x
		public void setSquaresXLocation(int x) {
			for (int i = 0; i < this.changes.size(); i++) {
				List<Square> change = this.changes.get(i);
				for (int j = 0; j < change.size(); j++) {
					Square s = change.get(j);
					s.setBeginX(s.getBeginX() + x);
				}
			}
		}
		
		//让Piece对象中的所有Square对象的y坐标都加上参数y
		public void setSquaresYLocation(int y){
			for (int i = 0; i < this.changes.size(); i++) {
				List<Square> change=this.changes.get(i);
				for (int j = 0; j < change.size(); j++) {
					Square s=change.get(j);
					s.setBeginY(s.getBeginY()+y);	
				}
			}
		}
		//变化方块
		public void change(){
			if (this.changes.size() == 0) return;
			this.currentIndex += 1;
			//如果当前变化超过changes集合的大小, 则从0开始变化
			if (this.currentIndex >= this.changes.size()) this.currentIndex = 0; 
			this.squares = this.changes.get(this.currentIndex);
		}
		//得到当前变化中x座标的最小值
		public int getMinXLocation() {
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < this.squares.size(); i++) {
				Square s = this.squares.get(i);
				if (s.getBeginX() < result) 
					result = s.getBeginX();
			}
			return result;
		}
		//得到当前变化中x座标最大的值
		public int getMaxXLocation() {
			int result = 0;
			for (int i = 0; i < this.squares.size(); i++) {
				Square s = this.squares.get(i);
				if (s.getBeginX() > result) 
					result = s.getBeginX();
			}
			return result + SQUARE_BORDER;
		}
		//得到当前变化中Y座标最大值
		public int getMaxYLocation() {
			int result = 0;
			for (int i = 0; i < this.squares.size(); i++) {
				Square s = this.squares.get(i);
				if (s.getBeginY() > result) result = s.getBeginY();
			}
			return result + SQUARE_BORDER;
		}

}
