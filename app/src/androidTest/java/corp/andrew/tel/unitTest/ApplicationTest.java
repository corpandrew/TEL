package corp.andrew.tel.unitTest;

import android.test.ActivityInstrumentationTestCase2;

import activities.MainActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

}