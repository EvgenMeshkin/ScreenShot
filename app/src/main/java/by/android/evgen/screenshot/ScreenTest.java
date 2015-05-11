package by.android.evgen.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.robotium.solo.Solo;

import junit.framework.Assert;

/**
 * Created by Yauheni_Meshkin on 5/5/2015.
 */
public class ScreenTest extends InstrumentationTestCase {

    private Solo solo;

    public ScreenTest() {
//        super(FriendsActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        Log.d("***", "***Start test");
//        friendsActivity = getActivity();

    }

    public void testActivity() {
//        solo.waitForActivity(MainActivity.class);
        Log.d("***", "***testActivity");
        solo.waitForActivity("by.android.evgen.vkclientexample.activity.FriendsActivity",
                5000);
//        Log.d("***", "***testSolo" + solo.getCurrentActivity().getLocalClassName());
        try {

            String act = getInstrumentation().getContext().getPackageName();
            Log.d("***", "***testInstrumentation" + act);
//            getInstrumentation().waitForIdleSync();
 //           act.finish();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

       /* Activity activity = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED).iterator().next();
*/

  /*      Activity parent = (Activity)getInstrumentation().getContext();
        // using the activity, get Window reference
        Window window = parent.getWindow();
        // using the reference of the window, do whatever you want :D
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getInstrumentation().getContext().getWindow().getDecorView().getRootView()
        String testCaseName = String.format("%s.%s", getClass().getName(), getName());
        View view = window.getDecorView().getRootView();
        try {
            takeScreenShot(view, testCaseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setDrawingCacheEnabled(true);
        try {
            takeScreenShot(view, "testRecorded_1316975601089");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());*/
        assertEquals(true, true);
    }

    public void testCase() {
        String testCaseName = String.format("%s.%s", getClass().getName(), getName());
//        solo.takeScreenshot(testCaseName);
/*        solo.goBackToActivity("by.android.evgen.screenshot.ScreenActivity");
        solo.getCurrentActivity();
        solo.startScreenshotSequence (String) / startScreenshotSequence (String, Int, Int, Int);
        solo.stopScreenshotSequence ();
        solo.getScreenshotSequence (String, Boolean, String, String);*/
//        View v = getWindow().getDecorView().getRootView();
        Assert.assertTrue(true);
    }

    public void takeScreenShot(final View view, final String name)
            throws Exception {

  /*      getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("GO save");
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap b = view.getDrawingCache();
                FileOutputStream fos = null;
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
                    if (fos != null) {
                        b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                        fos.close();
                    }
                } catch (IOException e) {
                }
            }
        });*/

    }

}
