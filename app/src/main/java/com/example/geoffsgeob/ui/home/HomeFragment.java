package com.example.geoffsgeob.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.Bonuses;
import com.example.geoffsgeob.EndGame;
import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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

        Button button = root.findViewById(R.id.bonuses);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), Bonuses.class);
            startActivity(intent);
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
                if (MainActivity.getWeek() == MainActivity.getTotalWeeks()) {
                    Intent intent = new Intent(getActivity(), EndGame.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                HomeFragment.progressBar(MainActivity.getUniChange(), MainActivity.getStudentChange());
                MainActivity.resetWeekChange();
                textView.setText("Fall 2019: Week " + MainActivity.getWeek());
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                List<String> list = new ArrayList<>();
                if (!MainActivity.getHWSubmit()) {
                    list.add("Homework\n");
                }
                if (!MainActivity.quizDone()) {
                    list.add("Quiz\n");
                }
                if (!MainActivity.midtermDone()) {
                    list.add("Midterm\n");
                }
                if (!MainActivity.mpDone()) {
                    list.add("Machine Project\n");
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("You can not move on to the next week till you set the difficulties for the following: \n\n");
                for (int i = 0; i < list.size(); i++) {
                    stringBuilder.append(list.get(i));
                }
                builder.setMessage(stringBuilder);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

        MainActivity.setStudentProgress(studentProgress);
        MainActivity.setUniversityProgress(universityProgress);

        universityBar.setProgress(MainActivity.getUniversityProgress(), true);
        studentBar.setProgress(MainActivity.getStudentProgress(), true);

        TextView universityNumber = root.findViewById(R.id.universityNumber);
        TextView studentNumber = root.findViewById(R.id.studentNumber);

        universityNumber.setText(universityBar.getProgress() + "%");
        studentNumber.setText(studentBar.getProgress() + "%");

    }

    public static void hideProgressBars() {
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);

        TextView universityNumber = root.findViewById(R.id.universityNumber);
        TextView studentNumber = root.findViewById(R.id.studentNumber);

        TextView university = root.findViewById(R.id.universitySatisfaction);
        TextView student = root.findViewById(R.id.studentSatisfaction);

        universityBar.setVisibility(View.GONE);
        studentBar.setVisibility(View.GONE);
        universityNumber.setVisibility(View.GONE);
        studentNumber.setVisibility(View.GONE);
        university.setVisibility(View.GONE);
        student.setVisibility(View.GONE);
    }
    public static void showProgressBars() {
        ProgressBar universityBar = root.findViewById(R.id.universityProgress);
        ProgressBar studentBar = root.findViewById(R.id.studentProgress);

        TextView universityNumber = root.findViewById(R.id.universityNumber);
        TextView studentNumber = root.findViewById(R.id.studentNumber);

        TextView university = root.findViewById(R.id.universitySatisfaction);
        TextView student = root.findViewById(R.id.studentSatisfaction);

        universityBar.setVisibility(View.VISIBLE);
        studentBar.setVisibility(View.VISIBLE);
        universityNumber.setVisibility(View.VISIBLE);
        studentNumber.setVisibility(View.VISIBLE);
        university.setVisibility(View.VISIBLE);
        student.setVisibility(View.VISIBLE);
    }
    public static void hideEncounters() {
        TextView randomEncounters = root.findViewById(R.id.encounter);
        Button yesButton = root.findViewById(R.id.positiveButton);
        Button noButton = root.findViewById(R.id.negativeButton);

        randomEncounters.setVisibility(View.GONE);
        yesButton.setVisibility(View.GONE);
        noButton.setVisibility(View.GONE);
    }
    public static void showEncounters() {
        TextView randomEncounters = root.findViewById(R.id.encounter);
        Button yesButton = root.findViewById(R.id.positiveButton);
        Button noButton = root.findViewById(R.id.negativeButton);

        randomEncounters.setVisibility(View.VISIBLE);
        yesButton.setVisibility(View.VISIBLE);
        noButton.setVisibility(View.VISIBLE);
    }
    public static void hideBonuses() {
        Button button = root.findViewById(R.id.bonuses);
        button.setVisibility(View.GONE);
    }
    public static void showBonuses() {
        Button button = root.findViewById(R.id.bonuses);
        button.setVisibility(View.VISIBLE);
    }
}