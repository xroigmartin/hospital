package Hospital;

class Radioleg extends Metge {

	Radioleg(String nomCognoms) {
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
	 * En cas d'assignaci� err�nia llen�a una excepci�.
	 */

}
