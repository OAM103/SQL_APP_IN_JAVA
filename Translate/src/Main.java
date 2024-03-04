import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Установка соединения с базой данных
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/database", "user", "password");

        // Создание Statement
        Statement stmt = conn.createStatement();

        // Выполнение SQL запроса
        ResultSet rs = stmt.executeQuery("SELECT * FROM table");

        // Обработка результатов
        while (rs.next()) {
            // Получение значений столбцов
            int id = rs.getInt("id");
            String name = rs.getString("name");

            // Вывод значений
            System.out.println("ID: " + id + ", Name: " + name);
        }

        // Закрытие соединения с базой данных
        conn.close();
    }
}
