import me.jackson.pro09fruitthymeleaf.db.SqlGetter;
import me.jackson.pro09fruitthymeleaf.db.SqlSetter;

public class MainTest
{
    public static void main(String[] args) {

        SqlSetter setter = new SqlSetter();
        SqlGetter getter = new SqlGetter(setter);
        getter.addFruit("dragonfruit", 3.8, 2000.0, "first food");
        setter.closeConnection();
    }
}
