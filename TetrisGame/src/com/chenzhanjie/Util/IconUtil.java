package com.chenzhanjie.Util;

import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * ���� ��ͣ ��ʼ �ı���
 * 
 *
 */
public class IconUtil {

	public static final Icon PAUSE_ON = geticon("pause-on");
	public static final Icon PAUSE = geticon("pause");
	public static final Icon RESUME_ON = geticon("resume-on");
	public static final Icon RESUME = geticon("resume");
	public static final Icon START_ON = geticon("start-on");
	public static final Icon START = geticon("start");
    //��ð�ť����ͼƬ
	private static Icon geticon(String a) {
		String filename = "images/button-bg-"+a+".gif";
		Icon icon = new ImageIcon(filename);
		return icon;
	}
	

}
