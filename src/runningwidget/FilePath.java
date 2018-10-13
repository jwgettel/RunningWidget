package runningwidget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePath {
	
	// 
	public static void saveFilePath(String filePath) throws IOException {
		
		FileWriter write = new FileWriter("src/filepath.txt");
		PrintWriter print_line = new PrintWriter(write);
		print_line.printf("%s" + "%n", filePath);
		print_line.close();
		
	}
	
	// 
	public static String retrieveFilePath() throws FileNotFoundException {

		try {
			File file = new File("src/filepath.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String filePath;
			
			while ((filePath = bufferedReader.readLine()) != null) {
				stringBuffer.append(filePath);
			}
			fileReader.close();
			return stringBuffer.toString();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "";
	}
}
