package com.example.topquiz;

import java.util.List;

class Question {

    private String mQuestion;
    private List<String> mChoise;
    private  int mAnswerIndex;

    public Question ( String question, List<String> choiceList, int answerIndex){
        this.setQuestion(question);
        this.setChoise(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoise() {
        return mChoise;
    }

    public void setChoise(List<String> choiseList) {
        mChoise = choiseList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;
    }

}
