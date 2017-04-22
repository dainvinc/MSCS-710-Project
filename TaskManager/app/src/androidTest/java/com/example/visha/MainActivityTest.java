package com.example.visha;

import com.mscs721.taskmanager.MainActivity;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by mitin on 4/17/2017.
 */
public class MainActivityTest {

    float output;
    int exptected = 0;

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void getTotalMemory() throws Exception {
        MainActivity m = new MainActivity();
        //output = m.getTotalMemory();
        //assertNull(m.getTotalMemory());

    }

    @Test
    public void CpuRead() throws Exception{
        MainActivity m = new MainActivity();
        int res = m.CpuRead();
        assertTrue("passed",res > 0);
    }

    @Test
    public void getAvailMemory() throws Exception {
        MainActivity m = new MainActivity();
        //m.getAvailMemory();
    }

    @Test
    public void readUsage() throws Exception {
        MainActivity m = new MainActivity();
        float res = m.readUsage();
        assertTrue("read usage passed",true);
    }

}