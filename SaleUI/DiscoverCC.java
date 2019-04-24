import java.util.Date;

public class DiscoverCC extends CreditCard{

	public DiscoverCC(String name, String expireDate, int CVV, String cardNumber) {
		super(name, expireDate, CVV, cardNumber);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isValid() {
		return true;
	}
}
