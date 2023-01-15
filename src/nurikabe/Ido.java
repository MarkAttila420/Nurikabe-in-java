package nurikabe;

import javax.swing.JLabel;

public class Ido extends JLabel implements Runnable{
	private int ido=0;
	private boolean fut=true;
	
	public int getIdo() {
		return ido;
	}
	public void setIdo(int szam) {
		ido=szam;
	}
	
	public Ido(int i){
		ido=i;
	}
	public void stop() {
		fut=false;
	}
	
	public void run() {
		while(fut) {
			setText(idos());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ido++;
		}
	}
	
	public String idos() {
		int mp=ido%60;
		int p=(ido-mp)/60;
		String szo;
		
		if(mp<10&&p>=10) {
			szo=p+":0"+mp;
			return szo;
		}
		else if(mp<10&&p<10) {
			szo="0"+p+":0"+mp;
			return szo;
		}
		else if(mp>=10&&p<10) {
			szo="0"+p+":"+mp;
			return szo;
		}
		else{
			szo=p+":"+mp;
			return szo;
		}
	}
}
