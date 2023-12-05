package linea;

/**
 *
 * @author luca
 * Questa classe si occupa di archiviare in alcuni file tutti i riferimenti 
 * interni ed esterni del sito che si sta scaricando
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class ArchivioURLS 
{
private final String ROOTD;
private StringBuilder DATI_ORIGINE;
private final VariabiliGlobali VG = new VariabiliGlobali();


public ArchivioURLS()
{
//ATTENZIONE! Prima di chiamare questo inizializzatore devono essere istanziate
//le variabili globali dalla classe VariabiliGlobali()
ROOTD = VG.get_root();
}

public String get_root()
{
return ROOTD;
}

public void set_origin (StringBuilder dati)
{
this.DATI_ORIGINE=dati;
}

public void scrivi_su_file()throws IOException
{
File file = VG.get_file_URLS();
StringBuilder riga = new StringBuilder();
Set <String> ts = VG.get_set_collegamenti();
FileWriter fw = new FileWriter(file);
try
    {
        if (this.DATI_ORIGINE != null)
            {
                int a = 0;
                while ((a < (this.DATI_ORIGINE.length())) & (a!=-1))
                    {
                        while((DATI_ORIGINE.charAt(a)!=10) & (DATI_ORIGINE.charAt(a)!=-1))
                            {
                                riga.append(DATI_ORIGINE.charAt(a));
                                a++;
                            }
                        ts.add(riga.toString());
                        riga.delete(0, riga.length());
                        a++;
                    }
/*                for (int i = 0; i < (this.DATI_ORIGINE.length()); i++)
                fw.write(this.DATI_ORIGINE.charAt(i));*/
               for (String s : ts)
                   fw.write(s + "\n");
            }
    }
catch(IOException ex)
    {
        System.err.print("Si è verificato un errore di scrittura sul file: " + ex);
    }
finally
    {
            //if(fw != null) fw.close();
    }
if(fw != null) fw.close();
}
}
