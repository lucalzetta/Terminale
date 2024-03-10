/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linea;

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
private final String STR_ERROR;

public ErrorsClasse()
{
STR_ERROR = "";
}

public ErrorsClasse(String errore)
{
STR_ERROR = errore;
}
}
