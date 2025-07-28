package controllers;

import enums.Curso;
import enums.TipoIdentificacion;
import java.io.IOException;
import java.text.DecimalFormat;
import utiles.Utilidades;
import java.util.Arrays;


/**
 *
 * @author HenryRomero
 */
public class DocenteController {

    private Utilidades u = new Utilidades();
    private notasController nota = new notasController();
    String name_file = "estudiantes";
    String name_fileVerificador = "Docentes";

    public boolean guardar(String Cedula, TipoIdentificacion TipoID, String Nombres, String Apellidos, String Telefono, String Correo, Curso Grado, String materia1, String materia2, String materia3, String materia4, String materia5) {
        String data = Cedula + "\t" + TipoID + "\t" + Nombres + "\t" + Apellidos + "\t" + Telefono + "\t" + Correo + "\t" + Grado + "\t" + materia1
                + "\t" + materia2 + "\t" + materia3 + "\t" + materia4 + "\t" + materia5 + "\n";

        try {
            u.save(data, name_fileVerificador);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String[][] listar() {
        try {
            return u.listAll(name_fileVerificador);
        } catch (Exception e) {
            System.out.println("Error en listar: " + e);
            return null;
        }
    }

    public String[][] listarEstudiantesPorDocente(String correoDocente) throws IOException {
        String[][] docentes = listar(); // modulo de Docentes
        String[][] estudiantes = u.listAll(name_file);//modulo de Alumnos
        String[][] notas = nota.listar(); // modulo de chamba

        String gradoDocente = null;

        for (String[] row : docentes) {
            if (row[5].equalsIgnoreCase(correoDocente)) {
                gradoDocente = row[6];
                break;
            }
        }

        if (gradoDocente == null) {
            return new String[0][0];
        }

        int countEst = 0;
        for (String[] row : estudiantes) {
            if (row[6].equalsIgnoreCase(gradoDocente)) {
                countEst++;
            }
        }

        if (countEst == 0) {
            return new String[0][0];
        }

        String[] cedulas = new String[countEst];
        int pos = 0;
        for (String[] row : estudiantes) {
            if (row[6].equalsIgnoreCase(gradoDocente)) {
                cedulas[pos] = row[0];
                pos++;
            }
        }

        int countNotas = 0;
        for (String[] row : notas) {
            for (int i = 0; i < cedulas.length; i++) {
                if (row[0].equalsIgnoreCase(cedulas[i])) {
                    countNotas++;
                    break;
                }
            }
        }

        if (countNotas == 0) {
            return new String[0][0];
        }

        String[][] resultado = new String[countNotas][notas[0].length];
        int index = 0;
        for (String[] row : notas) {
            for (int i = 0; i < cedulas.length; i++) {
                if (row[0].equalsIgnoreCase(cedulas[i])) {
                    resultado[index] = row;
                    index++;
                    break;
                }
            }
        }

        return resultado;
    }

    public String[][] listarPorMateria(String materia, String correoDocente) throws IOException {
        String[][] allData = listarEstudiantesPorDocente(correoDocente);
        int count = 0;

        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {
                count++;
            }
        }

        if (count == 0) {
            return new String[0][0];
        }

        String[][] filteredData = new String[count][7];

        DecimalFormat df = new DecimalFormat("#.###");

        int index = 0;
        for (String[] row : allData) {
            if (row[3].equalsIgnoreCase(materia)) {

                Double unidad1 = Double.parseDouble(row[4]);
                Double unidad2 = Double.parseDouble(row[5]);
                Double unidad3 = Double.parseDouble(row[6]);
                Double promedio = ((unidad1 + unidad2 + unidad3) / 3);

                filteredData[index][0] = row[1]; // Nombres del estudiante
                filteredData[index][1] = row[4]; // Nota de unidad 1
                filteredData[index][2] = row[5]; // Nota de unidad 2
                filteredData[index][3] = row[6]; // Nota de unidad 3
                filteredData[index][4] = df.format(promedio); // Promedio 
                index++;
            }
        }

        return filteredData;
    }

    public String[] obtenerMateriasDocente(String correoDocente) throws IOException {
        String[][] docentes = listar();

        for (String[] row : docentes) {
            if (row[4].equalsIgnoreCase(correoDocente)) {
                return new String[]{row[7], row[8], row[9], row[10], row[11]};
            }
        }
        return new String[0];
    }

    public static void main(String[] args) {
        try {
            DocenteController controller = new DocenteController();

            // Correo de prueba
            String correo = "henryromero@unl.edu.ec";

            // Llamamos al método
            String[][] resultado = controller.listarEstudiantesPorDocente(correo);

            System.out.println("Total filas devueltas: " + resultado.length);
            for (String[] fila : resultado) {
                System.out.println(Arrays.toString(fila));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

