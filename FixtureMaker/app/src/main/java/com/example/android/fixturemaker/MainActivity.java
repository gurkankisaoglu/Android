package com.example.android.fixturemaker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinkedList<String> Teams = new LinkedList<String>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addTeam(View v){
        EditText text=(EditText) findViewById(R.id.newTeam);
        String team=text.getEditableText().toString();
        Teams.add(team);
        TextView myTextView = (TextView) findViewById(R.id.team_area);
        myTextView.append(team+'\n');
    }

    public void undo(View v){
        Teams.removeLast();
        TextView myTextView = (TextView) findViewById(R.id.team_area);
        myTextView.setText("");
        for(int i=0 ; i<Teams.size() ; i++){
            myTextView.append(Teams.get(i)+'\n');
        }
    }


    public int showRandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        long range = (long)aEnd - (long)aStart + 1;

        long fraction = (long)(range * aRandom.nextDouble());

        return (int)(fraction + aStart);
    }


    public int getRandomNumber(int i, int n) {
        int rand;
        rand=showRandomInteger(i,n,random);
        return rand;
    }
    public void shuffle(){
        int j;
        for(int i=0 ; i<Teams.size()-1 ; i++){
            j=getRandomNumber(i, Teams.size()-1);
            if(i<j) {Collections.swap(Teams,i,j);}
        }
    }

    public String makeMatch(String s,String t1,String t2){
        s=t1+"-"+t2+'\n';
        return s;
    }

    public void makeWeeksFirstHalf(int i){
        TextView myTextView = (TextView) findViewById(R.id.team_area);
        myTextView.append("Week " + i + '\n');
        String match="";
        for(int j=0 ; j<Teams.size() ; j++){
            match="";
            if(i%2==0 && j==0){
                j++;
                match=makeMatch(match,Teams.get(j),Teams.get(j-1));
                myTextView.append(match);
                continue;
            }
            match=makeMatch(match,Teams.get(j),Teams.get(j+1));
            j++;
            myTextView.append(match);
        }

    }

    public void makeWeeksSecondHalf(int i){
        TextView myTextView = (TextView) findViewById(R.id.team_area);
        myTextView.append("Week " + i + '\n');
        String match="";
        for(int j=0 ; j<Teams.size() ; j++){
            match="";
            if(j==0 && i%2!=0){
                match=makeMatch(match,Teams.get(j),Teams.get(j+1));
                j++;
                myTextView.append(match);
                continue;
            }
            j++;
            match=makeMatch(match,Teams.get(j),Teams.get(j-1));
            myTextView.append(match);
        }
    }

    public void makeFixture(View v){
        shuffle();
        TextView myTextView = (TextView) findViewById(R.id.team_area);
        TextView header=(TextView) findViewById(R.id.header);
        header.setText("Fixture:");
        myTextView.setText("");

        for (int i=0 ; i<Teams.size()-1 ; i++){
            if(i>0){
                String tmp=Teams.remove(1);
                Teams.add(tmp);
            }
            makeWeeksFirstHalf(i+1);
        }


        String abd=Teams.remove(1);
        Teams.add(abd);



        for(int i=0 ; i<Teams.size()-1 ; i++){
            if(i>0){
                String tmp=Teams.remove(1);
                Teams.add(tmp);
            }
            makeWeeksSecondHalf(i+Teams.size());
        }
    }



}
