package Hospital;

class Anestesia extends Metge {

	Anestesia(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.RADIOLEG;
	}

	@Override
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;

	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */

}
