package main.java.hrk.timeline.converter;

import java.io.Reader;
import java.io.Writer;
import com.google.gson.Gson;
import main.java.hrk.timeline.pojo.TimeLine;

public class JSONHandler implements MySerializationHandler<TimeLine>{

	@Override
	public void serialize(TimeLine t, Writer writer) {
		final Gson gson = new Gson();
		gson.toJson(t, writer);
		
	}

	@Override
	public TimeLine deSerialize(Reader reader) {
		final Gson gson = new Gson();
		TimeLine tl = gson.fromJson(reader, TimeLine.class);
		return tl;
	}

	@Override
	public String supportedFileType() {
		return "json";
	}
}
