# TVMaze

TVMaze is an App to show a list of several series to users. It is based in API https://www.tvmaze.com/api

The App offers the following features:

* List all of the series contained in the API used by the paging scheme provided by the API.
* Allow users to search series by name.
* The listing and search views show the name and poster image of the series.
* After clicking on a series, the application shows the details of the series with the following information:
    * Name
    * Poster
    * Days and time during which the series airs
    * Genres
    * Summary
    * List of episodes separated by season

* After clicking on an episode, the application shows the episode’s information, including:
    * Name
    * Number
    * Season
    * Summary
    * Image, if there is one

# How to run it

To run the App only is needed to clone the project with 'git clone', open it with Android Studio 
Bumblebee, wait for synchronization and after run the App as with other Android Apps

As it is an App demo, There are two values to simulate slow networks and check loaders functionality.
Change 'Constants.DEBUG' to true to simulate it, and put a desired value in Constants.DELAY to lower 
each server call to it. 