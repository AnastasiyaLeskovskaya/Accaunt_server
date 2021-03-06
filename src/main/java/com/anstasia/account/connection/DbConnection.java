package com.anstasia.account.connection;
import com.anstasia.account.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection implements Serializable {

    // JDBC variables for opening and managing connection
    private  Connection con;
    private  Statement stmt;
    private  ResultSet rs;
    private int idCount;

    // Реализация одиночки
    private static DbConnection Db_Instance = null;
    private DbConnection() {
    }
    public static synchronized DbConnection getInstance() {
        if (Db_Instance == null)
            Db_Instance = new DbConnection();
        return Db_Instance;
    }


    public void getConnection(String url,String user,String pass) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();
            // con.close();

        } catch (NumberFormatException e) {
            System.out.println(" Error connection");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error connection");
        }
    }

    // Вызывается в ObjectA
    public void addNewAccountDb( Account account) throws SQLException, ClassNotFoundException {
        int i = accountSize();
        String query = "insert into account.objecta(id,balance,name)" + " values( "+(i) + "," + (account.getBalance()) + ",'" + account.getName() + "')";
        stmt.executeUpdate(query);
        System.out.println(query);
    }


    public  int accountSize()throws  SQLException {
        rs = stmt.executeQuery("select max(id) from account.objecta");
        while (rs.next()) {
            idCount = rs.getInt(1)+1;
            // System.out.println(idCount);
        }
        return idCount;
    }

    //вызов CreatePopupMenu и ObjectA
    public void deleteAccount(int idAccountForDel)throws SQLException{
        String query = "delete from account.objecta where id=" + (idAccountForDel);
        stmt.executeUpdate(query);
    }
    public void deleteAllAccountsFromDb()throws SQLException{
        String query = "delete from account.objecta ";
        stmt.executeUpdate(query);
    }


    //вызов EditAccountDialog
    public  ArrayList<Account> getAccountsArray() throws ClassNotFoundException, SQLException, JsonProcessingException {
        rs = stmt.executeQuery("select * from objecta");
        ArrayList<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String balance = rs.getString("balance");
            String name = rs.getString("name");
            accounts.add(new Account(id , balance, name));
        }
        return accounts;
    }

    //вызов EditAccountDialog
    public void addNewTransactionDb( int recevierId, int amount) throws SQLException {
        rs = stmt.executeQuery("select count(id) from transactions");
        int  idNext = 0;
        while (rs.next()) {
            idNext = rs.getInt(1);
            // System.out.println("AAA"+idNext);
        }
        String query = "insert into account.transactions(id, receiverId,amount)" + " values( "+(idNext+1)+","+(recevierId) + "," + (amount) + ")";
        stmt.executeUpdate(query);
    }

    public void getAccount() throws SQLException, ClassNotFoundException {
        DbConnection.getInstance();
//        CallableStatement proc =
//                con.prepareCall("{ call getAccount() }");
//        proc.getString(1, poetName);
//        proc.setInt(2, age);
//        cs.execute();
        rs = stmt.executeQuery("select * from objecta");
        while (rs.next())
            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " +
                    rs.getString(3));
        // con.close();
    }


    // мнетод забирает коллекцию из одного листа и засовывает в другую.
    public void test() throws JsonProcessingException, SQLException, ClassNotFoundException {
        DbConnection.getInstance();
        ArrayList<Account> accounts;
        accounts = getAccountsArray();
        for (Account account : accounts) {

            String query = "insert into account.new_table(id,balance,name)" + " values( "+(account.getId()) + "," + (account.getBalance()) + ",'" + account.getName() + "')";
            stmt.executeUpdate(query);
            System.out.println(query);
        }
    }

}





