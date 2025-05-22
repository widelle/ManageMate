package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ToDoList implements ActionListener{

	JFrame frame = new JFrame();
	JButton BackToLaunch = new JButton("< Go Back");
	
    JPanel TaskPanel;
	ImagePanel HomePanel;
	JPanel ImportantPanel;

    JButton Task;
    JButton Home;
    JButton Task2;
    JButton Important;
    
    private ToDoListFunction taskFunction;
    
    public ImportantTask ImportantFunction;
    
	ToDoList(){
		
		Color titleGray = new Color (49, 49, 49);
    	
        frame.setTitle("ManageMate | To-Do List");
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
		
        ImageIcon image1 = new ImageIcon("abstract.jpg");
        JLabel bgLabel = new JLabel(image1);
        bgLabel.setBounds(0, 0, 1280, 720);
        frame.setContentPane(bgLabel); 
        
//PANELS
        
        JPanel OptPanel = new JPanel();
        OptPanel.setBackground(new Color(242, 242, 242));
        OptPanel.setBounds(0, 0, 300, 720);
        OptPanel.setLayout(null);
        bgLabel.add(OptPanel);
        
        HomePanel = new ImagePanel("homepanelbg.png");
        HomePanel.setBounds(300, 0, 980, 720);
        HomePanel.setLayout(null);
        HomePanel.setVisible(true);
        frame.add(HomePanel);
        
        TaskPanel = new JPanel();
        TaskPanel.setBackground(new Color(74, 97, 117));
        TaskPanel.setBounds(300, 0, 980, 720);
        TaskPanel.setLayout(null);
        TaskPanel.setVisible(false);
        frame.add(TaskPanel);
        
        ImportantPanel = new JPanel();
        ImportantPanel.setBackground(new Color (148, 46, 46));
        ImportantPanel.setBounds(300, 0, 980, 720);
        ImportantPanel.setLayout(null);
        ImportantPanel.setVisible(false);
        frame.add(ImportantPanel);
        
//TEXT        
        
    	JLabel homeTitle = new JLabel("Welcome to ManageMate!");
    	homeTitle.setFont(new Font("San Francisco", Font.BOLD, 60));
    	homeTitle.setForeground(Color.WHITE);
    	homeTitle.setBounds(130, 155, 800, 80);  
    	HomePanel.add(homeTitle);
    	
    	JLabel optTitle = new JLabel("ManageMate");
    	optTitle.setFont(new Font("San Francisco", Font.BOLD, 30));
    	optTitle.setForeground(titleGray);
    	optTitle.setBounds(60, 50, 380, 80);  
    	OptPanel.add(optTitle);
    	
    	JLabel taskTitle = new JLabel("Your Tasks");
    	taskTitle.setFont(new Font("San Francisco", Font.BOLD, 30));
    	taskTitle.setForeground(Color.WHITE);
    	taskTitle.setBounds(60, 50, 380, 80);  
    	TaskPanel.add(taskTitle);
    	
    	JLabel impTitle = new JLabel("Important Tasks");
    	impTitle.setFont(new Font("San Francisco", Font.BOLD, 30));
    	impTitle.setForeground(Color.WHITE);
    	impTitle.setBounds(60, 50, 380, 80);  
    	ImportantPanel.add(impTitle);
 
        
//BUTTONS
    	
        ImageIcon home = new ImageIcon("homeicon.png");
    	Home = new JButton("Home", home);
    	Home.setBackground(new Color(211, 211, 211));
    	Home.setForeground(titleGray);
    	Home.setFont(new Font("San Francisco", Font.BOLD, 15));
    	Home.setHorizontalTextPosition(SwingConstants.RIGHT); 
        Home.setVerticalTextPosition(SwingConstants.CENTER);  
        Home.setIconTextGap(20);
    	Home.setBounds(0, 200, 300, 60);
    	Home.setBorderPainted(false);
    	Home.setFocusable(false);
    	Home.addActionListener(this);
    	OptPanel.add(Home);
    	
    	ImageIcon user = new ImageIcon("user.png");
    	JButton User = new JButton("My Account", user);
    	User.setBackground(new Color(242, 242, 242));
    	User.setForeground(titleGray);
    	User.setFont(new Font("San Francisco", Font.BOLD, 18));
    	User.setHorizontalTextPosition(SwingConstants.RIGHT); 
        User.setVerticalTextPosition(SwingConstants.CENTER);  
        User.setIconTextGap(20);
    	User.setBounds(0, 500, 300, 60);
    	User.setBorderPainted(false);
    	User.setFocusable(false);
    	OptPanel.add(User);
    	
        ImageIcon task = new ImageIcon("tasksicon.png");
    	Task = new JButton("Tasks", task);
    	Task.setBackground(new Color(242, 242, 242));
    	Task.setForeground(titleGray);
    	Task.setFont(new Font("San Francisco", Font.BOLD, 15));
    	Task.setHorizontalTextPosition(SwingConstants.RIGHT); 
        Task.setVerticalTextPosition(SwingConstants.CENTER);  
        Task.setIconTextGap(20);
    	Task.setBounds(0, 260, 300, 60);
    	Task.setBorderPainted(false);
    	Task.setFocusable(false);
    	Task.addActionListener(this);
    	OptPanel.add(Task);
    	
        ImageIcon important = new ImageIcon("importanticon.png");
    	Important = new JButton("Important", important);
    	Important.setBackground(new Color(242, 242, 242));
    	Important.setForeground(titleGray);
    	Important.setFont(new Font("San Francisco", Font.BOLD, 15));
    	Important.setHorizontalTextPosition(SwingConstants.RIGHT); 
        Important.setVerticalTextPosition(SwingConstants.CENTER); 
        Important.setIconTextGap(20);
    	Important.setBounds(0, 320, 300, 60);
    	Important.setBorderPainted(false);
    	Important.setFocusable(false);
    	Important.addActionListener(this);
    	OptPanel.add(Important);
    	
    	ImageIcon task2 = new ImageIcon("arrownext50.png");
     	Task2 = new JButton("Go to Tasks to start", task2);
     	Task2.setBackground(new Color(242, 242, 242));
     	Task2.setForeground(Color.WHITE);
     	Task2.setFont(new Font("San Francisco", Font.BOLD, 15));
     	Task2.setHorizontalTextPosition(SwingConstants.RIGHT); 
        Task2.setVerticalTextPosition(SwingConstants.CENTER);  
        Task2.setIconTextGap(20);
     	Task2.setBounds(350, 500, 300, 80);
     	Task2.setBorderPainted(false);
     	Task2.setFocusable(false);
     	Task2.setOpaque(false);
     	Task2.addActionListener(this);
     	HomePanel.add(Task2);
     
        
        frame.setVisible(true);

     
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BackToLaunch) {
	        frame.dispose();        
	        new OptionWindow();      
	    }  else if (e.getSource() == Task) {
            HomePanel.setVisible(false);
            TaskPanel.setVisible(true);           
            Task.setBackground(new Color(211, 211, 211));
            Home.setBackground(new Color(242, 242, 242));
            Important.setBackground(new Color(242, 242, 242)); 
            
            if(taskFunction == null) {
            	taskFunction = new ToDoListFunction(TaskPanel);
            }
            
        } else if (e.getSource() == Home) {
            TaskPanel.setVisible(false);
            HomePanel.setVisible(true);
            Home.setBackground(new Color(211, 211, 211));
            Task.setBackground(new Color(242, 242, 242));
            Important.setBackground(new Color(242, 242, 242));
            
        } else if (e.getSource() == Task2){
        	HomePanel.setVisible(false);
            TaskPanel.setVisible(true);
            Task.setBackground(new Color(211, 211, 211));
            Home.setBackground(new Color(242, 242, 242));
            Important.setBackground(new Color(242, 242, 242));
            
            if(taskFunction == null) {
            	taskFunction = new ToDoListFunction(TaskPanel);
            }
            
        } else if(e.getSource() == Important) {
        	HomePanel.setVisible(false);
            TaskPanel.setVisible(false);
            ImportantPanel.setVisible(true);
            Important.setBackground(new Color(211, 211, 211));
            Task.setBackground(new Color(242, 242, 242));
            Home.setBackground(new Color(242, 242, 242));
            
            if(	ImportantFunction == null) {
            	ImportantFunction = new ImportantTask(ImportantPanel);
            }
        }
	}
}