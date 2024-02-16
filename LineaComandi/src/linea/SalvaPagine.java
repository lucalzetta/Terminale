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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
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
private final VariabiliGlobali VG = new VariabiliGlobali();


public SalvaPagine(String nome_pagina)
{
DESTINAZIONE = VG.get_destinazione_files_sito();
NOME_PAGINA = nome_pagina;
}
public SalvaPagine(String dir, String nome_pagina)
{
DESTINAZIONE = dir;
NOME_PAGINA = nome_pagina;
}

public void scrivi(boolean testo)throws IOException
{
try
    {
        System.out.printf("%nClasse SalvaPagine, metodo scrivi(),"
                + " destinazione del file: %s%nNome del file: %s%n%n", DESTINAZIONE, NOME_PAGINA);
        URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream orig = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(orig);
        File pagina = new File(DESTINAZIONE, NOME_PAGINA); 
        FileOutputStream fos = new FileOutputStream(pagina);
        String str_pagina = "";
        StringBuilder build_pagina = new StringBuilder();
        int c;
        if(testo)
            {
                while ((c = reader.read()) != -1) 
                    {
          
                        fos.write(c);
                        str_pagina = str_pagina + (char)c;
                        build_pagina.append((char)c);
                        //System.out.print((char) c);
                    }
            }
        else
            {
                while ((c = reader.read()) != -1) 
                    {          
                        fos.write(c);
                    }
            }
        
        /**
         * tratto  di codice di debug per una prima valutazione dei metadati
         * del file tentiamo una lettura dei primi 200 caratteri
         */
        if (! VG.get_testo())
            {
                System.out.printf("%nClasse SalvaPagine, metodo scrivi(), il file "
                        + "%s non è un fie di testo.%n", NOME_PAGINA);
            }
        else
            {
                VG.set_page(str_pagina);
                VG.set_page_builder(build_pagina);
                System.out.printf("%nCLasse SalvaPagine, metodo scrivi(), risultato "
                                + "della lettura dei primi caratteri del file:%n%n");
                for (int i = 0; i < 300; i++)
                    {
                        System.out.printf("%s", build_pagina.charAt(i));
                    }
                System.out.printf("%n%n%n");
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
}
}
