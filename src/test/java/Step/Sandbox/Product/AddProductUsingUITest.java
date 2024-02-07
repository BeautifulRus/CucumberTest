package Step.Sandbox.Product;


import core.DriverInit;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class AddProductUsingUITest extends DriverInit {
    private int tableScopeToCheck = 4; // значение строки в таблице, которую мы проверяем.
    // По умолчанию, заполненны первые 4


    @FindBy(xpath = "//div/div/div/div[1]/button")
    private static WebElement buttonAddProduct;

    @FindBy(xpath = "//input [@id='name']")
    private static WebElement inputName;

    @FindBy(xpath = "//select [@id =\"type\"]")
    private static WebElement selectorIsFruit;
    @FindBy(xpath = "//select/option [@value=\"FRUIT\"]")
    private static WebElement listType_Fruit;
    @FindBy(xpath = "//select/option [@value=\"VEGETABLE\"]")
    private static WebElement listType_Vegetable;

    @FindBy(xpath = "//input [@id = \"exotic\"]")
    private static WebElement checkboxExotic;

    @FindBy(xpath = "//button [@id = \"save\"]")
    private static WebElement buttonSave;

    public AddProductUsingUITest() {
        PageFactory.initElements(remoteWebDriver,
                this);
    }


    @Given("Я нахожусь на странице {string}")
    public void яНахожусьНаСтранице(String url) throws IOException {
        initDriver();
        AddProductUsingUITest addProductUsingUITest = new AddProductUsingUITest();
        remoteWebDriver.get(url);
    }


    @Given("Нажимается кнопка Добавить")
    public void нажимаетсяКнопкаДобавить() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddProduct));
        buttonAddProduct.click();
    }

    @Given("Вводится тестовое значение в поле Наименование {string}, в поле Тип {string}, в поле Экзотический {string}")
    public void вводитсяТестовоеЗначениеВПолеНаименованиеВПолеТипВПолеЭкзотический(String name, String fruit, String exotic) {
        inputName.sendKeys(name);
        if (fruit.equals("FRUIT")) {
            listType_Fruit.click();
        } else listType_Vegetable.click();
        if (exotic.equals("true")) {
            checkboxExotic.click();
        }
    }

    @Given("Поле Наименование содержит значение {string}, Поле Тип содержит значение {string}, Поле Экзотический содержит значение {string}")
    public void полеНаименованиеСодержитЗначениеПолеТипСодержитЗначениеПолеЭкзотическийСодержитЗначение(String name, String fruit, String exotic) {

        Assertions.assertEquals(inputName.getAttribute("value"), name); // Сверка введённых данных поля "Наименование"

        if (selectorIsFruit.getAttribute("value").equals("VEGETABLE")) {
            if (fruit.equals("FRUIT")) {
                Assertions.fail();
            }
        } else if (selectorIsFruit.getAttribute("value").equals("FRUIT")) {
            if (fruit.equals("VEGETABLE")) {
                Assertions.fail();
            }
        } else Assertions.fail(); // Сверка введённых данных поля "Тип"

        /* Сверка введённых данных поля "Экзотический"
                достать значение чекбокса с песочницы невозможно. Атрибут не возвращает изменённое значение.
                поэтому проверки нет.*/
    }


    @Given("Нажимается кнопка Сохранить")
    public void нажимаетсяКнопкаСохранить() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonSave));
        buttonSave.click();
        tableScopeToCheck++;
    }


    @Given("В таблице поле Наименование равно значению {string}, поле Тип равно значению {string}, поле Экзотический равно значению {string}")
    public void вТаблицеПолеНаименованиеРавноЗначениюПолеТипРавноЗначениюПолеЭкзотическийРавноЗначению(String name, String fruit, String exotic) {
        String prePathForTableCheck = "//tr[" + tableScopeToCheck + "]";
        By colomnNumberPath = By.xpath(prePathForTableCheck + "/th[text()=" + tableScopeToCheck + "]");
        WebElement rowNumber = remoteWebDriver.findElement(colomnNumberPath);
        int rowNumberInt = Integer.parseInt(rowNumber.getText());
        Assertions.assertEquals(rowNumberInt, tableScopeToCheck); //проверка очерёдности строки с её идентификатором || проверка поля "#"

        By colomnNamePath = By.xpath(prePathForTableCheck + "/td[1]");
        WebElement rowName = remoteWebDriver.findElement(colomnNamePath);
        Assertions.assertEquals(rowName.getText(), name); //проверка поля "Наименование"


        By colomnFruitPath = By.xpath(prePathForTableCheck + "/td[2]");
        WebElement rowFruit = remoteWebDriver.findElement(colomnFruitPath);
        if (rowFruit.getText().equals("Фрукт")) {
            if (!fruit.equals("FRUIT")) {
                Assertions.fail();
            }
        } else if (fruit.equals("FRUIT")) {
            Assertions.fail();
        }
        //Проверка поля "Тип"

        /*Проверка поля "Экзотический":
            достать значение чекбокса с песочницы невозможно. Атрибут не возвращает изменённое значение.
             поэтому проверки нет.*/

    }

    @Given("Сайт закрывается")
    public void сайтЗакрывается() {
        remoteWebDriver.close();
        remoteWebDriver.quit();
    }
}
