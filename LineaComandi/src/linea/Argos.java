package linea;

public class Argos 
{
private String[] ARGOMENTI;
private int[] CARATTERI;

public Argos()
{
this.ARGOMENTI = new String[0];
this.CARATTERI = new int[0];
System.out.println("Costruttore di default della classe Argos");
}

public Argos(String[] argomenti)
{
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
}
