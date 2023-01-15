package nurikabe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

public class myFrame {
	
	private Maze maze;
	private Object[] meretek= {4,5,6,7,8,9,10};
	private Thread idot;
	
	
	private JPanel winpanel;
	private JLabel szoveg;
	private JButton menu2;
	
	private JFrame f=new JFrame("Nurikabe");
	private JPanel p;
	private JPanel p2;
	private JPanel jatekap;
	private JPanel jatek=new JPanel();
	private JPanel idop;
	private JPanel bp;
	private CardLayout cl=new CardLayout();
	private JPanel kartyak=new JPanel();
	private JComboBox<Object> cbx=new JComboBox<Object>(meretek);
	private JComboBox<Object> cby=new JComboBox<Object>(meretek);
	private Koordinate[][] b;
	private JButton menu;
	private JButton feladb;
	private JButton mentesb;
	
	private JPanel menup;
	private JButton ujJatek;
	private JButton betoltesb;
	
	private JPanel ujJatekp;
	private JPanel ujJatekpn;
	private JPanel ujJatekpc;
	private JLabel m;
	private JLabel x;
	private JPanel ujJatekps;
	private JButton vissza;
	private JButton start;
	
	public myFrame() {
		f=new JFrame("Nurikabe");
		cl=new CardLayout();
		kartyak=new JPanel();
		kartyak.setLayout(cl);
		
		menu();
		kartyak.add(menup,"menu");
		win();
		kartyak.add(winpanel, "win");
		
		f.add(kartyak);
		f.pack();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	public void setmaze(Maze uj) {
		maze=uj;
	}
	public void setb(Koordinate[][] uj) {
		b=uj;
	}
	
	public boolean ellenoriz() {
		Maze teszt=new Maze(b,maze.getX(),maze.getY());
		Maze teszt2=new Maze(b,maze.getX(),maze.getY());
		ArrayList<Koordinate> szigetek=new ArrayList<Koordinate>();
		int tesztTengerDb=0;
		int tesztSzigetMeret=0;
		int mazeSzigetDb=0;
		int mazeTengerDb=0;
		for(int i=0;i<maze.getX();i++) {
			for(int j=0;j<maze.getY();j++) {
				if(teszt.getKoordinate(i, j).getState()==-2) {
					tesztTengerDb++;
					teszt2.getKoordinate(i,j).setState(-1);
				}
				if(maze.getKoordinate(i, j).getState()==-1) {
					mazeTengerDb++;
				}
				if(maze.getKoordinate(i, j).getState()>0) {
					mazeSzigetDb++;
					szigetek.add(new Koordinate(i,j,maze.getKoordinate(i, j).getState()));
				}
				if(teszt.getKoordinate(i, j).getState()==-1||teszt.getKoordinate(i, j).getState()>0) {
					tesztSzigetMeret++;
					teszt2.getKoordinate(i,j).setState(-2);
				}
			}
		}
		if(tesztSzigetMeret==((maze.getX()*maze.getY())-mazeTengerDb)) {
			for(int row=0;row<maze.getX();row++) {
				for(int column=0;column<maze.getY();column++) {
					if(teszt2.getKoordinate(row, column).getState()==-2) {
						teszt2.getKoordinate(row,column).setState(0);
					}
					else {
						teszt2.getKoordinate(row,column).setState(-1);
					}
				}
			}
		}
		if(tesztTengerDb==mazeTengerDb) {
			for(int row=0;row<maze.getX();row++) {
				for(int column=0;column<maze.getY();column++) {
					if(teszt2.getKoordinate(row, column).getState()==-1) {
						teszt2.getKoordinate(row,column).setState(-1);
					}
					else {
						teszt2.getKoordinate(row,column).setState(0);
					}
				}
			}
		}
		
		if(tesztTengerDb==mazeTengerDb||tesztSzigetMeret==((maze.getX()*maze.getY())-mazeTengerDb)) {
			if(teszt2.negyzet(-4)) {
				for(int row=0;row<maze.getX();row++) {
					for(int column=0;column<maze.getY();column++) {
						if(teszt2.getKoordinate(row, column).getState()==-1) {
							teszt2.setTest();
							if(mazeTengerDb==teszt2.meret(row, column, 0)) {
								for(int i=0;i<mazeSzigetDb;i++) {
									teszt2.setTest();
									int szam=teszt2.meret(szigetek.get(i).getXkoord(), szigetek.get(i).getYkoord(), 1);
									if(szam!=maze.getKoordinate(szigetek.get(i).getXkoord(), szigetek.get(i).getYkoord()).getState()) {
										return false;
									}
								}
								return true;
							}
							return false;
						}
					}
				}
					
			}
			else {return false;}
		}
		else {
			return false;
		}
		return false;
	}
	
	public void mentes() throws IOException {
			FileOutputStream f=new FileOutputStream("mentes.txt");
			ObjectOutputStream out=new ObjectOutputStream(f);
			out.writeObject(maze);
			out.close();
			FileOutputStream f2=new FileOutputStream("mentes2.txt");
			ObjectOutputStream out2=new ObjectOutputStream(f2);
			out2.writeObject(b);
			out2.close();
		
	}
	public void betoltes(boolean teszt) throws ClassNotFoundException, IOException {
		File t=new File("mentes.txt");
		File t2=new File("mentes2.txt");
		if(t.exists()||t2.exists()) {
			FileInputStream f = new FileInputStream("mentes.txt");
		    ObjectInputStream in = new ObjectInputStream(f);
		    maze = (Maze) in.readObject();
		    in.close();
		    FileInputStream f2 = new FileInputStream("mentes2.txt");
		    ObjectInputStream in2 = new ObjectInputStream(f2);
		    b = (Koordinate[][]) in2.readObject();
		    in.close();
		    if(!teszt) {
		    	jatek(maze.getIdo().getIdo());
		    }
		    for(int i=0;i<maze.getX();i++) {
		    	for(int j=0;j<maze.getY();j++) {
		    		if(b[i][j].getState()==0) {
		    			b[i][j].setBackground(Color.WHITE);
		    		}
		    		else if(b[i][j].getState()>0){
		    			b[i][j].setBackground(Color.YELLOW);
		    		}
		    		else {
		    			b[i][j].setBackground(Color.BLUE);
		    		}
		    		b[i][j].addActionListener(valt(i,j));
		    		if(!teszt) {
						p.add(b[i][j]);
		    		}
		    	}
		    }
		    if(!teszt) {
				kartyak.add(jatek,"jatek");
				cl.show(kartyak, "jatek");
		    }
		}
	}
	
	public void felad() {
		maze.getIdo().stop();
		for(int i=0;i<maze.getX();i++) {
			for(int j=0;j<maze.getY();j++) {
				for(ActionListener al: b[i][j].getActionListeners()) {
					b[i][j].removeActionListener(al);
				}
				for(ActionListener al: mentesb.getActionListeners()) {
					mentesb.removeActionListener(al);
				}
				if(maze.getKoordinate(i, j).getState()==-1) {
					b[i][j].setBackground(Color.BLUE);
				}
				else {
					b[i][j].setBackground(Color.YELLOW);
				}
			}
		}
	}
	
	
	public ActionListener valt(final int i, final int j) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(b[i][j].getState()==0) {
		    		b[i][j].setState(-2);
		    		b[i][j].setBackground(Color.BLUE);
		    	}
		    	else if(b[i][j].getState()==-1) {
		    		b[i][j].setState(0);
		    		b[i][j].setBackground(Color.WHITE);
		    	}
		    	else if(b[i][j].getState()==-2) {
		    		b[i][j].setState(-1);
		    		b[i][j].setBackground(Color.YELLOW);
		    	}
		    	if(ellenoriz()) {
		    		cl.show(kartyak,"win");
		    	}
			}
		};
	}
	
	public void win() {
		winpanel=new JPanel(new BorderLayout());
		szoveg=new JLabel("Sikerült megtalálnod a megoldást!");
		menu2=new JButton("MENÜ");
		menu2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cl.show(kartyak, "menu");
				}
			});
		winpanel.add(szoveg,BorderLayout.CENTER);
		winpanel.add(menu2,BorderLayout.SOUTH);
	}
	
	public void menu() {
		menup=new JPanel(new GridLayout(2,1));
		menup.setPreferredSize(new Dimension(250,250));
		ujJatek=new JButton("ÚJ JÁTÉK");
		betoltesb=new JButton("MENTÉS BETÖLTÉSE");
		menup.add(ujJatek,BorderLayout.CENTER);
		menup.add(betoltesb,BorderLayout.SOUTH);
		
		ujJatek.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					ujJatek();
					kartyak.add(ujJatekp,"ujJatek");
					cl.show(kartyak, "ujJatek");
				}
			});
		betoltesb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					try {
						betoltes(false);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
	}
	
	public void ujJatek() {
		ujJatekp=new JPanel(new BorderLayout());
		ujJatekp.setPreferredSize(new Dimension(250,250));
		ujJatekpn=new JPanel();
		ujJatekp.add(ujJatekpn,BorderLayout.NORTH);
		
		ujJatekpc=new JPanel();
		m=new JLabel("MÉRET:");
		x=new JLabel("x");
		cbx=new JComboBox<Object>(meretek);
		cby=new JComboBox<Object>(meretek);
		ujJatekpc.add(m);
		ujJatekpc.add(cbx);
		ujJatekpc.add(x);
		ujJatekpc.add(cby);
		ujJatekp.add(ujJatekpc,BorderLayout.CENTER);
		
		ujJatekps=new JPanel();
		vissza=new JButton("VISSZA");
		start=new JButton("START");
		ujJatekps.add(vissza);
		ujJatekps.add(start);
		ujJatekp.add(ujJatekps, BorderLayout.SOUTH);
		

		vissza.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cl.show(kartyak, "menu");
				}
			});
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				maze=new Maze((Integer)cbx.getSelectedItem(),(Integer)cby.getSelectedItem());
				b=new Koordinate[maze.getX()][maze.getY()];			//0, ha semmi | -1,ha sziget | -2,ha tenger | egyéb ha meg van adva.
				jatek(0);
				kartyak.add(jatek,"jatek");
				kezdes(false);
				cl.show(kartyak, "jatek");
				}
			});
	}
	
	public void jatek(int i) {
		maze.setIdo(i);
		idot=new Thread(maze.getIdo());
		idot.start();
		
		
		menu=new JButton("MENÜ");
		feladb=new JButton("FELAD");
		mentesb=new JButton("MENTÉS");
		p=new JPanel();
		jatek=new JPanel();
		jatekap=new JPanel(new GridLayout(2,1));
		idop=new JPanel(new GridBagLayout());
		bp=new JPanel();
		p2=new JPanel();
		
		
		p.setPreferredSize(new Dimension(maze.getX()*500,maze.getY()*500));
		p.setLayout(new GridLayout(maze.getX(),maze.getY()));
		jatek.setLayout(new BorderLayout());

		idop.add(maze.getIdo());
		jatek.add(p,BorderLayout.CENTER);
		jatekap.add(idop);
		bp.add(menu);
		bp.add(feladb);
		bp.add(mentesb);
		jatekap.add(bp);
		p2.add(jatekap);
		jatek.add(p2,BorderLayout.SOUTH);

		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cl.show(kartyak, "menu");
				}
			});
		feladb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				felad();
				}
			});
		mentesb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					mentes();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				}
			});
	}
	
	
	public void kezdes(boolean teszt) {
		for(int i=0;i<maze.getX();i++) {
			for(int j=0;j<maze.getY();j++) {
				if(maze.getKoordinate(i, j).getState()>0) {
					b[i][j]=new Koordinate(i,j,maze.getKoordinate(i, j).getState());
				}
				else {
					b[i][j]=new Koordinate(i,j,0);
				}
				b[i][j].addActionListener(valt(i,j));
				if(!teszt) {
					p.add(b[i][j]);
				}
				
			}
		}
	}
}
