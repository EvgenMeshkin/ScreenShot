package by.android.evgen.screenshot;

import android.test.InstrumentationTestRunner;
import android.test.InstrumentationTestSuite;
import android.util.Log;

import junit.framework.TestSuite;

/**
 * Created by Yauheni_Meshkin on 5/7/2015.
 */
public class TestRunner extends InstrumentationTestRunner {

    @Override
    public TestSuite getAllTests(){
        InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(ScreenSoloTest.class);
        Log.d("***", "***testActivitySuite");
//        Intent intent = new Intent(getInstrumentation().getTargetContext(), clazz);
//        intent.setFlags(intent.getFlags() | FLAG_ACTIVITY_NEW_TASK);
//        getInstrumentation().startActivitySync(intent);
//        getCurrentProces().getCurrentWindow().ge
        return suite;
    }

    /*public  static Test suite (){
        return  new TestSuiteBuilder( ScreenSoloTest.class )
                . includeAllPackagesUnderHere ()
                . build ();
    }*/

    @Override
    public ClassLoader getLoader() {
        return TestRunner.class.getClassLoader();
    }

}
