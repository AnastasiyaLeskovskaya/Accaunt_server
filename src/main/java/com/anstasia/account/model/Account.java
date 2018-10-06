package com.anstasia.account.model;
import java.io.Serializable;

public class Account implements Serializable{

    private  String balance;
   // private Date dateToEnterBalance;
   // private String categoryOfBalance;
    private String name;
    private  int id;
    static int NEXT_ID = 0;

    public Account(String balance, String name) {
        this.balance = balance;
        this.name = name;
        this.id = NEXT_ID++ ;
    }
    public Account( int id, String balance, String name) {
        this.balance = balance;
        this.name = name;
        this.id = id;
        if (id>=NEXT_ID) NEXT_ID=++id;

    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance =  balance;
    }

    public String getName() { return name; }

    public void setNameAccount(String name) { this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

//    public String getCategoryOfBalance() { return categoryOfBalance; }
//
//    public void setCategoryOfBalance(String categoryOfBalance) { this.categoryOfBalance = categoryOfBalance; }

    public  void addBalance(int income){
        balance += income;
        //Controller.getInstance().accountGUI.accountTable.updateUI();
    }


    @Override
    public String toString() {
        return "Name: "+ name +
                " balance: " + balance +
                " id: " + id
                ;
    }
}
