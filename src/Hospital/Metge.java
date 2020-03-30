package Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Vector;

abstract class Metge {
	private List<Consulta> m_consultes = new ArrayList<Consulta>();
	private String m_nomCognoms;
	private boolean alta;
	private int[] m_horaIniciConsultes = new int[5];

	Metge(String nomCognoms) {
		m_nomCognoms = nomCognoms;
		alta = false;
	}

	/**
	 * Metode estatic factoria que permet crear una instancia de metge a partir
	 * de la seva especialitat
	 */
	public static Metge CrearMetge(Especialitat especialitat,
			String m_nomCognoms) {
		switch (especialitat) {
		case CIRURGIA:
			return new Cirurgia(m_nomCognoms);
		case ANESTESISTA:
			return new Anestesia(m_nomCognoms);
		case PSICOLEG:
			return new Psicoleg(m_nomCognoms);
		case ODONTOLEG:
			return new Odontoleg(m_nomCognoms);
		case DERMATOLEG:
			return new Dermatoleg(m_nomCognoms);
		case RADIOLEG:
			return new Radioleg(m_nomCognoms);
		case GINECOLEG:
			return new Ginecoleg(m_nomCognoms);
		case METGE_FAMILIA:
			return new MetgeFamilia(m_nomCognoms);
		}
		return null;
	}

	public String getNom() {
		return m_nomCognoms;
	}

	public abstract Especialitat getEspecialitat();

	/**
	 * Retorna l'hora d'inici de les consultes pel dia donat (0 indica diumenge)
	 */
	public int getHoraIniciConsultes(int dia) {
		return m_horaIniciConsultes[dia];
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public abstract void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException;

	public void AnotarConsulta(Consulta consulta) {
		m_consultes.add(consulta);
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public boolean getAlta() {
		return alta;
	}

	public int[] gethorari() {
		// TODO Auto-generated method stub
		return m_horaIniciConsultes;
	}

	public List<Consulta> getConsulta() {
		return m_consultes;
	}
}
