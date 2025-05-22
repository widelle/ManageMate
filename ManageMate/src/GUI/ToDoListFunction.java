package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;

public class ToDoListFunction extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField inputTask;
	private JPanel tasksScroll;
	private JCheckBox checkStatus;
	private String taskText;
	private ArrayList<String> taskList = new ArrayList<>();
	private ArrayList<JPanel> taskPanels = new ArrayList<>();
	private boolean editMode = false;
	private JButton editButton;
	private JButton addButton;
	private JButton deleteAllButton, saveButton;

	public ToDoListFunction(JPanel taskFunction) {
		// ADDING TASK
		inputTask = new JTextField(20);
		inputTask.setBounds(95, 124, 750, 65);
		inputTask.setOpaque(true);
		inputTask.setBorder(null);
		inputTask.setFont(new Font("San Francisco", Font.PLAIN, 16));
		taskFunction.add(inputTask);

		// Button
		Dimension buttonSize = new Dimension(176, 82);

		addButton = new JButton("+ Add");
		addButton.setFont(new Font("San Francisco", Font.BOLD, 15));
		addButton.setBounds(745, 199, 100, 35);
		addButton.setFocusable(false);
		addButton.setBorderPainted(false);
		addButton.setBackground(new Color(238, 238, 238));
		addButton.setPreferredSize(buttonSize);
		addButton.addActionListener(e -> {
			String task = inputTask.getText().trim();
			if (!task.isEmpty()) {
				taskList.add(task);
				addTaskToPanel(task, taskFunction);
				inputTask.setText("");
			}
		});
		taskFunction.add(addButton);

		editButton = new JButton("Edit");
		editButton.setFont(new Font("San Francisco", Font.BOLD, 15));
		editButton.setBounds(625, 199, 100, 35);
		editButton.setFocusable(false);
		editButton.setBorderPainted(false);
		editButton.setBackground(new Color(238, 238, 238));
		editButton.setPreferredSize(buttonSize);
		editButton.addActionListener(e -> EditMode());
		taskFunction.add(editButton);

		//delete all tasks
		deleteAllButton = new JButton("Clear All");
		deleteAllButton.setFont(new Font("San Francisco", Font.BOLD, 15));
		deleteAllButton.setBounds(470, 199, 110, 35);
		deleteAllButton.setFocusable(false);
		deleteAllButton.setBorderPainted(false);
		deleteAllButton.setForeground(Color.BLACK);
		deleteAllButton.setBackground(new Color(238, 238, 238));
		deleteAllButton.setVisible(false);
		deleteAllButton.addActionListener(e -> deleteAllTasks());
		taskFunction.add(deleteAllButton);

		// Scroll
		tasksScroll = new JPanel();
		tasksScroll.setLayout(null);
		tasksScroll.setBackground(new Color(84, 107, 127));

		JScrollPane scrollPanel = new JScrollPane(tasksScroll);
		scrollPanel.setBounds(75, 260, 800, 400);
		scrollPanel.setBorder(null);
		taskFunction.add(scrollPanel);
	}

	private void EditMode() {
		editMode = !editMode;
		if (editMode) {
			editButton.setText("Save");
			editButton.setBackground(new Color(238, 238, 238));
			editButton.setForeground(Color.BLACK);
			addButton.setEnabled(false);
			editButton.setBorderPainted(false);
			deleteAllButton.setVisible(true);
			showDeleteButtons();
		} else {
			editButton.setText("Edit");
			editButton.setBackground(new Color(238, 238, 238));
			editButton.setForeground(Color.BLACK);
			addButton.setEnabled(true);			
			deleteAllButton.setVisible(false);
			hideDeleteButtons();
		}
	}

	private void showDeleteButtons() {
		for (int i = 0; i < taskPanels.size(); i++) {
			JPanel taskPanel = taskPanels.get(i);

			boolean DeleteButton = false;
			for (int j = 0; j < taskPanel.getComponentCount(); j++) {
				if (taskPanel.getComponent(j) instanceof JButton) {
					JButton btn = (JButton) taskPanel.getComponent(j);
					if ("Delete".equals(btn.getText())) {
						DeleteButton = true;
						btn.setVisible(true);
						break;
					}
				}
			}

			if (!DeleteButton) {
				ImageIcon deleteicon = new ImageIcon("deleteicon.png");
				JButton deleteButton = new JButton(deleteicon);
		        deleteButton.setIconTextGap(10);
				deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
				deleteButton.setBounds(680, 0, 50, 50);
				deleteButton.setOpaque(false);
				deleteButton.setContentAreaFilled(false);
				deleteButton.setBorderPainted(false);
				deleteButton.setFocusable(false);

				final int taskIndex = i;
				deleteButton.addActionListener(e -> deleteTask(taskIndex));

				taskPanel.add(deleteButton);
			}
		}

		tasksScroll.revalidate();
		tasksScroll.repaint();
	}

	private void hideDeleteButtons() {
		for (JPanel taskPanel : taskPanels) {
			for (int i = 0; i < taskPanel.getComponentCount(); i++) {
				if (taskPanel.getComponent(i) instanceof JButton) {
					JButton btn = (JButton) taskPanel.getComponent(i);
					if ("Delete".equals(btn.getText())) {
						btn.setVisible(false);
						break;
					}
				}
			}
		}

		tasksScroll.revalidate();
		tasksScroll.repaint();
	}

	private void deleteTask(int taskIndex) {
		if (taskIndex >= 0 && taskIndex < taskList.size()) {
			taskList.remove(taskIndex);
			JPanel taskPanelToRemove = taskPanels.get(taskIndex);
			taskPanels.remove(taskIndex);

			tasksScroll.remove(taskPanelToRemove);

			repositionTasks();

			tasksScroll.setPreferredSize(new java.awt.Dimension(750, Math.max(380, (taskList.size() * 60))));
			tasksScroll.revalidate();
			tasksScroll.repaint();

			if (taskList.isEmpty()) {
				EditMode();
			}
		}
	}

	private void deleteAllTasks() {
		taskList.clear();
		taskPanels.clear();
		tasksScroll.removeAll();
		tasksScroll.setPreferredSize(new java.awt.Dimension(750, 380));
		tasksScroll.revalidate();
		tasksScroll.repaint();
		EditMode();
	}

	private void repositionTasks() {
		for (int i = 0; i < taskPanels.size(); i++) {
			JPanel taskPanel = taskPanels.get(i);
			int yPosition = i * 60;
			taskPanel.setBounds(5, yPosition, 770, 45);

			for (int j = 0; j < taskPanel.getComponentCount(); j++) {
				if (taskPanel.getComponent(j) instanceof JButton) {
					JButton btn = (JButton) taskPanel.getComponent(j);
					if ("Delete".equals(btn.getText())) {
						ActionListener[] listeners = btn.getActionListeners();
						for (ActionListener listener : listeners) {
							btn.removeActionListener(listener);
						}
						final int newIndex = i;
						btn.addActionListener(e -> deleteTask(newIndex));
						break;
					}
				}
			}
		}
	}

	private void addTaskToPanel(String task, JPanel taskFunction) {
		int taskCount = taskList.size() - 1;
		int addingTask = taskCount * 60;

		JPanel taskPanel = new JPanel();
		taskPanel.setBounds(5, addingTask, 770, 45);
		taskPanel.setBackground(new Color(0, 0, 0));
		taskPanel.setOpaque(false);
		taskPanel.setLayout(null);

		taskText = task;

		checkStatus = new JCheckBox();
		ImageIcon uncheckedIcon = new ImageIcon("checkboxunchecked.png");
        ImageIcon checkedIcon = new ImageIcon("checkbox.png");
		checkStatus.setLayout(null);
		checkStatus.setBounds(-10, 0, 50, 50);
		checkStatus.setOpaque(false);
		checkStatus.setIcon(uncheckedIcon);              
	    checkStatus.setSelectedIcon(checkedIcon);        
	    checkStatus.setOpaque(false);                    
	    checkStatus.setFocusPainted(false);              
	    checkStatus.setContentAreaFilled(false);         

		taskPanel.add(checkStatus);
		
		JLabel taskLabel = new JLabel(taskText);
		taskLabel.setFont(new Font("San Francisco", Font.PLAIN, 25));
		taskLabel.setForeground(Color.WHITE);
		taskLabel.setBounds(45, 10, 620, 25);
		taskPanel.add(taskLabel);
		
		Runnable updateFontStyle = () -> {
			Font baseFont = taskLabel.getFont();
			Map<TextAttribute, Object> attributes = new HashMap<>(baseFont.getAttributes());
			if (checkStatus.isSelected()) {
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
			} else {
				attributes.remove(TextAttribute.STRIKETHROUGH);
			}
			taskLabel.setFont(baseFont.deriveFont(attributes));
		};

		checkStatus.addActionListener(e -> updateFontStyle.run());

		taskLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				checkStatus.setSelected(!checkStatus.isSelected());
				updateFontStyle.run();
			}
		});

		taskPanels.add(taskPanel);
		tasksScroll.add(taskPanel);

		if (editMode) {
			JButton deleteButton = new JButton("Delete");
			deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
			deleteButton.setBounds(680, 10, 80, 25);
			deleteButton.setBackground(new Color(255, 69, 58));
			deleteButton.setForeground(Color.WHITE);
			deleteButton.setFocusable(false);

			final int taskIndex = taskPanels.size() - 1;
			deleteButton.addActionListener(e -> deleteTask(taskIndex));

			taskPanel.add(deleteButton);
		}

		tasksScroll.setPreferredSize(new java.awt.Dimension(750, Math.max(380, (taskList.size() * 60))));
		tasksScroll.revalidate();
		tasksScroll.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}