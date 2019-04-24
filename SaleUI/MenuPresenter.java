import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MenuPresenter {
	
	public HashMap<String,Double> menu;
	private File f;
	
	public MenuPresenter(File f){
		this.f = f;
		menu = new HashMap<String,Double>();
	}
	
	public HashMap<String,Double> parseFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       int index = line.indexOf("|");
		       if (index != -1){
		    	   String item = line.substring(0, index);
		    	   double price = Double.parseDouble(line.substring(index+1));
		    	   menu.put(item, price);
		    	   //System.out.println(menu);
		    	 }
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
		
	}

}
