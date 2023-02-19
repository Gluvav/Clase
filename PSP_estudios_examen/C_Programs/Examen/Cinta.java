package Tema1.Examen;

public class Cinta {
	int kilos = 0;

	public void cargaKilos(int kilos) {
		this.kilos += kilos;
	}

	public boolean sacarKilos() {
		if (this.kilos - 5 < 0) {
			return false;
		} else {
			this.kilos -= 5;
			System.out.println("--> Se saca 1 bolsa de: 5 kilos. Quedan: "+ kilos+ " kilos en la cinta.");
			return true;
		}
	}

	public int getKilos() {
		return kilos;
	}
}
