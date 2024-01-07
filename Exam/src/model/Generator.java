package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generator {

	public static void main(String[] args) {
		
		/** Get Questions from file
		 * 
		 * */
		
		List<Question> examQuestions = readExamQuestionsFromFile("Exam.txt");
		
		// 題目洗牌
		Collections.shuffle(examQuestions);
        
		// 每題目的選項洗牌
        for (Question q : examQuestions) {
        	getRandomOptions(List.of(q.getOptions()));
		}

		/** Export Questions to file
		 * 
		 * */
		exportToNewExamFile("newExam.txt", examQuestions);
        System.out.println("Exam questions exported to newExam.txt successfully.");


	}
	

    // 隨機排列選項
    private static List<String> getRandomOptions(List<String> options) {
        List<String> shuffledOptions = new ArrayList<>(options);
        Collections.shuffle(shuffledOptions);
        return shuffledOptions;
    }

	private static List<Question> readExamQuestionsFromFile(String filename) {
		List<Question> examQuestions = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			Question currentQuestion = null;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}

				if (line.startsWith("(")) {
					if (currentQuestion != null) {
						String option = line.substring(3).trim();
						currentQuestion.addOptions(option);
					}
				} else {
					if (currentQuestion != null) {
						examQuestions.add(currentQuestion);
					}
					String question = line.substring(line.indexOf(".")+1).trim();
					currentQuestion = new Question(question);
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
	
	private static void exportToNewExamFile(String filename, List<Question> examQuestions) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < examQuestions.size(); i++) {
            	Question examQuestion = examQuestions.get(i);
                writer.println((i + 1) + "." + examQuestion.getQuestion());
                for (int j = 0; j < examQuestion.getOptions().length; j++) {
                    writer.println("(" + (char) ('A' + j) + ")" + examQuestion.getOptions()[j]);
                }
                writer.println(); // Empty line between questions
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
