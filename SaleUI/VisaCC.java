import java.util.Date;

public class VisaCC extends CreditCard {

	public VisaCC(String name,String expireDate,int CVV,String cardNumber) {
		super(name,expireDate,CVV,cardNumber);	
	}

	@Override
	public boolean isValid() {
		return true;
	}
}
