package com.example.bestshift_as;

import org.junit.Test;

import java.lang.Exception;
import java.lang.Override;

import static org.junit.Assert.*;

import com.example.bestshift_as.MyActivity;
import com.example.bestshift_as.StartframeActivity;
/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    //where do u see an addition?
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * created by HDB 09.12.2015
     * tests the "Starten" Button to see if it starts "MyActivity" by monitoring.
     */
    @Test
    public void testOpenNextActivity() {
        // register next activity that need to be monitored.
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MyActivity.class.getName(), null, false);

        // open current activity.
        StartframeActivity sfa = getActivity();
        final Button button = (Button) sfa.findViewById(com.example.bestshift_as.StartframeActivity);
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                button.performClick();
            }
        });

        //No timeout for this
        assertNotNull(MyActivity);
        MyActivity.finish();
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}