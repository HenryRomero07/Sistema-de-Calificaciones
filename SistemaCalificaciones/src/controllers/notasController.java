package controllers;

import java.io.IOException;

/**
 *
 * @author Usuario iTC
 */
public class notasController {

    private Utilidades u = new Utilidades();
    private String file_name = "notas.txt";

    public boolean guardar(int Cedula, String Nombres, String Apellidos, String Materia, float ACG1, float ACI1, float Examen1,
            float ACG2, float ACI2, float Examen2, float ACG3, float ACI3, float Examen3) {

        Float Promedio1 = Calcular_promedio(ACG1, ACI1, Examen1);
        Float Promedio2 = Calcular_promedio(ACG2, ACI2, Examen2);
        Float Promedio3 = Calcular_promedio(ACG3, ACI3, Examen3);

        String data = Cedula + "\t" + Nombres + "\t" + Apellidos + "\t" + Materia + "\t" + ACG1 + "\t" + ACI1 + "\t" + Examen1 + "\t" + Promedio1
                + "\t" + ACG2 + "\t" + ACI2 + "\t" + Examen2 + "\t" + Promedio2 + "\t" + ACG3 + "\t" + ACI3 + "\t" + Examen3 + "\t" + Promedio3 + "\n";
        try {
            u.save(data, file_name);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String[][] listar() {
        try {
            return u.listAll(file_name);
        } catch (Exception e) {
            System.out.println("Error en listar " + e);
            return null;
        }

    }

    /*
    public float Calcular_promedio(float Nota1, float Nota2, float Nota3) {
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

    public String[] promediar() {
        String[][] allData = listar();
        String[] parciales = new String[3];

        for (String[] row : allData) {
            //Notas promediadas Parcial 1
            float ACG1 = (float) Double.parseDouble(row[2]);
            float ACI1 = (float) Double.parseDouble(row[3]);
            float Exam1 = (float) Double.parseDouble(row[4]);

            float promedio1 = Calcular_promedio(ACG1, ACI1, Exam1);
            //Notas promediadas Parcial 2
            float ACG2 = (float) Double.parseDouble(row[5]);
            float ACI2 = (float) Double.parseDouble(row[6]);
            float Exam2 = (float) Double.parseDouble(row[7]);

            float promedio2 = Calcular_promedio(ACG2, ACI2, Exam2);
            //Notas promediadas Parcial 3
            float ACG3 = (float) Double.parseDouble(row[8]);
            float ACI3 = (float) Double.parseDouble(row[9]);
            float Exam3 = (float) Double.parseDouble(row[10]);

            float promedio3 = Calcular_promedio(ACG3, ACI3, Exam3);

            //Asignar notas 
            parciales[0] = float.toString(promedio1);
            parciales[1] = float.toString(promedio2);
            parciales[2] = float.toString(promedio3);

        } 

        return parciales;

    }*/
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
        String[][] allData = u.listAll(file_name);
        if (filaActualizar < 0 || filaActualizar >= allData.length) {
            System.out.println("Fila inválida");
            return false;
        }
        allData[filaActualizar][1] = nuevoNombre;

        if (nuevasNotas.length != 9) {
            System.out.println("Se esperan 9 notas");
            return false;
        }
        for (int i = 0; i < 9; i++) {
            allData[filaActualizar][2 + i] = nuevasNotas[i];
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
        String[][] allData = u.listAll(file_name);
        String[][] filteredData = new String[allData.length][10]; // nombre + 9 notas

        int filaDestino = 0;
        for (int i = 0; i < allData.length; i++) {
            if (allData[i] != null && allData[i].length >= 11) {
                filteredData[filaDestino][0] = allData[i][1]; // Nombres
                for (int j = 0; j < 9; j++) {
                    filteredData[filaDestino][j + 1] = allData[i][j + 2];
                }
                filaDestino++;
            }
        }

        String[][] resultado = new String[filaDestino][10];
        for (int i = 0; i < filaDestino; i++) {
            for (int j = 0; j < 10; j++) {
                resultado[i][j] = filteredData[i][j];
            }
        }

        return resultado;
    }

    public String[][] relistarEstudiante(String nombreEstudiante, String materia) throws IOException {
        String[][] allData = u.listAll(file_name);

        for (int i = 0; i < allData.length; i++) {
            if (allData[i] != null && allData[i].length >= 11 && allData[i][1].equalsIgnoreCase(nombreEstudiante)) {
                String[][] resultado = new String[1][10];
                resultado[0][0] = materia;

                for (int j = 0; j < 9; j++) {
                    resultado[0][j + 1] = allData[i][2 + j];
                }

                return resultado;
            }
        }

        return null;
    }
    
    
    
}
