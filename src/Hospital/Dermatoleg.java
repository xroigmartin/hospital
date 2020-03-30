package Hospital;

class Dermatoleg extends Metge {

	Dermatoleg(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.DERMATOLEG;
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
