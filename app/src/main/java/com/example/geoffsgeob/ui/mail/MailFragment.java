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

        TextView mailSender = root.findViewById(R.id.mailSender);
        TextView mailSubject = root.findViewById(R.id.mailSubject);
        TextView mainBody = root.findViewById(R.id.mainBody);
        TextView outro = root.findViewById(R.id.outro);
        TextView endMail = root.findViewById(R.id.endMail);

        outro.setVisibility(View.VISIBLE);

        if (MainActivity.getWeek() == 3 || MainActivity.getWeek() == 9) {
            mailSender.setText(R.string.mailTACA);
            endMail.setText(R.string.mailClosingTACA);
        } else {
            mailSender.setText(R.string.mailAdmin);
            endMail.setText(R.string.mailClosingAdmin);
            if (MainActivity.getWeek() == 10 && MainActivity.getEncounterButtons() == 2) {
                mailSender.setText(R.string.mailBoth);
                mailSender.setText(R.string.mailCLosingEverybody);
            }
        }

        String[] mailSubjectArray = root.getResources().getStringArray(R.array.mailSubject);
        mailSubject.setText(mailSubjectArray[MainActivity.getWeek()]);
        if (MainActivity.getWeek() == 1 && MainActivity.getEncounterButtons() == 2) {
            mailSubject.setText(R.string.check_in);
        }
        if (MainActivity.getWeek() == 7 && MainActivity.getEncounterButtons() == 2) {
            mailSubject.setText(R.string.stressed_students);
        }

        if (MainActivity.getEncounterButtons() == 1) {
            String[] encounterMailFirst = root.getResources().getStringArray(R.array.encounterMailFirst);
            mainBody.setText(encounterMailFirst[MainActivity.getWeek()]);
        } else if (MainActivity.getEncounterButtons() == 2) {
            String[] encounterMailSecond = root.getResources().getStringArray(R.array.encounterMailSecond);
            mainBody.setText(encounterMailSecond[MainActivity.getWeek()]);
        }

        if (MainActivity.getWeek() == 0) {
            mainBody.setText(R.string.mail_welcome);
            outro.setVisibility(View.GONE);
        }

        if (MainActivity.getEncounterButtons() == 1) {
            int[] arrFirstUniversity = {0, 5, -5, -3, -5, -3, -2, 5, 4, -3, 4, -2, -2, +3, -3, +7, 0};
            if (arrFirstUniversity[MainActivity.getWeek()] > 0) {
                outro.setText(R.string.outro_good);
            } else if (arrFirstUniversity[MainActivity.getWeek()] < 0) {
                outro.setText(R.string.outro_bad);
            } else {
                outro.setVisibility(View.GONE);
            }
        }
        if (MainActivity.getEncounterButtons() == 2) {
            int[] arrSecondUniversity = {0, 0, 4, 5, 3, 4, -5, -3, -4, 0, -5, 2, -3, -3, 0, -7, 0};
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