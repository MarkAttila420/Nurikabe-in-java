package nurikabe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MazeTest {
	Koordinate[][] teszt1=new Koordinate[4][4];
	int[][] teszt1i={{0,-1,-1,0},
					 {0,-1,-1,0},
					 {0, 0, 0,0},
					 {0, 0, 0,0}};
	Maze teszt1m;
	
	Koordinate[][] teszt2=new Koordinate[4][4];
	int[][] teszt2i={{0,-1, 0,0},
					 {0,-1,-1,0},
					 {0, 0, 0,0},
					 {0, 0, 0,0}};
	Maze teszt2m;
	
	Koordinate[][] teszt3=new Koordinate[4][4];
	int[][] teszt3i={{ 0,-1, 0, 0},
					 { 0,-1,-1, 0},
					 { 0, 0, 0, 0},
				 	 {-1,-1,-1,-1}};
	Maze teszt3m;
	
	Koordinate[][] teszt4=new Koordinate[4][4];
	int[][] teszt4i={{-1, 0,-1, 0},
					 {-1, 0,-1,-1},
					 {-1, 0,-1, 0},
				 	 {-1,-1,-1,-1}};
	Maze teszt4m;
	
	
	@Before
	public void setup() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				teszt1[i][j]=new Koordinate(j, j,teszt1i[i][j]);
				teszt2[i][j]=new Koordinate(j, j,teszt2i[i][j]);
				teszt3[i][j]=new Koordinate(j, j,teszt3i[i][j]);
				teszt4[i][j]=new Koordinate(j, j,teszt4i[i][j]);
			}
		}
		teszt1m=new Maze(teszt1,4,4);
		teszt2m=new Maze(teszt2,4,4);
		teszt3m=new Maze(teszt3,4,4);
		teszt4m=new Maze(teszt4,4,4);
	}
	
	@Test
	public void negyzettest() {
		boolean bool=teszt1m.negyzet(-4);
		assertEquals("Nem detektálta a négyzetet, pedig volt.",false,bool);
		bool=teszt2m.negyzet(-4);
		assertEquals("Detektálta a négyzetet, pedig nem volt.",true,bool);
	}
	
	@Test
	public void folytonostest() {
		boolean bool=teszt1m.folytonos();
		assertEquals("Azt detektálta, hogy nem folytonos, pedig az volt.",true,bool);
		bool=teszt2m.folytonos();
		assertEquals("Azt detektálta, hogy nem folytonos, pedig az volt.",true,bool);
		bool=teszt3m.folytonos();
		assertEquals("Azt detektálta, hogy folytonos, pedig nem volt az.",false,bool);
	}
	
	@Test
	public void szumtest() {
		int x=teszt1m.szum(teszt1);
		assertEquals("Rossz a szum().",4,x);
		x=teszt2m.szum(teszt2);
		assertEquals("Rossz a szum().",3,x);
		x=teszt3m.szum(teszt3);
		assertEquals("Rossz a szum().",7,x);
	}
	
	@Test
	public void merettest() {
		int x;
		x=teszt1m.meret(0,1,0);
		assertEquals("Rossz a meret().",4,x);
		teszt1m.setTest();
		x=teszt1m.meret(0,0,1);
		assertEquals("Rossz a meret().",12,x);
		
		x=teszt2m.meret(0,1,0);
		assertEquals("Rossz a meret().",3,x);
		teszt2m.setTest();
		x=teszt2m.meret(0,0,1);
		assertEquals("Rossz a meret().",13,x);
		
		x=teszt3m.meret(0,1,0);
		assertEquals("Rossz a meret().",3,x);
		teszt3m.setTest();
		x=teszt3m.meret(0,0,1);
		assertEquals("Rossz a meret().",9,x);
		teszt3m.setTest();
		x=teszt3m.meret(3,0,0);
		assertEquals("Rossz a meret().",4,x);
	}
	
	@Test
	public void solvabletest() {
		boolean bool=teszt1m.solvable();
		assertEquals("Azt detektálta, hogy folytonos, pedig nem volt az.",false,bool);
		bool=teszt2m.solvable();
		assertEquals("Azt detektálta, hogy folytonos, pedig nem volt az.",true,bool);
		bool=teszt3m.solvable();
		assertEquals("Azt detektálta, hogy folytonos, pedig nem volt az.",false,bool);
	}
	
	@Test
	public void szamoktest() {
		teszt4m.szamok();
		boolean bool=false;
		if(teszt4m.getKoordinate(0, 1).getState()==3||teszt4m.getKoordinate(1, 1).getState()==3||teszt4m.getKoordinate(2, 1).getState()==3) {
			bool=true;
		}
		assertEquals("Azt detektálta, hogy folytonos, pedig nem volt az.",true,bool);
		
	}
	

}
