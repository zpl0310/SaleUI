import java.util.Date;

public class MasterCC extends CreditCard {

	public MasterCC(String name,String expireDate,int CVV,String cardNumber) {
		super(name,expireDate,CVV,cardNumber);
		// TODO Auto-generated constructor stub
	}

	public boolean isValid() {
		return true;
	}
}
