package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;

    private Random random = new Random();

    private String commands;

    public ProcessingThread(Context context, String commands) {
        this.context = context;
        this.commands = commands;
    }

    @Override
    public void run() {
        sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[0]);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, new Date(System.currentTimeMillis()) + " " + commands);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
