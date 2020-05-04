package main.java.hrk.timeline;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import main.java.hrk.timeline.converter.MySerializationHandler;
import main.java.hrk.timeline.converter.SerializationHandlerFactory;
import main.java.hrk.timeline.pojo.TimeLine;

public class App {

	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 3) {
			String message = String.join(System.getProperty("line.separator"), "Please provide valid arguments",
					"*** Usage *** ", "App SOURCE_FILE_TYPE \"SOURCE_FILE/FOLDER\" \"TARGET_FOLDER\")", "Example-1 - ",
					"JSON JSON_FILE/FOLDER_ABS_PATH TARGET_FOLDER", "Example- 2 - ",
					"YML YML_FILE/FOLDER_PATH TARGET_FOLDER");

			throw new IllegalArgumentException(message);
		}

		MySerializationHandler<TimeLine> handler = SerializationHandlerFactory.getHandler(args[0]);
		List<File> sourceFiles = new LinkedList<>();
		getAllFiles(new File(args[1]), sourceFiles);

		File targetFolder = new File(args[2]);
		if (!targetFolder.isDirectory()) {
			throw new IllegalArgumentException("Target not a valid directory : " + targetFolder);
		}
		String message = String.join(System.getProperty("line.separator"), "Input parameters : ",
				"SOURCE_FILE/FOLDER = " + args[1], "TARGET_FOLDER = " + args[2], "Source file type = " + args[0]);
		System.out.println(message);
		System.out.println("Start");
		if (sourceFiles.isEmpty()) {
			System.out.println("No files in source folder are eligible for processing");
		} else {
			sourceFiles.forEach(sourceFile -> {
				process(sourceFile, targetFolder, handler);
			});
		}

		System.out.println("Complete");

	}

	private static void getAllFiles(File file, List<File> fileList) {
		if (file.isDirectory()) {
			File[] files = file.listFiles(f -> !f.isDirectory());
			if (files != null) {
				for (File child : files) {
					fileList.add(child);
				}
			}

		} else if (file.isFile()) {
			fileList.add(file);
		} else {
			// do nothing
		}

	}

	private static void process(File source, File target, MySerializationHandler<TimeLine> handler) {
		try (FileReaderDecoratorUncheckedException sourceReader = new FileReaderDecoratorUncheckedException(source)) {
			System.out.println("Now processing : " + source);
			TimeLine timeLine = handler.deSerialize(sourceReader);
			SerializationHandlerFactory.getAllHandlersExcept(handler).forEach(targetHandler -> {
				File targetFolder = new File(target, targetHandler.supportedFileType());
				targetFolder.mkdirs();
				String targetFilename = FilenameUtils.getBaseName(source.getName()) + "."
						+ targetHandler.supportedFileType();
				File targetFile = new File(targetFolder, targetFilename);
				try (FileWriterDecoratorUncheckedException targetWriter = new FileWriterDecoratorUncheckedException(
						targetFile)) {
					targetHandler.serialize(timeLine, targetWriter);
				} finally {
					System.out.println("Output into : " + targetFile);
				}
			});
		}
	}

}
