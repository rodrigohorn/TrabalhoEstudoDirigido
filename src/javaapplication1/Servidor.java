/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.sun.corba.se.spi.activation.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Rodrigo
 */
public class Servidor implements Runnable{
    public Socket cliente;

    public Servidor(Socket cliente){
        this.cliente = cliente;
    }

    public static void main(String[] args) throws IOException {     

        //Cria um socket na porta 12345
        ServerSocket servidor = new ServerSocket (12345);
        System.out.println("Porta 12345 aberta!");

        System.out.println("Aguardando conexão do cliente...");   

        while (true) {
          Socket cliente = servidor.accept();
          // Cria uma thread do servidor para tratar a conexão
          Servidor tratamento = new Servidor(cliente);
          Thread t = new Thread((Runnable) tratamento);
          // Inicia a thread para o cliente conectado
          t.start();
        }
    }

    
    public void run(){
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try {
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());

            //Exibe mensagem no console
            while(s.hasNextLine()){
                System.out.println(s.nextLine());
            }

            //Finaliza objetos
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
