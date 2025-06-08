package servidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class ServidorM {
    public static void main(String[] args) {
        System.out.println("APLICACIÓN DE SERVIDOR CRIPTO");
        System.out.println("CARGANDO LISTADO DE CRIPTOMONEDAS...");
        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("localhost",2000);  //ojo el puerto
            servidor.bind(direccion);
            System.out.println("Servidor listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());
            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloEscuchador(enchufeAlCliente);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}