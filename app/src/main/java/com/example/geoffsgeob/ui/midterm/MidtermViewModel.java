package com.example.geoffsgeob.ui.midterm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

public class MidtermViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MidtermViewModel(Application application) {
        super(application);
        String[] midtermNames = getApplication().getResources().getStringArray(R.array.midterm_names);
        mText = new MutableLiveData<>();
        if ((HomeFragment.getWeek() + 1) % 5 == 0) {
            mText.setValue("Midterm " + (HomeFragment.getWeek()) / 5 );
        } else {
            mText.setValue("Upcoming\n Midterm " + HomeFragment.getWeek() / 5 + ": " + midtermNames[HomeFragment.getWeek() / 5]);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}
