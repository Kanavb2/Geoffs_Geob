package com.example.geoffsgeob.ui.mp;

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

public class MpFragment extends Fragment {

    interface iLikePrettyCode {
        void onSubmit();
    }
    private MpViewModel mpViewModel;
    public static int optimumMp;
    private Spinner mp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mpViewModel =
                ViewModelProviders.of(this).get(MpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mp, container, false);
        final TextView text = root.findViewById(R.id.text_mp);
        mpViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text.setText(s);
            }
        });

        mp = root.findViewById(R.id.mpDifficulty);

        final TextView enterDiff = root.findViewById(R.id.enterDifficultyMp);
        final Button submitButton = root.findViewById(R.id.mpSubmit);
        final TextView advice = root.findViewById(R.id.mpAdvice);
        TextView difficultySet = root.findViewById(R.id.difficultySet);
        difficultySet.setVisibility(View.GONE);
        final TextView submitted = root.findViewById(R.id.submittedMP);
        submitted.setVisibility(View.GONE);

        iLikePrettyCode r = () ->
        {
            mp.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            advice.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitted.setVisibility(View.VISIBLE);
            difficultySet.setVisibility(View.VISIBLE);
            difficultySet.setText("You selected a difficulty of " + MainActivity.getMpSelection());
        };

        submitButton.setOnClickListener(v -> {
            MainActivity.setMPSubmit(true);
            int mpChange = (2 - Math.abs(MainActivity.getMpSelection() - optimumMp)) * 5 / 4;
            HomeFragment.mpChange = mpChange;
            MainActivity.thisWeekChange(0, mpChange);
            r.onSubmit();
        });

        if ((MainActivity.getWeek() < 2 || MainActivity.getWeek() % 2 == 1) && MainActivity.getWeek() <= 12) {
            mp.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            advice.setText(R.string.no_mp);
        } else if (MainActivity.getWeek() > 12) {
            advice.setVisibility(View.GONE);
            mp.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
        } else {
            Random random = new Random();
            optimumMp = random.nextInt(10);
            if (optimumMp <= 2) {
                advice.setText(R.string.mp_advice_02);
            } else if (optimumMp <=  6) {
                advice.setText(R.string.mp_advice_36);
            } else {
                advice.setText(R.string.mp_advice_79);
            }
            enterDiff.setVisibility(View.VISIBLE);
            mp.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);

            runSpinner();
        }

        if (MainActivity.getMPSubmit()) {
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
        mp.setAdapter(adapter);

        mp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                // Called when the user selects a different item in the dropdown
                // The position parameter is the selected index
                // The other parameters can be ignored
                MainActivity.setMpSelection(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                // Called when the selection becomes empty
                // Not relevant to the MP - can be left blank
            }
        });
    }
}
