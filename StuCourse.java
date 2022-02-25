package CMS;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;


public class StuCourse {
	//including necessary like frame, panel, label for gui
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	JButton[] button = new JButton[5];
	JLabel label = new JLabel();
	JLabel labela = new JLabel();
	JPanel panel = new JPanel();
	
	StuCourse() {            //constructor
		stucourse();
	}
	
	void stucourse() {
		content();
		frame.setLayout(null);
		panel.setBounds(100,200,200,150);
		panel.setLayout(new GridLayout(2,3));
		panel.setBounds(20,25,300,300);
		panel.setBackground(new Color(51,204,255));
		frame.setSize(1000,800);
		frame.setTitle("Courses");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(panel);

		ImageIcon headerimg = new ImageIcon("logo.png");  //image icon
		frame.setIconImage(headerimg.getImage());
		
		//back button
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(750,15,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              Student ad = new Student();
	              ad.student();     
	             frame.dispose();}
		});
			
		
		frame.setVisible(true);
	}
	
	//connection for course
	void Data(String level) {
		  Connection  connection;
		  Statement MS;
		  ResultSet RS;
			try {
				connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
				MS = connection.createStatement();
				RS = MS.executeQuery("select * from " + level);
				String string = "";
				while(RS.next()) {
					if(RS.getString("level").equals("")&RS.getString("semester").equals("")) {
						string += (RS.getString("modulecode")+":"+RS.getString("moduletitle")+"<br>");
					}else {
						string += ("<br>"+RS.getString("level")+" sem:"+RS.getString("semester")+"<br>"+RS.getString("modulecode")+ RS.getString("moduletitle")+"<br>");
					}
				}
			
				panel.removeAll();
				panel.setVisible(false);
				labela.setText("<html>"+ string +"</html>");
				panel.add(labela);
				panel.setVisible(true);
				panel.setBounds(50,60,1100,1100);	
			}catch(Exception e) {
				System.out.println("Error"+ e);
	}
	  }
	  
	 //conection for course
	void content() {
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
				button[i] = new JButton();
				button[i].setText(al.get(i));
				button[i].setBounds(100,100,300,30);
				String cname = al.get(i);
				button[i].addActionListener(e->Data(cname));
				panel.add(button[i]);	
			}
		
			
		}catch(Exception e){
			System.out.println("Error ayo" +e);
		}
		}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuCourse st = new StuCourse();
		
	}

}
