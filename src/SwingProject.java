
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
 
 
public class SwingProject extends JFrame {
 
	int n=50;
	Color kolor;
 
	private MyComponent komponent;
	class MyComponent extends JComponent{
 
		@Override
		protected void paintComponent(Graphics g) {
			int w=getWidth();
			int h=getHeight();
 
			int x,x0,x1,x2,y,y0,y1,y2,r=h/8;
			g.setColor(kolor);
 
			g.drawRect(w*3/4, h*3/4, w, h);
			g.drawOval(w*3/4-r, h/4-r, 2*r, 2*r);
			g.drawOval(w/4-r, h/2-r, 2*r, 2*r);

			
			g.drawLine(w/2, 0, w/2, h/2);
			g.drawLine(w/2, h/2, 0, h);

			
			for(int i=0; i<n;i++){
				y=h*i/n;
				if(y>h*3/4){
				g.drawLine(w*3/4, y, w, y);
				}
			}
			for(int i=0; i<n;i++){
				y=h*i/n;
				x1=0;
				x2=w;
				if(y<h/2-r){
					x=w*i/n;
					g.drawLine(0, y, w/2, y);
				}else if(y<h/2){
					x0=w/4;y0=h/2;
					x1=x0-(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//pocz
					x2=x0+(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//kon
					g.drawLine(0, y, x1, y);
					g.drawLine(x2, y, w/2, y);
				}else if(y<h/2+r){
					x=w-w*i/n;
					x0=w/4;y0=h/2;
					x1=x0-(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//pocz
					x2=x0+(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//kon
					g.drawLine(0, y, x1, y);
					g.drawLine(x2, y, x, y);
				}else{
					x=w-w*i/n;
					g.drawLine(0, y, x, y);

				}
	
			}
			for(int i=0;i<n;i++){
				y=h*i/n;
				if((y<h/4+r)&&(y>h/4-r)){
				x0=w*3/4;y0=h/4;
				x1=x0-(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//pocz
				x2=x0+(int)Math.round(Math.sqrt(r*r-(y-y0)*(y-y0)));//kon
				g.drawLine(x1, y, x2, y);
				}
			}
		super.paintComponent(g);
		}
 
	}
	public SwingProject(String string) {
		super(string);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension d=kit.getScreenSize();
		setBounds(d.width/6,d.height/6,d.width*2/3,d.height*2/3);
		add(komponent=new MyComponent());
		setVisible(true);
		JPanel panel = new JPanel(new BorderLayout());
 
		JSlider slider = new JSlider(0,100,n);
		add(slider, BorderLayout.SOUTH);
		JButton cyan = new JButton("CYAN");
		JButton black = new JButton("BLACK");
		cyan.setBounds(d.width*2/3*8/16, d.height*2/3*1/2, 100, 20);
		black.setBounds(d.width*2/3*8/16, d.height*2/3*1/2-20, 100, 20);
		add(cyan);
		add(black);
 
		cyan.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(kolor != Color.CYAN){
				 kolor =(Color.CYAN);
				komponent.repaint();
				}
			}
		});
		black.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(kolor !=Color.BLACK){
				 kolor =(Color.BLACK);
				 komponent.repaint();
				}
			}
		});
 
		slider.addChangeListener(new ChangeListener() {
 
			@Override
			public void stateChanged(ChangeEvent e) {
				n = slider.getValue();
				komponent.repaint();
			}
		});
		add(panel);
 
	}
 
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
 
			@Override
			public void run() {
				new SwingProject("SwingProject");
			}
		});
	}
 
}