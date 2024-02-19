package linea;
/**
 *Questa classe prende gli argomenti dalla riga di comando e definisce le operazioni da fare
 * in base alle lettere specificate come opzioni imitando le tipiche opzioni della riga 
 * di comando dei sistemi LINUX based.
 * Si prefigge di essere il vero entry point del programma, dopo il controllo dele 
 * opzioni si procede alla chiamata delle varie classi necessarie alle operazioni richieste.
 * Offre anche gli argomenti sotto forma di array di interi, stringa di testo alla 
 * classe delle variabili globali. Può essere sfruttata anche all'interno di altri 
 * programmi che si vogliono eseguire dalla shell
 * @author Luca Alzetta
 */
import java.io.IOException;

public class Argos 
{
    /**
     *Area delle variabili di classe
     * @param ARGOMENTI è la stringa degli argomenti così come viene inserita dall'utente
     * @param CARATTERI contiene la stringa degli argomenti convertita in cifre
     * 
     */
private static String[] ARGOMENTI;
private static int[] CARATTERI;
private final VariabiliGlobali VG = new VariabiliGlobali();

public Argos()throws IOException
{
//System.out.printf("%nCLASSE Argos, costruttore di default.%n");        
    /**
     * Costruttore di default nel caso in cui non vengano inserite opzioni di esecuzione
     * ovvero si esegua il codice in modalità diversa dalla riga di comando
     */
ARGOMENTI = new String[0];
CARATTERI = new int[0];
System.out.printf("Nessun argomento ottenuto dalla riga di comando. Si forniscono i dati dell'host locale\n");
    this.options(CARATTERI);

}

public Argos(String[] argomenti)throws IOException
{
//System.out.printf("%nCLASSE Argos, costruttore per la riga di comando con argomenti String[].%n");    
    /**
     * Costruttore per la riga di comando, si dovrà porre molta attenzione al trattamento 
     * dei caratteri apice di apostrofazione che dalla bash vengono interpretati come 
     * inizio di un parametro e se non vengono chiusi generano un'attesa di ulteriore
     * input
     */
ARGOMENTI = argomenti;
int l = ARGOMENTI.length;
String testo = "";
int caratteri = 0;

//System.out.printf("Numero di argomenti passati dalla riga di comando: %d%n",l);
/**Questo ciclo non fa altro che contare i caratteri degli argomenti inseriti dall'utente e passare 
 * il contenuto dell'array di stringhe restituito dalla shell ad un unica variabile di tipo stringa.
 * Conoscendo il numero di caratteri da lavorare possiamo istanziare un array con la dimensione dei 
 * caratteri da contenere.
 */
for (int i = 0; i < l; i ++)
        {
            caratteri = caratteri + ARGOMENTI[i].length() + 1;//l'aggiunta di uno serve a conteggiare gli spazi tra un argomento e l'altro
            testo = testo + ARGOMENTI[i] + " ";
        }

    caratteri = testo.length();
    CARATTERI = new int[caratteri];
/**Carichiamo l'array con i valori interi dei caratteri della stringa di argomenti.
 * Ciò ci permetterà di valutare con delle somme fra interi il valore delle opzioni
 * inserite dall'utente.
 */
    for(int a = 0; a < caratteri; a++)
        {
            CARATTERI[a] = testo.charAt(a);
        }
    /**
     * Passiamo i valori alla classe delle variabili globali.
     */
VG.set_args(testo);
VG.set_int_args(CARATTERI);
VG.set_opzioni(ARGOMENTI[0]);
//VG.set_files();//Crea i files di supporto o li imposta come riferimento.
                //commentato perché in questa fase le informazioni non sono sufficienti
    /*for(int b = 0; b < caratteri; b++)
        {
            System.out.printf("Carattere N° %d dell'array, valore numerico: %d rappresentazione del carattere: '%c'%n",b, CARATTERI[b],CARATTERI[b]);
        }
    */

   options(CARATTERI);
}

private void options(int[] argomenti)throws IOException
{
//System.out.printf("%nCLASSE Argos, metodo options().%n");            
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
 //System.out.println("Dentro options");
 if (argomenti.length == 0 ) 
    {
        opzioni = 0;
        //passare il controllo alle analisi dell'host locale. 26/11/2023
    }
 else if (argomenti[0] != 45) //45 è il valore del segno '-' trattino alto
    {
        System.out.println("Le opzioni di esecuzione vanno precedute dal carattere trattino breve (segno meno)!\n"
                + "Inserire '-h' per la guida");
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
 //System.out.printf("Valore della variabile opzioni: %d%n", opzioni);//riga di debug
 //System.out.printf("Lunghezza di 'ARGOMENTI[]': %d%n", ARGOMENTI.length);//riga di debug
/**
 * A questo punto bisogna passare il controllo alla classe EvalOpzioni per l'estrazione degli argomenti.
 */
EvalOpzioni eo = new EvalOpzioni();

if (ARGOMENTI.length > 0)
    {
        System.out.printf("Valore di 'argomenti[0]: %s%n",ARGOMENTI[0]);//riga di debug
        eo.evalOpz(opzioni);
    }
else
    {
        eo.evalOpz(150);
    }
}
}