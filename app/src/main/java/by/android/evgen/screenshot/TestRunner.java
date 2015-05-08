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
        Log.d("***", "***testActivitySuite");
        InstrumentationTestSuite suite = new InstrumentationTestSuite(this);
        suite.addTestSuite(ScreenTest.class);
//        getCurrentProces().getCurrentWindow().ge
        return suite;
    }

    @Override
    public ClassLoader getLoader() {
        return TestRunner.class.getClassLoader();
    }

}
