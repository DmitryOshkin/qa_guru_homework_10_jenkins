package yandex.oshkin.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static yandex.oshkin.tests.TestData.*;

public class PracticeFormTests extends TestBase {

    public void fillForm(String firstName, String lastName, String email, String gender,
                         String phoneNumber,
                         String day, String month, String year,
                         String subject_1, String subject_2,
                         String hobbie_1, String hobbie_2,
                         String picture,
                         String address,
                         String state, String city) {
        registrationPage
                .openPage();
        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .selectGender(gender)
                .setPhoneNumber(phoneNumber)
                .setBirthDate(day, month, year)
                .setSubject(subject_1)
                .setSubject(subject_2)
                .selectHobbies(hobbie_1)
                .selectHobbies(hobbie_2)
                .uploadFile(picturePath + picture)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmit();
    }

    public void checkResultForm(String header,
                                String firstName, String lastName,
                                String email, String gender,
                                String phoneNumber,
                                String day, String month, String year,
                                String subject_1, String subject_2,
                                String hobbie_1, String hobbie_2,
                                String picture,
                                String address,
                                String state, String city) {
        registrationPage
                .checkResultsFormHeaderText(header)
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", gender)
                .checkResultsValue("Mobile", phoneNumber)
                .checkResultsValue("Date of Birth", day + " " + month + "," + year) //"05 June,1988"
                .checkResultsValue("Subjects", subject_1 + ", " + subject_2)
                .checkResultsValue("Hobbies", hobbie_1 + ", " + hobbie_2)
                .checkResultsValue("Picture", picture)
                .checkResultsValue("Address", address)
                .checkResultsValue("State and City", state + " " + city); //Haryana Panipat
    }

    @Test
    @Tag("randomValues")
    @Owner("OshkinDmitrii")
    @Feature("Forms")
    @Story("Заполнение Student Registration Form отдельными шагами")
    @DisplayName("Заполнение Student Registration Form с использованием аннотаций и лиссенера по умолчанию для отчета")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "demoqa", url = "https://demoqa.com/")
    void fillPracticeFormStepsTest() {

        fillForm(randomFirstName,
                randomLastName,
                randomUserEmail,
                maleGender,
                randomPhoneNumber,
                day, month, year,
                subject_1, subject_2,
                hobbie_1,
                hobbie_2,
                picture,
                randomAddress,
                state, city);
        checkResultForm("Thanks for submitting the form",
                randomFirstName, randomLastName,
                randomUserEmail,
                maleGender,
                randomPhoneNumber,
                day, month, year,
                subject_1, subject_2,
                hobbie_1, hobbie_2,
                picture,
                randomAddress,
                state, city);

    }


    @Test
    @Tag("exactValues")
    @DisplayName("Заполнение Student Registration Form с использованием и аннотаций, лямбда шагов и лиссенера для отчета")
    void fillPracticeFormBlockTest() {

        Allure.label("owner", "OshkinDmitrii");
        Allure.feature("Forms");
        Allure.story("Заполнение Student Registration Form блоком");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("demoqa", "https://demoqa.com/");

        step("Открываем форму регистрации и заполняем данные о студенте", () -> {
            fillForm(firstName,
                    lastName,
                    email,
                    maleGender,
                    phone,
                    day, month, year,
                    subject_1, subject_2,
                    hobbie_1,
                    hobbie_2,
                    picture,
                    address,
                    state, city);
        });
        step("Проверяем корректность заполнения формы", () -> {
            checkResultForm("Thanks for submitting the form",
                    firstName, lastName,
                    email,
                    maleGender,
                    phone,
                    day, month, year,
                    subject_1, subject_2,
                    hobbie_1, hobbie_2,
                    picture,
                    address,
                    state, city);
        });
    }

    @Test
    @Tag("randomValues")
    @DisplayName("Заполнение Student Registration Form с использованием и аннотаций и лиссенера для отчета")
    void fillPracticeFormListenerTest() {

        Allure.label("owner", "OshkinDmitrii");
        Allure.feature("Forms");
        Allure.story("Заполнение Student Registration Form поэтапно по полям");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("demoqa", "https://demoqa.com/");

        fillForm(randomFirstName,
                randomLastName,
                randomUserEmail,
                maleGender,
                randomPhoneNumber,
                day, month, year,
                subject_1, subject_2,
                hobbie_1,
                hobbie_2,
                picture,
                randomAddress,
                state, city);
        checkResultForm("Thanks for submitting the form",
                randomFirstName, randomLastName,
                randomUserEmail,
                femaleGender,
                randomPhoneNumber,
                day, month, year,
                subject_1, subject_2,
                hobbie_1, hobbie_2,
                picture,
                randomAddress,
                state, city);

    }
}
