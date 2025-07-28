/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

/**
 *
 * @author franz
 */
public class materia {
    private static String notas;
    private static String[] enviar;
    public static String[] materias;
    

    public static String[] getMaterias() {
        return materias;
    }

    public static void setMaterias(String[] materias) {
        materia.materias = materias;
    }

    public static String[] getEnviar() {
        return enviar;
    }

    public static void setEnviar(String[] enviar) {
        materia.enviar = enviar;
    }

    public static String getNotas() {
        return notas;
    }

    public static void setNotas(String notas) {
        materia.notas = notas;
    }
    
}
