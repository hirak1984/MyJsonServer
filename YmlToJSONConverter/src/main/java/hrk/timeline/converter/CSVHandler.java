package main.java.hrk.timeline.converter;

import java.io.Reader;
import java.io.Writer;

import main.java.hrk.timeline.pojo.TimeLine;

public class CSVHandler implements MySerializationHandler<TimeLine>{

	@Override
	public void serialize(TimeLine t, Writer writer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TimeLine deSerialize(Reader reader) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String supportedFileType() {
		return "csv";
	}
}
