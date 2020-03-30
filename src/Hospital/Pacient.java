package Hospital;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Pacient {
	private String m_nom;
	private List<Consulta> p_consultes;

	Pacient(String p_nom) {
		m_nom = p_nom;
		p_consultes = new ArrayList<Consulta>();
	}

	/**
	 * Comprova que el pacient no tingui la data de consultes ocupada
	 */

	public boolean ComprovaData(Calendar data) {
		return false;
	}

	public Collection<Consulta> getConsultes() {
		return p_consultes;
	}

	public String nomPacient() {
		return m_nom;
	}

	public void setConsulta(Consulta c) {
		p_consultes.add(c);
	}

	public List<Consulta> getConsulta() {
		return p_consultes;
	}

	public Collection<Consulta> getHistoric() {
		// Metode per crear l'historic de consultes del pacient
		ArrayList<Consulta> historic = new ArrayList<Consulta>();
		Iterator<Consulta> it = p_consultes.iterator();
		while (it.hasNext()) {
			historic.add(it.next());
		}
		if(historic.size()==0){
			System.out.println("No ha realitzat cap consulta encara");
		}
		Collections.sort(historic, new Ordenar());
		return historic;
	}

}
