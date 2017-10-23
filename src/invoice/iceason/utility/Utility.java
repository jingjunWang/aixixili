package invoice.iceason.utility;

import invoice.iceason.debug.DebugLog;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.output.ByteArrayOutputStream;


public class Utility {
	private static SimpleDateFormat simpleDateFormatYMD = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat simpleDateFormatY_M_D = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat simpleDateFormatYYMMDD = new SimpleDateFormat("yyMMdd");
	private static SimpleDateFormat simpleDateFormatYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat simpleDateFormatyyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat simpleDateFormatHM = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat simpleDateFormatH = new SimpleDateFormat("HH");
	private static SimpleDateFormat simpleDateFormatD = new SimpleDateFormat("dd");
	private static SimpleDateFormat simpleDateFormatHHMMSS = new SimpleDateFormat("HHmmss");
	private static SimpleDateFormat simpleDateFormatYYMMDDHHmm = new SimpleDateFormat("yyMMddHHmm");
	private static final SimpleDateFormat simpleDateFormatYYMMDDHHmmss = new SimpleDateFormat("yyMMddHHmmss");
	// Changed this from 1900 to 1970 to align with the start date of system.currentTimeMills()
	private final static String DEFAULTYMDHMS = "1970-01-01 00:00:00";
	

	public static final String ERROR_HEADER = "error:";
	public static final String ERROR_HEADER_100 = "error_100:"; //订单检验失败，要求放弃订单
	public static final String UPDATE_HEADER = "update:";
	public static final String OK_HEADER = "ok:";
	public static final String CRLF = "\r\n";
	public static final String ERROR_HEADER_200 = "error_200";//TSPOS-384	ljl 为了不让程序出现死锁、定义特殊的错误信息

	//TSPOS-412   why 20151229 注释掉
//	public static final int LOG_SYNC_MAX_LINE = 20;

	public static String formatDateYMD(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYMD.format(date);
	}

	public static String formatDateYYMMDD(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYYMMDD.format(date);
	}

	/**
	 * 返回YYDDMMHHmmss类型的日期
	 * @param date
	 * @return
	 */
	public static synchronized String formatDateYYDDMMHHmmss(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYYMMDDHHmmss.format(date);
	}
	public static String formatDateY_M_D(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatY_M_D.format(date);
	}

	public static String formatDateYMDHMS(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYMDHMS.format(date);
	}
	
	public static String formatDateHM(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatHM.format(date);
	}

	public static int formatDateH(Date date) {
		if (date == null) {
			return 0;
		}
		return Integer.parseInt(simpleDateFormatH.format(date));
	}

	public static int formatDateD(Date date) {
		if (date == null) {
			return 0;
		}
		return Integer.parseInt(simpleDateFormatD.format(date));
	}
	
	public static String formatDateyyyyMMddHHmmss(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatyyyyMMddHHmmss.format(date);
	}

	public static String currentTimeYMDHMS() {
		return formatDateYMDHMS(today());
	}

	public static String currentTimesimpleDateFormatyyyyMMddHHmmss() {
		return formatDateyyyyMMddHHmmss(today());
	}
	//TSPOS-427
	public static String currentTimeYMD(){
		return formatDateYYMMDD(today());
	}

	public static Date today() {
		return new Date();
	}

	// 默认日期时间
	public static Date defaultDate() {
		Date defdate = null;
		try {
			// defdate = simpleDateFormatYMDHMS.parse(DEFAULTYMDHMS);
			defdate = new Date();
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}

	// 根据时间字符串获取时间
	public static Date getDateYMDByString(String date) {
		Date defdate = null;
		try {
			defdate = simpleDateFormatYMD.parse(date);
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}
	public static Date getDateY_M_DByString(String date){
		Date defdate = null;
		try {
			defdate = simpleDateFormatY_M_D.parse(date);
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}
	public static Date getDateYYMMDDByString(String date){
		Date defdate = null;
		try {
			defdate = simpleDateFormatYYMMDD.parse(date);
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}
	public static Date getDateYMDHMSByString(String date){
		Date defdate = null;
		try {
			defdate = simpleDateFormatYMDHMS.parse(date);
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}
	public static Date getDateyyyyMMddHHmmssByString(String date){
		Date defdate = null;
		try {
			defdate = simpleDateFormatyyyyMMddHHmmss.parse(date);
		} catch (Exception ex) {
			DebugLog.logger.error("Exception!!", ex);
		}
		return defdate;
	}
	

	public static byte[] getUpdateData(Charset charset){
		return getUpdateStr().getBytes(charset);
	}
	public static byte[] getUpdateData(){
		return getUpdateStr().getBytes();
	}
	public static String getUpdateStr(){
		return UPDATE_HEADER;
	}
	public static byte[] getErrorData(Charset charset){
		return getErrorStr().getBytes(charset);
	}
	public static byte[] getErrorData(){
		return getErrorStr().getBytes();
	}
	public static byte[] getErrorData(String errorHeader, String info, Charset charset){
		return getErrorStr(errorHeader, info).getBytes(charset);
	}
	public static byte[] getErrorData(String info){
		return getErrorStr(ERROR_HEADER, info).getBytes();
	}
	public static String getErrorStr(){
		return ERROR_HEADER;
	}
	//TSPOS-355 XY
	public static byte[] getErrorCodeData(String errorHeader,String errorCode, String info, Charset charset){
		return getErrorCodeStr(errorHeader,errorCode,info).getBytes(charset);
	}
	//TSPOS-355 XY
	public static String getErrorCodeStr(String errorHeader,String errorCode, String info){
		if(info != null){
			return errorHeader + errorCode + info;
		}else{
			return errorHeader;
		}
	}
	public static String getErrorStr(String errorHeader, String info){
		if(info != null){
			return errorHeader + info;
		}else{
			return errorHeader;
		}
	}
	public static byte[] getOkData(Charset charset){
		return getOkStr().getBytes(charset);
	}
	public static byte[] getOkData(){
		return getOkStr().getBytes();
	}
	public static byte[] getOkData(String info, Charset charset){
		return getOkStr(info).getBytes(charset);
	}
	public static byte[] getOkData(String info){
		return getOkStr(info).getBytes();
	}
	public static String getOkStr(){
		return OK_HEADER;
	}
	public static String getOkStr(String info){
		if(info != null){
			return OK_HEADER + info;
		}else{
			return OK_HEADER;
		}
	}
	//TSPOS-300	ljl
	public static String getOKInfo(String data){
		return data.substring(data.indexOf(":") + 1);
	}
	
	public static boolean hasOK(String data){
		return data.startsWith(Utility.OK_HEADER);
	}
	
	public static boolean hasError(String data){
		return data.startsWith(Utility.ERROR_HEADER);
	}
	public static boolean hasError(byte[] data){
		return hasError(new String(data));
	}
	public static boolean hasError(byte[] data, Charset charset){
		return hasError(new String(data, charset));
	}
	public static String getErrorInfo(String data, Charset charset){
		return data.substring(data.indexOf(":") + 1);
	}
	public static String getErrorInfo(byte[] data, Charset charset){
		return getErrorInfo(new String(data, charset));
	}
	public static String getErrorInfo(String data){
		return data.substring(data.indexOf(":") + 1);
	}
	public static String getErrorInfo(byte[] data){
		return getErrorInfo(new String(data));
	}
	//TSPOS-384	ljl
	public static boolean hasError_200(byte[] data, Charset charset){
		return hasError_200(new String(data, charset));
	}
	public static boolean hasError_200(String data){
		return data.startsWith(Utility.ERROR_HEADER_200);
	}
	public static String getErrorInfo_200(byte[] data, Charset charset){
		return getErrorInfo(new String(data, charset));
	}
	public static String getErrorInfo_200(String data){
		return data.substring(data.indexOf(":") + 1);
	}

	/**
	 * 返回HHmmss类型的日期
	 * @param date
	 * @return
	 */
	public static String formatDateYYDDMMHHmm(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatYYMMDDHHmm.format(date);
	}
	/**
	 * 返回HHmmss类型的日期
	 * @param date
	 * @return
	 */
	public static String formatDateHHMMSS(Date date) {
		if (date == null) {
			return null;
		}
		return simpleDateFormatHHMMSS.format(date);
	}
	// Serialize an object to byte[]. Used for TCPIP message serialization
	public static byte[] serialize(Serializable obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	// read in an object from a byte[]. Used for TCPIP message serialization and POSService/Hash of POSServices 
	// deserialization (from the TCPIPMessage:messageData, for example).
	public static Serializable deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return (Serializable) is.readObject();
	}
	public static Object recoverObjectFromFile(String fileName){ // throws IOException, ClassNotFoundException 
		Object obj = null;
		File file  = new File(fileName);
		FileInputStream fis = null;
		int bytes = 0;
		if(file.exists()){
			byte[] content = new byte[(int) file.length()];
			try {
				fis = new FileInputStream(file);
				bytes = fis.read(content);
				fis.close();								
			} catch (IOException e) {
				DebugLog.logger.error("ServiceAgentToolkit:recoverObjectFromFile - When read file " + file + " got exception", e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						DebugLog.logger.error("ServiceAgentToolkit:recoverObjectFromFile - When close file " + fileName + " got exception", e);
					}
				}
			}
			if (bytes > 0) {
				try {
					obj = deserialize(content);
				} catch (ClassNotFoundException | IOException e) {
					DebugLog.logger.error("ServiceAgentToolkit:recoverObjectFromFile - When deserialize file " + file + " got exception", e);
				}
			}
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				DebugLog.logger.error("ServiceAgentToolkit:recoverObjectFromFile - When create file " + file + " got exception", e);
			}
		}
		return obj;
	}
	public static void writeData2File(String fileName, byte[] data) { // throws IOException 
		File f = new File(fileName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write(data);
			fos.close();
		} catch (IOException ioe) {
			DebugLog.logger.error("ServiceAgentToolkit:writeData2File - When write file " + fileName + " got exception", ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 删除文件   重试后删除失败会抛出异常
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) throws Exception{   
		try {
			java.io.File myDelFile = new java.io.File(filePathAndName);
			if(myDelFile.exists()){
				myDelFile.delete();
				DebugLog.logger.debug("Utility.delFile ...filepath=" + filePathAndName + " success !");
			}else{
				DebugLog.logger.debug("Utility.delFile ...filepath=" + filePathAndName + " not found !");
			}

		} catch (Exception e) {
			DebugLog.logger.error( "Exception in delete file " + filePathAndName, e);
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				java.io.File myDelFile = new java.io.File(filePathAndName);
				if(myDelFile.exists()){
					myDelFile.delete();
				}
			} catch (Exception exx) {
				DebugLog.logger.error( "Exception in trying to delete file once more " + filePathAndName, exx);
				throw exx;
			}
		}
	}
}
