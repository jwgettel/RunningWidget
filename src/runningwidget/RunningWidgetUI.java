package runningwidget;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
		lblTime.setBounds(177, 5, 189, 16);
		frame.getContentPane().add(lblTime);
		
		// Time Input
		timeInput = new JTextField();
		timeInput.setColumns(10);
		timeInput.setBounds(177, 21, 137, 32);
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
				if (m.find()) {
					String split = RunningWidget.splitCalculator(time, dist);
					String file = outputFile.getText();
					RunningWidget.recordData(dist, time, split, file);
					
					splitOutput.setText(split);
					try {
						FilePath.saveFilePath(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					timeInput.setText("");
				}
			}
		});
		btnRecord.setBounds(5, 61, 117, 29);
		frame.getContentPane().add(btnRecord);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		panel.setBounds(5, 6, 439, 95);
		frame.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		panel_1.setBounds(5, 113, 439, 29);
		frame.getContentPane().add(panel_1);
		
				// Split Time Label
				JLabel lblSplit = new JLabel("Split:");
				lblSplit.setHorizontalAlignment(SwingConstants.LEFT);
				panel_1.add(lblSplit);
				
				// Split Time Output
				splitOutput = new JTextField();
				panel_1.add(splitOutput);
				splitOutput.setColumns(10);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
				panel_2.setBounds(5, 154, 439, 36);
				frame.getContentPane().add(panel_2);
				
				// Output File Label
				JLabel lblOutputFile = new JLabel("Output File:");
				lblOutputFile.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblOutputFile);
				
				// Output File Input
				outputFile = new JTextField(FilePath.retrieveFilePath());
				panel_2.add(outputFile);
				outputFile.setColumns(10);
		
	}
}
