package Hospital;

class Cirurgia extends Metge {

	Cirurgia(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.CIRURGIA;
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
