package com.example.geoffsgeob.ui.mp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("mp text");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
