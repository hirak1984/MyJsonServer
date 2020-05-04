package main.java.hrk.timeline.converter;

import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.stream.Collectors;

public interface MySerializationHandler<T> {
	String supportedFileType();
	void serialize(final T t,final Writer writer);
	
	T deSerialize(final Reader reader);
	
	default Collection<T> deSerialize(final Collection<Reader> readers){
		return readers.stream().map(reader->deSerialize(reader)).collect(Collectors.toSet());
		
	}
	
}
