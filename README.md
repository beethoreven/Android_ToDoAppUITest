# Android_ToDoAppUITest

This code run for to-do app UI test. Following steps describe how to run it with emulator.
Before ready to test, install Android SDK and prepare your emulator device in Android AVD manager to build the app and test. For my example, use Pixel_3a_API_30_x86.

1. Change directiry to any place you want to put the test file. And git clone to get ToDoListUITests.swift here.

>git clone git@github.com:beethoreven/iOS_ToDoAppUITest.git


2. Change directiry to any place you want to put the to-do app project. Since it provide many branches to pull, I use todo-mvp branch.

>git clone git@github.com:denyszelenchuk/architecture-samples.git
>git checkout todo-mvp

3. Put the `ToDoAppUITest.jave` to a place. In my example, put it to `/YOUR_PATH_OF_architecture-samples/todoapp/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/tasks/ToDoAppUITest.java`. This path related to the test's class.

4. Into the todo app project folder

>cd architecture-samples/todoapp/

5. Install the debug app, and debug test.

>./gradlew installMockDebug

>./gradlew installMockDebugAndroidTest

6. You can definitely use Android SDK to open rpoject, and run UI test by right-click. The result will be the same as run with console command

7. Run the UI test on sample device emulator.

>adb shell am instrument -w \
>  -e class 'com.example.android.architecture.blueprints.todoapp.tasks.ToDoAppUITest'
>  com.example.android.architecture.blueprints.todomvp.mock.test/androidx.test.runner.AndroidJUnitRunner

8, Test result will be like this.

![result](https://i.imgur.com/id2ByIn.png)
