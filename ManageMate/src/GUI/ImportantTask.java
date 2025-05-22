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

import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.HashMap;

public class ImportantTask extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField inputImportant;
	private JPanel ImportantScroll;
	private JCheckBox checkStatus;
	private String ImportantText;
	private ArrayList<String> ImportantList = new ArrayList<>();
	private ArrayList<JPanel> ImportantPanels = new ArrayList<>();
	private boolean editMode = false;
	private JButton editButton;
	private JButton addButton;
	private JButton deleteAllButton, saveButton;

	public ImportantTask(JPanel ImportantFunction) {
		// ADDING TASK
		inputImportant = new JTextField(20);
		inputImportant.setBounds(95, 124, 750, 65);
		inputImportant.setOpaque(true);
		inputImportant.setBorder(null);
		inputImportant.setFont(new Font("San Francisco", Font.PLAIN, 16));
		ImportantFunction.add(inputImportant);

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
			String Important = inputImportant.getText().trim();
			if (!Important.isEmpty()) {
				ImportantList.add(Important);
				addTaskToPanel(Important, ImportantFunction);
				inputImportant.setText("");
			}
		});
		ImportantFunction.add(addButton);

		editButton = new JButton("Edit");
		editButton.setFont(new Font("San Francisco", Font.BOLD, 15));
		editButton.setBounds(625, 199, 100, 35);
		editButton.setFocusable(false);
		editButton.setBorderPainted(false);
		editButton.setBackground(new Color(238, 238, 238));
		editButton.setPreferredSize(buttonSize);
		editButton.addActionListener(e -> EditMode());
		ImportantFunction.add(editButton);

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
		ImportantFunction.add(deleteAllButton);

		// Scroll
		ImportantScroll = new JPanel();
		ImportantScroll.setLayout(null);
		ImportantScroll.setBackground(new Color(140, 25, 25));

		JScrollPane scrollPanel = new JScrollPane(ImportantScroll);
		scrollPanel.setBounds(75, 260, 800, 400);
		scrollPanel.setBorder(null);
		ImportantFunction.add(scrollPanel);
	}

	private void EditMode() {
		editMode = !editMode;
		if (editMode) {
			editButton.setText("Save");
			editButton.setBackground(new Color(238, 238, 238));
			editButton.setBorderPainted(false);
			editButton.setForeground(Color.BLACK);
			addButton.setEnabled(false);
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
		for (int i = 0; i < ImportantPanels.size(); i++) {
			JPanel taskPanel = ImportantPanels.get(i);

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

		ImportantScroll.revalidate();
		ImportantScroll.repaint();
	}

	private void hideDeleteButtons() {
		for (JPanel taskPanel : ImportantPanels) {
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

		ImportantScroll.revalidate();
		ImportantScroll.repaint();
	}

	private void deleteTask(int ImportantIndex) {
		if (ImportantIndex >= 0 && ImportantIndex < ImportantList.size()) {
			ImportantList.remove(ImportantIndex);
			JPanel ImportantPanelToRemove = ImportantPanels.get(ImportantIndex);
			ImportantPanels.remove(ImportantIndex);

			ImportantScroll.remove(ImportantPanelToRemove);

			repositionTasks();

			ImportantScroll.setPreferredSize(new java.awt.Dimension(750, Math.max(380, (ImportantList.size() * 60))));
			ImportantScroll.revalidate();
			ImportantScroll.repaint();

			if (ImportantList.isEmpty()) {
				EditMode();
			}
		}
	}

	private void deleteAllTasks() {
		ImportantList.clear();
		ImportantPanels.clear();
		ImportantScroll.removeAll();
		ImportantScroll.setPreferredSize(new java.awt.Dimension(750, 380));
		ImportantScroll.revalidate();
		ImportantScroll.repaint();
		EditMode();
	}

	private void repositionTasks() {
		for (int i = 0; i < ImportantPanels.size(); i++) {
			JPanel ImportantPanel = ImportantPanels.get(i);
			int yPosition = i * 60;
			ImportantPanel.setBounds(5, yPosition, 770, 45);

			for (int j = 0; j < ImportantPanel.getComponentCount(); j++) {
				if (ImportantPanel.getComponent(j) instanceof JButton) {
					JButton btn = (JButton) ImportantPanel.getComponent(j);
					if ("Delete".equals(btn.getText())) {
						ActionListener[] listeners = btn.getActionListeners();
						for (ActionListener listener : listeners) {
							btn.removeActionListener( listener);
						}
						final int newIndex = i;
						btn.addActionListener(e -> deleteTask(newIndex));
						break;
					}
				}
			}
		}
	}

	private void addTaskToPanel(String Important, JPanel ImportantFunction) {
		int ImportantCount = ImportantList.size() - 1;
		int addingImportant = ImportantCount * 60;

		JPanel ImportantPanel = new JPanel();
		ImportantPanel.setBounds(5, addingImportant, 770, 45);
		ImportantPanel.setBackground(new Color(0, 0, 0));
		ImportantPanel.setOpaque(false);
		ImportantPanel.setLayout(null);

		ImportantText = Important;

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
		ImportantPanel.add(checkStatus);
		
		JLabel ImportantLabel = new JLabel(ImportantText);
		ImportantLabel.setFont(new Font("San Francisco", Font.PLAIN, 25));
		ImportantLabel.setForeground(Color.WHITE);
		ImportantLabel.setBounds(45, 10, 620, 25);
		ImportantPanel.add(ImportantLabel);
		
		Runnable updateFontStyle = () -> {
			Font baseFont = ImportantLabel.getFont();
			Map<TextAttribute, Object> attributes = new HashMap<>(baseFont.getAttributes());
			if (checkStatus.isSelected()) {
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
			} else {
				attributes.remove(TextAttribute.STRIKETHROUGH);
			}
			ImportantLabel.setFont(baseFont.deriveFont(attributes));
		};

		checkStatus.addActionListener(e -> updateFontStyle.run());

		ImportantLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				checkStatus.setSelected(!checkStatus.isSelected());
				updateFontStyle.run();
			}
		});

		ImportantPanels.add(ImportantPanel);
		ImportantScroll.add(ImportantPanel);

		if (editMode) {
			JButton deleteButton = new JButton("Delete");
			deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
			deleteButton.setBounds(680, 10, 80, 25);
			deleteButton.setBackground(new Color(255, 69, 58));
			deleteButton.setForeground(Color.WHITE);
			deleteButton.setFocusable(false);

			final int taskIndex = ImportantPanels.size() - 1;
			deleteButton.addActionListener(e -> deleteTask(taskIndex));

			ImportantPanel.add(deleteButton);
		}

		ImportantScroll.setPreferredSize(new java.awt.Dimension(750, Math.max(380, (ImportantList.size() * 60))));
		ImportantScroll.revalidate();
		ImportantScroll.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}