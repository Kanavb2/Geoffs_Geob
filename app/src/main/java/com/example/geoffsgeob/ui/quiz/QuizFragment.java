package com.example.geoffsgeob.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

import java.util.Random;

public class QuizFragment extends Fragment {

    private QuizViewModel quizViewModel;
    private static int quizDifficulty;
    private static Spinner quiz;
    private static View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(QuizViewModel.class);
        root = inflater.inflate(R.layout.fragment_quiz, container, false);
        final TextView textView = root.findViewById(R.id.text_quiz);
        quizViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        quiz = root.findViewById(R.id.quizDifficulty);

        Random random = new Random();
        int optimumQuiz = random.nextInt(10);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.difficulty, R.layout.spinner_layout);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        // Apply the adapter to the spinner
        quiz.setAdapter(adapter);

        quiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                // Called when the user selects a different item in the dropdown
                // The position parameter is the selected index
                // The other parameters can be ignored
                quizDifficulty = position;
            }
            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });
        int quizChange = Math.abs(quizDifficulty - optimumQuiz) + 2;
        HomeFragment.progressBar(0, quizChange);

        return root;
    }

    public static void nextWeek() {

        TextView quizAdvice = root.findViewById(R.id.quizAdvice);
        if ((HomeFragment.getWeek() + 1) % 5 == 0) {
            quiz.setVisibility(View.GONE);
            quizAdvice.setText("There are no quizzes this week. Check back later!");
        } else {
            Random random = new Random();
            int optimumHW = random.nextInt(10);
            quiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(final AdapterView<?> parent, final View view,
                                           final int position, final long id) {
                    // Called when the user selects a different item in the dropdown
                    // The position parameter is the selected index
                    // The other parameters can be ignored
                    quizDifficulty = position;
                }
                @Override
                public void onNothingSelected(final AdapterView<?> parent) {
                    // Called when the selection becomes empty
                    // Not relevant to the MP - can be left blank
                }
            });
            int hwChange = Math.abs(quizDifficulty - optimumHW) + 2;
            HomeFragment.progressBar(0, hwChange);
        }

    }
}
