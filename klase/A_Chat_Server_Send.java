package klase;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class A_Chat_Server_Send implements Runnable{
    
    private String MESSAGE;
    private PrintWriter OUT;
    private  Socket SOCK;

    
    A_Chat_Server_Send(String str, Socket s ){
        this.MESSAGE = str;
        this.SOCK = s;

    }   

    public void run(){

     try{
      int a  = MESSAGE.indexOf( ':' );
         String TEMP1 = MESSAGE.substring(0, a);// I mast split string :
         System.err.println("destringe:  " + TEMP1 );
         OUT = new PrintWriter(SOCK.getOutputStream());
         String name = ""; 
         for(int i = 0; i <A_Chat_Server.ConnectionArray.size(); i++){
          if(A_Chat_Server.ConnectionArray.get(i)==SOCK){
            name = A_Chat_Server.CurrentUsers.get(i);
          }
          else{
            System.out.println("dont have this client");
          }
        }      
        for(int i = 0; i <A_Chat_Server.ConnectionArray.size(); i++){
          if(TEMP1.equals(A_Chat_Server.CurrentUsers.get(i))){ 
            Socket TEMP_SOCK = (Socket) A_Chat_Server.ConnectionArray.get(i);
            PrintWriter TEMP_OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            String SENDMESSAGE =  MESSAGE.substring(a+1);
            String SENDMESSAGE1 =name + ":" +  SENDMESSAGE;
            TEMP_OUT.println(SENDMESSAGE1);
            System.err.println("urish mesige:  " + SENDMESSAGE1 );
            TEMP_OUT.flush();
            System.out.println("Sent to: " + TEMP_SOCK.getLocalAddress().getHostName());
            if(!TEMP1.equals(name) ) {        
             for(int k = 0; k <A_Chat_Server.ConnectionArray.size(); k++){
              if(name.equals(A_Chat_Server.CurrentUsers.get(k))){ 
                Socket TEMP_SOCK1 = (Socket) A_Chat_Server.ConnectionArray.get(k);
                PrintWriter TEMP_OUT1 = new PrintWriter(TEMP_SOCK1.getOutputStream());
                String SENDMESSAGE2 = name +  " write to " + TEMP1 + ":" + SENDMESSAGE;
                TEMP_OUT1.println(SENDMESSAGE2);
                TEMP_OUT1.flush();
              }
            } 
          }

          break;
        }
        else{

          System.out.println("I can't sand");
        }

      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}      
