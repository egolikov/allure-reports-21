package guru.qa.allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Открываем главную страницу GitHub")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Поиск репозитория по наименованию {nameRepository}")
    public void searchForRepository(String nameRepository) {
        $(".search-input").click();
        $("#query-builder-test").sendKeys(nameRepository);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке найденного репозитория {nameRepository}")
    public void clickOnRepositoryLink(String nameRepository) {
        $(linkText(nameRepository)).click();
    }

    @Step("Открываем Issues")
    public void openIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с Наименованием {nameIssue}")
    public void shouldSeeIssueWithName(String nameIssue) {
        $(withText(nameIssue)).should(exist);
    }
}
