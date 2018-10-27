package runningwidget;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Properties;

public class FilePath {
	
	static String configFilePath = "src/config.properties";
	// Save output file path 
	// This will be the default output file path until its changed
	public static void saveFilePath(String filePath) {

		Properties prop = new Properties();
		OutputStream output = null;
		
		try {
			output = new FileOutputStream(configFilePath);
			prop.setProperty("filepath",  filePath);
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Retrieve output file path from filepath.txt
	// This is the the location of the file where the data will be recorded
	public static String retrieveFilePath() {

		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(configFilePath);
			prop.load(input);
			return prop.getProperty("filepath");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
}
