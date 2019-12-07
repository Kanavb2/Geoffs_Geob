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

        endUniversity.setText("Your final University Satisfaction: " + universityProgress);
        endStudent.setText("Your final Student Satisfaction: " + studentProgress);
    }

    public static void setUniversityProgress(int set) {
        universityProgress = set;
    }
    public static void setStudentProgress(int set) {
        studentProgress = set;
    }
}
