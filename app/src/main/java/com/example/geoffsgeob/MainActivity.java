package com.example.geoffsgeob;

import android.content.Intent;
import android.os.Bundle;

import com.example.geoffsgeob.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

/** Navigation menu.
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static boolean hwSubmit;
    private static boolean quizSubmit;
    private static boolean midtermSubmit;
    private static boolean mpSubmit;
    private static int hwSelection;
    private static int quizSelection;
    private static int midtermSelection;
    private static int mpSelection;
    private static int week;

    /*Current issues to fix:
        1. The previousWeek algorithm
        2. You have to open MP/Midterm fragments every week just to set their booleans to true. resolved :)
        3. Next week FAB doesn't go to the next week kmn in ANY of them. kinda sorta did it maybe i dunno im sorry
        4. The submit button in quiz just pops up in week 1 for some reason. resolved :)
        5. MP fragment isn't showing the right text. resolved :)
     */

    /** Runs when the menu button is clicked.
     * @param savedInstanceState is a bundle man idk we didn't learn this in the MPs.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        week = 0;
        hwSubmit = false;
        quizSubmit = false;
        midtermSubmit = false;
        mpSubmit = false;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_forum, R.id.nav_mail, R.id.nav_homework,
                R.id.nav_quiz, R.id.nav_midterm, R.id.nav_mp)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /** Menu item for settings, about, help at top right.
     * @param item the option selected
     * @return whether the selection processed correctly or not
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_help:
                intent = new Intent(this, Help.class);

                startActivity(intent);
                return true;
            case R.id.action_settings:
                intent = new Intent(this, Settings.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void toggleHW() {
        hwSubmit = !hwSubmit;
    }
    public static boolean getHWSubmit() {
        return hwSubmit;
    }
    public static void setHwSelection(int toSet) {
        hwSelection = toSet;
    }
    public static int getHwSelection() {
        return hwSelection;
    }

    public static void toggleMidterm() {
        midtermSubmit = !midtermSubmit;
    }
    public static boolean getMidtermSubmit() {
        return midtermSubmit;
    }
    public static void setMidtermSelection(int toSet) {
        midtermSelection = toSet;
    }
    public static int getMidtermSelection() {
        return midtermSelection;
    }

    public static void toggleMP() {
        mpSubmit = !mpSubmit;
    }
    public static boolean getMPSubmit() {
        return mpSubmit;
    }
    public static void setMpSelection(int toSet) {
        mpSelection = toSet;
    }
    public static int getMpSelection() {
        return mpSelection;
    }

    public static void toggleQuiz() {
        quizSubmit = !quizSubmit;
    }
    public static boolean getQuizSubmit() {
        return quizSubmit;
    }
    public static void setQuizSelection(int toSet) {
        quizSelection = toSet;
    }
    public static int getQuizSelection() {
        return quizSelection;
    }
    public static void nextWeek() {
        week++;
    }
    public static int getWeek() {
        return week;
    }
}
