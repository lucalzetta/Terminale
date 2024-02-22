/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

/**
 *
 * @author luca
 * Questa classe si occupa di identificare il nome_directory da costruire andandole 
 * a scovare nel file ListaURLS. Quindi le costruisce e ne riassume la struttura
 * nel file ListaDIRS
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Iterator;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

public class TrovaDirs 
{
private static String TESTO;
private final VariabiliGlobali VG = new VariabiliGlobali();
private final String ROOT;
private final String CARTELLA_SITO;
private static CheckerVariabili CV = new CheckerVariabili();
//L'uso dei file viene sostituito dall'uso dei Set<> 15/02/2024
//private final File FILE_OUT;
//private final File FILE_IN;

public TrovaDirs()throws IOException
{
System.out.printf("%nCLASSE TrovaDirs, costruttore di default.%n");
ROOT = VG.get_root();
CARTELLA_SITO = VG.get_destinazione_files_sito();
}

public String cerca_dire()throws IOException
{
System.out.printf("%nCLASSE TrovaDirs, metodo cerca_dire().%n");
String tmp = "";
String riga = "";
String sub_dir= "";
String file_name = "";
String u_perc;
boolean perc = false;
boolean find = true;
Set <String> ts = VG.get_set_collegamenti();
Set <String> visitati = VG.get_set_scaricati();
Iterator collegamenti = ts.iterator();
Iterator coll_visitati = visitati.iterator();

/**
 * Questo metodo esplora il contenuto del Set in cui vengono
 * archiviati i collegamenti del sito e trasforma il loro percorso
 * in directory locali
 * 07/02/2024
 * Invece dei file useremo le liste in cui vengono archiviati 
 * i links, in questo modo si semplifica il codice e si velocizza
 * l'esecuzione, la scrittura dei file viene rimandata alla 
 * conclusione del programma
 * 20/02/2024
 * Vengono impostate anche alcune importanti variabili globali
 * @param ROOT_D
 * @param ROOT_DEST
 * @param SUBDIR
 * @param NOME_PAGINA
 */
if(collegamenti.hasNext())
{    
    while((! perc) & (collegamenti.hasNext()))//coll_visitati.hasNext()
        {
            riga = collegamenti.next().toString();
            while ((find) & (coll_visitati.hasNext()))
                {
                    tmp = coll_visitati.next().toString();
                    find = riga.equalsIgnoreCase(tmp);
                }
            
            if(!find)
                {
                    perc = true;
                }
            if (! coll_visitati.hasNext())
                {
                    perc = true;
                }
            
        }
        
            System.out.printf("%nValore di riga da elaborare: %s%n",riga);
            perc = false;

    if((riga != "") & (riga != null))
        {
            perc = esame_directory(riga);
            if(perc)
                {
                    Path path_riga = Paths.get(riga);
                    sub_dir = path_riga.getParent().toString();
                    
                    if(sub_dir.endsWith("/"))
                        {
                        
                        }
                    else
                        {
                            sub_dir = sub_dir + "/";
                        }
                    
                    file_name = path_riga.getFileName().toString();
                    
                    if(CARTELLA_SITO.lastIndexOf("/") == (CARTELLA_SITO.length() - 1))
                        {
                            u_perc = CARTELLA_SITO + sub_dir;
                        }
                    else
                        {
                            u_perc = CARTELLA_SITO + "/" + sub_dir;
                        }
                            /**
                             * Aggiungiamo un ulteriore controllo per superare 
                             * malformazioni della sotto directory che si sono 
                             * verificate e per normalizzare la struttura delle
                             * sottodirectory
                             */
                           
                    System.out.printf("%nClasse TrovaDIrs, "
                                + "metodo cerca_dire(),%n VALORE DI file_name: %s%n"
                                + "VALORE DI sub_dir: %s%n"
                                + "VALORE DI root_d: %s%n",file_name, sub_dir,u_perc);
                    
                    if ((path_riga != null) & (path_riga.toString() != ""))
                        {
                            if(sub_dir.equalsIgnoreCase(VG.get_subdir()))
                                {
                                    System.out.printf("%nClasse TrovaDIrs, "
                                            + "metodo cerca_dire() La vecchia "
                                            + "subdirectory e la nuova corrispondono%n");
                                }
                            else
                                {
                                    VG.set_subdir(sub_dir);
                                    CV.get_SUBDIR();
                                    VG.set_root(sub_dir);
                                    CV.get_ROOT_D();
                                    VG.set_root_dest(u_perc);
                                    CV.get_ROOT_DEST();
                                }

                            VG.set_name_page(file_name);
                            CV.get_NOME_PAGINA();
                            //VG.set_sito(nuovo_perc);
                            CV.get_URL_SITO();
                            CV.get_CARTELLA_SITO();
                            System.out.printf("%nPercorso da creare: %s%n",u_perc);
                            
                            path_riga = Paths.get(u_perc);
                            
                            if(Files.notExists(path_riga, LinkOption.NOFOLLOW_LINKS))
                                {
                                    Files.createDirectories(path_riga);
                                }
                        }
                    tmp = u_perc;
                    riga = "";
                }
        }
return tmp;
}
else
{
    System.out.println();
        for(int i = 0; i < 12; i++)
            {
                System.out.printf("%s", "TOP ");
            }
    System.out.printf("%nAl momento i treeSet sono vuoti%n");
return tmp;
}
}

public void imposta_sito(String pagina_da_visitare)
{
System.out.printf("%nCLASSE TrovaDirs, metodo imposta_sito().%n");    
Iterator i_links = VG.get_set_collegamenti().iterator();
Iterator i_scaricati = VG.get_set_scaricati().iterator();
boolean t = false;
boolean u = false;
String to_visit = "";
String visitato = "";
String tmp = "";
String n_file = "";
String sub_dir = "/";
int contatore_e = 0;
int contatore_i = 0;
URL perc;
URL nuovo_perc;
File page;
try
    {
        perc = new URL(VG.get_sito_ridotto().toString());
        tmp = tmp + perc.getProtocol();
        tmp = tmp + "://";
        tmp = tmp + perc.getAuthority();
        tmp = tmp + "/";
        tmp = tmp + pagina_da_visitare;
        nuovo_perc = new URL(tmp);
        
        VG.set_sito(nuovo_perc);
        CV.get_URL_SITO();
        CV.get_CARTELLA_SITO();
        System.out.printf("%nProssima pagina da visitare: %s%nclasse Loopper metodo nuova_pagina.%n", tmp);
        System.out.printf("Scaricheremo il file %s%nNella directory %s%n", VG.get_name_page(), VG.get_subdir());
    }
catch(MalformedURLException mue)
    {
        System.err.printf("Il percorso %s non è valido, si è verificato un errore: %s%n", to_visit, mue);
    }
}

private boolean esame_directory(String dir)
{
System.out.printf("%nCLASSE TrovaDirs, metodo esame_directory().%n");
/**
 * Questo metodo riceve una stringa e valuta se si tratta di una 
 * directory, a scopo di test valuteremo alcune caratteristiche
 * della riga passata come argomento prima di passare allla sua 
 * scrittura sul file delle directory
 */
    
boolean directory = false;
int n_elementi;
boolean contatore_file;
String nome_file = "";
String testo = "Nome di file o percorso non valido.";

Path riga = Paths.get(dir);
System.out.printf("Elaborazione di %s:\tNome: %s\tNumero di elementi: %d%n"
        + "\tPercorso: %s%n\tRadice: %s%n",riga.toString(), riga.getFileName(), riga.getNameCount()
        , riga.getParent(), riga.getRoot());

n_elementi = riga.getNameCount();

switch (n_elementi)
        {
            case 0:
                System.out.printf("%s",testo);
                contatore_file = false;
            break;
            case 1://se ha un elemento supponiamo che si tratti di un file
                    //prendiamo in considerazione una serie di stringhe non ammesse
                    //nella composizione del nome di un file*/
                    caratteri_vietati(riga.toString());
                    contatore_file = false;
            break;          
            default:
                    //System.out.printf("Costrutto switch, esecuzione di default.%n");
                    contatore_file = caratteri_vietati(riga.toString());
            break;
        }
        /*}*/
return contatore_file;
}

private boolean caratteri_vietati(String nome)
{
System.out.printf("%nCLASSE TrovaDirs, metodo caratteri_vietati().%n");
String nome_file = nome;    
//int index = -2;
boolean abeam_strada;
            if(nome_file.contains("https:"))
                {
                    /*System.out.println("Trovato la stringa \"https:\" in posizione " + nome_file.indexOf("https:"));
                    index= nome_file.indexOf("https:");*/
                    abeam_strada = false;
                }
            else if(nome_file.contains("?"))
                {
                    /*System.out.println("Trovato la stringa \"?\" in posizione " + nome_file.indexOf("?"));
                    index= nome_file.indexOf("?");*/
                    abeam_strada = false;
                }
            else if(nome_file.contains("="))
                {
                    /*System.out.println("Trovato la stringa \"=\" in posizione " + nome_file.indexOf("="));
                    index= nome_file.indexOf("=");*/
                    abeam_strada = false;
                }
            else if(nome_file.contains("&"))
                {
                    /*System.out.println("Trovato la stringa \"&\" in posizione " + nome_file.indexOf("&"));
                    index= nome_file.indexOf("&");*/
                    abeam_strada = false;
                }
            else if(nome_file.contains(";"))
                {
                    /*System.out.println("Trovato la stringa \";\" in posizione " + nome_file.indexOf(";"));
                    index= nome_file.indexOf(";");*/
                    abeam_strada = false;
                }
            else if(nome_file.contains("#"))
                {
                    /*System.out.println("Trovato la stringa \"#\" in posizione " + nome_file.indexOf("#"));
                    index= nome_file.indexOf("#");*/
                    abeam_strada = false;
                }
//            else if (index > -1)
  //              {
                    //nome_file = riga.getFileName().toString();
                    //System.out.printf("Questo non è un nome difile valido: %s", nome_file);
    //                abeam_strada = false;
      //          }
            else
                {
                    abeam_strada = true;
                }
            return abeam_strada;
}   
}