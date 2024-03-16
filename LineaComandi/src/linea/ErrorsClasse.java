/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author lucaamministratore
 */
public class ErrorsClasse 
{
/**
 * Questa classe si propone di gestire gli errori dell'appicazione e dirigerli
 * verso file di log, outputstream o un uso consapevole di printf.
 */
private final VariabiliGlobali VG = new VariabiliGlobali();
private static String STR_ERROR;

public ErrorsClasse()
{
STR_ERROR = "";
}

public ErrorsClasse(String errore)
{
STR_ERROR = errore;
}

public void set_msg(String msg)
{
STR_ERROR = msg;
}

public String get_msg()
{
return STR_ERROR;
}

public void scrivi_su_file()
{
File ERRORI = VG.get_file_ERR();
byte[] b_msg;
try
    {
        b_msg = STR_ERROR.getBytes();
        FileOutputStream fw = new FileOutputStream(ERRORI, true);
        fw.write(b_msg);
    }
catch(IOException ioe)
    {
        System.err.printf("%nErrore nella classe ErrorsClasse, metodo scrivi_su_file()%n"
                + "%s%n", ioe);
    }
}
}
