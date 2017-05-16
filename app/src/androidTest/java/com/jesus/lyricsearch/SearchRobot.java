package com.jesus.lyricsearch;

import com.jesus.lyricsearch.networking.EspressoIdlingResource;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
/*
 * The point of this robot class it to add a seperatation of concerns and to match the
 * architecture of the app. Separates what the app is testing from how it is being tested
 * Also prevents rewriting multiple on view code.
 */

public class SearchRobot {

    static SearchRobot newInstance(){
        return new SearchRobot();
    }

    SearchRobot typeSearchTerm(String searchTerm){
        onView(withId(R.id.search_ed_txt)).perform(typeText(searchTerm),
                closeSoftKeyboard());
        return this;
    }

    SearchRobot clickSearchBtn(){
        onView(withId(R.id.search_button)).perform(click());
        return this;
    }

    SearchRobot isProgressHidden(){
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())));
        return this;
    }

    SearchRobot isProgressDisplayed(){
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
        return this;
    }
    SearchRobot isErrorMessageDisplayed() {
        onView(withId(R.id.error_txt)).check(matches(isDisplayed()));
        return this;
    }

    SearchRobot isListDisplayed() {
        onView(withId(R.id.result_list)).check(matches(isDisplayed()));
        return this;
    }

    SearchRobot checkErrorMessageText() {
        onView(withId(R.id.error_txt)).check(matches(withText("No Results")));
        return this;
    }

    SearchRobot isListHidden() {
        onView(withId(R.id.result_list)).check(matches(not(isDisplayed())));
        return this;
    }
}
