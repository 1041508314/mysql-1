

import javax.print.attribute.standard.PresentationDirection;
import java.sql.*;

/**
 * @Version 1.0
 * @Author:LiuXinYu
 * @Date:2020/6/6
 * @Content:
 */
public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //注册驱动 -- 选择了乙方
        Class.forName("com.mysql.jdbc.Driver");

        /**
         * 完整目标 是执行select * from student;
         */
        //建立数据库连接
        //写明mysql服务端所在地
        //以后写项目，只需要修改默认数据库即可
        String defaultDatabaseName = "lxy";
        String password = "980911";
        //下面这里，基本不变
        String user = "root";

        String url = "jdbc:mysql://127.0.0.1:3306/" + defaultDatabaseName + "?useSSL=false&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url, user, password);
        //打印connection 对象
        System.out.println(connection);
//        queryDemo(connection);

        updateDemo(connection);
        //-1 关闭刚才的连接
        connection.close();
    }

    private static void updateDemo(Connection connection) throws SQLException {
        //获取Statement对象
        Statement statement = connection.createStatement();
        String sql = "insert into student (sn,name,sex) values ('20200605','小刘','男')";
        int affectedRows = statement.executeUpdate(sql);
        System.out.printf("Query OK, %d row affected%n",affectedRows);
        statement.close();
    }

    private static void queryDemo(Connection connection) throws SQLException {
        //要真正的执行 sql语言 并且获取数据库返回的结果
        Statement statement = connection.createStatement();

        String sql = "select * from student";//没有要求必须分号结尾
        ResultSet resultSet = statement.executeQuery(sql);

        int count = 0;
        System.out.println("+----+------+--------+-----+");
        System.out.println("| id | sn   | name   | sex |");
        System.out.println("+----+------+--------+-----+");
        while (resultSet.next()){
            String id = resultSet.getString(1);
            String sn = resultSet.getString(2);
            String name = resultSet.getString(3);
            String sex = resultSet.getString(4);
            count++;
            System.out.format("| %2s | %4s | %3s | %3s |%n",id,sn,name,sex);
        }

        System.out.println("+----+------+--------+-----+");
        System.out.format("%d rows in set",count);

        //-3 关闭resultSet对象
        resultSet.close();

        //-2 关闭statement对象
        statement.close();
    }
}
