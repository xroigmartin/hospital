
package Hospital;

class Ginecoleg extends Metge {
	
	Ginecoleg(String nomCognoms){
		super(nomCognoms);
	}
	
  public Especialitat getEspecialitat() {
	return Especialitat.GINECOLEG;  
  }

  /**
   * En cas d'assignaci� err�nia llen�a una excepci�.
   */
  public void setHoraIniciConsultes(int dia, int horaInicial) throws IllegalArgumentException {
	  super.gethorari()[dia]=horaInicial;
  }

}
