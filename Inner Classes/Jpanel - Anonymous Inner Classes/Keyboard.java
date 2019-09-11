import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Keyboard extends JPanel implements KeyListener{

	public Keyboard(){

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);
		addKeyListener(this);
	}

	public static void main(String[]args){

	Keyboard demo = new Keyboard();

	}

	public void keyPressed(KeyEvent e){

		System.out.println(e.getKeyChar());
	}
	public void keyReleased(KeyEvent e){

	}
	public void keyTyped(KeyEvent e){

	}


}