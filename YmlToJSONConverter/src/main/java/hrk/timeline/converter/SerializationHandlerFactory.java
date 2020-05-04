package main.java.hrk.timeline.converter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import main.java.hrk.timeline.pojo.TimeLine;

public enum SerializationHandlerFactory {
	JSONHandler(new JSONHandler()), YMLHandler(new YMLHandler());//, CSVHandler(new CSVHandler());
	MySerializationHandler<TimeLine> handler;

	private SerializationHandlerFactory(MySerializationHandler<TimeLine> handler) {
		this.handler = handler;
	}

	public static MySerializationHandler<TimeLine> getHandler(String extension) {
		Optional<MySerializationHandler<TimeLine>> op = Optional
				.of(Arrays.stream(SerializationHandlerFactory.values()).map(item -> item.handler)
						.filter(handler -> handler.supportedFileType().equalsIgnoreCase(extension)).findFirst())
				.orElseThrow(() -> new IllegalArgumentException("No handler found for : " + extension));
		return op.get();
	}

	public static Stream<MySerializationHandler<TimeLine>> getAllHandlersExcept(
			MySerializationHandler<TimeLine> filterMeOut) {
		return Arrays.stream(SerializationHandlerFactory.values()).filter(item -> !item.handler.equals(filterMeOut))
				.map(item -> item.handler);
	}
}
