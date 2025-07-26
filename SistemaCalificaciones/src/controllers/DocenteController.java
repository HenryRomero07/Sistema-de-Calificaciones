package controllers;

import enums.Curso;
import enums.TipoIdentificacion;
import java.io.IOException;
import java.text.DecimalFormat;
import utiles.Utilidades;

/**
 *
 * @author HenryRomero
 */
public class DocenteController {

    private Utilidades u = new Utilidades();
    private AlumnosController alum = new AlumnosController();
    String name_file = "estudiantes";
    String name_fileVerificador = "Docentes";

    public boolean guardar(String Cedula, TipoIdentificacion TipoID, String Nombres, String Apellidos, String Correo, Curso Grado, String materia1, String materia2, String materia3, String materia4, String materia5) {
        String data = Cedula + "\t" + TipoID + "\t" + Nombres + "\t" + Apellidos + "\t" + Correo + "\t" + Grado + "\t" + materia1
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
            return null;
        }
    }

    public String[][] listarPorMateria(String materia) throws IOException {
        String[][] allData = alum.listar();
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

}
