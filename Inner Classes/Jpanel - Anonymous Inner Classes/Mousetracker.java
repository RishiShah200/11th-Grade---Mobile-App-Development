import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Mousetracker extends JPanel{

	int x = 100, y = 100;
	int initial = 0, end = 0;

	public Mousetracker(){

		JFrame frame = new JFrame();
		frame.add(this);
		MouseMotionListener mouse = new Mouseinterface();
		addMouseMotionListener(mouse);
		frame.setVisible(true);
		setFocusable(true);

	}


	public static void main(String[]args){

	Mousetracker track = new Mousetracker();

	}

	public class Mouseinterface implements MouseMotionListener{

		public void mouseExited(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseReleased(MouseEvent e){
			end = e.getX();
		}
		public void mousePressed(MouseEvent e){}
		public void mouseClicked(MouseEvent e){
			initial = e.getX();
		}
		public void mouseMoved(MouseEvent e){}
		public void mouseDragged(MouseEvent e){
			x = e.getX();
			y = e.getY();
			System.out.println("X"+x);
			System.out.println("Y"+y);
		}

	}

}