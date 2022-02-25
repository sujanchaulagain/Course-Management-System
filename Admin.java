package CMS;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;


public class Admin {
	//including necessary like frame, textfield, button for gui
	JFrame frame = new JFrame();
	JTextField text, text1, text2;
	JComboBox cbox,cbox1; 
	JTable table = new JTable();
	JPanel panel3 = new JPanel();
	JButton sdetail = new JButton();
	JButton tdetail = new JButton();
	JButton result = new JButton();
	JButton student = new JButton();
	JButton course = new JButton();
	JButton add = new JButton();
	JButton delete = new JButton();
	JButton edit = new JButton();
	JButton cancel = new JButton();
	JLabel cm = new JLabel("Course & Modules");
	JLabel nc = new JLabel("Module Title: ");
	JLabel mt = new JLabel("Module Code: ");
	
	
	void admin(){
		frame.setLayout(null);
		frame.setSize(1150,800);
		frame.setTitle("Administrator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		
		ImageIcon headerimg = new ImageIcon("admin.png");
		frame.setIconImage(headerimg.getImage());
		
		
		sdetail.setText("Details");
		sdetail.setBounds(50,70,190,30);
		frame.add(sdetail);
		
		
		tdetail.setText("Add Teacher");
		tdetail.setBounds(260,70,190,30);
		frame.add(tdetail);
		tdetail.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	        	   Addinstructor teacher = new Addinstructor();
	               teacher.teacher();     
	               frame.dispose();}
		});
		
		
		result.setText("Student Result");
		result.setBounds(475,70,190,30);
		frame.add(result);
		result.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	        	   SResult result = new SResult();
	               result.sresult();     
	               frame.dispose();}
		});
		
		
		student.setText("Students and Teachers");
		student.setBounds(910,70,190,30);
		frame.add(student);
		student.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	        	   Enrolled teacher = new Enrolled();
	               teacher.enrolled();     
	               frame.dispose();}
		});
		
		
		course.setBounds(695,70,190,30);
		course.setText("See Courses");
		course.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              Course ad = new Course();
	              ad.conect();     
	             frame.dispose();}
		});
		frame.add(course);
		
			
		
		cm.setBounds(130,130,170,30);
		cm.setFont(new Font("Arial",Font.BOLD,15));
		frame.add(cm);
		
		
		nc.setBounds(160,180,290,30);
		text = new JTextField(); //create TextField  for User name
		text.setBounds(350,180,300,30);
		frame.add(nc);
		frame.add(text);
		
		
		mt.setBounds(160,230,250,30);
		text1 = new JTextField(); //create TextField  for User name
		text1.setBounds(350,230,300,30);
		frame.add(mt);
		frame.add(text1);
		
		JLabel mc = new JLabel("Course ");
		mc.setBounds(160,280,300,30);
		text2 = new JTextField(); //create TextField  for User name
		text2.setBounds(350,280,300,30);
		frame.add(mc);
		frame.add(text2);
		
		JLabel sem = new JLabel("Select Level: ");
		sem.setBounds(160,330,280,30);
		frame.add(sem);
		String[] leve = {"3","4","5","6"}; 
		cbox1 = new JComboBox(leve);
	    cbox1.setBounds(350, 330, 90, 30);
	    frame.add(cbox1);
		
		
		JLabel le = new JLabel("Select Semester: ");
		le.setBounds(160,380,280,30);
		frame.add(le);
		String[] semm = {"1","2"};
		cbox = new JComboBox(semm);
	    cbox.setBounds(350, 380, 90, 30);
	    frame.add(cbox);		
		
		JPanel panel = new JPanel();
		panel.setBounds(50,100,190,100);
		frame.add(panel);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(50,120,650,600);
		frame.add(panel1);
		
		JButton but = new JButton();
		but.setText("Logout");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(1000,20,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              LoginPage ad = new LoginPage();
	              ad.Login();     
	             frame.dispose();
	             }
		});
   
        
        
		add.setBounds(250,500,200,30);
		add.setText("ADD Course");
		add.setForeground(Color.WHITE);
		add.setBackground(Color.gray);
		add.addActionListener(e -> adcourse());
		frame.add(add);
		
		
		
		edit.setBounds(250,550,200,30);
		edit.setText("EDIT Course");
		edit.setForeground(Color.WHITE);
		edit.setBackground(Color.gray);
		edit.addActionListener(e -> edcourse());
		frame.add(edit);
		
		
		delete.setBounds(250,600,200,30);
		delete.setText("DELETE Course");
		delete.setForeground(Color.WHITE);
		delete.setBackground(Color.gray);
		delete.addActionListener(e -> delcourse());
		frame.add(delete);
		
		
		cancel.setBounds(250,650,200,30);
		cancel.setText("CLEAR");
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.gray);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text.setText("");
				text1.setText("");
				text2.setText("");		
			}
		});
		frame.add(cancel);
		
			
		frame.setVisible(true);
	}
	
	//connection for adding course
	void adcourse() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			String course = text.getText();
			String moduletitle = text1.getText();
			String modulecode = text2.getText();
			String level = cbox.getSelectedItem().toString();
			String semester = cbox1.getSelectedItem().toString();		
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
			MS = connection.createStatement();
			RS = MS.executeQuery("Select * from course");
			boolean b = true;
			while(RS.next()){
				if(course.equals(RS.getString("course").toLowerCase())) {
					b = false;
					break;
				}
			}
				if(b){
					String x= "INSERT INTO course(course)" + "values('"+course+"')";
					PreparedStatement ps = connection.prepareStatement(x);
					ps.executeUpdate();
					Statement stmt = connection.createStatement();
					String y = "create table "+ course + " (level varchar(255), semester varchar(20), modulecode varchar(255), moduletitle varchar(520))";
					stmt.executeUpdate(y);
					String e = "INSERT INTO "+ course +"(level, semester, modulecode, moduletitle)" + "values('" + level + "','"+semester+"','" + modulecode + "', '" + moduletitle + "')";
					stmt.executeUpdate(e);
					
					JOptionPane.showMessageDialog(null, "Course added","SUCESS",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Course exists", "ERROR",JOptionPane.ERROR_MESSAGE);
				}
		}catch(Exception e) {
			System.out.println("noting"+e);
		}
	}
		
	//connection for delete course
	void delcourse() {
			Connection connection;
			Statement MS;
			ResultSet RS;
			try {
				String course= text.getText();
				connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
				MS = connection.createStatement();
				RS=MS.executeQuery("select * from course");
				boolean b= false;
				while(RS.next()) {
					if(course.equals(RS.getString("course"))) {
						b = true;
						break;
					}	
				}
					if(b) {
						String x ="DELETE FROM course WHERE course = "+"'" + course +"'";
						PreparedStatement ps=connection.prepareStatement(x);
						ps.executeUpdate();
						String y = "DROP TABLE " + course;
						PreparedStatement Data=connection.prepareStatement(y);
						Data.executeUpdate(y);
						JOptionPane.showMessageDialog(null, "Course deleted","SUCESS",JOptionPane.PLAIN_MESSAGE);
		
					}
					else {
						JOptionPane.showMessageDialog(null, "Course not found database","ERROR",JOptionPane.ERROR_MESSAGE);	
					}	
			}
			catch(Exception e) {
				System.out.println("Error \n"+e);
			}
		}
		
	//connection for editing course
	void edcourse() {
			Connection connection;
			Statement MS;
			ResultSet RS;
			try {
				String course= text.getText();
				String moduletitle= text1.getText();
				String modulecode= text2.getText();
				String level = cbox.getSelectedItem().toString();
				String semester = cbox1.getSelectedItem().toString();	
				connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
				MS = connection.createStatement();
				RS=MS.executeQuery("select * from course");
				boolean b = false;
				while(RS.next()) {
					if(course.equals(RS.getString("course"))) {
						b = true;
						break;
					}
				}
					if(b) {
						String e = "INSERT INTO " + course + "(level, semester, modulecode, moduletitle)"+"values('"+level+"', '"+semester+"', '"+modulecode+"', '"+moduletitle+"')";
						Statement stmt = connection.createStatement();
						stmt.executeUpdate(e);
						JOptionPane.showMessageDialog(null, "Sucessfully editied","SUCESS",JOptionPane.PLAIN_MESSAGE);	
					}
					else {
						JOptionPane.showMessageDialog(null, "Course not found database","ERROR",JOptionPane.ERROR_MESSAGE);	
					}
			}
			catch(Exception e) {
				System.out.println("Error \n"+e);
			}
		}
	
	void delete() {
		Connection connection;
		Statement MS;
		ResultSet RS;
	
		try {
			String course= text.getText();
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
			MS = connection.createStatement();
			RS=MS.executeQuery("select * from student");
			boolean b = false;
			while(RS.next()) {
				if(course.equals(RS.getString("course"))) {
					b = true;
					break;
				}
			}
				if(b) {
					adcourse();
				}
				else {
					JOptionPane.showMessageDialog(null, "Cannot delete! Student are enrolled","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			
		}catch(Exception e) {
			System.out.println("Error"+e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Admin ad = new Admin();
		 ad.admin();
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}




