import java.util.EventObject;

public class ButtonEvent extends EventObject{
	private String item;
	private double price;

	public ButtonEvent(Object source) {
		super(source);
		
	}
	
	public ButtonEvent(Object source,String item, double price){
		super(source);
		this.item = item;
		this.price = price;
	}

	public String getItem() {
		return item;
	}

	public double getPrice() {
		return price;
	}
	
	

}
