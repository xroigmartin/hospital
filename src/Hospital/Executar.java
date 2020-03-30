package Hospital;

/* Aquesta classe s'encarrega de la gestio de tot l'aplicatiu */

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

public class Executar {
	private static Hospital hospital;
	private static Metge m;
	private static int opcio;
	private static Calendar calendari;

	/**
	 * @param args
	 */

	public static void main(String[] args) throws IOException {

		// Metode per triar una opcio del menu principal
		hospital = new Hospital();
		// Instancia la classe Calendar i crea un calendari Gregoria
		calendari = new GregorianCalendar();
		m = null;
		opcio = 0;
		opcioPrincipal();

	}

	private static void opcioPrincipal() throws IOException {
		// Metode que gestona el menu principal de l'aplicacio
		opcio = 0;
		System.out.println("Benvingut a l'aplicatiu de l'Hospital");
		System.out.println();
		System.out.println("Esculliu una opcio per continuar");
		System.out.println();
		System.out.println("1. Gestió Metges");
		System.out.println("2. Gestió Consultes");
		System.out.println("3. Sortir");
		System.out.println();
		System.out.print("Quina opcio trieu: ");

		try {
			opcio = Integer.parseInt(llegirTeclat());
		} catch (Exception e) {
			System.out.println("Has d'escriure un numero entre 1 i 3");
		}

		switch (opcio) {
		case 1:
			gestioMetge();
			break;
		case 2:
			gestioConsultes();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			System.out.println("Opcio Erronia");
			System.out.println();
			opcioPrincipal();
		}

	}

	private static void gestioMetge() throws IOException {
		// Metode que gestiona el menu dels metges
		opcio = 0;
		System.out.println();
		System.out.println("Que vols fer amb els Metges");
		System.out.println("1. Alta de Metge");
		System.out.println("2. Modificar Dades Metge");
		System.out.println("3. Baixa de Metge");
		System.out.println("4. Llistat de tots els Metges del Centre");
		System.out.println("5. Tornar al menu principal");
		System.out.println();
		System.out.print("Quina opcio tries: ");

		try {
			opcio = Integer.parseInt(llegirTeclat());
		} catch (Exception e) {
			System.out.println("Has d'escriure un numero entre 1 i 5");
		}

		switch (opcio) {
		case 1:
			altaMetge();
			break;
		case 2:
			modificarMetge();
			break;
		case 3:
			borrarMetge();
			break;
		case 4:
			mostrarMetge();
			break;
		case 5:
			opcioPrincipal();
			break;
		default:
			System.out.println("Opcio Erronia");
			System.out.println();
			gestioMetge();
		}

	}

	private static void modificarMetge() throws IOException {
		// Metode per gestionar la modificacio del metge
		opcio = 0;
		int j = 0;
		System.out
				.println("Introdueix les dades personals del metge que es vol modificar");
		String nom = introduirDades();
		mostrarHorari(nom);

		System.out.println("Vols modificar els seus horaris");
		System.out.println("1. Si");
		System.out.println("2. No");
		System.out.print("Indica l'opcio: ");

		opcio = Integer.parseInt(llegirTeclat());

		if (opcio == 1) {
			modificacioHorari(m, nom);
		}
		continuar(4);
	}

	private static void modificacioHorari(Metge m, String nom)
			throws NumberFormatException, IOException {
		// Metode per poder modificar l'horari
		opcio = 0;
		int dia;

		System.out.println("Quin dia voleu modificar:");
		System.out.println("1. Dilluns");
		System.out.println("2. Dimarts");
		System.out.println("3. Dimecres");
		System.out.println("4. Dijous");
		System.out.println("5. Divendres");
		System.out.print("Indique el dia:");

		dia = Integer.parseInt(llegirTeclat());
		int hora = 0;
		System.out.print("Quan a de començar l'horari de consultes:");
		hora = llegirHora();

		if (hora != 0 && hora >= 8 && hora <= 14) {
			m.setHoraIniciConsultes(dia - 1, hora);
			System.out.println("El nou horai del metge: " + nom + " es\n");
			mostrarHorari(nom);
		} else {
			System.out
					.println("Has introduit una hora incorrecta l'hora d'inici de consultes es entre les 8h i les 14 h");
		}

		System.out
				.println("Li vols modificar algun altre horari a aquest metge:");
		System.out.println("1. Si");
		System.out.println("2. No");
		System.out.print("Indica l'opcio que desitges: ");

		opcio = Integer.parseInt(llegirTeclat());
		if (opcio == 1) {
			modificacioHorari(m, nom);
		}
	}

	private static void mostrarHorari(String nom) {
		// Metode que mostra l'horari del metge
		int[] i = hospital.horariMetge(nom);
		System.out.println("L'horari del metge " + nom + " es:");
		System.out.println("Dilluns: " + i[0]);
		System.out.println("Dimarts: " + i[1]);
		System.out.println("Dimecres: " + i[2]);
		System.out.println("Dijous: " + i[3]);
		System.out.println("Divendres: " + i[4]);
	}

	private static void borrarMetge() throws IOException {
		// Metode per esborrar un Metge
		opcio = 0;
		if (hospital.hihaMetge()) {
			System.out
					.println("Introdueix les dades personals del metge que es vol esborrar");
			String nom = introduirDades();
			System.out.println("Que vols fer amb el merge:");
			System.out.println("1. Donar de Baixa a un Metge");
			System.out.println("2. Esborrar un Metge");
			System.out.print("Indica l'opcio escollida:");
			opcio = Integer.parseInt(llegirTeclat());
			if (opcio == 1) {
				hospital.donarBaixa(nom);
				System.out.println("El metge a sigut donat de baixa");
			} else {
				hospital.BorrarMetge(nom);
				System.out.println("El metge ha estat esborrat");
			}
			continuar(1);
		} else {
			System.out.println("No hi ha cap metge al centre");
		}

	}

	private static String introduirDades() throws IOException {
		// Metode per introduir dades personals
		String nom = null, cog1 = null, cog2 = null;
		System.out.print("Nom: ");
		nom = llegirTeclat();
		if (nom.isEmpty()) {
			nom = "";
		} else {
			System.out.print("Primer Cognom: ");
			cog1 = llegirTeclat();
			if (cog1.isEmpty()) {
				nom = "";
			} else {
				System.out.print("Segon Cognom: ");
				cog2 = llegirTeclat();
				if (cog2.isEmpty()) {
					nom = "";
				} else {
					nom = nom + " " + cog1 + " " + cog2;
				}
			}

		}
		return nom;
	}

	private static void mostrarMetge() throws IOException {
		// Metode per mostrar tots els metges;
		if (hospital.hihaMetge()) {
			hospital.getMetges();
		} else {
			System.out.println("No hi ha cap metge al centre");
		}
		continuar(2);
	}

	private static void gestioConsultes() throws IOException {
		// Metode que gestiona el menu dels Pacients
		opcio = 0;
		boolean zero = false;
		System.out.println();
		System.out.println("Que vols fer amb els Pacients");
		if (hospital.hihaPacient()) {
			System.out.println("1. Donar d'alta un pacient nou");
			System.out.println("2. Demanar hora de consulta");
			System.out.println("3. Cancel·lar una consulta");
			System.out.println("4. Passar una consulta");
			System.out.println("5. Mostrar Consultes pendents");
			System.out.println("6. Mostrar Historic Pacient");
			System.out.println("7. Tornar al menu principal");
		} else {
			System.out.println("1. Donar d'alta un pacient nou");
			zero = true;
		}
		System.out.println();
		System.out.print("Quina opcio tries: ");

		try {
			opcio = Integer.parseInt(llegirTeclat());
			if (zero && opcio != 1) {
				System.out
						.println("Nomes pots escollir l'opcio 1, perque no hi ha cap pacient al centre");
			}
		} catch (Exception e) {
			System.out.println("Has d'escriure un numero entre 1 i 7");
		}
		if (!zero) {
			switch (opcio) {
			case 1:
				altaPacient();
				break;
			case 2:
				controlConsulta(0);
				break;
			case 3:
				controlConsulta(1);
				break;
			case 4:
				controlConsulta(2);
				break;
			case 5:
				consultesPendents();
				break;
			case 6:
				historicPacient();
				break;
			case 7:
				opcioPrincipal();
				break;
			default:
				System.out.println("Opcio Erronia");
				System.out.println();
				gestioConsultes();
			}
		} else {
			altaPacient();
		}

	}

	private static void altaPacient() throws IOException {
		// Metode per crear un nou pacient
		String n_pacient = introduirDades();
		if (n_pacient.equals("")) {
			System.out.println("No has introduït be les dades del pacient");
		} else {
				Pacient p= hospital.CercarPacient(n_pacient);
			if (p == null) {
				hospital.altaPacient(n_pacient);
				System.out.println("Pacient donat d'alta");
			} else {
				System.out.println("Aquest Pacient ja esta donat d'alta");
			}
		}
		continuar(10);

	}

	private static void consultesPendents() throws IOException {
		// Metode per poder veure les consultes que te pendents un pacient
		int diac = 0;
		System.out.println("Dades del Pacient");
		String n_pacient = introduirDades();
		Pacient p = hospital.CercarPacient(n_pacient);
		if (p == null) {
			System.out.println("El pacient no existeix");
		} else {
			System.out
					.println("Introdueix la data i l'hora de consulta de consulta");
			System.out.print("Introdueix quin dia vols la visita:");
			int dia = llegirHora();
			if (dia != 0) {
				diac = dia % 7;
				if (diac == 6 || diac == 0) {
					System.out
							.println("No es realitzen consultes en dissabte o en diumenge");
				} else {
					System.out.print("Introdueix quin mes vols la visita:");
					int mes = llegirHora();
					if (mes != 0) {
						if (mes == 2 && dia > 28) {
							System.out
									.println("Febrer no pot tindre mes de 28 dies");
							System.out
									.println("L'hora de consulta no ha sigut donada d'alta");
						} else {
							if (dia == 31) {
								if (mes == 4 || mes == 6 || mes == 9
										|| mes == 11) {
									System.out
											.println("Els mesos de Abril, Juny, Septembre i Novembre no poden tindre 31 dies");
									System.out
											.println("L'hora de consulta no ha sigut donada d'alta");
								}
							} else {
								System.out
										.print("Introdueix a quina hora vols la visita:");
								int hora = llegirHora();
								if (hora == 0 || hora < 8 || hora > 14) {
									System.out
											.println("Introdueix una hora entre les 8h i les 19h");
									System.out
											.println("L'hora de consulta no ha sigut donada d'alta");
								} else {
									mes = mes - 1;
									calendari.set(calendari.get(Calendar.YEAR),
											mes, diac, hora, dia);
									hospital.consultesPendents(p);
								}
							}
						}
					} else {
						System.out.println("Introdueix un mes entre 1 i 12");
					}
				}
			} else {
				System.out.println("Introdueix un dia entre 1 i 30");
			}
		}
		continuar(9);
	}

	private static void historicPacient() throws IOException {
		// Metode que serveix per mostrar tot l'historial de consultes d'un
		// pacient
		System.out.println("Introdueixi les dades del pacient:");
		String n_pacient = introduirDades();
		Pacient p = hospital.CercarPacient(n_pacient);
		if (p == null) {
			System.out.println("No hi ha cap pacient amb aquest nom");
		} else {
			System.out.println("El pacient " + n_pacient + " te guardaes les següents consultes:");
			Iterator<Consulta> it = p.getHistoric().iterator();
			while (it.hasNext()) {
				System.out.println(it.next().EsciureHistoric());
			}
		}
		continuar(8);
	}

	private static void controlConsulta(int i) throws IOException {
		// Metode que dona d'alta les consultes
		System.out.println("Dades del Pacient");
		String n_pacient = introduirDades();
		if (n_pacient.equals("")) {
			System.out.println("No has introduït be les dades del pacient");
		} else {
			Pacient p = hospital.CercarPacient(n_pacient);
			System.out.println("Dades del Metge");
			String n_metge = introduirDades();
			if (n_metge.equals("")) {
				System.out.println("No has introduït be les dades del metge");
			} else {
				m = hospital.CercarMetge(n_metge);
				if (m == null) {
					System.out.println("El metge no s'ha trobat");
				} else {
					while (!administracioConsultes(i, m, p)) {
						System.out.println();
						administracioConsultes(i, m, p);
					}
				}
			}
		}
		continuar(5 + i);
	}

	private static boolean administracioConsultes(int i, Metge m, Pacient p) {
		/*
		 * Metode d'administracio de les consultes Els valors de i son: 0 ->
		 * Demanar Consulta 1 -> Cancel·lar consulta 2 -> Passar Consulta
		 */
		int diac;
		boolean correcte = true; // comprova que no hi ha hagut cap error
		System.out
				.println("Introdueix la data i l'hora de consulta de consulta");
		System.out.print("Introdueix quin dia vols la visita:");
		int dia = llegirHora();
		if (dia != 0) {
			diac = dia % 7;
			if (diac == 6 || diac == 0) {
				System.out
						.println("No es realitzen consultes en dissabte o en diumenge");
				correcte = false;
			} else {
				if (correcte) {
					System.out.print("Introdueix quin mes vols la visita:");
					int mes = llegirHora();
					if (mes != 0) {
						if (mes == 2 && dia > 28) {
							System.out
									.println("Febrer no pot tindre mes de 28 dies");
							System.out
									.println("L'hora de consulta no ha sigut donada d'alta");
							correcte = false;
						} else {
							if (correcte) {
								if (dia == 31) {
									if (mes == 4 || mes == 6 || mes == 9
											|| mes == 11) {
										System.out
												.println("Els mesos de Abril, Juny, Septembre i Novembre no poden tindre 31 dies");
										System.out
												.println("L'hora de consulta no ha sigut donada d'alta");
										correcte = false;
									}
								} else {
									if (correcte) {
										System.out
												.print("Introdueix a quina hora vols la visita:");
										int hora = llegirHora();
										if (hora == 0 || hora < 8 || hora > 14) {
											System.out
													.println("Introdueix una hora entre les 8h i les 19h");
											System.out
													.println("L'hora de consulta no ha sigut donada d'alta");
											correcte = false;
										} else {
											if (correcte) {
												mes = mes - 1;
												calendari.set(calendari
														.get(Calendar.YEAR),
														mes, diac, hora, dia);
												if (i == 0) {
													if (hospital
															.AnotarConsulta(m,
																	p,
																	calendari)) {
														System.out
																.println("L'hora de consulta s'ha afegit correctament");
													} else {
														System.out
																.println("L'hora de consulta ya esta afagada per un altre pacient");
													}
												} else {
													if (i == 1) {
														if (hospital
																.BorrarConsulta(
																		m, p,
																		calendari)) {
															System.out
																	.println("L'hora de consulta s'ha esborrat correctament");
														} else {
															System.out
																	.println("L'hora de consulta no s'ha pogut esborrar perque no existeix o ja esta realitzada");
														}
													} else {
														if (i == 2) {
															if (hospital
																	.realitzaConsulta(
																			m,
																			p,
																			calendari)) {
																System.out
																		.println("L'hora de consulta s'ha passat correctament");
															} else {
																System.out
																		.println("L'hora de consulta no s'ha pogut passar perque no existeix");
															}
														}
													}
												}
											}

										}
									}
								}
							}
						}
					} else {
						System.out.println("Introdueix un mes entre 1 i 12");
					}
				}
			}
		} else {
			System.out.println("Introdueix un dia entre 1 i 30");
			correcte = false;
		}
		return correcte;
	}

	private static String llegirTeclat() throws IOException {
		// Metode per llegir de teclat
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclat = new BufferedReader(entrada);
		return teclat.readLine();
	}

	private static void altaMetge() throws IOException {
		opcio = 0;
		System.out.println("Introdueix les dades personals del nou metge; ");
		String nom = introduirDades();
		if (nom.equals("")) {
			System.out
					.println("Les Dades del metge no han estat ben introduides.");
		} else {
			if (hospital.CercarMetge(nom) != null) {
				System.out
						.println("El metge ya existeix. Introdueixi un nou metge");
				altaMetge();
			} else {
				System.out.println("El nom del metge es: " + nom);
				System.out.println();
				System.out.println("Quina es la seva especialita?");
				System.out.println("1. Cirurgia");
				System.out.println("2. Anestesista");
				System.out.println("3. Psicoleg");
				System.out.println("4. Odontoleg");
				System.out.println("5. Dermatoleg");
				System.out.println("6. Radioleg");
				System.out.println("7. Ginecoleg");
				System.out.println("8. Metge de Familia");
				System.out.print("Indiqui l'especialitat:");

				try {
					opcio = Integer.parseInt(llegirTeclat());
				} catch (Exception e) {
					System.out.println("Has d'escriure un numero entre 1 i 8");
				}

				switch (opcio) {
				case 1:
					m = hospital.AltaMetge(nom, Especialitat.CIRURGIA);
					break;
				case 2:
					m = hospital.AltaMetge(nom, Especialitat.ANESTESISTA);
					break;
				case 3:
					m = hospital.AltaMetge(nom, Especialitat.PSICOLEG);
					break;
				case 4:
					m = hospital.AltaMetge(nom, Especialitat.ODONTOLEG);
					break;
				case 5:
					m = hospital.AltaMetge(nom, Especialitat.DERMATOLEG);
					break;
				case 6:
					m = hospital.AltaMetge(nom, Especialitat.RADIOLEG);
					break;
				case 7:
					m = hospital.AltaMetge(nom, Especialitat.GINECOLEG);
					break;
				case 8:
					m = hospital.AltaMetge(nom, Especialitat.METGE_FAMILIA);
					break;
				default:
					System.out
							.println("Opcio Erronia torna has de tornar a fer el proces d'alta del metge");
					System.out.println();
					altaMetge();
				}

				if (opcio == 2 || opcio == 6) {
					for (int i = 0; i <= 4; i++) {
						m.setHoraIniciConsultes(i, 9);
					}
				} else {
					System.out
							.println("Introduiex l'hora de consulta de cada dia ?");
					int hora;
					int i = 0;
					// Estableix el dia on es vol que comenci a contar el
					// calendari
					calendari.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					while (i < 5) {
						System.out.println(calendari.getDisplayName(
								Calendar.DAY_OF_WEEK, Calendar.LONG,
								new Locale("ca")).toUpperCase());
						hora = llegirHora();
						if (hora == 0 || hora < 8 || hora > 14) {
							System.out
									.println("Introdueix una hora entre les 8h i les 14h");
						} else {
							m.setHoraIniciConsultes(i, hora);
							// Passa afegeix el dia següent de la setmana
							calendari.add(Calendar.DAY_OF_WEEK, 1);
							i++;
						}
					}
				}
				System.out.println("Alta del Metge realitzada correctament");
				System.out.println();
			}
		}
		continuar(3);
	}

	private static int llegirHora() {
		// Metode que comprova que fiques una hora correcta
		// i no qualsevol altra cosa
		int hora = 0;
		try {
			hora = Integer.parseInt(llegirTeclat());
		} catch (Exception e) {
			System.out.println("Has d'escriure una hora correcte");
		}
		return hora;
	}

	private static void continuar(int i) throws IOException {
		switch (i) {
		case 1:
			System.out.println("Vols esborrar algun altre metge?");
			break;
		case 2:
			System.out.println("Vols mostrar un altre metge?");
			break;
		case 3:
			System.out.println("Vols introduir un altre metge?");
			break;
		case 4:
			System.out.println("Vols modificar un altre metge?");
			break;
		case 5:
			System.out.println("Vols crear alguna altre consulta?");
			break;
		case 6:
			System.out.println("Vols esborrar alguna altra consulta?");
			break;
		case 7:
			System.out.println("Vols passar alguna altra consulta?");
			break;
		case 8:
			System.out
					.println("Vols buscar l'historial d'alguna altre pacient?");
			break;
		case 9:
			System.out
					.println("Vols comprovar les consultes pendents d'algun altre pacient?");
			break;
		case 10:
			System.out.println("Vols afegir un nou pacient?");
			break;
		}
		opcio = 0;
		System.out.println("1. Si");
		System.out.println("2. No");
		System.out.println("Que tries:");

		try {
			opcio = Integer.parseInt(llegirTeclat());
		} catch (Exception e) {
			System.out.println("Has d'escriure un numero entre 1 i 2");
			continuar(i);
		}

		if (opcio > 2) {
			System.out.println("Has de triar una opcio entre 1 i 2");
			continuar(i);
		} else {
			if (opcio == 1) {
				switch (i) {
				case 1:
					borrarMetge();
					break;
				case 2:
					mostrarMetge();
					break;
				case 3:
					altaMetge();
					break;
				case 4:
					modificarMetge();
					break;
				case 5:
					controlConsulta(0);
					break;
				case 6:
					controlConsulta(1);
					break;
				case 7:
					controlConsulta(2);
					break;
				case 8:
					historicPacient();
					break;
				case 9:
					consultesPendents();
					break;
				case 10:
					altaPacient();
					break;
				}
			} else {
				opcioPrincipal();
			}
		}
	}
}
