import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class OrderPanel extends JPanel implements ActionListener{
	private JTable table;
	public JTextField sum;
	private OrderTableModel otm;
	private JButton placeOrder;
	private JButton cancelOrder;
	private JButton payOrder;
	private Controller controller;
	private OrderListener ol;
	private JPopupMenu pu;
	private DeleteListener dl;
	private JLabel total;
		
	public OrderPanel(){
		sum = new JTextField(10);
		otm = new OrderTableModel();
		controller = new Controller();
		table = new JTable(otm);
		placeOrder = new JButton("Place Order");
		cancelOrder = new JButton ("Cancel Order");
		payOrder = new JButton("Pay");
		total = new JLabel("Total Price: ");
		
		Border innerborder = BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(128, 170, 255));;
		//Border outterBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
		//setBorder(BorderFactory.createCompoundBorder(outterBorder, innerborder));
		setBorder(innerborder);
		
		setButtonLook(placeOrder);
		setButtonLook(cancelOrder);
		setButtonLook(payOrder);
		setTableLook(table);
		
		layoutComponent();
		
		cancelOrder.addActionListener(this);
		placeOrder.addActionListener(this);
		
		pu = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete item");
		pu.add(removeItem);
		
		table.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON3){
					pu.show(table, e.getX(), e.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				if (dl != null) {
					dl.deleteRow(row);
					otm.fireTableRowsDeleted(row, row);
				}
			}
			
		});
		
		payOrder.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				initiateJOptionPane();			
			}
			
		});
		
	}
	
	public void setListener(OrderListener ol){
		this.ol = ol;	
	}	
	
	public void setDeleteListener(DeleteListener dl){
		this.dl = dl;
	}
		
	private void setButtonLook(JButton b){
		b.setPreferredSize(new Dimension(140,60));
		b.setBackground(new Color(59, 89, 182));
        b.setForeground(new Color(255, 102, 153));
        b.setFocusPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 16));
        b.setOpaque(true);
        //b.setBorderPainted(false);
        //b.setContentAreaFilled(false);
	}
	
	private void setTableLook(JTable t){
		t.setFont(new Font("Tahoma", Font.PLAIN, 12));
		t.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		t.getTableHeader().setForeground(new Color(59, 89, 182));
		t.setRowHeight(25);
		t.setBackground(new Color(128, 170, 255));
	}
	
	private void layoutComponent(){
		setLayout(new BorderLayout());
		JPanel botPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		rightPanel.add(total);
		rightPanel.add(sum);
		botPanel.add(placeOrder);
		botPanel.add(cancelOrder);
		botPanel.add(payOrder);
		add(new JScrollPane(table),BorderLayout.CENTER);
		add(botPanel,BorderLayout.SOUTH);
		add(rightPanel,BorderLayout.NORTH);
		total.setFont(new Font("Tahoma", Font.BOLD, 14));
		total.setForeground(new Color(59, 89, 182));
	}
	
	public void setData(List<Item> db){
		otm.setData(db);	
	}
	
	
	public void refresh(){
		otm.fireTableDataChanged();
		//System.out.println(opc.totalPrice);
	}
	
	public void setTotalPrice(String totalPrice){
		sum.setText(totalPrice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == placeOrder){
			if (ol!= null){
				ol.placeOrder();
			}
		}
		if (e.getSource() == cancelOrder){
			if (ol!= null){
				ol.cancelOrder();
			}
			
		}
		
	}
	
	public void cancalOrder(){
		controller.cancelOrder();
	}
	
	public void initiateJOptionPane(){
		JTextField name = new JTextField(10);
	    JTextField expireDate = new JTextField(10);
	    JTextField cardNumber = new JTextField(20);
	    JTextField CVV = new JTextField(10);

	    JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
	    panel.add(new JLabel("Name on Card"));
	    panel.add(name);
	    panel.add(new JLabel("Card Number"));
	    panel.add(cardNumber);
	    panel.add(new JLabel("Expire Date (MM/YY)"));
	    panel.add(expireDate);
	    panel.add(new JLabel("CVV Number"));
	    panel.add(CVV);
	    
	    int result = JOptionPane.showConfirmDialog(OrderPanel.this, panel, "Please Enter Credit Card Information", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	    	
	    	String nameText = name.getText();
		    String dateText = expireDate.getText();
		    String numberText = cardNumber.getText();
		    int CVVText = 0;
		    if (!CVV.getText().equals("")){
		    	CVVText = Integer.parseInt(CVV.getText());}
		    
	         //System.out.println("Name on Card: " + name.getText());
	        // System.out.println("Card Number: " + cardNumber.getText());
	         //System.out.println("Expire Date: " + expireDate.getText());
	         //System.out.println("CVV: " + CVV.getText());
	    	
	    	CreditCard cc = controller.getCardObject(nameText, dateText, CVVText, numberText);
	    	if (cc != null){
	    		String message = cc.getClass().getName() + " object is created";
				JOptionPane.showMessageDialog(null, message,"Credit Card Accepted"
						,JOptionPane.PLAIN_MESSAGE);
				System.out.println(message);
	    	}
	      }
	}
}

