package com.anstasia.account.main;

import com.anstasia.account.connection.DbConnection;
import com.anstasia.account.connection.Server_Socket;

import java.io.IOException;
import java.sql.SQLException;



public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("hello Anastasiya!");

        // Соединились с бд
        DbConnection.getInstance().getConnection(args[0],args[1],args[2]);

      //  DbConnection.getInstance().test();
        Server_Socket ss = new Server_Socket();
        Thread t1 = new Thread(ss);
        t1.start();
        }
}

