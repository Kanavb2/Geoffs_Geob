package com.example.geoffsgeob.ui.midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.R;

import java.util.Random;

public class MidtermFragment extends Fragment {

    private MidtermViewModel midtermViewModel;
    private int midtermDifficulty;
    private static int week;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        midtermViewModel =
                ViewModelProviders.of(this).get(MidtermViewModel.class);
        View root = inflater.inflate(R.layout.fragment_midterm, container, false);
        final TextView textView = root.findViewById(R.id.text_midterm);
        midtermViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ProgressBar studentProgress = root.findViewById(R.id.studentProgress);
        ProgressBar universityProgress = root.findViewById(R.id.universityProgress);
        Random random = new Random();
        Spinner midterm = root.findViewById(R.id.midtermDifficulty);
        if (week % 5 != 0) {
            midterm.setVisibility(View.GONE);
        } else {
            midterm.setVisibility(View.VISIBLE);
            int optimumMidterm = random.nextInt(10);
            midterm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {
                    // Called when the user selects a different item in the dropdown
                    // The position parameter is the selected index
                    // The other parameters can be ignored
                    midtermDifficulty = position;
                }
                @Override
                public void onNothingSelected(final AdapterView<?> parent) {
                    // Called when the selection becomes empty
                    // Not relevant to the MP - can be left blank
                }
            });
            int midtermChange = Math.abs(midtermDifficulty - optimumMidterm) + 2;
            studentProgress.setProgress(midtermChange, true);
        }

        return root;
    }

    public static void nextWeek() {
        week++;
    }
}
