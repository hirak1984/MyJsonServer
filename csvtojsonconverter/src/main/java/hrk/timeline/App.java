package hrk.timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import hrk.timeline.pojo.TestFormat;


public final class App {
	static final File directory = new File("C:\\tmp\\jsoncsv");

	/**
	 * Says hello to the world.
	 * SOURCE_FILE TARGET_FILE OPERATION
	 * 
	 * @param args
	 *            The arguments of the program.
	 * @throws IOException
	 */
	public static void main(String[] args) {
		if(args==null || args.length<2) {
			String message = String.join(
					System.getProperty("line.separator"),
					 "Please provide valid arguments",
			        "*** Usage *** ",
			        "App \"SOURCE_FILE_ABS_PATH\" \"TARGET_FILE_ABS_PATH\" <OPERATION>(Optional.CSV2JSON(default) or JSON2CSV)",
			        "Example-1 - ",
			        "JSON_FILE_ABS_PATH CSV_FILE_ABS_PATH JSON2CSV",
			        "Example- 2 - ",
			        "CSV_FILE_PATH JSON_FILE_PATH CSV2JSON"
			);
			
			throw new IllegalArgumentException(message);
		}
		if(args.length==2) {
			args = new String[]{args[0],args[1],"CSV2JSON"};
		}
		File sourceFile = new File(args[0]);
		File targetFile = new File(args[1]);
		String ops = args[2];
		String message = String.join(
				System.getProperty("line.separator"),
				 "Input parameters : ",
		        "SOURCE_FILE_ABS_PATH = "+sourceFile.getAbsolutePath(),
		        "TARGET_FILE_ABS_PATH = "+targetFile.getAbsolutePath(),
		        "Operation = "+ops
		);
		System.out.println(message);
		System.out.println("Start");
		try(FileReader srcFileReader = new FileReader(sourceFile);
			FileWriter targetFileWriter = new FileWriter(targetFile)	){
			if(ops.equals("CSV2JSON")) {
				TestFormat testFormat = CSV_JSON_ADAPTER.fromCSV(srcFileReader);
				CSV_JSON_ADAPTER.asJSONInto(testFormat, targetFileWriter);
			}else if(ops.equals("JSON2CSV")) {
				TestFormat testFormat = CSV_JSON_ADAPTER.fromJSON(srcFileReader);
				CSV_JSON_ADAPTER.asCSVInto(testFormat, targetFileWriter);
			}else {
				throw new IllegalArgumentException("Invalid operation requested :" + ops);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		System.out.println("Done");	
	}
}
