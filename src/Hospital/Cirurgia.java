package Hospital;

class Cirurgia extends Metge {

	Cirurgia(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.CIRURGIA;
	}

	/**
	 * En cas d'assignaci� err�nia llen�a una excepci�.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
