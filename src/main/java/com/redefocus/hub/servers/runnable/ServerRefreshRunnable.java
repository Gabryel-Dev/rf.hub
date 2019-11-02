package com.redefocus.hub.servers.runnable;

import com.redefocus.hub.servers.manager.ServerManager;

public class ServerRefreshRunnable implements Runnable {
    @Override
    public void run() {
        ServerManager.refresh();
    }
}
