package com.example.android.architecture.blueprints.todoapp.tasks;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.android.architecture.blueprints.todoapp.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToDoAppUITest {

    @Rule
    public ActivityTestRule<TasksActivity> mActivityTestRule = new ActivityTestRule<>(TasksActivity.class);

    @Test
    public void test01_AddTask() {

        //CLick add task button to start add task
        onView(withId(R.id.fab_add_task))
                .perform(click());
        onView(withText("New TO-DO"))
                .check(matches(isDisplayed()));

        //Type in title, description, and save. Check if screen back to task list
        onView(withId(R.id.add_task_title))
                .perform(click())
                .perform(typeText("Arthur Test Title"));
        onView(withId(R.id.add_task_description))
                .perform(click())
                .perform(typeText("Arthur Test Description"));
        onView(withId(R.id.fab_edit_task_done))
                .perform(click());
        onView(withText("All TO-DOs"))
                .check(matches(isDisplayed()));

        //Open the added item. Check it title and description are right
        onView(allOf(withId(R.id.title), withText("Arthur Test Title")))
                .perform(click());
        onView(allOf(withId(R.id.task_detail_title), withText("Arthur Test Title")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.task_detail_description), withText("Arthur Test Description")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test02_EditTask() {

        //Check the editing target is existing. Content are not changed after restart app
        onView(allOf(withId(R.id.title), withText("Arthur Test Title")))
                .perform(click());
        onView(allOf(withId(R.id.task_detail_title), withText("Arthur Test Title")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.task_detail_description), withText("Arthur Test Description")))
                .check(matches(isDisplayed()));

        //Clear all the current content, and write new string
        onView(withId(R.id.fab_edit_task))
                .perform(click());
        onView(withId(R.id.add_task_title))
                .perform(click())
                .perform(replaceText("Arthur Edited Title"));
        onView(withId(R.id.add_task_description))
                .perform(click())
                .perform(replaceText("Arthur Edited Description"));
        onView(withId(R.id.fab_edit_task_done))
                .perform(ViewActions.closeSoftKeyboard())
                .perform(click());
        onView(withText("All TO-DOs"))
                .check(matches(isDisplayed()));

        //Open the edited item. Check it title and description are right
        onView(allOf(withId(R.id.title), withText("Arthur Edited Title")))
                .perform(click());
        onView(allOf(withId(R.id.task_detail_title), withText("Arthur Edited Title")))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.task_detail_description), withText("Arthur Edited Description")))
                .check(matches(isDisplayed()));
    }

    @Test
    public void test03_DeleteTask() {

        //Check the deleting target is existing after restart app
        onView(allOf(withId(R.id.title), withText("Arthur Edited Title")))
                .check(matches(isDisplayed()));

        //Open the target and delete it
        onView(allOf(withId(R.id.title), withText("Arthur Edited Title")))
                .perform(click());
        onView(withId(R.id.menu_delete))
                .perform(click());

        //Check if the page is on first page by check adding button. And deleting is success
        onView(withId(R.id.fab_add_task))
                .check(matches(isDisplayed()));
        onView(allOf(withId(R.id.title), withText("Arthur Edited Title")))
                .check(matches(not(isDisplayed())));
    }
}
