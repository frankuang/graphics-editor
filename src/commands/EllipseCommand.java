package commands;

import shapes.MyEllipse;
import ui.PaintPanel;

public class EllipseCommand extends Command{

	private MyEllipse ellipse = null;
	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	public EllipseCommand(double x,double y,double w,double h){
		ellipse = new MyEllipse(x, y, w, h);	
	}
	
	public EllipseCommand(double x,double y,double w,double h,float wid){
		ellipse = new MyEllipse(x, y, w, h);	
		ellipse.setWid(wid);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		pPanel.shapes.add(ellipse);
		pPanel.labelState.setText("已修改");
		pPanel.getReminder().addToReminder();  //当前操作结束后shapes的状态存入备忘录（创建时）
		pPanel.repaint();
	}
}
