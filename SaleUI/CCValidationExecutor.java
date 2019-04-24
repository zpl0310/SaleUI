
public class CCValidationExecutor {
	private CreditCard cc;
	
	public CCValidationExecutor(CreditCard cc){
		this.cc = cc;
	}
	
	public void executeValidation(){
		cc.isValid();		
	}
}
