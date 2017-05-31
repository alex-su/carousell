# Carousell Topics

## About this project

This is an application created for Carousell coding challenge.

## Key technologies

- Android Architecture Components
- DataBinding
- Dagger 2
- Espresso and Robolectric for testing

## Main components

The project uses Model-View-ViewModel design.
- TopicsRepository stores the data and provides get/insert/update interface to manipulate it.
- ViewModel manages user input and retrieves data from the Repository.
- View observes changes of the ViewModel. These two are totally separated: view methods never get called directly.

## Data storage

TopicsRepository provides a simple in-memory storage using standard Java collections, where every item can be searched by id.

The repository is implemented in a Dagger module and is usually injected by its users rather than instantiated. Although usage of dependency injection was not necessary in this project due to its simplicity, it helps to separate different layers of the architecture. Hypothetically, if at some point in the future we want to change the way data is provided (add network calls, database, etc.), everything could be done just by injecting a different implementation of the repository and without affecting other parts of the app.

## Lifecycle awareness

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) allow us to observe live changes in data without worrying about memory leaks or committing a fragment from a wrong state.

All ViewModel subclasses survive configuration changes, so we always have the most recent data on the screen. No manipulations with `savedInstanceState` are needed.

The data is provided in a reactive way, meaning that the View observes the "stream" and reflects changes, but never updates its state from callbacks.
For example, when a Topic object is updated in the Repository, observers immediately receive a new list of topics, and the new item gets rendered on the screen. They will then automatically unsubscribe from changes when the corresponding View enters an inactive state.

## Testing

There's one UI test for MainActivity . Please note that we override the TopicsRepository dependency using Dagger, so some data is already stored before running tests.
There is also one Unit test for TopicsRepository.
