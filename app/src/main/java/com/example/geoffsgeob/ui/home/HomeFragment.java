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
import com.example.geoffsgeob.ui.forum.ForumFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static View root;
    public static int hwChange;
    public static int quizChange;
    public static int mpChange;
    public static int midtermChange;

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
        if (MainActivity.getChuchu() && MainActivity.getBen() && MainActivity.getChallen()) {
            button.setVisibility(View.GONE);
        }
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), Bonuses.class);
            startActivity(intent);
        });

        TextView encounterHeader = root.findViewById(R.id.encounterHeader);
        String[] encounterArray = root.getResources().getStringArray(R.array.encounterHeader);
        encounterHeader.setText(encounterArray[MainActivity.getWeek()]);

        TextView encounter = root.findViewById(R.id.encounter);
        String[] encounterText = root.getResources().getStringArray(R.array.random_encounters);
        encounter.setText(encounterText[MainActivity.getWeek()]);

        Button firstButton = root.findViewById(R.id.firstButton);
        String[] positiveEncounter = root.getResources().getStringArray(R.array.positiveEncounter);
        firstButton.setText(positiveEncounter[MainActivity.getWeek()]);

        Button secondButton = root.findViewById(R.id.secondButton);
        String[] negativeEncounter= root.getResources().getStringArray(R.array.negativeEncounter);
        secondButton.setText(negativeEncounter[MainActivity.getWeek()]);

        if (!MainActivity.getEncounter()) {
            showEncounters();
        } else {
            hideEncounters();
        }

        firstButton.setOnClickListener(view -> {
            MainActivity.setEncounter(true);
            hideEncounters();
            MainActivity.encounterMeow = 1;
        });

        secondButton.setOnClickListener(view -> {
            MainActivity.setEncounter(true);
            hideEncounters();
            MainActivity.encounterMeow = 2;
        });

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            if (MainActivity.getHWSubmit()
                    && MainActivity.quizDone()
                    && MainActivity.midtermDone()
                    && MainActivity.mpDone()
                    && MainActivity.getEncounter()) {
                MainActivity.setHwSubmit(false);
                MainActivity.setMidtermSubmit(false);
                MainActivity.setMPSubmit(false);
                MainActivity.setQuizSubmit(false);
                MainActivity.setEncounter(false);
                showEncounters();
                MainActivity.nextWeek();
                encounterHeader.setText(encounterArray[MainActivity.getWeek()]);
                encounter.setText(encounterText[MainActivity.getWeek()]);
                firstButton.setText(positiveEncounter[MainActivity.getWeek()]);
                secondButton.setText(negativeEncounter[MainActivity.getWeek()]);
                ForumFragment.setHwChange(hwChange);
                ForumFragment.setQuizChange(quizChange);
                ForumFragment.setMpChange(mpChange);
                ForumFragment.setMidtermChange(midtermChange);
                MainActivity.setEncounterButtons(MainActivity.encounterMeow);
                encounterProgress();
                progressBar(MainActivity.getUniChange(), MainActivity.getStudentChange());
                MainActivity.resetWeekChange();
                textView.setText("Fall 2019: Week " + MainActivity.getWeek());
                if (MainActivity.getWeek() == MainActivity.getTotalWeeks() || MainActivity.getUniversityProgress() <= 0 || MainActivity.getStudentProgress() <= 0) {
                    Intent intent = new Intent(view.getContext(), EndGame.class);
                    startActivity(intent);
                    hideProgressBars();
                    hideBonuses();
                    hideEncounters();
                    textView.setVisibility(View.GONE);
                    if (MainActivity.getUniversityProgress() < 0) {
                        MainActivity.setUniversityProgress(0);
                    }
                    if (MainActivity.getStudentProgress() < 0) {
                        MainActivity.setStudentProgress(0);
                    }
                    EndGame.setStudentProgress(MainActivity.getStudentProgress());
                    EndGame.setUniversityProgress(MainActivity.getUniversityProgress());
                    getActivity().finish();
                }
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
                if (!MainActivity.getEncounter()) {
                    list.add("Encounter");
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("You can't move on to the next week until you set the difficulties for the following: \n\n");
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

    private void encounterProgress() {
        int[] arrFirstStudent = {-1, 5, 3, 5, 1, 3, 7, -4, +4, -1, 4, 1, -1, 7, -7, 0};
        int[] arrFirstUniversity = {5, -5, -3, -5, -3, -2, 5, 3, -3, 4, -2, -2, +3, -5, +7, 0};
        int[] arrSecondStudent = {0, -5, -5, -7, -4, -5, -7, 0, -3, -4, -4, -3, 4, -2, 2, 0};
        int[] arrSecondUniversity = {0, 3, 5, 3, 2, -5, -3, -4, 0, -5, 2, -3, -3, 0, -7, 0};


        if (MainActivity.getEncounterButtons() == 1) {
            MainActivity.thisWeekChange(arrFirstUniversity[MainActivity.getWeek() - 1], arrFirstStudent[MainActivity.getWeek() - 1      ]);
        }
        if (MainActivity.getEncounterButtons() == 2) {
            MainActivity.thisWeekChange(arrSecondUniversity[MainActivity.getWeek() - 1], arrSecondStudent[MainActivity.getWeek() - 1]);
        }
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
        Button yesButton = root.findViewById(R.id.firstButton);
        Button noButton = root.findViewById(R.id.secondButton);
        TextView encounterHeader = root.findViewById(R.id.encounterHeader);
        View divider = root.findViewById(R.id.divider);

        encounterHeader.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        randomEncounters.setVisibility(View.GONE);
        yesButton.setVisibility(View.GONE);
        noButton.setVisibility(View.GONE);
    }
    public static void showEncounters() {
        TextView randomEncounters = root.findViewById(R.id.encounter);
        Button yesButton = root.findViewById(R.id.firstButton);
        Button noButton = root.findViewById(R.id.secondButton);
        TextView encounterHeader = root.findViewById(R.id.encounterHeader);
        View divider = root.findViewById(R.id.divider);

        encounterHeader.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
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