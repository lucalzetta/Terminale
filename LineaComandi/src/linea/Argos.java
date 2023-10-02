package linea;
/**
 *Questa classe prende gli argomenti dalla riga di comando e definisce le operazioni da fare
 * in base alle lettere specificate come opzioni imitando le tipiche opzioni della riga 
 * di comando dei sistemi LINUX based.
 * @author Luca Alzetta
 */
import java.io.Console;
import java.io.IOException;
public class Argos 
{
    /**
     *Area delle variabili di classe
     * @param ARGOMENTI è la stringa degli argomenti così come viene inserita dall'utente
     * @param CARATTERI contiene la stringa degli argomenti convertita in cifre
     */
private final String[] ARGOMENTI;
private final int[] CARATTERI;
private String PARAMETRI;

public Argos()
{
    /**
     * Costruttore di default nel caso in cui non vengano inserite opzioni di esecuzione
     * ovvero si esegua il codice in modalità diversa dalla riga di comando
     */
this.ARGOMENTI = new String[0];
this.CARATTERI = new int[0];
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
this.PARAMETRI="";
String testo = "";
int caratteri = 0;

System.out.printf("Numero di argomenti passati dalla riga di comando: %d%n",l);

for (int i = 0; i < l; i ++)
        {
            caratteri = caratteri + ARGOMENTI[i].length() + 1;//l'aggiunta di uno serve a conteggiare gli spazi tra un argomento e l'altro
            testo = testo + ARGOMENTI[i] + " ";
        }
    caratteri = testo.length();
    this.CARATTERI = new int[caratteri];
    for(int a = 0; a < caratteri; a++)
        {
            CARATTERI[a] = testo.charAt(a);
        }

    /*for(int b = 0; b < caratteri; b++)
        {
            System.out.printf("Carattere N° %d dell'array, valore numerico: %d rappresentazione del carattere: '%c'%n",b, CARATTERI[b],CARATTERI[b]);
        }
    */
    //System.out.printf("Numero di caratteri passati dalla riga di comando: %d%n",caratteri);

if (l>0)
{
    
    this.options(CARATTERI);
    this.PARAMETRI = parametri(CARATTERI);
    System.out.printf("Costruttore con parametri della classe Argos, risultato dell'elaborazione dei parametri %s%n",PARAMETRI);
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
 * A questo punto bisogna passare il controllo al metodo argomenti per l'estrazione degli argomenti
 * di elaborazione, dopo di che, il codice successivo va inserito in un metodo a parte per le elaborazioni 
 * complete.
 */
if (ARGOMENTI.length > 0)
    {
        evalOpz(opzioni, ARGOMENTI[1]);
    }
else
    {
        evalOpz(150, "");
    }
 
}
 private String parametri(int[] testo)
 {
 /**
  * Questo metodo riceve dal costruttore l'array di caratteri degli argomenti 
  * della linea di comando e ne estrae i parametri restituendo una stringa che 
  * dovrebbe contenere i parametri per la ricerca dei siti e restituirli 
  * in un array di stringhe.
  * Correggere i tipi restituiti 04/09/2023
  */
     
String disc = "";
int i = 0;

if (testo.length == 0 ) 
    {
        
    }
else if (testo[0] != 45)//45 è il valore del segno '-' trattino alto
    {
     while (i < testo.length)//fino alla fine del tasto
        {
         while (testo[i] != 32)//fino al primo spazio
         {
            disc = disc + (char)testo[i]; 
            i++;
         }
         i++;       
        }

    }

else if (testo[0] == 45) 
    {
    while ((testo[i] != 32) & (i < testo.length))//fino al primo spazio
        {
            i++;
        }
    while (i < testo.length)//fino alla fine del testo
        {
            disc = disc + (char)testo[i]; 
            i++;
        }    
    }

return disc;
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
         System.out.printf("É stata scelta l'opzione 'h'%n");
         InputStream is = new InputStream("/home/luca/GDrive/Luca/Programmazione/JAVA/Terminale/Maschera.txt");
        int lunghezzaFile = is.getLength();
        System.out.printf("La lunghezza del file selezionato è; %d byte\n", lunghezzaFile);
        is.mostraFile();
        Console cs = System.console();
        saluto = cs.readLine("\n");
        cs.printf("Dal tuo input è stato letto: %s\n", saluto);
         break;
     
    }
 }
}