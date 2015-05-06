package by.android.evgen.screenshot;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ServiceConfigurationError;

/**
 * Created by Yauheni_Meshkin on 5/6/2015.
 */
public class ScreenService extends Service {

    public Handler mHandler = new Handler();
    public static final long DELAY_MILLIS_FOR_CHECKING_CURRENT_APP = 1500l;
    public static final long REFRESH_DELAY_MILLIS = 1500l;
    public static final int START_TASK_TO_FRONT = 2;
    private ActivityManager mActivityManager;
    private Field mField;

    private String mNamePackageLaunch;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mActivityManager != null) {
            mCurrentTopAppRunnable.run();
            return;
        }
        mActivityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                mField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        PackageManager localPackageManager = getPackageManager();
        Intent launcherIntent = new Intent("android.intent.action.MAIN");
        launcherIntent.addCategory("android.intent.category.HOME");
        mNamePackageLaunch = localPackageManager.resolveActivity(launcherIntent,
                PackageManager.MATCH_DEFAULT_ONLY).activityInfo.packageName;

        mHandler.removeCallbacks(mCurrentTopAppRunnable);
        mHandler.postDelayed(mCurrentTopAppRunnable, REFRESH_DELAY_MILLIS);
        mCurrentTopAppRunnable.run();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private boolean isLauncherTop() {
        String top = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.RunningAppProcessInfo> appList = mActivityManager.getRunningAppProcesses();
            if (appList != null && !appList.isEmpty()) {
                for (ActivityManager.RunningAppProcessInfo app : appList) {
                    if (app.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        try {
                            int state = mField.getInt(app);
                            if (state == START_TASK_TO_FRONT) {
                                top = app.processName;
                                break;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        } else {
            if (mActivityManager == null) {
                return false;
            }
            List<ActivityManager.RunningTaskInfo> list = mActivityManager.getRunningTasks(2);
            if (list == null || list.isEmpty()) {
                return false;
            } else {
                ComponentName baseActivity = list.get(0).baseActivity;
                top = baseActivity.getPackageName();
                Log.d("******", "*******" + top + "***" + list.get(0).baseActivity.getClass());
            }
        }
        return top.contains(mNamePackageLaunch);
    }

    private Runnable mCurrentTopAppRunnable = new Runnable() {
        @Override
        public void run() {
            if (isLauncherTop()) {
                mHandler.removeCallbacks(this);
                mHandler.postDelayed(this, REFRESH_DELAY_MILLIS);
            } else {
                mHandler.removeCallbacks(this);
                mHandler.postDelayed(this, DELAY_MILLIS_FOR_CHECKING_CURRENT_APP);
            }
        }
    };


}
