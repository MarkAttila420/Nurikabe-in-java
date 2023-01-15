package nurikabe;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class myFrameTest {
	Koordinate[][] teszt1=new Koordinate[4][4];
	int[][] teszt1i={{0,-1,-1,0},
					 {0, 0,-1,4},
					 {7, 0,-1,0},
					 {0, 0,-1,0}};
	Maze teszt1m;
	Koordinate[][] b;
	
	int[][] teszt2i={{-1, 0, 0,-1},
					 {-1,-1, 0, 4},
					 { 7,-1, 0,-1},
					 {-1,-1, 0,-1}};
	Koordinate[][] b2;
	
	int[][] teszt3i={{ 0,-2,-2, 0},
			 		 { 0, 0,-2, 4},
			 		 { 7, 0,-2, 0},
			 		 { 0, 0,-2, 0}};
	Koordinate[][] b3;
	
	myFrame f;
	
	@Before
	public void setup() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				teszt1[i][j]=new Koordinate(j, j,teszt1i[i][j]);
			}
		}
		teszt1m=new Maze(teszt1,4,4);
		b=new Koordinate[4][4];
		b2=new Koordinate[4][4];
		b3=new Koordinate[4][4];
		f=new myFrame();
	}
	
	@Test
	public void kezdestest() {
		f.setmaze(teszt1m);
		f.setb(b);
		f.kezdes(true);
		int x;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				x=b[i][j].getState();
				if(teszt1i[i][j]>0) {
					assertEquals("Nem jó a következõ koordináta: "+i+" : "+j,teszt1i[i][j],x);
				}
				else {
					assertEquals("Nem jó a következõ koordináta: "+i+" : "+j,0,x);
				}
				
			}
		}
	}
	
	@Test
	public void ellenoriztest() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				b[i][j]=new Koordinate(i,j,teszt1i[i][j]);
				b2[i][j]=new Koordinate(i,j,teszt2i[i][j]);
				b3[i][j]=new Koordinate(i,j,teszt3i[i][j]);
			}
		}
		boolean bool;
		f.setmaze(teszt1m);
		f.setb(b);
		bool=f.ellenoriz();
		assertEquals("",false,bool);
		f.setb(b2);
		bool=f.ellenoriz();
		assertEquals("",true,bool);
		f.setb(b3);
		bool=f.ellenoriz();
		assertEquals("",true,bool);
	}
	
	@Test
	public void betoltestest() throws ClassNotFoundException, IOException {
		f.setmaze(teszt1m);
		f.setb(b);
		f.kezdes(true);
		
		f.mentes();
		f.betoltes(true);
	}
	@Test(expected=IOException.class)
	public void betoltestest2() throws ClassNotFoundException, IOException {
		File file = new File("mentes.txt");
		file.delete();
		f.betoltes(true);
	}

}
