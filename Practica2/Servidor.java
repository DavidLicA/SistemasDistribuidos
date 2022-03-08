package ejercicio_sockets_ddr_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {        

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        int tarjeta;
        int cvv;
        int cantidad;

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor Bancario iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();

                System.out.println(mensaje);
                

                //Le envio un mensaje
                out.writeUTF("Atendiendo. Indique detalles del pago\n Numero de Tarjeta\n");
                
                //Leo el mensaje que me envia
                mensaje = in.readUTF();
                System.out.println(mensaje);
                tarjeta=Integer.parseInt(mensaje);
                //Le envio un mensaje
                out.writeUTF("CVV:");
                //Leo el mensaje que me envia
                mensaje = in.readUTF();
                System.out.println(mensaje);
                cvv=Integer.parseInt(mensaje);
                //Le envio un mensaje
                out.writeUTF("Cantidad a pagar:");
                //Leo el mensaje que me envia
                mensaje = in.readUTF();
                System.out.println(mensaje);
                cantidad=Integer.parseInt(mensaje);
                
                pago(cantidad, tarjeta, cvv);
                
                

                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static int pago(int cantidad_a_pagar, int tarjeta, int cvv){
        
        int z;
        
        if (tarjeta!=0){
            if(cvv!=0){
                System.out.println("Transaccion Exitosa\n");
                System.out.println("Cantidad pagada: " + cantidad_a_pagar);
                return 1;
                
            }
            else{
                System.out.println("CVV incorrecto");
                return 0;
            }
        }
        else{
            System.out.println("Tarjeta erronea");
            return 0;}
    }
}
