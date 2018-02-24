package com.test.sri.testapp;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;


/**
 * Created by Myworld on 23/02/2018.
 */
@RunWith(AndroidJUnit4.class)
public class ScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);

    //

    //assert the state of the screen when set up with all data present,
    @Test
    public void screenTest()throws Exception {

        Espresso.registerIdlingResources(mainActivityActivityTestRule.getActivity().countingIdlingResource);
        onData(allOf(is(instanceOf(NewsFeedListModel.class))))
                .atPosition(13)//Gets The last position of the list view
                .inAdapterView(withId(R.id.main_list_view))
                .onChildView(withId(R.id.title_text_view))
                .check(matches(withText(startsWith("Language"))));//matches with the final title of the list view


        onView(withId(R.id.main_list_view))
                .check(matches(isDisplayed()));

    }

    // assert the state of the screen when in an error state.
    @Test
    public void screenErrorTest()
    {
        onView(withId(R.id.place_image_view))
                .check(doesNotExist());// checks if the image view is null
    }






}
