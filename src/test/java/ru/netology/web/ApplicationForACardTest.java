package ru.netology.web;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static dev.failsafe.internal.util.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationForACardTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    void test1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("span[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("span[data-test-id='phone'] input")).sendKeys("+79859175033");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button_view_extra")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
}
