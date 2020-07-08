package ntu.scse.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import ntu.scse.struct.BasicTaggedToken;
import ntu.scse.struct.TaggedToken;

public class StanfordPipeline {
	/** For StanfordNLP, use its function of pos and position */
	private Properties props;
	private StanfordCoreNLP pipeline;
	
	public StanfordPipeline(){
		props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        pipeline = new StanfordCoreNLP(props);
	}
	
	public static void main(String[] args){
		StanfordPipeline pipeline = new StanfordPipeline();
		List<TaggedToken> taggedTokenList = pipeline.tagging("his money.\n\nIn Hong Kong, is always belongs to the seller's market.\ncontent text 2: his money.");
		for(TaggedToken taggedToken : taggedTokenList){
			System.out.println(taggedToken.getToken() + "\t" + taggedToken.getPos());
		}
	}
	
	public List<BasicTaggedToken> basicTagging(String text){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<BasicTaggedToken> taggedTokenList = new ArrayList<BasicTaggedToken>();
		
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int beginPosition = token.beginPosition();
				int endPosition = token.endPosition();
				
				taggedTokenList.add(new BasicTaggedToken(word, lemma, posTag, beginPosition, endPosition));
			}
		}
		return taggedTokenList;
	}
	
	public List<TaggedToken> tagging(String text){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<TaggedToken> taggedTokenList = new ArrayList<TaggedToken>();
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int tokenPosition = taggedTokenList.size();
				int beginCharPosition = token.beginPosition();
				int endCharPosition = token.endPosition();
				
				taggedTokenList.add(new TaggedToken(word, lemma, posTag, tokenPosition, beginCharPosition, endCharPosition));
			}
		}
		return taggedTokenList;
	}
	
	public List<TaggedToken> tagging(String text, int initialTokenIndex, int initialCharIndex){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<TaggedToken> taggedTokenList = new ArrayList<TaggedToken>();
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int tokenPosition = initialTokenIndex + taggedTokenList.size();
				int beginCharPosition = initialCharIndex + token.beginPosition();
				int endCharPosition = initialCharIndex + token.endPosition();
				
				taggedTokenList.add(new TaggedToken(word, lemma, posTag, tokenPosition, beginCharPosition, endCharPosition));
			}
		}
		return taggedTokenList;
	}
}
