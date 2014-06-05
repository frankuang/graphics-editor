package tools;
import shapes.*;
import ui.PaintPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * �������ļ��򿪣������Լ�����Ľ���ִ��
 * @author kuangcong
 *
 */
public class FileTools {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	private JFileChooser fileChooser=new JFileChooser();
	private String iconPath = "e07.gif";
	ArrayList<MyShapes> tmp = null;		//�������л�ʱ����ʱ����
	
	
	//�ļ�����
	public void saveFile(){
		//�ӱ�������ȡ�ļ���
		File file=new File(pPanel.fileName);
		if(!file.exists())	//���ļ���������ִ��saveFileAs()
			saveFileAs();
		else{				//���ļ�����
			//����ָ���ļ��������
			try {
				//�������������
				ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(file));
				//�Ѷ���д���ļ�
				objOut.writeObject(pPanel.shapes);
				objOut.flush();
				objOut.close();
				//�޸�״̬��
				pPanel.labelState.setText("δ�޸�");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"д���ļ�ʧ��",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	//�ļ����Ϊ
	public void saveFileAs(){
		int option=fileChooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile();
			try {
				pPanel.fileName = file.getPath();
				file.createNewFile();
				saveFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"�޷��������ļ�",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		}
	}
	//�ر�
	public void close(){
		//�Ƿ��ѱ����ļ�
		if(pPanel.labelState.getText().equals("δ�޸�"))
			System.exit(0);
		else{
			int option=JOptionPane.showConfirmDialog(pPanel, "�ļ����޸ģ��Ƿ񱣴棿",
					"ͼ�α༭��",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
					new ImageIcon(this.getClass().getResource(iconPath)));
			switch(option){
			case JOptionPane.YES_OPTION:
				saveFile();
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
			}
		}
		System.exit(0);
	}
	//���ļ�
	public void openFile(){
		//����ļ�δ���޸ģ������ֱ��ִ���ļ��򿪲���
		if(pPanel.labelState.getText().equals("δ�޸�")){	
			open();		
		}else{  //����ļ��Ѿ����޸�
			int option2=JOptionPane.showConfirmDialog(pPanel, "�ļ����޸ģ��Ƿ񱣴棿",
					"ͼ�α༭��",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
					new ImageIcon(this.getClass().getResource(iconPath)));
			switch(option2){
			case JOptionPane.YES_OPTION:
				saveFile();
				break;
			case JOptionPane.NO_OPTION:
				open();
			}
		}
	}
	
	//open
	public void open() {
		// ��ʾ�ļ�ѡȡ�Ի���
		int option = fileChooser.showOpenDialog(null);
		// ����û������ȷ�ϡ���ť
		if (option == JFileChooser.APPROVE_OPTION) {
			// ��ѡȡ���ļ�
			try {
				// �����ļ�����������ȡ����������
				ObjectInputStream objIn = new ObjectInputStream(
						new FileInputStream(fileChooser.getSelectedFile()));
				pPanel.shapes.clear(); // ��յ�ǰ��ͼ������
				tmp = (ArrayList<MyShapes>) objIn.readObject();
				pPanel.shapes.addAll(tmp);
				pPanel.labelState.setText("δ�޸�"); // �޸�״̬��

				objIn.close();

			} catch (IOException e) {
				// ��ӡ������Ϣ
				JOptionPane.showMessageDialog(null, e.toString(), "���ļ�ʧ��",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "���ļ�ʧ��",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}
	}
	//�½��ļ�
	public void newFile(){
		//����ļ�δ���޸ģ������ֱ��ִ���½��ļ�����
		if(pPanel.labelState.getText().equals("δ�޸�")){	
			pPanel.shapes.clear();	//���ͼ���б�
			pPanel.fileName = "";	//����ļ���
		}else{  //����ļ��Ѿ����޸�
			int option2=JOptionPane.showConfirmDialog(pPanel, "�ļ����޸ģ��Ƿ񱣴棿",
					"ͼ�α༭��",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
					new ImageIcon(this.getClass().getResource(iconPath)));
			switch(option2){
			case JOptionPane.YES_OPTION:
				saveFile();
				break;
			case JOptionPane.NO_OPTION:
				pPanel.shapes.clear();	//���ͼ���б�
				pPanel.fileName = "";	//����ļ���
				pPanel.labelState.setText("δ�޸�");
			}
		}
	}
	
	
	
}
