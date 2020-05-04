package main.java.hrk.timeline.converter;

import java.io.Reader;
import java.io.Writer;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import main.java.hrk.timeline.pojo.TimeLine;

public class YMLHandler implements MySerializationHandler<TimeLine>{

	@Override
	public void serialize(TimeLine timeLine, Writer writer) {
			Yaml yaml = new Yaml();
			yaml.dump(timeLine, writer);		
	}

	@Override
	public TimeLine deSerialize(Reader reader) {
		Yaml yaml = new Yaml(new Constructor(TimeLine.class));
		TimeLine timeLine = yaml.load(reader);
		//System.out.println(timeLine);
		return timeLine;
	}
	@Override
	public String supportedFileType() {
		return "yml";
	}
}
