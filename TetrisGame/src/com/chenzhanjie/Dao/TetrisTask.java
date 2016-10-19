package com.chenzhanjie.Dao;

import java.util.TimerTask;

import com.chenzhanjie.view.MainFrame;
/**
 * �½�һ��TetrisTask�ദ��ʱ�������񣬸���̳���TimerTask��ΪTetrisTask�ṩһ��������������дrun������
 * ��ʱ�����м���ɼ�������������ǰ��Ϸ�ļ���Խ�ߣ����ʾ�󷽿���½��ٶ�Խ��.
 * ���鲻������Զ�½������������ײ�����ǰ�������ϰ���ʱ�򣬾���Ҫֹͣ�½�������½���.
 * 
 *
 */
public class TetrisTask extends TimerTask {
	//���������
	private MainFrame mainFrame;
	public TetrisTask(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {

		//�õ���ǰ�����˶��Ĵ󷽿�
		Piece currentPiece = this.mainFrame.getCurrentPiece();
		if(!mainFrame.isButtom()==true&&!mainFrame.isBlock()==true){
		//����Piece��setSquaresYLocation����
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
