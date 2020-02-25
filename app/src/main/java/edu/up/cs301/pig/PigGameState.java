package edu.up.cs301.pig;

import edu.up.cs301.game.GameFramework.infoMessage.GameState;

public class PigGameState extends GameState {
    private int turnId;
    private int player0Score;
    private int player1Score;
    private int runningTotal;
    private int dieValue;
    private int playerCount = 2;

    public PigGameState()
    {

    }
    public PigGameState(PigGameState stateToCopy)
    {
        turnId = stateToCopy.getTurnId();
        player1Score = stateToCopy.getPlayer1Score();
        player0Score = stateToCopy.getPlayer0Score();
        runningTotal = stateToCopy.getRunningTotal();
        dieValue = stateToCopy.getDieValue();
    }

    public int getDieValue() {
        return dieValue;
    }

    public int getPlayer0Score() {
        return player0Score;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public int getTurnId() {
        return turnId;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }

    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setRunningTotal(int runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
