package newworld;

import java.awt.*; 
import javax.swing.*; 
public class panelSetup extends JFrame { 
  
    // constuctor 
	panelSetup() 
    { 
        super("panelSetup"); 
  
        Canvas c = new Canvas() { 
   
            public void paint(Graphics g) 
            { 
                g.setColor(Color.red); 
  
                g.setFont(new Font("Bold", 1, 20)); 
    
                g.drawString("This is a canvas", 100, 100); 
            } 
        };
  
        c.setBackground(Color.black); 
  
        add(c); 
        setSize(400, 300);  
    } 
  
    // Main Method 
    public static void main(String args[]) 
    { 
    	panelSetup world = new panelSetup(); 
    } 
} 