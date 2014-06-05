package commands;

import ui.PaintPanel;

public class DeleteCommand extends Command {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(pPanel.selectedShapes != null){
			pPanel.shapes.remove(pPanel.selectedShapes);
			pPanel.labelState.setText("已修改");
			pPanel.selectedShapes = null;
			pPanel.getReminder().addToReminder(); // 当前操作结束后shapes的状态存入备忘录（删除时）
			pPanel.isSelectedMode = false;
			pPanel.repaint();
		}
	}

}
