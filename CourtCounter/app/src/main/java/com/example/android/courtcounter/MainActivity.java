package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int teamB=0;
    int teamA=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayForTeamB(int score){
        TextView scoreView=(TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }



    public void displayForTeamA(int score){
        TextView scoreView=(TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addThree(View v){
        teamA=teamA+3;
        displayForTeamA(teamA);
    }

    public void addTwo(View v){
        teamA=teamA+2;
        displayForTeamA(teamA);
    }

    public void addOne(View v){
        teamA=teamA+1;
        displayForTeamA(teamA);
    }

    public void addThreeB(View v){
        teamB=teamB+3;
        displayForTeamB(teamB);
    }

    public void addTwoB(View v){
        teamB=teamB+2;
        displayForTeamB(teamB);
    }

    public void addOneB(View v){
        teamB=teamB+1;
        displayForTeamB(teamB);
    }

    public void reset(View v){
        teamA=0;
        teamB=0;
        displayForTeamA(0);
        displayForTeamB(0);
    }
}
