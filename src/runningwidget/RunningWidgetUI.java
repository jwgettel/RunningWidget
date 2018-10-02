package runningwidget;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

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
	 */
	public RunningWidgetUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Main GUI Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Distance Label
		JLabel lblDistance = new JLabel("Distance:");
		lblDistance.setLabelFor(distanceInput);
		lblDistance.setBounds(5, 5, 61, 16);
		frame.getContentPane().add(lblDistance);
		
		// Distance Input
		distanceInput = new JTextField();
		distanceInput.setBounds(5, 21, 137, 32);
		frame.getContentPane().add(distanceInput);
		distanceInput.setColumns(10);
		
		// Time Label
		JLabel lblTime = new JLabel("Time: (x:xx:xx) or (xx:xx)");
		lblTime.setLabelFor(timeInput);
		lblTime.setBounds(5, 63, 189, 16);
		frame.getContentPane().add(lblTime);
		
		// Time Input
		timeInput = new JTextField();
		timeInput.setColumns(10);
		timeInput.setBounds(5, 84, 137, 32);
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
				Pattern r = Pattern.compile("((\\d+:)?([0...5]))?\\d:[0...5]\\d");
				Matcher m = r.matcher(time);
				if (m.find()) {
					String split = RunningWidget.splitCalculator(time, dist);
					String file = outputFile.getText();
					RunningWidget.recordData(dist, time, split, file);
					splitOutput.setText(split);
				} else {
					timeInput.setText("");
				}
			}
		});
		btnRecord.setBounds(167, 87, 117, 29);
		frame.getContentPane().add(btnRecord);

		// Split Time Label
		JLabel lblSplit = new JLabel("Split:");
		lblSplit.setBounds(5, 128, 61, 16);
		frame.getContentPane().add(lblSplit);
		
		// Split Time Output
		splitOutput = new JTextField();
		splitOutput.setBounds(5, 147, 137, 32);
		frame.getContentPane().add(splitOutput);
		splitOutput.setColumns(10);
		
		// Output File Label
		JLabel lblOutputFile = new JLabel("Output File:");
		lblOutputFile.setBounds(5, 248, 74, 16);
		frame.getContentPane().add(lblOutputFile);

		// Output File Input
		outputFile = new JTextField();
		outputFile.setBounds(82, 240, 362, 32);
		frame.getContentPane().add(outputFile);
		outputFile.setColumns(10);
		
	}
}
