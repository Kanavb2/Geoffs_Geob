package com.example.geoffsgeob.ui.forum;

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

import com.example.geoffsgeob.MainActivity;
import com.example.geoffsgeob.R;

public class ForumFragment extends Fragment {

    private ForumViewModel forumViewModel;
    private static int hwChange;
    private static int quizChange;
    private static int mpChange;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                ViewModelProviders.of(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        final TextView textView = root.findViewById(R.id.text_forum);
        forumViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        TextView encounterForum = root.findViewById(R.id.encounterForum);
        TextView encounterForumText = root.findViewById(R.id.encounterForumText);
        TextView hwForum = root.findViewById(R.id.hwForum);
        TextView hwForumText = root.findViewById(R.id.hwForumText);
        TextView quizForum = root.findViewById(R.id.quizForum);
        TextView quizForumText = root.findViewById(R.id.quizForumText);
        TextView mpForum = root.findViewById(R.id.mpForum);
        TextView mpForumText = root.findViewById(R.id.mpForumText);
        TextView noPosts = root.findViewById(R.id.noPosts);
        View d1 = root.findViewById(R.id.d1);
        View d2 = root.findViewById(R.id.d2);
        View d3 = root.findViewById(R.id.d3);
        View d4 = root.findViewById(R.id.d4);

        if (MainActivity.getWeek() < 2 || MainActivity.getWeek() % 2 == 0 || MainActivity.getWeek() > 13) {
            mpForum.setVisibility(View.GONE);
            mpForumText.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
        }

        if (MainActivity.getWeek() != 0) {
            if (MainActivity.getEncounterAnswer().equals("first")) {
                String[] encounterArrayFirst = root.getResources().getStringArray(R.array.encounterForumFirst);
                encounterForumText.setText(encounterArrayFirst[MainActivity.getWeek()]);
            }
            if (MainActivity.getEncounterAnswer().equals("second")) {
                String[] encounterArraySecond = root.getResources().getStringArray(R.array.encounterForumSecond);
                encounterForumText.setText(encounterArraySecond[MainActivity.getWeek()]);
            }

            if (Math.abs(hwChange) < 3) {
                hwForumText.setText("you done good");
            } else if (Math.abs(hwChange) < 6) {
                hwForumText.setText("you done bad");
            } else {
                hwForumText.setText("you done fucked up");
            }

            if (Math.abs(quizChange) < 2) {

            } else if (Math.abs(quizChange) < 6) {

            } else {

            }

            if (Math.abs(mpChange) < 2) {

            } else if (Math.abs(mpChange) < 6) {

            } else {

            }

            noPosts.setVisibility(View.GONE);
            encounterForum.setVisibility(View.VISIBLE);
            encounterForumText.setVisibility(View.VISIBLE);
            hwForum.setVisibility(View.VISIBLE);
            hwForumText.setVisibility(View.VISIBLE);
            quizForum.setVisibility(View.VISIBLE);
            quizForumText.setVisibility(View.VISIBLE);
            mpForum.setVisibility(View.VISIBLE);
            mpForumText.setVisibility(View.VISIBLE);
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
        } else {
            noPosts.setVisibility(View.VISIBLE);
            encounterForum.setVisibility(View.GONE);
            encounterForumText.setVisibility(View.GONE);
            hwForum.setVisibility(View.GONE);
            hwForumText.setVisibility(View.GONE);
            quizForum.setVisibility(View.GONE);
            quizForumText.setVisibility(View.GONE);
            mpForum.setVisibility(View.GONE);
            mpForumText.setVisibility(View.GONE);
            d1.setVisibility(View.GONE);
            d2.setVisibility(View.GONE);
            d3.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
        }

        return root;
    }

    public static void setHwChange(int set) {
        hwChange = set;
    }
    public static void setQuizChange(int set) {
        quizChange = set;
    }
    public static void setMpChange(int set) {
        mpChange = set;
    }
}
