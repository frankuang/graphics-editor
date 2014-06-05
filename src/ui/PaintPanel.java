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
 * ��ͼ����������������ͼ�������
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
	
	public boolean isPainting = false,   //����Ƿ����ڻ�ͼ
				   isSelectedMode = false;//����Ƿ���ͼ�α�ѡ��
	public int type = 0;				 //���ͼ�Σ�1Ϊ�����Σ�2Ϊ��Բ��3Ϊֱ�ߣ�4Ϊ������
	public ArrayList<MyShapes> shapes = new ArrayList<MyShapes>();  //�洢�����Ѿ�������ͼ��
	
	private MyShapes current = null;	 //��ǰͼ��
	public  MyShapes selectedShapes = null; //��ѡ�е�ͼ��
	private double x_start = 0,			 //��¼������ĳ�ʼ����
				   y_start = 0;
	public Color color = Color.black;	 //����ͼ�ε���ɫ,Ĭ��Ϊ��ɫ
	public float width = 2f;			 //������ϸ,Ĭ��ֵΪ2
	public JLabel labelState = new JLabel("δ�޸�"),  //״̬��
	       		  labelControl = new JLabel("Control: "),  //��������
	       		  labelPosition = new JLabel("Position:"); //���λ��
	public JTextField text = new JTextField(25);		   //���������
	public String command = null;
	public String fileName = "";						   //�洢�ļ���
	
	
	//constructor
	private PaintPanel(){
		setBackground(Color.white); //���屳��ɫ����Ϊ��ɫ
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
	//����ģʽ
	public static PaintPanel getPaintPanel(){
		if(pPanel == null)
			pPanel = new PaintPanel();
		return pPanel;
	}
	
	//��дpaintComponent����
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for(MyShapes s : shapes){	//�������е�ͼ��
			s.draw(g2);				//�����Ķ�̬��
		}
		//�ػ汻ѡ�е�ͼ�Σ�ʹ��ͻ��
		if(isSelectedMode && selectedShapes != null)
			selectedShapes.outStanding(g2);
	
	}
	
	//�ҵ���һ�������˵��ͼ��
	public MyShapes find(Point2D p){
		for(MyShapes r : shapes)
			if(r.contains(p)) return r;
		return null;
	}
	
	//��ʼ���������ڲ���Reminder����
	public PaintPanel.Reminder getAndInitReminder(){
		reminder.initialReminder();
		return reminder;
	}
	public Reminder getReminder(){
		return reminder;
	}
	
	/**
	 * �����յ����껭����Ӧ���͵�ͼ��(��������������ͼ��)
	 * @param x �յ�x����
	 * @param y �յ�y����
	 */
	public void setShapeSize(double x,double y){
		if(type == 1){ //������
			if(x > x_start && y > y_start)
			  ((MyRectangle) current).setFrame(x_start, y_start,x-x_start,y-y_start);
			else if(x > x_start && y < y_start)
			  ((MyRectangle) current).setFrame(x_start, y,x-x_start,y_start-y);
			else if(x < x_start && y > y_start)
			  ((MyRectangle) current).setFrame(x, y_start,x_start-x,y-y_start);
			else
			  ((MyRectangle) current).setFrame(x, y,x_start-x,y_start-y);
		}	
		else if(type == 2){ //��Բ
			if(x > x_start && y > y_start)
			  ((MyEllipse) current).setFrame(x_start, y_start,x-x_start,y-y_start);
			else if(x > x_start && y < y_start)
			  ((MyEllipse) current).setFrame(x_start, y,x-x_start,y_start-y);
			else if(x < x_start && y > y_start)
			  ((MyEllipse) current).setFrame(x, y_start,x_start-x,y-y_start);
			else
			  ((MyEllipse) current).setFrame(x, y,x_start-x,y_start-y);
		}
		else if(type == 3) //ֱ��
			  ((MyLine) current).setLine(x_start, y_start,x,y);
		
	}
	/**
	 * ��ʼ��������ѡ���ͼ��
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
	
	//�Ƚ�����ArrayList�е�ͼ���Ƿ�һ��
	private boolean equalsArray(ArrayList list1,ArrayList list2){
		for(int i = 0; i < list1.size(); i++)
			if(!list1.get(i).equals(list2.get(i)))
				return false;
		
		return true;
	}
	
	
	
	/**
	 * ��������������,��Enter��ִ������
	 */
	private void commandListenser(){
		
		text.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {      //�������س�������ִ������
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					command = text.getText().trim();  //ȥ��������λ�Ŀո�
					//�������
					if(!cmdtool.check(command)){   	  //��������д�
						labelState.setText(cmdtool.getErrorMsg());	
					}
					else{   						  //���������ȷ����ִ��
						cmd = cmdtool.getCmd();
						cmd.execute();	
					}	
				}	
			}
		});	
	}
	
	
	//ʵ��������������ڲ���
	private class MouseHandler extends MouseAdapter{
		private int clickCount = 0;  //��������������ڻ�������
		private Point[] points = new Point[3];  //�洢�����ε�������
		
		public void mousePressed(MouseEvent e){		//��굥���¼�
			x_start = e.getX();
			y_start = e.getY();	
			if(isPainting && type != 4){   //������ڻ�ͼ,�Ҳ����ڻ�������
				initiMyshapes();   
				shapes.add(current);
			}else if(isPainting && type == 4){	//����ڻ�������
				points[clickCount++] = e.getPoint();
				if(clickCount >= 3){			//�����������ʾ��������εĻ���
					current = new MyTriangle(points);
					current.setColor(color);
					current.setWid(width);
					shapes.add(current);
					clickCount = 0;
					isPainting = false;
					reminder.addToReminder();	//��¼����
					repaint();
				}
							
			}
			else{			 //������ڴ���
				current = find(e.getPoint());
			}
		}
		
		public void mouseReleased(MouseEvent e){
			if(isPainting && type != 4){
				isPainting = false;			
				reminder.addToReminder();   //��ǰ����������shapes��״̬���뱸��¼����ͼʱ��
			}
			else if(reminder.getTailFactor() != null ){
				
				//reminder.addToReminder();   //��ǰ����������shapes��״̬���뱸��¼(�϶�ʱ)
			}
		}
		
		public void mouseClicked(MouseEvent e){
			current = find(e.getPoint());
			if(current != null && e.getClickCount() == 2){  //���˫������ʾɾ��
				shapes.remove(current);
				current = null;
				labelState.setText("���޸�");
				reminder.addToReminder();   //��ǰ����������shapes��״̬���뱸��¼��ɾ��ʱ��
				isSelectedMode = false;
				repaint();
			}
			else if(e.getClickCount() == 1){		//�������굥��
				if(current != null){	//�����������ͼ�α�ʾѡ����
					isSelectedMode = true;
					selectedShapes = current;	
				}
				else{		//��������հף���ʾȡ��ѡ��
					isSelectedMode = false;
				}
				repaint();
			}
		}
		
		
	}
	//ʵ������ƶ����϶��������ڲ���
	private class MouseMotionHandler implements MouseMotionListener{
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX(), y = e.getY();
			labelPosition.setText("Position: [ "+ x +" , "+ y +" ]");
			if(isPainting){	//������ڻ�ͼ	
				setShapeSize(x, y);
				labelState.setText("���޸�");
				repaint();
			}
			else if(current != null){	//��������϶�ͼ��
					current.move(x, y);
					labelState.setText("���޸�");
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
	
	//��¼������Ϣ���ڲ���
	public class Reminder{
		private int tail = -1, 	//ָʾ�����β�ͣ���Ϊ����һ��ѭ������
					index = 0,	//��ǰ�������߻ع�����λ��
					memorySize = 0;  //memory��Ԫ�ص�����±�
		private int memoryLen = 10;	 //������������󳤶�
		//���ڴ洢�������Ա������ͻع���memoryһֱ���ڱ仯״̬
		private ArrayList<ArrayList<MyShapes>> memory = new ArrayList<ArrayList<MyShapes>>(memoryLen);
		
		//�Ѳ�����Ľ�����浽memory��
		public void addToReminder(){
			memorySize = ++tail;
			memorySize = memorySize >= memoryLen ? memoryLen - 1 :memorySize; 
			tail = tail%memoryLen;	
			if(tail < memory.size())
				memory.remove(tail); //�������Ӧλ�õ����ݣ������λ�õ����ݲ��ᱻ����
//			ArrayList<MyShapes> temp = new ArrayList<MyShapes>();
//			for(MyShapes ms : shapes)
//				temp.add(ms);
							
			memory.add(tail, (ArrayList<MyShapes>) shapes.clone()); //�����¡
			//memory.add(temp);
			index = tail;
		}
		//�ع�
		public void undo(){
			index = (--index) < 0 ? memorySize : index;
			shapes.clear();
			ArrayList<MyShapes> tmp = (ArrayList<MyShapes>) memory.get(index);
			if(tmp != null)
				shapes = (ArrayList<MyShapes>) tmp.clone();
			else
				shapes.clear();
		}
		//����
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
		
		public void initialReminder(){ //��һ��Ԫ�س�ʼ��Ϊ��
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
