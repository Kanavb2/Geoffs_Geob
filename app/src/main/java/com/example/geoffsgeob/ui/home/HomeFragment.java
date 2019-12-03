package com.example.geoffsgeob.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (MainActivity.getHWSubmit()
                    && MainActivity.quizDone()
                    && MainActivity.midtermDone()
                    && MainActivity.mpDone()) {
                MainActivity.setHwSubmit(false);
                MainActivity.setMidtermSubmit(false);
                MainActivity.setMPSubmit(false);
                MainActivity.setQuizSubmit(false);
                MainActivity.nextWeek();
                textView.setText("Fall 2019: Week " + MainActivity.getWeek());
            } else {
                //alertdialog to tell the player which difficulty they haven't set yet
            }
        });
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);

        universityBar.setProgress(MainActivity.getUniversityProgress());
        studentBar.setProgress(MainActivity.getStudentProgress());

        TextView universityNumber = root.findViewById(R.id.universityNumber);
        TextView studentNumber = root.findViewById(R.id.studentNumber);

        universityNumber.setText(universityBar.getProgress() + "%");
        studentNumber.setText(studentBar.getProgress() + "%");

        return root;
    }

    public static void progressBar(int universityProgress, int studentProgress) {
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);

        universityBar.setProgress(universityProgress, true);
        studentBar.setProgress(studentProgress, true);

    }

    public static void hideProgressBars() {
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);
        universityBar.setVisibility(View.GONE);
        studentBar.setVisibility(View.GONE);
    }
    public static void showProgressBars() {
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);
        universityBar.setVisibility(View.VISIBLE);
        studentBar.setVisibility(View.VISIBLE);
    }
}