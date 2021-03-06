package integration;

import com.coveros.selenified.Locator;
import com.coveros.selenified.application.App;
import com.coveros.selenified.exceptions.InvalidHTTPException;
import com.coveros.selenified.utilities.Property;
import org.openqa.selenium.Cookie;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.testng.Assert.assertNull;

public class ActionGoIT extends WebBase {

    @Test(groups = {"integration", "action", "go", "browser", "alert"},
            description = "An integration test to check the goBackOnePage method")
    public void goBackOnePageTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.newElement(Locator.ID, "submit_button").submit();
        app.azzert().textPresent("You're on the next page");
        app.goBack();
        app.azzert().textNotPresent("You're on the next page");
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go", "alert"},
            description = "An integration test to check the goBackOnePage method")
    public void goBackOnePageNoBackTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.goBack();
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the goBackOnePage method")
    public void goBackOnePageErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.goBack();
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go", "browser", "alert"},
            description = "An integration test to check the goForwardOnePage method")
    public void goForwardOnePageTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.newElement(Locator.ID, "submit_button").submit();
        app.azzert().textPresent("You're on the next page");
        app.goBack();
        app.azzert().textNotPresent("You're on the next page");
        app.goForward();
        app.azzert().textPresent("You're on the next page");
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go", "alert"},
            description = "An integration test to check the goForwardOnePage method")
    public void goForwardOnePageNoForwardTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.goForward();
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the goForwardOnePage method")
    public void goForwardOnePageErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.goForward();
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the refreshPage method")
    public void refreshPageTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.refresh();
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the refreshPage method")
    public void refreshPageErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.refresh();
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the refreshPageHard method")
    public void refreshPageHardTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.refreshHard();
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the refreshPageHard method")
    public void refreshPageHardErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.refreshHard();
        // verify 1 issue
        finish(1);
    }

    // skipping edge as retrieving cookies isn't working: https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/14838528/
    @Test(groups = {"integration", "action", "cookie", "no-edge"}, description = "An integration test to check the setCookie method")
    public void setCookieTest(ITestContext context) throws ParseException, InvalidHTTPException {
        String dateval = "2011-11-17T09:52:13";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Cookie cookie = new Cookie("new_cookie", "this_cookie",
                Property.getAppURL(this.getClass().getName(), context).split("/")[2].split(":")[0], "/", df.parse(dateval));
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.setCookie(cookie);
        // verify no issues
        finish();
    }

    // skipping edge as retrieving cookies isn't working: https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/14838528/
    @Test(groups = {"integration", "action", "go", "cookie", "no-edge"}, description = "An integration test to check the setCookie method")
    public void setCookieErrorTest(ITestContext context) throws ParseException, InvalidHTTPException {
        String dateval = "2011-11-17T09:52:13";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Cookie cookie = new Cookie("new_cookie", "this_cookie",
                Property.getAppURL(this.getClass().getName(), context).split("/")[2].split(":")[0], "/", df.parse(dateval));
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.setCookie(cookie);
        // verify 1 issue
        finish(1);
    }

    // skipping edge as retrieving cookies isn't working: https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/14838528/
    @Test(groups = {"integration", "action", "go", "cookie", "no-edge"},
            description = "An integration test to check the deleteCookie method")
    public void deleteCookieTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.deleteCookie("cookie");
        Cookie cookie = app.get().cookie("cookie");
        assertNull(cookie);
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration negative test to check the deleteCookie method")
    public void deleteNonExistentCookieTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.deleteCookie("new_cookie");
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration negative test to check the deleteCookie method")
    public void deleteCookieBadDriverTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.deleteCookie("cookie");
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the deleteAllCookies method")
    public void deleteAllCookiesTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.deleteAllCookies();
        Cookie cookie = app.get().cookie("cookie");
        assertNull(cookie);
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the deleteAllCookies method")
    public void deleteAllCookiesTwiceTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.deleteAllCookies();
        app.deleteAllCookies();
        Cookie cookie = app.get().cookie("cookie");
        assertNull(cookie);
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the deleteAllCookies method")
    public void deleteAllCookiesErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.deleteAllCookies();
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the maximizeScreen method")
    public void maximizeScreenTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.maximize();
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration test to check the maximizeScreen method")
    public void maximizeScreenErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.maximize();
        // verify 1 issue
        finish(1);
    }

    @Test(groups = {"integration", "action", "go"}, description = "An integration test to check the resize method")
    public void resizeScreenTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.resize(200, 300);
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"}, description = "An integration test to check the resize method")
    public void resizeScreenTooLargeTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.resize(200000, 300000);
        // verify no issues
        finish();
    }

    @Test(groups = {"integration", "action", "go"},
            description = "An integration negative test to check the resize method")
    public void resizeScreenErrorTest() {
        // use this object to manipulate the app
        App app = this.apps.get();
        // perform some actions
        app.killDriver();
        app.resize(200, 300);
        // verify no issues
        finish(1);
    }
}