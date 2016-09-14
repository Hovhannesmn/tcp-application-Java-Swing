package klase;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class A_Chat_Server_Return implements Runnable
{
    
    Socket SOCK;
    private Scanner INPUT;
    String MESSAGE = "";
    
    public A_Chat_Server_Return(Socket X)
    {
        this.SOCK=X;
    }
  
    public void run()
    {
        try
        {
            try
            {

                INPUT = new Scanner(SOCK.getInputStream());
                while(true)
                {
                  if(!INPUT.hasNext())
                  {
                    for(int i = 0; i < A_Chat_Server.ConnectionArray.size(); i++)
                    {
                        if(A_Chat_Server.ConnectionArray.get(i)==SOCK)
                        {
                            System.out.println("isntasfafconnected:chka connect");
                            A_Chat_Server.ConnectionArray.remove(i);
                            A_Chat_Server.CurrentUsers.remove(i);


                            for(int a = 0; a < A_Chat_Server.ConnectionArray.size(); a++)
                            {
                                Socket TEMP_SOCK = (Socket) A_Chat_Server.ConnectionArray.get(a);
                                PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
                                OUT.println("#?!" + A_Chat_Server.CurrentUsers);
                                OUT.flush();
                            }
                        }

                    }
                    return;
                }

                    MESSAGE = INPUT.nextLine();
                    A_Chat_Server_Send WRITE = new A_Chat_Server_Send(MESSAGE, SOCK );
                    Thread Y = new Thread(WRITE);
                    Y.start();

                }
            }
            finally
            {
            System.out.println("SOCKET must closed anpayman: ");
            }
        }
        catch(Exception e)
        {
       //     System.out.println(e);
        }
    }

}
