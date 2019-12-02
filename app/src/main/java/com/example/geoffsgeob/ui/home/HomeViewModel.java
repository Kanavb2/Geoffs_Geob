package com.example.geoffsgeob.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.geoffsgeob.MainActivity;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fall 2019: Week " + MainActivity.getWeek());
    }

    public LiveData<String> getText() {
        return mText;
    }
}