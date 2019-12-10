package com.example.geoffsgeob;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.geoffsgeob.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.widget.Button;

/** Navigation menu.
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static int totalWeeks = 15;
    private static boolean hwSubmit;
    private static boolean quizSubmit;
    private static boolean midtermSubmit;
    private static boolean mpSubmit;
    private static boolean encounter = false;
    private static int universityProgress;
    private static int studentProgress;
    private static int hwSelection;
    private static int quizSelection;
    private static int midtermSelection;
    private static int mpSelection;
    private static int week;
    private static int bgValue = 50;
    private static boolean disableProgress = false;
    private static boolean disableEncounters = false;
    private static boolean disableBonuses = false;
    private static boolean cat = false;
    private static boolean chuchu = false;
    private static boolean ben = false;
    private static boolean challen = false;
    private static int uniChange;
    private static int studentChange;
    private static int encounterButtons = 0;

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
        universityProgress = 70;
        studentProgress = 70;
        hwSubmit = false;
        quizSubmit = false;
        midtermSubmit = false;
        mpSubmit = false;
        resetWeekChange();

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

        Button settings = findViewById(R.id.settings);
        Button help = findViewById(R.id.help);

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        });

        help.setOnClickListener(v -> {
            Intent intent = new Intent(this, Help.class);
            startActivity(intent);
        });

        Intent svc = new Intent(this, BackgroundSoundService.class);
        startService(svc);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void setTotalWeeks(int toSet) {
        totalWeeks = toSet;
    }
    public static int getTotalWeeks() {
        return totalWeeks;
    }

    public static void setHwSubmit(boolean set) {
        hwSubmit = set;
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

    public static void setMidtermSubmit(boolean set) {
        midtermSubmit = set;
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
    public static boolean midtermDone() {
        return getMidtermSubmit() || ((getWeek() + 1) % 5 != 0);
    }

    public static void setMPSubmit(boolean set) {
        mpSubmit = set;
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
    public static boolean mpDone() {
        return getMPSubmit() || ((getWeek() < 2 || getWeek() % 2 == 1) && getWeek() <= 12) || getWeek() > 12;
    }

    public static void setQuizSubmit(boolean set) {
        quizSubmit = set;
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
    public static boolean quizDone() {
        return MainActivity.getQuizSubmit() || (MainActivity.getWeek() + 1) % 5 == 0;
    }
    public static void nextWeek() {
        week++;
    }
    public static int getWeek() {
        return week;
    }

    public static void setBgValue(int set) {
        bgValue = set;
    }
    public static int getBgValue() {
        return bgValue;
    }
    public static void setDisableProgress(boolean b) {
        disableProgress = b;
        if (b) {
            HomeFragment.hideProgressBars();
        } else {
            HomeFragment.showProgressBars();
        }
    }
    public static boolean getDisableProgress() {
        return disableProgress;
    }
    public static void setUniversityProgress(int set) {
        universityProgress += set;
    }
    public static void setStudentProgress(int set) {
        studentProgress += set;
    }
    public static int getUniversityProgress() {
        return universityProgress;
    }
    public static int getStudentProgress() {
        return studentProgress;
    }
    public static void setDisableEncounters(boolean b) {
        disableEncounters = b;
        if (b) {
            HomeFragment.hideEncounters();
        } else {
            HomeFragment.showEncounters();
        }
    }
    public static boolean getDisableEncounters() {
        return disableEncounters;
    }
    public static void setChuchu(boolean b) {
        chuchu = b;
    }
    public static boolean getChuchu() {
        return chuchu;
    }
    public static void setBen(boolean b) {
        ben = b;
    }
    public static boolean getBen() {
        return ben;
    }
    public static void setChallen(boolean b) {
        challen = b;
    }
    public static boolean getChallen() {
        return challen;
    }
    public static void setDisableBonuses(boolean b) {
        disableBonuses = b;
        if (b) {
            HomeFragment.hideBonuses();
        } else {
            HomeFragment.showBonuses();
        }
    }
    public static boolean getDisableBonuses() {
        return disableBonuses;
    }
    public static void resetWeekChange() {
        uniChange = 0;
        studentChange = 0;
    }
    public static void thisWeekChange(int u, int s) {
        uniChange += u;
        studentChange += s;
    }
    public static int getUniChange() {
        return uniChange;
    }
    public static int getStudentChange() {
        return studentChange;
    }
    public static void setWeek(int toSet) {
        week = toSet;
    }
    public static void resetProgress() {
        universityProgress = 70;
        studentProgress = 70;
    }
    public static void setEncounter(boolean b) {
        encounter = b;
    }
    public static boolean getEncounter() {
        return encounter;
    }
    public static void setEncounterButtons(int set) {
        encounterButtons = set;
    }
    public static int getEncounterButtons() {
        return encounterButtons;
    }
    public static void setCat(boolean b) {
        cat = b;
    }
    public static boolean getCat() {
        return cat;
    }
}
