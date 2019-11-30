package com.example.geoffsgeob.ui.mail;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MailViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome challen@illinois.edu!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
