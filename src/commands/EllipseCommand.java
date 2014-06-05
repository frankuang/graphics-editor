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
		pPanel.labelState.setText("���޸�");
		pPanel.getReminder().addToReminder();  //��ǰ����������shapes��״̬���뱸��¼������ʱ��
		pPanel.repaint();
	}
}
