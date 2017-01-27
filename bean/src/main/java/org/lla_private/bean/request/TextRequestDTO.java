package org.lla_private.bean.request;


public class TextRequestDTO {

	private Sentence sentence;
	
	public TextRequestDTO() {
		sentence  = new Sentence();
	}
	
	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

	public static class Sentence {
		String laufzeit;
		String sentence;
		String sentenceMethod;
		String textConverter;
		
		public String getLaufzeit() {
			return laufzeit;
		}
		
		public void setLaufzeit(String laufzeit) {
			this.laufzeit = laufzeit;
		}
		
		public String getSentence() {
			return sentence;
		}
		
		public void setSentence(String sentence) {
			this.sentence = sentence;
		}
		
		public String getSentenceMethod() {
			return sentenceMethod;
		}
		
		public void setSentenceMethod(String sentenceMethod) {
			this.sentenceMethod = sentenceMethod;
		}
		
		public String getTextConverter() {
			return textConverter;
		}
		
		public void setTextConverter(String textConverter) {
			this.textConverter = textConverter;
		}
	}
}
