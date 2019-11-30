package com.example.geoffsgeob.ui.quiz;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

public class QuizViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    public QuizViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        String[] quizNames = getApplication().getResources().getStringArray(R.array.quiz_names);
        int quizNumber = HomeFragment.getWeek();
        if (HomeFragment.getWeek() > 4) {
            quizNumber--;
            if (HomeFragment.getWeek() > 9) {
                quizNumber--;
            }
        }
        mText.setValue("Quiz " + quizNumber + ": " + quizNames[quizNumber]);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
