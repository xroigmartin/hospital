package Hospital;

/**
 * Manté les especialitats de tots els metges que hi poden haver a l'hospital i
 * permet consultar el nom d'aquestes
 */
enum Especialitat {
	// Tipus de metge i la seva especialitat. Li Passen al constructor
	// l'especialitat del metge
	METGE_FAMILIA("Revisio General"), CIRURGIA("Operar"), ANESTESISTA(
			"Anestesiar"), PSICOLEG("Terapia"), ODONTOLEG("Extreure Queixal"), RADIOLEG(
			"Generar Radiografia"), DERMATOLEG("Fer proves al·lergiques"), GINECOLEG(
			"Revisio");

	private String esp;

	// Forcem al constructor a passar-li el parametre al definir un nou tipus
	Especialitat(String esp) {
		this.esp = esp;
	}

	public String getEspecialitat() {
		return esp;
	}
}
