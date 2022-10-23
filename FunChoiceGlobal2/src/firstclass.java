import java.awt.Color;	 
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Cursor;
import java.awt.Toolkit;

public class firstclass extends Textures implements ActionListener, MouseListener, MouseMotionListener {

	Timer time = new Timer(5, this);
	int x = 500;
	Map map = new Map(70, 100);
	mouseclicker c = new mouseclicker(5, 5);

	int colorR = 0;
	int colorG = 0;
	int colorB = 0;

	int mapX = 150;
	int mapY = 50;
	
	boolean increaseR = true;
	boolean increaseG = true;
	boolean increaseB = true;

	int timecolor = 0;

	String currentCountry = "[PLEASE SELECT A COUNTRY]";
	Button currentbutton = null;
	String capital = "[PLEASE SELECT A COUNTRY]";
	String language = "[PLEASE SELECT A COUNTRY]";
	String attrac1 = "[PLEASE SELECT A COUNTRY]";
	String attrac2 = "";

	boolean onebuttonpressed = false;

	Button button_venezuela = new Button("Venezuela", "Caracas", "Spanish", mapX + 207, mapY + 61, 
			"In Venezuela, a top attraction is Angel Falls. Angel Walls is a large waterfall that is 979 meters high!", "The sight itself is magnificent, and you are able to go on a boat ride, as well as a hike as a part of the adventure.");
	Button button_colombia = new Button("Colombia", "Bogota", "Spanish", mapX + 113, mapY + 98, 
			"In Colombia, a top attraction is Eje Cafetero. In this area, you can visit many coffee plantations, and you can explore the process of coffee production.", "You are also able to stay at resorts to make your experience more enjoyable.");
	Button button_ecuador = new Button("Ecuador", "Qito", "Spanish, Quechua", mapX + 39, mapY + 153, 
			"In Ecuador, a top attraction is The Galápagos Islands. These islands that contain volcanoes, and it is known for nature sightseeing.", "These islands are home to many species of birds. These island are also famous because this is where Charles Darwin did his research on Finches.");
	Button button_peru = new Button("Peru", "Lima", "Spanish, Quéchua, Aymara", mapX + 86, mapY + 230, 
			"In Peru, a top attraction is Machu Picchu. Macu Piccu is an Inca city built on high perches. In this area, you can go hiking, and you can take the tour around Macu Piccu.", "You can learn a lot about the Incas and the way they lived.");
	Button button_bolivia = new Button("Bolivia", "La Paz", "Spanish, Quechua, Aymara, Guarani", mapX + 220, mapY + 307, 
			"In Bolivia, a top attraction is Madidi National Park. In this national park, you can see many diverse species! ", "Some of these species include jaguars, otters, and monkeys. Furthermore, hundreds of species of birds can be found here as well.");
	Button button_paraguay = new Button("Paraguay", "Asuncion", "Spanish, Guaraní", mapX + 285, mapY + 363, 
			"In Paraguay, a top attraction is Ybycuí National Park. In this national park, you are able to swim in the pools in the area, and you can also", "explore the different species of monkeys, butterflies, and peccaries.");
	Button button_chile = new Button("Chile", "Santiago", "Spanish", mapX + 159, mapY + 412, 
			"In Chile, a top attraction is Torres Del Paine National Park. In this national park, you are able to sightsee the surroundings.", "There are many lakes and mountains to see. Hiking is popular in this area, and you are able to go through long trails around the mountains.");
	Button button_argentina = new Button("Argentina", "Buenos Aires", "Spanish, English, Italian, German, French", 369, mapY + 484, 
			"In Argentina, a top attraction is Iguazú Falls. These waterfalls are great for sightseeing.", "These falls are made of 150-300 water falls and it all has a span of 3 km. ");
	Button button_uruguay = new Button("Uruguay", "Montevideo", "Spanish", mapX + 310, mapY + 469, 
			"In Uruguay, somewhere you MUST go is Punta del Este. Here, you are able to enjoy the beaches and resorts.", "You can also learn a lot about whales and go to the Museum of the Sea.");
	Button button_brazil = new Button("Brazil", "Brasilia", "Portuguese", mapX + 350, mapY + 250, 
			"In Brazil, a popular attraction is Copacabana, Rio de Janeiro. In this awesome beach area, you can swim and surf.", "Make sure you visit the many restaurants, shops, and more.");
	
	BufferedImage southAmerica;
	BufferedImage pointer;
	BufferedImage Button;
	BufferedImage ButtonConfirm;
	
	BufferedImage ar;
	BufferedImage bo;
	BufferedImage br;
	BufferedImage ch;
	BufferedImage co;
	BufferedImage ec;
	BufferedImage pe;
	BufferedImage py;
	BufferedImage uy;
	BufferedImage ve;
	
	public firstclass() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		try {
			southAmerica = ImageIO.read(firstclass.class.getResourceAsStream("/assets/SA_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			pointer = ImageIO.read(firstclass.class.getResourceAsStream("/assets/pointer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Button = ImageIO.read(firstclass.class.getResourceAsStream("/assets/buttonImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ButtonConfirm = ImageIO.read(firstclass.class.getResourceAsStream("/assets/buttonImageConfirm.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ar = ImageIO.read(firstclass.class.getResourceAsStream("/assets/ar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bo = ImageIO.read(firstclass.class.getResourceAsStream("/assets/bo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			br = ImageIO.read(firstclass.class.getResourceAsStream("/assets/br.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ch = ImageIO.read(firstclass.class.getResourceAsStream("/assets/cl.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			co = ImageIO.read(firstclass.class.getResourceAsStream("/assets/co.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ec = ImageIO.read(firstclass.class.getResourceAsStream("/assets/ec.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			pe = ImageIO.read(firstclass.class.getResourceAsStream("/assets/pe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			py = ImageIO.read(firstclass.class.getResourceAsStream("/assets/py.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			uy = ImageIO.read(firstclass.class.getResourceAsStream("/assets/uy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ve = ImageIO.read(firstclass.class.getResourceAsStream("/assets/ve.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
		Collision();
		System.out.println();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(colorR, 255, 255));

		timecolor++;
		if (timecolor % 5 == 0) {
			if (colorR < 200 && increaseR) {
				colorR += 1;
			} else {
				increaseR = false;
			}
			if (colorR > 50 && !increaseR) {
				colorR -= 1;
			} else {
				increaseR = true;
			}
		}
		g.fillRect(0, 0, 20000, 20000);
		g.setColor(Color.BLACK);
		
		addText(g, "Yash Nishikant", 770, 75, 50);
		
		g.drawImage(southAmerica, mapX, mapY, 3000, 3000, this);
		
		if(!button_venezuela.canclick)
		g.drawImage(Button, button_venezuela.x, button_venezuela.y, button_venezuela.wh, button_venezuela.wh, this);
		else
			g.drawImage(ButtonConfirm, button_venezuela.x, button_venezuela.y, button_venezuela.wh, button_venezuela.wh, this);	
		
		if(!button_colombia.canclick)
		g.drawImage(Button, button_colombia.x, button_colombia.y, button_colombia.wh, button_colombia.wh, this);
		else
			g.drawImage(ButtonConfirm, button_colombia.x, button_colombia.y, button_colombia.wh, button_colombia.wh, this);
		
		if(!button_ecuador.canclick)
		g.drawImage(Button, button_ecuador.x, button_ecuador.y, button_ecuador.wh, button_ecuador.wh, this);
		else
			g.drawImage(ButtonConfirm, button_ecuador.x, button_ecuador.y, button_ecuador.wh, button_ecuador.wh, this);
		
		if(!button_peru.canclick)
		g.drawImage(Button, button_peru.x, button_peru.y, button_peru.wh, button_peru.wh, this);
		else
			g.drawImage(ButtonConfirm, button_peru.x, button_peru.y, button_peru.wh, button_peru.wh, this);
		
		if(!button_bolivia.canclick)
		g.drawImage(Button, button_bolivia.x, button_bolivia.y, button_bolivia.wh, button_bolivia.wh, this);
		else
			g.drawImage(ButtonConfirm, button_bolivia.x, button_bolivia.y, button_bolivia.wh, button_bolivia.wh, this);
		
		if(!button_paraguay.canclick)
		g.drawImage(Button, button_paraguay.x, button_paraguay.y, button_paraguay.wh, button_paraguay.wh, this);
		else
			g.drawImage(ButtonConfirm, button_paraguay.x, button_paraguay.y, button_paraguay.wh, button_paraguay.wh, this);
		
		if(!button_chile.canclick)
		g.drawImage(Button, button_chile.x, button_chile.y, button_chile.wh, button_chile.wh, this);
		else
			g.drawImage(ButtonConfirm, button_chile.x, button_chile.y, button_chile.wh, button_chile.wh, this);
		
		if(!button_argentina.canclick)
		g.drawImage(Button, button_argentina.x, button_argentina.y, button_argentina.wh, button_argentina.wh, this);
		else
			g.drawImage(ButtonConfirm, button_argentina.x, button_argentina.y, button_argentina.wh, button_argentina.wh, this);
		
		if(!button_uruguay.canclick)
		g.drawImage(Button, button_uruguay.x, button_uruguay.y, button_uruguay.wh, button_uruguay.wh, this);
		else
			g.drawImage(ButtonConfirm, button_uruguay.x, button_uruguay.y, button_uruguay.wh, button_uruguay.wh, this);	
		
		if(!button_brazil.canclick)
		g.drawImage(Button, button_brazil.x, button_brazil.y, button_brazil.wh, button_brazil.wh, this);
		else
			g.drawImage(ButtonConfirm, button_brazil.x, button_brazil.y, button_brazil.wh, button_brazil.wh, this);	
			
			
		g.setColor(new Color(0, mapX, 255));
		g.fillRect(975, 450, 550, 220);
		
		g.setColor(Color.BLACK);
		addText(g, "Country: " + currentCountry, 1000, 500, 20);
		addText(g, "Capital: " + capital, 1000, 550, 20);
		addText(g, "Language: " + language, 1000, 600, 20);
		addText(g, "Attraction: " + attrac1, 10, 850, 20);
		addText(g, attrac2, 10, 875, 20);

		if (onebuttonpressed)
			g.drawLine(973, 450, currentbutton.x + 15, currentbutton.y + 15);

		double scale = 1.3;

		int flagW = 450;
		int flagH = 270;
		
		if(currentCountry.equals(button_argentina.name))
		g.drawImage(ar, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_bolivia.name))
		g.drawImage(bo, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_brazil.name))
		g.drawImage(br, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_chile.name))
		g.drawImage(ch, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_colombia.name))
		g.drawImage(co, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_ecuador.name))
		g.drawImage(ec, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_peru.name))
		g.drawImage(pe, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_paraguay.name))
		g.drawImage(py, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_uruguay.name))
		g.drawImage(uy, 1000, 100, flagW, flagH, this);
		else if(currentCountry.equals(button_venezuela.name))
		g.drawImage(ve, 1000, 100, flagW, flagH, this);
		else {
			
		}
		g.drawImage(pointer, c.x-10, c.y-10, (int)(20*scale), (int)(30*scale), this);
		
	}

	
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		Container contentpane = frame.getContentPane();
		firstclass sPanel = new firstclass();
		frame.setSize(800, 800);
		contentpane.add(sPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);

	}

	public void Collision() {
		setupButton(c.bounds(), button_venezuela.bounds(), button_venezuela);
		setupButton(c.bounds(), button_colombia.bounds(), button_colombia);
		setupButton(c.bounds(), button_ecuador.bounds(), button_ecuador);
		setupButton(c.bounds(), button_peru.bounds(), button_peru);
		setupButton(c.bounds(), button_bolivia.bounds(), button_bolivia);
		setupButton(c.bounds(), button_paraguay.bounds(), button_paraguay);
		setupButton(c.bounds(), button_chile.bounds(), button_chile);
		setupButton(c.bounds(), button_argentina.bounds(), button_argentina);
		setupButton(c.bounds(), button_uruguay.bounds(), button_uruguay);
		setupButton(c.bounds(), button_brazil.bounds(), button_brazil);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		onClick(button_venezuela);
		onClick(button_colombia);
		onClick(button_ecuador);
		onClick(button_peru);
		onClick(button_bolivia);
		onClick(button_paraguay);
		onClick(button_chile);
		onClick(button_argentina);
		onClick(button_uruguay);
		onClick(button_brazil);

	}

	public void onClick(Button b) {
		if (b.canclick) {
			currentCountry = b.name;
			language = b.language;
			capital = b.capital;
			attrac1 = b.description;
			attrac2 = b.description2;
			currentbutton = b;
			onebuttonpressed = true;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		c.x = e.getX() + 2;
		c.y = e.getY() + 2;
	
		
	}

	public void setupButton(Rectangle mouse, Rectangle country, Button b) {
		if (mouse.intersects(country)) {
			b.canclick = true;
		} else {
			b.canclick = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		c.x = e.getX() + 2;
		c.y = e.getY() + 2;
		onClick(button_venezuela);
		onClick(button_colombia);
		onClick(button_ecuador);
		onClick(button_peru);
		onClick(button_bolivia);
		onClick(button_paraguay);
		onClick(button_chile);
		onClick(button_argentina);
		onClick(button_uruguay);
		onClick(button_brazil);

	}

}
