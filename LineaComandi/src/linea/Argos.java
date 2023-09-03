package linea;
/**
 *Questa classe prende gli argomenti dalla riga di comando e definisce le operazioni da fare
 * in base alle lettere specificate come opzioni imitando le tipiche opzioni della riga 
 * di comando dei sistemi LINUX based.
 * @author Luca Alzetta
 */
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

public Argos(String[] argomenti)
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
}

private void options(int[] argomenti)
{
/**
 * Questo metodo riceve l'array sotto forma di numeri interi degli argomenti passati 
 * al costruttore e ne estrae le opzioni per l'esecuzione del codice, ricorreremo alle
 * somme dei valori interi dei caratteri per stabilire quali azioni eseguire.
 */
 int opzioni = 0;
 if (argomenti.length == 0 ) opzioni = 0;
 if (argomenti[0] != 45) opzioni = 0;//45 è il valore del segno '-' trattino alto
 
    
}
}
