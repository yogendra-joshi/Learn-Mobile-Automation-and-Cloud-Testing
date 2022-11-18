package swiggyAppTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class SwiggyAppTest {
	private AndroidDriver driver;
	
	 @BeforeTest
	  public void setUp() throws MalformedURLException, InterruptedException {
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("appium:platformVersion", "11");
	    desiredCapabilities.setCapability("appium:deviceName", "Samsung SM-M022G");
	    desiredCapabilities.setCapability("appium:appPackage", "in.swiggy.android");
	    desiredCapabilities.setCapability("appium:appActivity", "in.swiggy.android.activities.HomeActivity");
	    
	    
	    URL remoteUrl = new URL("http://localhost:4723/wd/hub");

	    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		initalSetUp();
	  }
	 
	 public void initalSetUp() throws InterruptedException {
		  System.out.println("Started setup...");
		  Thread.sleep(5000);
		  // Continue as guest
		  // driver.findElementById("in.swiggy.android:id/tv_secondary_cta").click();
		  // Thread.sleep(2000);
		  // Set location
		  driver.findElementById("in.swiggy.android:id/tv_primary_cta").click();
		  Thread.sleep(2000);
		  
		  // Allow access location permission
		  driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button").click();
		  Thread.sleep(2000);
		  		  
		  // confirm location
		  driver.findElement(By.id("in.swiggy.android:id/google_place_search_title_text1")).click();
		  Thread.sleep(5000);
		  System.out.println("Completed setup!");
	 }
	  
	 public void search(String text) throws InterruptedException {

		 System.out.println("Searching for " + text);
		 Thread.sleep(500);
		  driver.findElement(By.id("in.swiggy.android:id/disc_search_bar_container")).click();
		  Thread.sleep(2500);
		  driver.findElement(By.id("in.swiggy.android:id/et_search_query_v2")).sendKeys(text);
		  Thread.sleep(2500);
		  driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]").click();
		  
		  Thread.sleep(5000);
		  driver.findElementByAccessibilityId("go back").click();
		  Thread.sleep(500);
		  driver.findElementByAccessibilityId("go back").click();
		  Thread.sleep(5000);
	 }
	 
	  @Test
	  public void searchItemTest() throws InterruptedException {
		  // Turn on location access
		  search("Biryani");
	  }

	  @Test
	  public void searchRestaurantTest() throws InterruptedException {
		  
		  search("A2B");
	  }
	  
	  @Test
	  public void changeLocation() throws InterruptedException {
		  
		  String location = "Bangalore";
		  System.out.println("Changing location to " + location);
		  driver.findElementById("in.swiggy.android:id/arrow_imageview").click();
		  Thread.sleep(1000);
		  driver.findElementById("in.swiggy.android:id/location_description").sendKeys(location);
		  Thread.sleep(1000);
		  driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]").click();
		  Thread.sleep(1000);
		  driver.findElementById("in.swiggy.android:id/google_place_search_title_text1").click();
		  System.out.println("Location changed to " + location);
		  Thread.sleep(2000);
	  }
	  
	  @AfterTest
	  public void tearDown() {
	    driver.quit();
	  }
	 
}
