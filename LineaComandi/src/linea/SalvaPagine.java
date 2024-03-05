/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;
/**
 *
 * @author luca
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
/**
 * 
 * @author luca
 * Questa classe salva la pagina corrente nel file locale di destinazione
 * la chiamata a monte di questa classe dovrà aver creato le directory di 
 * destinazione.
 */
public class SalvaPagine 
{
private final String DESTINAZIONE;
private static String NOME_PAGINA;
private static boolean NOME_VALIDO;
private final VariabiliGlobali VG = new VariabiliGlobali();
private static CheckerVariabili CV = new CheckerVariabili();
private Set<String> SET_LINKS_VISITATI = VG.get_set_scaricati();
private TrovaDirs TD;

public SalvaPagine(String nome_pagina)throws IOException
{
//System.out.printf("%nCLASSE SalvaPagine, costruttore di default.%n");
DESTINAZIONE = VG.get_root();
NOME_PAGINA = nome_pagina.strip();
TD = new TrovaDirs();
NOME_VALIDO = TD.caratteri_vietati(nome_pagina);
}
public SalvaPagine(String dir, String nome_pagina)throws IOException
{
//System.out.printf("%nCLASSE SalvaPagine, costruttore con parametri.%n");
DESTINAZIONE = dir;
NOME_PAGINA = nome_pagina.strip();
TD = new TrovaDirs();
NOME_VALIDO = TD.caratteri_vietati(nome_pagina);
}

public void scrivi(boolean testo)throws IOException
{
//System.out.printf("%nCLASSE SalvaPagine, metodo scrivi().%n");
int stop = 0;    
if(NOME_VALIDO)
    {
        OutputStream fos = null;
        InputStreamReader reader = null;
        java.io.InputStream in = null;
        try
            {
                System.out.printf("%nClasse SalvaPagine, metodo scrivi(),%n%n"
                        + "destinazione del file: %s%n"
                        + "Nome del file: %s%n"
                        + "Origine del file: %s%n%n", DESTINAZIONE, NOME_PAGINA.strip(), VG.get_sito());
                URL u = VG.get_sito();
                URLConnection uc = u.openConnection();
                stop = uc.getContentLength();
                in = uc.getInputStream();
                //java.io.BufferedInputStream orig = new BufferedInputStream(in);
                //reader = new InputStreamReader(in);
                
                File pagina = new File(DESTINAZIONE, NOME_PAGINA); 
                fos = new FileOutputStream(pagina);
                String str_pagina = "";
                StringBuilder build_pagina = new StringBuilder();
                int c = 0;
                int contatore = 0;
                System.out.printf("%nClasse SalvaPagine, metodo scrivi(),%n"
                        + "%nIl file %s contiene %d bytes%n", NOME_PAGINA, stop);
                if(testo)
                    {
                        while ((c = in.read()) != -1) 
                            {
                               fos.write(c);
                               str_pagina = str_pagina + (char)c;
                               build_pagina.append((char)c);
                                //System.out.print((char) c);
                                contatore ++;
                            }
                    }
                else
                    {
                        while ((c = in.read()) != -1) 
                            {          
                                fos.write(c);
                                contatore ++;
                            }
                    }
                System.out.printf("%nClasse SalvaPagine, metodo scrivi(),%n%n"
                        + "letti: %d byte dal file %s%n",contatore , NOME_PAGINA.strip());
                
                    
                //Aggiorniamo il set dei links visitati
                if(VG.get_subdir() != null)
                    {
                        this.SET_LINKS_VISITATI.add(VG.get_subdir() + NOME_PAGINA.strip());
                    }
                else
                    {
                        this.SET_LINKS_VISITATI.add("" + NOME_PAGINA.strip());
                    }
                    
                /**
                * tratto  di codice di debug per una prima valutazione dei metadati
                * del file tentiamo una lettura dei primi 200 caratteri
                */
                if (! VG.get_testo())
                    {
                        //System.out.printf("%nClasse SalvaPagine, metodo scrivi(), il file "
                                //+ "%s non è un fie di testo.%n", NOME_PAGINA);
                    }
                else
                    {
                        VG.set_page(str_pagina);
                        VG.set_page_builder(build_pagina);
                        /*System.out.printf("%nCLasse SalvaPagine, metodo scrivi(), risultato "
                                        + "della lettura dei primi caratteri del file:%n%n");
                        for (int i = 0; i < 300; i++)
                            {
                                System.out.printf("%s", build_pagina.charAt(i));
                            }
                        System.out.printf("%n%n%n");*/
                    }
                /**
                 * Fine del codice di debug
                 */
            }
        catch(FileNotFoundException fnf)
            {
                System.err.printf("Il file %s non è stato trovato dal metodo "
                        + "scrivi della classe SalvaPagina().", fnf);
            }
        finally
            {
                if(fos != null)
                    {
                    try
                        {
                            fos.close();
                        }
                    catch(IOException ioe)
                        {
                        
                        }
                    }
                if(in != null)
                    {
                    try
                        {
                            in.close();
                        }
                    catch(IOException ioe)
                        {
                        
                        }
                    }

            }
    }
}
}
