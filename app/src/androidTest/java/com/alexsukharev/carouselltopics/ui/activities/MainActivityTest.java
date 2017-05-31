package com.alexsukharev.carouselltopics.ui.activities;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.alexsukharev.carouselltopics.R;
import com.alexsukharev.carouselltopics.di.Components;
import com.alexsukharev.carouselltopics.di.component.DaggerRepositoryComponent;
import com.alexsukharev.carouselltopics.di.module.FakeRepositoryModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.alexsukharev.carouselltopics.util.RecyclerViewMatcher.withRecyclerView;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setUp() {
        // Setting up fake components to help with testing
        Components.setRepositoryComponent(
                DaggerRepositoryComponent
                        .builder()
                        .repositoryModule(new FakeRepositoryModule())
                        .build());
        mActivityTestRule.launchActivity(getActivityStartIntent());
    }

    /**
      See https://spin.atomicobject.com/2016/04/15/espresso-testing-recyclerviews/
     */
    @Test
    public void testTopicList() {
        // Check that 3 Topic items are displayed on the screen
        onView(withId(R.id.recycler_view)).check((view, noViewFoundException) -> assertThat(((RecyclerView) view).getAdapter().getItemCount(), is(3)));

        // Check if the first item has the correct title
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.title)).check(matches(withText("Topic 1")));

        // Test voting up
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.button_upvote)).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.text_score)).check(matches(withText("1")));

        // Test voting down
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(2, R.id.button_downvote)).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(2, R.id.text_score)).check(matches(withText("-1")));
    }

    @Test
    public void testAddTopic() {
        // Show dialog
        onView(withId(R.id.add_topic_button)).perform(click());
        onView(withId(R.id.edit_text)).check(matches(isDisplayed()));

        // Create a new topic
        onView(withId(R.id.edit_text)).perform(typeText("Test topic"));
        onView(withId(R.id.submit_button)).perform(click());

        // Check if the new item appeared in the list
        onView(withId(R.id.recycler_view)).check((view, noViewFoundException) -> assertThat(((RecyclerView) view).getAdapter().getItemCount(), is(4)));
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(3, R.id.title)).check(matches(withText("Test topic")));
    }

    private Intent getActivityStartIntent() {
        return new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
    }

}
