package tools;

import ui.PaintPanel;
import commands.*;

/**
 * ��������Ĺ�����
 * @author kuangcong
 *
 */
public class CommandTools {

	private Command cmd = null;		//ָ�������������
	//֧�ֵ������
	private String[] cmds = {"rectangle","ellipse","triangle","line","clear", 
							 "exit","delete","move","rotate","magnify","shrink"};		
	private String errorMsg = null;	//������Ϣ
	
	public Command getCmd() {
		return cmd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * ��������ʽ�������ʽ��ȷ����������һ���������
	 * ��Ҳ��һ����������
	 * @param command
	 * @return
	 */
	public boolean check(String command){
		String[] cmdForm = command.split("[ ]+"); //�������ַ������ո�һ���������ֿ�
		int cmdLen = cmdForm.length;
		//�������ε�����
		if(cmdForm[0].equals(cmds[0])){
			//���������������͸�ʽ�Ƿ���ȷ
			if(cmdLen < 5){								
				errorMsg = "����������";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "�������࣡";
				return false;
			}
			//������ָ�ʽ
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //�����������
					errorMsg = "�����������������������";
					return false;
				}
			
			if(cmdLen == 5){
				cmd = new RectangleCommand(Double.parseDouble(cmdForm[1]),
										   Double.parseDouble(cmdForm[2]),
									       Double.parseDouble(cmdForm[3]),
									       Double.parseDouble(cmdForm[4]));	
			}
			else if(cmdLen == 6){
				if(Float.parseFloat(cmdForm[5]) > 25){
					errorMsg = "���ʴ�ϸΪ1-25";
					return false;
				}
				cmd = new RectangleCommand(Double.parseDouble(cmdForm[1]),
										   Double.parseDouble(cmdForm[2]),
					                       Double.parseDouble(cmdForm[3]),
					                       Double.parseDouble(cmdForm[4]),
					                       Float.parseFloat(cmdForm[5]));		
			}
			return true;
		}
		//����Բ������
		else if(cmdForm[0].equals(cmds[1])){
			//���������������͸�ʽ�Ƿ���ȷ
			if(cmdLen < 5){								
				errorMsg = "����������";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "�������࣡";
				return false;
			}
			//������ָ�ʽ
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //�����������
					errorMsg = "�����������������������";
					return false;
				}
			
			if(cmdLen == 5){
				cmd = new EllipseCommand(Double.parseDouble(cmdForm[1]),
										 Double.parseDouble(cmdForm[2]),
									     Double.parseDouble(cmdForm[3]),
									     Double.parseDouble(cmdForm[4]));	
			}
			else if(cmdLen == 6){
				if(Float.parseFloat(cmdForm[5]) > 25){
					errorMsg = "���ʴ�ϸΪ1-25";
					return false;
				}
				cmd = new EllipseCommand(Double.parseDouble(cmdForm[1]),
										 Double.parseDouble(cmdForm[2]),
					                     Double.parseDouble(cmdForm[3]),
					                     Double.parseDouble(cmdForm[4]),
					                     Float.parseFloat(cmdForm[5]));		
			}
			return true;	
		}
		//�������ε�����
		else if(cmdForm[0].equals(cmds[2])){
			//���������������͸�ʽ�Ƿ���ȷ
			if(cmdLen < 7){								
				errorMsg = "����������";
				return false;
			}
			if(cmdLen > 8){
				errorMsg = "�������࣡";
				return false;
			}
			//������ָ�ʽ
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //�����������
					errorMsg = "�����������������������";
					return false;
				}
			
			if(cmdLen == 7){
				cmd = new TriangleCommand(Integer.parseInt(cmdForm[1]),
										  Integer.parseInt(cmdForm[2]),
										  Integer.parseInt(cmdForm[3]),
										  Integer.parseInt(cmdForm[4]),
										  Integer.parseInt(cmdForm[5]),
										  Integer.parseInt(cmdForm[6]));		
			}
			else if(cmdLen == 8){
				if(Float.parseFloat(cmdForm[7]) > 25){
					errorMsg = "���ʴ�ϸΪ1-25";
					return false;
				}
				cmd = new TriangleCommand(Integer.parseInt(cmdForm[1]),
						  			      Integer.parseInt(cmdForm[2]),
						  			      Integer.parseInt(cmdForm[3]),
						  			      Integer.parseInt(cmdForm[4]),
						  			      Integer.parseInt(cmdForm[5]),
						  			      Integer.parseInt(cmdForm[6]),
						  			      Float.parseFloat(cmdForm[7]));		
			}
			return true;		
			
		}
		//��ֱ�ߵ�����
		else if(cmdForm[0].equals(cmds[3])){
			//���������������͸�ʽ�Ƿ���ȷ
			if(cmdLen < 5){								
				errorMsg = "����������";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "�������࣡";
				return false;
			}
			//������ָ�ʽ
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //�����������
					errorMsg = "�����������������������";
					return false;
				}
			
			if (cmdLen == 5) {
				cmd = new LineCommand(Double.parseDouble(cmdForm[1]),
									  Double.parseDouble(cmdForm[2]),
									  Double.parseDouble(cmdForm[3]),
									  Double.parseDouble(cmdForm[4]));
			}
			else if(cmdLen == 6){
				if(Float.parseFloat(cmdForm[5]) > 25){
					errorMsg = "���ʴ�ϸΪ1-25";
					return false;
				}
				cmd = new LineCommand(Double.parseDouble(cmdForm[1]),
									  Double.parseDouble(cmdForm[2]),
					                  Double.parseDouble(cmdForm[3]),
					                  Double.parseDouble(cmdForm[4]),
					                  Float.parseFloat(cmdForm[5]));		
			}
			return true;		
		}
		//�������
		else if(cmdForm[0].equals(cmds[4])){	//clearȫ���������
			if(cmdLen > 1){
				errorMsg = "��������";
				return false;
			}		
			cmd = new ClearCommand();	
			return true;	
		}
		//exit�˳�����
		else if(cmdForm[0].equals(cmds[5])){	
			if(cmdLen > 1){
				errorMsg = "��������";
				return false;
			}		
			cmd = new ExitCommand();	
			return true;	
		}
		//deleteɾ������
		else if(cmdForm[0].equals(cmds[6])){	//deleteɾ������
			if(cmdLen > 1){
				errorMsg = "��������";
				return false;
			}		
			cmd = new DeleteCommand();	
			return true;	
		}
		//move�ƶ�����
		else if(cmdForm[0].equals(cmds[7])){
			//���������������͸�ʽ�Ƿ���ȷ
			if(cmdLen < 3){								
				errorMsg = "��������2��";
				return false;
			}
			if(cmdLen > 3){
				errorMsg = "��������2��";
				return false;
			}			
			//������ָ�ʽ
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //�����������
					errorMsg = "�����������������������";
					return false;
				}
			
			cmd = new MoveCommand(Double.parseDouble(cmdForm[1]), 
								  Double.parseDouble(cmdForm[2]));
			
			return true;
			
		}
		
		
		
		
		
		
		
		errorMsg = "�޴����";
		return false;
	}
	
	
}
