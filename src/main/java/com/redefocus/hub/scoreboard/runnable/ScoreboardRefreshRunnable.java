package com.redefocus.hub.scoreboard.runnable;

import com.redefocus.hub.scoreboard.manager.ScoreboardManager;

public class ScoreboardRefreshRunnable implements Runnable {
    @Override
    public void run() {
        ScoreboardManager.refresh();
    }
}
