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
 * �˵����������������ͼ�������
 * @date 2011-05-15
 * @author kuangcong
 *
 */
public class EditorMenuBar {
	
	PaintPanel  pPanel = PaintPanel.getPaintPanel();//������Ϣ������ĸ����
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
	 * ���췽��
	 */
	public EditorMenuBar(){
		setMenuBar();
		setListener();
	}
	/**
	 * ���Ʋ˵���
	 */
	private void setMenuBar(){
		//���File�˵�
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
		//���edit�˵�
		editM.add(redo);
		editM.addSeparator();
		editM.add(undo);
		menuBar.add(editM);
		//���help�˵�
		helpM.add(help);
		helpM.addSeparator();
		helpM.add(about);
		menuBar.add(helpM);	
		
		//���˵�����ӿ�ݼ�
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
		
		redo.setEnabled(reminder.getIndex() < reminder.getTail());
	}
	
	/**
	 * �Բ˵�������¼�����
	 */
	private void setListener(){
		//�˳�
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
						"����ͼ�α༭��",1,
						new ImageIcon(this.getClass().getResource("about.jpg")));
			}
		});
		
		//�ļ�����
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.saveFile();
			}
		});
		
		//�ļ����Ϊ
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fcTools.saveFileAs();
			}
		});
		//�ļ���
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
		//�½��ļ�
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
		//����redo
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
		//�ع�undo
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
		//help��ʾ����˵��
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(pPanel, helpDoc,
						"ͼ�α༭������˵��",1,
						new ImageIcon(this.getClass().getResource("about.jpg")));
			}
		});
		
		
		
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	private String helpDoc = "���ܣ�ʹ��˵������\n"+
	"1.�������Σ�����������������ͼ�꣬�����ͼ����ĳλ�ò��϶���\n"+
	"2.����Բ��������������Բ��ͼ�꣬�����ͼ����ĳλ�ò��϶���\n"+
	"3.�������Σ�����������������ͼ�꣬������ͼ����ĳ����λ�ú󻭳������Σ�\n"+
	"4.��ֱ�ߣ�����������ֱ��ͼ�꣬�����ͼ����ĳλ�ò��϶���\n"+
	"5.ѡ��ͼ�Σ�����ƶ���������ͼ���ڲ�ĳλ��ʱ����ʮ���Σ���ʱ����ͼ�α�ʾѡ�д�ͼ�Σ�ѡ�е�ͼ�ν�������ʾ��\n"+
	"   �����հ׽�ȡ��ѡ��ͼ�Σ���ϵͳ���ס���һ�α�ѡ�е�ͼ�Σ����ڸı���ɫ��������ϸʱʵʱ�۲���ɫ��������ϸ�ı仯��\n"+
	"6.��ɫ����ͼǰ������������ɫѡ��ͼ��ѡ����ɫ������ѡ��ĳͼ�κ󵥻���������ɫѡ��ͼ�꣬ѡ����ɫ��ͼ����ɫ��ʵʱ�仯��\n"+
	"7.������ϸ����ͼǰ������������ϸѡ���ѡ���ϸ������ѡ��ĳͼ�κ󵥻���������ϸѡ���ѡ���ϸ��ͼ��������ϸ��ʵʱ�仯��\n"+
	"8.�϶�������ƶ���������ĳͼ���ڲ�ĳλ��ʱ����ʮ���Σ���ʱ�������϶���꣬ͼ�λ���������ƶ���\n"+
	"9.ɾ���������ʮ����ʱ˫������ͼ�ν���ɾ����\n"+
	"10.����������˵���Edit���µ�undo�����ʹ��Ctrl-z��ϼ�������������\n"+
	"11.����������˵���Edit���µ�redo�����ʹ��Ctrl-y��ϼ�������������\n"+
	"12.���棺�����ļ�ʱ�ļ���׺��û�����ƣ�\n"+
	"13.�����������Խ��������϶�������λ�ã��Է�����ͼ��\n"+
	"14.����ڣ�֧���������루��Сд���У���������Ĵ��������м���ʾ���ܣ����������Ϊ������\n"+
	"   Ŀǰ֧�ֵ�������:\n"+
	"1) rectangle x y width height  or  rectangle x y width height wid\n"+
	"   ˵�����������Σ�x��yΪ���Ͻ����꣬width��heightΪ�����εĳ��Ϳ�widΪ������ȡ�\n"+
	"2) ellipse x y width height  or  ellipse x y width height wid\n"+
	"   ˵��������Բ��ͬ�������ε����\n"+
	"3) triangle x1 y1 x2 y2 x3 y3  or  triangle x1 y1 x2 y2 x3 y3 wid\n"+
	"   ˵�����������Σ�x1 y1 x2 y2 x3 y3�ֱ�Ϊ������������������꣬widΪ�����Ŀ�ȡ�\n"+
	"4) line x1 y1 x2 y2  or  line x1 y1 x2 y2 wid\n"+
    "   ˵������ֱ�ߣ�x1 y1 x2 y2�ֱ�Ϊֱ�����˵�����꣬widΪ������ȡ�\n"+
	"5) clear   ˵������ջ�����\n"+
	"6) move x y\n"+
	"   ˵�����ƶ�ѡ��ͼ�ε�ָ��λ�ã�x yΪѡ��ͼ��Ҫ�ƶ������������ꡣ\n"+
	"7) delete  ˵����ɾ��ѡ�е�ͼ�Ρ�\n"+
	"8) exit       ˵�����ر�ͼ�α༭����";
	
}
