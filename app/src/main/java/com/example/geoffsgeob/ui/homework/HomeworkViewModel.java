package com.example.geoffsgeob.ui.homework;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeworkViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeworkViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Set the average difficulty level for this week's homework by entering a number from 0 to 9, where 0 is the easiest and 9 is the hardest.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
