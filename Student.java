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

import java.util.ArrayList;
import java.util.Random;

public class Student {
	
	JFrame frame = new JFrame();
	JButton button = new JButton();
	JButton[] bit = new JButton[5];
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	JLabel label = new JLabel("Student Enrollment");
	JLabel label2 = new JLabel("First Name:");
	JLabel label3 = new JLabel("Last Name:");
	JLabel label4 = new JLabel("Address: ");
	JLabel label5 = new JLabel("Contact: ");
	JLabel label6 = new JLabel("Courses");
	JLabel label7 = new JLabel("Level");
	JComboBox cbox;
	
	
	JPanel panel = new JPanel();
	JTextField tex, text, text1, text2, text3, text4, text5, text6, text7;
	
	void student() {
		Com();
		
		frame.setLayout(null);
		frame.setSize(1500,900);
		frame.setTitle("STUDENT");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		ImageIcon headerimg = new ImageIcon("student.png");
		frame.setIconImage(headerimg.getImage());
		
		
		label.setBounds(50,50,300,30);
		frame.add(label);
		label.setFont(new Font("Arial",Font.BOLD,25));
		
		
		Random random = new Random();
		JLabel idd = new JLabel("ID");	
		idd.setFont(new Font("Arial",Font.BOLD,15));
		idd.setBounds(50,90,200,30);
		frame.add(idd);
		idd.setForeground(Color.BLUE);
		tex = new JTextField();
		tex.setBounds(150,90,140,30);
		tex.setText(Integer.toString(random.nextInt(20000)));
		frame.add(tex);
		
		
		label2.setFont(new Font("Arial",Font.BOLD,15));
		label2.setBounds(50,112,200,80);
		frame.add(label2);
		text = new JTextField();
		text.setBounds(150,140,300,30);
		frame.add(text);
		
		
		label3.setFont(new Font("Arial",Font.BOLD,15));
		label3.setBounds(50,170,200,80);
		frame.add(label3);
		text1 = new JTextField();
		text1.setBounds(150,200,300,30);
		frame.add(text1);
		
		
		label4.setFont(new Font("Arial",Font.BOLD,15));
		label4.setBounds(50,230,200,80);
		frame.add(label4);
		text2 = new JTextField();
		text2.setBounds(150,260,200,30);
		frame.add(text2);
		
		
		label5.setFont(new Font("Arial",Font.BOLD,15));
		label5.setBounds(400,230,210,80);
		frame.add(label5);
		text3 = new JTextField();
		text3.setBounds(500,260,200,30);
		frame.add(text3);
		
		
		label6.setFont(new Font("Arial",Font.BOLD,15));
		label6.setBounds(50,300,210,80);
		frame.add(label6);
		String[] cour = {"bit","bibm"};
		cbox = new JComboBox(cour);
		cbox.setBounds(150,325,200,30);
		frame.add(cbox);
		
	
		label7.setFont(new Font("Arial",Font.BOLD,15));
		label7.setBounds(400,300,210,80);
		frame.add(label7);
		text5 = new JTextField();
		text5.setBounds(500,325,200,30);
		frame.add(text5);
		
		JLabel label8 = new JLabel("Email: ");
		label8.setFont(new Font("Arial",Font.BOLD,15));
		label8.setBounds(50,370,200,80);
		frame.add(label8);
		text6 = new JTextField();
		text6.setBounds(150,395,300,30);
		frame.add(text6);
		
		button = new JButton("ADD");
		button.setBounds(400,460,150,30);
		button.setFont(new Font("Arial",Font.BOLD,15));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener(e -> enroll());
		frame.add(button);
		
		JLabel seg = new JLabel("To SEE RESULT");
		seg.setBounds(50,650,300,30);
		seg.setFont(new Font("Arial",Font.BOLD,20));
		frame.add(seg);
		
		JLabel stid = new JLabel("Enter ID");
		stid.setBounds(80,690,300,30);
		frame.add(stid);
		text7 = new JTextField();
		text7.setBounds(150,690,100,30);
		frame.add(text7);
		
		JButton result = new JButton();
		result.setText("See Result");
		result.setBounds(320,690,100,30);
		result.addActionListener(e -> Reesult());
		frame.add(result);
		
		JButton course = new JButton();
		course.setBounds(50,550,150,30);
		course.setText("See Courses ->");
		course.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              StuCourse ad = new StuCourse();
	              ad.stucourse();     
	             frame.dispose();}
		});
		frame.add(course);
		
		JButton modul = new JButton();
		modul.setText("Module Teacher ->");
		modul.setBounds(300,550,150,30);
		modul.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              Modules ad = new Modules();
	              ad.modules();     
	             frame.dispose();}
		});
		frame.add(modul);
		
		
		JButton but = new JButton();
		but.setText("LogOut");
		but.setFont(new Font("Arial",Font.BOLD,15));
		but.setBounds(1200,20,150,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              LoginPage ad = new LoginPage();
	              ad.Login();     
	             frame.dispose();
	             }
		});
		
		
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(750,100,600,30);
		panel1.setSize(720,600);
		frame.add(panel1);
		
		
		String[] column= {"Firstname","Lastname","Course","Level","Semester","ModuleCode","Mark","Remarks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setBounds(890,110,500,30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(700, 580));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		
		frame.setVisible(true);
		}
		
		
	void enroll() {
			Connection connection;
			Statement MS;
			ResultSet RS;
	        try {
	        	String id = tex.getText();
	            String firstname = text.getText();
	            String lastname = text1.getText();
	            String address = text2.getText();
	            String contact = text3.getText();
	            String course = cbox.getSelectedItem().toString();
	            String level = text5.getText();
	            String email = text6.getText();
	            
	            connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	            MS = connection.createStatement();
	            RS = MS.executeQuery("select * from student");
	            boolean b = true;
	            while(RS.next()) {
	                if(id.equals(RS.getString("id"))) {
	                    b = false;
	                    break;
	                }
	            	}
	            	if(b) {
	                String a = "INSERT INTO student(id, firstname, lastname, address, contact, course, level, email)"+"values('"+id+"','"+firstname+"','"+lastname+"','"+address+"','"+contact+"','"+course+"','"+level+"','"+email+"')";
	                PreparedStatement Database=connection.prepareStatement(a);
	                Database.executeUpdate();
	                JOptionPane.showMessageDialog(null, "Sucessfully added","SUCESS",JOptionPane.PLAIN_MESSAGE);
	            }
	            	else {
	                JOptionPane.showMessageDialog(null, "Student name Already exists","ERROR",JOptionPane.ERROR_MESSAGE);
	            	}
	            	
//	            	frame.removeAll();
	    		}catch(Exception e) {
	    			System.out.println("kei ni nai"+e);
	    		}
			}

		
	void Reesult() {
			Connection connection;
			Statement MS;
			ResultSet RS;
			try {
				String id = text7.getText();
				connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
		        MS = connection.createStatement();
		        RS = MS.executeQuery("select * from grades");
		        boolean b = false;
		        while(RS.next()) {
		        	if(id.equals(RS.getString("id"))) {
		        		b = true;
		        		break;
		        	}
		        }   
		        if(b) {
		        	String a = "SELECT * FROM grades WHERE id = "+"'"+id+"'";
		        	PreparedStatement Database = connection.prepareStatement(a);
					ResultSet base = Database.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(base));
					JOptionPane.showMessageDialog(null, "SUCESS","SUCESS",JOptionPane.PLAIN_MESSAGE);
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "not in database","SUCESS",JOptionPane.ERROR_MESSAGE);
		        }
				
			}catch(Exception e) {
				System.out.println("Error" +e);
			}
		}
		
	void Com() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
			MS = connection.createStatement();
			RS = MS.executeQuery("select * from course");
			
			ArrayList<String> al = new ArrayList<String>();
			while(RS.next()){
				al.add(RS.getString("course"));
			}
			
			int i=0;
			for(i=0; i<al.size(); i++) {
				cbox.addItem(al.get(i));
				cbox.setBounds(150,325,200,30);
			}
			frame.add(cbox);
		}catch(Exception e){
			System.out.println("Error+e");
		}
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student std = new Student();
		std.student();
//		std.enroll();
//		std.Reesult();

	}

}
