package com.example.geoffsgeob.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

import java.util.Random;

public class QuizFragment extends Fragment {

    interface iLikePrettyCode {
        void onSubmit();
    }
    private QuizViewModel quizViewModel;
    private int optimumQuiz;
    private Spinner quiz;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        quizViewModel =
                ViewModelProviders.of(this).get(QuizViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quiz, container, false);
        final TextView text = root.findViewById(R.id.text_quiz);
        quizViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text.setText(s);
            }
        });

        quiz = root.findViewById(R.id.quizDifficulty);
        final TextView advice = root.findViewById(R.id.quizAdvice);
        final TextView enterDifficulty = root.findViewById(R.id.enterDifficultyQuiz);
        final TextView submitted = root.findViewById(R.id.submittedQuiz);
        TextView difficultySet = root.findViewById(R.id.difficultySet);
        difficultySet.setVisibility(View.GONE);
        final Button submitButton = root.findViewById(R.id.quizSubmit);
        submitted.setVisibility(View.GONE);

        iLikePrettyCode r = () ->
        {
            quiz.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            advice.setVisibility(View.GONE);
            enterDifficulty.setVisibility(View.GONE);
            submitted.setVisibility(View.VISIBLE);
            difficultySet.setVisibility(View.VISIBLE);
            difficultySet.setText("You selected a difficulty of " + MainActivity.getQuizSelection());
        };

        submitButton.setOnClickListener(v -> {
            MainActivity.setQuizSubmit(true);
            int quizChange = 2 - Math.abs(MainActivity.getQuizSelection() - optimumQuiz);
            HomeFragment.quizChange = quizChange;
            MainActivity.thisWeekChange(0, quizChange);
            r.onSubmit();
        });

        if ((MainActivity.getWeek() + 1) % 5 == 0) {
            quiz.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            enterDifficulty.setVisibility(View.GONE);
            advice.setText(R.string.no_quiz);
        } else {
            Random random = new Random();
            optimumQuiz = random.nextInt(10);
            if (optimumQuiz < 2) {
                advice.setText(R.string.quiz_advice_02);
            } else if (optimumQuiz < 6) {
                advice.setText(R.string.quiz_advice_36);
            } else {
                advice.setText(R.string.quiz_advice_79);
            }
            quiz.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            runSpinner();
        }

        if (MainActivity.getQuizSubmit()) {
            r.onSubmit();
        }
        return root;
    }

    public void runSpinner() {
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
                MainActivity.setQuizSelection(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });
    }
}
