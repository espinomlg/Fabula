import java.util.Random;

public class Carrera {
	
	//sucesos tortuga
	public static final int RESBALON = -6;
	public static final int AVANCE_LENTO = 1;
	public static final int AVANCE_RAPIDO = 3;
	
	//sucesos liebre
	public static final int DORMIR = 0;
	public static final int RESBALON_GRANDE = -12;
	public static final int RESBALON_PEQUENO = -2;
	public static final int SALTO_PEQUENO = 1;
	public static final int SALTO_GRANDE = 9;
	
	private static Random rnd = new Random();
	private boolean enProgreso;
	private byte ganador;
	
	
	public Carrera(){
		enProgreso = false;
		ganador = 0;
	}
	
	public synchronized int setCasilla(byte animal){//true = liebre || false = tortuga
		int movimiento = 0;
		
		switch (animal) {
		
		case Liebre.ID:
			movimiento = sucesoLiebre();
			break;

		case Tortuga.ID:
			movimiento = sucesoTortuga();
			break;
		}
				
		return movimiento;
		
	}

	private int sucesoTortuga(){

		int suceso = -1;
		int prob = rnd.nextInt(100);

		if(prob <= 49)
			suceso = AVANCE_RAPIDO;
		else if(prob > 49 && prob <= 69)
			suceso = RESBALON;
		else if(prob > 69 && prob <= 99)
			suceso = AVANCE_LENTO;


		return suceso;
	}
	
	private int sucesoLiebre(){
		int suceso = -1;
		int prob = rnd.nextInt(100);
		
		if(prob <= 29)
			suceso = SALTO_PEQUENO;
		else if(prob > 29 && prob <= 49)
			suceso = DORMIR;
		else if(prob > 49 && prob <= 69)
			suceso = RESBALON_PEQUENO;
		else if(prob > 69 && prob <= 89)
			suceso = SALTO_GRANDE;
		else if(prob > 89 && prob <= 99)
			suceso = RESBALON_GRANDE;
			
		return suceso;
	}
	
	public synchronized void finalizar(byte id){
		enProgreso = false;
		ganador += id;
	}
	
	public synchronized void comenzarCarrera(){
		enProgreso = true;
		System.out.println("\t---La carrera ha comenzado---");
		notifyAll();
	}
	
	public void declararGanador(){
		
		switch (ganador) {
		case Tortuga.ID:
			System.out.println("\n\t---Ha ganado la tortuga---");
			break;

		case Liebre.ID:
			System.out.println("\n\t---Ha ganado la liebre---");
			break;
			
		case Tortuga.ID + Liebre.ID:
			System.out.println("\n\t---Ha habido un empate---");
			break;
		}
	}
	
	public synchronized boolean isEnProgreso(){
		return enProgreso;
	}
}
