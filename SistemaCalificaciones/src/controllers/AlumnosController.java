/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import utiles.Utilidades;
public class AlumnosController {
    private Utilidades util = new Utilidades();
       private String archivo_alumnos = "Notas_alumnos";
       private String archivo_notas = "asg_notas";
        public boolean guardar(String Cedula, String Nombres, String Apellidos, String Telefono, String Correo, String Curso){
            String data = Cedula + "\t" + Nombres + "\t" + Apellidos + "\t" + Telefono + "\t" + Correo + "\t" + Curso + "\n";
            try {
                util.save(data,"Notas_alumnos");
                return true;
            } catch (Exception e){
                System.out.println("Error al guardar estudiante: " + e);
                return false; 
            }
        }
        public String [][] listar (){
            try {
                return util.listAll(archivo_alumnos);
            } catch (Exception e) {
                System.out.println("Error en listar: " + e);
                return new String [0][];
            }
        }
        public String [][] buscarPorcorreo (String Correo){
        try {
            String [][] todos = util.listAll(archivo_alumnos);// listar de la misma 
            
            int count = 0;
            for (int i = 0; i < todos.length; i++){
                if(todos[i][4].equalsIgnoreCase(Correo)){
                    count ++;
                }
            }
            String [][] filtrado = new String [count][];
            int pos = 0;
            for (int i = 0; i < todos.length; i ++){
                if (todos [i][4].equalsIgnoreCase(Correo)){
                    filtrado[pos++] = todos [i];
                }
            }
            return filtrado;
        } catch (Exception e){
            System.out.println("Error al buscar: " + e);
            return new String [0][];
        }
    }
        public String [][] buscarNotasPorCedula (String Cedula){
            try {// agragar arreglo
                String [][] Notas = util.listAll(archivo_notas);//modulo de notas
                int count = 0;
                for(int i = 0; i < Notas.length; i++){
                    if(Notas[i][0].equalsIgnoreCase(Cedula)){
                        count ++;
                    }
                }
                String [][] notasFiltradas = new String [count][4];
                int pos = 0;
                for (int i = 0; i < Notas.length; i++){
                    if(Notas[i][0].equalsIgnoreCase(Cedula)){
                        notasFiltradas[pos] = new String[4];
                        notasFiltradas[pos][0] = Notas[i].length > 2 ? Notas [i][2]: "";
                        notasFiltradas[pos][1] = Notas[i].length > 6 ? Notas [i][6]: "0";
                        notasFiltradas[pos][2] = Notas[i].length > 10 ? Notas [i][10]: "0";
                        notasFiltradas[pos][3] = Notas[i].length > 14 ? Notas [i][14]: "0";
                        pos++;
                    }
                }
                return notasFiltradas;
            } catch (Exception e) {
                System.out.println("Error al buscar nostas por cedula: " + e);
                return new String [0][];
            }
        }
}
