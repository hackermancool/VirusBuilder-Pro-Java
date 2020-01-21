package com.hackermancool.main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	private JFrame virusBuilderProJava;
	private JTextField field1TextField;
	private JTextField field2TextField;
	
	private String batchFile;
	private String shellFile;
	private JTextField batchPathTextField;
	private JTextField shellPathTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.virusBuilderProJava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		virusBuilderProJava = new JFrame();
		virusBuilderProJava.setType(Type.UTILITY);
		virusBuilderProJava.setTitle("VirusBuilder Pro Java");
		virusBuilderProJava.setBounds(100, 100, 900, 385);
		virusBuilderProJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		virusBuilderProJava.getContentPane().setLayout(null);
		
		batchFile = "@echo off\ncls\n";
		shellFile = "";
		
		JLabel titleLabel = new JLabel("VirusBuilder Pro Java");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 72));
		titleLabel.setBounds(10, 11, 864, 61);
		virusBuilderProJava.getContentPane().add(titleLabel);
		
		JLabel sloganLabel = new JLabel("Effective cross-platform virus creation.");
		sloganLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		sloganLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sloganLabel.setBounds(285, 83, 589, 17);
		virusBuilderProJava.getContentPane().add(sloganLabel);
		
		JList<String> componentList = new JList<String>();
		componentList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 6294122823847344145L;
			String[] values = new String[] {"Title", "Colour (Windows Only)", "Echo Text to Screen", "Shutdown", "Restart", "Hibernate", "Wait", "Open CMD (Windows Only)", "Open Notepad/Default Text Editor"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		componentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		componentList.setBounds(25, 110, 250, 172);
		virusBuilderProJava.getContentPane().add(componentList);
		
		JLabel selectComponentLabel = new JLabel("Select a component to add to virus:");
		selectComponentLabel.setBounds(25, 86, 250, 15);
		virusBuilderProJava.getContentPane().add(selectComponentLabel);
		
		JLabel field1Label = new JLabel("Field 1 Label");
		field1Label.setBounds(285, 110, 589, 14);
		field1Label.setVisible(false);
		virusBuilderProJava.getContentPane().add(field1Label);
		
		field1TextField = new JTextField();
		field1TextField.setBounds(285, 126, 589, 20);
		field1TextField.setVisible(false);
		virusBuilderProJava.getContentPane().add(field1TextField);
		field1TextField.setColumns(10);
		
		JLabel field2Label = new JLabel("Field 2 Label");
		field2Label.setBounds(285, 150, 589, 14);
		field2Label.setVisible(false);
		virusBuilderProJava.getContentPane().add(field2Label);
		
		field2TextField = new JTextField();
		field2TextField.setBounds(285, 166, 589, 20);
		field2TextField.setVisible(false);
		virusBuilderProJava.getContentPane().add(field2TextField);
		field2TextField.setColumns(10);
		
		JLabel extraInfoLabel = new JLabel("Extra Info");
		extraInfoLabel.setBounds(285, 197, 589, 14);
		extraInfoLabel.setVisible(false);
		virusBuilderProJava.getContentPane().add(extraInfoLabel);
		
		JButton addComponentButton = new JButton("Add Component");
		addComponentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check what component to add
				switch(componentList.getSelectedIndex()) {
				case 0:
					// Add a title to batchFile and shellFile
					batchFile += "title " + field1TextField.getText() + "\n";
					shellFile += "export PS1='\\[\\e]0;" + field1TextField.getText() + ":\\w\\a\\]\\n\\[\\e[32m\\]\\u@\\h \\[\\e[33m\\]~\\w\\[\\e[0m\\]\\n\\$'\n";
					break;
				case 1:
					// Add a colour to batchFile
					batchFile += "color " + field1TextField.getText() + field2TextField.getText() + "\n";
					break;
				case 2:
					// Add a text echo to batchFile and shellFile
					batchFile += "echo \"" + field1TextField.getText() + "\"\n";
					shellFile += "echo \"" + field1TextField.getText() + "\"\n";
					break;
				case 3:
					// Add a shutdown to batchFile and shellFile
					batchFile += "shutdown /s /f /t " + field2TextField.getText() + " /c \"" + field1TextField.getText() + "\"\n";
					shellFile += "shutdown || poweroff || halt -p\n";
					break;
				case 4:
					// Add a restart to batchFile and shellFile
					batchFile += "shutdown /r /f /t " + field2TextField.getText() + " /c \"" + field1TextField.getText() + "\"\n";
					shellFile += "reboot || halt --reboot || restart\n";
					break;
				case 5:
					// Add a hibernate to batchFile and shellFile
					batchFile += "shutdown /h /f /t " + field2TextField.getText() + " /c \"" + field1TextField.getText() + "\"\n";
					shellFile += "halt || pm-suspend\n";
					break;
				case 6:
					// Add a wait to batchFile and shellFile
					batchFile += "sleep " + field1TextField.getText() + "\n";
					shellFile += "sleep " + field1TextField.getText() + "\n";
				case 7:
					// Add a cmd open to batchFile
					batchFile += "start cmd\n";
					break;
				case 8:
					// Add a notepad open to batchFile
					batchFile += "notepad " + field1TextField.getText() + "\n";
					break;
				}
				addComponentButton.setVisible(false);
				field1Label.setVisible(false);
				field1TextField.setVisible(false);
				field2Label.setVisible(false);
				field2TextField.setVisible(false);
				extraInfoLabel.setVisible(false);
				System.out.println("Added: " + componentList.getSelectedValue());
			}
		});
		addComponentButton.setFont(new Font("Tahoma", Font.PLAIN, 32));
		addComponentButton.setBounds(285, 222, 589, 51);
		addComponentButton.setVisible(false);
		virusBuilderProJava.getContentPane().add(addComponentButton);
		
		JButton selectComponentButton = new JButton("Select");
		// On click load the rest of the menu for the selected option
		selectComponentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset field values
				field1TextField.setText("");
				field2TextField.setText("");
				// Hide all fields
				field1Label.setVisible(false);
				field1TextField.setVisible(false);
				field2Label.setVisible(false);
				field2TextField.setVisible(false);
				extraInfoLabel.setVisible(false);
				// Make sure that there is a valid component selected
				if(componentList.getSelectedValue() != null && componentList.getSelectedValue() != "") {
					System.out.println("Selected: " + componentList.getSelectedValue());
					addComponentButton.setVisible(true);
				} else {
					// Hide the component button if nothing is selected
					addComponentButton.setVisible(false);
				}
				switch(componentList.getSelectedIndex()) {
				case 0:
					// Set field labels for title
					field1Label.setText("Title:");
					// Set extra info for title
					extraInfoLabel.setText("Warning: Won't always work for shell file.");
					// Show fields required for a title
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					extraInfoLabel.setVisible(true);
					break;
				case 1:
					// Set field labels for colour
					field1Label.setText("Background colour:");
					field2Label.setText("Text colour:");
					// Set extra info
					extraInfoLabel.setText("Colours should be written as either a number from 0-9 or a letter from A-F.");
					// Show fields required for colour
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					field2Label.setVisible(true);
					field2TextField.setVisible(true);
					extraInfoLabel.setVisible(true);
					break;
				case 2:
					// Set field labels for echo text
					field1Label.setText("Text:");
					// Show fields required for echo text
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					break;
				case 3:
				case 4:
				case 5:
					// Set field labels for shutdown/restart/hibernate
					field1Label.setText("Text (Windows Only):");
					field2Label.setText("Timer in seconds (Windows Only):");
					// Show fields required for shutdown/restart/hibernate
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					field2Label.setVisible(true);
					field2TextField.setVisible(true);
					break;
				case 6:
					// Set field labels for wait
					field1Label.setText("Wait duration in seconds:");
					// Show fields required for wait
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					break;
				case 7:
					// Set extra info for new cmd
					extraInfoLabel.setText("There are no arguments for this virus component.");
					// Show required fields for new cmd
					extraInfoLabel.setVisible(true);
					break;
				case 8:
					// Set field labels for notepad
					field1Label.setText("File to open (Optional):");
					// Set extra info for notepad
					extraInfoLabel.setText("Note: File will be opened relative to virus location, not VirusBuilder Pro Java.");
					// Show required fields for notepad
					field1Label.setVisible(true);
					field1TextField.setVisible(true);
					extraInfoLabel.setVisible(true);
					break;
				}
			}
		});
		selectComponentButton.setFont(new Font("Tahoma", Font.PLAIN, 32));
		selectComponentButton.setBounds(25, 284, 250, 50);
		virusBuilderProJava.getContentPane().add(selectComponentButton);
		
		JLabel creditLabel = new JLabel("Created by hackermancool under GPL-3.0");
		creditLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		creditLabel.setBounds(285, 328, 589, 15);
		virusBuilderProJava.getContentPane().add(creditLabel);
		
		batchPathTextField = new JTextField();
		batchPathTextField.setHorizontalAlignment(SwingConstants.TRAILING);
		batchPathTextField.setText("virusbuilder-pro-java");
		batchPathTextField.setBounds(362, 273, 394, 20);
		virusBuilderProJava.getContentPane().add(batchPathTextField);
		batchPathTextField.setColumns(10);
		
		shellPathTextField = new JTextField();
		shellPathTextField.setHorizontalAlignment(SwingConstants.TRAILING);
		shellPathTextField.setText("virusbuilder-pro-java");
		shellPathTextField.setColumns(10);
		shellPathTextField.setBounds(362, 296, 394, 20);
		virusBuilderProJava.getContentPane().add(shellPathTextField);
		
		JLabel batExtensionLabel = new JLabel(".bat");
		batExtensionLabel.setBounds(755, 278, 32, 14);
		virusBuilderProJava.getContentPane().add(batExtensionLabel);
		
		JLabel shellExtensionLabel = new JLabel(".sh");
		shellExtensionLabel.setBounds(757, 301, 19, 14);
		virusBuilderProJava.getContentPane().add(shellExtensionLabel);
		
		JLabel batchPathLabel = new JLabel("Batch Path:");
		batchPathLabel.setBounds(285, 276, 77, 14);
		virusBuilderProJava.getContentPane().add(batchPathLabel);
		
		JLabel shellPathLabel = new JLabel("Shell Path:");
		shellPathLabel.setBounds(285, 299, 77, 14);
		virusBuilderProJava.getContentPane().add(shellPathLabel);
		
		JButton createVirusButton = new JButton("Build Virus");
		createVirusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintWriter batchWriter = new PrintWriter(new FileWriter(batchPathTextField.getText() + ".bat"));
					PrintWriter shellWriter = new PrintWriter(new FileWriter(shellPathTextField.getText() + ".sh"));
					
					batchWriter.print(batchFile);
					shellWriter.print(shellFile);
					
					batchWriter.close();
					shellWriter.close();
				} catch (IOException e1) {
					extraInfoLabel.setVisible(true);
					extraInfoLabel.setText("ERROR: Failed to write virus files.");
					e1.printStackTrace();
				}
			}
		});
		createVirusButton.setBounds(777, 273, 97, 44);
		virusBuilderProJava.getContentPane().add(createVirusButton);
	}
}
