package org.h2.test.mytest;

import java.sql.*;

/**
 * Created by liufq on 7/17/18.
 */
public class TestDb {
    public static Connection createDb() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        //Class.forName("org.h2.Driver");
        String url = "jdbc:h2:/Users/liufq/Documents/h2/h2";
        //conn = DriverManager.getConnection(url, "sa", "sa");
        org.h2.Driver.load();
        // url += ";DEFAULT_TABLE_TYPE=1";
        // Class.forName("org.hsqldb.jdbcDriver");
        // return DriverManager.getConnection("jdbc:hsqldb:" + name, "sa", "");
        return DriverManager.getConnection(url, "sa", "sa");
    }

    public static void createTable(Connection conn) throws SQLException {
        Statement stmt = null;
        String sql = "create table t_user(id int,name varchar(20));";
        stmt = conn.createStatement();
        boolean flag = stmt.execute(sql);//建表成功返回的是false
        System.out.println(flag);
    }

    public static void insert(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String sql = "insert into t_user values(1,'x')";
        int count = stmt.executeUpdate(sql);
        System.out.println(count);
    }

    public static void select(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        String sql = "select * from t_user";
        rs = stmt.executeQuery(sql);
        while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("id: " + id + ",name: " + name);
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = createDb();
            createTable(conn);
            insert(conn);
            select(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
