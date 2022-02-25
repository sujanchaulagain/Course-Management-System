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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class Modules extends JFrame {
	//including necessary like frame, textfield for gui
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	
	
	void modules() {
		frame.setLayout(null);
		frame.setSize(1000,800);
		frame.setTitle("Modules");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		
		ImageIcon headerimg = new ImageIcon("logo.png");
		frame.setIconImage(headerimg.getImage());
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(50,50,600,30);
		panel1.setSize(900,550);
		frame.add(panel1);
		
		//button to show modules
		JButton click = new JButton();
		click.setText("CLICK ME");
		click.setBounds(300,650,300,50);
		click.addActionListener(e -> Update());
		frame.add(click);
		
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
	             frame.dispose();
	             }
		});
		
		//jtable
		String[] column= {"ID","Teachername","ModuleTitle","ModuleCode","Course","Level"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setBounds(890,110,500,30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll9 = new JScrollPane(table);
        scroll9.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll9.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll9.setPreferredSize(new Dimension(850, 500));
        panel1.add(scroll9);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		
		
		frame.setVisible(true);
		
	}
	
	
	//connection to show information in jtable
	void Update() {
		Connection connection = null;
		Statement MS = null;
		ResultSet RS = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
            MS = connection.createStatement();
            RS = MS.executeQuery("select * from instructor");
            while(RS.next()) {
            	String inst = "select * from instructor";
            	PreparedStatement Database=connection.prepareStatement(inst);
                Database.executeQuery();
                

            }
		}catch(Exception e) {
			System.out.println("error"+e);
		}finally {
			try {
				MS.close();
				RS.close();
				connection.close();
			}catch(Exception e) {
				System.out.println("error"+e);
			}
			showTableData();
		}
	}
	
	//connection for jtable
	public void showTableData() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	         MS = connection.createStatement();
	         RS = MS.executeQuery("select * from instructor");
	         
			table.setModel(DbUtils.resultSetToTableModel(RS));
			
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modules mod = new Modules();
		mod.modules();
		mod.Update();
		
	}

}
