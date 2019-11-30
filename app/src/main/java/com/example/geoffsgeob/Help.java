package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Help extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        final TextView homeTitle = findViewById(R.id.help_home_title);
        final TextView homeBody = findViewById(R.id.help_home_body);
        // hide until its title is clicked
        homeBody.setVisibility(View.GONE);

        homeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeBody.isShown()) {
                    homeBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    homeBody.startAnimation(animSlideUp);
                } else {
                    homeBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    homeBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView forumTitle = findViewById(R.id.help_forum_title);
        final TextView forumBody = findViewById(R.id.help_forum_body);
        // hide until its title is clicked
        forumBody.setVisibility(View.GONE);

        forumTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forumBody.isShown()) {
                    forumBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    forumBody.startAnimation(animSlideUp);
                } else {
                    forumBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    forumBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView mailTitle = findViewById(R.id.help_mail_title);
        final TextView mailBody = findViewById(R.id.help_mail_body);
        // hide until its title is clicked
        mailBody.setVisibility(View.GONE);

        mailTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mailBody.isShown()) {
                    mailBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    mailBody.startAnimation(animSlideUp);
                } else {
                    mailBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    mailBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView hwTitle = findViewById(R.id.help_homework_title);
        final TextView hwBody = findViewById(R.id.help_homework_body);
        // hide until its title is clicked
        hwBody.setVisibility(View.GONE);

        hwTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hwBody.isShown()) {
                    hwBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    hwBody.startAnimation(animSlideUp);
                } else {
                    hwBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    hwBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView quizTitle = findViewById(R.id.help_quiz_title);
        final TextView quizBody = findViewById(R.id.help_quiz_body);
        // hide until its title is clicked
        quizBody.setVisibility(View.GONE);

        quizTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizBody.isShown()) {
                    quizBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    quizBody.startAnimation(animSlideUp);
                } else {
                    quizBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    quizBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView midtermTitle = findViewById(R.id.help_midterm_title);
        final TextView midtermBody = findViewById(R.id.help_midterm_body);
        // hide until its title is clicked
        midtermBody.setVisibility(View.GONE);

        midtermTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midtermBody.isShown()) {
                    midtermBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    midtermBody.startAnimation(animSlideUp);
                } else {
                    midtermBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    midtermBody.startAnimation(animSlideDown);
                }
            }
        });

        final TextView mpTitle = findViewById(R.id.help_mp_title);
        final TextView mpBody = findViewById(R.id.help_mp_body);
        // hide until its title is clicked
        mpBody.setVisibility(View.GONE);

        mpTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpBody.isShown()) {
                    mpBody.setVisibility(View.GONE);
                    Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    mpBody.startAnimation(animSlideUp);
                } else {
                    mpBody.setVisibility(View.VISIBLE);
                    Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    mpBody.startAnimation(animSlideDown);
                }
            }
        });


    }
}
