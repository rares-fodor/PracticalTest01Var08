package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Context;
import android.content.Intent;

import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var08.Constants.Constants;

public class WorkerThread extends Thread {
    private String answer;
    private Context context;

    private boolean isRunning = true;

    public WorkerThread(Context context, String answer) {
        this.answer = answer;
        this.context = context;
    }

    @Override
    public void start() {
        String value = "*".repeat(answer.length());
        StringBuilder sb = new StringBuilder(value);

        Random rand = new Random();
        int pos = rand.nextInt(answer.length());

        while (isRunning) {
            Intent intent = new Intent();

            sb.setCharAt(pos, answer.charAt(pos));
            pos = rand.nextInt(answer.length());

            System.out.println("sending bcast");
            System.out.println(sb.toString());

            intent.putExtra(Constants.HINT, sb.toString());
            context.sendBroadcast(intent);

            System.out.println("sent bcast");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}
