package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
/**
 * ͼ�νӿڣ���֤�����л�
 * @version 1.0; 2011-05-15
 * @author kuangcong
 *
 */
public interface MyShapes extends Shape,Serializable{

	void draw(Graphics2D g2);		//���Լ�
	void move(double x,double y);	//�ƶ��Լ�
	void setColor(Color c);		    //����ͼ����ɫ
	Color getColor();            	//ȡ��ͼ�ε���ɫ
	void setWid(float wid);	 		//����������ϸ
	float getWid();
	void outStanding(Graphics2D g2);//ͼ�εļ�����ʾ
	MyShapes clone();
}
