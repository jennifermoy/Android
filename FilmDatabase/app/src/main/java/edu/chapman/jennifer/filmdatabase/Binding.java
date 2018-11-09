package edu.chapman.jennifer.filmdatabase;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Binding extends Activity{

    private boolean mIsBound;

    private LocalService mBoundService;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((LocalService.LocalBinder)service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
            Toast.makeText(Binding.this, "Local Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    void doBindService() {
        Intent i = new Intent(Binding.this, LocalService.class);

        bindService(i, mConnection, Context.BIND_AUTO_CREATE);

        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    private View.OnClickListener mBindListener = new View.OnClickListener() {
        public void onClick(View v) {
            doBindService();
        }
    };

    private View.OnClickListener mUnbindListener = new View.OnClickListener() {
        public void onClick(View v) {
            doUnbindService();
        }
    };

    private static String TAG = "Binding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate: "+ Thread.currentThread().getName());
        setContentView(R.layout.filmlist);

        ListView filmList = findViewById(R.id.lvFilmList);
        filmList.setOnClickListener(mBindListener);
    }
}
