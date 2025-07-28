package controllers;

import java.io.IOException;
import utiles.Utilidades;

/**
 *
 * @author Usuario iTC
 */
public class notasController {

    private Utilidades u = new Utilidades();
    //private DocenteController doc = new DocenteController();
    private String file_name = "notas";
    private String name_fileVerificador = "Docentes";
    //Metodo para duplicar por materia 
    public Boolean guardar(String Cedula, String Nombres, String Apellidos, String Grado) throws IOException {
        boolean band = false;
        try {
            String[] materia = new String[5];
            String[][] docentes = u.listAll(name_fileVerificador);//modulo de docentes
            for (int i = 0; i < docentes.length; i++) {
                if (docentes[i][6].equalsIgnoreCase(Grado)) {
                    materia[0] = docentes[i][7];
                    materia[1] = docentes[i][8];
                    materia[2] = docentes[i][9];
                    materia[3] = docentes[i][10];
                    materia[4] = docentes[i][11];
                }
            }
            for (int i = 0; i < materia.length; i++) {
                String Materia = materia[i];
                String data = Cedula + "\t" + Nombres + "\t" + Apellidos + "\t" + Materia + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0"
                        + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\t" + "0.0" + "\n";
                u.save(data, file_name);
                band = true;
            }
        } catch (Exception e) {
            System.out.println("Error al guardar " + e);
            band = false;
        }
        return band;
    }

    public String[][] listar() {
        try {
            return u.listAll(file_name);
        } catch (Exception e) {
            System.out.println("Error en listar: " + e);
            return null;
        }
    }

    public Float Calcular_promedio(float Nota1, float Nota2, float Nota3) {
        if (validacion(Nota1) && validacion(Nota2) && validacion(Nota3)) {
            float Notafinal = (Nota1 + Nota2 + Nota3) / 3;
            return Notafinal;
        } else {
            return null;
        }
    }

    public boolean validacion(float nota) {
        return nota >= 0 && nota <= 10;
    }

    public boolean actualizarNombreYNotas(int filaActualizar, String nuevoNombre, String[] nuevasNotas) throws IOException {
        String[][] allData = listar();// cambiar por listar
        if (filaActualizar < 0 || filaActualizar >= allData.length) {
            System.out.println("Fila inválida");
            return false;
        }
        allData[filaActualizar][1] = nuevoNombre;

        if (nuevasNotas.length != 9) {
            System.out.println("Se esperan 9 notas");
            return false;
        }
        for (int i = 1; i < 9; i++) {
            allData[filaActualizar][3 + i] = nuevasNotas[i];
        }

        StringBuilder contenidoNuevo = new StringBuilder();
        for (int i = 0; i < allData.length; i++) {
            for (int j = 0; j < allData[i].length; j++) {
                contenidoNuevo.append(allData[i][j]);
                if (j < allData[i].length - 1) {
                    contenidoNuevo.append("\t");
                }
            }
            contenidoNuevo.append("\n");
        }

        u.actualizar(contenidoNuevo.toString(), file_name);
        return true;
    }

    public String[][] relistar() throws IOException {
        String[][] allData = listar();//listar
        String[][] filteredData = new String[allData.length][11]; // nombre + 9 notas

        int filaDestino = 0;
        for (int i = 0; i < allData.length; i++) {
            if (allData[i] != null && allData[i].length >= 12) {
                filteredData[filaDestino][0] = allData[i][1]; // Nombres
                filteredData[filaDestino][1] = allData[i][2]; // Apellido
                for (int j = 1; j < 10; j++) {
                    filteredData[filaDestino][j + 1] = allData[i][j + 3];
                }
                filaDestino++;
            }
        }

        String[][] resultado = new String[filaDestino][11];
        for (int i = 0; i < filaDestino; i++) {
            for (int j = 0; j < 11; j++) {
                resultado[i][j] = filteredData[i][j];
            }
        }

        return resultado;
    }

    public String[][] relistarEstudiante(String cedula) throws IOException {
        String[][] allData = listar();
        int count = 0;
        for (int i = 0; i < allData.length; i++) {
            if (allData[i] != null && allData[i].length >= 13 && allData[i][0].equals(cedula)) {
                count++;
            }
        }
        if (count == 0) {
            return null;
        }
        String[][] resultado = new String[count][10];
        int indice = 0;
        for (int i = 0; i < allData.length; i++) {
            if (allData[i] != null && allData[i].length >= 13 && allData[i][0].equals(cedula)) {
                resultado[indice][0] = allData[i][3];
                for (int j = 0; j < 9; j++) {
                    resultado[indice][j + 1] = allData[i][4 + j];
                }
                indice++;
            }
        }
        return resultado;
    }

    public String[][] listarPorMateria(String materia) throws IOException {
        String[][] allData = listar();//listar
        int count = 0;
        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {
                count++;
            }
        }
        String[][] filteredData = new String[count][11];
        int index = 0;
        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {
                filteredData[index][0] = row[1]; // Nombre
                filteredData[index][1] = row[2]; // Apellido
                filteredData[index][2] = row[4]; // ACG1
                filteredData[index][3] = row[5]; // ACI1
                filteredData[index][4] = row[6]; // Exam1
                filteredData[index][5] = row[8]; // ACG2
                filteredData[index][6] = row[9]; // ACI2
                filteredData[index][7] = row[10]; // Exam2
                filteredData[index][8] = row[12]; // ACG3
                filteredData[index][9] = row[13]; // ACI3
                filteredData[index][10] = row[14]; // Exam3
                index++;
            }
        }
        return filteredData;
    }

}
