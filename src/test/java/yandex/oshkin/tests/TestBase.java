package yandex.oshkin.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import yandex.oshkin.config.Credentials;
import yandex.oshkin.helpers.Attach;
import yandex.oshkin.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {


    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "91");
        Configuration.browserSize = System.getProperty("browserSize", "1024x768");

        String remoteUrl = System.getProperty("remoteUrl"); //Получаем значение из параметров Jenkins
        String user = Credentials.config.user();
        String password = Credentials.config.password();
        Configuration.remote = format("https://%s:%s@%s", user, password, remoteUrl);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSourceText();
        Attach.PageSourceHtml();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    RegistrationPage registrationPage = new RegistrationPage();
    static Faker faker = new Faker(); //new Locale("ru") внутрь Faker для локализации языка
}
