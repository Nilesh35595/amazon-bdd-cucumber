package step_definitions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.amazon.ui.core.DriverManager;
import com.amazon.ui.utility.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    WebDriver driver;
    protected static final Logger LOGGER = LogManager.getLogger(Hooks.class);
    String url = Config.getUrl(); 

    @Before
    public void beforeScenario() {
        try {
            driver = DriverManager.createInstance(Config.getBrowser());
            DriverManager.get(url);
        } catch (Exception e) {
            LOGGER.info(e);
            LOGGER.error("Failed to instantiate WebDriver instance");
        }
    }

    @After
    public void afterScenario() {
        try {
            LOGGER.info("Finishing test run");
            DriverManager.exitBrowser();
        } catch (Exception e) {
            LOGGER.error("Failed to finish the session " + e.getMessage());
        }
    }
}
