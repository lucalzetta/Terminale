package linea;

/**
 *
 * @author luca
 * classe che contiene le variabili globali del programma
 */
import java.net.URL;
import java.io .File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class VariabiliGlobali 
{
private final String ARCHIVIO_DIRS ="ArchivioDIRS.txt";
private final String LISTA_URLS ="ListaURLS.txt";
private static String CARTELLA_SITO = "httpdocs";
private static String ROOT_D;
private static URL SITO;
private static File URLS;
private static File DIRS;
private static String ARGOMENTI_STRING;
private static int[] ARGOMENTI_INT;
private static String OPZIONI;
private final static Set <String> LISTA_URLS_LIST = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //tutti i link della pagina scaricata
private final static Set <String> LISTA_PAGINE = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //tutte le pagine del sito

private final static Set <String> LISTA_IMMAGINI = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //i collegamenti a tutte le immagini del sito

public VariabiliGlobali()
{
}

public void set_sito(URL sito)
{
SITO = sito;
}


public void set_root(String root_d)throws IOException
{
ROOT_D = root_d;
CARTELLA_SITO = ROOT_D + CARTELLA_SITO;
boolean dir = (new File(CARTELLA_SITO)).mkdirs();//crea la cartella, resta false 
                                                       //se l'operazione fallisce
if(dir)
    {
        System.out.println("Cartella di destinazione del sito: " + CARTELLA_SITO);
    }
else
    {
        System.out.println("La cartella di destinazione del sito: " + CARTELLA_SITO + ""
                + " NON è stata creata, VERIFICARE l'errore!");
    }
}

public void set_files()throws IOException
{
try
    {
        URLS = new File (ROOT_D, LISTA_URLS);
        if(!URLS.exists())
            {
                URLS.createNewFile();
            }
        DIRS = new File (ROOT_D, ARCHIVIO_DIRS);
        if(!DIRS.exists())
            {
                DIRS.createNewFile();
            }
    }
catch(IOException ioe)
    {
       System.err.printf("Errore nella classe VariabiliGlobali, metodo set_files: ", ioe);
    }
}

public void set_args(String argomenti)
{
ARGOMENTI_STRING = argomenti;
}

public void set_int_args(int[] argomenti_interi)
{
ARGOMENTI_INT = new int[(argomenti_interi.length)];
for (int i = 0; i <  argomenti_interi.length; i++)
    {
        ARGOMENTI_INT[i] = argomenti_interi[i];
    }
}

public void set_opzioni(String opz)
{
OPZIONI = opz;
}

public String get_root()
{
return ROOT_D;
}
public URL get_sito()
{
return SITO;
}

public String get_destinazione_files_sito()
{
return CARTELLA_SITO;
}
public File get_file_URLS()
{
return URLS;
}

public File get_file_DIRS()
{
return DIRS;
}

public String get_args()
{
return ARGOMENTI_STRING;
}

public int[] get_int_args()
{
return ARGOMENTI_INT;
}

public String get_opzioni()
{
return OPZIONI;
}

public Set<String> get_set_collegamenti()
{
return LISTA_URLS_LIST;
}

public Set<String> get_set_pagina()
{
return LISTA_PAGINE;
}

public Set<String> get_set_immagini()
{
return LISTA_IMMAGINI;
}
}
