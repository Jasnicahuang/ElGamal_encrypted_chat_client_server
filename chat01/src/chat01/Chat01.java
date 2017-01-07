/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Nick Pagano
 */
public class Chat01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Socket socket = new Socket("localhost", 9898);
        /*BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);*/
        ChatGUI g1 = new ChatGUI(socket);
        Thread T1 = new Thread(g1);
        T1.start();
        
    }
    
}
