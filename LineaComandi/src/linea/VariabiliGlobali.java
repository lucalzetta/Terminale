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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;

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
private final String FILE_URLS ="ListaURLS.txt";
private final String FILE_IMMAGINI ="ListaImmagini.txt";
private final String FILE_PAGINE ="ListaPagine.txt";
private final String FILE_PAGINE_VISITATE ="ListaPagineVisitate.txt";
private final String FILE_PAGINE_SCARTATE ="ListaPagineScartate.txt";
private final String FILE_FNF ="ListaPagineNonTrovate.txt";
private final String FILE_ERR ="FileDiLogErrori.txt";
private final String FILE_LOG ="FileDiLog.txt";
private static String PAGINA;//questa variabile conterrà il testo della pagina da scaricare
private static String NOME_PAGINA;//questa variabile conterrà il nome della pagina da scaricare
//private static String CARTELLA_SITO = "/home/lucaamministratore/tmp/"; //Percorso per Debian
private static String CARTELLA_SITO = "/home/luca/tmp/";//percorso per sistema operativo Linux Mint
private static String ROOT_DEST = "";
private static String ROOT_D;
private static String SUBDIR;//questa variabile conterrà i percorsi relativi in cui salvare i file
private static StringBuilder PAGINA_BUILDER;
private static URL SITO;
private static URL SITO_RIDOTTO;//rappresenta la root directory del sito in esame
private static File URLS;
private static File DIRS;
private static File FNF;
private static File IMG;
private static File PAG;
private static File PAG_VISIT;
private static File PAG_SCART;
private static File ERR;
private static File LOG;
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
private final static Set <String> LISTA_FNF = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //l'elenco di tutte le pagine il cui download NON è 
                                                                //andato a buon fine
private final static Set <String> LISTA_PG_SCARTATE = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //l'elenco di tutte le pagine a cui NON HA SENSO TENTARE di
                                                                //accedere.
                                                                //LISTA_SCARICATI
private final static Set <String> LISTA_PG_VISITATE = new TreeSet<>();//lo scopo di questa lista è quello di memorizzare 
                                                                //l'elenco di tutte le pagine a cui si è tentato di 
                                                                //accedere, dovrebbe essere la somma di LISTA_FNF e
                                                                //LISTA_SCARICATI

private static CheckerVariabili CV = new CheckerVariabili();
private static ErrorsClasse EC = new ErrorsClasse();

public VariabiliGlobali()
{
//System.out.printf("%nCLASSE VariabiliGlobali, costruttore di default.%n");
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
//System.out.printf("%nCLASSE VariabiliGlobali, metodo set_root().%n");    

if ((root_d != null))
    {
        //aggiungiamo sempre la barra all'ultima directory
        if(root_d.endsWith("/"))
            {
            
            }
        else
            {
                root_d = root_d +"/";
            }
        /*System.out.printf("%nCLASSE VariabiliGlobali, metodo set_root().%n"
                        + "Valore della variabile locale root_d"
                + " fuori dall'if: %s%n", root_d );
        */        
        if (root_d.substring(0, 1) == "/")
            {
                root_d = root_d.substring(1);
                /*System.out.printf("%nCLASSE VariabiliGlobali, metodo set_root().%n"
                        + "Valore della variabile locale root_d: %s%n", root_d );*/
            }
        //se root_d è contenuta nella vecchia root allora degradiamo la vecchia root
        //per risalirla
        ROOT_D = root_d;
        System.out.printf("ROOT_D: %s%n", ROOT_D);

        //System.out.printf("Root directory del progetto: %s%n", ROOT_D);
        ROOT_DEST = ROOT_D;
        Path percorso = Paths.get(ROOT_D);
        //CV.get_ROOT_D();
        //CV.get_ROOT_DEST();
        
        if(Files.notExists(percorso, LinkOption.NOFOLLOW_LINKS))
            {
                Files.createDirectories(percorso);
                System.out.println("Cartella di destinazione del sito: " + ROOT_D
                            + " CREATA CORRETTAMENTE!");
            }
    }
else
    {
                System.out.printf("%nPercorso impossibile da creare: %s%n"
                        + " xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                        ,ROOT_D);
    }
}


public void set_subdir(String subdir)throws IOException
{
if(subdir.startsWith("/"))
    {
        subdir = subdir.substring(1);
    }    
SUBDIR = subdir;
//CV.get_SUBDIR();
//System.out.printf("Percorso di salvataggio del file corrente: %s%n", ROOT_D + subdir);

}
public void set_files()throws IOException
{
try
    {
        URLS = new File (ROOT_D, FILE_URLS);
        if(!URLS.exists())
            {
                URLS.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        DIRS = new File (ROOT_D, ARCHIVIO_DIRS);
        if(!DIRS.exists())
            {
                //System.out.printf("Creato il file %s%n", DIRS.toString());
                DIRS.createNewFile();
            }
        IMG = new File (ROOT_D, FILE_IMMAGINI);
        if(!IMG.exists())
            {
                IMG.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        PAG = new File (ROOT_D, FILE_PAGINE);
        if(!PAG.exists())
            {
                PAG.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        PAG_VISIT = new File (ROOT_D, FILE_PAGINE_VISITATE);
        if(!PAG_VISIT.exists())
            {
                PAG_VISIT.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        PAG_SCART = new File (ROOT_D, FILE_PAGINE_SCARTATE);
        if(!PAG_SCART.exists())
            {
                PAG_SCART.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        FNF = new File (ROOT_D, FILE_FNF);
        if(!FNF.exists())
            {
                FNF.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        ERR = new File (ROOT_D, FILE_ERR);
        if(!ERR.exists())
            {
                ERR.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
            }
        LOG = new File (ROOT_D, FILE_LOG);
        if(!LOG.exists())
            {
                LOG.createNewFile();
                //System.out.printf("Creato il file %s%n", URLS.toString());
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
NOME_PAGINA = nome_pagina.strip();
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

public void set_cartella_sito(String cartella_sito)
{
CARTELLA_SITO = cartella_sito;
}
/**
 * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
 * Fine delle istruzioni set e inizio delle istruzioni get
 * @return 
 * YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY
 */
public String get_archivio_dirs()
{
return ARCHIVIO_DIRS;
}
public String get_lista_urls()
{
return FILE_URLS;
}
public String get_nome_file_pagine()
{
return FILE_PAGINE;
}
public String get_nome_file_pagine_visitate()
{
return FILE_PAGINE_VISITATE;
}
public String get_nome_file_pagine_scartate()
{
return FILE_PAGINE_SCARTATE;
}
public String get_nome_file_immagini()
{
return FILE_IMMAGINI;
}
public String get_nome_file_pagine_non_trovate()
{
return FILE_FNF;
}
public String get_nome_file_errori()
{
return FILE_ERR;
}

public String get_page()
{
return PAGINA;
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
public String get_root_dest()
{
return ROOT_DEST;
}
public String get_root()
{
return ROOT_D;
}
public String get_subdir()
{
return SUBDIR;
}
public StringBuilder get_pagina_builder()
{
return PAGINA_BUILDER;
}
public URL get_sito()
{
return SITO;
}
public URL get_sito_ridotto()
{
return SITO_RIDOTTO;
}
public File get_file_URLS()
{
return URLS;
}
public File get_file_DIRS()
{
return DIRS;
}
public File get_file_PAG()
{
return PAG;
}
public File get_file_PAG_VISIT()
{
return PAG_VISIT;
}
public File get_file_PAG_SCART()
{
return PAG_SCART;
}
public File get_file_IMG()
{
return IMG;
}
public File get_file_FNF()
{
return FNF;
}
public File get_file_ERR()
{
return ERR;
}
public File get_file_LOG()
{
return LOG;
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
public int get_conter()
{
return CONTATORE;
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
public Set<String> get_set_pagine_scartate()
{
return LISTA_PG_SCARTATE;
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
public Set<String> get_set_fnf()
{
return LISTA_FNF;
}
}
