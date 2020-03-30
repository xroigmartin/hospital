package Hospital;

class Odontoleg extends Metge {

	public Odontoleg(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.ODONTOLEG;
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
