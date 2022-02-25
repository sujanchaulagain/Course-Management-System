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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class InEnrolled {
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	JTable table1 = new JTable();
	
	void enrolled() {
		frame.setLayout(null);
		frame.setSize(1000,850);
		frame.setTitle("Student Enrolled");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		
		ImageIcon headerimg = new ImageIcon("logo.png");
		frame.setIconImage(headerimg.getImage());
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(50,50,600,30);
		panel1.setSize(900,300);
		frame.add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(java.awt.Color.WHITE);
		panel2.setBounds(50,430,600,30);
		panel2.setSize(900,300);
		frame.add(panel2);
		
		JButton click = new JButton();
		click.setText("CLICK TO SEE STUDENTS ENROLLED");
		click.setBounds(300,370,300,30);
		click.addActionListener(e -> Update());
		frame.add(click);
		
		JButton click1 = new JButton();
		click1.setText("CLICK TO SEE TEACHER MODULES");
		click1.setBounds(300,750,300,30);
		click1.addActionListener(e -> tmodule());
		frame.add(click1);
		
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,15));
		but.setBounds(750,15,100,30);
		frame.add(but);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	              Instructor ad = new Instructor();
	              ad.instructor();     
	             frame.dispose();}
		});
		
		
		String[] column= {"ID","Firstname","Lastname","Address","Contact","Course","Level","Email"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setBounds(890,110,500,30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(850, 280));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
        String[] column1= {"ID","Teachername","ModuleTitle","ModuleCode","Course","Level"};
        DefaultTableModel model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(column1);

        table1.setModel(model1); 
        table1.setBounds(890,110,500,30);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setFillsViewportHeight(true);
        JScrollPane scroll1 = new JScrollPane(table1);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll1.setPreferredSize(new Dimension(850, 280));
        panel2.add(scroll1);
        panel2.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		frame.setVisible(true);
		
	}
	
	void Update() {
		Connection connection = null;
		Statement MS = null;
		ResultSet RS = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
            MS = connection.createStatement();
            RS = MS.executeQuery("select * from student");
            while(RS.next()) {
            	String inst = "select * from student";
            	PreparedStatement Database=connection.prepareStatement(inst);
                Database.executeQuery();
            }
		}catch(Exception e) {
			System.out.println("error"+e);
		}
		showTableData();
	}
	
	//connetion for jtable
	public void showTableData() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from student");
	         
			table.setModel(DbUtils.resultSetToTableModel(RS));
			
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}
	
	//connection for instructor in j table
	void tmodule() {
		try {
			Connection connection = null;
			Statement MS = null;
			ResultSet RS = null;
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
            MS = connection.createStatement();
            RS = MS.executeQuery("select * from instructor");
            while(RS.next()) {
            	String inst = "select * from instructor";
            	PreparedStatement Database=connection.prepareStatement(inst);
                ResultSet rs = Database.executeQuery();
                table1.setModel(DbUtils.resultSetToTableModel(rs));
            }
			
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InEnrolled en = new InEnrolled();
		en.enrolled();
		en.Update();
		en.tmodule();
	}

}