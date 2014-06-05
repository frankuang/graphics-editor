package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;
/**
 * 图形接口，保证可序列化
 * @version 1.0; 2011-05-15
 * @author kuangcong
 *
 */
public interface MyShapes extends Shape,Serializable{

	void draw(Graphics2D g2);		//画自己
	void move(double x,double y);	//移动自己
	void setColor(Color c);		    //设置图形颜色
	Color getColor();            	//取得图形的颜色
	void setWid(float wid);	 		//设置线条粗细
	float getWid();
	void outStanding(Graphics2D g2);//图形的加亮显示
	MyShapes clone();
}
