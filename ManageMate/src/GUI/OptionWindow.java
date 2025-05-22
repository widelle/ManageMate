package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class OptionWindow implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton ToDoList = new JButton("Open");
	JButton BackToLaunch = new JButton("< Go Back");
	
	OptionWindow(){
//FRAME	
        frame.setTitle("ManageMate");
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        ImageIcon image1 = new ImageIcon("abstract.jpg");
        JLabel bgLabel = new JLabel(image1);
        bgLabel.setBounds(0, 0, 1280, 720);
        frame.setContentPane(bgLabel); 
 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ToDoList) {
			frame.dispose();
			ToDoList tdList = new ToDoList();      
		}
	}
			
}
	