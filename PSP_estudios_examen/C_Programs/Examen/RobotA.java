package Tema1.Examen;

public class RobotA extends Thread {
	final Cinta cinta;
	int kilos;
	int tiempo;

	public RobotA(Cinta cinta, int kilos, int tiempo) {
		this.cinta = cinta;
		this.kilos = kilos;
		this.tiempo = tiempo * 1000;
	}

	public void run() {
		synchronized (cinta) {
			while (true) {
				try {
					cinta.notify();
					sleep(tiempo);
					cinta.cargaKilos(kilos);
					System.out.printf("->Se ponen: %d kilos. Quedan: %d kilos en la cinta.\n", kilos, cinta.getKilos());
					cinta.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
