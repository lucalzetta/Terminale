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
/**
 * @param TESTO
 * Memorizza un valore booleano che indica se il file che stiamo valutando 
 * è esplorabile da programma come file di testo oppure può essere solo 
 * scaricato e salvato in una directory.
 * @param LISTA_URLS_LIST
 * questa variabile di tipo TreeSet ci permetterà di memorizzare tutti gli URL del 
 * sito fino alla loro trascrizione sul file ListaURLS.txt senza il rischio di 
 * memorizzare duplicati o valori nulli, stesso discorso vale, per scopi diversi, 
 * per le variabili:
 * @param LISTA_PAGINE
 * @param LISTA_IMMAGINI
 * @param LISTA_LINKS
 * Discorso simile ma a scopo di riscontro e verifica per la lista di tipo TreeSet
 * @param LISTA_SCARICATI
 * che riceve gli URLS il cui download è andato a buon fine, quando il confronto
 * fra questa e la lista dei links dà esito di uguaglianza il processo è 
 * completato
 */
private final String ARCHIVIO_DIRS ="ArchivioDIRS.txt";
private final String LISTA_URLS ="ListaURLS.txt";
private static String PAGINA;//questa variabile conterrà il testo della pagina da scaricare
private static String NOME_PAGINA;//questa variabile conterrà il nome della pagina da scaricare
private static String CARTELLA_SITO = "httpdocs";
private final String ROOT_DEST = "/home/lucaamministratore/GDrive/Luca/Programmazione/tMP/";
private static String ROOT_D;
private static String SUBDIR;//questa variabile conterrà i percorsi relativi in cui salvare i file
private static StringBuilder PAGINA_BUILDER;
private static URL SITO;
private static URL SITO_RIDOTTO;
private static File URLS;
private static File DIRS;
private static String ARGOMENTI_STRING;
private static int[] ARGOMENTI_INT;
private static int CONTATORE;
private static String OPZIONI;
private static boolean TESTO;
private final static Set <String> LISTA_URLS_LIST = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //tutti i link della pagina scaricata
private final static Set <String> LISTA_PAGINE = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //tutte le pagine del sito

private final static Set <String> LISTA_IMMAGINI = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //i collegamenti a tutte le immagini del sito
private final static Set <String> LISTA_LINKS = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //i collegamenti a tutti i siti esterni al sito
private final static Set <String> LISTA_SCARICATI = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //l'elenco di tutte le pagine il cui download è 
                                                                //andato a buon fine

public VariabiliGlobali()
{
}

public void set_sito(URL sito)
{
SITO = sito;
}

public void set_sito_ridotto(URL sito)
{
SITO_RIDOTTO = sito;
}

public void set_root(String root_d)throws IOException
{
ROOT_D = ROOT_DEST + root_d;
System.out.printf("Root directory del progetto: %s%n", root_d);
CARTELLA_SITO = ROOT_D;
/**
 * Sospendiamo temporaneamente il seguente tratto di codice perché fuori posto.
 * UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
 */

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

/**
 * Fine del tratto sospeso. 16/02/2024
 * OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
 */
}
public void set_subdir(String subdir)throws IOException
{
SUBDIR = subdir;
System.out.printf("Percorso di salvataggio del file corrente: %s%n", ROOT_D + subdir);
/**
 * Sospendiamo temporaneamente il seguente tratto di codice perché fuori posto.
 * UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
 */

boolean dir = (new File(SUBDIR)).mkdirs();//crea la cartella, resta false 
                                                       //se l'operazione fallisce
if(dir)
    {
        System.out.println("Cartella di destinazione del file: " + SUBDIR + " CREATA CON SUCCESSO!");
    }
else
    {
        System.out.println("La cartella di destinazione del FILE: " + SUBDIR + ""
                + " NON è stata creata, VERIFICARE l'errore!");
    }
/**
 * Fine del tratto sospeso. 16/02/2024
 * OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
 */
}
public void set_files()throws IOException
{
try
    {
        URLS = new File (ROOT_D, LISTA_URLS);
        if(!URLS.exists())
            {
                URLS.createNewFile();
                System.out.printf("Creato il file %s%n", URLS.toString());
            }
        DIRS = new File (ROOT_D, ARCHIVIO_DIRS);
        if(!DIRS.exists())
            {
                System.out.printf("Creato il file %s%n", DIRS.toString());
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

public void set_page(String pagina)
{
PAGINA = pagina;    
}

public void set_name_page(String nome_pagina)
{
NOME_PAGINA = nome_pagina;
}

public void set_page_builder(StringBuilder builder)
{
PAGINA_BUILDER = builder;
}

public void set_conter()
{
CONTATORE = CONTATORE + 1;
}

public void set_testo(boolean testo)
{
TESTO = testo;
}
/**
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * Fine delle istruzioni set e inizio delle istruzioni get
 * @return 
 * YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
 */
public String get_root_dest()
{
return ROOT_DEST;
}
public String get_root()
{
return ROOT_D;
}
public URL get_sito()
{
return SITO;
}
public URL get_sito_ridotto()
{
return SITO_RIDOTTO;
}
public String get_subdir()
{
return SUBDIR;
}
public String get_name_page()
{
return NOME_PAGINA;
}
public String get_destinazione_files_sito()
{
    /**
     * Questo metodo ritorna la directory locale a partire dalla quale vengono 
     * archiviate le pagine e le cartelle del sito in esame
     */
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
//System.out.printf("Valore degli argomenti in VariabiliGlobali.get_args: %s%n", ARGOMENTI_STRING);
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

public boolean get_testo()
{
return TESTO;
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

public Set<String> get_set_sitiext()
{
return LISTA_LINKS;
}
public Set<String> get_set_scaricati()
{
return LISTA_SCARICATI;
}


public String get_page()
{
    return PAGINA;
}

public StringBuilder get_pagina_builder()
{
return PAGINA_BUILDER;
}

public int get_conter()
{
return CONTATORE;
}
}
