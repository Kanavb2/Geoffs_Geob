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
import com.example.geoffsgeob.ui.homework.HomeworkFragment;
import com.example.geoffsgeob.ui.midterm.MidtermFragment;
import com.example.geoffsgeob.ui.mp.MpFragment;
import com.example.geoffsgeob.ui.quiz.QuizFragment;

import java.util.Random;

public class ForumFragment extends Fragment {

    private ForumViewModel forumViewModel;
    private static int hwChange;
    private static int quizChange;
    private static int midtermChange;
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
        TextView extraForum = root.findViewById(R.id.extraForum);
        TextView extraForumText = root.findViewById(R.id.extraForumText);
        View d1 = root.findViewById(R.id.d1);
        View d2 = root.findViewById(R.id.d2);
        View d3 = root.findViewById(R.id.d3);
        View d4 = root.findViewById(R.id.d4);
        View d5 = root.findViewById(R.id.d5);


        if (MainActivity.getWeek() != 0) {
            if (MainActivity.encounterButtons == 1) {
                String[] encounterArrayFirst = root.getResources().getStringArray(R.array.encounterForumFirstNames);
                String[] encounterArrayFirstText = root.getResources().getStringArray(R.array.encounterForumFirst);
                encounterForum.setText(encounterArrayFirst[MainActivity.getWeek()]);
                encounterForumText.setText(encounterArrayFirstText[MainActivity.getWeek()]);
            }
            if (MainActivity.encounterButtons == 2) {
                String[] encounterArraySecond = root.getResources().getStringArray(R.array.encounterForumSecondNames);
                String[] encounterArraySecondText = root.getResources().getStringArray(R.array.encounterForumSecond);
                encounterForum.setText(encounterArraySecond[MainActivity.getWeek()]);
                encounterForumText.setText(encounterArraySecondText[MainActivity.getWeek()]);
            }

            String[] studentNames = root.getResources().getStringArray(R.array.student_names);
            Random random = new Random();
            int hwName = random.nextInt(50);
            int quizName = random.nextInt(50);
            int mpName = random.nextInt(50);

            hwForum.setText(studentNames[hwName]);
            quizForum.setText(studentNames[quizName]);
            mpForum.setText(studentNames[mpName]);

            if (hwChange < -1 && MainActivity.getHwSelection() > HomeworkFragment.optimumHw) {
                hwForumText.setText(R.string.forum_hw_hard);
            } else if (hwChange < -1 && MainActivity.getHwSelection() < HomeworkFragment.optimumHw) {
                hwForumText.setText(R.string.forum_hw_easy);
            } else {
                hwForumText.setText(R.string.forum_hw_perfect);
            }

            if (quizChange < -1 && MainActivity.getQuizSelection() > QuizFragment.optimumQuiz) {
                quizForumText.setText(R.string.forum_quiz_hard);
            } else if (quizChange < -1 && MainActivity.getQuizSelection() < QuizFragment.optimumQuiz) {
                quizForumText.setText(R.string.forum_quiz_easy);
            } else {
                quizForumText.setText(R.string.forum_quiz_perfect);
            }

            if ((MainActivity.getWeek() + 2) % 5 == 0) {
                if (midtermChange < -1 && MainActivity.getMidtermSelection() > MidtermFragment.optimumMidterm) {
                    quizForumText.setText(R.string.forum_midterm_hard);
                } else if (midtermChange < -1 && MainActivity.getMidtermSelection() < MidtermFragment.optimumMidterm) {
                    quizForumText.setText(R.string.forum_midterm_easy);
                } else {
                    quizForumText.setText(R.string.forum_midterm_perfect);
                }
            }

            if (mpChange < -1 && MainActivity.getMpSelection() > MpFragment.optimumMp) {
                mpForumText.setText(R.string.forum_mp_hard);
            } else if (mpChange < -1 && MainActivity.getMpSelection() < MpFragment.optimumMp) {
                mpForumText.setText(R.string.forum_mp_easy);
            } else {
                mpForumText.setText(R.string.forum_mp_perfect);
            }

            String[] extraForumArray = root.getResources().getStringArray(R.array.extra_names);
            String[] extraForumTextArray = root.getResources().getStringArray(R.array.extra_text);

            extraForum.setText(extraForumArray[MainActivity.getWeek()]);
            extraForumText.setText(extraForumTextArray[MainActivity.getWeek()]);

            if (MainActivity.getWeek() == 2) {
                extraForum.setText(R.string.geoff);
                extraForum.setText(R.string.iclicker_rant);
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
            extraForum.setVisibility(View.VISIBLE);
            extraForumText.setVisibility(View.VISIBLE);
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
            d5.setVisibility(View.VISIBLE);
            if (MainActivity.getWeek() < 2 || MainActivity.getWeek() % 2 == 0 || MainActivity.getWeek() > 13) {
                mpForum.setVisibility(View.GONE);
                mpForumText.setVisibility(View.GONE);
                d4.setVisibility(View.GONE);
            }
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
            extraForum.setVisibility(View.GONE);
            extraForumText.setVisibility(View.GONE);
            d1.setVisibility(View.GONE);
            d2.setVisibility(View.GONE);
            d3.setVisibility(View.GONE);
            d4.setVisibility(View.GONE);
            d5.setVisibility(View.GONE);
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
    public static void setMidtermChange(int set) {
        midtermChange = set;
    }
}
