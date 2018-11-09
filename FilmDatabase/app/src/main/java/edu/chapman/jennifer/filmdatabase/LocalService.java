package edu.chapman.jennifer.filmdatabase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class LocalService extends Service{

    private NotificationManager mNM;
    private String TAG = "LocalService";

    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate: "+ Thread.currentThread().getName());
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        showNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + Film.class.getName() + ": " + intent);
        Log.i("Local Service", "FILM PLAYING SERVICE STARTED");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy()");
        Log.i("TAG", "FILM PLAYING SERVICE STOPPED");

        // Tell the user we stopped.
        Toast.makeText(this, "Film Stopped Playing", Toast.LENGTH_SHORT).show();
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotificationManager.cancel(0);
    }
    String s = "";
    @Override
    public IBinder onBind(Intent intent) {
        s = intent.getStringExtra("test");
        return mBinder;
    }

    private final IBinder mBinder = new LocalBinder();

    private void showNotification() {

        CharSequence text = MySingleton.getInstance().curr_FilmName + " is Playing";

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(LocalService.this, "Chapman")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Service") // title for notification
                .setContentText(text)// message for notification
                .setOngoing(true);

        Intent intent = new Intent(LocalService.this, DisplayFilm.class);
        PendingIntent pi = PendingIntent.getActivity(LocalService.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }

    public String getCurrentString(){
        return s + "service";
    }
}
