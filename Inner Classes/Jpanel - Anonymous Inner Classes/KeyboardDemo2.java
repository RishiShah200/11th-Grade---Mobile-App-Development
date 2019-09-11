import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class KeyboardDemo2 extends JPanel {

	public KeyboardDemo2(){

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

	KeyboardDemo2 demo = new KeyboardDemo2();

	}


//this uses anonymous inner classes

}