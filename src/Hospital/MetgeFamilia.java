package Hospital;

class MetgeFamilia extends Metge {

	MetgeFamilia(String nomCognoms) {
		super(nomCognoms);
	}

	public Especialitat getEspecialitat() {
		return Especialitat.METGE_FAMILIA;
	}

	/**
	 * En cas d'assignaci� err�nia llen�a una excepci�.
	 */
	public void setHoraIniciConsultes(int dia, int horaInicial)
			throws IllegalArgumentException {
		super.gethorari()[dia] = horaInicial;
	}

}
