package Hospital;

class Psicoleg extends Metge {

	Psicoleg(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.PSICOLEG;
	}

	/**
	 * En cas d'assignaci� err�nia llen�a una excepci�.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
