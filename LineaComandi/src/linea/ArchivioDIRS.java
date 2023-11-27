package linea;

/**
 *
 * @author luca
 * Questa classe si occupa di archiviare in un file tutte le directory 
 * necessarie alla ricostruzione del sito che si sta scaricando
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArchivioDIRS 
{
private final String ROOTD;
private String FILE_ORI;
private String DATI_ORIGINE;
 
public ArchivioDIRS()
{
//ATTENZIONE! Prima di chiamare questo inizializzatore devono essere istanziate
//le variabili globali dalla classe VariabiliGlobali()
VariabiliGlobali vg = new linea.VariabiliGlobali();
ROOTD = vg.get_root();
}

public String get_root()
{
return ROOTD;
}

public void set_origin (String dati)
{
DATI_ORIGINE = dati;
}

public void scrivi_su_file()throws IOException
{
VariabiliGlobali vg = new VariabiliGlobali();    
File file = vg.get_file_DIRS();
FileOutputStream fw = new FileOutputStream(file, true);
try
    {
        if (this.DATI_ORIGINE != null)
            {
                for (int i = 0; i < (this.DATI_ORIGINE.length()); i++)
                fw.write(this.DATI_ORIGINE.charAt(i));
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