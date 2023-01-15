package nurikabe;

import java.io.Serializable;
import java.util.*;

public class Maze implements Serializable{
	  	//0 a sziget
		//-1 a tenger
	private int x=5;
	private int y=5;
	private int db=0;
	private Ido ido;
	private Koordinate[][] koords=new Koordinate[x][y];
	private Koordinate[][] test=new Koordinate[x][y];
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Koordinate getKoordinate(int a, int b) {
		return koords[a][b];
	}
	public Ido getIdo() {
		return ido;
	}
	public void setIdo(int i) {
		ido=new Ido(i);
	}
	
	Maze(int a, int b){
		x=a;
		y=b;
	    koords=new Koordinate[x][y];
	    test=new Koordinate[x][y];

	    do{
	    	db=0;
	    	for(int i=0;i<x;i++){
	    		for(int j=0;j<y;j++){
	    			koords[i][j]=new Koordinate(i,j);
	    			test[i][j]=new Koordinate(i,j);
	    			if(Math.random()<0.5){
	    				koords[i][j].setState(-1);
	    				test[i][j].setState(-1);
	    			}
	    			else{
	    				koords[i][j].setState(0);
	    				test[i][j].setState(0);
	    			}
	    		}
	    	}
	     
	    } while(!solvable());
	    szamok();
	  }
	
	Maze(){
	    koords=new Koordinate[x][y];
	    test=new Koordinate[x][y];
	}

	Maze(Koordinate[][] matrix, int a, int b){
		x=a;
	    y=b;
	    koords=new Koordinate[x][y];
	    test=new Koordinate[x][y];
	    for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			koords[i][j]=new Koordinate(i,j);
    			test[i][j]=new Koordinate(i,j);
    			koords[i][j].setState(matrix[i][j].getState());
    			test[i][j].setState(matrix[i][j].getState());
    			}
    		}
	}
	  
 	public boolean solvable(){
	    if(!negyzet(-4)){
	    	return false;
	    }
	    if(!folytonos()){
	    	return false;
	    }
	    return true;
 	}
	  
 	public boolean negyzet(int szam){
	    int szum=0;
	    for(int i=0;i<x-1;i++){
	    	for(int j=0;j<y-1;j++){
	    		szum=koords[i][j].getState()+koords[i+1][j].getState()+koords[i][j+1].getState()+koords[i+1][j+1].getState();
	    		if(szum==szam){
	    			return false;
	    		}
	    	}
	    }
	    return true;
 	}
 	
 	public boolean folytonos(){
 		int sum=szum(koords);
 		int size=0;
 		for(int row=0; row<x;row++) {
 			for(int column=0;column<y;column++) {
 				if(test[row][column].getState()==-1) {
 					size=meret(row,column,0);
 				}
 			}
 		}
 		if(size==sum){
 			return true;
 		}
 		return false;
 	}
 	
 	public int szum(Koordinate[][] matrix) {
 		int sum=0;
 		for(int row=0; row<x;row++) {
 			for(int column=0;column<y;column++) {
 				sum-=matrix[row][column].getState();
 			}
 		}
 		return sum;
 	}

 	public void szamok() {
 		for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			test[i][j]=new Koordinate(i,j);
    			test[i][j].setState(koords[i][j].getState());
    		}
 		}
 		
 		Random rand=new Random();
 		while(szum(test)<x*y) {
 			int row=rand.nextInt(x);
 			int column=rand.nextInt(y);
 			
 			if(koords[row][column].getState()==0) {
 	 			koords[row][column].setState(meret(row,column,-1));
 			}
 		}
 	}

 	public int meret(int row, int column,int szam) {
 		if(row<0||column<0||row>=test.length||column>=test[row].length) {
 			return 0; 
 		}
 		if(test[row][column].getState()==szam) {
 			return 0;
 		}
 		test[row][column].setState(szam);
 		int size=1;
 		for(int r=row-1;r<=row+1;r++) {
 			for(int c=column-1;c<=column+1;c++) {
 				if(r==row-1&&c==column-1||r==row+1&&c==column-1||r==row-1&&c==column+1||r==row+1&&c==column+1) {}
 				else if(r!=row||c!=column) {
 					size+=meret(r,c,szam);
 				}
 			}
 		}
 		return size;
 	}
 	
 	public void setTest() {
 		for(int i=0;i<x;i++){
    		for(int j=0;j<y;j++){
    			test[i][j]=new Koordinate(i,j);
    			if(koords[i][j].getState()>=0) {
    				test[i][j].setState(0);
    			}
    			else {
    				test[i][j].setState(1);
    			}
    			
    		}
 		}
 	}
  /*
 	public void kiir(){
 		for(int i=0;i<x;i++){
 			System.out.print("|");
 			for(int j=0;j<y;j++){
 				System.out.print(koords[i][j].getState());
 			}
 			System.out.println("|");
 		}
 	}*/
 	
}
