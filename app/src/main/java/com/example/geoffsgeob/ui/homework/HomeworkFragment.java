package com.example.geoffsgeob.ui.homework;

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

import org.w3c.dom.Text;

import java.util.Random;

public class HomeworkFragment extends Fragment {

    private HomeworkViewModel homeworkViewModel;
    private int homeworkDifficulty;
    private Spinner homework;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeworkViewModel =
                ViewModelProviders.of(this).get(HomeworkViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homework, container, false);
        final TextView text = root.findViewById(R.id.text_homework);
        homeworkViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text.setText(s);
            }
        });

        final TextView advice = root.findViewById(R.id.hwAdvice);
        final TextView enterDifficulty = root.findViewById(R.id.enterDifficultyHw);
        final TextView submitted = root.findViewById(R.id.submittedHw);
        submitted.setVisibility(View.GONE);
        homework = root.findViewById(R.id.hwDifficulty);
        final Button submitButton = root.findViewById(R.id.hwSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MainActivity.toggleHW();
                homework.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                advice.setVisibility(View.GONE);
                enterDifficulty.setVisibility(View.GONE);
                submitted.setVisibility(View.VISIBLE);
            }
        });

        if (MainActivity.getHWSubmit()) {
            homework.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            advice.setVisibility(View.GONE);
            enterDifficulty.setVisibility(View.GONE);
            submitted.setVisibility(View.VISIBLE);

        }
        Random random = new Random();
        int optimumHW = random.nextInt(10);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.difficulty, R.layout.spinner_layout);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        // Apply the adapter to the spinner
        homework.setAdapter(adapter);

        homework.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                // Called when the user selects a different item in the dropdown
                // The position parameter is the selected index
                // The other parameters can be ignored
                homeworkDifficulty = position;
            }
            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });

        int hwChange = (homeworkDifficulty - optimumHW) - 2;  //create a function: +3, +2, +1, 0, -1, -2....
        HomeFragment.progressBar(0, hwChange); //put function in mainactivity
        //put homefragment.nextweek in MainActivity
        //do the boolean logic thing
        //previous week = hwdifficulty using that one variable that doesn't change until the end of the function

        return root;
    }
}
