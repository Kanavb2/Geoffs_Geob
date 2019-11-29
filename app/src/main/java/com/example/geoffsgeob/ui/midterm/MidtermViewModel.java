package com.example.geoffsgeob.ui.midterm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MidtermViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MidtermViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("midterm text");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
