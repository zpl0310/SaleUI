import java.util.Date;

public abstract class CreditCard{
	private String name;
	private String expireDate;
	private int CVV;
	private String cardNumber;
	
	public CreditCard(String name,String expireDate,int CVV,String cardNumber){
		this.cardNumber = cardNumber;
		this.name = name;
		this.CVV = CVV;
		this.expireDate = expireDate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public boolean isValid() {
		return true;
	}

	
	
	
}
