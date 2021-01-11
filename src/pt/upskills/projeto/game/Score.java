package pt.upskills.projeto.game;

import pt.upskills.projeto.objects.Enemy;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.Items.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Score implements Comparable<Score>{
    private int finalScore;
    private long timeStamp;
    //private static List<Score> scoreBoardList=new ArrayList<>();
    private String ignore;

    public Score(int finalScore){
        this.finalScore=finalScore;
        timeStamp= Calendar.getInstance().getTimeInMillis();
    }

    public Score(int finalScore, long date){
        this.finalScore=finalScore;
        timeStamp= date;
    }
    public Score(String ignore){
    }

    public  int getFinalScore(){return finalScore;}
    public  long getTimeStamp(){return timeStamp;}

    public void saveScore(){
        calculateScores();
        Score score=new Score(finalScore);
        System.out.println(score);
        Engine.scoreBoardlist.add(score);
        Collections.sort(Engine.scoreBoardlist);
        Engine.readWriteScoreBoard();

        //Engine.writeScoreBoard();
    }

    public void calculateScores(){
        int tempFinalScore= Item.getScoreCountItem()+ Enemy.getScoreCountEnemy()- Engine.hero.getNumberOfMovements();
        if (tempFinalScore<0){
            finalScore=0;
        }
        else{
            finalScore=tempFinalScore;
        }
    }

    @Override
    public String toString() {
        return getFinalScore()+";"+getTimeStamp();
    }


    @Override
    public int compareTo(Score o) {
        return o.getFinalScore()-getFinalScore();
    }
}

