/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloTabla;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author franz
 */
public class ModeloRegistroDocente extends AbstractTableModel {
    private String[][] docentes;

    public String[][] getDocentes() {
        return docentes;
    }

    public void setDocentes(String[][] docentes) {
        this.docentes = docentes;
    }
    
    @Override
    public int getRowCount() {
        return docentes.length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
            case 0:                
                return docentes[rowIndex][0];
            case 1:
                return docentes[rowIndex][1];
            case 2:
                return docentes[rowIndex][2];
            case 3:
                return docentes[rowIndex][3];
            case 4:
                return docentes[rowIndex][4];
            case 5:
                return docentes[rowIndex][5];
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:                
                return "Cedula";
            case 1:
                return "tipo de identificacion";
            case 2:
                return "Nombres";
            case 3:
                return "Apellidos";
            case 4:
                return "Telefono";
            case 5:
                return "Correo";
            default:
                return null;
        }
    
    }
}
