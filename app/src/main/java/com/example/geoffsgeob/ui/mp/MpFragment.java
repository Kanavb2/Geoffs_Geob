package com.example.geoffsgeob.ui.mp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.R;

public class MpFragment extends Fragment {

    private MpViewModel mpViewModel;
    private int mpDifficulty;
    private static int week;

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

        week = 0;
        return root;
    }
}
