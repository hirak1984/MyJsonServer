package hrk.timeline;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.google.gson.Gson;

import hrk.timeline.pojo.Data;
import hrk.timeline.pojo.TestFormat;

public class CSV_JSON_ADAPTER {

	public static void asJSONInto(TestFormat testFormat,final Writer writer) {
		final Gson gson = new Gson();
		gson.toJson(testFormat, writer);
	}

	public static  void asCSVInto(TestFormat testFormat,final Writer writer) {
		asCSVInto(testFormat,",", new WriterDecoratorUncheckedException(writer));
	}

	public static  void asCSVInto(TestFormat testFormat,final String delimiter, final Writer writer) {
		asCSVInto(testFormat,delimiter, new WriterDecoratorUncheckedException(writer));
	}

	public static void asCSVInto(final TestFormat testFormat,final String delimiter, final WriterDecoratorUncheckedException writer) {
		Consumer<String[]> arrayToDelimitedString = new Consumer<String[]>() {
			@Override
			public void accept(String[] columns) {
				String[] colSeparator = new String[] { "" };
				Arrays.stream(columns).forEach(column -> {
					writer.write(colSeparator[0]);
					writer.write(column);
					if (colSeparator[0] == "") {
						colSeparator[0] = delimiter;
					}
				});
				writer.write(System.lineSeparator());
			}

		};
		arrayToDelimitedString.accept(new String[] { "dateText", "title", "subtitle", "texts" });

		testFormat.getData().stream().forEach(d -> {
			d.getTexts().stream().forEach(text -> arrayToDelimitedString
					.accept(new String[] { d.getDateText(), d.getTitle(), d.getSubtitle(), text }));
		});

	}

	public static TestFormat fromJSON(final Reader reader) {
		final Gson gson = new Gson();
		TestFormat testFormat = gson.fromJson(reader, TestFormat.class);
		return testFormat;
	}

	public static TestFormat fromCSV(final Reader reader) {

		return fromCSV(',', reader);
	}

	public static TestFormat fromCSV(final char delimiter, final Reader reader) {
		TestFormat testFormat = new TestFormat();
		try {
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().withDelimiter(delimiter).parse(reader);
			List<Data> dataList = new LinkedList<>();
			Data data = null;
			for (CSVRecord record : records) {
				String dateText = record.get("dateText");
				String title = record.get("title");
				String subtitle = record.get("subtitle");
				String text = record.get("texts");
				if (data==null) {
					data = new Data(dateText, title, subtitle);
					data.setText(text);
				} else {
					if (data.getDateText().equals(dateText) && data.getTitle().equals(title)
							&& data.getSubtitle().equals(subtitle)) {
						data.setText(text);
					} else {
						dataList.add(data);
						data = new Data(dateText, title, subtitle);
						data.setText(text);
					}
				}

			}
			dataList.add(data);
			testFormat.setData(dataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testFormat;

	}
}