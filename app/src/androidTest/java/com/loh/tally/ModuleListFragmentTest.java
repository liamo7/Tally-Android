package com.loh.tally;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.loh.tally.ui.main.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * File: ModuleListFragmentTest.java
 * Date: 18/03/2017
 * Created By: Liam O'Hanlon
 */
@RunWith(AndroidJUnit4.class)
public class ModuleListFragmentTest {

    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        activityActivityTestRule.launchActivity(new Intent(appContext, MainActivity.class));
    }

    @Test
    public void test_navigate_to_module() {
    }

    @Test
    public void test_navigate_to_chat() {
    }
}
