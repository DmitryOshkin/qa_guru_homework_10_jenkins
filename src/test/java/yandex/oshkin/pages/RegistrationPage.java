package yandex.oshkin.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import yandex.oshkin.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $("#react-select-3-input"),
            citySelect = $("#react-select-4-input"),
            submitButton = $("#submit"),
            resultFormHeaderText = $(".modal-content"),
            resultsTable = $(".table-responsive");

    public CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Открываем страницу заполнения регистрационной формы студентов")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper")
                .shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Заполняем имя студента")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Заполняем фамилию студента")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Заполняем email студента")
    public RegistrationPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Выбираем пол студента")
    public RegistrationPage selectGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    @Step("Заполняем номер телефона студента")
    public RegistrationPage setPhoneNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Заполняем дату рождения студента")
    public RegistrationPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    @Step("Вводим предметы")
    public RegistrationPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage selectHobbies(String value) {
        hobbiesInput.$(byText(value)).scrollTo().click();
        return this;
    }

    @Step("Загружаем картинку")
    public RegistrationPage uploadPicture(String fileName) {
        pictureInput.uploadFromClasspath(fileName);
        return this;
    }
    @Step("Загружаем файл {file}")
    public RegistrationPage uploadFile(String file) {
        File cat = new File(file);
        pictureInput.uploadFile(cat);
        return this;
    }

    @Step("Загружаем адресс")
    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationPage selectState(String value) {
        stateSelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationPage selectCity(String value) {
        citySelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Завершаем заполнение формы")
    public RegistrationPage clickSubmit() {
        submitButton.scrollTo().click();
        return this;
    }

    @Step("Проверяем заголовок формы")
    public RegistrationPage checkResultsFormHeaderText(String value) {
        resultFormHeaderText.shouldHave(text(value));
        return this;
    }

    @Step("Проверка заполненных данных")
    public RegistrationPage checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }

}
