package Tema1.Examen;

public class RobotB extends Thread {
	final Cinta cinta;
	int mallas;
	int tiempo;

	public RobotB(Cinta cinta, int mallas, int tiempo) {
		this.cinta = cinta;
		this.mallas = mallas;
		this.tiempo = tiempo * 1000;
	}

	public void run() {
		synchronized (cinta) {
			while (true) {
				try {
					cinta.notify();
					sleep(tiempo);
					for (int i = 0; i < mallas; i++) {
						if (!(cinta.sacarKilos())) {
							System.out.printf("**** No puedo sacar 5 kilos. Solo quedan %d kilos.\n", cinta.getKilos());
							cinta.wait();
							System.out.println("**** Ya puedo seguir sacando.");
							cinta.notify();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
