package hrk.timeline;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import hrk.timeline.pojo.TestFormat;


public final class App {
	static final File directory = new File("C:\\tmp\\jsoncsv");

	/**
	 * Says hello to the world.
	 * 
	 * @param args
	 *            The arguments of the program.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileReader csvSourceFile = new FileReader(new File(directory, "data.csv"));
		TestFormat testFormat = CSV_JSON_ADAPTER.fromCSV(csvSourceFile);
		FileWriter jsonTargetFile = new FileWriter(new File(directory, "data.json"));
		CSV_JSON_ADAPTER.asJSONInto(testFormat, jsonTargetFile);
		System.out.println("Done");
	}
}
