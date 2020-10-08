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
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
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
        int randNum=(int) (Math.random()*2);
        if (randNum==0){
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
