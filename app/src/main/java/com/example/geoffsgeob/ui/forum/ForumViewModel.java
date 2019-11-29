package com.example.geoffsgeob.ui.forum;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForumViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForumViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to the CS125 Forum!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
