package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExamFromFile  {
	public static void main(String[] args) {
		List<ExamQuestion1> examQuestions = readExamQuestionsFromFile("Exam.txt");
		for (int i = 0; i < examQuestions.size(); i++) {
			ExamQuestion1 examQuestion = examQuestions.get(i);
			System.out.println("Question " + (i + 1) + ": " + examQuestion.getQuestion());
			System.out.println("Choices:");
			String[] choices = examQuestion.getChoices();
			for (int j = 0; j < choices.length; j++) {
				System.out.println("(" + (char) ('A' + j) + ") " + choices[j]);
			}
			System.out.println(); // 換行
		}
	}

	private static List<ExamQuestion1> readExamQuestionsFromFile(String filename) {
		List<ExamQuestion1> examQuestions = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			ExamQuestion1 currentQuestion = null;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}

				if (line.startsWith("(")) {
					if (currentQuestion != null) {
						String choice = line.substring(3).trim();
						currentQuestion.addChoice(choice);
					}
				} else {
					if (currentQuestion != null) {
						examQuestions.add(currentQuestion);
					}
					String question = line.substring(2).trim();
					currentQuestion = new ExamQuestion1(question);
				}
			}

			// 添加最後一個題目
			if (currentQuestion != null) {
				examQuestions.add(currentQuestion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return examQuestions;
	}
}

class ExamQuestion1 {
	private String question;
	private List<String> choices;

	public ExamQuestion1(String question) {
		this.question = question;
		this.choices = new ArrayList<>();
	}

	public String getQuestion() {
		return question;
	}

	public void addChoice(String choice) {
		choices.add(choice);
	}

	public String[] getChoices() {
		return choices.toArray(new String[0]);
	}
}
