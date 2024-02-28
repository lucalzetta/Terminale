/*
 * Click nbfs://nbhost///SystemFile//System/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost///SystemFile//System/Templates/Classes/Class.java to edit this template
 */
package linea;

import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

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
private static CheckerVariabili CV = new CheckerVariabili();
private Set <String> HOME_LINK;

public EvalOpzioni()
{
////System.out.printf("%nCLASSE EvalOpzioni, costruttore di default.%n");
HOME_LINK = VG.get_set_collegamenti();    
}
    
     
 private URL sito(String sito)
 {
//System.out.printf("%nCLASSE EvalOpzioni, metodo sito().%n");     
 /**
  * Questo metodo è al servizio di evalOpzioni e serve ad estrarre un URL dagli 
  * argomenti specificati nella riga di comando; l'URL del sito di cui si vogliono 
  * ottenere le pagine dovrà trovarsi subito dopo le opzioni di esecuzione del 
  * programma.
  * La home page del sito viene aggiunta qui alla lista dei collegamenti del sito
  * perchè un sito con una sola pagina possa avere il riferimento almeno a 
  * quella stessa pagina.
  */
//dalla stringa passata come argomento estraiamo un URL valido, se presente
int i;
String tmp = "";
i = sito.indexOf("http");
////System.out.println("Valore di indice per http " + i);
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
        //System.err.printf("Eccezione nel metodo 'sito' della classe EvalOpzioni: %s%n ", mux);
    }

return sito_da_lavorare;
 }
 private URL sito_ridotto(String sito)
 {
//System.out.printf("%nCLASSE EvalOpzioni, metodo sito_ridotto().%n");     
 /**
  * Questo metodo è al servizio di evalOpzioni e serve ad estrarre un URL dagli 
  * argomenti specificati nella riga di comando.
  * A differenza del metodo sito() restituisce solo la radice del sito senza 
  * sottodirectory ne nomi di pagina, serve per l'impostazione della variabile
  * globale SITO_RIDOTTO
  */
//dalla stringa passata come argomento estraiamo un URL valido, se presente
int i;
int stop;
int start;
String nome_base = "";
String tmp = "";
i = sito.indexOf("http");
////System.out.println("Valore di indice per http " + i);
while((sito.charAt(i) != ' ') & (sito.charAt(i) != '\n'))
    {
        tmp = tmp + sito.charAt(i);
        i++;
    }
sito = tmp;
stop= tmp.lastIndexOf("/");
start = 0;
while(start < stop)
    {
        nome_base = nome_base + tmp.charAt(start);
        start++;
    }
URL sito_da_lavorare;
try
    {
        sito_da_lavorare = new URL(nome_base);
        
    }
catch(MalformedURLException mux)
    {
        sito_da_lavorare=null;
        //System.err.printf("Eccezione nel metodo 'sito_ridotto()' della classe EvalOpzioni: %s%n ", mux);
    }

return sito_da_lavorare;
 }
 
 private String directory(String dir)
 {
//System.out.printf("%nCLASSE EvalOpzioni, metodo directory().%n");     
 /**
  * Anche questo è un metodo di servizio che estrae una directory dai parametri
  * passati come argomento.
  * Sarà utile per l'impostazione delle variabili globali.
  */
     String tmp = "";
     String root_directory = "";
     int i = 0;
    i = dir.indexOf("http");
    if (i == -1)
        {
            //se fallisce la ricerca di http impostiamo la root directory ad un valore
            //predefinito
            root_directory = "httpdocs";
        }
    else
        {
            //SE la ricerca di http dà esito positivo dobbiamo raggiungere
            //la fine della stringa del protocollo ed estrarre la cartella 
            //radice se presente, altrimenti la root assumerà il nome della
            //pagina principale.
            boolean check = false;
            while( !check)
                {
                    //valutiamo una serie di possibilità 
                    //di composizione dell'URL
                    if (dir.indexOf("http://www.")!= -1)
                        {
                            i = i + 11;
                            check = true;
                            break;
                        }
                    else if(dir.indexOf("https://www.")!= -1)
                        {
                            i = i + 12;
                            check = true;
                            break;
                        }
                    else if(dir.indexOf("https://")!= -1)
                        {
                            i = i + 8;
                            check = true;
                            break;
                        }
                    else if(dir.indexOf("http://")!= -1)
                        {
                            i = i + 7;
                            check = true;
                            break;
                        }
                    else if(dir.indexOf("www.")!= -1)
                        {
                            i = i + 4;
                            check = true;
                            break;
                        }
                    else
                        {
                            i = 0;
                            check = true;
                            break;
                        }                   
                }
        }
    while((dir.charAt(i) != ' ') & (dir.charAt(i) != '/')& (dir.charAt(i) != '.')& (dir.charAt(i) != '\n'))
    {
        root_directory = root_directory + dir.charAt(i);
        i++;
    }

return root_directory;
 }
 
 private String file(String fl)
 {
//System.out.printf("%nCLASSE EvalOpzioni, metodo file().%n");
 String f = fl;
 String tmp = "";
 int i = 0;
 int fine = f.length();
 i = f.lastIndexOf("/");
 i++;
 while (i < fine)
    {
        tmp = tmp + f.charAt(i);
        i ++;
    }
 f = tmp;
 return f;
 }

 public void evalOpz(int opzioni)throws IOException
 {
//System.out.printf("%nCLASSE EvalOpzioni, metodo evalOpz().%n");
String saluto = "";     
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
         //System.out.printf("Non è stata specificata alcuna opzione valida!%n");
         break;
         /**
          * Questo caso valuta se si è scelto di visualizzare le istruzioni del 
          * programma. Carattere 'h' 104
          */
     case 149:
         //System.out.printf("É stata scelta l'opzione '%s'%n", VG.get_opzioni());
         TermInputStream is = new TermInputStream("/home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/Maschera.txt");
        int lunghezzaFile = is.getLength();
        //System.out.printf("La lunghezza del file selezionato è; %d byte\n", lunghezzaFile);
        is.mostraFile();
        Console cs = System.console();
        //saluto = cs.readLine("\n");
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
         //System.out.printf("%nRisultato della ricerca di interfacce locali:%n %s%n", saluto);
         saluto = in.mieInfos();
         //System.out.printf("%nRisultato della ricerca del mio indirizzo host: %s%n", saluto);

         saluto = in.genericInfos();
         //System.out.printf("Risultato dell'elaborazione %s%n", saluto);
         break;
         /**
          * Questo caso valuta se si è scelto di scaricare un sito con tutte le 
          * sue pagine. Carattere 's' 115
          */
     case 160:
         //Per l'opzione '-s' deve venire fornito un sito valido e una root directory
        //che andrà validata anch'essa
        //impostiamo le variabili globali
         String root = VG.get_destinazione_files_sito();
         //System.out.printf("%n&&&&&&&&&&&&&&&&&&&&&& EvalOpzioni &&&&&&&&&&&&&&&&&&&&&&%n");
        //System.out.printf("Valore della variabile VG.get_args() in EvalOpzioni.EvalOpz() %s%n", VG.get_args());
        
        VG.set_sito(sito(VG.get_args()));
        VG.set_sito_ridotto(sito_ridotto(VG.get_args()));
        //System.out.printf("Valore della variabile sito in EvalOpzioni.EvalOpz() %s%n", sito(VG.get_args()));
        ////CV.get_URL_SITO();
        VG.set_root(VG.get_destinazione_files_sito() + directory(VG.get_args()));
        //CV.get_ROOT_D();
        //CV.get_ROOT_DEST();
        VG.set_cartella_sito(VG.get_root());
        VG.set_files();//aL MOMENTO INUTILE 17/02/2024
        VG.set_name_page(file(VG.get_args()));
        
        if (VG.get_subdir() == null)
            {
                VG.set_subdir("");
            }
        
        HOME_LINK.add(VG.get_name_page());
        //System.out.printf("EvalOpzioni.EvalOpz() aggiunta la pagina %s al set dei collegamenti.%n",(VG.get_name_page()));
        
        //CV.get_NOME_PAGINA();
        //System.out.printf("Valore della variabile root directory in EvalOpzioni.EvalOpz() %s%n", directory(VG.get_args()));
        System.out.printf("É stata scelta l'opzione 's', verrà scaricato il "
                + "sito:%n%s%nNella directory:%n"
                + "%s%ne scritti i "
                + "file:%n%s%n%s%n"
                + "scaricata la pagina: %s%n",VG.get_sito().toString(),VG.get_root(), 
                VG.get_file_URLS().toString(), VG.get_file_DIRS().toString(),
                VG.get_name_page());
        //a questo punto inizia la successione di comandi per l'esecuzione del programma
        Loopper l = new Loopper();
        break;
     
    }
 }   
}