package com.example.geoffsgeob.ui.midterm;

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

import java.util.Random;

public class MidtermFragment extends Fragment {

    interface iLikePrettyCode {
        void onSubmit();
    }
    private MidtermViewModel midtermViewModel;
    private int optimumMidterm;
    private Spinner midterm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        midtermViewModel =
                ViewModelProviders.of(this).get(MidtermViewModel.class);
        View root = inflater.inflate(R.layout.fragment_midterm, container, false);
        final TextView text = root.findViewById(R.id.text_midterm);
        midtermViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text.setText(s);
            }
        });

        midterm = root.findViewById(R.id.midtermDifficulty);

        final TextView submitted = root.findViewById(R.id.submittedMidterm);
        final TextView advice = root.findViewById(R.id.midtermAdvice);
        final TextView enterDiff = root.findViewById(R.id.enterDifficultyMidterm);
        TextView difficultySet = root.findViewById(R.id.difficultySet);
        difficultySet.setVisibility(View.GONE);
        final Button submitButton = root.findViewById(R.id.midtermSubmit);
        submitted.setVisibility(View.GONE);

        iLikePrettyCode r = () ->
        {
            midterm.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            advice.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitted.setVisibility(View.VISIBLE);
            difficultySet.setVisibility(View.VISIBLE);
            difficultySet.setText("You selected a difficulty of " + MainActivity.getMidtermSelection());
        };

        submitButton.setOnClickListener(v -> {
            MainActivity.setMidtermSubmit(true);
            int midtermChange = 2 - Math.abs(MainActivity.getMidtermSelection() - optimumMidterm);
            MainActivity.thisWeekChange(0, midtermChange);
            r.onSubmit();
        });

        if ((MainActivity.getWeek() + 1) % 5 != 0) {
            midterm.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            advice.setText(R.string.no_midterm);
        } else {
            midterm.setVisibility(View.VISIBLE);
            enterDiff.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            Random random = new Random();
            optimumMidterm = random.nextInt(10);

            runSpinner();
        }

        if (MainActivity.getMidtermSubmit()) {
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
        midterm.setAdapter(adapter);

        midterm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                // Called when the user selects a different item in the dropdown
                // The position parameter is the selected index
                // The other parameters can be ignored
                MainActivity.setMidtermSelection(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank

            }
        });
    }
}
