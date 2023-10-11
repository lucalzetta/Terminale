package linea;
import java.io.*;
import java.net.*;

public class princip 
{
    public static void main(String[] args)throws IOException
    {
            /**
             * Codice di test per il debug dell'applicazione
             */
//            System.out.println("Hello world!");
//            Argos ag = new Argos(args);
            /**
             * Inseriamo del codice per testare un URLConnection
             * Questo tratto è destinato ad una classe specifica per ottenere
             * le pagine grezze dai siti
             */
            
    if  (args.length > 0) 
    {
     TestGET tg = new TestGET(args[0]);   
    }
  }
  }
