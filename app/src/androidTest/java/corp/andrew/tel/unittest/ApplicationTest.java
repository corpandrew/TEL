package corp.andrew.tel.unittest;

import android.test.ActivityInstrumentationTestCase2;

import corp.andrew.tel.activities.MainActivity;

public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity>{

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
        MainActivity activity = getActivity();
    }

}