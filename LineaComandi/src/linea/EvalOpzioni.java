/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author luca
 * Questa classe valuta le opzioni passate dalla riga di comando e stabilisce il 
 * comportamento che dovrà assumere il programma di conseguenza.
 */
public class EvalOpzioni 
{
/**
* @param PARAMETRI è destinata a contenere gli argomenti elaborati di volta in volta    
*/
private final VariabiliGlobali VG = new VariabiliGlobali();

public EvalOpzioni()
{

}
    
     
 private URL sito(String sito)
 {
 /**
  * Questo metodo è al servizio di evalOpzioni e serve ad estrarre un URL dagli 
  * argomenti specificati nella riga di comando; l'URL del sito di cui si vogliono 
  * ottenere le pagine dovrà trovarsi subito dopo le opzioni di esecuzione del 
  * programma.
  */
//dalla stringa passata come argomento estraiamo un URL valido, se presente
int i;
String tmp = "";
i = sito.indexOf("http");
System.out.println("Valore di i " + i);
while((sito.charAt(i) != ' ') & (sito.charAt(i) != '\n'))
    {
        tmp = tmp + sito.charAt(i);
        i++;
    }
sito = tmp;     
URL sito_da_lavorare;     
try
    {
        sito_da_lavorare = new URL(sito);
    }
catch(MalformedURLException mux)
    {
        sito_da_lavorare=null;
        System.err.printf("Eccezione nel metodo 'sito' della classe Argos: %s%n ", mux);
    }

return sito_da_lavorare;
 }
 
 private String directory(String dir)
 {
 /**
  * Anche questo è un metodo di servizio che estrae una directory dai parametry
  * passati come argomento.
  * Sarà utile per l'impostazione delle variabili globali.
  */
     String tmp = "";
     String root_directory;
     int i;
    i = dir.indexOf("http");
    //ripetiamo il ciclo del metodo sito per escludere, questa volta,
    //il primo parametro contenente il sito da analizzare ed estrappolare il 
    //secondoparametro contenente la root directory da cui iniziare 
    //l'archiviazione dei dati
while((dir.charAt(i) != ' ') & (dir.charAt(i) != '\n'))
    {
        i++;
    }

i++;//ci spostiamo di una posizione per saltare il primo carattere di spazio

//A questo punto ci troviamo sullo spazio tra il primo parametro e il secondo
//Iniziamo a scrivere il valore della root nella variabile tmp e lo passiamo 
//poi a root_directory
while((dir.charAt(i) != ' ') & (dir.charAt(i) != '\n'))
    {
        tmp = tmp + dir.charAt(i);
        i++;
    }
root_directory = tmp;

return root_directory;
 }

 public void evalOpz(int opzioni)throws IOException
 {
String saluto;     
 switch (opzioni)
    {
     /**
      * Questo caso fa uscire dalla procedura nel caso in cui non ci siano 
      * opzioni valide
      */
     case 0:
         break;
     /**
      * Questo caso ci aiuta a capire se il primo carattere delle opzioni è un trattino 
      * alto 45.
      */
     case 45:
         System.out.printf("Non è stata specificata alcuna opzione valida!%n");
         break;
         /**
          * Questo caso valuta se si è scelto di visualizzare le istruzioni del 
          * programma. Carattere 'h' 104
          */
     case 149:
         System.out.printf("É stata scelta l'opzione '%s'%n", VG.get_opzioni());
         TermInputStream is = new TermInputStream("/home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/Maschera.txt");
        int lunghezzaFile = is.getLength();
        System.out.printf("La lunghezza del file selezionato è; %d byte\n", lunghezzaFile);
        is.mostraFile();
        Console cs = System.console();
        saluto = cs.readLine("\n");
        cs.printf("Dal tuo input è stato letto: %s\n", saluto);
         break;
     /**
      * Questo caso valuta se si è scelto di visualizzare le informazioni di base
      * di un sito, passato senza parametri del sito da analizzare dovrà fornire le
      * informazioni di un sito di default. Carattere 'i' 105
      */   
     case 150:
         //a questo punto bisogna controllare la presenza di argomenti e passarli 
         //alla procedura che fornisce le informazioni le prossime righe sono di 
         //test, da modificare
         InfoNet in = new InfoNet(VG.get_args());
         /**
          * I risultati del controllo delle interfacce locali vengono sempre restituiti tra le informazioni
          * per evitarlo commentare le seguenti quattro righe di codice
          */

         saluto = in.localInfos();
         System.out.printf("%nRisultato della ricerca di interfacce locali:%n %s%n", saluto);
         saluto = in.mieInfos();
         System.out.printf("%nRisultato della ricerca del mio indirizzo host: %s%n", saluto);

         saluto = in.genericInfos();
         System.out.printf("Risultato dell'elaborazione %s%n", saluto);
         break;
         /**
          * Questo caso valuta se si è scelto di scaricare un sito con tutte le 
          * sue pagine. Carattere 's' 115
          */
     case 160:
         //Per l'opzione '-s' deve venire fornito un sito valido e una root directory
        //che andrà validata anch'essa
        //impostiamo le variabili globali
                
        VG.set_sito(sito(VG.get_args()));
        VG.set_root(directory(VG.get_args()));
        VG.set_files();
        /*System.out.printf("É stata scelta l'opzione 's', verrà scaricato il "
                + "sito:%n%s%nNella directory:%n"
                + "%s%ne scritti i "
                + "file:%n%s%n%s%n",VG.get_sito().toString(),VG.get_root(), VG.get_file_URLS().toString(), VG.get_file_DIRS().toString());*/
        //a questo punto inizia la successione di comandi per l'esecuzione del programma
        TestGET tg = new TestGET(true);
        break;
     
    }
 }
    
}
