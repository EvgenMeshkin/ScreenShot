package by.android.evgen.screenshot;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.util.Log;

import com.robotium.solo.Solo;

import junit.framework.Assert;

/**
 * Created by Yauheni_Meshkin on 5/11/2015.
 */
public class ScreenSoloTest extends InstrumentationTestCase {
    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "by.android.evgen.vkclientexample.activity.StartActivity";

   /* private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
*/



   /* public void setUp() throws Exception {
        super.setUp();
        Log.d("***", "***testActivity" + getActivity().getClass().getCanonicalName());
//        solo = new Solo(getInstrumentation(), getActivity());
    }*/

   /* @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }*/

    public void testCase() {
        String testCaseName = String.format("%s.%s", getClass().getName(), getName());
        Log.d("***", "***testActivity1");
        Instrumentation instrumentation = getInstrumentation();
        Log.d("***", "***testActivity2" + instrumentation.getComponentName());

        // Register we are interested in the authentication activiry...
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(LAUNCHER_ACTIVITY_FULL_CLASSNAME, null, false);
        Log.d("***", "***testActivity3");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(instrumentation.getTargetContext(), LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        Log.d("***", "***testActivity4");
        instrumentation.getContext().startActivity(intent);
        Log.d("***", "***testActivity5");
        Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
//        Log.d("***", "***testActivity" + currentActivity.getClass().getCanonicalName());
        solo = new Solo(getInstrumentation(), currentActivity);



        solo.takeScreenshot(testCaseName);
/*        solo.goBackToActivity("by.android.evgen.screenshot.ScreenActivity");
        solo.getCurrentActivity();
        solo.startScreenshotSequence (String) / startScreenshotSequence (String, Int, Int, Int);
        solo.stopScreenshotSequence ();
        solo.getScreenshotSequence (String, Boolean, String, String);*/
//        View v = getWindow().getDecorView().getRootView();
        Assert.assertTrue(true);
    }

}
