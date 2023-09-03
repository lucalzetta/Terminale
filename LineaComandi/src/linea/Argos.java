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
private String[] ARGOMENTI;
private int[] CARATTERI;

public Argos()
{
    /**
     * Costruttore di default nel caso in cui non vengano inserite opzioni di esecuzione
     * ovvero si esegua il codice in modalità diversa dalla riga di comando
     */
this.ARGOMENTI = new String[0];
this.CARATTERI = new int[0];
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

for(int b = 0; b < caratteri; b++)
    {
        System.out.printf("Carattere N° %d dell'array, valore numerico: %d rappresentazione del carattere: '%c'%n",b, CARATTERI[b],CARATTERI[b]);
    }

System.out.printf("Numero di caratteri passati dalla riga di comando: %d%n",caratteri);
this.options(CARATTERI);
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
 int i = 0;
 String saluto;
 
 if (argomenti.length == 0 ) opzioni = 0;
 if (argomenti[0] != 45) opzioni = 0;//45 è il valore del segno '-' trattino alto
 if (argomenti[0] == 45) 
    {
    while ((argomenti[i] != 32) & (i < argomenti.length))//fino al primo spazio
        {
            opzioni = opzioni + argomenti[i];
            i++;
        }
    }
 System.out.printf("Valore della variabile opzioni: %d%n", opzioni);//riga di debug
 switch (opzioni)
    {
     case 45:
         System.out.printf("Non è stata specificata alcuna opzione valida!%n");
         break;
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
     case 150:
         System.out.printf("É stata scelta l'opzione 'i'%n");
         break;
         
    }
    
}
}
