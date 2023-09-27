package com.concesionario;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.objeto.carro.Carro;

public class ServerConcesionario {
    public static void main(String[] args) {
       
        int puerto = 1148;

        System.out.println(String.format("Servidor esperando en puerto %d ...", puerto));

        try(
            ServerSocket server = new ServerSocket(puerto);
            Socket cliente = server.accept();
            PrintWriter out = new PrintWriter(cliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
        ){

            System.out.println("Cliente conectado");

            Carro carro = null;

            while(true){
                carro = (Carro) in.readObject();

                System.out.println(String.format("Recibido objeto: %s", carro.toString()));

                out.println(String.format("Se ha recibido un carro de marca %s y color %s", carro.getMarca(), carro.getColor()));

                out.flush();
            }

        }catch(EOFException eofe){
            System.out.println("Cliente ha enviado objeto null (posiblemente)");
            eofe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Cerrando servidor...");
    }
}