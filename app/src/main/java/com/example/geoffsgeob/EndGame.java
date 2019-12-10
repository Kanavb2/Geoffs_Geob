package com.example.geoffsgeob;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EndGame extends Activity {

    private static int universityProgress;
    private static int studentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        TextView endUniversity = findViewById(R.id.endUniversity);
        TextView endStudent = findViewById(R.id.endStudent);

        if (universityProgress < 0) {
            universityProgress = 0;
        }
        if (studentProgress < 0) {
            studentProgress = 0;
        }

        endUniversity.setText("Your final University Satisfaction: " + universityProgress);
        endStudent.setText("Your final Student Satisfaction: " + studentProgress);

        String[] student0 = getResources().getStringArray(R.array.endgame_student_0);
        String[] student40 = getResources().getStringArray(R.array.endgame_student_40);
        String[] student60 = getResources().getStringArray(R.array.endgame_student_60);
        String[] student80 = getResources().getStringArray(R.array.endgame_student_80);
        String[] student99 = getResources().getStringArray(R.array.endgame_student_99);
        String[] student100 = getResources().getStringArray(R.array.endgame_student_100);

        TextView ending = findViewById(R.id.ending);
        if (studentProgress <= 0 && universityProgress <= 0) {
            ending.setText(student0[0]);
        } else if (studentProgress <= 0) {
            ending.setText(student0[1]);
        } else if (universityProgress <= 0) {
            ending.setText(student40[0]);
        }

        if (studentProgress > 0 && studentProgress <= 40) {
            if (universityProgress > 0 && universityProgress <= 40) {
                ending.setText(student40[1]);
            } else if (universityProgress > 40 && universityProgress <= 60) {
                ending.setText(student40[2]);
            } else if (universityProgress > 60 && universityProgress <= 80) {
                ending.setText(student40[3]);
            } else if (universityProgress > 80 && universityProgress <= 99) {
                ending.setText(student40[4]);
            } else if (universityProgress >= 100) {
                ending.setText(student40[5]);
            }
        } else if (studentProgress > 40 && studentProgress <= 60) {
            if (universityProgress > 0 && universityProgress <= 40) {
                ending.setText(student60[1]);
            } else if (universityProgress > 40 && universityProgress <= 60) {
                ending.setText(student60[2]);
            } else if (universityProgress > 60 && universityProgress <= 80) {
                ending.setText(student60[3]);
            } else if (universityProgress > 80 && universityProgress <= 99) {
                ending.setText(student60[4]);
            } else if (universityProgress >= 100) {
                ending.setText(student60[5]);
            }
        } else if (studentProgress > 60 && studentProgress <= 80) {
            if (universityProgress > 0 && universityProgress <= 40) {
                ending.setText(student80[1]);
            } else if (universityProgress > 40 && universityProgress <= 60) {
                ending.setText(student80[2]);
            } else if (universityProgress > 60 && universityProgress <= 80) {
                ending.setText(student80[3]);
            } else if (universityProgress > 80 && universityProgress <= 99) {
                ending.setText(student80[4]);
            } else if (universityProgress >= 100) {
                ending.setText(student80[5]);
            }
        } else if (studentProgress > 80 && studentProgress <= 99) {
            if (universityProgress > 0 && universityProgress <= 40) {
                ending.setText(student99[1]);
            } else if (universityProgress > 40 && universityProgress <= 60) {
                ending.setText(student99[2]);
            } else if (universityProgress > 60 && universityProgress <= 80) {
                ending.setText(student99[3]);
            } else if (universityProgress > 80 && universityProgress <= 99) {
                ending.setText(student99[4]);
            } else if (universityProgress >= 100) {
                ending.setText(student99[5]);
            }
        } else if (studentProgress == 100) {
            if (universityProgress > 0 && universityProgress <= 40) {
                ending.setText(student100[1]);
            } else if (universityProgress > 40 && universityProgress <= 60) {
                ending.setText(student100[2]);
            } else if (universityProgress > 60 && universityProgress <= 80) {
                ending.setText(student100[3]);
            } else if (universityProgress > 80 && universityProgress <= 99) {
                ending.setText(student100[4]);
            } else if (universityProgress >= 100) {
                ending.setText(student100[5]);
            }
        }

    }

    public static void setUniversityProgress(int set) {
        universityProgress = set;
    }
    public static void setStudentProgress(int set) {
        studentProgress = set;
    }
}
