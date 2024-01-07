package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomExamQuestion {
    public static void main(String[] args) {
        // 題目和選項的數據
        List<String> questions = new ArrayList<>();
        questions.add("這是第一個題目嗎?");
        questions.add("這是第二個題目嗎?");
        questions.add("這是第三個題目嗎?");

        List<List<String>> choices = new ArrayList<>();
        choices.add(List.of("A) 選項1", "B) 選項2", "C) 選項3", "D) 選項4"));
        choices.add(List.of("A) 選項A", "B) 選項B", "C) 選項C", "D) 選項D"));
        choices.add(List.of("A) 選項X", "B) 選項Y", "C) 選項Z"));

        // 隨機抽取題目
        List<Integer> randomIndexes = getRandomIndexes(questions.size());
        for (int i = 0; i < randomIndexes.size(); i++) {
            int index = randomIndexes.get(i);
            System.out.println("第 " + (i + 1) + " 題：" + questions.get(index));

            // 隨機排序選項
            List<String> randomChoices = getRandomChoices(choices.get(index));
            for (String choice : randomChoices) {
                System.out.println(choice);
            }

            System.out.println(); // 換行
        }
    }

    // 生成從0到size-1的隨機排列
    private static List<Integer> getRandomIndexes(int size) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        return indexes;
    }

    // 隨機排列選項
    private static List<String> getRandomChoices(List<String> choices) {
        List<String> shuffledChoices = new ArrayList<>(choices);
        Collections.shuffle(shuffledChoices);
        return shuffledChoices;
    }
}
