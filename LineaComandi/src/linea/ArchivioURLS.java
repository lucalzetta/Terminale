package linea;

/**
 *
 * @author luca
 * Questa classe si occupa di archiviare in alcuni file tutti i riferimenti 
 * interni ed esterni del sito che sis ta scaricando
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ArchivioURLS 
{
private String ROOTD;
private StringBuilder DATI_ORIGINE;


public ArchivioURLS()
{

}

public void set_root(String root)
{
this.ROOTD=root;
}

public void set_origin (StringBuilder dati)
{
this.DATI_ORIGINE=dati;
}

public void scrivi_su_file()throws IOException
{
String nome_file="";
nome_file = ROOTD + "ListaURLS.txt";
File file = new File(nome_file);
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
