import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep1 extends JFrame implements ActionListener, KeyListener{
	
		
	private static final long serialVersionUID = 427610188913178394L;
	
	//storage classes for our 2 characters
	private Doctor myDoctor;
	private Tardis myTardis;
	
	//labels to show the graphics
	private JLabel DoctorLabel, TardisLabel;
	private ImageIcon DoctorImage, TardisImage;
	
	//button to control Tardis
	private JButton HideTardisButton, AnimateButton;
	
	//container to hold graphics
	private Container content;
	
	//GUI setup
	public GamePrep1() {
		super("Doctor Tardis Chase");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		myDoctor = new Doctor();
		DoctorLabel = new JLabel();
		DoctorImage = new ImageIcon( getClass().getResource( myDoctor.getFilename() ) );
		DoctorLabel.setIcon(DoctorImage); 
		DoctorLabel.setSize(myDoctor.getWidth(),myDoctor.getHeight());		
		
		//TardisLabel = new JLabel();
		//myTardis = new Tardis(TardisLabel);
		myTardis = new Tardis();
		TardisLabel = new JLabel();
		TardisImage = new ImageIcon( getClass().getResource( myTardis.getFilename() ) );
		TardisLabel.setIcon(TardisImage);
		TardisLabel.setSize(myTardis.getWidth(),myTardis.getHeight());
		
		myTardis.setTardisLabel(TardisLabel);
		myTardis.setDoctor(myDoctor);
		myTardis.setDoctorLabel(DoctorLabel);
		
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		myDoctor.setX(100);
		myDoctor.setY(250);
		
		add(DoctorLabel);
		add(TardisLabel);
		TardisLabel.setVisible( myTardis.getVisible());
		
		//update the label position to match the stored values
		
		DoctorLabel.setLocation(myDoctor.getX(), myDoctor.getY());
		TardisLabel.setLocation(myTardis.getX(), myTardis.getY());
		
		HideTardisButton = new JButton("Disappear");
		HideTardisButton.setSize(100,50);
		HideTardisButton.setLocation(GameProperties.SCREEN_WIDTH-150,GameProperties.SCREEN_HEIGHT-100);
		add(HideTardisButton);
		HideTardisButton.addActionListener(this);
		HideTardisButton.setFocusable(false);
		
		AnimateButton = new JButton("Run");
		AnimateButton.setSize(100,50);
		AnimateButton.setLocation(GameProperties.SCREEN_WIDTH-150,GameProperties.SCREEN_HEIGHT-150);
		add(AnimateButton);
		AnimateButton.addActionListener(this);
		AnimateButton.setFocusable(false);
		myTardis.setAnimationButton(AnimateButton);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String[] args) {
		GamePrep1 myGame = new GamePrep1();
		myGame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int dx = myDoctor.getX();
		int dy = myDoctor.getY();
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			dy -= GameProperties.CHARACTER_STEP;
			if(dy + myDoctor.getHeight() <0) dy= GameProperties.SCREEN_HEIGHT;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			dy += GameProperties.CHARACTER_STEP;
			if(dy > GameProperties.SCREEN_HEIGHT) dy = -1*myDoctor.getHeight();
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
			dx -= GameProperties.CHARACTER_STEP;
			if(dx + myDoctor.getWidth() <0) dx= GameProperties.SCREEN_WIDTH;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			dx += GameProperties.CHARACTER_STEP;
			if(dx > GameProperties.SCREEN_WIDTH) dx = -1*myDoctor.getWidth();
		}
		
		myDoctor.setX(dx);
		myDoctor.setY(dy);
		
		DoctorLabel.setLocation(myDoctor.getX(), myDoctor.getY());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == HideTardisButton) {
			
			if (myTardis.getVisible()) {
				//already visible, hide and change button text to appear
				myTardis.hide();
				HideTardisButton.setText("Appear");
			} else {
				//already hidden, show and change button text to dissapear
				myTardis.show();
				HideTardisButton.setText("Disappear");
			}
			TardisLabel.setVisible(myTardis.getVisible());
			
		} else if (e.getSource() == AnimateButton) {
			
			if (myTardis.getMoving()) {
				//tardis is moving, stop , change button text to run
				myTardis.setMoving(false);
				AnimateButton.setText("Run");
			} else {
				//tardis is not moving. start, change button text to stop
				AnimateButton.setText("Stop");
				myTardis.moveTardis();
			}
		}
		
	}		

}
