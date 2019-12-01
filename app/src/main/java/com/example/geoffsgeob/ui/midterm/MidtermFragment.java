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

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

import java.util.Random;

public class MidtermFragment extends Fragment {

    private MidtermViewModel midtermViewModel;
    private int midtermDifficulty;
    private Spinner midterm;

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

        midterm = root.findViewById(R.id.midtermDifficulty);
        TextView midtermAdvice = root.findViewById(R.id.midtermAdvice);
        TextView enterDiff = root.findViewById(R.id.enterDifficultyMidterm);
        Button submitButton = root.findViewById(R.id.midtermSubmit);
        if ((HomeFragment.getWeek() + 1) % 5 != 0) {
            midterm.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            midtermAdvice.setText(R.string.no_midterm);
        } else {
            midterm.setVisibility(View.VISIBLE);
            enterDiff.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            Random random = new Random();
            int optimumMidterm = random.nextInt(10);

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
                    midtermDifficulty = position;
                }

                @Override
                public void onNothingSelected(final AdapterView<?> parent) {
                    // Called when the selection becomes empty
                    // Not relevant to the MP - can be left blank

                }
            });
            int midtermChange = Math.abs(midtermDifficulty - optimumMidterm) + 2;
            HomeFragment.progressBar(0, midtermChange);
        }
        return root;
    }
}
