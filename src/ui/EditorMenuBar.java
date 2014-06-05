package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import tools.FileTools;
/**
 * 菜单栏相关组件，动作和监听设置
 * @date 2011-05-15
 * @author kuangcong
 *
 */
public class EditorMenuBar {
	
	PaintPanel  pPanel = PaintPanel.getPaintPanel();//用于消息弹出框的父组件
	PaintPanel.Reminder reminder = pPanel.getAndInitReminder();
	FileTools fcTools = new FileTools();
	private String saveIcon = "save.gif";
	private String openIcon = "open.gif";
	private String newIcon = "new.gif";
	private String redoIcon = "redo.gif";
	private String undoIcon = "undo.gif";
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileM  = new JMenu("File"),
		  		  editM  = new JMenu("Edit"), 
		  	      helpM  = new JMenu("Help");
	private JMenuItem open = new JMenuItem("Open",new ImageIcon(this.getClass().getResource(openIcon))),
			  		  newfile = new JMenuItem("New",new ImageIcon(this.getClass().getResource(newIcon))),
			  		  save = new JMenuItem("Save",new ImageIcon(this.getClass().getResource(saveIcon))),
			  		  saveAs = new JMenuItem("Save As"),
			  		  exit = new JMenuItem("Exit");
	private JMenuItem redo = new JMenuItem("redo",new ImageIcon(this.getClass().getResource(redoIcon))),
			  		  undo = new JMenuItem("undo",new ImageIcon(this.getClass().getResource(undoIcon)));
	private JMenuItem help = new JMenuItem("Help"),
			  		  about = new JMenuItem("About");
	/**
	 * 构造方法
	 */
	public EditorMenuBar(){
		setMenuBar();
		setListener();
	}
	/**
	 * 绘制菜单栏
	 */
	private void setMenuBar(){
		//添加File菜单
		fileM.add(open);
		fileM.addSeparator();
		fileM.add(newfile);
		fileM.addSeparator();
		fileM.add(save);
		fileM.addSeparator();
		fileM.add(saveAs);
		fileM.addSeparator();
		fileM.add(exit);
		menuBar.add(fileM);
		//添加edit菜单
		editM.add(redo);
		editM.addSeparator();
		editM.add(undo);
		menuBar.add(editM);
		//添加help菜单
		helpM.add(help);
		helpM.addSeparator();
		helpM.add(about);
		menuBar.add(helpM);	
		
		//给菜单项添加快捷键
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
		
		redo.setEnabled(reminder.getIndex() < reminder.getTail());
	}
	
	/**
	 * 对菜单项添加事件监听
	 */
	private void setListener(){
		//退出
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.close();
			}
		});
		//about
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(pPanel, 
						"A Wonderful Graphics Editor!right?\n@Copyright USTC2011",
						"关于图形编辑器",1,
						new ImageIcon(this.getClass().getResource("about.jpg")));
			}
		});
		
		//文件保存
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.saveFile();
			}
		});
		
		//文件另存为
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.saveFileAs();
			}
		});
		//文件打开
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.openFile();
				reminder.clearReminder();
				reminder.addToReminder();
				pPanel.isSelectedMode = false;
				pPanel.repaint();
			}
		});
		//新建文件
		newfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.newFile();
				reminder.clearReminder();
				reminder.addToReminder();
				pPanel.isSelectedMode = false;
				pPanel.repaint();
			}
		});
		//重做redo
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reminder.redo();
				redo.setEnabled(reminder.getIndex() < reminder.getTail() || 
								reminder.getTail() < reminder.getMemorySize());
				pPanel.repaint();
			}
		});
		//回滚undo
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reminder.undo();
				redo.setEnabled(reminder.getIndex() < reminder.getTail() || 
								reminder.getTail() < reminder.getMemorySize());
				pPanel.isSelectedMode = false;
				pPanel.repaint();
			}
		});
		//help显示功能说明
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(pPanel, helpDoc,
						"图形编辑器功能说明",1,
						new ImageIcon(this.getClass().getResource("about.jpg")));
			}
		});
		
		
		
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	private String helpDoc = "功能（使用说明）：\n"+
	"1.画长方形：单击工具栏长方形图标，点击画图区域某位置并拖动；\n"+
	"2.画椭圆：单击工具栏椭圆形图标，点击画图区域某位置并拖动；\n"+
	"3.画三角形：单击工具栏三角形图标，单击画图区域某三个位置后画出三角形；\n"+
	"4.画直线：单击工具栏直线图标，点击画图区域某位置并拖动；\n"+
	"5.选中图形：鼠标移动至画布上图形内部某位置时会变成十字形，此时单击图形表示选中此图形，选中的图形将高亮显示；\n"+
	"   单击空白将取消选中图形，但系统会记住最后一次被选中的图形，便于改变颜色和线条粗细时实时观察颜色和线条粗细的变化；\n"+
	"6.着色：画图前单击工具栏颜色选择图标选择颜色，或者选中某图形后单击工具栏颜色选择图标，选择颜色后图形颜色会实时变化；\n"+
	"7.线条粗细：画图前单击工具栏粗细选择框选择粗细，或者选中某图形后单击工具栏粗细选择框，选择粗细后图形线条粗细会实时变化；\n"+
	"8.拖动：鼠标移动至画布上某图形内部某位置时会变成十字形，此时单击并拖动鼠标，图形会随着鼠标移动；\n"+
	"9.删除：鼠标变成十字形时双击，此图形将被删除；\n"+
	"10.撤销：点击菜单栏Edit项下的undo项或者使用Ctrl-z组合键将撤销操作；\n"+
	"11.重做：点击菜单栏Edit项下的redo项或者使用Ctrl-y组合键将重做操作；\n"+
	"12.保存：保存文件时文件后缀名没有限制；\n"+
	"13.工具栏：可以将工具栏拖动至任意位置，以方便作图；\n"+
	"14.命令窗口：支持命令输入（大小写敏感），对输入的错误命令有简单提示功能，命令参数均为正数，\n"+
	"   目前支持的命令有:\n"+
	"1) rectangle x y width height  or  rectangle x y width height wid\n"+
	"   说明：画长方形，x，y为左上角坐标，width，height为长方形的长和宽，wid为线条宽度。\n"+
	"2) ellipse x y width height  or  ellipse x y width height wid\n"+
	"   说明：画椭圆，同画长方形的命令。\n"+
	"3) triangle x1 y1 x2 y2 x3 y3  or  triangle x1 y1 x2 y2 x3 y3 wid\n"+
	"   说明：画三角形，x1 y1 x2 y2 x3 y3分别为三角形三个顶点的坐标，wid为线条的宽度。\n"+
	"4) line x1 y1 x2 y2  or  line x1 y1 x2 y2 wid\n"+
    "   说明：画直线，x1 y1 x2 y2分别为直线两端点的坐标，wid为线条宽度。\n"+
	"5) clear   说明：清空画布。\n"+
	"6) move x y\n"+
	"   说明：移动选中图形到指定位置，x y为选中图形要移动到的中心坐标。\n"+
	"7) delete  说明：删除选中的图形。\n"+
	"8) exit       说明：关闭图形编辑器。";
	
}
