package unit;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;

import static com.coveros.selenified.utilities.Property.*;

public class SaveProperties {
    protected static final String SELENIFIED = "src/test/resources/selenified.properties";

    static final String DEFAULT_WAIT = "defaultWait";
    static final String GENERATE_PDF = "generatePDF";
    static final String PACKAGE_RESULTS = "packageResults";
    static final String HUB = "hub";
    static final String PROXY = "proxy";

    private String setDefaultWait = null;
    private String setGeneratePDF = null;
    private String setPackageResults = null;
    private String setHub = null;
    private String setProxy = null;
    private String setAppUrl = null;
    private String setBrowser = null;
    private String setHeadless = null;
    private String setOptions = null;

    File propertiesFile = new File(SELENIFIED);
    File savePropertiesFile = new File(SELENIFIED + ".tmp");

    @BeforeClass(alwaysRun = true)
    public void saveProperties() {
        if (System.getProperty(DEFAULT_WAIT) != null) {
            setDefaultWait = System.getProperty(DEFAULT_WAIT);
        }
        if (System.getProperty(GENERATE_PDF) != null) {
            setGeneratePDF = System.getProperty(GENERATE_PDF);
        }
        if (System.getProperty(PACKAGE_RESULTS) != null) {
            setPackageResults = System.getProperty(PACKAGE_RESULTS);
        }
        if (System.getProperty(HUB) != null) {
            setHub = System.getProperty(HUB);
        }
        if (System.getProperty(PROXY) != null) {
            setProxy = System.getProperty(PROXY);
        }
        if (System.getProperty(APP_URL) != null) {
            setAppUrl = System.getProperty(APP_URL);
        }
        if (System.getProperty(BROWSER) != null) {
            setBrowser = System.getProperty(BROWSER);
        }
        if (System.getProperty(HEADLESS) != null) {
            setHeadless = System.getProperty(HEADLESS);
        }
        if (System.getProperty(OPTIONS) != null) {
            setOptions = System.getProperty(OPTIONS);
        }
        propertiesFile.renameTo(savePropertiesFile);
    }

    @AfterClass(alwaysRun = true)
    public void restoreProperties() {
        if (setDefaultWait!= null) {
            System.setProperty(DEFAULT_WAIT, setDefaultWait);
        }
        if (setGeneratePDF != null) {
            System.setProperty(GENERATE_PDF, setGeneratePDF);
        }
        if (setPackageResults != null) {
            System.setProperty(PACKAGE_RESULTS, setPackageResults);
        }
        if (setHub != null) {
            System.setProperty(HUB, setHub);
        }
        if (setProxy != null) {
            System.setProperty(PROXY, setProxy);
        }
        if (setAppUrl != null) {
            System.setProperty(APP_URL, setAppUrl);
        }
        if (setBrowser != null) {
            System.setProperty(BROWSER, setBrowser);
        }
        if (setHeadless != null) {
            System.setProperty(HEADLESS, setHeadless);
        }
        if (setOptions != null) {
            System.setProperty(OPTIONS, setOptions);
        }
        savePropertiesFile.renameTo(propertiesFile);
    }

    @BeforeMethod(alwaysRun = true)
    @AfterMethod(alwaysRun = true)
    public void clearProperties(ITestContext context) {
        System.clearProperty(DEFAULT_WAIT);
        System.clearProperty(GENERATE_PDF);
        System.clearProperty(PACKAGE_RESULTS);
        System.clearProperty(HUB);
        System.clearProperty(PROXY);
        System.clearProperty(APP_URL);
        context.removeAttribute(this.getClass().getName() + APP_URL);
        System.clearProperty(BROWSER);
        System.clearProperty(HEADLESS);
        System.clearProperty(OPTIONS);

        if (new File(SELENIFIED).exists()) {
            new File(SELENIFIED).delete();
        }
    }
}
