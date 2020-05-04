package main.java.hrk.timeline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderDecoratorUncheckedException extends Reader {
	FileReader fileReader;

	public FileReaderDecoratorUncheckedException(File file) {
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int read(char[] cbuf, int offset, int length){
		try {
			return fileReader.read(cbuf, offset, length);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close(){
		try {
			fileReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
