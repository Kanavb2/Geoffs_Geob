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

import com.example.geoffsgeob.R;
import com.example.geoffsgeob.ui.home.HomeFragment;

import java.util.Random;

public class MpFragment extends Fragment {

    private MpViewModel mpViewModel;
    private int mpDifficulty;
    private Spinner mp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mpViewModel =
                ViewModelProviders.of(this).get(MpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mp, container, false);
        final TextView textView = root.findViewById(R.id.text_mp);
        mpViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mp = root.findViewById(R.id.mpDifficulty);
        TextView mpAdvice = root.findViewById(R.id.mpAdvice);
        TextView enterDiff = root.findViewById(R.id.enterDifficultyMp);
        Button submitButton = root.findViewById(R.id.mpSubmit);
        if ((HomeFragment.getWeek() < 2 || HomeFragment.getWeek() % 2 == 1) && HomeFragment.getWeek() <= 12) {
            mp.setVisibility(View.GONE);
            enterDiff.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            mpAdvice.setText(R.string.no_mp);

        } else if (HomeFragment.getWeek() > 12) {
            mpAdvice.setVisibility(View.GONE);

        } else {
            Random random = new Random();
            int optimumHW = random.nextInt(10);
            enterDiff.setVisibility(View.VISIBLE);
            mp.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);

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
                    mpDifficulty = position;
                }

                @Override
                public void onNothingSelected(final AdapterView<?> parent) {
                    // Called when the selection becomes empty
                    // Not relevant to the MP - can be left blank
                }
            });
            int hwChange = Math.abs(mpDifficulty - optimumHW) + 2;
            HomeFragment.progressBar(0, hwChange);
        }
        return root;
    }
}
