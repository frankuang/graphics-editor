package commands;

import ui.PaintPanel;

public class DeleteCommand extends Command {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(pPanel.selectedShapes != null){
			pPanel.shapes.remove(pPanel.selectedShapes);
			pPanel.labelState.setText("���޸�");
			pPanel.selectedShapes = null;
			pPanel.getReminder().addToReminder(); // ��ǰ����������shapes��״̬���뱸��¼��ɾ��ʱ��
			pPanel.isSelectedMode = false;
			pPanel.repaint();
		}
	}

}
