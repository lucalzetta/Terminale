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
        System.out.printf("Testo del file %s copiato nello StringBuilder", urle);
        //ricostruiamo le righe in successione e cancelliamo le ricorrenze che troviamo
        int y = 0;
        while(y < 3)//urle.length())
            {
                System.out.printf("Variabili 'segno' %d.%n%n",segno);
                acapo = urle.indexOf("\n", segno);
                int x = acapo - segno;
                if(x < 0)
                    {
                        System.out.printf("Segno di nuova Linea non trovato, riga N°%d%n", y);
                        //riga = new char[0];
                        break;
                    }
                else
                    {
                        riga = new char[(x)];
                    }
                int i = 0;
                System.out.printf("Variabili 'x' %d, 'i' %d, 'acapo' %d, 'segno' %d.%n",x,i,acapo,segno);
                while ((i < x) & (x != -1))
                    {
                        riga[i] = urle.charAt(segno);
                        segno ++;
                        i++;
                        System.out.printf("Test di del_URLS_doppi, ripetizione N° %d%n"
                        + " nel ciclo annidato x vale: %d%nsegno vale: %s%n", i,  x, segno);
                    }
                //carichiamo i dati della variabile 'riga' in una stringa
                s_riga = "";
                for (int a = 0; a < riga.length; a++)
                    {
                        s_riga =  s_riga + riga[a];
                    }
                segno = acapo + 1;
                System.out.printf("s_riga vale %s%n", s_riga);
                //cerchiamo i duplicati e li cancelliamo
                /*x = urle.indexOf(s_riga);
                x ++;*/
                int a = 0;
                int b = 0;
                c = 0;
                while((a < urle.length()) & (a != -1))
                    {
                        c ++;
                        System.out.printf("?????%nTest di del_URLS_doppi, ripetizione N° "
                                + "%d%ns_riga vale: %s%n a vale: %d%n", c, s_riga, a);
                        //b = a;
                        a = urle.indexOf(s_riga, b);
                        b = a + s_riga.length();
                        if((c > 0))// & (a != -1))//cancelliamo le istanze doppie di ogni stringa
                        {
                            if (a != -1)
                                  {
                                      System.out.printf("REGULA!%na vale: %d%nb vale: %d%ns_riga vale "
                                              + "%s%nVerranno cancellati i caratteri: %s%n", a,b,s_riga, urle.substring(a,b));
                                      urle.delete(a, b);
                                  }
                            else
                                {
                                    System.out.printf("ERRORE_1!%nSelezione if interna del ciclo di eliminazione delle righe"
                                            + ", valore di c: %d\tvalore di a: %d%n", c,a);
                                    a = 0;
                                    break;
                                }
                        }
                        else
                        {
                            System.out.printf("ERRORE_2!%nSelezione if esterna del ciclo di eliminazione delle righe"
                                            + ", valore di c: %d\tvalore di a: %d%n", c,a);
                            a = 0;
                            break;
                        }
                    }
                //System.out.printf("Trovate %d istanze di '%s'%n", c, s_riga);
                y ++;
            }
        System.out.printf("urle ora vale: %s%n",urle);
    }
catch(IOException ioe)
    {
        System.err.printf("Errore %s nel metodo "
                + "del_URLS_doppi della classe PuliziaFiles().", ioe);
    }

}
}
