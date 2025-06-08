package cliente;

import model.Cripto;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSSMulti {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");
        Scanner lector = new Scanner(System.in);
        ObjectInputStream objectInputStream = null;
        Cripto cripto;
        try {
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2000);
            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");
            InputStream entrada = cliente.getInputStream();
            OutputStream salida = cliente.getOutputStream();
            String texto = "";
            objectInputStream = new ObjectInputStream(entrada);
            while (!texto.equals("FIN")) {
                System.out.println("Consulta una criptomoneda (FIN para terminar): ");
                texto = lector.next();
                salida.write(texto.getBytes());
                salida.flush();
                byte[] mensaje = new byte[100];
                System.out.println("Esperando respuesta ...... ");
                entrada.read(mensaje);
                String respuesta = new String(mensaje).trim();
                if (respuesta.equals("OK")) {
                    cripto = (Cripto)objectInputStream.readObject();
                    System.out.println(cripto);
                }else {
                    System.out.println(respuesta);
                }
            }

            objectInputStream.close();
            entrada.close();
            salida.close();
            cliente.close();
            System.out.println("Comunicación cerrada");
        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada");
            System.out.println(e.getMessage());
        }
    }
}
