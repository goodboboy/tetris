package com.chenzhanjie.Dao;

import java.util.TimerTask;

import com.chenzhanjie.view.MainFrame;
/**
 * 新建一个TetrisTask类处理时间间隔任务，该类继承于TimerTask，为TetrisTask提供一个构造器，并重写run方法。
 * 定时器运行间隔由级别决定，如果当前游戏的级别越高，则表示大方块的下降速度越快.
 * 方块不可能永远下降，当到达界面底部或者前面遇到障碍的时候，就需要停止下降（完成下降）.
 * 
 *
 */
public class TetrisTask extends TimerTask {
	//主界面对象
	private MainFrame mainFrame;
	public TetrisTask(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {

		//得到当前正在运动的大方块
		Piece currentPiece = this.mainFrame.getCurrentPiece();
		if(!mainFrame.isButtom()==true&&!mainFrame.isBlock()==true){
		//调用Piece的setSquaresYLocation方法
		currentPiece.setSquaresYLocation(Piece.SQUARE_BORDER);
		this.mainFrame.getGamepanel().repaint();

		}
		else{
			if(mainFrame.isLost()){
				mainFrame.pause();
			}
			mainFrame.appendToSquares();
			mainFrame.finishDown();
			mainFrame.cleanRows();
			
			this.mainFrame.getNextpanel().repaint();
			
		}
		

	}

}
