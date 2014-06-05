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
 * �������������������ͼ�������
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
	
	private JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL); //��ʼΪ��ֱ����
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
	 * ���ƹ�����
	 */
	private void setToolBar(){
		//��ӹ�����ʾ
		rectangle.setToolTipText("������");
		ellipse.setToolTipText("��Բ");
		triangle.setToolTipText("������");
		line.setToolTipText("ֱ��");
		colorButton.setToolTipText("��ɫѡ����");
		
		//�����ӵ�������
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
	 * �����������¼�����
	 */
	private void setListener(){
		//�������εĶ���
		rectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 1;
			}
		});
		
		//����Բ�Ķ���
		ellipse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 2;
			}
		});
		//�������εĶ���
		triangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 4;
			}
		});
		
		//��ֱ�ߵĶ���
		line.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pPanel.isPainting = true;
				pPanel.type = 3;
			}
		});
		
		//��ɫѡ����
		colorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color tmp = JColorChooser.showDialog(toolBar, "ѡ��ͼ����ɫ", Color.black);
				pPanel.color = tmp;
				if(pPanel.selectedShapes != null){
					pPanel.selectedShapes.setColor(tmp);
					//pPanel.getReminder().addToReminder(); //��¼�ı���ɫ���״̬
					pPanel.repaint();
				}
			}
		});
		
		//������ϸѡ��
		widCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String item = (String)widCombo.getSelectedItem();
				if(!item.equals(width) && pPanel.selectedShapes != null){
					pPanel.width = Float.parseFloat(item);
					pPanel.selectedShapes.setWid(pPanel.width);
					//pPanel.getReminder().addToReminder(); //��¼�ı��ϸ���״̬
					pPanel.repaint();
				}
			}
		});
		
			
	}
	
	public JToolBar getToolBar(){
		return toolBar;
	}
		
}
