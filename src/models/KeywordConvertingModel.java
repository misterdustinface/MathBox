package models;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;


public class KeywordConvertingModel {
	
	class KeywordMappingPair
	{
		String[] keywords;
		String mapsTo;
		
		public KeywordMappingPair(String[] words, char c){
			keywords 	= words;
			mapsTo 		= String.valueOf(c);
		}
		
		public KeywordMappingPair(String[] words, String s){
			keywords 	= words;

			char[] chars = s.toCharArray();
			boolean isUnicode = true;
			for(char c : chars){
				if(!Character.isDigit(c)){
					isUnicode = false;
					break;
				}
			}
			
			if(isUnicode){
				mapsTo = String.valueOf((char) Integer.parseInt(s));
			}else{
				mapsTo = s;
			}
		}
		
		public String toString(){
			String str = new String();
			for(String word: keywords)
			{
				str += word;
				if(word != keywords[keywords.length - 1]){
					str += ", ";
				}
			}
			str += " == {" + mapsTo + "}\n";
			return str;
		}
	}
	
	////////////////////////////////////////
	
	private LinkedList<KeywordMappingPair> set;
	
	public KeywordConvertingModel(File f){
		set = decode(f);
	}
	
	private LinkedList<KeywordMappingPair> decode(File f){
		 LinkedList<KeywordMappingPair> temp = new LinkedList<KeywordMappingPair>();

		 try {
			 BufferedReader br = new BufferedReader(new FileReader(f));
			 String line;
			 while ((line = br.readLine()) != null) {
			    
				 String[] keywords;
				 
				 int previousPos 		= 0;
				 int endOfKeywords 		= line.indexOf(';');
				 int keywordSeparator 	= line.indexOf(',');
				 int numberOfKeywords 	= 1;
				 
				 LinkedList<String> words = new LinkedList<String>();
				 
				 while(keywordSeparator != -1)
				 {
					 words.add(line.substring(previousPos, keywordSeparator).trim());
					 previousPos 		= keywordSeparator + 1;
					 keywordSeparator 	= line.indexOf(',', previousPos);
					 numberOfKeywords++;
				 }
				 
				 words.add(line.substring(previousPos, endOfKeywords).trim());
					
				 keywords = new String[numberOfKeywords];
				 for(int i = 0; i < numberOfKeywords; i++){
					 keywords[i] = words.pop();
				 }
				 				 
				 temp.add(new KeywordMappingPair(keywords, line.substring(endOfKeywords + 1).trim()));
			 }
			 
			 br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return temp;
	}
	
	// An operation
	public String convert(String text)
	{
		for(KeywordMappingPair pair : set){
			for(String searchword : pair.keywords){
				text = text.replace(searchword, pair.mapsTo);
			}
		}
		
		return text;
	}
	
	public String toString(){
		String str = new String();
		
		for(KeywordMappingPair pair : set){
			str += pair.toString();
			str += "-------------------------\n";
		}
		
		return str;
	}
}
