package by.android.evgen.screenshot;

import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Yauheni_Meshkin on 5/5/2015.
 */
public class ScreenTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private MainActivity mainActivity;

    public ScreenTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        mainActivity = getActivity();
    }

    public void testActivity() {
        solo.waitForActivity(by.android.evgen.screenshot.MainActivity.class);
        View view = mainActivity.getWindow().getDecorView().getRootView();
        view.setDrawingCacheEnabled(true);
        try {
            takeScreenShot(view, "testRecorded_1316975601089");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        assertEquals(true, true);
    }

    public void testCase() {
        String testCaseName = String.format("%s.%s", getClass().getName(), getName());
        solo.takeScreenshot(testCaseName);
/*        solo.goBackToActivity("by.android.evgen.screenshot.ScreenActivity");
        solo.getCurrentActivity();
        solo.startScreenshotSequence (String) / startScreenshotSequence (String, Int, Int, Int);
        solo.stopScreenshotSequence ();
        solo.getScreenshotSequence (String, Boolean, String, String);*/
        Assert.assertTrue(true);
    }

    public void takeScreenShot(final View view, final String name)
            throws Exception {

        getActivity().runOnUiThread(new Runnable() {
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
        });

    }

}
