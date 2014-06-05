package ui;
import shapes.*;
import tools.*;
import commands.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * 画图面板相关组件，动作和监听设置
 * @version 1.0
 * @author kuangcong
 *
 */
public class PaintPanel extends JPanel{
	
	private static final long serialVersionUID = -5281327052360326087L;

	public static PaintPanel pPanel = null;
	private PaintPanel.Reminder reminder = this.new Reminder();
	private CommandTools cmdtool = new CommandTools();
	private Command cmd = null;
	
	public boolean isPainting = false,   //标记是否是在画图
				   isSelectedMode = false;//标记是否有图形被选中
	public int type = 0;				 //标记图形，1为长方形；2为椭圆；3为直线；4为三角形
	public ArrayList<MyShapes> shapes = new ArrayList<MyShapes>();  //存储所有已经画过的图形
	
	private MyShapes current = null;	 //当前图形
	public  MyShapes selectedShapes = null; //被选中的图形
	private double x_start = 0,			 //记录鼠标点击的初始坐标
				   y_start = 0;
	public Color color = Color.black;	 //绘制图形的颜色,默认为黑色
	public float width = 2f;			 //线条粗细,默认值为2
	public JLabel labelState = new JLabel("未修改"),  //状态栏
	       		  labelControl = new JLabel("Control: "),  //命令输入
	       		  labelPosition = new JLabel("Position:"); //鼠标位置
	public JTextField text = new JTextField(25);		   //命令输入框
	public String command = null;
	public String fileName = "";						   //存储文件名
	
	
	//constructor
	private PaintPanel(){
		setBackground(Color.white); //画板背景色设置为白色
		setLayout(new BorderLayout());
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		southPanel.add(labelControl);
		southPanel.add(text);
		southPanel.add(labelState);
		southPanel.add(labelPosition);
		add(southPanel,BorderLayout.SOUTH);
		
		addMouseListener(new MouseHandler());
		addMouseMotionListener(new MouseMotionHandler());
		commandListenser();
	}
	//单例模式
	public static PaintPanel getPaintPanel(){
		if(pPanel == null)
			pPanel = new PaintPanel();
		return pPanel;
	}
	
	//重写paintComponent方法
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for(MyShapes s : shapes){	//绘制所有的图形
			s.draw(g2);				//方法的多态性
		}
		//重绘被选中的图形，使其突出
		if(isSelectedMode && selectedShapes != null)
			selectedShapes.outStanding(g2);
	
	}
	
	//找到第一个包含此点的图形
	public MyShapes find(Point2D p){
		for(MyShapes r : shapes)
			if(r.contains(p)) return r;
		return null;
	}
	
	//初始化并返回内部类Reminder对象
	public PaintPanel.Reminder getAndInitReminder(){
		reminder.initialReminder();
		return reminder;
	}
	public Reminder getReminder(){
		return reminder;
	}
	
	/**
	 * 根据终点坐标画出相应类型的图形(仅限于以下三种图形)
	 * @param x 终点x坐标
	 * @param y 终点y坐标
	 */
	public void setShapeSize(double x,double y){
		if(type == 1){ //长方形
			if(x > x_start && y > y_start)
			  ((MyRectangle) current).setFrame(x_start, y_start,x-x_start,y-y_start);
			else if(x > x_start && y < y_start)
			  ((MyRectangle) current).setFrame(x_start, y,x-x_start,y_start-y);
			else if(x < x_start && y > y_start)
			  ((MyRectangle) current).setFrame(x, y_start,x_start-x,y-y_start);
			else
			  ((MyRectangle) current).setFrame(x, y,x_start-x,y_start-y);
		}	
		else if(type == 2){ //椭圆
			if(x > x_start && y > y_start)
			  ((MyEllipse) current).setFrame(x_start, y_start,x-x_start,y-y_start);
			else if(x > x_start && y < y_start)
			  ((MyEllipse) current).setFrame(x_start, y,x-x_start,y_start-y);
			else if(x < x_start && y > y_start)
			  ((MyEllipse) current).setFrame(x, y_start,x_start-x,y-y_start);
			else
			  ((MyEllipse) current).setFrame(x, y,x_start-x,y_start-y);
		}
		else if(type == 3) //直线
			  ((MyLine) current).setLine(x_start, y_start,x,y);
		
	}
	/**
	 * 初始化工具栏选择的图形
	 */
	private void initiMyshapes(){
		
		if(type == 1){
			current = new MyRectangle(x_start, y_start, 0, 0);
			current.setColor(color);
			current.setWid(width);
		}
		else if(type == 2){
			current = new MyEllipse(x_start, y_start, 0, 0);
			current.setColor(color);
			current.setWid(width);
		}
		else if(type == 3){
			current = new MyLine(x_start, y_start, 0, 0);
			current.setColor(color);
			current.setWid(width);
		}
		
	}
	
	//比较两个ArrayList中的图形是否一样
	private boolean equalsArray(ArrayList list1,ArrayList list2){
		for(int i = 0; i < list1.size(); i++)
			if(!list1.get(i).equals(list2.get(i)))
				return false;
		
		return true;
	}
	
	
	
	/**
	 * 命令输入框监听器,按Enter键执行命令
	 */
	private void commandListenser(){
		
		text.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {      //如果点击回车键，则执行命令
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					command = text.getText().trim();  //去掉命令首位的空格
					//检查命令
					if(!cmdtool.check(command)){   	  //如果命令有错
						labelState.setText(cmdtool.getErrorMsg());	
					}
					else{   						  //如果命令正确，则执行
						cmd = cmdtool.getCmd();
						cmd.execute();	
					}	
				}	
			}
		});	
	}
	
	
	//实现鼠标点击监听的内部类
	private class MouseHandler extends MouseAdapter{
		private int clickCount = 0;  //鼠标点击次数，用于画三角形
		private Point[] points = new Point[3];  //存储三角形的三个点
		
		public void mousePressed(MouseEvent e){		//鼠标单击事件
			x_start = e.getX();
			y_start = e.getY();	
			if(isPainting && type != 4){   //如果是在画图,且不是在画三角形
				initiMyshapes();   
				shapes.add(current);
			}else if(isPainting && type == 4){	//如果在画三角形
				points[clickCount++] = e.getPoint();
				if(clickCount >= 3){			//点击三次鼠标表示完成三角形的绘制
					current = new MyTriangle(points);
					current.setColor(color);
					current.setWid(width);
					shapes.add(current);
					clickCount = 0;
					isPainting = false;
					reminder.addToReminder();	//记录操作
					repaint();
				}
							
			}
			else{			 //如果是在打酱油
				current = find(e.getPoint());
			}
		}
		
		public void mouseReleased(MouseEvent e){
			if(isPainting && type != 4){
				isPainting = false;			
				reminder.addToReminder();   //当前操作结束后shapes的状态存入备忘录（画图时）
			}
			else if(reminder.getTailFactor() != null ){
				
				//reminder.addToReminder();   //当前操作结束后shapes的状态存入备忘录(拖动时)
			}
		}
		
		public void mouseClicked(MouseEvent e){
			current = find(e.getPoint());
			if(current != null && e.getClickCount() == 2){  //鼠标双击，表示删除
				shapes.remove(current);
				current = null;
				labelState.setText("已修改");
				reminder.addToReminder();   //当前操作结束后shapes的状态存入备忘录（删除时）
				isSelectedMode = false;
				repaint();
			}
			else if(e.getClickCount() == 1){		//如果是鼠标单击
				if(current != null){	//如果单击的是图形表示选中它
					isSelectedMode = true;
					selectedShapes = current;	
				}
				else{		//如果单击空白，表示取消选中
					isSelectedMode = false;
				}
				repaint();
			}
		}
		
		
	}
	//实现鼠标移动和拖动监听的内部类
	private class MouseMotionHandler implements MouseMotionListener{
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX(), y = e.getY();
			labelPosition.setText("Position: [ "+ x +" , "+ y +" ]");
			if(isPainting){	//如果是在画图	
				setShapeSize(x, y);
				labelState.setText("已修改");
				repaint();
			}
			else if(current != null){	//如果是在拖动图形
					current.move(x, y);
					labelState.setText("已修改");
					repaint();	
			}	
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			labelPosition.setText("Position: [ "+e.getX()+" , "+e.getY()+" ]");
			
			if(find(e.getPoint()) == null )
				setCursor(Cursor.getDefaultCursor());
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}	
	}
	
	//记录操作信息的内部类
	public class Reminder{
		private int tail = -1, 	//指示数组的尾巴，因为这是一个循环数组
					index = 0,	//当前重做或者回滚到的位置
					memorySize = 0;  //memory中元素的最大下标
		private int memoryLen = 10;	 //备忘操作的最大长度
		//用于存储操作，以便重做和回滚；memory一直处于变化状态
		private ArrayList<ArrayList<MyShapes>> memory = new ArrayList<ArrayList<MyShapes>>(memoryLen);
		
		//把操作后的结果保存到memory中
		public void addToReminder(){
			memorySize = ++tail;
			memorySize = memorySize >= memoryLen ? memoryLen - 1 :memorySize; 
			tail = tail%memoryLen;	
			if(tail < memory.size())
				memory.remove(tail); //先清空相应位置的内容，否则此位置的内容不会被覆盖
//			ArrayList<MyShapes> temp = new ArrayList<MyShapes>();
//			for(MyShapes ms : shapes)
//				temp.add(ms);
							
			memory.add(tail, (ArrayList<MyShapes>) shapes.clone()); //必须克隆
			//memory.add(temp);
			index = tail;
		}
		//回滚
		public void undo(){
			index = (--index) < 0 ? memorySize : index;
			shapes.clear();
			ArrayList<MyShapes> tmp = (ArrayList<MyShapes>) memory.get(index);
			if(tmp != null)
				shapes = (ArrayList<MyShapes>) tmp.clone();
			else
				shapes.clear();
		}
		//重做
		public void redo(){
			index = (++index) >= tail ? tail : index;
			shapes.clear();
			ArrayList<MyShapes> tmp = (ArrayList<MyShapes>) memory.get(index);
			//System.out.print("\ntmp:"+tmp);
			if(tmp != null)
				shapes = (ArrayList<MyShapes>) tmp.clone();
			else
				shapes.clear();
		}
		
		public void initialReminder(){ //第一个元素初始化为空
			memory.add(++tail,null);
		}
		public ArrayList<MyShapes> getTailFactor(){
			if(tail != -1)
				return memory.get(tail);
			return null;
		}
		public void clearReminder(){
			memory.clear();
			tail = -1;
			index = 0;
			memorySize = 0;
		}
		public int getTail(){
			return tail;
		}
		public int getIndex(){
			return index;
		}
		public int getMemorySize(){
			return memorySize;
		}
		
	}
	
	
	
}
