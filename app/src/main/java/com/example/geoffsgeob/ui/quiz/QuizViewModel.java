package com.example.geoffsgeob.ui.quiz;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class QuizViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    public QuizViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        String[] quizNames = getApplication().getResources().getStringArray(R.array.quiz_names);
        int quizNumber = MainActivity.getWeek();
        if (MainActivity.getWeek() > 4) {
            quizNumber--;
            if (MainActivity.getWeek() > 9) {
                quizNumber--;
            }
        }
        if ((MainActivity.getWeek() + 1) % 5 == 0) {
            mText.setValue("Upcoming\nQuiz " + quizNumber + ": " + quizNames[quizNumber]);
        } else {
            mText.setValue("Quiz " + quizNumber + ": " + quizNames[quizNumber]);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}
