package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigSmartComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof PigGameState)){return;}
        PigGameState gs=new PigGameState((PigGameState)info);
        if (playerNum!=gs.getTurn()){return;}//if it's not your turn do nothing

        int myScore;
        int theirScore;
        if (playerNum==0){myScore=gs.getP1Score(); theirScore=gs.getP2Score();}
        else{myScore=gs.getP2Score(); theirScore=gs.getP1Score();}

        int num=1;
        int difference=myScore/(theirScore+1);
        int threshold=20;

        if (theirScore>25 && myScore<15){
            threshold=20+3/difference;
        }
        if (theirScore>40 && myScore<20){
            threshold=20+4/difference;
        }
        if (myScore>20 && theirScore<15){
            threshold=16;
        }
        if (threshold>30){threshold=29;}//don't make the threshold too big
        if (threshold<12){threshold=13;}//or too small
        if (gs.getRunningScore()>=threshold){//the point at which it is better to hold
            num=0;
        }
        if (myScore+gs.getRunningScore()>=50){//holding now means a win. Definitely hold.
            num=0;
        }


        if (num==0){
            PigHoldAction act=new PigHoldAction(this);
            game.sendAction(act);
        }
        else{
            PigRollAction act=new PigRollAction(this);
            game.sendAction(act);
        }
        // TODO  You will implement this method
    }//receiveInfo

}
