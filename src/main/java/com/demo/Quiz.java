package com.demo;

import java.lang.reflect.Array;
import java.util.*;

public class Quiz {
    private static String[] questions = {
            "3, 1, 4, 1, 5",
            "1, 1, 2, 3, 5",
            "1, 4, 9, 16, 25",
            "2, 3, 5, 7, 11",
            "1, 2, 4, 8, 16"
    };
    private static int[] answers = {9, 8, 36, 13, 32};
    private int score;
    private int count;
    static Random rand = new Random();
    private static Set<Integer> set = new LinkedHashSet<Integer>();
    private int index;
    private List<Integer> order = new ArrayList<>();
    private Integer[] indexArr = {0,1, 2, 3, 4};



    public Quiz(){
        this.score=0;
        this.count=0;
        order = Arrays.asList(indexArr);
        Collections.shuffle(this.order);
        index = 0;
    }

    public String getQuestion() {
        return questions[order.get(index++)];
    }

    public int getAnswer() {
        if(index == 0){
            return answers[order.get(0)];
        }
        return answers[order.get(index-1)];
    }

    public int getCount(){
        return count;
    }
    public void incrementCount(){
        this.count++;
    }
    public int getScore(){
        return score;
    }
    public void incrementScore(){
       this.score++;
    }

}
