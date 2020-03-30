package Hospital;

class MetgeFamilia extends Metge {

	MetgeFamilia(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.METGE_FAMILIA;
	}

	/**
	 * En cas d'assignació errònia llença una excepció.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
