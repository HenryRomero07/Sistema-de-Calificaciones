package controllers;

import utiles.Utiles;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author HenryRomero
 */
public class DocenteController {

    private Utiles u = new Utiles();

    String name_file = "estudiantes.txt";
    String name_fileVerificador = "Docentes.txt";
    // cedula, tipo de identificador, nombre, apellidos, telefono, correo, grado, materia1, materia2, materia3, materia4, materia5
     public boolean guardar(String  Cedula,String TipoID , String Nombres, String Apellidos, String Correo, String Grado, String materia1, String materia2 ,String materia3, String materia4, String materia5 ) {
        String data = Cedula + "\t" + TipoID + "\t" +   Nombres + "\t" +   Apellidos + "\t" +  Correo + "\t" +  Grado + "\t" +  materia1
                + "\t" +  materia2 + "\t" +  materia3 + "\t" +  materia4 + "\t" + materia5 + "\n";
        
        
        try {
            u.save(data, name_fileVerificador);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


//    public String[][] listar() throws IOException {
//        String[][] allData = u.listAll(name_file);
//        String[][] filteredData = new String[allData.length][6];
//        for (int i = 0; i < allData.length; i++) { 
//            filteredData[i][0] = allData[i][0]; // Cedula
//            filteredData[i][1] = allData[i][1]; // Nombres de estudiante
//            filteredData[i][2] = allData[i][3]; // Nota de Parcial 1
//            filteredData[i][3] = allData[i][4]; // Nota de Parcial 2
//            filteredData[i][4] = allData[i][5]; // Nota de Parcial 3
//            filteredData[i][5] = allData[i][6]; // Promedio
//        }
//        return filteredData;
//    }

    public String[][] listarPorMateria(String materia) throws IOException {
        String[][] allData = u.listAll(name_file);
        int count = 0;

        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {
                count++;
            }
        }

        String[][] filteredData = new String[count][7];
        
        DecimalFormat df = new DecimalFormat("#.###");
        
        int index = 0;
        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {
                
                Double unidad1 = Double.parseDouble(row[4]);
                Double unidad2 = Double.parseDouble(row[5]);
                Double unidad3 = Double.parseDouble(row[6]);
                Double promedio = ((unidad1 + unidad2 + unidad3)/3);
                
                filteredData[index][0] = row[1]; // Nombres del estudiante
                filteredData[index][1] = row[4]; // Nota de unidad 1
                filteredData[index][2] = row[5]; // Nota de unidad 2
                filteredData[index][3] = row[6]; // Nota de unidad 3
                filteredData[index][4] =  df.format(promedio); // Promedio 
                index++;
            }
        }
        
        
        
        return filteredData;
    }

    
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);
       DocenteController d = new DocenteController();
       
       
        for (int i = 0; i < 3; i++) {
            System.out.println("Cedula: ");
       String Cedula = sc.nextLine();
       
       System.out.println("Tipo de identificación: ");
       String TipoID = sc.nextLine() ;
       
       System.out.println("Nombres: ");
       String Nombres = sc.nextLine();
       
       System.out.println("Apellidos: ");
       String Apellidos = sc.nextLine();
       
       System.out.println("Correo: ");
       String Correo = sc.nextLine();
       
       System.out.println("Grado: ");
       String Grado = sc.nextLine();
       
       System.out.println("1ra materia: ");
       String materia1 = sc.nextLine();
       
       System.out.println("2da materia: ");
       String materia2 = sc.nextLine();
       
       System.out.println("3ra materia: ");
       String materia3 = sc.nextLine();
       
       System.out.println("4ta materia: ");
       String materia4 = sc.nextLine();
       
       System.out.println("5ta materia: ");
       String materia5 = sc.nextLine();
       
            if (d.guardar(Cedula, TipoID, Nombres, Apellidos, Correo, Grado, materia1, materia2, materia3, materia4, materia5)) {
                System.out.println("Guardado correctamente");
            }
        }
       
               
       
//       String[][] filteredData = d.listarPorMateria("Matematicas");
//        
//       
//       for (String[] strings : filteredData) {
//            for (String string : strings) {
//                System.out.println(string + "\t");
//            }
//            System.out.println();
//        }
    }
    
}