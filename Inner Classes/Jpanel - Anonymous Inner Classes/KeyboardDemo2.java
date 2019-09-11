import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class KeyboardDemo2 extends JPanel {

	public KeyboardDemo2(){

		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		MouseListener key = new KeyboardInterface();
		addKeyListener(key);
	}

	public static void main(String[]args){

	KeyboardDemo2 demo = new KeyboardDemo2();

	}


	public class KeyboardInterface implements KeyListener{

			public void keyPressed(KeyEvent e){
				System.out.println(e.getKeyChar());
			}
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}

	}

}