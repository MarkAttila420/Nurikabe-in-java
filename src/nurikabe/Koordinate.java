package nurikabe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;

public class Koordinate extends JButton implements Serializable{
	  private int x;
	  private int y;
	  private int state;
	  
	  Koordinate(int a, int b){
	    x=a;
	    y=b;
	    state=0;
	  }
	  
	  Koordinate(int a, int b,int c){
	    x=a;
	    y=b;
	    state=c;
		if(state>0) {
			this.setText(String.valueOf(state));
			this.setBackground(Color.YELLOW);
		}
		else {
			this.setBackground(Color.WHITE);
		}
	  }

	  public void setState(int a){
	  	state=a;
	  }
	  public int getState(){
	    return state;
	  }
	  public int getXkoord() {
		  return x;
	  }
	  public int getYkoord() {
		  return y;
	  }
}
