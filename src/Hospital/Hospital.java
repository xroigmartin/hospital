package Hospital;

import java.util.*;

class Hospital {
	private Map<String, Metge> m_metges;
	private List<Pacient> m_pacients;

	Hospital() {
		m_metges = new HashMap<String, Metge>();
		m_pacients = new ArrayList<Pacient>();
	}

	/**
	 * Anota consulta entre el metge i pacient indicats. Retorna cert si ha
	 * funcionat correctament o false si l'hora escollida no estava disponible
	 * (tant pel pacient com pel metge).
	 */

	public void altaPacient(String nom){
		m_pacients.add(new Pacient(nom));
		
	}
	public boolean AnotarConsulta(Metge metge, Pacient pacient, Calendar data) {
		int horaIniciConsultes = metge.getHoraIniciConsultes(data
				.get(Calendar.DAY_OF_MONTH));
		// Comprova que no hi hagui ya una consulta reservada entre el metge i
		// el pacient
		if (existeixConsulta(metge, pacient, data)) {
			return false;
		} else {
			// Comprova que l'hora de la consulta esta dintre de l'horari del
			// metge
			if ((data.get(Calendar.HOUR_OF_DAY) < horaIniciConsultes || (data
					.get(Calendar.HOUR_OF_DAY)) > (horaIniciConsultes + 5))) {
				return false;
			} else {
				if ((data.get(Calendar.DAY_OF_MONTH) == 0 || (data
						.get(Calendar.DAY_OF_MONTH) == 6))) {
					return false;
				} else {
					Consulta c = new Consulta(metge, pacient, data);
					m_metges.get(metge.getNom()).AnotarConsulta(c);

					// Comprovar que el pacient no existeix dintre de la base de
					// dades
					/*if (m_pacients.indexOf(pacient) == -1) {
						pacient.setConsulta(c);
						m_pacients.add(pacient);
					} else {*/
						m_pacients.get(m_pacients.indexOf(pacient))
								.setConsulta(c);
					//}
				}
			}
		}

		return true;
	}

	private boolean existeixConsulta(Metge metge, Pacient pacient, Calendar data) {
		// Metode per comprovar si existeix una hora de consulta per el metge o
		// per el pacient en un dia concret
		boolean existeix = false;
		Calendar c;

		Iterator<Consulta> it = metge.getConsulta().iterator();

		// Comprova que el metge no tingui una consulta en aquella hora
		while (it.hasNext() && existeix == false) {
			c = it.next().getCalendari();
			if (c.get(Calendar.MINUTE) == data
					.get(Calendar.MINUTE)) {
				if(c.get(Calendar.MONTH) == data.get(Calendar.MONTH)){
					if(c.get(Calendar.HOUR_OF_DAY) == data.get(Calendar.HOUR_OF_DAY)){
						existeix = true;
					}
				}
			}
		}
		it = pacient.getConsulta().iterator();

		// Comprova que el pacient en aquella hora no tingui una altra consulta
		while (it.hasNext() && existeix == false) {
			c= it.next().getCalendari();
			if (c.get(Calendar.MINUTE) == data
					.get(Calendar.MINUTE)) {
				if(c.get(Calendar.MONTH) == data.get(Calendar.MONTH)){
					if(c.get(Calendar.HOUR_OF_DAY) == data.get(Calendar.HOUR_OF_DAY)){
						existeix = true;
					}
				}
			}
		}
		return existeix;
	}

	/**
	 * Dóna d'alta un nou metge i en retorna la referencia. Genera una excepció
	 * si el nom i cognoms ja existien.
	 */
	public Metge AltaMetge(String nomCognoms, Especialitat especialitat)
			throws IllegalArgumentException {
			Metge m = null;
			m = Metge.CrearMetge(especialitat, nomCognoms);
			m.setAlta(true);
			m_metges.put(nomCognoms, m);
		// Retorna el metge acabat de crear per poder assignar-li l'horari de
		// consultes
		return m;
	}

	/**
	 * Consulta un metge a partir del seu nom i cognoms i en retorna la refència
	 * o nul si no existia
	 */

	public Metge CercarMetge(String nomCognoms) {
		// Metode per buscar un metge concret
		return m_metges.get(nomCognoms);
	}

	public Metge BorrarMetge(String nomCognoms) {
		// Metode per esborrar un Metge
		if (CercarMetge(nomCognoms) == null) {
			System.out.println("No Existeis cap metge amb aquest nom");
		} else {
			if (m_metges.get(nomCognoms).getAlta() == false) {
				m_metges.remove(nomCognoms);
			} else {
				System.out
						.println("No s'ha pogut esborrar el metge perque encara te consultes que realitzar o no esta donat de baixa");
			}
		}
		return null;
	}

	public Collection<Metge> getMetges() {
		String dades;
		Iterator it = m_metges.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			Metge m = (Metge) e.getValue();
			dades = "El nom del metge es: " + e.getKey() + "\n";
			dades = dades + "La seva especialitat es: " + m.getEspecialitat() + "\n";
			dades = dades + "El seu horari d'inici de consultes es:\n";
			for(int i = 0; i < 5; i++){
				switch (i){
					case 0:
						dades = dades + "Dilluns: " + m.getHoraIniciConsultes(i)+ "\n";
						break;
					case 1:
						dades = dades + "Dimarts: " + m.getHoraIniciConsultes(i)+ "\n";
						break;
					case 2:
						dades = dades + "Dimecres: " + m.getHoraIniciConsultes(i)+ "\n";
						break;
					case 3:
						dades = dades + "Dijous: " + m.getHoraIniciConsultes(i)+ "\n";
						break;
					case 4:
						dades = dades + "Divendres: " + m.getHoraIniciConsultes(i)+ "\n";
						break;
				}
			}
			System.out.println(dades);
		}

		return null;
	}

	public boolean hihaMetge() {
		// Metode que comproba que hi hagui algun metge al centre
		if (m_metges.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hihaPacient() {
		// Metode que comproba que hi hagui algun pacient al centre
		if (m_pacients.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	int[] horariMetge(String nom) {
		// Metode per mostrar l'horari del metge
		int[] horari = new int[5];
		int i = 0;
		if (CercarMetge(nom) == null) {
			System.out.println("El Metge no existeix");
		} else {
			while (i <= 4) {
				horari[i] = m_metges.get(nom).getHoraIniciConsultes(i);
				i++;
			}
		}
		return horari;
	}

	public void donarBaixa(String nomCognoms) {
		// Metode per donar de baixa un metge
		boolean totes = true; // boolea que comprova que estan totes les consultes realitzades
		Iterator<Consulta> it = m_metges.get(nomCognoms).getConsulta().iterator();
		while(it.hasNext() && totes){
			if(!it.next().getConsultaFeta()){
				totes = false;
			}
		}
		if(totes){
			m_metges.get(nomCognoms).setAlta(false);
		}
	}

	public Pacient CercarPacient(String n_pacient) {
		// Metode que busca a un pacient dintre de l'array de pacient
		Pacient p = null;
		Pacient trobat = null;
		boolean existeix = false;
		Iterator<Pacient> itpacient = m_pacients.iterator();
		while (itpacient.hasNext() && existeix == false) {
			p = itpacient.next();
			if (p.nomPacient().equals(n_pacient)) {
				existeix = true;
				trobat = p;
			}
		}
		return trobat;
	}

	public boolean BorrarConsulta(Metge m, Pacient p, Calendar calendari) {
		// Metode que esborra la consulta especificada
		Consulta c;
		Iterator<Consulta> it_borrar;
		boolean esborrat = false;
		if (!existeixConsulta(m, p, calendari)) {
			return false;
		}

		it_borrar = m.getConsulta().iterator();
		while (it_borrar.hasNext() && !esborrat) {
			c = it_borrar.next();
			if (c.getCalendari().get(Calendar.HOUR_OF_DAY) == calendari
					.get(Calendar.HOUR_OF_DAY)) {
				if (c.getCalendari().get(Calendar.DAY_OF_WEEK) == calendari
						.get(Calendar.DAY_OF_WEEK)) {
					if (c.getCalendari().get(Calendar.MONTH) == calendari.get(Calendar.MONTH)) {
						if (c.getCalendari().get(Calendar.YEAR) == calendari
								.get(Calendar.YEAR)) {
							if(!c.getConsultaFeta()){
								it_borrar.remove();
								esborrat = true;
							}
						}
					}
				}
			}
		}

		if (esborrat) {
			esborrat = false;
			it_borrar = p.getConsulta().iterator();
			while (it_borrar.hasNext() && !esborrat) {
				c = it_borrar.next();
				if (c.getCalendari().get(Calendar.HOUR_OF_DAY) == calendari
						.get(Calendar.HOUR_OF_DAY)) {
					if (c.getCalendari().get(Calendar.DAY_OF_WEEK) == calendari
							.get(Calendar.DAY_OF_WEEK)) {
						if (c.getCalendari().get(Calendar.MONTH) == calendari
								.get(Calendar.MONTH)) {
							if (c.getCalendari().get(Calendar.YEAR) == calendari
									.get(Calendar.YEAR)) {
								it_borrar.remove();
								esborrat = true;
							}
						}
					}
				}
			}
		}

		return esborrat;
	}

	public boolean realitzaConsulta(Metge m, Pacient p, Calendar calendari) {
		// Metode que s'encarrega de marcar si la consulta s'ha ralitzat o no
		boolean realitzada = false;
		Consulta c;
		Iterator<Consulta> it_realitzar;
		boolean esborrat = false;
		if (!existeixConsulta(m, p, calendari)) {
			return false;
		}

		it_realitzar = m.getConsulta().iterator();
		while (it_realitzar.hasNext() && !esborrat) {
			c = it_realitzar.next();
			if (c.getCalendari().get(Calendar.HOUR_OF_DAY) == calendari
					.get(Calendar.HOUR_OF_DAY)) {
				if (c.getCalendari().get(Calendar.DAY_OF_WEEK) == calendari
						.get(Calendar.DAY_OF_WEEK)) {
					if (c.getCalendari().get(Calendar.MONTH) == calendari
							.get(Calendar.MONTH)) {
						if (c.getCalendari().get(Calendar.YEAR) == calendari
								.get(Calendar.YEAR)) {
							c.setCosultaFeta();
							esborrat = true;
						}
					}
				}
			}
		}

		if (esborrat) {
			esborrat = false;
			it_realitzar = p.getConsulta().iterator();
			while (it_realitzar.hasNext() && !esborrat) {
				c = it_realitzar.next();
				if (c.getCalendari().get(Calendar.HOUR_OF_DAY) == calendari
						.get(Calendar.HOUR_OF_DAY)) {
					if (c.getCalendari().get(Calendar.DAY_OF_WEEK) == calendari
							.get(Calendar.DAY_OF_WEEK)) {
						if (c.getCalendari().get(Calendar.MONTH) == calendari
								.get(Calendar.MONTH)) {
							if (c.getCalendari().get(Calendar.YEAR) == calendari
									.get(Calendar.YEAR)) {
								c.setCosultaFeta();
								realitzada = true;
							}
						}
					}
				}
			}
		}

		return realitzada;
	}

	public void consultesPendents(Pacient p) {
		// Metode que gestiona la cerca de consultes pendents dels pacients
		Iterator<Consulta> it = p.getConsulta().iterator();
		String info;
		while (it.hasNext()) {
			Consulta dades = it.next();
			if (!dades.getConsultaFeta()) {
				info = "El nom del pacient es: "
						+ dades.getPacient().nomPacient() + "\n";
				info = info
						+ "L'hora de consulta es: "
						+ dades.getCalendari().getDisplayName(
								Calendar.DAY_OF_WEEK, Calendar.LONG,
								new Locale("ca")) + "a les "
						+ dades.getCalendari().getTime().getHours() + ":00 \n";
				info = info + "El metge es: " + dades.getMetge().getNom()
						+ " i la seva especialitat es: "
						+ dades.getMetge().getEspecialitat();
				System.out.println(info);
			}
		}
	}

}
