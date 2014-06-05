package commands;

import shapes.MyTriangle;
import ui.PaintPanel;

public class TriangleCommand extends Command {

	private MyTriangle triangle = null;
	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	public TriangleCommand(int x1,int y1,int x2,int y2,int x3,int y3){
		triangle = new MyTriangle(x1, y1, x2, y2, x3, y3);	
	}
	
	public TriangleCommand(int x1,int y1,int x2,int y2,int x3,int y3,float wid){
		triangle = new MyTriangle(x1, y1, x2, y2, x3, y3);	
		triangle.setWid(wid);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		pPanel.shapes.add(triangle);
		pPanel.labelState.setText("���޸�");
		pPanel.getReminder().addToReminder();  //��ǰ����������shapes��״̬���뱸��¼������ʱ��
		pPanel.repaint();
	}
}
