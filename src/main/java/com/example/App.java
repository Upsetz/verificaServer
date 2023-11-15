package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main(String[] args) 
    {
        try 
        {
            System.out.println("Server in avvio!");
            ServerSocket server = new ServerSocket(3000); //crea socket su cui ricevere
            do 
            {
                Socket s = server.accept(); //accetta connessione
                MioThread m = new MioThread(s); //crea thread
                m.start(); //start processo
            } 
            while (true);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}

