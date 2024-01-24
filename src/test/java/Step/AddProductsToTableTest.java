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

import java.time.Duration;

public class AddProductsToTableTest extends BaseSeleniumTestPage {
private int tableScopeToCheck = 4;
   // WebDriverWait wait =
        //    new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10000));

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
        PageFactory.initElements(driver,
                this);
    }





    @Given("Я нахожусь на странице {string}")
    public void openPage(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(10000));
        AddProductsToTableTest addProductsToTableTest = new AddProductsToTableTest();
        BaseSeleniumTestPage.setDriver(driver);
        driver.get(url);
    }


    @Given("Кнопка Добавить кликабельна")
    public void кнопкаДобавитьКликабельна() {
       // wait.until(ExpectedConditions.elementToBeClickable(buttonAddProduct));
    }

    @Given("Нажимается кнопка Добавить")
    public void нажимаетсяКнопкаДобавить() {
        buttonAddProduct.click();
    }

    @Given("Окно Добавление товара видимо")
    public void окноДобавлениеТовараВидимо() {
      //  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body [@class=\"modal-open\"]")));
    }

    @Given("Вводится тестовое значение в поле Наименование {string}, в поле Тип {string}, в поле Экзотический {string}")
    public void вводитсяТестовоеЗначениеВПолеНаименованиеВПолеТипВПолеЭкзотический(String arg0, String arg1, String arg2) {
        inputName.sendKeys(arg0);
        if (arg1.equals("FRUIT")){
            listType_Fruit.click();
        }else listType_Vegetable.click();
        if (arg2.equals("true")){
            checkboxExotic.click();

        }
        tableScopeToCheck++;

    }

    @Given("Поле Наименование содержит значение {string}, Поле Тип содержит значение {string}, Поле Экзотический содержит значение {string}")
    public void полеНаименованиеСодержитЗначениеПолеТипСодержитЗначениеПолеЭкзотическийСодержитЗначение(String arg0, String arg1, String arg2) {
        if (!inputName.getAttribute("value").equals(arg0)) {
            Assertions.fail();
        }
        if (selectorIsFruit.getAttribute("value").equals("VEGETABLE")) {
            if (arg1.equals("FRUIT")) {
                Assertions.fail();
            }}
        else if (selectorIsFruit.getAttribute("value").equals("FRUIT")) {
            if (arg1.equals("VEGETABLE")) {
                Assertions.fail();
            }} else Assertions.fail();

        //if (checkboxExotic.getAttribute ("checked").equals(false)) {
//                    if (arg2.equals("true") {
//                        checkboxExotic.click();
//                    }
//                } else if (!isExotic) {
//                    checkboxExotic.click();
//                }
//
//                if (checkboxExotic.getAttribute("checked").equals(false)) {
//                    if (isExotic) {
//                        Assertions.fail();
//                    }
//                } else if (!isExotic) {
//                    Assertions.fail();
//                }
            //доделать не смог. Получить значение из чекбокса "Экзотический" не вышло
            //плюс весь код выцеплен из готового теста, сделанного по другой структуре
            //так что выглядит всё несолько натянуто, и не со всеми проверками
        //если бы тест переписывался с нуля, может быть, выглядело бы всё красиво
    }

    @Given("Кнопка Сохранить кликабельна")
    public void кнопкаСохранитьКликабельна() {
        //Реализация
    }

    @Given("Нажимается кнопка Сохранить")
    public void нажимаетсяКнопкаСохранить() {
        buttonSave.click();
    }

    @Given("Окно Добавление товара невидимо")
    public void окноДобавлениеТовараНевидимо() {
        //Реализация
    }

    @Given("В таблице имеется пятая строка")
    public void вТаблицеИмеетсяПятаяСтрока() {
        //Реализация
    }

    @Given("В таблице номер строки равно её отображаемому номеру")
    public void вТаблицеНомерСтрокиРавноЕёОтображаемомуНомеру() {
       //Реализация
    }

    @Given("В таблице поле Наименование равно значению {string}, поле Тип равно значению {string}, поле Экзотический равно значению {string}")
    public void вТаблицеПолеНаименованиеРавноЗначениюПолеТипРавноЗначениюПолеЭкзотическийРавноЗначению(String arg0, String arg1, String arg2) {
        String prePathForTableCheck = "//tr[" + tableScopeToCheck + "]";
        By colomnNumberPath = By.xpath(prePathForTableCheck + "/th[text()=" + tableScopeToCheck + "]");
        WebElement rowNumber = driver.findElement(colomnNumberPath);

        String rowNumberText = rowNumber.getText();

        int rowNumberInt = Integer.parseInt(rowNumberText);


        if (rowNumberInt == tableScopeToCheck) {
            By colomnNamePath = By.xpath(prePathForTableCheck + "/td[1]");
            WebElement rowName = driver.findElement(colomnNamePath);
            String rowNameText = rowName.getText();

            if (!rowNameText.equals(arg0) ) {
                Assertions.fail();
            }
        } else Assertions.fail();
        //Проверка полей таблицы # и Наименование


        By rowFruitt = By.xpath(prePathForTableCheck + "/td[2]");
        WebElement rowFruit = driver.findElement(rowFruitt);
        String rowFruitText = rowFruit.getText();
        //сам не понял что написал, но работает
        // с именем переменной тоже самое
        if (rowFruitText.equals("Фрукт") ) {
            if (!arg1.equals("FRUIT")) {
                Assertions.fail();
            }
        } else if (arg1.equals("FRUIT")){Assertions.fail();}
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
        driver.close();
        driver.quit();
    }
}
