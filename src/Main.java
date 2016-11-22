
public class Main {

	public static void main(String[] args){
		Carrera carrera = new Carrera();
		Liebre liebre = new Liebre(carrera);
		Tortuga tortuga = new Tortuga(carrera);
		
		tortuga.start();
		liebre.start();
		carrera.comenzarCarrera();
		
		try {
			tortuga.join();
			liebre.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		carrera.declararGanador();
	}
}
