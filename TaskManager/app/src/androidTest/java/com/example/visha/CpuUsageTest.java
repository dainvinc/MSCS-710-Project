package com.example.visha;

import com.mscs721.taskmanager.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by visha on 4/17/2017.
 */

public class CpuUsageTest {
    @Test
    public void cpuUsageTest() {
        onView(withId(R.id.cpuusage));
    }
}
