package runningwidget;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class RunningWidgetUI {

	private JFrame frame;
	private JTextField distanceInput;
	private JTextField timeInput;
	private JTextField splitOutput;
	private JTextField outputFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
				
					RunningWidgetUI window = new RunningWidgetUI();
					window.frame.setVisible(true);
				
				} catch (Exception e) {
				
					e.printStackTrace();
				
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws FileNotFoundException 
	 */
	public RunningWidgetUI() throws FileNotFoundException {
		
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws FileNotFoundException 
	 */
	private void initialize() throws FileNotFoundException {
		
		// Main GUI Frame
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setBounds(100, 100, 370, 195);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// INPUT PANEL
		//************************
		// Distance Label
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblDistance.setLabelFor(distanceInput);
		lblDistance.setBounds(10, 10, 60, 15);
		frame.getContentPane().add(lblDistance);
		
		// Distance Input
		distanceInput = new JTextField();
		distanceInput.setBounds(10, 25, 150, 30);
		frame.getContentPane().add(distanceInput);
		distanceInput.setColumns(10);
		
		// Time Label
		JLabel lblTime = new JLabel("Time: (x:xx:xx) or (xx:xx)");
		lblTime.setLabelFor(timeInput);
		lblTime.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblTime.setBounds(175, 10, 180, 15);
		frame.getContentPane().add(lblTime);
		
		// Time Input
		timeInput = new JTextField();
		timeInput.setColumns(10);
		timeInput.setBounds(175, 25, 150, 30);
		frame.getContentPane().add(timeInput);
		
		// Record Data Button
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Get Inputs
				String distance = distanceInput.getText();
				Double dist = Double.parseDouble(distance);
				String time = timeInput.getText();
				
				// Validate Inputs
				Pattern r = Pattern.compile("((\\d+:)?([0-5]))?\\d:[0-5]\\d");
				Matcher m = r.matcher(time);
				String file = outputFile.getText();
				File outputLocation = new File(file);

				// If file doesn't exists then clear field and do nothing
				if (!outputLocation.exists()) {
				
					outputFile.setText("");
				
				// If time input isn't in correct format clear field and do nothing
				} else if(!m.find()) {
					
					timeInput.setText("");
					
				} else {

					String split = RunningWidget.splitCalculator(time, dist);
					RunningWidget.recordData(dist, time, split, file);
					splitOutput.setText(split);
					try {
						
						FilePath.saveFilePath(file);
					
					} catch (IOException e1) {
					
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
					}
				}
			}
		});
		btnRecord.setBounds(10, 55, 100, 25);
		frame.getContentPane().add(btnRecord);
				
		// SPLIT TIME
		//******************
		// Split Time Label
		JLabel lblSplit = new JLabel("Split:");
		lblSplit.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSplit.setBounds(10, 95, 35, 25);
		frame.getContentPane().add(lblSplit);
						
		// Split Time Output
		splitOutput = new JTextField();
		splitOutput.setBounds(85, 95, 130, 25);
		frame.getContentPane().add(splitOutput);
		splitOutput.setColumns(10);
		lblSplit.setLabelFor(splitOutput);

		
		// OUTPUT FILE PANEL
		//********************
		// Output File Label
		JLabel lblOutputFile = new JLabel("Output File:");
		lblOutputFile.setBackground(new Color(123, 104, 238));
		lblOutputFile.setBounds(10, 135, 75, 25);
		lblOutputFile.setFont(new Font("Dialog", Font.PLAIN, 13));
		frame.getContentPane().add(lblOutputFile);
						
		// Output File Input
		outputFile = new JTextField(FilePath.retrieveFilePath());
		outputFile.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		outputFile.setBounds(85, 135, 270, 25);
		frame.getContentPane().add(outputFile);
		outputFile.setColumns(10);
		
		
		// PANELS
		//******************************
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		inputPanel.setBackground(new Color(30, 144, 255));
		inputPanel.setBounds(5, 5, 360, 80);
		frame.getContentPane().add(inputPanel);
		
		JPanel splitPanel = new JPanel();
		splitPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		splitPanel.setBackground(new Color(255, 140, 0));
		splitPanel.setBounds(5, 90, 360, 35);
		frame.getContentPane().add(splitPanel);
		
		JPanel outputFilePanel = new JPanel();
		outputFilePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		outputFilePanel.setBackground(new Color(50, 205, 50));
		outputFilePanel.setBounds(5, 130, 360, 35);
		frame.getContentPane().add(outputFilePanel);

	}
}
