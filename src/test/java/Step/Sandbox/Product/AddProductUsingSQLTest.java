package Step.Sandbox.Product;

import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class AddProductUsingSQLTest {

    int foodId;
    String foodName = null;
    String foodType = null;
    boolean foodExotic = true;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    @Step
    @Given("Я авторизируюсь на стенде через SQL")
    public void яАвторизируюсьНаСтендеЧерезSQL() throws SQLException {
        connection
                = DriverManager.getConnection("jdbc:h2:tcp://149.154.71.152:9092/mem:testdb", "user", "pass");

        statement = connection.createStatement();

    }
    @Step
    @Given("Я ввожу тестовые данные и отправляю их на сервер")
    public void яВвожуТестовыеДанныеИОтправляюИхНаСервер() throws SQLException {
        String testData = "use PUBLIC;\n" +
                "\n" +
                "insert into FOOD values (NULL,'Мандаринка', 'FRUIT', 1);";
        statement.executeUpdate(testData);
        resultSet = statement.executeQuery("Select FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC from FOOD");

    }

    @Given("Я сравниваю тестовые данные с содержащимися в базе, и в случае успеха, удаляю их")
    public void яСравниваюТестовыеДанныеССодержащимисяВБазеИВСлучаеУспехаУдаляюИх() throws SQLException {
        while (resultSet.next()) {
            foodId = resultSet.getInt("FOOD_ID");
            foodName = resultSet.getString("FOOD_NAME");
            foodType = resultSet.getString("FOOD_TYPE");
            foodExotic = resultSet.getBoolean("FOOD_EXOTIC");

            System.out.printf("%d, %s, %s, %b%n", foodId, foodName, foodType, foodExotic);
        }
        if (foodName.equals("Мандаринка"))
            if (foodType.equals("FRUIT"))
                if (foodExotic) {
                    //проверка прошла успешно
                    statement.executeUpdate("delete from food\n" +
                            "\n" +
                            "where food_name = 'Мандаринка';");
                } else Assertions.fail();

    }
}

