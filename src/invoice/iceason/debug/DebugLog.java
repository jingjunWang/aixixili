package invoice.iceason.debug;

import invoice.iceason.utility.ConstantStaticVariablesSet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DebugLog {
	public static Logger logger; 
	static{
		System.setProperty ("WORKDIR", ApplicationListener.rootPath+ConstantStaticVariablesSet.Log4jFilePathAndName);
		PropertyConfigurator.configure(ApplicationListener.rootPath + ConstantStaticVariablesSet.Log4jPathAndName);
		logger = Logger.getLogger("Iceason_Invoice"); 
		System.setErr(new PrintStream(new CustomOutputStream()));
	}
	public  DebugLog(){
	}
}

class CustomOutputStream extends OutputStream {
	StringBuffer toWrite = new StringBuffer();
	@Override
	public final void write(int b) throws IOException {
		if(b != 10 && b != 13) {
			toWrite = toWrite.append((char) b);
		} else if ( b == 10 || b == 13) {
			if (toWrite.length() > 0) {
				DebugLog.logger.error("!! " + toWrite.toString());
				toWrite.setLength(0);
			}
		}
	}
	
	@Override
	public final void write(byte[] b) throws IOException {
		DebugLog.logger.error("!! " + new String(b));
	}	
}