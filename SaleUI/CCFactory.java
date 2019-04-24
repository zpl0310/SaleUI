

import javax.swing.JOptionPane;


public class CCFactory {
	private int oneDigit(int number){
		if (number >= 10){
			String num = new Integer(number).toString();
			int a = Character.getNumericValue(num.charAt(1));
			int b = Character.getNumericValue(num.charAt(0));
			number = a+b;
		}	
		//System.out.println(number);
		return number;
		
	}
	
	private int[] convertDigit(String cardNumber){
		int[] digits = new int[cardNumber.length()];
		int index = 0;
		for (int i = cardNumber.length()-1;i>=0;i--){
			digits[index] = Character.getNumericValue(cardNumber.charAt(i));
			index++;
		}
		for (int i = 1; i< cardNumber.length(); i += 2){
			digits[i] = oneDigit (digits[i]*2);
			
		}	
		
		/*
		for (int i = 0;i<cardNumber.length();i++){
			System.out.println(digits[i]);
		}
		*/
		return digits;	
	}
	

	private boolean checkDigit(int[] cardNumber){
		int sum = 0;
		for (int i:cardNumber){
			sum += i;
		}
		
		//System.out.println(sum);
		
		if (sum%10 ==0){
			return true;
		}
		return false;
	}
	
	public CreditCard getCardObject(String name,String expireDate,int CVV,String cardNumber){
		if (!checkDigit(convertDigit(cardNumber))){
			JOptionPane.showMessageDialog(null, "Invalid Credit Card Number according to ISO 2894/ANSI!","Error"
					,JOptionPane.ERROR_MESSAGE);
			return null;
		}
		

		if(cardNumber.length()>19|| cardNumber.length()<=4){
			JOptionPane.showMessageDialog(null, "Invaid Card Number Length!","Error"
					,JOptionPane.ERROR_MESSAGE);
			//System.out.println("Invalid Credit Card Number!");
			return null;
		}
		
		int firstDigit = Character.getNumericValue(cardNumber.charAt(0));
		int secondDigit = Character.getNumericValue(cardNumber.charAt(1));
		
		if (firstDigit == 5 && secondDigit <= 5 && secondDigit >= 1 && cardNumber.length() == 16){
			//System.out.println("Master Card is accepted");
			return new MasterCC(name,expireDate,CVV,cardNumber);
		}
		
		if (firstDigit == 4 && cardNumber.length() == 13 || cardNumber.length() == 16){
			//System.out.println("Visa Card is accepted");
			return new VisaCC(name,expireDate,CVV,cardNumber);
		}
		
		if (firstDigit == 3 && secondDigit ==4 || secondDigit == 7 && cardNumber.length() ==15){
			//System.out.println("American Express Card is accepted");
			return new AmExCC(name,expireDate,CVV,cardNumber);
		}
		
		if (cardNumber.substring(0, 4).equals("6011")){
			//System.out.println("Discover Card is accepted");
			return new DiscoverCC(name,expireDate,CVV,cardNumber);
		}
		
		JOptionPane.showMessageDialog(null, "Invalid Credit Card Number or Unknown Type of Card","Error"
				,JOptionPane.ERROR_MESSAGE);
		//System.out.println("Invalid Credit Card Number or Unknown Type of Card");
		return null;
	}

}
