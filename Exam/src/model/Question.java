package model;

import java.util.ArrayList;
import java.util.List;

public class Question {

	private String question;
	private String answer;
	private List<String> options;

	protected Question() {

	}

	/**
	 * 添加固定 option
	 * 
	 */
	protected Question(String question, String answer, List<String> option) {
		this.question = question;
		this.answer = answer;
		this.options = option;
	}

	/**
	 * 動態添加 option
	 * 
	 */
	public Question(String question) {
		this.question = question;
		this.options = new ArrayList<>();
	}

	protected String getQuestion() {
		return question;
	}

	protected void setQuestion(String question) {
		this.question = question;
	}

	protected String getAnswer() {
		return answer;
	}

	protected void setAnswer(String answer) {
		this.answer = answer;
	}

//	protected List<String> getOptions() {
//		return options;
//	}
	public String[] getOptions() {
		return options.toArray(new String[0]);
	}

	protected void setOptionsList(List<String> strings) {
		this.options = strings;
	}

	public void addOptions(String option) {
		options.add(option);
	}


}
