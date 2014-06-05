package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * 长方形类
 * @author kuangcong
 *
 */
public class MyRectangle extends Rectangle2D.Double implements MyShapes {
	
	private static final long serialVersionUID = 8621149112459675847L;
	private Color color = Color.black;
	private float widthOfPen= 2f;		//默认画笔宽度
//	private double x_start = -1,
//				   y_start = -1;
	
	public MyRectangle(double x,double y,double w,double h){
		super(x,y,w,h);
	}
	
	
	public float getWid() {
		return widthOfPen;
	}

	@Override
	public void setWid(float wid) {
		// TODO Auto-generated method stub
		widthOfPen = wid;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
	public Color getColor(){
		return color;
	}
	
	@Override
	public void move(double xt, double yt) {
		// TODO Auto-generated method stub
		
//		if(x_start == -1 && y_start == -1){
//			x_start = xt;
//			y_start = yt;   
//		}
//		double x_move = xt - x_start,
//			   y_move = yt - y_start;
		setFrame(xt - width/2, yt - height/2, width, height);
		
//		setFrame(x + x_move, y + y_move, width, height);
//		x_start = xt;
//		y_start = yt;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(getWid()));  //设置线条粗细
		g2.setColor(getColor());	  			  //设置线条颜色
		g2.draw(this);	
	}

	@Override
	public void outStanding(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setStroke(new BasicStroke(5f));
		g2.setColor(new Color(0,255,255));
		g2.draw(this);
	}

	public MyRectangle clone(){
		
		
		return this.clone();
	}

}
