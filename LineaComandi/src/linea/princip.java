package linea;
import java.io.*;
import java.net.*;

public class princip 
{
    public static void main(String[] args)throws IOException, UnknownHostException
    {
        String[] argomenti = new String[2];
        argomenti[0] = "-s";
        argomenti[1] = "http://www.scuoladipoliticamaniago.it/index.html";
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
        //la forma seguente accetta un URL per il reperimento e la visualizzazione
        //di una pagina nota di un sito
//     TestGET tg = new TestGET(args[0]);   
        //la forma seguente, più complessa, permette di impostare una pagina di partenza
        //remota, preferibilmente la home page del sito e di recuperare tutti i 
        //riferimenti di tutto il sito per tentare di scaricarne il contenuto 
        //nella root directory specificata
        //TestGET tg = new TestGET();   
        //tg.setSite(args[0]);
        //tg.setRoot(args[1]);
        //tg.OttieniPagina();
        //Ulteriore evoluzione 24/11/2023 passando per la classe argos per la validazione degli argomenti
        //che, in seguito alla verifica imposta le variabili globali necessarie 
        //al funzionamento del programma
        Argos as = new Argos(args);
     System.out.println("Elaborazione completata.");
    }
    else
    {
    //inseriamo un else di test per il debug fuori dalla linea di comando
     Argos as = new Argos(argomenti);
     System.out.println("Elaborazione completata.");
    }
    
    // throws  {
    /**InetAddress ia = InetAddress.getByName("142.132.201.249");
    System.out.println(ia.getCanonicalHostName());
    */
  }
  }
  