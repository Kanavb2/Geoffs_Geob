package com.example.geoffsgeob.ui.mail;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MailViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("mail text");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
