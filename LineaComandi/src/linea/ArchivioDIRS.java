package linea;

/**
 *
 * @author luca
 * Questa classe si occupa di archiviare in un file tutte le directory 
 * necessarie alla ricostruzione del sito che si sta scaricando
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ArchivioDIRS 
{
private String ROOTD;
private String FILE_ORI;
private StringBuilder DATI_ORIGINE;
 
public ArchivioDIRS()
{

}

public void set_root(String root)
{
this.ROOTD=root;
}

public void set_origin (String nome_file)
{
this.FILE_ORI=nome_file;
}

}
