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
			pPanel.labelState.setText("已修改");
			pPanel.getReminder().addToReminder();  //当前操作结束后shapes的状态存入备忘录（移动时）
			pPanel.repaint();
		}
	}

}
