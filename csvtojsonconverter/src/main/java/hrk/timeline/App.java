package hrk.timeline;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.apache.commons.io.FilenameUtils;

import hrk.timeline.pojo.TestFormat;

public final class App {

	/**
	 * Says hello to the world. SOURCE_FILE TARGET_FILE OPERATION
	 * 
	 * @param args
	 *            The arguments of the program.
	 * @throws IOException
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			String message = String.join(System.getProperty("line.separator"), "Please provide valid arguments",
					"*** Usage *** ",
					"App \"SOURCE_FILE/FOLDER_ABS_PATH\" \"TARGET_FILE/FOLDER_ABS_PATH\" <OPERATION>(Optional.CSV2JSON(default) or JSON2CSV)",
					"Example-1 - ", "JSON_FILE/FOLDER_ABS_PATH CSV_FILE/FOLDER_ABS_PATH JSON2CSV", "Example- 2 - ",
					"CSV_FILE/FOLDER_PATH JSON_FILE/FOLDER_PATH CSV2JSON");

			throw new IllegalArgumentException(message);
		}
		if (args.length == 2) {
			args = new String[] { args[0], args[1], OPS.CSV2JSON.name() };
		}
		File sourceFile = new File(args[0]);
		File targetFile = new File(args[1]);
		OPS ops = OPS.getOps(args[2]);
		String message = String.join(System.getProperty("line.separator"), "Input parameters : ",
				"SOURCE_FILE_ABS_PATH = " + sourceFile.getAbsolutePath(),
				"TARGET_FILE_ABS_PATH = " + targetFile.getAbsolutePath(), "Operation = " + ops);
		System.out.println(message);
		System.out.println("Start");

		if (sourceFile.isDirectory() && targetFile.isDirectory()) {
			File[] sources = sourceFile.listFiles(ops.fileMetadataHelper.get().getFileFilter());
			if(sources==null || sources.length==0) {
				System.out.println("No files in source folder are eligible for processing");
			}else {
				for (File s : sources) {
					String fileNameWithoutExt = FilenameUtils.getBaseName(s.getName());
					File t = new File(targetFile, fileNameWithoutExt + ops.fileMetadataHelper.get().getTargetExtension());
					process(s, t, ops);
				}
			}

		} else {
			process(sourceFile, targetFile, ops);
		}

		System.out.println("Done");
	}

	private static enum OPS {
		CSV2JSON((sourceFileReader, targetFileWriter) -> {
			TestFormat testFormat = CSV_JSON_ADAPTER.fromCSV(sourceFileReader);
			CSV_JSON_ADAPTER.asJSONInto(testFormat, targetFileWriter);
		}, () ->new FileMetadataHelper(".csv",".json")), JSON2CSV((sourceFileReader, targetFileWriter) -> {
			TestFormat testFormat = CSV_JSON_ADAPTER.fromJSON(sourceFileReader);
			CSV_JSON_ADAPTER.asCSVInto(testFormat, targetFileWriter);
		}, () ->new FileMetadataHelper(".json",".csv"));
		public BiConsumer<FileReader, FileWriter> perform;
		public Supplier<FileMetadataHelper> fileMetadataHelper;

		OPS(BiConsumer<FileReader, FileWriter> perform, Supplier<FileMetadataHelper> fileMetadataHelper) {
			this.perform = perform;
			this.fileMetadataHelper = fileMetadataHelper;
		}

		public static OPS getOps(String name) {
			OPS retVal = OPS.valueOf(name);
			if (retVal == null) {
				throw new IllegalArgumentException("Not a valid operation : " + name);
			}
			return retVal;
		}
	}
static class FileMetadataHelper {
	private String sourceExtension;
	private String targetExtension;
	private FileFilter fileFilter;

	
	public FileMetadataHelper(String sourceExtension, String targetExtension) {
		super();
		this.sourceExtension = sourceExtension;
		this.targetExtension = targetExtension;
		fileFilter = (f)->{
			return f.getName().endsWith(sourceExtension);
		};
	}

	public FileFilter getFileFilter() {
		return fileFilter;
	}

	public String getSourceExtension() {
		return sourceExtension;
	}

	public String getTargetExtension() {
		return targetExtension.startsWith(".")?targetExtension:targetExtension.substring(1);
	}

	
	
	
}
	private static void process(File sourceFile, File targetFile, OPS ops) {
		try (FileReader srcFileReader = new FileReader(sourceFile);
				FileWriter targetFileWriter = new FileWriter(targetFile)) {
			ops.perform.accept(srcFileReader, targetFileWriter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
