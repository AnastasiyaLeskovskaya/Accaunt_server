package com.anstasia.account.connection;

import com.anstasia.account.model.Account;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Server_Socket implements Serializable, Runnable {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ServerSocket ss;
    private Gson gs;
    private Object obj;

    private int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)

    public Server_Socket() throws IOException {
        ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
        System.out.println("Waiting for a client...");
        gs = new Gson();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = ss.accept();
                System.out.println("Got a client :) ");
                System.out.println();

                InputStream sin = client.getInputStream();
                OutputStream sout = client.getOutputStream();

                //  Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
//                System.out.println("Sleep for 3 sec...");
//                TimeUnit.SECONDS.sleep(3);
                in = new ObjectInputStream(sin);
                System.out.println("ObjectInputStream...");
                out = new ObjectOutputStream(sout);
                System.out.println("ObjectOutputStream...");

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("End of streams init.");
            System.out.println("________________________");
            Object line = null;
            boolean isConnected = true;
            while (isConnected) {
                System.out.println("Connect Closed? " + ss.isClosed());
                try {
                    line = in.readObject(); // ожидаем пока клиент пришлет строку текста.
                    System.out.println("in.readObject() : " + line);

                    switch ((String) line) {
                        // попытка отослать данные с бд на клиент весь список
                        case "generateDataObjectA":
                            System.out.println("Case line : " + line);
                            gs.toJson(DbConnection.getInstance().getAccountsArray());
                            out.writeObject((gs.toJson(DbConnection.getInstance().getAccountsArray())));
                            out.flush(); // заставляем поток закончить передачу данных.
                            System.out.println(gs.toJson(DbConnection.getInstance().getAccountsArray()));
                            System.out.println("________________________");
                            // out.writeUTF(DbConnection.getInstance().getAccountsArray().toString()); // отсылаем клиенту обратно ту самую строку текста.
                            // System.out.println("Case genDataworked");
                            break;
                        case "addNewAccount":
                            System.out.println("Case downloadData line : " + line);
                            obj = in.readObject();
                            Account account = gs.fromJson((String) obj, Account.class);
                            DbConnection.getInstance().addNewAccountDb(account);
                            // System.out.println( gs.fromJson((String) obj, Account.class));
                            //System.out.println(account.getClass());
                            System.out.println("________________________");
                            break;
                        case "deleteAccount":
                            System.out.println("Case downloadData line : " + line);
                            obj = in.readObject();
                            int idAccountForDel = gs.fromJson((String) obj, int.class);
                            DbConnection.getInstance().deleteAccount(idAccountForDel);
                            // System.out.println( gs.fromJson((String) obj, Account.class));
                            //System.out.println(account.getClass());
                            System.out.println("________________________");
                            break;

                    }

                    System.out.println("Waiting for the next line...");
                    System.out.println();
                } catch (Exception e) {
                    System.out.println("errrorrrr:" + e.toString());
                    isConnected = false;
                }
            }
        }
    }
}






