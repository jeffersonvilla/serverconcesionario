package com.concesionario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConcesionario {
    public static void main(String[] args) {
       
        int puerto = 1148;

        System.out.println(String.format("Servidor esperando en puerto %d ...", puerto));

        try(
            ServerSocket server = new ServerSocket(puerto);
            Socket cliente = server.accept();
            PrintWriter out = new PrintWriter(cliente.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        ){

            System.out.println("Cliente conectado");

            String marcaCarro = null;

            while ((marcaCarro = in.readLine()) != null){
                System.out.println(String.format("Recibido carro marca: %s", marcaCarro));
                out.println(String.format("Se ha recibido un carro de marca %s y color %s", marcaCarro, "Verde"));
                out.flush();
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        System.out.println("Cerrando servidor...");
    }
}