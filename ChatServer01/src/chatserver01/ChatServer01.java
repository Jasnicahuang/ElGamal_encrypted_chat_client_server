/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatserver01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nick Pagano
 */
public class ChatServer01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Socket clientSocket;
        ServerSocket server = new ServerSocket(9898);
        while(true){
            clientSocket = server.accept();
            System.out.println("a");
            ChatGUI g1 = new ChatGUI(clientSocket);
            System.out.println("b");
            Thread T1 = new Thread(g1);
            T1.start();
            /*BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String line = in.readLine();
            System.out.println(line);*/
        }

    }
    
}
