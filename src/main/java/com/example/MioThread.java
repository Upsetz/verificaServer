package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class MioThread extends Thread
{

    Socket s;

    public MioThread(Socket s)
    {
        this.s = s;
    }

    public void run()
    {

        ArrayList <String> array = new ArrayList<>();
        ArrayList <String> lettere = new ArrayList<>();
        array.add("ciao");
        array.add("cacio");
        array.add("piernoel");
        array.add("zanna");
        array.add("orwell");
        array.add("bianca");

        int randomNum = (int)(Math.random() * 6);
        String parola = array.get(randomNum);
        

        try 
        {
            System.out.println("Un client si è collegato");
            
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream())); //istanza per ricevere dati dal client
            DataOutputStream output = new DataOutputStream(s.getOutputStream()); //istanza per inviare dati al client

            String letPar = "";
            int lettereIndovinate = 0;
            int tentativi = 0;
            do 
            {   
                letPar = input.readLine();
                System.out.println("lettra/parola:" + letPar );

                if(letPar.length() == 1)
                {
                    
                    output.writeBytes("1");
                    int num = Integer.parseInt(input.readLine());
                    
                    if(num == 1){
                        
                        String lettera = input.readLine();
                        String parolaconAsterischi = null;

                        if(parola.contains(lettera)){

                            for(int i = 0; i < parola.length(); i++){

                                

                                if(parola.charAt(i) != lettera.charAt(0)){

                                    parolaconAsterischi = parolaconAsterischi + "*";

                                }
                                else{

                                    parolaconAsterischi = parolaconAsterischi + lettera;
                                }
                            }

                            output.writeBytes(parolaconAsterischi);
                        }
                        else{

                            output.writeBytes(String.valueOf(2));
                        }
                        

                    }

                    else{

                        System.out.println("lettera sbagliata");
                        tentativi ++;

                    }
                    
                
                }
            } while (letPar == "esci");
            s.close(); //chiude socket
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}