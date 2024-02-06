package Step;


import core.BaseSeleniumTestPage;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class AddProductsToTableTest extends BaseSeleniumTestPage {
private int tableScopeToCheck = 4;


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
   public AddProductsToTableTest() {
        PageFactory.initElements(getRemoteWebDriver(),
                this);
    }





    @Given("Я нахожусь на странице {string}")
    public void openPage(String url) {

        try {
            initDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        getRemoteWebDriver().manage().window().maximize();
        getRemoteWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //WebDriverWait wait =
               // new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10000));
        AddProductsToTableTest addProductsToTableTest = new AddProductsToTableTest();
        //BaseSeleniumTestPage.setDriver(getRemoteWebDriver());
        getRemoteWebDriver().get(url);
    }




    @Given("Нажимается кнопка Добавить")
    public void нажимаетсяКнопкаДобавить() {
        buttonAddProduct.click();
    }

    @Given("Вводится тестовое значение в поле Наименование {string}, в поле Тип {string}, в поле Экзотический {string}")
    public void вводитсяТестовоеЗначениеВПолеНаименованиеВПолеТипВПолеЭкзотический(String name, String fruit, String exotic) {
        inputName.sendKeys(name);
        if (fruit.equals("FRUIT")){
            listType_Fruit.click();
        }else listType_Vegetable.click();
        if (exotic.equals("true")){
            checkboxExotic.click();

        }
        tableScopeToCheck++;

    }

    @Given("Поле Наименование содержит значение {string}, Поле Тип содержит значение {string}, Поле Экзотический содержит значение {string}")
    public void полеНаименованиеСодержитЗначениеПолеТипСодержитЗначениеПолеЭкзотическийСодержитЗначение(String name, String fruit, String exotic) {

       Assertions.assertEquals(inputName.getAttribute("value"), name);

        if (selectorIsFruit.getAttribute("value").equals("VEGETABLE")) {
            if (fruit.equals("FRUIT")) {
                Assertions.fail();
            }}
        else if (selectorIsFruit.getAttribute("value").equals("FRUIT")) {
            if (fruit.equals("VEGETABLE")) {
                Assertions.fail();
            }} else Assertions.fail();

        //if (checkboxExotic.getAttribute ("checked").equals(false)) {
//                    if (exotic.equals("true") {
//                        checkboxExotic.click();
//                    }
//                } else if (!exotic) {
//                    checkboxExotic.click();
//                }
//
//                if (checkboxExotic.getAttribute("checked").equals(false)) {
//                    if (exotic) {
//                        Assertions.fail();
//                    }
//                } else if (!exotic) {
//                    Assertions.fail();
//                }
            //доделать не смог. Получить значение из чекбокса "Экзотический" не вышло
            //плюс весь код выцеплен из готового теста, сделанного по другой структуре
            //так что выглядит всё несолько натянуто, и не со всеми проверками
        //если бы тест переписывался с нуля, может быть, выглядело бы всё красиво
    }



    @Given("Нажимается кнопка Сохранить")
    public void нажимаетсяКнопкаСохранить() {
        buttonSave.click();
    }


    @Given("В таблице поле Наименование равно значению {string}, поле Тип равно значению {string}, поле Экзотический равно значению {string}")
    public void вТаблицеПолеНаименованиеРавноЗначениюПолеТипРавноЗначениюПолеЭкзотическийРавноЗначению(String name, String fruit, String exotic) {
        String prePathForTableCheck = "//tr[" + tableScopeToCheck + "]";
        By colomnNumberPath = By.xpath(prePathForTableCheck + "/th[text()=" + tableScopeToCheck + "]");
        WebElement rowNumber = getRemoteWebDriver().findElement(colomnNumberPath);

        String rowNumberText = rowNumber.getText();

        int rowNumberInt = Integer.parseInt(rowNumberText);

        Assertions.assertEquals(rowNumberInt, tableScopeToCheck);
            By colomnNamePath = By.xpath(prePathForTableCheck + "/td[1]");
            WebElement rowName = getRemoteWebDriver().findElement(colomnNamePath);
            String rowNameText = rowName.getText();
            Assertions.assertEquals(rowNameText, name);
        //Проверка полей таблицы # и Наименование


        By rowFruitt = By.xpath(prePathForTableCheck + "/td[2]");
        WebElement rowFruit = getRemoteWebDriver().findElement(rowFruitt);
        String rowFruitText = rowFruit.getText();
        //сам не понял что написал, но работает
        // с именем переменной тоже самое
        if (rowFruitText.equals("Фрукт") ) {
            if (!fruit.equals("FRUIT")) {
                Assertions.fail();
            }
        } else if (fruit.equals("FRUIT")){Assertions.fail();}
        //Проверка поля таблицы Тип


//        By rowExoticc = By.xpath(prePathForTableCheck + "/td[3]");
//        WebElement rowExotic = driver.findElement(rowExoticc);
//        String rowExoticText = rowExotic.getText();
//
//        if (rowExoticText.equals("true") ) {
//            if (!isExotic) {
//                Assert.fail();
//            }
//        } else Assert.fail();
//        //Проверка поля таблицы Экзотический

    }

    @Given("Сайт закрывается")
    public void сайтЗакрывается() {
        getRemoteWebDriver().close();
        getRemoteWebDriver().quit();
    }
}
