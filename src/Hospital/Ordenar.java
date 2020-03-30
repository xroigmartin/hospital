package Hospital;

import java.util.Comparator;

public class Ordenar implements Comparator {

	public int compare(Object o0, Object o1) {
		Consulta c0 = (Consulta) o0;
		Consulta c1 = (Consulta) o1;
		return c0.getCalendari().compareTo(c1.getCalendari());
	}
}
