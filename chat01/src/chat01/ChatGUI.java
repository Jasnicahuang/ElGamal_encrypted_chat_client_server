/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nick Pagano
 */
public class ChatGUI extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form ChatGUI
     */
    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private BigInteger q;
    private BigInteger a;
    private BigInteger xa;
    private BigInteger ya;
    private BigInteger yb;
    
    public ChatGUI(Socket socket) throws IOException {
        initComponents();
        
        this.a = new BigInteger("19");  //radice primitiva di p
        this.q = new BigInteger("191"); //numero primo
        this.xa = new BigInteger("165"); //chiave privata
        this.ya = a.modPow(xa, q); //chiave pubblica
        
        this.socket = socket;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintStream(socket.getOutputStream(), true);
        this.yb = new BigInteger("142");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ChatWindow = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        InputWindow = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ChatWindow.setColumns(20);
        ChatWindow.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        ChatWindow.setRows(5);
        jScrollPane1.setViewportView(ChatWindow);

        InputWindow.setColumns(20);
        InputWindow.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        InputWindow.setRows(5);
        jScrollPane2.setViewportView(InputWindow);

        jButton1.setText("Invia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("A (Client)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String message, aux;
        int m, temp, C2;
        char c;
        char[] cv;
        BigInteger C1, k, K, M, Kinv;
        
        
        
        /*BigInteger xb = new BigInteger("57"); //chiave privata
        BigInteger yb; //chiave pubblica
        yb = a.modPow(xb, q);*/
        
        
        message = InputWindow.getText();
        out.println("new_message");
        out.flush();
        out.println(message.length());
        System.out.println("lenght: " + message.length());
        out.flush();
        int[] buffer = new int[2*message.length()];
        
        for(int i = 0; i < message.length(); i++){
            c = message.charAt(i);
            
            aux = String.valueOf( (int)c );
            M = new BigInteger(aux);
            System.out.println(M);
            
            //generazione di k
            temp =(int)( 1 + Math.random()*(191-2) ); 
            aux = String.valueOf(temp);
            k = new BigInteger(aux);
            
            //calcolo chiave di sessione
            K = yb.modPow(k, q);
            C1 = a.modPow(k, q);
            
            //invio di C1
            out.println(C1.intValue());
            System.out.println("C1: " + C1.intValue());
            
            //calcolo di C2 ed invio
            C2 = (K.intValue()*M.intValue())%q.intValue();
            out.println(C2);
            System.out.println("C2: " + C2);
            
            //K = C1.modPow(xb, q);
            //Kinv = K.modInverse(q);
            //m = (Kinv.intValue()*C2)%q.intValue();
            //aux = String.valueOf(m);
            //char ch = (char)m;
            //System.out.println(m);
            //System.out.println(String.valueOf((char)(m)));
        }
        
        out.flush();
        this.ChatWindow.append("A: " + message + "\n");
        InputWindow.setText(null);
        //System.out.println("flush " + s);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatGUI().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ChatWindow;
    private javax.swing.JTextArea InputWindow;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //java.awt.EventQueue.invokeLater(new Runnable() {
        
                 ChatGUI gc;
                 int C1, C2, y, i, dim;
                 String s;
                 int[] buffer;
                 char c;
        try {
            gc = new ChatGUI(socket);
            gc.setVisible(true);
            /*gc.in = new BufferedReader(
                new InputStreamReader(gc.socket.getInputStream()));
        this.out = new PrintWriter(gc.socket.getOutputStream(), true);*/
            
        
                
            
                while (true) {
                    s = gc.in.readLine();
                    if(s.compareTo("new_message") == 0){
                        System.out.println(s);
                        //dim = gc.in.read() - 48;
                        dim = Integer.parseInt(gc.in.readLine());
                        System.out.println(dim);
                        buffer = new int[2*dim];
                        i = 0;
                        while(i < 2*dim){
                            C1 = Integer.parseInt(gc.in.readLine());
                            System.out.println("C1: " + C1);
                            C2 = Integer.parseInt(gc.in.readLine());
                            System.out.println("C2: " + C2);
                            buffer[i] = C1;
                            i++;
                            buffer[i] = C2;
                            i++;
                            System.out.println(i);
                        }
                        System.out.println("buffer riempito");
                        showCipherText(buffer, 2*dim, gc.ChatWindow);
                        gc.ChatWindow.append("B: " + getPlainText(buffer, 2*dim) + "\n");
                        s = null;
                    } 
                }
        }
    catch (IOException ex) {
                         Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
                     }
    }
    
    public char decrypt(int C1, int C2){
            BigInteger b;
            String aux = String.valueOf( C1 );
            b = new BigInteger(aux);
            BigInteger K = b.modPow(this.xa, this.q);
            BigInteger Kinv = K.modInverse(this.q);
            int m = (Kinv.intValue()*C2)%this.q.intValue();
            System.out.println("m:" + m);
            return (char)m;
    }
    
    public String getPlainText(int[] buffer, int dim){
        int s_dim = dim/2;
        int j = 0;
        char[] c = new char[s_dim];
        for(int i = 0; i < dim; i = i + 2){
            c[j] = decrypt(buffer[i], buffer[i+1]);
            j++;
        }
        System.out.println("Decryption complete: " + String.valueOf(c));
        return String.valueOf(c);
    }
    
    public void showCipherText(int buffer[], int dim, javax.swing.JTextArea ChatWindow){
        ChatWindow.append("CipherText: ");
        for(int i = 0; i < dim; i++){
            ChatWindow.append(buffer[i] + " ");
        }
        ChatWindow.append("\n");
    }
                

}
