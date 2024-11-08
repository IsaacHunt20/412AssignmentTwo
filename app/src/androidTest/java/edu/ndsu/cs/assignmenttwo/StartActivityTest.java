package edu.ndsu.cs.assignmenttwo;

import android.content.Context;
import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;
import androidx.test.core.app.ApplicationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static androidx.test.uiautomator.Until.hasObject;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartActivityTest {

    private UiDevice device;
    private static final String BASIC_SAMPLE_PACKAGE = "edu.ndsu.cs.assignmenttwo";  // Updated package name
    private static final long LAUNCH_TIMEOUT = 5000;  // Timeout in milliseconds

    @Before
    public void setUp() throws Exception {
        // Initialize the UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Ensure the device is unlocked and go to the home screen
        device.wakeUp();
        if (device.isScreenOn()) {
            device.pressHome();
        }

        // Start the main activity from the home screen
        startMainActivityFromHomeScreen();
    }

    private void startMainActivityFromHomeScreen() {
        // Start from the home screen
        device.pressHome();

        // Wait for the launcher package to appear
        final String launcherPackage = device.getLauncherPackageName();
        assertThat("Launcher package is null", launcherPackage, notNullValue());

        // Wait for the launcher to appear and then launch the app
        device.wait(hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the app using the package name
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        assert intent != null;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);  // Clear any previous instance
        context.startActivity(intent);

        // Wait for the app's main activity to appear
        device.wait(hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        // Verify that the main activity has started
        UiObject2 mainActivityScreen = device.findObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0));
        assertThat("Main activity screen did not appear", mainActivityScreen, notNullValue());
    }

    @Test
    public void testStartActivityExplicitly() throws Exception {
        // Click the "start activity explicitly" button
        clickStartActivityButton();

        // Verify the second activity (check if challenge text is present)
        verifySecondActivity();
    }

    private void clickStartActivityButton() throws Exception {
        // Find and click the "start activity explicitly" button
        UiObject startButton = device.findObject(new UiSelector().text("Start Activity Explicitly"));
        if (startButton.exists()) {
            startButton.click();
        } else {
            throw new AssertionError("'Start Activity Explicitly' button not found");
        }

        // Wait for the second activity to open
        device.wait(hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        // Verify that the second activity is opened by checking for a UI element that is only present in the second activity
        UiObject2 secondActivityScreen = device.findObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0));
        assertThat("Second activity screen did not appear", secondActivityScreen, notNullValue());
    }

    private void verifySecondActivity()  {
        // Check if the second activity contains the expected challenge text
        UiObject challengeText = device.findObject(new UiSelector().textContains("User Experience - If the users do not like the app it will be quickly dropped on the App store"));
        assertThat("Expected challenge text not found", challengeText.exists(), is(true));

    }
}
