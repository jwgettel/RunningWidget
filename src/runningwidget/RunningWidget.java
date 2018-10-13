package runningwidget;

import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RunningWidget {
	
	public static String splitCalculator(String time, Double distance) {
		
		// Variables
		String split = "";
		double splt;	
		int hrs;
		int mins;
		int secs;
		int seconds;
		
		// Calculate Split Time
		// Time input is mm:ss
		if(time.length() == 5) {
			
			mins = Integer.parseInt(time.substring(0,2));
			secs = Integer.parseInt(time.substring(3,5));
			seconds = mins * 60 + secs;

			splt = seconds / distance;
			mins = (int) (splt / 60);
			secs = (int) (splt % 60);

			split = String.valueOf(mins) + ":" + String.format("%02d", secs + 1);

		// Calculate Split Time
		// Time input is h:mm:ss	
		} else {
			
			hrs = Integer.parseInt(time.substring(0,1));
			mins = Integer.parseInt(time.substring(2,4));
			secs = Integer.parseInt(time.substring(5,7));
			seconds = hrs * 3600 + mins * 60 + secs;
			
			splt = seconds / distance;
			mins = (int) (splt / 60);
			secs = (int) (splt % 60);
			
			split = String.valueOf(mins) + ":" + String.format("%02d", secs + 1);
		
		}

		return split;
	}
		
	public static void recordData(Double distance, String time, String split, String file) {
		try {

			// Open output file
			FileInputStream fsIP = new FileInputStream(new File(file));  
			XSSFWorkbook wb = new XSSFWorkbook(fsIP);
			XSSFSheet worksheet = wb.getSheetAt(0); 
				
			// Find first empty row
			int nextRow = 0;
			while(worksheet.getRow(nextRow) != null) {
				nextRow++;
			}

			// Initiate new cells
			Cell cell_0 = null; 
			Cell cell_1 = null;
			Cell cell_2 = null;
			Cell cell_3 = null;
				
			worksheet.createRow(nextRow);
			cell_0 = worksheet.getRow(nextRow).createCell(0);   
			cell_1 = worksheet.getRow(nextRow).createCell(1);
			cell_2 = worksheet.getRow(nextRow).createCell(2);
			cell_3 = worksheet.getRow(nextRow).createCell(3);

			
			DateFormat df = new SimpleDateFormat("MMM-dd");
			Date today = Calendar.getInstance().getTime();
			String date = df.format(today);
				
			// Insert data into new cells
			cell_0.setCellValue(date);
			cell_1.setCellValue(distance);
			cell_2.setCellValue(time);
			cell_3.setCellValue(split);
				
			fsIP.close(); 
			
			// Save data to output file
			FileOutputStream output_file = new FileOutputStream(new File(file));  		
			wb.write(output_file);
			output_file.close();
	            
		} catch (FileNotFoundException e) {
	    	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}