package commands;

import shapes.MyRectangle;
import ui.PaintPanel;

public class RectangleCommand extends Command{

	private MyRectangle rect = null;
	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	public RectangleCommand(double x,double y,double w,double h){
		rect = new MyRectangle(x, y, w, h);	
	}
	
	public RectangleCommand(double x,double y,double w,double h,float wid){
		rect = new MyRectangle(x, y, w, h);	
		rect.setWid(wid);
	}
	

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		pPanel.shapes.add(rect);
		pPanel.labelState.setText("���޸�");
		pPanel.getReminder().addToReminder();  //��ǰ����������shapes��״̬���뱸��¼������ʱ��
		pPanel.repaint();
	}
	
	
	
	
	
}