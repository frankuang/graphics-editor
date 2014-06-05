package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
/**
 * 工具栏相关组件，动作和监听设置
 * @version 1.0;2011-05-15
 * @author kuangcong
 *
 */
public class EditorToolBar {
	
	private String iconPath1 = "rectangle.jpg";
	private String iconPath2 = "ellipse.jpg";
	private String iconPath3 = "triangle.jpg";
	private String iconPath4 = "line.jpg";
	private String iconPath5 = "colorSelector.jpg";
	
	PaintPanel pPanel = PaintPanel.getPaintPanel();
	private String width = "width";
	
	private JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL); //初始为垂直放置
	private JButton rectangle = new JButton(new ImageIcon(this.getClass().getResource(iconPath1))),
					ellipse = new JButton(new ImageIcon(this.getClass().getResource(iconPath2))),
					triangle = new JButton(new ImageIcon(this.getClass().getResource(iconPath3))),
					line = new JButton(new ImageIcon(this.getClass().getResource(iconPath4))),
					colorButton = new JButton(new ImageIcon(this.getClass().getResource(iconPath5)));
	private JComboBox widCombo = new JComboBox();
	
	
	//constructor
	public EditorToolBar(){
		setToolBar();
		setListener();
	}
	/**
	 * 绘制工具栏
	 */
	private void setToolBar(){
		//添加工具提示
		rectangle.setToolTipText("长方形");
		ellipse.setToolTipText("椭圆");
		triangle.setToolTipText("三角形");
		line.setToolTipText("直线");
		colorButton.setToolTipText("颜色选择器");
		
		//组件添加到工具栏
		toolBar.add(rectangle);
		toolBar.addSeparator();
		toolBar.add(ellipse);
		toolBar.addSeparator();
		toolBar.add(triangle);
		toolBar.addSeparator();
		toolBar.add(line);
		toolBar.addSeparator();
		toolBar.add(colorButton);
		
		widCombo.addItem(width);
		widCombo.addItem("1");
		widCombo.addItem("2");
		widCombo.addItem("3");
		widCombo.addItem("4");
		widCombo.addItem("5");
		widCombo.addItem("6");
		widCombo.addItem("7");
		toolBar.addSeparator();
		JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		comboPanel.add(widCombo);
		toolBar.add(comboPanel);
		
		
	}
	/**
	 * 给各组件添加事件监听
	 */
	private void setListener(){
		//画长方形的动作
		rectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 1;
			}
		});
		
		//画椭圆的动作
		ellipse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 2;
			}
		});
		//画三角形的动作
		triangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 4;
			}
		});
		
		//画直线的动作
		line.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 3;
			}
		});
		
		//颜色选择器
		colorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color tmp = JColorChooser.showDialog(toolBar, "选择图形颜色", Color.black);
				pPanel.color = tmp;
				if(pPanel.selectedShapes != null){
					pPanel.selectedShapes.setColor(tmp);
					//pPanel.getReminder().addToReminder(); //记录改变颜色后的状态
					pPanel.repaint();
				}
			}
		});
		
		//线条粗细选择
		widCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String item = (String)widCombo.getSelectedItem();
				if(!item.equals(width) && pPanel.selectedShapes != null){
					pPanel.width = Float.parseFloat(item);
					pPanel.selectedShapes.setWid(pPanel.width);
					//pPanel.getReminder().addToReminder(); //记录改变粗细后的状态
					pPanel.repaint();
				}
			}
		});
		
			
	}
	
	public JToolBar getToolBar(){
		return toolBar;
	}
		
}
