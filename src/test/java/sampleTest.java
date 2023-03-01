
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class sampleTest extends BaseTest {

    AppiumDriver driver;
    AppiumDriverLocalService service;

    public sampleTest() throws MalformedURLException {
    }

    @BeforeClass
    void startAppiumServer() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(8888)
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/bin/appium"));
        HashMap<String, String> environment = new HashMap();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        serviceBuilder.withEnvironment(environment);
        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();
    }

    @BeforeMethod
    void startAppiumSession() {
        System.out.println("...Setup Started.....");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Galaxy S9");
        capabilities.setCapability("appPackage", "com.dexcom.dexcomone");
        capabilities.setCapability("appActivity", "com.dexcom.phoenix.ui.SplashActivity");
        driver = new AndroidDriver(service.getUrl(), capabilities);
    }

    @Test
    @Description ("Login for D1")
    void login() throws IOException {
        System.out.println("Launching the app.....");
       /* driver.launchApp();
        driver.findElement(By.id("id_login_button")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='mat-input-0']"))
                .sendKeys(getUserName());
        driver.findElement(By.xpath("//android.widget.Button[@resource-id='login_id_btn']")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='password']"))
                .sendKeys(getPassword());
        driver.findElement(By.xpath("//android.view.View[@resource-id='edit-actions']/android.widget.Button")).click();*/
    }

    @Attachment
    public byte[] takeFailedScreenShot() {
       return driver.getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    void endSession() {
        driver.quit();
    }

    @AfterClass
    void stopAppiumServer() {
        service.stop();
    }

}
