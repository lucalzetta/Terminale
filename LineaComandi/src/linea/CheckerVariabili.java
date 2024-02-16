package linea;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class CheckerVariabili 
{
/**
 * Lo scopo di questa classe è di offrire un controllo del
 * valore asunto dalle variabili globali in qualsiasi punto 
 * del programma
 */    
private final String ARCHIVIO_DIRS ="";
private final String LISTA_URLS ="";
private static String PAGINA;//questa variabile conterrà il testo della pagina da scaricare
private static String NOME_PAGINA;//questa variabile conterrà il nome della pagina da scaricare
private static String CARTELLA_SITO = "";
private final String ROOT_DEST = "";
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
private final VariabiliGlobali VG = new VariabiliGlobali();

public CheckerVariabili()
{

}

public void get_ARCHIVIO_DIRS ()
{
System.out.printf("%nValore di ARCHIVIO_DIRS: %s%n", VG.get_archivio_dirs());
}
public void get_LISTA_URLS()
{
System.out.printf("%nValore di LISTA_URLS: %s%n", VG.get_lista_urls());
}
public void get_PAGINA()//questa variabile conterrà il testo della pagina da scaricare
{
System.out.printf("%nTesto della pagina che si sta elaborando:%n%s%n", VG.get_page());
}
public void get_NOME_PAGINA()//questa variabile conterrà il nome della pagina da scaricare
{
System.out.printf("%nNome della pagina che si sta elaborando:%s%n", VG.get_name_page());
}
public void get_CARTELLA_SITO()
{
System.out.printf("%nNome della cartella in cui verrà salvato il sito:%s%n", VG.get_destinazione_files_sito());
}
public void get_ROOT_DEST()
{
System.out.printf("%nRoot directory in cui verrà salvato il sito:%s%n", VG.get_root_dest());
}
public void get_ROOT_D()
{
System.out.printf("%nAltra Root directory in cui verrà salvato il sito:%s%n", VG.get_root());
}
public void get_SUBDIR()//questa variabile conterrà i percorsi relativi in cui salvare i file
{
System.out.printf("%nSotto directory in cui verrà salvata la pagina:%s%n", VG.get_subdir());
}
public void get_PAGINA_BUILDER()
{
System.out.printf("%nTesto della pagina che si sta elaborando contenuto in uno STringBuilder:%n%s%n", VG.get_pagina_builder());
}
public void get_URL_SITO()
{
System.out.printf("%nURL della pagina che si sta elaborando contenuto in una"
        + " variabile di tipo URL: %s%n", VG.get_file_URLS().toString());
}
public void get_URL_SITO_RIDOTTO()
{
System.out.printf("%nURL origine del sito che si sta elaborando contenuto in una"
        + " variabile di tipo URL: %s%n", VG.get_sito_ridotto().toString());
}
public void get_File_URLS()
{
System.out.printf("%nURL origine del file che si sta elaborando contenuto in una"
        + " variabile di tipo File: %s%n", VG.get_file_URLS().toString());
}
public void get_File_DIRS()
{
System.out.printf("%nPercorso origine del file che si sta elaborando contenuto in una"
        + " variabile di tipo File: %s%n", VG.get_file_DIRS().toString());
}
public void get_ARGOMENTI_STRING()
{
System.out.printf("%nValore degli argomenti iniziali forniti al programma: %s%n"
        , VG.get_args());
}
public void get_ARGOMENTI_INT()
{
int[] args = new int[VG.get_int_args().length];
System.out.printf("%nValore degli argomenti iniziali forniti al programma "
        + "sotto forma di numeri interi:%n");
args = VG.get_int_args();
for(int i = 0; i < args.length; i++)
    {
    System.out.printf("%d\t%s%n", args[i], args[i]);
    }
}
public void get_CONTATORE()
{
System.out.printf("%nValore del contatore globale: %d%n",VG.get_conter());
}
public void get_OPZIONI()
{
System.out.printf("%nValore dele opzioni iniziali fornite al programma: %s%n", VG.get_opzioni());
}
public void get_TESTO()
{
System.out.printf("%nValore della variabile booleana TESTO che indica se "
        + "il file che si stà elaborando è un file di testo o meno: %b%n", VG.get_testo());
}
public void get_Set_URLS_LIST()
{
System.out.printf("%nElenco degli URLS presenti nel sito%n");
MostraRisultati(VG.get_set_sitiext());
}
public void get_Set_LISTA_PAGINE()
{
System.out.printf("%nElenco delle pagine presenti nel sito%n");
MostraRisultati(VG.get_set_pagina());
}
public void get_Set_LISTA_IMMAGINI()
{
System.out.printf("%nElenco delle immagini presenti nel sito%n");
MostraRisultati(VG.get_set_immagini());
}
public void get_Set_LISTA_LINKS()
{
System.out.printf("%nElenco dei collegamenti interni presenti nel sito%n");
MostraRisultati(VG.get_set_collegamenti());
}
public void get_Set_LISTA_SCARICATI()
{
System.out.printf("%nElenco delle pagine scaricate dal sito%n");
MostraRisultati(VG.get_set_scaricati());
}
private void MostraRisultati(Set<String> InSet)
{
   
System.out.printf("%n=============================================================================================%n"
                  + "Lista dei collegamenti presenti nel sito%n");
String STRINGA = "";
String spazi = "";
String sub = "";
int INT = 0;

Iterator it = InSet.iterator();

for (String st : InSet)
    {
        INT = 60 - st.length();
        if (INT > 0)
            {
                while (INT > 0)
                    {
                    spazi = spazi + " ";
                    INT--;
                    }
            }
        STRINGA = STRINGA + st;
        STRINGA = STRINGA + spazi;
        STRINGA = STRINGA + "*";
        spazi = "";
}
System.out.printf("%s", STRINGA);
}
}
