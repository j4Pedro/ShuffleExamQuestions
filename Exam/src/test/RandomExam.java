package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class RandomExam {
    public static void main(String[] args) {
        // 考試題目和選項的數據
        List<ExamQuestion> questions = new ArrayList<>();
        questions.add(new ExamQuestion("Look at the picture. The woman is putting ____ on the cake.", "candles", "forks", "plates", "strawberries"));
        questions.add(new ExamQuestion("The movie starts at two o'clock, ____ let's meet at the theater at one forty-five.", "so", "or", "if", "because"));

        // 隨機抽取題目
        List<ExamQuestion> randomQuestions = getRandomQuestions(questions, 2); // 抽取2題
        for (int i = 0; i < randomQuestions.size(); i++) {
            ExamQuestion question = randomQuestions.get(i);
            System.out.println( (i + 1) + "." + question.getQuestion());

            // 隨機排序選項
            List<String> randomChoices = getRandomChoices(question.getChoices());
            int asciiVal = 65;
            for (String choice : randomChoices) {
                System.out.println("("+Character.toString( (char) asciiVal)+")"+choice);
                asciiVal++;
            }

            System.out.println(); // 換行
        }
    }

    // 生成指定數量的隨機題目
    private static List<ExamQuestion> getRandomQuestions(List<ExamQuestion> questions, int numQuestions) {
        List<ExamQuestion> randomQuestions = new ArrayList<>();
        List<Integer> indexes = getRandomIndexes(questions.size(), numQuestions);
        for (int index : indexes) {
            randomQuestions.add(questions.get(index));
        }
        return randomQuestions;
    }

    // 生成從0到size-1的不重複隨機數字序列
    private static List<Integer> getRandomIndexes(int size, int count) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        return indexes.subList(0, count);
    }

    // 隨機排列選項
    private static List<String> getRandomChoices(List<String> choices) {
        List<String> shuffledChoices = new ArrayList<>(choices);
        Collections.shuffle(shuffledChoices);
        return shuffledChoices;
    }
}

class ExamQuestion {
    private String question;
    private List<String> choices;

    public ExamQuestion(String question, String... choices) {
        this.question = question;
        this.choices = new ArrayList<>();
        for (String choice : choices) {
            this.choices.add(choice);
        }
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }
}
