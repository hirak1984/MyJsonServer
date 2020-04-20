package hrk.timeline;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import hrk.timeline.pojo.Data;
import hrk.timeline.pojo.TestFormat;

/**
 * Unit test for simple App.
 */
public class AppTest {
	static final File directory = new File("C:\\tmp\\jsoncsv");

	@Test
	public void testDataLoad() {
		try {
			TestFormat testFormatJSON = CSV_JSON_ADAPTER.fromJSON(new FileReader(directory));
			TestFormat testFormatCSV = CSV_JSON_ADAPTER.fromCSV(new FileReader(directory));
			assertTrue(testFormatJSON.equals(testFormatCSV));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDataOutput() {
		try {
			TestFormat testFormat = new TestFormat();
			List<Data> dataList = new LinkedList<Data>();
			for (int i = 0; i < 5; i++) {
				Data data = new Data("dateText" + i, "title" + i, "subtitle" + i);
				data.setText("texta" + i);
				data.setText("textb" + i);
				dataList.add(data);
			}
			testFormat.setData(dataList);
			try (Writer jsonW = new StringWriter(); Writer csvW = new StringWriter()) {
				CSV_JSON_ADAPTER.asCSVInto(testFormat, csvW);
				CSV_JSON_ADAPTER.asJSONInto(testFormat, jsonW);
				assertTrue(true);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
