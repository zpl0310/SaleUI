import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class guiController extends JFrame{
	private MenuButtonPanel mbp;
	private OrderPanel op;
	private Controller controller;
	
	public guiController(){
		super("McPattern");
		mbp = new MenuButtonPanel();
		op = new OrderPanel();
		controller = new Controller();
		op.setData(controller.getOrders());
		
		setLayout(new BorderLayout());
		add(mbp,BorderLayout.CENTER);
		add(op,BorderLayout.EAST);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		op.setListener(new OrderListener(){
			@Override
			public void placeOrder(){
				String message = "Order placed for " + controller.getOrders().size() + " items, " + "total price is " + controller.getTotalPrice()+" dollar";
				JOptionPane.showMessageDialog(null, message,"Order Placed"
						,JOptionPane.PLAIN_MESSAGE);
				System.out.println(message);		
			}

			@Override
			public void cancelOrder() {
				controller.cancelOrder();
				op.setData(controller.getOrders());
				op.setTotalPrice(new Double(0.0).toString());
				//System.out.println(opc.getOrders());
				op.refresh();
				System.out.println("Order canceled");
			}		
		});
		
		op.setDeleteListener(new DeleteListener(){

			@Override
			public void deleteRow(int row) {
				controller.removeItem(row);
				//System.out.println(row);	
				double sum = Math.floor(controller.getTotalPrice()*100)/100;		
				op.setTotalPrice(new Double(sum).toString());
			}
			
		});
		
		mbp.setButtonListener(new ButtonListener(){
			@Override
			public void ButtonEventOccoured(ButtonEvent be) {
				controller.addItem(be);
				op.refresh();
				double sum = Math.floor(controller.getTotalPrice()*100)/100;		
				op.setTotalPrice(new Double(sum).toString());
			}
		});
		
	}

	public static void main(String[] args) {
		new guiController();

	}

}
