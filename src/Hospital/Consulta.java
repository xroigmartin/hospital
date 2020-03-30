package Hospital;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

class Consulta {
	private Metge m;
	private Pacient p;
	private Calendar c;
	private boolean realitzada; //variable que controla que la consulta s'hagi fet
								// True = consulta realitzada, False = consulta no realitzada

	public Consulta(Metge metge, Pacient pacient, Calendar c) {
		m = metge;
		p = pacient;
		this.c = new GregorianCalendar();
		this.c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		//Crea la data de la consulta dintre del calendari
		realitzada = false;
	}

	public Pacient getPacient() {
		return p;
	}

	public Metge getMetge() {
		return m;
	}

	public Calendar getCalendari() {
		return this.c;
	}

	// Metode que serveix per dir que la consulta ja a estat realitzada
	public void setCosultaFeta() {
		realitzada = true;
	}

	// Metode que retorna l'estat de la consulta;
	public boolean getConsultaFeta() {
		return realitzada;
	}

	public String EsciureHistoric() {
		// Metode per poder escriure l'historic del pacient

		String historic = "El nom del pacient es: " + p.nomPacient() + ".\n";
		historic = historic + "El metge amb el que te una consulta es: "
				+ m.getNom() + ".\n";
		historic = historic
				+ "La seva data de consulta es el "
				+ c.get(Calendar.MINUTE) +" de " + c.getDisplayName(Calendar.MONTH, Calendar.LONG,
						new Locale("ca")) + " a les " + c.getTime().getHours() + ":00 \n";
		historic = historic + "La consulta esta passada : ";
		if (realitzada) {
			historic = historic + "Si";
		} else {
			historic = historic + "No";
		}

		return historic;
	}
}
