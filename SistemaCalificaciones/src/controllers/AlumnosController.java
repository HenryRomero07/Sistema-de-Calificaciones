/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.Curso;
import enums.TipoIdentificacion;
import utiles.Utilidades;

public class AlumnosController {

    private Utilidades util = new Utilidades();
    private notasController nota = new notasController();
    private String name_file = "estudiantes";

    public boolean guardar(String Cedula, TipoIdentificacion TipoID, String Nombres, String Apellidos, String Telefono, String Correo, Curso Grado) {
        String data = Cedula + "\t" + TipoID + "\t" + Nombres + "\t" + Apellidos + "\t" + Telefono + "\t" + Correo + "\t" + Grado + "\n";
        try {
            util.save(data, name_file);
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar estudiante: " + e);
            return false;
        }
    }

    public String[][] listar() {
        try {
            return util.listAll(name_file);
        } catch (Exception e) {
            System.out.println("Error en listar: " + e);
            return null;
        }
    }

    public String[] buscarPorcorreo(String Correo) {
        try {
            String[][] todos = listar();
            if (todos != null) {
                String[] usuar = new String[todos[0].length];
                Integer cont = -1;
                for (int i = 0; i < todos.length; i++) {
                    if (Correo.equals(todos[i][5])) {
                        cont = i;
                        break;
                    }
                }
                if (cont >= 0) {
                    for (int i = 0; i < usuar.length; i++) {
                        usuar[i] = todos[cont][i];
                    }
                } else {
                    usuar = null;
                }
                return usuar;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error al buscar: " + e);
            return new String[0];
        }
    }

    public String[][] buscarNotasPorCedula(String Cedula) {
        try {// agragar arreglo
            String[][] Notas = nota.listar();
            int count = 0;
            for (int i = 0; i < Notas.length; i++) {
                if (Notas[i][0].equalsIgnoreCase(Cedula)) {
                    count++;
                }
            }
            String[][] notasFiltradas = new String[count][4];
            int pos = 0;
            for (int i = 0; i < Notas.length; i++) {
                if (Notas[i][0].equalsIgnoreCase(Cedula)) {
                    notasFiltradas[pos] = new String[4];
                    notasFiltradas[pos][0] = Notas[i].length > 3 ? Notas[i][3] : "";
                    notasFiltradas[pos][1] = Notas[i].length > 6 ? Notas[i][6] : "0";
                    notasFiltradas[pos][2] = Notas[i].length > 10 ? Notas[i][10] : "0";
                    notasFiltradas[pos][3] = Notas[i].length > 14 ? Notas[i][14] : "0";
                    pos++;
                }
            }
            return notasFiltradas;
        } catch (Exception e) {
            System.out.println("Error al buscar nostas por cedula: " + e);
            return new String[0][];
        }
    }
}
