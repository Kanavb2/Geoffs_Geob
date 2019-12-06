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

        Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        final TextView homeTitle = findViewById(R.id.help_home_title);
        final TextView homeBody = findViewById(R.id.help_home_body);
        // hide until its title is clicked
        homeBody.setVisibility(View.GONE);

        final TextView mailTitle = findViewById(R.id.help_mail_title);
        final TextView mailBody = findViewById(R.id.help_mail_body);
        // hide until its title is clicked
        mailBody.setVisibility(View.GONE);

        final TextView hqmmTitle = findViewById(R.id.help_hqmm_title);
        final TextView hqmmBody = findViewById(R.id.help_hqmm_body);
        // hide until its title is clicked
        hqmmBody.setVisibility(View.GONE);


        final TextView forumTitle = findViewById(R.id.help_forum_title);
        final TextView forumBody = findViewById(R.id.help_forum_body);
        // hide until its title is clicked
        forumBody.setVisibility(View.GONE);


        homeTitle.setOnClickListener(v -> {
            if (homeBody.isShown()) {
                homeBody.setVisibility(View.GONE);
                homeBody.startAnimation(animSlideUp);
            } else {
                homeBody.setVisibility(View.VISIBLE);
                homeBody.startAnimation(animSlideDown);
                if (forumBody.isShown()) {
                    forumBody.setVisibility(View.GONE);
                    forumBody.startAnimation(animSlideUp);
                }
                if (hqmmBody.isShown()) {
                    hqmmBody.setVisibility(View.GONE);
                    hqmmBody.startAnimation(animSlideUp);
                }
                if (mailBody.isShown()) {
                    mailBody.setVisibility(View.GONE);
                    mailBody.startAnimation(animSlideUp);
                }
            }
        });

        forumTitle.setOnClickListener(v -> {
            if (forumBody.isShown()) {
                forumBody.setVisibility(View.GONE);
                forumBody.startAnimation(animSlideUp);
            } else {
                forumBody.setVisibility(View.VISIBLE);
                forumBody.startAnimation(animSlideDown);
                if (hqmmBody.isShown()) {
                    hqmmBody.setVisibility(View.GONE);
                    hqmmBody.startAnimation(animSlideUp);
                }
                if (mailBody.isShown()) {
                    mailBody.setVisibility(View.GONE);
                    mailBody.startAnimation(animSlideUp);
                }
                if (homeBody.isShown()) {
                    homeBody.setVisibility(View.GONE);
                    homeBody.startAnimation(animSlideUp);
                }
            }
        });



        mailTitle.setOnClickListener(v -> {
            if (mailBody.isShown()) {
                mailBody.setVisibility(View.GONE);
                mailBody.startAnimation(animSlideUp);
            } else {
                mailBody.setVisibility(View.VISIBLE);
                mailBody.startAnimation(animSlideDown);
                if (homeBody.isShown()) {
                    homeBody.setVisibility(View.GONE);
                    homeBody.startAnimation(animSlideUp);
                }
                if (forumBody.isShown()) {
                    forumBody.setVisibility(View.GONE);
                    forumBody.startAnimation(animSlideUp);
                }
                if (hqmmBody.isShown()) {
                    hqmmBody.setVisibility(View.GONE);
                    hqmmBody.startAnimation(animSlideUp);

                }
            }
        });


        hqmmTitle.setOnClickListener(v -> {
            if (hqmmBody.isShown()) {
                hqmmBody.setVisibility(View.GONE);
                hqmmBody.startAnimation(animSlideUp);


            } else {
                hqmmBody.setVisibility(View.VISIBLE);
                hqmmBody.startAnimation(animSlideDown);
                if (homeBody.isShown()) {
                    homeBody.setVisibility(View.GONE);
                    homeBody.startAnimation(animSlideUp);
                }
                if (forumBody.isShown()) {
                    forumBody.setVisibility(View.GONE);
                    forumBody.startAnimation(animSlideUp);
                }
                if (mailBody.isShown()) {
                    mailBody.setVisibility(View.GONE);
                    mailBody.startAnimation(animSlideUp);
                }
            }
        });
    }
}