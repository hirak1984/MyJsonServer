package hrk.timeline;

import java.io.IOException;
import java.io.Writer;

public class WriterDecoratorUncheckedException extends Writer {

	private Writer w;
	public WriterDecoratorUncheckedException(Writer w) {
		this.w = w;
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
