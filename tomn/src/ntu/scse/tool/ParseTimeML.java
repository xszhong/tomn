package ntu.scse.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import ntu.scse.struct.Article;
import ntu.scse.struct.CONST;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.StanfordPipeline;

public class ParseTimeML {
	private HtmlCleaner cleaner;
	private StanfordPipeline pipeline;
	
	public ParseTimeML(){
		cleaner = new HtmlCleaner();
		pipeline = new StanfordPipeline();
	}
	
	public Article parseTimeML(String inputTmlFile){
		return parseTimeML(new File(inputTmlFile));
	}
	
	public Article parseTimeML(File tmlFile){
		return parseTimeML(tmlFile, CONST.CRF_TEST);
	}
	
	public Article parseTimeML(String inputTmlFile, boolean isTrain){
		return parseTimeML(new File(inputTmlFile), isTrain);
	}
	
	public Article parseTimeML(File tmlFile, boolean isTrain){
		List<TaggedToken> articleTaggedTokenList = new ArrayList<TaggedToken>();
		StringBuffer articleSb = new StringBuffer();
		
		try {
			TagNode node = cleaner.clean(tmlFile);
			Object[] textNodes = node.evaluateXPath("//TimeML//TEXT");
			if(textNodes == null || textNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Could not parse the XPath of file: " + tmlFile.getName());
				return null;
			}
			
			int startTokenIndex = 0;
			int startCharIndex = 0;
			for(Object obj : textNodes){
				TagNode textNode = (TagNode) obj;
				
				String text = textNode.getText().toString();
				articleSb.append(text);
				
				if(isTrain){
					List<Object> itemList = textNode.getAllChildren();
					for (Object item : itemList) {
						if (item instanceof TagNode) {
							TagNode tagNode = (TagNode) item;
							String nodeName = tagNode.getName();

							String nodeText = tagNode.getText().toString();

							int length = nodeText.length();

							List<TaggedToken> nodeTaggedTokenList = pipeline.tagging(nodeText, startTokenIndex, startCharIndex);

							startTokenIndex += nodeTaggedTokenList.size();
							startCharIndex += length;

							if (nodeName.toUpperCase().equals(CONST.TIMEX3))
								setIsTimex(nodeTaggedTokenList, CONST.IS_TIMEX);
							else
								setIsTimex(nodeTaggedTokenList, CONST.ISNOT_TIMEX);

							articleTaggedTokenList.addAll(nodeTaggedTokenList);
						} else if (item instanceof ContentNode) {
							ContentNode contentNode = (ContentNode) item;
							String contentText = contentNode.getContent();

							int length = contentText.length();

							List<TaggedToken> contentTaggedTokenList = pipeline.tagging(contentText, startTokenIndex, startCharIndex);

							startTokenIndex += contentTaggedTokenList.size();
							startCharIndex += length;

							setIsTimex(contentTaggedTokenList, CONST.ISNOT_TIMEX);

							articleTaggedTokenList.addAll(contentTaggedTokenList);
						}
					}
				}else{
					List<TaggedToken> textTaggedTokenList = pipeline.tagging(text);
					setIsTimex(textTaggedTokenList, CONST.ISNOT_TIMEX);
					
					articleTaggedTokenList.addAll(textTaggedTokenList);
				}
			}
		} catch (IOException | XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		
		Article article = new Article(articleSb.toString(), articleTaggedTokenList);
		
		return article;
	}
	
	private void setIsTimex(List<TaggedToken> taggedTokenList, boolean isTimex){
		if(! isTimex){
			for(TaggedToken taggedToken : taggedTokenList){
				taggedToken.setIsTimex(isTimex);
				taggedToken.setBilouTag(CONST.BILOU_O);
				taggedToken.setBioTag(CONST.BIO_O);
			}
		}else{
			int size = taggedTokenList.size();
			if(size == 0)
				return;
			else if(size == 1){
				TaggedToken taggedToken = taggedTokenList.get(0);
				taggedToken.setIsTimex(isTimex);
				taggedToken.setBilouTag(CONST.BILOU_U);
				taggedToken.setBioTag(CONST.BIO_B);
			}else{
				for(int i = 0; i < size; i ++){
					TaggedToken taggedToken = taggedTokenList.get(i);
					taggedToken.setIsTimex(isTimex);
					
					if(i == 0){
						taggedToken.setBilouTag(CONST.BILOU_B);
						taggedToken.setBioTag(CONST.BILOU_B);
					}else if(i == size - 1){
						taggedToken.setBilouTag(CONST.BILOU_L);
						taggedToken.setBioTag(CONST.BIO_I);
					}else{
						taggedToken.setBilouTag(CONST.BILOU_I);
						taggedToken.setBioTag(CONST.BIO_I);
					}
				}
			}
		}
	}

}
