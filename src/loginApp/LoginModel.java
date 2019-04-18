package loginApp;

import dbUtil.dbConnection;

import java.sql.*;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }catch (SQLException ex){
            ex.printStackTrace();

        }
        if(this.connection==null){
            System.exit(1);
        }
    }

    public  boolean isDatabaseConnected(){
        return  this.connection !=null;
    }
    public boolean isLogin(String user,String pass,String opt)throws Exception{

        PreparedStatement pr = null;
//        PreparedStatement chek = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM login where username = ? and password = ? and division = ?";

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS login (\n" +
                "    id       INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                "    username VARCHAR,\n" +
                "    password VARCHAR (18),\n" +
                "    division VARCHAR\n" +
                ");");

//        chek = this.connection.prepareStatement("SELECT name FROM  sqlite_master WHERE type ='table' AND  name NOT LIKE 'sqlite_%';");
//        chek.execute();

        try {
            pr = this.connection.prepareStatement(sql);

            pr.setString(1,user);
            pr.setString(2,pass);
            pr.setString(3,opt);

            rs = pr.executeQuery();

            boolean bool;
            if(rs.next()){
                return true;

            }
            //todo
            return false;

        }catch (SQLException ex){
           return false;
        }
        finally {
            pr.close();
            pr.close();
        }
    }
}
