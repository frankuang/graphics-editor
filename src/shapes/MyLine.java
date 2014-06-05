package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * 直线类
 * @author kuangcong
 *
 */
public class MyLine extends Line2D.Double implements MyShapes {

	private static final long serialVersionUID = -7901042642615016765L;
	private Color color = Color.black;
	private float widthOfPen= 2f;

	public MyLine(double x1,double y1,double x2,double y2){
		super(x1,y1,x2,y2);
	}
	
	
	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		double dx = (x1 - x2)/2,
			   dy = (y1 - y2)/2;
		
		setLine(x + dx, y + dy, x - dx, y - dy); //平移	
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


	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(getWid()));  //设置线条粗细
		g2.setColor(getColor());	  //设置线条颜色
		g2.draw(this);	
	}
	//重写Line2D的包含方法
	public boolean contains(Point2D p) {
		if(ptSegDist(p) < 5)   //点到直线的距离小于5个像素时，看作包含
			return true;
		return false;
	}


	@Override
	public void outStanding(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(5f));
		g2.setColor(new Color(0,255,255));
		g2.draw(this);
		this.draw(g2);
	}
	
	public MyLine clone(){
		
		return this.clone();
	}

}
