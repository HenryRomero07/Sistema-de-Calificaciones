package modeloTabla;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Usuario iTC
 */
public class mt_asignacion extends AbstractTableModel {

    private String data[][];
    private String header[] = {"Nombres","Apellidos", "ACG1","ACI1","Examen1","ACG2","ACI2","Examen2","ACG3","ACI3","Examen3"};

   
    public String[][] getData() {
        return data;
    }

    public void setData(String[][] Data) {
        this.data = Data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return (data == null) ? 0 : data.length;
    }

    @Override
    public int getColumnCount() {

        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {

        return header[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object valor, int row, int column) {
        data[row][column] = valor.toString();
        fireTableCellUpdated(row, column);
    }
}
