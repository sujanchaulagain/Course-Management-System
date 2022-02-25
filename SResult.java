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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import net.proteanit.sql.DbUtils;

public class SResult {
	
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JPanel panel1 = new JPanel();
	JTextField text, text1;
	JFrame f = new JFrame();
	JTextArea area = new JTextArea();
	
	
	void sresult() {
		frame.setLayout(null);
		frame.setSize(1200,800);
		frame.setTitle("Student Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(51,204,255));
		frame.setResizable(false);
		
		ImageIcon headerimg = new ImageIcon("logo.png");
		frame.setIconImage(headerimg.getImage());
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(java.awt.Color.WHITE);
		panel1.setBounds(50,150,600,30);
		panel1.setSize(900,550);
		frame.add(panel1);
		
		JLabel name = new JLabel("First name ");
		name.setBounds(40,50,150,30);
		name.setFont(new Font("Arial",Font.BOLD,15));
		frame.add(name);
		text = new JTextField();
		text.setBounds(150,50,250,30);
		frame.add(text);
		
		JLabel sname = new JLabel("Second name ");
		sname.setBounds(40,100,150,30);
		sname.setFont(new Font("Arial",Font.BOLD,15));
		frame.add(sname);
		text1 = new JTextField();
		text1.setBounds(150,100,250,30);
		frame.add(text1);
		
		JButton click = new JButton();
		click.setText("Search");
		click.setBounds(450,103,150,30);
		click.addActionListener(e -> marks());
		frame.add(click);
		
		
		
		JButton but = new JButton();
		but.setText("Back");
		but.setFont(new Font("Arial",Font.BOLD,20));
		but.setBounds(1000,20,100,30);
		but.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {  
	             Admin ad = new Admin();
	              ad.admin();     
	             frame.dispose();
	             }
		});
		frame.add(but);
		
		
		String[] column= {"id","firstname","lastname","course","level","semester","moduleCode","mark","remarks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);

        table.setModel(model); 
        table.setBounds(890,110,500,30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(780, 520));
        panel1.add(scroll);
        panel1.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.cyan));
		
		frame.setVisible(true);
	}
	
	//connection
	void marks() {
		Connection connection;
		Statement MS;
		ResultSet RS;
		try {
			String fname = text.getText();
			String sname = text1.getText();
			connection = DriverManager.getConnection("jdbc:mysql:///cmsystem","root","");
	        MS = connection.createStatement();
	        RS = MS.executeQuery("select * from grades");
	        boolean b = true;
	        while(RS.next()) {
	        	if(fname.equals(RS.getString("firstname"))&sname.equals(RS.getString("lastname"))) {
	        		b = false;
	        		break;
	        	}
	        	
	        	if(b) {	
	        		String a = "SELECT * FROM grades WHERE firstname = "+"'"+fname+"' && lastname = "+"'"+sname+"'";
		        	PreparedStatement Database = connection.prepareStatement(a);
					ResultSet base = Database.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(base));
					JOptionPane.showMessageDialog(null, "SUCESS","SUCESS",JOptionPane.PLAIN_MESSAGE);
	        			
	        	}   
	        	else {
	        		JOptionPane.showMessageDialog(null, "ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
	        }
	        }
			
		}catch(Exception e) {
			System.out.println("Error" +e);
		}
	}
	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SResult sr = new SResult();
		sr.sresult();
	}

}
