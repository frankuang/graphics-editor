package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
/**
 * 椭圆类
 * @author kuangcong
 *
 */
public class MyEllipse extends Ellipse2D.Double implements MyShapes{

	private static final long serialVersionUID = -5506867480201618444L;
	private Color color = Color.black;
	private float widthOfPen= 2f;
	
	//constructor
	public MyEllipse(double x,double y,double w,double h){
		super(x,y,w,h);
	}

	@Override
	public void move(double x, double y) {
		// width,height是父类的成员
		setFrame(x - width/2, y - height/2, width, height);
	}
	

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
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

	@Override
	public void outStanding(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(5f));
		g2.setColor(new Color(0,255,255));
		g2.draw(this);
		this.draw(g2);
	}
	
	public MyEllipse clone(){
		
		return this.clone();
	}

}
