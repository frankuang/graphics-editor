package commands;

import ui.PaintPanel;

public class MoveCommand extends Command {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	
	public MoveCommand(double x,double y){
		if(pPanel.selectedShapes != null)
			pPanel.selectedShapes.move(x, y);
	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(pPanel.selectedShapes != null){
			pPanel.labelState.setText("���޸�");
			pPanel.getReminder().addToReminder();  //��ǰ����������shapes��״̬���뱸��¼���ƶ�ʱ��
			pPanel.repaint();
		}
	}

}
