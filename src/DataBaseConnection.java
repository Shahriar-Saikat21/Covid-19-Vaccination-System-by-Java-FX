import java.sql.*;

public class DataBaseConnection {
    static Connection DBConnection = null;
    
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://u4z248jyae48dg2q:UGShA2LNYyd9kARxUjh4@bpuo3ezcmm7god7qlfp1-mysql.services.clever-cloud.com:3306/bpuo3ezcmm7god7qlfp1";
            String user = "u4z248jyae48dg2q";
            String password = "UGShA2LNYyd9kARxUjh4";
            Connection DBConnection = DriverManager.getConnection(url,user,password);
            return DBConnection;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }   
}
