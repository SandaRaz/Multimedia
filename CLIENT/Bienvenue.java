package gui;

import javax.swing.*;
import java.awt.*;

public class Bienvenue extends JFrame{

	public Bienvenue(){
		JLabel tongasoa = new JLabel("<html><body><p>Bienvenue ...</p><p style=\"font-size: large; \">Streaming player by Sanda</p></body></html>");
		tongasoa.setOpaque(false);
		tongasoa.setFont(new Font("Century Gothic",Font.BOLD,48));
		tongasoa.setHorizontalAlignment(SwingConstants.CENTER);
		this.setContentPane(tongasoa);
		
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,150);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}