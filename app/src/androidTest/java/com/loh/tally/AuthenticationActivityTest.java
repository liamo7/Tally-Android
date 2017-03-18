package com.loh.tally;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.loh.tally.ui.authentication.activity.AuthenticationActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * File: AuthenticationActivityTest.java
 * Date: 18/03/2017
 * Created By: Liam O'Hanlon
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthenticationActivityTest {

    @Rule
    public ActivityTestRule<AuthenticationActivity> activityTestRule =
            new ActivityTestRule(AuthenticationActivity.class);

    @Before
    public void setup() {
    }

    @Test
    public void test_choice_register_button_clicked() {
        onView(allOf(withId(R.id.registerButton), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.email), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.password), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.registerButton), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void test_choice_login_button_clicked() {
        onView(allOf(withId(R.id.loginButton), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.email), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.password), isDisplayed())).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.loginButton), isDisplayed())).check(matches(isDisplayed()));
    }

    @Test
    public void test_register_button_clicked() {
        // navigate to register view
        onView(allOf(withId(R.id.registerButton), isDisplayed())).perform(click());

        // enter details
        onView(allOf(withId(R.id.email), isDisplayed())).perform(typeText("test@email.com"));
        onView(allOf(withId(R.id.password), isDisplayed())).perform(typeText("password"));

        // click register button
        onView(allOf(withId(R.id.registerButton), isDisplayed())).perform(click());
    }

    @Test
    public void test_login_button_clicked() {
        // navigate to login view
        onView(allOf(withId(R.id.loginButton), isDisplayed())).perform(click());

        // enter details
        onView(allOf(withId(R.id.email), isDisplayed())).perform(typeText("test@email.com"));
        onView(allOf(withId(R.id.password), isDisplayed())).perform(typeText("password"));

        // click login button
        onView(allOf(withId(R.id.loginButton), isDisplayed())).perform(click());
    }
}
