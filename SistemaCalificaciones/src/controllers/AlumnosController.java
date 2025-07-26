/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import utiles.Utilidades;
public class AlumnosController {
    private Utilidades util = new Utilidades();
    private String archivo = "notas_estudiantes";
       private String archivo_alumnos = "Notas_alumnos";
        public boolean guardar(String Cedula, String Nombres, String Apellidos, String Telefono, String Correo, String Curso, String [] Materias){
            String data = Cedula + "\t" + Nombres + "\t" + Apellidos + "\t" + Telefono + "\t" + Correo + "\t" + Curso + "\t" + Materias + "\n";
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
                System.out.println("Error en listar" + e);
                return null;
            }
        }
        public String [][] buscarPorcorreo (String Correo){
        try {
            String [][] todos = util.listAll(archivo);
            
            int count = 0;
            for (int i = 0; i < todos.length; i++){
                if(todos[i][2].equalsIgnoreCase(Correo)){
                    count ++;
                }
            }
            String [][] filtrado = new String [count][];
            int pos = 0;
            for (int i = 0; i < todos.length; i ++){
                if (todos [i][2].equalsIgnoreCase(Correo)){
                    filtrado[pos++] = todos [i];
                }
            }
            return filtrado;
        } catch (Exception e){
            System.out.println("Error al buscar: "+ e);
            return new String [0][];
        }
    }
}
