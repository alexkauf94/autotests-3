package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class appForCardTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void createBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void AfterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void appForCardTest1() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id = 'name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id = 'phone'] input")).sendKeys("+79110002345");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement'] span")).click();
        driver.findElement(By.className("button_theme_alfa-on-white")).click();

        String actualText = driver.findElement(By.cssSelector("[data-test-id = 'order-success']")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.strip());
    }
}