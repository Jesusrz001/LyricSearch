package com.jesus.lyricsearch;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import com.jesus.lyricsearch.ui.search.SearchActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public IntentsTestRule<SearchActivity> searchIntentRule =
            new IntentsTestRule<>(SearchActivity.class);

    /*
     * Due to there being a network call, register the idling resource so espresso knows to sync actions
     */
    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(
                searchIntentRule.getActivity().getCountingIdlingResource());
    }

    @SmallTest
    public void validateInitialScreen(){
        SearchRobot.newInstance()
                .isProgressHidden()
                .isListHidden();
    }
    @Test
    public void searchUnknownTerm(){
        SearchRobot robot = new SearchRobot()
                .isProgressHidden()
                .typeSearchTerm("X%$#@^&Dasdfas")
                .clickSearchBtn()
                .isProgressHidden()
                .isErrorMessageDisplayed()
                .checkErrorMessageText();
    }

    @Test
    public void searchBruno(){
        SearchRobot.newInstance()
                .typeSearchTerm("Bruno Mars")
                .clickSearchBtn()
                .isListDisplayed();

    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                searchIntentRule.getActivity().getCountingIdlingResource());
    }
}
