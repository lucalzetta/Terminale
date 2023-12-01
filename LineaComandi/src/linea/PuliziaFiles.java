package linea;

/**
 *
 * @author lucaamministratore
 * Questa classe ha lo scopo di eliminare le righe doppie nei file di appoggio 
 * ListaURLS.txt e ArchivioDIRS.txt e altri eventuali file di appoggio su cui
 * potrebbe essere necessario compiere delle azioni aggiuntive.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PuliziaFiles 
{
private final VariabiliGlobali VG = new VariabiliGlobali();

public PuliziaFiles()
{

}

public void del_URLS_doppi()
{
/**
 * Questo è il metodo che opera la pulizia nel file degli URLS
 */
StringBuilder urle = new StringBuilder();
File f = VG.get_file_URLS();
char[] riga;
String s_riga = "";
int acapo;
int segno = 0;
/**
 * Ricostruiamo il testo del file e lo salviamo in uno StringBuilder
 */
try
    {
        FileInputStream fis = new FileInputStream(f);
        int c;
        while ((c = fis.read()) != -1) 
        {
          urle = urle.append((char)c);
          //System.out.print((char) c);
        }
        c = 0;
        //System.out.printf("Testo del file %s copiato nello StringBuilder", urle);
        //ricostruiamo le righe in successione e cancelliamo le ricorrenze che troviamo
        int y = 0;
        while(y < 50)//urle.length()
            {
                //System.out.printf("Variabili 'segno' %d.%n%n",segno);
                acapo = urle.indexOf("\n", segno);
                int x = acapo - segno;
                riga = new char[(x)];
                int i = 0;
                System.out.printf("Variabili 'x' %d, 'i' %d, 'acapo' %d, 'segno' %d.%n",x,i,acapo,segno);
                while ((i < x) & (x != -1))
                    {
                        riga[i] = urle.charAt(segno);
                        segno ++;
                        i++;
                        System.out.printf("Test di del_URLS_doppi, ripetizione N° %d%n"
                        + " nel ciclo annidato x vale: %d%n", i,  x);
                    }
                //carichiamo i dati della variabile 'riga' in una stringa
                s_riga = "";
                for (int a = 0; a < riga.length; a++)
                    {
                        s_riga =  s_riga + riga[a];
                    }
                segno = acapo + 1;
                //cerchiamo i duplicati e li cancelliamo
                x = urle.indexOf(s_riga);
                System.out.printf("Test di del_URLS_doppi, ripetizione N° %d%ns_riga vale: %s%n x vale: %d%n", y, s_riga, x);
                y ++;
            }
    }
catch(IOException ioe)
    {
        System.err.printf("Errore %s nel metodo "
                + "del_URLS_doppi della classe PuliziaFiles().", ioe);
    }

}
}
