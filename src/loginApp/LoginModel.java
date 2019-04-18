package loginApp;

import dbUtil.dbConnection;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.lang.annotation.Documented;
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
        PreparedStatement firstAdminUser = null;
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

            } else {

                //This statement is for First time starting the  Application and Create a Admin user in the Data Base using the User Input in Login form!
                firstAdminUser = this.connection.prepareStatement("select * from login where division like '%min'"); //SQL select to check if any Admin user the Application have?

                if(!firstAdminUser.executeQuery().next()){  // if no user with role Admin found then it create one whit user and password from Login dialog Box.
                    firstAdminUser = this.connection.prepareStatement("INSERT INTO login(username,password,division) VALUES(?,?,'Admin')");
                    firstAdminUser.setString(1,user);
                    firstAdminUser.setString(2,pass);


                   firstAdminUser.execute();
                    JOptionPane.showMessageDialog(null,"New User Was Created ","User Manager",JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }

            //todo
            return false;

        }catch (SQLException ex){
           return false;
        }
        finally {
            pr.close();
            pr.close();
            if(firstAdminUser!= null) {
                firstAdminUser.close();
            }
        }
    }
}
