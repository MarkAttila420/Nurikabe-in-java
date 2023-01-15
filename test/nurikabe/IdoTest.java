package nurikabe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IdoTest {
	Ido egy;
	Ido ketto;
	Ido harom;
	Ido negy;
	@Before
	public void setup() {
		egy=new Ido(671);	//11:11
		ketto=new Ido(661);	//11:01
		harom=new Ido(71);	//01:11
		negy=new Ido(61);	//01:01
	}
	
	@Test
	public void idosTeszt() {
		String string=egy.idos();
		assertEquals("nem jo az elsõ:","11:11",string);
		string=ketto.idos();
		assertEquals("nem jo a második:","11:01",string);
		string=harom.idos();
		assertEquals("nem jo a harmadik:","01:11",string);
		string=negy.idos();
		assertEquals("nem jo a negyedik:","01:01",string);
	}

}
