package commands;

import shapes.MyLine;
import ui.PaintPanel;

public class LineCommand extends Command {

	private MyLine line = null;
	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	public LineCommand(double x1,double y1,double x2,double y2){
		line = new MyLine(x1, y1, x2, y2);	
	}
	public LineCommand(double x1,double y1,double x2,double y2,float wid){
		line = new MyLine(x1, y1, x2, y2);	
		line.setWid(wid);
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		pPanel.shapes.add(line);
		pPanel.labelState.setText("���޸�");
		pPanel.getReminder().addToReminder();  //��ǰ����������shapes��״̬���뱸��¼������ʱ��
		pPanel.repaint();
	}

}
