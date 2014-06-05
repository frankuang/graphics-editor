package commands;

import tools.FileTools;

public class ExitCommand extends Command {

	private FileTools ft = new FileTools();
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ft.close();
	}

}
