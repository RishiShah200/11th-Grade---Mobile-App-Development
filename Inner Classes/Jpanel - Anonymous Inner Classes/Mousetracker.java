import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Mousetracker extends JPanel{

	int x = 100, y = 100;
	double total = 0.0;
	int initX, initY, newX, newY;


	public Mousetracker(double pix, double in){

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addMouseMotionListener(new MouseMotionListener(){

			public void mouseMoved(MouseEvent e){
				newX = e.getX();
				newY = e.getY();

				double dist = Math.sqrt(Math.pow(newX - initX, 2) + Math.pow(newY - initY, 2)) * ((in / 12.0) / pix);
				total+=dist;
				System.out.println(total);

				initX = e.getX();
				initY = e.getY();



			}

			public void mouseDragged(MouseEvent e){}


		});

		addKeyListener(new KeyAdapter(){

			String choice = " ";

			public void keyTyped(KeyEvent e){
				char in = e.getKeyChar();
				if("aeiouAEUOI".contains(""+in)){
					choice = "Vowel";
					System.out.println("The string you typed is a "+choice);

				}
				else if("qwrtypsdfghjklzxcvbnmQWRTYPSDFGHJKLZXCVBNM".contains(""+in)){
					choice = "Consonant";
					System.out.println("The string you typed is a "+choice);
				}
				else{
					choice = "You have made a mistake";
					System.out.println("The string you typed is a "+choice);
				}

					//System.out.println("The string you types is a "+choice);
			}


		});



	}





	public static void main(String[]args){

	Mousetracker track = new Mousetracker(1600.0,16);

	}



}