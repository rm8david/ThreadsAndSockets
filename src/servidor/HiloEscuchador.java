package servidor;

import model.Cripto;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;
    private ArrayList<Cripto>listado = new ArrayList<>();
    public HiloEscuchador(Socket cliente) {
        this.listado = cargarFichero();
        numCliente++;
        hilo = new Thread(this, "Cliente" + numCliente);
        this.enchufeAlCliente = cliente;
        hilo.start();

    }

    private ArrayList<Cripto> cargarFichero() {
        File file = new File("src/resources/criptomonedas.dat");
        ArrayList<Cripto> criptos = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while(true){
                criptos.add((Cripto)objectInputStream.readObject());
            }
        }catch (EOFException e){
            // fin del fichero alcanzado
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "clase con encontrada");
        }
        return criptos;
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();

            String texto = "";
            while (!texto.trim().equals("FIN")) {
                byte[] mensaje = new byte[100];
                entrada.read(mensaje);
                texto = new String(mensaje).trim();  //  AÑADE .trim();
                if (texto.trim().equals("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName()+" ha cerrado la comunicación");
                } else {
                    System.out.println(hilo.getName() + " solicita: " + texto);
                    String textoBuscar = texto.trim().toLowerCase();
                    Cripto cripto = buscarCripto(textoBuscar);
                    if (cripto != null) {
                        salida.write("OK".getBytes());
                        salida.flush();
                        objectOutputStream.writeObject(cripto);
                    }else {
                        salida.write("No se encuentra. Prueba con bitcoin, ethereum, solana, tether, xrp, bnb o dogecoin".getBytes());
                        salida.flush();
                    }
                }
            }
            objectOutputStream.close();
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private Cripto buscarCripto(String nombre){
        for (Cripto cripto: listado){
            if (cripto.getNombre().equals(nombre)){
                return cripto;
            }
        }return null;
    }
}
