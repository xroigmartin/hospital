package Hospital;

class Dermatoleg extends Metge {

	Dermatoleg(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.DERMATOLEG;
	}

	/**
	 * En cas d'assignaci� err�nia llen�a una excepci�.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
