package ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import tools.FileTools;

public class EditorFrame extends JFrame{

	private static final long serialVersionUID = -783091853298584229L;
	
	private FileTools ft = new FileTools();
	private static final int WIDTH = 700;
	private static final int HEIGHT = 600;
	
	public EditorFrame(){
		setTitle("Í¼ÐÎ±à¼­Æ÷");
		setLocation(300,70);
		setSize(WIDTH,HEIGHT);
		
		EditorPanel ePanel = new EditorPanel();
		add(ePanel);
		
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				ft.close();
			}
		});
	}

}


class EditorPanel extends JPanel{
	
	private static final long serialVersionUID = 4440032875918464551L;
	
	EditorMenuBar menuBar = new EditorMenuBar();
	EditorToolBar toolBar = new EditorToolBar();
	PaintPanel paintPanel = PaintPanel.getPaintPanel();
	
	//constructor
	public EditorPanel(){
		setLayout(new BorderLayout());
		
		add(menuBar.getMenuBar(),BorderLayout.NORTH);
		add(toolBar.getToolBar(),BorderLayout.EAST);
		add(paintPanel,BorderLayout.CENTER);
		
	}
	
}