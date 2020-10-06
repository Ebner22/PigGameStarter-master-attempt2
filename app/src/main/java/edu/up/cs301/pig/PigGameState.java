package edu.up.cs301.pig;

public class PigGameState {
    private int turn;
    private int p1Score;
    private int p2Score;
    private int runningScore;
    private int dieValue;

    public PigGameState(){
        turn=0;
        p1Score=0;
        p2Score=0;
        runningScore=0;
        dieValue=0;
    }
    public PigGameState(PigGameState state){
        turn=state.getTurn();
        p1Score=state.getP1Score();
        p2Score=state.getP2Score();
        runningScore=state.getRunningScore();
        dieValue=state.getDieValue();
    }
    public int getTurn(){return turn;}
    public int getP1Score(){return p1Score;}
    public int getP2Score(){return p2Score;}
    public int getRunningScore(){return runningScore;}
    public int getDieValue(){return dieValue;}

    public boolean setTurn(int t){turn=t; return true;}
    public boolean setP1Score(int s){p1Score=s; return true;}
    public boolean setP2Score(int s){p2Score=s; return true;}
    public boolean setRunningScore(int s){runningScore=s; return true;}
    public boolean setDieValue(int v){dieValue=v; return true;}
}
