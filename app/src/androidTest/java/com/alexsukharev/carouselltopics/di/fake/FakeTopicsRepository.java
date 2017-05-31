package com.alexsukharev.carouselltopics.di.fake;

import com.alexsukharev.carouselltopics.repository.TopicsRepository;

/**
 * Same as the normal @{@link TopicsRepository}, but pre-filled with data.
 */

public class FakeTopicsRepository extends TopicsRepository {

    public FakeTopicsRepository() {
        super();
        createTopic("Topic 1");
        createTopic("Topic 2");
        createTopic("Topic 3");
    }

}
