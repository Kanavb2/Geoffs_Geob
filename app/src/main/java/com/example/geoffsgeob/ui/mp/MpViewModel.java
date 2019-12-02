package com.example.geoffsgeob.ui.mp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class MpViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MpViewModel(Application application) {
        super(application);
        String[] mpNames = getApplication().getResources().getStringArray(R.array.mp_names);
        mText = new MutableLiveData<>();
        if ((MainActivity.getWeek() < 2 || MainActivity.getWeek() % 2 == 1) && MainActivity.getWeek() <= 12) {
            mText.setValue("Upcoming\n MP " + MainActivity.getWeek() / 2 + ": " + mpNames[MainActivity.getWeek() / 2]);
        } else if (MainActivity.getWeek() > 12) {
            mText.setValue("There are no more MPs left in this semester!");
        } else {
            mText.setValue("MP " + (MainActivity.getWeek() / 2 - 1) + ": " + mpNames[MainActivity.getWeek() / 2 - 1]);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}
