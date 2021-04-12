import java.sql.*;

public class jdbcTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/employees",
                    "postgres",
                    ""
            );

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "select department, sum(salary) from employees group by department"
            );
            int columnsCount = result.getMetaData().getColumnCount();

            while (result.next()) {
                for (int i = 1; i <= columnsCount; ++i) {
                    if (i == columnsCount) {
                        System.out.print(result.getString(i));
                    } else {
                        System.out.print(result.getString(i) + ":" + "\t\t");
                    }
                }
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
