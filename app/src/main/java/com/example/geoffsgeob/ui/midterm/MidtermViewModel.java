package com.example.geoffsgeob.ui.midterm;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class MidtermViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MidtermViewModel(Application application) {
        super(application);
        String[] midtermNames = getApplication().getResources().getStringArray(R.array.midterm_names);
        mText = new MutableLiveData<>();
        if ((MainActivity.getWeek() + 1) % 5 == 0) {
            mText.setValue("Midterm " + (MainActivity.getWeek()) / 5 );
        } else {
            mText.setValue("Upcoming\n Midterm " + MainActivity.getWeek() / 5 + ": " + midtermNames[MainActivity.getWeek() / 5]);
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}
