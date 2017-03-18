package com.loh.tally;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.loh.tally.ui.main.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * File: MainActivityTest.java
 * Date: 18/03/2017
 * Created By: Liam O'Hanlon
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        activityActivityTestRule.launchActivity(new Intent(appContext, MainActivity.class));
    }

    @Test
    public void test_click_module_list_bottom_nav_item() {
        onView(withId(R.id.bottomNavView)).check(matches(isDisplayed()));
        onView(withId(R.id.action_modules)).perform(click());
    }

    @Test
    public void test_click_chat_list_bottom_nav_item() {
        onView(withId(R.id.bottomNavView)).check(matches(isDisplayed()));
        onView(withId(R.id.action_chats)).perform(click());
    }

    @Test
    public void test_add_module_fab_click() {
        onView(allOf(withId(R.id.enrollFab), isDisplayed())).perform(click());
        onView(allOf(withText(R.string.enrollment_module_dialog_title), isDisplayed()));
    }
}
