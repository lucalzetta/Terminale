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

public void scrivi()throws IOException
{
try
    {
        URL u = VG.get_sito();
        URLConnection uc = u.openConnection();
        java.io.InputStream in = uc.getInputStream();
        java.io.InputStream orig = new BufferedInputStream(in);
        Reader reader = new InputStreamReader(orig);
        File pagina = new File(DESTINAZIONE, NOME_PAGINA); 
        FileOutputStream fos = new FileOutputStream(pagina);
        
        int c;
        while ((c = reader.read()) != -1) 
        {
          
          fos.write(c);
          //System.out.print((char) c);
        }
    }
catch(FileNotFoundException fnf)
    {
        System.err.printf("Il file %s non è stato trovato dal metodo "
                + "scrivi della classe SalvaPagina().", fnf);
    }
}
}
