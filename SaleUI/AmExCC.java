import java.util.Date;

public class AmExCC extends CreditCard {

	public AmExCC(String name,String expireDate,int CVV,String cardNumber) {
		super(name, expireDate, CVV, cardNumber);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isValid() {
		return true;
	}
}
