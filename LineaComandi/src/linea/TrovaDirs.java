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
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrovaDirs 
{
private static String TESTO;
private final VariabiliGlobali VG = new VariabiliGlobali();
private final String ROOT;
private final String CARTELLA_SITO;
private final File FILE_OUT;
private final File FILE_IN;

public TrovaDirs()throws IOException
{
VG.set_files();//Questo garantisce anche la creazione dei file se non 
               //dovessero esistere
ROOT = VG.get_root();
FILE_OUT = VG.get_file_DIRS();
FILE_IN = VG.get_file_URLS();
CARTELLA_SITO = VG.get_destinazione_files_sito();
}

public String cerca_dire()throws IOException
{
String tmp = "";
String riga = "";
String u_perc = "";
Set <String> ts = VG.get_set_collegamenti();
/**
 * Questo metodo esplora il contenuto del file in cui vengono
 * archiviati i collegamenti del sito e trasforma il loro percorso
 * in directory locali
 */
try
    {
        FileInputStream fis = new FileInputStream(FILE_IN);
        boolean perc;
        ArchivioDIRS ad = new ArchivioDIRS();
        int c = 0;
        int limit = 0;//limitatore di esecuzioni per il debug
        while((c != -1) & (limit < 50))//fino alla fine del file
            {
                //System.out.printf("%n****************************************************%n");                
                System.out.printf("Ciclo N° %d %s%n", limit,(char)c); 
                while ((c != 10) & (c != -1))//fino alla fine della riga
                    {
                        c = fis.read();
                        riga = riga + (char)c;
                    }
                
                perc = esame_directory(riga);
                if(perc)
                    {
                        /*System.out.printf("????????????????????????????????????????????????\n"
                                + "Il percorso %s potrebbe contenere una directory\n"
                                + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n", riga);*/
                    try
                        {
                            //URL u_perc = new URL(CARTELLA_SITO + riga);
                            u_perc = CARTELLA_SITO + riga;
                            File f_perc = new File(u_perc);
                            //System.out.printf("%nPercorso da creare: %s%n",u_perc);
                            Path percorso = Paths.get(u_perc);
/**************************************************************************************************
 * Verificare la presenza di caratteri vietati prima di procedere alla creazione delle directory. *
 *Eliminare i possibili nomi di file dalla stringa del percorso.                                  *
 *Aggiungere il carattere di fine linea prima della scrittura sul file ArchivioDIRS.txt           *
 **************************************************************************************************/                            
                            //percorso = percorso.getParent();
                            boolean dir = (new File(u_perc).mkdirs());//crea la cartella, resta false 
                                                       //se l'operazione fallisce
                            if(dir)
                                {
                                    System.out.println("Cartella creata: " + u_perc);
                                }
                            else
                                {
                                    /*System.out.println("La cartella : " + u_perc + ""
                                                + " NON è stata creata, VERIFICARE l'errore!");*/
                                }
                            ad.set_origin(percorso.getParent().toString());
                            ad.scrivi_su_file();
                        }
                    catch(MalformedURLException mue)
                        {
                            System.err.printf("Errore nel metodo cerca_dire della classe TrovaDirs: ", mue);
                        }
                    }
           /** Tratto di codice di test per la scrittura dei dati in una variabile 
           * di tipo List, in seguito questo ottimizzerà anche la scrittura dei file.
           * 05/11/2023
           */  
            /********************************************************************/
                ts.add(riga + "\n");
                System.out.printf("Valore di 'riga' in TrovaDirs.cerca_dire(): %s%n", riga);
                tmp = tmp + riga;
                riga = "";
                limit ++;
                c ++;
            }
         /** Tratto di codice di test per la scrittura dei dati in una variabile 
           * di tipo List, in seguito questo ottimizzerà anche la scrittura dei file.
           * 05/11/2023
           */  
            /********************************************************************/
        System.out.printf("####################################%n%h%n", '/'); //47 
        System.out.printf("%n----------------------------------------------------------------------%n"
                + "Classe TrovaDirs, metodo cerca_dire(), lettura della lista dei link%n");
        for (String t : ts)
            {
                System.out.printf("%s", t);
            }
        System.out.printf("%n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n"
                + "Classe TrovaDirs, metodo cerca_dire(), fine lettura della lista dei link%n");
        /***********************************************************************/
    }
catch(IOException ioe)
    {
        System.err.printf("Il file %s non è stato trovato.", FILE_IN.getName());
    }
return tmp;
}

private boolean esame_directory(String dir)
{
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
/*System.out.printf("Elaborazione di %s:\tNome: %s\tNumero di elementi: %d%n"
        + "\tPercorso: %s%n\tRadice: %s%n",riga.toString(), riga.getFileName(), riga.getNameCount()
        , riga.getParent(), riga.getRoot());*/
n_elementi = riga.getNameCount();

switch (n_elementi)
        {
            case 0:
//                System.out.printf("%s",testo);
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
String nome_file = nome;    
int index = -2;
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
            else if (index > -1)
                {
                    //nome_file = riga.getFileName().toString();
                    //System.out.printf("Questo non è un nome difile valido: %s", nome_file);
                    abeam_strada = false;
                }
            else
                {
                    abeam_strada = true;
                }
            return abeam_strada;
}   
}