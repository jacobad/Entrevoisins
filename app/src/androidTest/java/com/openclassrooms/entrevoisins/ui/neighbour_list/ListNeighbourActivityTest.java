package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourActivityTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);



    @Test
    public void NeighboursListOpenDetailActivity() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.detail_text),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction frameLayout2 = onView(
                allOf(withId(R.id.detail_text),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));
    }

    @Test
    public void NeighboursDetailNameCorrect() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.mDetailName), withText("Caroline"),
                        withParent(allOf(withId(R.id.linearLayoutDetail2),
                                withParent(withId(R.id.detail_text)))),
                        isDisplayed()));
        textView.check(matches(withText("Caroline")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.mDetailName), withText("Caroline"),
                        withParent(allOf(withId(R.id.linearLayoutDetail2),
                                withParent(withId(R.id.detail_text)))),
                        isDisplayed()));
        textView2.check(matches(withText("Caroline")));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.toolbar_layout), withContentDescription("Caroline")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
    }

    @Test
    public void NeighboursListDeleted() {
        ViewInteraction viewGroup = onView(
                allOf(withParent(allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction viewGroup2 = onView(
                allOf(withParent(allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container)))),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.item_list_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        1),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction viewGroup3 = onView(
                allOf(withParent(allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container)))),
                        isDisplayed()));
        viewGroup3.check(doesNotExist());

        ViewInteraction viewGroup4 = onView(
                allOf(withParent(allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container)))),
                        isDisplayed()));
        viewGroup4.check(doesNotExist());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                allOf(withId(R.id.toolbar_layout), withContentDescription("Vincent")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

    }
    @Test
    public void NeighboursListFavorite(){


        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));
    }

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
