package Hospital;

class Psicoleg extends Metge {

	Psicoleg(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.PSICOLEG;
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
