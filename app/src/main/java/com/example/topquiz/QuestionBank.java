package com.example.topquiz;

import java.util.Collections;
import java.util.List;

class QuestionBank {

    private List<Question> mQuestionList;
    private int mNextQuestionIndex;


    public QuestionBank (List<Question> questionList){
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);


    }


    public Question getQuestion() {
        if (mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
    }
}
