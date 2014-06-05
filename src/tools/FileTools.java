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
 * 负责处理文件打开，保存以及命令的解释执行
 * @author kuangcong
 *
 */
public class FileTools {

	private PaintPanel pPanel = PaintPanel.getPaintPanel();
	private JFileChooser fileChooser=new JFileChooser();
	private String iconPath = "e07.gif";
	ArrayList<MyShapes> tmp = null;		//对象反序列化时的临时数组
	
	
	//文件保存
	public void saveFile(){
		//从标题栏获取文件名
		File file=new File(pPanel.fileName);
		if(!file.exists())	//若文件不存在则执行saveFileAs()
			saveFileAs();
		else{				//若文件存在
			//建立指定文件的输出流
			try {
				//建立对象输出流
				ObjectOutputStream objOut=new ObjectOutputStream(new FileOutputStream(file));
				//把对象写入文件
				objOut.writeObject(pPanel.shapes);
				objOut.flush();
				objOut.close();
				//修改状态栏
				pPanel.labelState.setText("未修改");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"写入文件失败",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	//文件另存为
	public void saveFileAs(){
		int option=fileChooser.showSaveDialog(null);
		if(option==JFileChooser.APPROVE_OPTION){
			File file=fileChooser.getSelectedFile();
			try {
				pPanel.fileName = file.getPath();
				file.createNewFile();
				saveFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"无法建立新文件",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		}
	}
	//关闭
	public void close(){
		//是否已保存文件
		if(pPanel.labelState.getText().equals("未修改"))
			System.exit(0);
		else{
			int option=JOptionPane.showConfirmDialog(pPanel, "文件已修改，是否保存？",
					"图形编辑器",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
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
	//打开文件
	public void openFile(){
		//如果文件未被修改，则可以直接执行文件打开操作
		if(pPanel.labelState.getText().equals("未修改")){	
			open();		
		}else{  //如果文件已经被修改
			int option2=JOptionPane.showConfirmDialog(pPanel, "文件已修改，是否保存？",
					"图形编辑器",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
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
		// 显示文件选取对话框
		int option = fileChooser.showOpenDialog(null);
		// 如果用户点击”确认“按钮
		if (option == JFileChooser.APPROVE_OPTION) {
			// 打开选取的文件
			try {
				// 创建文件输入流，读取对象输入流
				ObjectInputStream objIn = new ObjectInputStream(
						new FileInputStream(fileChooser.getSelectedFile()));
				pPanel.shapes.clear(); // 清空当前的图形数组
				tmp = (ArrayList<MyShapes>) objIn.readObject();
				pPanel.shapes.addAll(tmp);
				pPanel.labelState.setText("未修改"); // 修改状态栏

				objIn.close();

			} catch (IOException e) {
				// 打印出错信息
				JOptionPane.showMessageDialog(null, e.toString(), "打开文件失败",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString(), "打开文件失败",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

		}
	}
	//新建文件
	public void newFile(){
		//如果文件未被修改，则可以直接执行新建文件操作
		if(pPanel.labelState.getText().equals("未修改")){	
			pPanel.shapes.clear();	//清空图形列表
			pPanel.fileName = "";	//清空文件名
		}else{  //如果文件已经被修改
			int option2=JOptionPane.showConfirmDialog(pPanel, "文件已修改，是否保存？",
					"图形编辑器",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,
					new ImageIcon(this.getClass().getResource(iconPath)));
			switch(option2){
			case JOptionPane.YES_OPTION:
				saveFile();
				break;
			case JOptionPane.NO_OPTION:
				pPanel.shapes.clear();	//清空图形列表
				pPanel.fileName = "";	//清空文件名
				pPanel.labelState.setText("未修改");
			}
		}
	}
	
	
	
}
