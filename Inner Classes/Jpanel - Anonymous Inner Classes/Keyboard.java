import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Keyboard extends JPanel {

	public Keyboard(){

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addKeyListener( new KeyListener(){

			public void keyPressed(KeyEvent e){
				System.out.println(e.getKeyChar());
			}
			public void keyReleased(KeyEvent e){

			}
			public void keyTyped(KeyEvent e){

			}

		});
	}

	public static void main(String[]args){

	Keyboard demo = new Keyboard();

	}


//this uses anonymous inner classes

}