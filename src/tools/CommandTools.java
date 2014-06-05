package tools;

import ui.PaintPanel;
import commands.*;

/**
 * 处理命令的工具类
 * @author kuangcong
 *
 */
public class CommandTools {

	private Command cmd = null;		//指向具体的命令对象
	//支持的命令集合
	private String[] cmds = {"rectangle","ellipse","triangle","line","clear", 
							 "exit","delete","move","rotate","magnify","shrink"};		
	private String errorMsg = null;	//错误信息
	
	public Command getCmd() {
		return cmd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 检查命令格式；如果格式正确则将命令打包成一个命令对象
	 * 这也是一个工厂方法
	 * @param command
	 * @return
	 */
	public boolean check(String command){
		String[] cmdForm = command.split("[ ]+"); //把命令字符串按空格（一个或多个）分开
		int cmdLen = cmdForm.length;
		//画长方形的命令
		if(cmdForm[0].equals(cmds[0])){
			//检查命令参数个数和格式是否正确
			if(cmdLen < 5){								
				errorMsg = "参数不够！";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "参数过多！";
				return false;
			}
			//检查数字格式
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //如果不是数字
					errorMsg = "错误：命令参数必须是正数！";
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
					errorMsg = "画笔粗细为1-25";
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
		//画椭圆的命令
		else if(cmdForm[0].equals(cmds[1])){
			//检查命令参数个数和格式是否正确
			if(cmdLen < 5){								
				errorMsg = "参数不够！";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "参数过多！";
				return false;
			}
			//检查数字格式
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //如果不是数字
					errorMsg = "错误：命令参数必须是正数！";
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
					errorMsg = "画笔粗细为1-25";
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
		//画三角形的命令
		else if(cmdForm[0].equals(cmds[2])){
			//检查命令参数个数和格式是否正确
			if(cmdLen < 7){								
				errorMsg = "参数不够！";
				return false;
			}
			if(cmdLen > 8){
				errorMsg = "参数过多！";
				return false;
			}
			//检查数字格式
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //如果不是数字
					errorMsg = "错误：命令参数必须是正数！";
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
					errorMsg = "画笔粗细为1-25";
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
		//画直线的命令
		else if(cmdForm[0].equals(cmds[3])){
			//检查命令参数个数和格式是否正确
			if(cmdLen < 5){								
				errorMsg = "参数不够！";
				return false;
			}
			if(cmdLen > 6){
				errorMsg = "参数过多！";
				return false;
			}
			//检查数字格式
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //如果不是数字
					errorMsg = "错误：命令参数必须是正数！";
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
					errorMsg = "画笔粗细为1-25";
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
		//清除命令
		else if(cmdForm[0].equals(cmds[4])){	//clear全部清除命令
			if(cmdLen > 1){
				errorMsg = "参数过多";
				return false;
			}		
			cmd = new ClearCommand();	
			return true;	
		}
		//exit退出命令
		else if(cmdForm[0].equals(cmds[5])){	
			if(cmdLen > 1){
				errorMsg = "参数过多";
				return false;
			}		
			cmd = new ExitCommand();	
			return true;	
		}
		//delete删除命令
		else if(cmdForm[0].equals(cmds[6])){	//delete删除命令
			if(cmdLen > 1){
				errorMsg = "参数过多";
				return false;
			}		
			cmd = new DeleteCommand();	
			return true;	
		}
		//move移动命令
		else if(cmdForm[0].equals(cmds[7])){
			//检查命令参数个数和格式是否正确
			if(cmdLen < 3){								
				errorMsg = "参数少于2个";
				return false;
			}
			if(cmdLen > 3){
				errorMsg = "参数多于2个";
				return false;
			}			
			//检查数字格式
			for(int i = 1; i < cmdLen; i++)  		
				if(!cmdForm[i].matches("[0-9]+[.]?[0-9]+|[0-9]")){  //如果不是数字
					errorMsg = "错误：命令参数必须是正数！";
					return false;
				}
			
			cmd = new MoveCommand(Double.parseDouble(cmdForm[1]), 
								  Double.parseDouble(cmdForm[2]));
			
			return true;
			
		}
		
		
		
		
		
		
		
		errorMsg = "无此命令！";
		return false;
	}
	
	
}
