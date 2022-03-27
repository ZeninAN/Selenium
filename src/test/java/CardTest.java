import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
        driver = null;
    }
    @Test
    public void shouldSendForm(){
        driver.get("http://0.0.0.0:7777");
        List<WebElement> textFields = driver.findElements(By.className("input__control"));
        textFields.get(0).sendKeys("Андрей");
        textFields.get(1).sendKeys("+79483748594");
        ///driver.findElement().sendKeys("Андрей Корчук");
        //driver.findElement().sendKeys("+79483748594");
        driver.findElement(By.className("checkbox__control")).click();
        driver.findElement(By.tagName("button")).click();
        String actualText = driver.findElement(By.className("order-success")).getText();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected,actualText);
    }
}
