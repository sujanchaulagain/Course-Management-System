package CMS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class Result {
	
	//including necessary like frame, textfield for gui
	JFrame frame = new JFrame();
	JTextField tex, text, text1, text2, text3, text4, text5, text6, finame, sename;
	JComboBox combo, combo1, combo2;
	 JTable jtable = new JTable();
	

	
	void result() {
		frame.setLayout(null);
		frame.setSize(1400,800);
		frame.setTitle("Student Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		ImageIcon header = new ImageIcon("logo.png");
		frame.setIconImage(header.getImage());
		
		//label
		JLabel re = new JLabel("Result Sheet");
		re.setBounds(50,80,170,30);
		re.setFont(new Font("Arial",Font.BOLD,22));
		frame.add(re);
		
		JLabel idd = new JLabel("ID");
		idd.setBounds(60,130,290,30);
		tex = new JTextField();
		tex.setBounds(200,130,100,30);
		frame.add(idd);
		frame.add(tex);
		
		JLabel name = new JLabel("First Name");
		name.setBounds(60,180,290,30);
		text = new JTextField(); //create TextField  for User name
		text.setBounds(200,180,340,30);
		frame.add(name);
		frame.add(text);
		
		JLabel sname = new JLabel("Second Name");
		sname.setBounds(60,230,290,30);
		text1 = new JTextField(); //create TextField  for User name
		text1.setBounds(200,230,340,30);
		frame.add(sname);
		frame.add(text1);
		
		JLabel co = new JLabel("Course");
		co.setBounds(60,280,290,30);
		frame.add(co);
		String[] title= {"bit","bibm"};
		combo = new JComboBox(title);
	    combo.setBounds(200, 280, 80, 30);
	    frame.add(combo);
	    
	    JLabel level = new JLabel("Level");
		level.setBounds(350,280,290,30);
		frame.add(level);
		String[] le = {"3","4","5","6"};
		combo1 = new JComboBox(le);
	    combo1.setBounds(450, 280, 90, 30);
	    frame.add(combo1);
	    
	    JLabel sem = new JLabel("Semester");
		sem.setBounds(60,330,290,30);
		frame.add(sem);
		String[] seme = {"1","2"};
		combo2 = new JComboBox(seme);
		combo2.setBounds(200, 330, 80, 30);
		frame.add(combo2);

		
		JLabel mcode = new JLabel("Module Code");
		mcode.setBounds(350,330,290,30);
		text2 = new JTextField(); //create TextField  for User name
		text2.setBounds(450,330,90,30);
		frame.add(mcode);
		frame.add(text2);	
	    
		JLabel mtitle = new JLabel("Module Title");
		mtitle.setBounds(60,380,290,30);
		text3 = new JTextField(); //create TextField  for User name
		text3.setBounds(200,380,340,30);
		frame.add(mtitle);
		frame.add(text3);
			
		JLabel marks = new JLabel("Marks Obtained");
		marks.setBounds(60,430,290,30);
		text4 = new JTextField(); //create TextField  for User name
		text4.setBounds(200,430,100,30);
		frame.add(marks);
		frame.add(text4);
		
		JLabel remark = new JLabel("Remarks");
		remark.setBounds(60,480,290,30);
		text5 = new JTextField(); //create TextField  for User name
		text5.setBounds(200,480,340,30);
		frame.add(remark);
		frame.add(text5);
		
		JLabel tid = new JLabel("Teacher ID");
		JLabel please = new JLabel("*Please enter teacher id to add result");
		please.setBounds(350,450,290,30);
		please.setFont(new Font("Arial",Font.BOLD,10));
		please.setForeground(Color.RED);
		tid.setBounds(350,430,290,30);
		text6 = new JTextField(); //create TextField  for User name
		text6.setBounds(440,430,100,30);
		frame.add(please);
		frame.add(tid);
		frame.add(text6);
		
		//submit button
		JButton submit = new JButton();
		submit.setText("Submit");
		submit.setBounds(250,550,150,30);
		submit.addActionListener(e -> subconnect());
		frame.add(submit);
		
		JButton deletee = new JButton();
		deletee.setText("Delete");
		deletee.setBounds(250,600,150,30);
//		deletee.addActionListener(e->delete());
		frame.add(deletee);
		
		//back button
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(1200,20,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	             Instructor ad = new Instructor();
	              ad.instructor();     
	             frame.dispose();
//	             ad.setVisible(true);
	             }
		});
		
		//panel for jtable
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(650,200,600,30);
		panel1.setSize(700,550);
		frame.add(panel1);
		
		JLabel ffname = new JLabel("First name ");
		ffname.setBounds(700,50,150,30);
		ffname.setFont(new Font("Arial",Font.BOLD,14));
		frame.add(ffname);
		finame = new JTextField();
		finame.setBounds(800,50,250,30);
		frame.add(finame);
		
		JLabel ssname = new JLabel("Second name ");
		ssname.setBounds(700,100,150,30);
		ssname.setFont(new Font("Arial",Font.BOLD,14));
		frame.add(ssname);
		sename = new JTextField();
		sename.setBounds(800,100,250,30);
		frame.add(sename);
		
		JButton click = new JButton();
		click.setText("Search");
		click.setBounds(800,150,150,30);
		click.addActionListener(e -> showTableData());
		frame.add(click);
		
		String[] column= {"ID","Firstname","Lastname","Course","Level","Semester","ModuleCode","Mark","Remarks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

//        JTable table = new JTable();
        jtable.setModel(model); 
        jtable.setAutoResizeMode(JTable.FRAMEBITS);
        jtable.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(jtable);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scroll.setPreferredSize(new Dimension(660, 520));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		
		
		frame.setVisible(true);
	}
	
	//connection for adding the marks of students
	void subconnect() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			System.out.println("hello");
			 String id = tex.getText();
			 String firstname = text.getText();
	         String lastname = text1.getText();
	         String course = combo.getSelectedItem().toString();
	         String level = combo1.getSelectedItem().toString();
	         String semester = combo2.getSelectedItem().toString();
	         String modulecode = text2.getText();
	         String moduletitle = text3.getText();
	         String marks = text4.getText();
	         String remarks = text5.getText();
			
			 connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from student");
	         boolean b=false;
	         while(RS.next()){
	        	 System.out.println("hello");
	                if(id.equals(RS.getString("id"))){
	                	System.out.println("hello");
	                	b = true;
	                	break;
	                }
	                }
	                if(b){
	                	System.out.println("hello");
	                	String a = "INSERT INTO grades(id, firstname, lastname, course, level, semester, modulecode, moduletitle, marks, remarks)"+
	         		               "values('"+id+"','"+firstname+"','"+lastname+"','"+course+"','"+level+"','"+semester+"','"+modulecode+"','"+moduletitle+"','"+marks+"','"+remarks+"')";
	         			PreparedStatement base = connection.prepareStatement(a);
		                base.executeUpdate();
		                JOptionPane.showMessageDialog(null, "Sucessfully added","SUCESS",JOptionPane.PLAIN_MESSAGE);
	                }  
	         		else {
	         			JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
		            }
		            
		}catch(Exception e) {
			System.out.println("Error" +e);
		}
	}
	
//connection showing table
	public void showTableData() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			String firname = finame.getText();
			String secname = sename.getText();
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	        MS = connection.createStatement();
	        RS = MS.executeQuery("select * from grades");
	        boolean b = true;
	        while(RS.next()) {
	        	if(firname.equals(RS.getString("firstname"))&secname.equals(RS.getString("lastname"))) {
	        		b = false;
	        		break;
	        	}
	        	
	        	if(b) {	
	        		String a = "SELECT * FROM grades WHERE firstname = "+"'"+firname+"' && lastname = "+"'"+secname+"'";
		        	PreparedStatement Database = connection.prepareStatement(a);
					ResultSet base = Database.executeQuery();
					jtable.setModel(DbUtils.resultSetToTableModel(base));
					JOptionPane.showMessageDialog(null, "SUCESS","SUCESS",JOptionPane.PLAIN_MESSAGE);
					break;
	        			
	        	}   
	        	else {
	        		JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
	        }
	        }
			
		}catch(Exception e) {
			System.out.println("Error" +e);
		}
	}
	
	//connection for adding result (condition)
	 void id() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			 String id = text6.getText();
			 String modulename = text3.getText();
			 connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from instructor");
	         boolean b = false;
	         while(RS.next()) {
	        	 if(id.equals(RS.getString("id"))&modulename.equals(RS.getString("moduletitle"))){
	        	 b = true;
	        	 break;
	        	 }
	         }
	         if(b){
	        		 subconnect();
	        		 System.out.println("heelo");
	        	 }
			 else {
				 JOptionPane.showMessageDialog(null, "Module not match","ERROR",JOptionPane.ERROR_MESSAGE);
			 
	         }
	}catch(Exception e) {
		System.out.println("error"+e);
	}
	}
	
	 void delete() {
		 try {
			 String id = tex.getText();
			Connection connection;
			Statement MS;
			ResultSet RS;
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from grades");
	         while(RS.next()) {
	        	 String del = "Delete from grades WHERE id= "+"'"+id+"'";
	            	PreparedStatement Database=connection.prepareStatement(del);
	                Database.executeUpdate();
	                JOptionPane.showMessageDialog(null, "Student deleted! Please Refresh","SUCESS",JOptionPane.PLAIN_MESSAGE);
	                break;
	         }
			
			 
		 }catch(Exception e) {
			 System.out.println("Error"+e);
		 }
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.result();
//		result.showTableData();

	}

}
