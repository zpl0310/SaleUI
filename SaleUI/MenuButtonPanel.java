import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuButtonPanel extends JPanel{
	private JPanel buttons;
	private JButton LoadMenu;
	private MenuPresenter mp;
	private JFileChooser fc;
	private ButtonListener bl;
	
	public MenuButtonPanel(){
		initiatePanel();
		
	}
	
	public void initiatePanel(){
		buttons = new JPanel();
		LoadMenu = new JButton("Replace with actual Menu Items");
		fc = new JFileChooser();
		
		LoadMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(fc.showOpenDialog(MenuButtonPanel.this)==JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					mp = new MenuPresenter(f);
					HashMap<String,Double> menu = mp.parseFile();
					//System.out.println(menu);
					addButtons(menu);
					revalidate();
					repaint();
				}
			}		
				
		});
		
		LoadMenu.setPreferredSize(new Dimension(300,60));
		LoadMenu.setBackground(new Color(59, 89, 182));
		LoadMenu.setForeground(new Color(255, 102, 153));
		LoadMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		LoadMenu.setOpaque(true);
		//LoadMenu.setBorderPainted(false);
		
		setLayout(new BorderLayout());
		buttons.setLayout(new GridLayout(5,5));
		//buttons.setBackground(new Color(255,255,204));
		
		buttons.setPreferredSize(new Dimension(800,600));
		
		add(new JScrollPane(buttons),BorderLayout.CENTER);
		add(LoadMenu,BorderLayout.SOUTH);	
		
	}
	
	public JButton initiateButton(String item){
		JButton button = new JButton(item);
		button.setBackground(new Color(128, 170, 255));
        button.setForeground(new Color(255, 102, 153));
        button.setFocusPainted(false);
        button.setFont(new Font("Tahoma", Font.PLAIN, 14));
        button.setOpaque(true);
        //button.setContentAreaFilled(false);
        //button.setBorderPainted(false);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				double price = mp.menu.get(item);
				ButtonEvent be = new ButtonEvent(this,item,price);
				
				if (bl != null){
					bl.ButtonEventOccoured(be);	
					//System.out.println(item +" "+ mp.menu.get(item));
				}		
			}					
		});		
		//System.out.println("added");
		//button.setPreferredSize(new Dimension(80,40));
		
		return button;		
	}
	
	public void addButtons(HashMap<String,Double> menu){
		for (String item:menu.keySet()){
			JButton but = initiateButton(item);
			buttons.add(but);
			//System.out.println(item);
		}
	}
	
	public void setButtonListener(ButtonListener bl){
		this.bl = bl;
	}
}
