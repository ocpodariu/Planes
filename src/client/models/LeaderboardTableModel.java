package client.models;

import javax.swing.table.AbstractTableModel;


public class LeaderboardTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Username", "Total games", "Wins", "Losses"};
	private Object[][] data;
	
	public LeaderboardTableModel(Object[][] data) {
		this.data = data;
	}

	public int getRowCount() { return data.length; }

	public int getColumnCount() { return columnNames.length; }
	
	public String getColumnName(int column) { return columnNames[column]; }

	public Object getValueAt(int row, int column) { return data[row][column]; }
	
	/**
	 * Updates the table data.
	 * @param data new data
	 */
	public void updateData(Object[][] data) {
		// Update the cell values
		this.data = data;
		
		// "Notify" the view that some changes have occurred
		// and therefore it should update the table
		this.fireTableDataChanged();
	}
	
}
