package com.example.geoffsgeob.ui.mail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class MailFragment extends Fragment {

    private MailViewModel mailViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mailViewModel =
                ViewModelProviders.of(this).get(MailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mail, container, false);
        final TextView textView = root.findViewById(R.id.text_mail);
        mailViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        TextView mailSubject = root.findViewById(R.id.mailSubject);
        TextView mainBody = root.findViewById(R.id.mainBody);
        TextView outro = root.findViewById(R.id.outro);

        outro.setVisibility(View.VISIBLE);

        if (MainActivity.encounterButtons == 1) {
            int[] arrFirstUniversity = {0, 5, -5, -3, -5, -3, -2, 5, 3, -3, 4, -2, -2, +3, -5, +7, 0};
            if (arrFirstUniversity[MainActivity.getWeek()] > 0) {
                outro.setText(R.string.outro_good);
            } else if (arrFirstUniversity[MainActivity.getWeek()] < 0) {
                outro.setText(R.string.outro_bad);
            } else {
                outro.setVisibility(View.GONE);
            }
        }
        if (MainActivity.encounterButtons == 2) {
            int[] arrSecondUniversity = {0, 0, 3, 5, 3, 2, -5, -3, -4, 0, -5, 2, -3, -3, 0, -7, 0};
            if (arrSecondUniversity[MainActivity.getWeek()] > 0) {
                outro.setText(R.string.outro_good);
            } else if (arrSecondUniversity[MainActivity.getWeek()] < 0) {
                outro.setText(R.string.outro_bad);
            } else {
                outro.setVisibility(View.GONE);
            }
        }

        return root;
    }
}