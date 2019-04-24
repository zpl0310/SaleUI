import java.util.ArrayList;

public class Controller {
	public double totalPrice;
	public ArrayList<Item> orders;
	private CCFactory factory;
	
	public Controller(){
		orders = new ArrayList<Item>();
		totalPrice = 0.0;
		factory = new CCFactory();
	}
	
	public ArrayList<Item> getOrders(){
		return orders;
	}
	
	public void addItem(ButtonEvent be){
		orders.add(new Item(be.getItem(),be.getPrice()));
		//System.out.println(orders);
		totalPrice += be.getPrice();
		//System.out.println(totalPrice);
	}
	
	public void removeItem(int index){
		totalPrice -= orders.get(index).getPrice();
		orders.remove(index);	
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void cancelOrder(){
		orders = new ArrayList<Item>();
		totalPrice = 0.0;
	}
	
	public CreditCard getCardObject(String name,String expireDate,int CVV,String cardNumber){
		return factory.getCardObject(name, expireDate, CVV, cardNumber);		
	}
}
