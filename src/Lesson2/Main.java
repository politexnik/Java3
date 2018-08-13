package Lesson2;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static void main (String[] args) {
        try {
            connect();
            //создаем таблицу

/*            stmt.execute("CREATE TABLE 'products' (" +
                    "id     INTEGER      PRIMARY KEY AUTOINCREMENT," +
                    "prodid INTEGER      UNIQUE, " +
                    "title  STRING (128)," +
                    "cost   INTEGER      NOT NULL)");
*/

            connection.setAutoCommit(false);
            stmt.execute("DELETE FROM 'products' ");

            //чистим таблицу и заполняем 10000 видов товара
            pstmt = connection.prepareStatement("INSERT INTO products (id, title, cost, prodid)" +
                    "VALUES (?, ?, ?, ?);");

            for (int i = 1; i <= 10000; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2,"товар" + i);
                pstmt.setInt(3, i*10);
                pstmt.setInt(4, i);
                pstmt.execute();
            }
            connection.commit();

//            stmt.execute("SELECT cost FROM products WHERE title = 'товар545'");

            userCommandInput();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public static void connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:MyBase.db");
        stmt = connection.createStatement();
        System.out.println("База данных подключена!");
    }

    public static void disconnect()  throws SQLException {
        connection.close();
    }

    private static ResultSet showProductPrice (String productName) throws SQLException {
        String query = String.format("SELECT cost FROM products WHERE (title = '%s');" , productName);
        ResultSet rs = stmt.executeQuery( query );
        return rs;
    }

    private static void changeProductPrice (String productName, int productPrice) throws SQLException {
        String query = String.format("UPDATE products SET cost = %d WHERE (title = '%s');" , productPrice, productName);
        stmt.execute( query );
        connection.commit();
		System.out.println("Цена изменена!");
    }

    private static ResultSet showProductPriceInArea(int minPrice, int maxPrice) throws SQLException{
        String query = String.format("SELECT prodid, title, cost FROM products WHERE ((cost > %d) AND (cost < %d));" ,
                minPrice, maxPrice);
        ResultSet rs = stmt.executeQuery( query );
        if (rs.getFetchSize() == 0) {
        }
        return rs;
    }

    private static void userCommandInput() throws SQLException {
        System.out.println("Введите запрос по базе данных:");
        String userCommand = scanner.nextLine();

        //Показ цены продукта по команде "/цена товар545"
		if (userCommand.startsWith("/цена ")) {
            System.out.println("вход в if");
				userCommand.split(" ");
				ResultSet rs = showProductPrice(userCommand.split(" ")[1]);
				System.out.println( rs.getString(1) );
		}
		
		//Изменение цены продукта по команде "/сменитьцену товар10 10000"
		if (userCommand.startsWith("/сменитьцену" )) {
                String[] acceptedCommand = userCommand.split(" ");
                changeProductPrice(acceptedCommand[1], Integer.parseInt(acceptedCommand[2]));
        }
		
		//Показ продуктов по цене внутри диапазона по команде "/товарыпоцене 100 600"
        if (userCommand.startsWith("/товарыпоцене ")) {
                String[] acceptedCommand = userCommand.split(" ");
                if (Integer.parseInt(acceptedCommand[1]) < Integer.parseInt(acceptedCommand[2]))	{
                    ResultSet rs =  showProductPriceInArea(Integer.parseInt(acceptedCommand[1]) ,
                            Integer.parseInt(acceptedCommand[2]));
                    System.out.println("#   title    price");
                    while (rs.next()) {
                        System.out.printf("%d   %s    %d\n", rs.getInt(1), rs.getString(2), rs.getInt(3));
                    }

                } else {
                    System.out.println("Неверные значения цены внутри команды");
                }
        }
	}
}



