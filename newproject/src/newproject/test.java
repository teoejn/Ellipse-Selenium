package newproject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;
//comment the above line and uncomment below line to use Chrome
//import org.openqa.selenium.chrome.ChromeDriver;
public class test {


    public static void main(String[] args) throws InterruptedException {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.gecko.driver","C:\\Users\\Joshua\\Desktop\\Browser Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
    	
        String baseUrl = "https://todo-list-login.firebaseapp.com/";
        String expectedTitle = "Todo App";
        String actualTitle = "";
        // launch Firefox and direct it to the Base URL
        driver.get(baseUrl);
        // get the actual value of the title
        actualTitle = driver.getTitle();
        Thread.sleep(1000);
        
        //https://stackoverflow.com/questions/9588827/how-to-switch-to-the-new-browser-window-which-opens-after-click-on-the-button
        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();
        
        driver.findElement(By.xpath("//*[@class='btn btn-social btn-github']")).click();   
        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
         
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='login_field']")).sendKeys("dummyaccountjoshua");    
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Qwert@@@123");   
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Thread.sleep(3500);
        
        try {
        	Boolean isPresent = driver.findElements(By.xpath("//*[@name='authorize']")).size() > 0;
            if (isPresent == true) {
            	 driver.findElement(By.xpath("//*[@data-octo-click='oauth_application_authorization']")).click();   
            }
          } catch (Exception e) {
            System.out.println("Something went wrong.");
          }
        
        Thread.sleep(5000);
        // Switch back to original browser (first window)
        driver.switchTo().window(winHandleBefore);
        
        for(int x=0;x<10;x++) {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@ng-model='home.list']")).sendKeys(randomNames());   
        driver.findElement(By.xpath("//*[@ng-click='home.list && home.add()']")).click();
        }
        driver.findElement(By.xpath("//*[@class='btn btn-default']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@class='btn btn-social btn-github']")).click();
        
        Thread.sleep(5000);
        for(int x=0;x<5;x++) {
        driver.findElement(By.xpath("//*[@ng-click='home.delete($index)']")).click();
        }
        /*
         * compare the actual title of the page with the expected one and print
         * the result as "Passed" or "Failed"
         */
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        //System.out.println(randomNames());
        //close Firefox
        //driver.close();
    }

	private static String randomNames() {
		    int leftLimit = 97; // letter 'a'
		    int rightLimit = 122; // letter 'z'
		    int targetStringLength = 10;
		    String  generatedString =  "abc";
		    Random random = new Random();

		    for(int x=1;x<=100;x++) {
		        StringBuilder buffer = new StringBuilder(targetStringLength);
		    for (int i = 0; i < targetStringLength; i++) {
		        int randomLimitedInt = leftLimit + random.nextInt(rightLimit - leftLimit + 1);
		        buffer.append((char) randomLimitedInt);
		    }
		    generatedString = buffer.toString();
		    }
		    return generatedString;
	}
}
