package controllers;

public class MateriasController {

    public String agregarMaterias(String[] seleccionadas) {
        if (seleccionadas == null || seleccionadas.length != 5) {
            return "Debe seleccionar 5 materias.\n";
        }

        for (int i = 0; i < seleccionadas.length; i++) {
            String materia = seleccionadas[i];

            for (int j = i + 1; j < seleccionadas.length; j++) {
                if (materia.equals(seleccionadas[j])) {
                    return "No se pueden repetir materias.\n";
                }
            }
        }

        StringBuilder resultado = new StringBuilder("Materias seleccionadas:\n");
        for (String materia : seleccionadas) {
            resultado.append("- ").append(materia).append("\n");
        }
        resultado.append("\n");
        return resultado.toString();
    }
}
