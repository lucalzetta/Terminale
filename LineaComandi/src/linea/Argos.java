package linea;
/**
 *Questa classe prende gli argomenti dalla riga di comando e definisce le operazioni da fare
 * in base alle lettere specificate come opzioni imitando le tipiche opzioni della riga 
 * di comando dei sistemi LINUX based.
 * Si prefigge di essere il vero entry point del programma, dopo il controllo dele 
 * opzioni si procede alla chiamata delle varie classi necessarie alle operazioni richieste.
 * @author Luca Alzetta
 */
import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

public class Argos 
{
    /**
     *Area delle variabili di classe
     * @param ARGOMENTI è la stringa degli argomenti così come viene inserita dall'utente
     * @param CARATTERI contiene la stringa degli argomenti convertita in cifre
     */
private final String[] ARGOMENTI;
private static int[] CARATTERI;
private static String PARAMETRI;

public Argos()
{
    /**
     * Costruttore di default nel caso in cui non vengano inserite opzioni di esecuzione
     * ovvero si esegua il codice in modalità diversa dalla riga di comando
     */
this.ARGOMENTI = new String[0];
CARATTERI = new int[0];
PARAMETRI = "";
System.out.println("Costruttore di default della classe Argos");
}

public Argos(String[] argomenti)throws IOException
{
    /**
     * Costruttore per la riga di comando, si dovrà porre molta attenzione al trattamento 
     * dei caratteri apice di apostrofazione che dalla bash vengono interpretati come 
     * inizio di un parametro e se non vengono chiusi generano un'attesa di ulteriore
     * input
     */
this.ARGOMENTI = argomenti;
int l = ARGOMENTI.length;
//this.PARAMETRI= "";
String testo = "";
int caratteri = 0;

//System.out.printf("Numero di argomenti passati dalla riga di comando: %d%n",l);

for (int i = 0; i < l; i ++)
        {
            caratteri = caratteri + ARGOMENTI[i].length() + 1;//l'aggiunta di uno serve a conteggiare gli spazi tra un argomento e l'altro
            testo = testo + ARGOMENTI[i] + " ";
        }

    caratteri = testo.length();
    PARAMETRI = testo;
    CARATTERI = new int[caratteri];

    /*System.out.printf("Valore della variabile locale 'caratteri' nel cosrtruttore con parametri della "
        + "classe Argos(): %s%nValore della variabile locale 'testo nel cosrtruttore con parametri "
        + "della classe Argos(): %s%n" , caratteri, testo);*/
    
    for(int a = 0; a < caratteri; a++)
        {
            CARATTERI[a] = testo.charAt(a);
        }

    /*for(int b = 0; b < caratteri; b++)
        {
            System.out.printf("Carattere N° %d dell'array, valore numerico: %d rappresentazione del carattere: '%c'%n",b, CARATTERI[b],CARATTERI[b]);
        }
    */
 
if (l>0)
{
    
    this.options(CARATTERI);
   
 //    System.out.printf("\"Costruttore con parametri della classe Argos, risultato dell'elaborazione dei parametri:\"%n%n");

    for (int i = 0; i < PARAMETRI.length(); i++)
        {
            if(PARAMETRI.charAt(i)!=32)
                {
                    System.out.printf("%s",PARAMETRI.charAt(i));
                }
            else
                {
                    System.out.println();
                }
        }
}
else
{
    System.out.printf("Nessun argomento ottenuto dalla riga di comando. Si forniscono i dati dell'host locale\n");
    this.options(CARATTERI);
}
}

private void options(int[] argomenti)throws IOException
{
/**
 * Questo metodo riceve l'array sotto forma di numeri interi degli argomenti passati 
 * al costruttore e ne estrae le opzioni per l'esecuzione del codice, ricorreremo alle
 * somme dei valori interi dei caratteri per stabilire quali azioni eseguire.
 * @param opzioni definirà il valore delle opzioni.
 * @param i contatore
 * @param saluto stringa di appoggio
 */
 int opzioni = 0;
 //String argos = "";
 int i = 0;
 //String saluto;
 
 if (argomenti.length == 0 ) 
    {
        opzioni = 0;
    }
 else if (argomenti[0] != 45) //45 è il valore del segno '-' trattino alto
    {
        opzioni = 0;
    }
else if (argomenti[0] == 45) 
    {
    while ((argomenti[i] != 32) & (i < argomenti.length))//fino al primo spazio
        {
            opzioni = opzioni + argomenti[i];
            i++;
        }
    }
 System.out.printf("Valore della variabile opzioni: %d%n", opzioni);//riga di debug
 
/**
 * A questo punto bisogna passare il controllo al metodo evalOpz per l'estrazione degli argomenti
 * di elaborazione, dopo di che, il codice successivo va inserito in un metodo a parte per le elaborazioni 
 * complete.
 */
if (ARGOMENTI.length > 0)
    {
        evalOpz(opzioni, ARGOMENTI[0]);
    }
else
    {
        evalOpz(150, "");
    }
 
}
 
 private URL sito(String sito)
 {
 /**
  * Questo metodo è al servizio di eval Opzioni e serve ad estrarre un URL dagli 
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
     String root_directory = "";
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

 private void evalOpz(int opzioni, String argos)throws IOException
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
      * Questo caso valuta se si è scelto di visualizzare le informazioni di base
      * di un sito, passato senza parametri del sito da analizzare dovrà fornire le
      * informazioni di un sito di default. Carattere 'i' 105
      */     
     case 150:
         //a questo punto bisogna controllare la presenza di argomenti e passarli 
         //alla procedura che fornisce le informazioni le prossime righe sono di 
         //test, da modificare
         InfoNet in = new InfoNet(argos);
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
          * Questo caso valuta se si è scelto di visualizzare le istruzioni del 
          * programma. Carattere 'h' 104
          */
     case 149:
         System.out.printf("É stata scelta l'opzione '%s'%n", argos);
         TermInputStream is = new TermInputStream("/home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/Maschera.txt");
        int lunghezzaFile = is.getLength();
        System.out.printf("La lunghezza del file selezionato è; %d byte\n", lunghezzaFile);
        is.mostraFile();
        Console cs = System.console();
        saluto = cs.readLine("\n");
        cs.printf("Dal tuo input è stato letto: %s\n", saluto);
         break;
     case 160:
         //Per l'opzione '-s' deve venire frnito un sito valido e una root directory
        //che andrà validata anch'essa
        //impostiamo le variabili globali
        VariabiliGlobali vg = new VariabiliGlobali();
        vg.set_sito(sito(PARAMETRI));
        vg.set_root(directory(PARAMETRI));
        vg.set_files();
        System.out.printf("É stata scelta l'opzione 's', verrà scaricato il "
                + "sito:%n%s%nNella directory:%n"
                + "%s%ne scritti i "
                + "file:%n%s%n%s%n",vg.get_sito().toString(),vg.get_root(), vg.get_file_URLS().toString(), vg.get_file_DIRS().toString());
        //a questo punto inizia la successione di comandi per l'esecuzione del programma
        break;
     
    }
 }
}