import java.util.List;

import javax.swing.table.AbstractTableModel;



public class OrderTableModel extends AbstractTableModel {

	private List<Item> orders;
	
	private String[] colName = {"Item","Price"};
	
	public OrderTableModel(){
		
	}

	public void setData(List<Item> orders){
		this.orders = orders;
	}
	@Override
	public int getColumnCount() {
		
		return 2;
	}

	@Override
	public int getRowCount() {
		return orders.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Item order = orders.get(row);
		switch(col){
		case 0:
			return order.getItem();
		case 1:
			return order.getPrice();	
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return colName[column];
	}	
	
}
