package commands;

import ui.PaintPanel;

public class ClearCommand extends Command {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		pPanel.shapes.clear();
		pPanel.isSelectedMode = false;
		pPanel.selectedShapes = null;
		pPanel.repaint();
	}

}
