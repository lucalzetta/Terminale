package linea;

import java.io.FileInputStream;
import java.io.IOException;

/**
*Questa classe,
*crea un inputstream basato su un file e ne restituisce
*il contenuto.
 * @author luca
 */
public class InputStream 
{

private final java.io.InputStream in;
private int[] dati;
private final String nomeFile;

public InputStream(String nomeFile)throws IOException
{
    this.nomeFile = nomeFile;
    in = new FileInputStream(nomeFile);
}

public int getLength()
        /**
         * Questo metodo consente di determinare la lunghezza di un file passato 
         * come argomento in byte, può tornere utile nel dimensionamento di array 
         * di byte da utilizzare nel programma.
         */
{
int lunghezzaFile = 0;
boolean b = true;

try
    {
    while (b)
        {
            int dato = in.read();
            if(dato == -1) break;
            lunghezzaFile = lunghezzaFile +1;
        }
    dati = new int[lunghezzaFile];
    }
catch(IOException ioe)
    {
        System.out.printf("Eccezione nella lettura ddel file: %s", ioe);
    }
return lunghezzaFile;
}

public void mostraFile()
{
/**
*Questo metodo mostra tutti i caratteri del fileInputStream.
*Va a capo in corrispondenza di un carattere di ritorno a capo.
* Un primo utilizzo ne viene fatto nella classe Argos per visualizzare
* a console il file Maschera.txt in cui si sono memorizzate le opzioni 
* di lancio del programma.
*/
java.io.InputStream nf = null;
    
try
{
nf = new FileInputStream(this.nomeFile);    
    for (int i = 0; i < dati.length; i++) 
        {
            int datum = nf.read();
            if (datum == -1)break;
            dati[i] = datum;
        }
    for (int i = 0; i < dati.length; i++) 
        {
            if(dati[i] == 10) 
                {
                    System.out.printf("\n");
                }
            else
                {
                    char cara = (char)dati[i];
                    System.out.printf("%s",cara);
                }
        }
}
catch(IOException ioe)
{
        System.out.printf("Eccezione nella lettura del file nel metodo mostraFile: %s", ioe);
}
finally
{
    if (nf != null)
        {
            try
                {
                    nf.close();
                }
            catch (IOException ioe)
                {
                    System.err.printf("Errore nel tentativo di chiusura dell'inputStream nel metodo MostraFile: %s", ioe);
                }
        }
}
}
}