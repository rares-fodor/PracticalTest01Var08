package ro.pub.cs.systems.eim.practicaltest01var08.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ro.pub.cs.systems.eim.practicaltest01var08.Constants.Constants;
import ro.pub.cs.systems.eim.practicaltest01var08.WorkerThread;

public class PracticalTest01Var08Service extends Service {
    private WorkerThread workerThread = null;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        workerThread = new WorkerThread(this, intent.getExtras().getString(Constants.ANSWER_TEXT));
        System.out.println("STARTING THREAD");
        workerThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        workerThread.stopThread();
    }
}