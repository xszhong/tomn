package ntu.scse.tool;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import ntu.scse.util.IOProcess;
import ntu.scse.util.Setting;

public class TimeML {
	private static HtmlCleaner cleaner;
	
	static {
		cleaner = new HtmlCleaner();
	}
	
	/**For TimeML*/
	public static final String TML_HEAD = "<?xml version=\"1.0\" ?>\r\n<TimeML xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://timeml.org/timeMLdocs/TimeML_1.2.1.xsd\">";
    public static final String TML_END = "</TimeML>";
    
    public static final String DOCID_HEAD = "<DOCID>";
    public static final String DOCID_END = "</DOCID>";
    
    public static final String DCT_HEAD = "<DCT><TIMEX3 functionInDocument=\"CREATION_TIME\" temporalFunction=\"false\" tid=\"t0\" type=\"DATE\" value=\"";
    public static final String DCT_MID = "\">";
    public static final String DCT_END = "</TIMEX3></DCT>";
    
    public static final String TEXT_HEAD = "<TEXT>";
    public static final String TEXT_END = "</TEXT>";
    
    public static final String TIMEX3_TID = "<TIMEX3 tid=\"t";
    public static final String TIMEX3_TYPE = "\" type=\"";
    public static final String TIMEX3_VALUE = "\" value=\"";
    public static final String TIMEX3_MID = "\">";
    public static final String TIMEX3_END = "</TIMEX3>";
    
    public static final  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
    /*********************************************
     * Deal with TimeML and Timex3
     * ******************************************/
	public static String extractTimeMLDocId(File inputTmlFile){
		String docId = null;
		try {
			TagNode node = cleaner.clean(inputTmlFile);
			Object[] dctNodes = node.evaluateXPath("//TimeML//DOCID");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputTmlFile.getName());
				return docId;
			}
			docId = ((TagNode)dctNodes[0]).getText().toString();
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return docId;
	}
	public static String extractTimeMLDocId(String inputTmlFile){
		return extractTimeMLDocId(new File(inputTmlFile));
	}
	
	public static String extractTimeMLDCT(File inputTmlFile){
		String dct = null;
		try {
			TagNode node = cleaner.clean(inputTmlFile);
			Object[] dctNodes = node.evaluateXPath("//TimeML//DCT//TIMEX3");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputTmlFile.getName());
				return dct;
			}
			dct = ((TagNode)dctNodes[0]).getText().toString();
			
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return dct;
	}
	
	public static String extractTimeMLDCT(String inputTmlFile){
		return extractTimeMLDCT(new File(inputTmlFile));
	}
	
	public static String extractTimeMLText(File inputTmlFile){
		String text = null;
		try {
			TagNode node = cleaner.clean(inputTmlFile);
			Object[] dctNodes = node.evaluateXPath("//TimeML//TEXT");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputTmlFile.getName());
				return text;
			}
			text = ((TagNode)dctNodes[0]).getText().toString();
			
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return text;
	}
	public static String extractTimeMLText(String inputTmlFile){
		return extractTimeMLText(new File(inputTmlFile));
	}
	
	public static String induceTIMEX3(int tid, String type, String value, String timex){
		return TIMEX3_TID + tid + TIMEX3_TYPE + type + TIMEX3_VALUE + value + TIMEX3_MID + timex + TIMEX3_END;
	}
	
	public static void saveToTimeML(String docId, String dct, String timeMLContent, String outputTimeMLFile){
		StringBuffer sb = new StringBuffer();
		sb.append(TML_HEAD + Setting.NEWLINE);
		sb.append(DOCID_HEAD + docId + DOCID_END + Setting.NEWLINE);
		sb.append(DCT_HEAD + dct + DCT_MID + dct + DCT_END + Setting.NEWLINE);
		sb.append(TEXT_HEAD);
		sb.append(timeMLContent);
		sb.append(TEXT_END + Setting.NEWLINE);
		sb.append(TML_END + Setting.NEWLINE);
		IOProcess.saveFile(outputTimeMLFile, sb.toString());
	}
	
	/***************************************************
	 * Deal with DOC and Timex2
	 * *************************************************/
	
	public static String extractTIMEX2DocId(File inputFile){
		String docId = null;
		try {
			TagNode node = cleaner.clean(inputFile);
			Object[] dctNodes = node.evaluateXPath("//DOC//DOCID");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputFile.getName());
				return docId;
			}
			docId = ((TagNode)dctNodes[0]).getText().toString();
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return docId;
	}
	public static String extractTIMEX2DocId(String inputFile){
		return extractTimeMLDocId(new File(inputFile));
	}
	
	public static String extractTIMEX2DCT(File inputFile){
		String dct = null;
		try {
			TagNode node = cleaner.clean(inputFile);
			Object[] dctNodes = node.evaluateXPath("//DOC//DATETIME");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputFile.getName());
				return dct;
			}
			dct = ((TagNode)dctNodes[0]).getText().toString().trim().substring(0, 10);			
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return dct;
	}
	
	public static String extractTIMEX2DCT(String inputFile){
		return extractTimeMLDCT(new File(inputFile));
	}
	
	public static String extractTIME2Text(File inputFile){
		String text = null;
		try {
			TagNode node = cleaner.clean(inputFile);
			Object[] dctNodes = node.evaluateXPath("//DOC//TEXT");
			if(dctNodes == null || dctNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + inputFile.getName());
				return text;
			}
			text = ((TagNode)dctNodes[0]).getText().toString();			
		} catch (XPatherException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		
		return text;		
	}
}
