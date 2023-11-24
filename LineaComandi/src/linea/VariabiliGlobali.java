package linea;

/**
 *
 * @author luca
 * classe che contiene le variabili globali del programma
 */
import java.net.URL;
import java.io .File;

public class VariabiliGlobali 
{
private final String ARCHIVIO_DIRS ="ArchivioDIRS.txt";
private final String LISTA_URLS ="ListaURLS.txt";
private static String ROOT_D;
private static URL SITO;
private static File URLS;
private static File DIRS;

public VariabiliGlobali()
{
}

public void set_sito(URL sito)
{
SITO = sito;
}


public void set_root(String root_d)
{
ROOT_D = root_d;
}

public void set_files()
{
URLS = new File (ROOT_D, LISTA_URLS);
DIRS = new File (ROOT_D, ARCHIVIO_DIRS);
}
public URL get_sito()
{
return SITO;
}


public String get_root()
{
return ROOT_D;
}

public File get_file_URLS()
{
return URLS;
}

public File get_file_DIRS()
{
return DIRS;
}

}
