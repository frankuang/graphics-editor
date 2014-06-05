package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MyTriangle extends Line2D.Double implements MyShapes {

	private static final long serialVersionUID = -6465778200383354347L;
	private Color color = Color.black;
	private float widthOfPen= 2f;		//画笔宽度
	private Point[] points = new Point[3]; 		//三角形的三个点
	private double centerX = 0.0,
				   centerY = 0.0;
	
	public MyTriangle(Point[] p){
		points = p.clone();	
		centerX = (points[0].x + points[1].x + points[2].x)/3;
		centerY = (points[0].y + points[1].y + points[2].y)/3;
	}
	public MyTriangle(int x1,int y1,int x2,int y2,int x3,int y3){
		points[0] = new Point(x1,y1);
		points[1] = new Point(x2,y2);
		points[2] = new Point(x3,y3);
		centerX = (points[0].x + points[1].x + points[2].x)/3;
		centerY = (points[0].y + points[1].y + points[2].y)/3;	
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(getWid()));  //设置线条粗细
		g2.setColor(getColor());	  			  //设置线条颜色
		g2.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
		g2.drawLine(points[1].x, points[1].y, points[2].x, points[2].y);
		g2.drawLine(points[2].x, points[2].y, points[0].x, points[0].y);
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		points[0].setLocation(points[0].x + x - centerX, points[0].y + y - centerY);
		points[1].setLocation(points[1].x + x - centerX, points[1].y + y - centerY);
		points[2].setLocation(points[2].x + x - centerX, points[2].y + y - centerY);
		centerX = x;
		centerY = y;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		color = c;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setWid(float wid) {
		// TODO Auto-generated method stub
		widthOfPen = wid;
	}

	@Override
	public float getWid() {
		// TODO Auto-generated method stub
		return widthOfPen;
	}

	//重写包含方法
	public boolean contains(Point2D p) {
		double dist = p.distance(centerX, centerY);
		if(dist < 20)   //点到直线的距离小于20个像素时，看作包含
			return true;
		return false;
	}
	//图形被选中时的加亮显示
	@Override
	public void outStanding(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(5f));
		g2.setColor(new Color(0,255,255));
		g2.drawLine(points[0].x, points[0].y, points[1].x, points[1].y);
		g2.drawLine(points[1].x, points[1].y, points[2].x, points[2].y);
		g2.drawLine(points[2].x, points[2].y, points[0].x, points[0].y);
		this.draw(g2);
	}
	
	public MyTriangle clone(){
		return this.clone();
	}

}
