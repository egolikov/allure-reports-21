package guru.qa.allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String NAME_REPOSITORY = "egolikov/allure-reports-21";
    private static final String NAME_ISSUE = "Hello! This is test issue";

    @Test
    public void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("https://github.com");
        });

        step("Поиск репозитория по наименованию " + NAME_REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").sendKeys(NAME_REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке найденного репозитория " + NAME_REPOSITORY, () -> {
            $(linkText(NAME_REPOSITORY)).click();
        });

        step("Открываем Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с Наименованием " + NAME_ISSUE, () -> {
            $(withText(NAME_ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotationStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchForRepository(NAME_REPOSITORY);
        steps.clickOnRepositoryLink(NAME_REPOSITORY);
        steps.openIssues();
        steps.shouldSeeIssueWithName(NAME_ISSUE);

    }
}


