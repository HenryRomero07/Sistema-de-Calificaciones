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
public class ModeloRegistroAdministrador extends AbstractTableModel {
    private String[][] data;

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //return data[rowIndex][columnIndex];
        switch (columnIndex) {
            case 0:                
                return data[rowIndex][0];
            case 1:
                return data[rowIndex][1];
            case 2:
                return data[rowIndex][2];
            case 3:
                return data[rowIndex][3];
            case 4:
                return data[rowIndex][4];
            case 5:
                return data[rowIndex][5];
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
