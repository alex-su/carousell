package com.alexsukharev.carouselltopics.repository;

import com.alexsukharev.carouselltopics.BuildConfig;
import com.alexsukharev.carouselltopics.model.Topic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * We need Robolectric to use LiveData, which is a part of Android platform and would be mocked otherwise.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TopicsRepositoryTest {

    private TopicsRepository mTopicsRepository;

    private List<Topic> mTopics;

    @Before
    public void setUp() {
        mTopicsRepository = new TopicsRepository();
    }

    @Test
    public void testRepository() {
        // Observe changes
        mTopicsRepository.getTopics().observeForever(topics -> mTopics = topics);

        // Create topic and test the observer
        mTopicsRepository.createTopic("Test topic");
        assertNotNull(mTopics);
        assertEquals(mTopics.size(), 1);

        // Update topic
        final Topic topic = mTopics.get(0);
        topic.setScore(100);
        mTopicsRepository.updateTopic(topic);
        assertEquals(mTopics.get(0).getScore(), 100);
    }

}
