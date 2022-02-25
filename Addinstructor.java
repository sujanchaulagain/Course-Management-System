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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addinstructor {
	//creating frame textfield and combobox in gui
	JFrame frame = new JFrame();
	JTextField tex, text, text1, text2, text3, text4;
	JComboBox cbox,cbox1; 
	
	void teacher() {
		frame.setLayout(null);
		frame.setSize(1000,800);
		frame.setTitle("Administrator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		ImageIcon headerimg = new ImageIcon("admin.png");  //Image icon
		frame.setIconImage(headerimg.getImage());
		
		//teacher button
		JButton tdetail = new JButton();
		tdetail.setText("Teacher");
		tdetail.setBounds(320,70,190,30);
		frame.add(tdetail);
		
		JLabel td = new JLabel("Teacher Detail");
		td.setBounds(50,125,170,30);
		td.setFont(new Font("Arial",Font.BOLD,18));
		frame.add(td);
		
		Random random = new Random();
		JLabel idd = new JLabel("ID");	
		idd.setFont(new Font("Arial",Font.BOLD,15));
		idd.setBounds(70,180,200,30);
		frame.add(idd);
		idd.setForeground(Color.BLUE);
		tex = new JTextField();
		tex.setBounds(220,180,100,30);
		tex.setText(Integer.toString(random.nextInt(2000)));
		frame.add(tex);
		
		JLabel nc = new JLabel("Teacher name: ");
		nc.setBounds(70,230,290,30);
		text = new JTextField(); //create TextField  for User name
		text.setBounds(220,230,300,30);
		frame.add(nc);
		frame.add(text);
		
		JLabel mn = new JLabel("Module name: ");
		mn.setBounds(70,280,250,30);
		text1 = new JTextField(); //create TextField  for User name
		text1.setBounds(220,280,300,30);
		frame.add(mn);
		frame.add(text1);
		
		JLabel mc = new JLabel("Module Code: ");
		mc.setBounds(70,330,300,30);
		text2 = new JTextField(); //create TextField  for User name
		text2.setBounds(220,330,300,30);
		frame.add(mc);
		frame.add(text2);
		
		JLabel course = new JLabel("Course ");
		course.setBounds(70,370,280,30);
		String[] semm = {"bit","bibm"};
		cbox = new JComboBox(semm);
	    cbox.setBounds(220,380,100,30);
	    frame.add(cbox);
		frame.add(course);
		
		JLabel level = new JLabel("Level: ");
		level.setBounds(70,430,280,30);
		String[] lev = {"3","4","5","6"};
		cbox1 = new JComboBox(lev);
	    cbox1.setBounds(220,430,100,30);
	    frame.add(cbox1);
		frame.add(level);
		
		JButton add = new JButton();
		add.setBounds(170,550,200,30);
		add.setText("ADD");
		add.setForeground(Color.WHITE);
		add.setBackground(Color.gray);
		add.addActionListener(e -> teachconn());
		frame.add(add);
		
		JButton delete = new JButton();
		delete.setBounds(170,600,200,30);
		delete.setText("DELETE");
		delete.setForeground(Color.WHITE);
		delete.setBackground(Color.gray);
		delete.addActionListener(e -> delteacher());
		frame.add(delete);
		
//		panel for teacher
		JPanel panel1 = new JPanel();
		panel1.setBounds(40,120,600,600);
		frame.add(panel1);
		
		JPanel panel = new JPanel();
		panel.setBounds(320,70,190,100);
		frame.add(panel);
		
		//back button
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(700,20,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              Admin ad = new Admin();
	              ad.admin();     
	             frame.dispose();}
		});
		
		frame.setVisible(true);
	}
	
	//connection for adding instructor
	void teachconn() {
		Connection connection;
		Statement myStatement;
		ResultSet myData;
		try {
			String id = tex.getText();
			String teachername = text.getText();
			String  moduletitle = text1.getText();
			String modulecode = text2.getText();
			String course = cbox.getSelectedItem().toString();
			String level = cbox1.getSelectedItem().toString();
			
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	        myStatement = connection.createStatement();
	        myData = myStatement.executeQuery("select * from instructor");  
	        boolean b = true;
	        while(myData.next()) {
	        	if(id.equals(myData.getString("id"))) {
	        		b = false;
	        		break;
	        	}	
	        }
	        	if(b) {
	        	String a = "INSERT INTO instructor(id, teachername, moduletitle, modulecode, course, level)"+"values('"+id+"','"+teachername+"','"+moduletitle+"','"+modulecode+"','"+course+"','"+level+"')";
	        	PreparedStatement Database=connection.prepareStatement(a);
	            Database.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Sucessfully added","SUCESS",JOptionPane.PLAIN_MESSAGE);
	        }
	        	else {
	        		JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
	        	}
	        
	
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}

	//connection for deleting instructor
	void delteacher() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			String id = tex.getText();
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
			MS = connection.createStatement();
			RS=MS.executeQuery("select * from instructor");
			boolean b= false;
			while(RS.next()) {
				if(id.equals(RS.getString("id"))) {
					b = true;
					break;
				}
				
			}
				if(b) {
					String x ="DELETE FROM instructor WHERE id = "+"'" +id+"'";
					PreparedStatement Database=connection.prepareStatement(x);
					Database.executeUpdate();
					JOptionPane.showMessageDialog(null, "Name deleted","SUCESS",JOptionPane.PLAIN_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Name not found in database","ERROR",JOptionPane.ERROR_MESSAGE);
				}	
		}
		catch(Exception e) {
			System.out.println("Error \n"+e);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Addinstructor teacher = new Addinstructor();
		teacher.teacher();

	}

}
