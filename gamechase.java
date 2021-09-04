import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import javax.swing.JPanel;



public class gamechase extends JPanel implements ActionListener, KeyListener {
	Timer tm =new Timer (40, this);
	private Graphics2D g2;
	private int xp=250,yp=250,velX =0,velY=0;
	private int xb=470,yb=0;
	private int xg=470,yg=440;
	private int xr=0,yr=0;
	private int xo=0,yo=440;
	private int firstp=1;
	private boolean finish=false;
	private long startTime;
	private long skor=0;
	private int pressing=0;
	private int isFirst=2;
	private JFrame myframe;

	
	
	private Rectangle rt_player;
	private Rectangle ghostb;
	private Rectangle ghostg;
	private Rectangle ghostr;
	private Rectangle ghosto;
	
	class ghostb_movement extends Thread {
		int f;
		public void run() {
			while(!finish) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(checkx(xb)){
					f=yb-yp;
					if(f<0){
						yb=yb+10;
						repaint();
					}
					else{
						yb=yb-10;
						repaint();
					}
				}
				else{
					f=xb-xp;
					if(f<0){
						xb=xb+10;
						repaint();
					}
					else{
						xb=xb-10;
						repaint();
					}
				}
				
				if(ghostb.contains(xp,yp)){
					skor=(long) ((System.currentTimeMillis()-startTime)*0.001);
					finish=true;
					JOptionPane.showOptionDialog(myframe, "Skorunuz: "+skor,"Final Skor", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
					System.exit(0);
					this.interrupt();
					}
				
			}
		}
	}
	class ghosto_movement extends Thread {
		int f;
		public void run() {
			while(!finish) {
				try {
					Thread.sleep(1250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(checkx(xo)){
					f=yo-yp;
					if(f<0){
						yo=yo+10;
						repaint();
					}
					else{
						yo=yo-10;
						repaint();
					}
				}
				else{
					f=xo-xp;
					if(f<0){
						xo=xo+10;
						repaint();
					}
					else{
						xo=xo-10;
						repaint();
					}
				}
				
				if(ghosto.contains(xp,yp)){
					skor=(long) ((System.currentTimeMillis()-startTime)*0.001);
					finish=true;
					JOptionPane.showOptionDialog(null, "Skorunuz: "+skor,"Final Skor", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
					System.exit(0);
					this.interrupt();
					}
				
			}
		}
	}
	class ghostr_movement extends Thread {
		int f;
		public void run() {
			while(!finish) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(checky(yr)){
					f=xr-xp;
					if(f<0){
						xr=xr+10;
						repaint();
					}
					else{
						xr=xr-10;
						repaint();
					}
				}
				else{
					f=yr-yp;
					if(f<0){
						yr=yr+10;
						repaint();
					}
					else{
						yr=yr-10;
						repaint();
					}
				}
				
				if(ghostr.contains(xp,yp)){
					skor=(long) ((System.currentTimeMillis()-startTime)*0.001);
					finish=true;
					JOptionPane.showOptionDialog(null, "Skorunuz: "+skor,"Final Skor", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
					System.exit(0);
					this.interrupt();
					}
				
			}
		}
	}
	class ghostg_movement extends Thread {
		int f;
		public void run() {
			while(!finish) {
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(checky(yg)){
					f=xg-xp;
					if(f<0){
						xg=xg+10;
						repaint();
					}
					else{
						xg=xg-10;
						repaint();
					}
				}
				else{
					f=yg-yp;
					if(f<0){
						yg=yg+10;
						repaint();
					}
					else{
						yg=yg-10;
						repaint();
					}
				}
				
				if(ghostg.contains(xp,yp)){
					skor=(long) ((System.currentTimeMillis()-startTime)*0.001);
					finish=true;
					JOptionPane.showOptionDialog(null, "Skorunuz: "+skor,"Final Skor", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
					System.exit(0);
					this.interrupt();
					}
				
			}
		}
	}
	public gamechase(JFrame fm)
	{
		this.myframe=fm;
		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	public boolean checkx(int xgelen){
		if(xp==xgelen)
			return true;
		else
			return false;
	}
	public boolean checky(int ygelen){
		if(yp==ygelen)
			return true;
		else
			return false;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		
		rt_player=new Rectangle(xp,yp,10,10);
		ghostb=new Rectangle(xb,yb,10,10);
		ghostg=new Rectangle(xg,yg,10,10);
		ghostr=new Rectangle(xr,yr,10,10);
		ghosto=new Rectangle(xo,yo,10,10);
		
		g2.setPaint(Color.BLUE);
		g2.fill(ghostb);
		g2.setPaint(Color.GREEN);
		g2.fill(ghostg);
		g2.setPaint(Color.RED);
		g2.fill(ghostr);
		g2.setPaint(Color.ORANGE);
		g2.fill(ghosto);
		g2.setPaint(Color.BLACK);
		g2.fill(rt_player);
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		//System.out.println("process");
		if(isFirst==0)
			return;
		if(xp<0){
			velX=0;
			xp=0;
		}
		if(xp>470){
			velX=0;
			xp=470;
		}
		if(yp<0){
			velY=0;
			yp=0;
		}
		if(yp>440){
			velY=0;
			yp=440;
		}
			
		xp=xp+velX;
		yp=yp+velY;
		repaint();
		if(isFirst==1)
			isFirst=0;
		
	}
	@Override
	public synchronized void  keyPressed(KeyEvent e) {
		if(isFirst==0)
			return;
		if(this.firstp==1){
			startTime = System.currentTimeMillis();
			ghostb_movement tgb = new ghostb_movement();
			ghosto_movement tgo = new ghosto_movement();
			ghostr_movement tgr = new ghostr_movement();
			ghostg_movement tgg = new ghostg_movement();
			tgb.start();
			tgo.start();
			tgr.start();
			tgg.start();
		}
		//System.out.println("pressed strat");
		int c=e.getKeyCode();
		if(c== KeyEvent.VK_LEFT){
			velX=-10;
			velY=0;
		}
		if(c==KeyEvent.VK_UP){
			velX=0;
			this.velY=-10;
		}
		if(c==KeyEvent.VK_RIGHT){
			velX=10;
			this.velY=0;
		}
		if(c==KeyEvent.VK_DOWN){
			velX=0;
			this.velY=10;
		}
		firstp=0;
		//System.out.println("exit");
		isFirst=1;
		
	}
	@Override
	public synchronized void  keyReleased(KeyEvent e) {
		this.velX=0;
		this.velY=0;
		isFirst=2;
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public static void main(String[] args){
		
		JFrame f=new JFrame();
		gamechase gm=new gamechase(f);
		f.setTitle("Chasing Game");
		f.setSize(500,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(gm);
		System.out.println("Eger ilk denediginizde kacan cisim hareket etmiyorsa lutfen pencereyi kapatıp kodu tekrar run edin.\n"
				+ "Kendi bilgisayarimda nadiren böyle bir sikintiyla karsilasdim.\n"
				+ "2. run ediliste program düzgün calisiyor");
		
			 
		
		
		
	
	}
	
	
	
	

}
