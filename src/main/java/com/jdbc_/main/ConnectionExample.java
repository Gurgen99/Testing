package com.jdbc_.main;

import com.jdbc_.init.InitDatabase;

public class ConnectionExample {
    public static void main(String[] args) {
        Menu menu = new Menu();
        InitDatabase.initDatabase();
        while (true) {
            menu.printMainMenu();
            menu.readUserOption();
        }
    }
}
