package com.example.geoffsgeob.ui.mp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

public class MpViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MpViewModel(Application application) {
        super(application);
        String[] mpNames = getApplication().getResources().getStringArray(R.array.mp_names);
        mText = new MutableLiveData<>();
        if ((HomeFragment.getWeek() < 2 || HomeFragment.getWeek() % 2 == 1) && HomeFragment.getWeek() <= 12) {
            mText.setValue("Upcoming\n MP " + HomeFragment.getWeek() / 2 + ": " + mpNames[HomeFragment.getWeek() / 2]);
        } else if (HomeFragment.getWeek() > 12) {
            mText.setValue("There are no more MPs left in this semester!");
        } else {
            mText.setValue("MP " + (HomeFragment.getWeek() / 2 - 1) + ": " + mpNames[HomeFragment.getWeek() / 2 - 1]);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}
