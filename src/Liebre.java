
public class Liebre extends Thread{
	
	public static final byte ID = 1;
	
	private Carrera carrera;
	private int posicion;
	
	public Liebre(Carrera carrera){
		this.carrera = carrera;
		posicion = 1;
	}
	
	@Override
	public void run(){
		while(!carrera.isEnProgreso()){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(carrera.isEnProgreso()){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			posicion += carrera.setCasilla(ID);
			if(posicion < 1)
				posicion = 1;
			else if(posicion > 70){
				posicion = 70;
				carrera.finalizar(ID);
			}

			for(byte indice = 0; indice < posicion;indice++){
				System.out.print(" ");
			}
			
			System.out.print("L\n");
			
			
		}

	}


}
