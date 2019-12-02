package com.example.geoffsgeob.ui.homework;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class HomeworkViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public HomeworkViewModel(Application application) {
        super(application);
        String[] hwNames = getApplication().getResources().getStringArray(R.array.homework_names);
        mText = new MutableLiveData<>();
        mText.setValue("Homework " + MainActivity.getWeek() + ": " + hwNames[MainActivity.getWeek()]);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
