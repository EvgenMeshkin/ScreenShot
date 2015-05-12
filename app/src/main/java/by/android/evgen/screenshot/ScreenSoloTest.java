package by.android.evgen.screenshot;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yauheni_Meshkin on 5/11/2015.
 */
public class ScreenSoloTest extends InstrumentationTestCase {

    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "by.android.evgen.testapplication.MainActivity";

    public void testCase() {
        String testCaseName = String.format("%s.%s", getClass().getName(), getName());
        Instrumentation instrumentation = getInstrumentation();
        IntentFilter filter = new IntentFilter(Intent.ACTION_MAIN);
        filter.addCategory(Intent.CATEGORY_LAUNCHER);
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(filter, null, false);
        Log.d("***", "***testActivity1" );
        // Register we are interested in the authentication activiry...

        Log.d("***", "***testActivity2" + instrumentation.getComponentName());
        Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 50000);


        Log.d("***", "***testActivity3" + currentActivity.getLocalClassName());
        solo = new Solo(getInstrumentation(), currentActivity);
        solo.takeScreenshot();

        Log.d("***", "***testActivity4" );
//        instrumentation.getTargetContext().startActivity(intent);
        Log.d("***", "***testActivity5");
        try {
//            takeScreenShotTest(currentActivity, testCaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        monitor = instrumentation.addMonitor(LAUNCHER_ACTIVITY_FULL_CLASSNAME, null, false);
        getInstrumentation().waitForMonitorWithTimeout(monitor, 500000);
        Log.d("***", "***testActivity6");

        Assert.assertTrue(true);
    }

    public void takeScreenShotTest(Activity activity, final String name)
            throws Exception {
        final View view = activity.getWindow().getDecorView().getRootView();
        System.out.println("GO save");
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());

        FileOutputStream fos;
        try {
            final String path = Environment.getExternalStorageDirectory() + "/Pictures/Screenshots/";
            File dir = new File(path);
            if (!dir.mkdirs()) {
                System.out.println("Creaet sd card failed");
            }
            if (!dir.exists()) {
                System.out.println(path);
                dir.mkdirs();
            }
            fos = new FileOutputStream(path + name + ".jpg");
            b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
