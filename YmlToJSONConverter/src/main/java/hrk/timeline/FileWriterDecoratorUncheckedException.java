package main.java.hrk.timeline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileWriterDecoratorUncheckedException extends Writer {

	private FileWriter w;
	public FileWriterDecoratorUncheckedException(File file) {
		try {
			this.w = new FileWriter(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(String str){
		try {
			w.write(str);	
		}catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
	}

	@Override
	public void write(char[] cbuf, int off, int len) {
		try {
			w.write(cbuf, off,len);	
		}catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
	}

	@Override
	public void flush()  {
		try {
			w.flush();
		}catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
		
	}

	@Override
	public void close() {
		try {
			w.close();
		}catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
	}
}
