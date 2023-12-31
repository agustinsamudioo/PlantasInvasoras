package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Scanner;
import entorno2.Entorno;
import entorno2.Herramientas;
import entorno2.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables y métodos propios de cada grupo
	// String puntaje[][] = new String[10][2];
	Image escudito;
	Pocion pocion;
	Portal portal;
	Estrella estrella;
	Escudo escudo;
	Image winner;
	Image gameOver;
	Image Fondo;
	Image corazon;
	Boss boss;
	Layka layka;
	Domingo domingo;
	Planta planta1;
	Planta planta2;
	Planta planta3;
	Planta planta4;
	Planta planta5;
	Auto auto;
	Auto auto2;
	Colision manzana1;
	Colision manzana2;
	Colision manzana3;
	Colision manzana4;
	Colision manzana5;
	Colision manzana6;
	Proyectil proyectil;
	Proyectil proyectil2;
	Image laykaOriginal;
	Image BolaFuegoPlanta;
	Image Score;
	double anguloFondo;
	boolean EscudoDesactivado;
	boolean choco;

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Plantas Invasoras - Grupo 5 - v1", 800, 591);
		// Inicializar lo que haga falta para el juego
		choco = false;
		domingo = new Domingo(170, 570, 20, 20);
		escudo = new Escudo(400, 580, 20, 20);
		boss = new Boss(400, 560, 80, 80);
		layka = new Layka(720, 180, 40, 40);
		planta1 = new Planta(480, 575, 20, 20);
		planta1.setAngulo(Herramientas.radianes(0));
		planta2 = new Planta(22, 50, 20, 20);
		planta2.setAngulo(Herramientas.radianes(270));
		planta3 = new Planta(266, 318, 20, 20);
		planta3.setAngulo(Herramientas.radianes(270));
		planta4 = new Planta(100, 15, 20, 20);
		planta4.setAngulo(Herramientas.radianes(0));
		auto = new Auto(490, 300, 100, 40);
		auto.setAngulo(Herramientas.radianes(90));
		auto2 = new Auto(200, 280, 37, 100);
		auto2.setAngulo(Herramientas.radianes(0));
		manzana1 = new Colision(173, 174, 143, 185);
		manzana2 = new Colision(404, 174, 143, 185);
		manzana3 = new Colision(627, 174, 143, 185);
		manzana4 = new Colision(173, 434, 143, 185);
		manzana5 = new Colision(404, 434, 143, 185);
		manzana6 = new Colision(627, 434, 143, 185);
		pocion = new Pocion(310, 20, 10, 10);
		estrella = new Estrella(20, 540, 10, 10);
		portal = new Portal(780, 570, 10, 10);
		BolaFuegoPlanta = Herramientas.cargarImagen("recursos/BolaFuego.gif");
		Fondo = Herramientas.cargarImagen("recursos/FondoCalleJuego3.png");
		gameOver = Herramientas.cargarImagen("recursos/gameover.png");
		winner = Herramientas.cargarImagen("recursos/winner.png");
		corazon = Herramientas.cargarImagen("recursos/vida.gif");
		escudito = Herramientas.cargarImagen("recursos/DugEscudo.gif");
		laykaOriginal = Herramientas.cargarImagen("recursos/Dug.gif");
		Score = Herramientas.cargarImagen("recursos/scoreFinal.png");
		anguloFondo = 0;
		proyectil = new Proyectil(100, 100, 10, 20);
		proyectil2 = new Proyectil(100, 300, 10, 20);
		Herramientas.cargarSonido("recursos/musica2.wav");
		Herramientas.cargarSonido("recursos/muerte.wav");
		Herramientas.loop("recursos/musica2.wav");
		EscudoDesactivado = true;

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	// VARIABLES
	int items=0;
	int puntos = 0;
	int nivel = 1;
	int muertesPlantas = 0;
	int muertesLayka=0;
	int muertesJefe=0;
	int muertesPorAuto=0;
	int muertesPorPlantas=0;
	int muertesPorJefe=0;
	int cont = 0;
	int vidas = 5;
	boolean gano = false;
	int vidasJefe = 10;

	@SuppressWarnings("resource")

	public void tick() {
		if (layka != null) {

			// DIBUJOS EN LA PANTALLA
			anguloFondo = 0;
			entorno.dibujarImagen(Fondo, 400, 295.5, anguloFondo); // FONDO
			entorno.dibujarImagen(corazon, 780, 20, anguloFondo, 0.7);
			planta1.dibujarse(entorno);
			planta1.moverAdelante();
			planta2.dibujarse(entorno);
			planta2.moverAdelante();
			planta3.dibujarse(entorno);
			planta3.moverAdelante();
			planta4.dibujarse(entorno);
			planta4.moverAdelante();
			auto.moverAdelante();
			auto2.moverAdelante();
			auto2.dibujarse(entorno);
			domingo.dibujarse(entorno);
			layka.dibujarse(entorno);
			auto.dibujarse(entorno);
			pocion.dibujarse(entorno);
			estrella.dibujarse(entorno);
			portal.dibujarse(entorno);
			entorno.cambiarFont("Impact", 20, Color.black);
			entorno.escribirTexto("posicion en x:" + layka.x, 500, 150);
			entorno.escribirTexto("posicion en y:" + layka.y, 500, 200);
			entorno.cambiarFont("Impact", 20, Color.white);
			entorno.escribirTexto("Points:" + puntos, 8, 20);
			entorno.escribirTexto("Level:" + nivel, 740, 580);
			entorno.escribirTexto("Plants:" + muertesPlantas, 8, 580);
			entorno.escribirTexto("" + vidas, 775, 25);
			proyectil2.cambiarImagen(BolaFuegoPlanta);

			// MOVIMIENTOS PERRO 1 (LAYKA)
			if (entorno.sePresiono(entorno.TECLA_DERECHA))
				layka.setAngulo(Herramientas.radianes(0));

			if (entorno.sePresiono(entorno.TECLA_IZQUIERDA))
				layka.setAngulo(Herramientas.radianes(180));

			if (entorno.sePresiono(entorno.TECLA_ARRIBA))
				layka.setAngulo(Herramientas.radianes(270));

			if (entorno.sePresiono(entorno.TECLA_ABAJO))
				layka.setAngulo(Herramientas.radianes(90));
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada(entorno.TECLA_DERECHA)
					|| entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				layka.moverAdelante();
				layka.prenderMotor();

			} else {
				layka.apagarMotor();
			}
			// MOVIMIENTOS PERRO 2 (DOM)
			if (entorno.sePresiono(entorno.TECLA_D))
				domingo.setAngulo(Herramientas.radianes(0));

			if (entorno.sePresiono(entorno.TECLA_A))
				domingo.setAngulo(Herramientas.radianes(180));

			if (entorno.sePresiono(entorno.TECLA_W))
				domingo.setAngulo(Herramientas.radianes(270));

			if (entorno.sePresiono(entorno.TECLA_S))
				domingo.setAngulo(Herramientas.radianes(90));
			if (entorno.estaPresionada(entorno.TECLA_A) || entorno.estaPresionada(entorno.TECLA_D)
					|| entorno.estaPresionada(entorno.TECLA_W) || entorno.estaPresionada(entorno.TECLA_S)) {
				domingo.moverAdelante();
				domingo.prenderMotor();

			} else {
				domingo.apagarMotor();
			}
			// COLISION MANZANA CON LAYKA
			if (manzana1.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana1.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana2.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana3.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana4.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana5.colision(layka.x, layka.y, layka.ancho, layka.alto)
					|| manzana6.colision(layka.x, layka.y, layka.ancho, layka.alto)) {
				{
					layka.empujar();
				}
			}
			// COLISION MANZANA CON PERRO 2
			if (manzana1.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana1.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana2.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana3.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana4.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana5.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)
					|| manzana6.colision(domingo.x, domingo.y, domingo.ancho, domingo.alto)) {
				{
					domingo.empujar();
				}
			}

			// PROYECTILES: AL CONTAR MAS DE 300 TICKS ESTANDO EN PANTALLA, EL PROYECTIL
			// DESAPARECE. ESTE MISMO APARECE EN EL X Y EL Y DE LAYKA, Y SE VA MOVIENDO
			// A LA IZQUIERDA O DERECHA,DEPENDIENDO DEL ANGULO EN EL QUE MIRE EL PROYECTIL
			if (!proyectil.cooldown()) {
				if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					proyectil.setPosicion(layka.x, layka.y);
					proyectil.setAngulo(layka.angulo);
					proyectil.dibujarse(entorno);
					proyectil.contadorTicks = 0;
				}
			} else {
				proyectil.dibujarse(entorno);
				proyectil.moverAdelante();
			}
			// BOLA DE FUEGO PLANTA, falta solucionar esto
			if (!proyectil2.cooldown()) {
				proyectil2.contadorTicks = 0;
				proyectil2.setPosicion(planta1.x, planta1.y);
				proyectil2.setAngulo(layka.angulo);
				proyectil2.dibujarse(entorno);
			} else {
				proyectil2.dibujarse(entorno);
				proyectil2.moverAdelante();
			}

			// COLISIONES PROYECTIL CON LA PLANTA
			if (auto.colisionCaja(planta1.x, planta1.y, planta1.alto, planta1.ancho, proyectil.x, proyectil.y,
					proyectil.ancho, proyectil.alto)) {
				Herramientas.play("recursos/muerte.wav");
				planta1 = null;
				puntos += 5;
				muertesPlantas += 1;
				// ALTERNATIVA, 2 UBICACIONES
				if (cont == 0) {
					planta1 = new Planta(310, 10, 40, 40);
					cont += 1;
				} else {
					planta1 = new Planta(80, 540, 40, 40);
					cont = 0;
				}
			}
			if (auto.colisionCaja(planta2.x, planta2.y, planta2.alto, planta2.ancho, proyectil.x, proyectil.y,
					proyectil.ancho, proyectil.alto)) {
				Herramientas.play("recursos/muerte.wav");
				planta2 = null;
				puntos += 5;
				muertesPlantas += 1;
				// ALTERNATIVA, 2 UBICACIONES
				if (cont == 0) {
					planta2 = new Planta(80, 10, 40, 40);
					cont += 1;
				} else {
					planta2 = new Planta(480, 575, 40, 40);
					cont = 0;
				}
			}
			if (auto.colisionCaja(planta3.x, planta3.y, planta3.alto, planta3.ancho, proyectil.x, proyectil.y,
					proyectil.ancho, proyectil.alto)) {
				Herramientas.play("recursos/muerte.wav");
				planta3 = null;
				puntos += 5;
				muertesPlantas += 1;
				// ALTERNATIVA, 2 UBICACIONES
				if (cont == 0) {
					planta3 = new Planta(166, 58, 40, 40);
					cont += 1;
				} else {
					planta3 = new Planta(530, 580, 40, 40);
					cont = 0;
				}
			}
			if (auto.colisionCaja(planta4.x, planta4.y, planta4.alto, planta4.ancho, proyectil.x, proyectil.y,
					proyectil.ancho, proyectil.alto)) {
				Herramientas.play("recursos/muerte.wav");
				planta4 = null;
				puntos += 5;
				muertesPlantas += 1;
				// ALTERNATIVA, 2 UBICACIONES
				if (cont == 0) {
					planta4 = new Planta(310, 10, 40, 40);
					cont += 1;
				} else {
					planta4 = new Planta(774, 320, 40, 40);
					cont = 0;
				}
			}
			// COLISION PROYECTIL CON JEFE FINAL
			if (auto.colisionCaja(boss.x, boss.y, boss.ancho, boss.alto, proyectil.x, proyectil.y - 20,
					proyectil.ancho + 30, proyectil.alto + 30)) {
				boss = null;
				muertesJefe +=1;
				vidasJefe -= 1;
				puntos += 10;
				if (cont == 0) {
					boss = new Boss(310, 35, 10, 10);
					cont += 1;
				} else {
					boss = new Boss(100, 560, 10, 10);
					cont = 0;
				}
			}
			if (vidasJefe == 0) {
				puntos += 1000;
			}

			// COLISON POSION
			if (auto.colisionCaja(pocion.x, pocion.y, pocion.ancho, pocion.alto, layka.x, layka.y, layka.ancho,
					layka.alto)) {
				items+=1;
				pocion = null;
				pocion = new Pocion(300, 560, 10, 10);
				vidas += 1;
				if (cont == 0) {
					pocion = new Pocion(310, 20, 10, 10);
					cont += 1;
				} else {
					pocion = new Pocion(100, 560, 10, 10);
					cont = 0;
				}
				if (vidas > 5) {
					vidas = 5;
				}
			}
			// COLISION ESTRELLA
			if (auto.colisionCaja(estrella.x, estrella.y, estrella.alto, estrella.ancho, layka.x, layka.y, layka.ancho,
					layka.alto)) {
				items+=1;
				estrella = null;
				estrella = new Estrella(300, 560, 10, 10);
				layka.velocidad += 1;
				if (cont == 0) {
					estrella = new Estrella(500, 20, 10, 10);
					cont += 1;
				} else {
					estrella = new Estrella(300, 560, 10, 10);
					cont = 0;
				}
				if (layka.velocidad > 4) {
					layka.velocidad = 4;
				}
			}
			// COLSION PORTAL
			if (auto.colisionCaja(portal.x, portal.y, portal.alto, portal.ancho, layka.x, layka.y, layka.ancho,
					layka.alto)) {
				layka = null;
				layka = new Layka(20, 20, 40, 40);

			}
			// COLISION ESCUDO
			if (nivel == 2 || nivel == 3 || nivel == 4) {

				if (auto.colisionCaja(escudo.x, escudo.y, escudo.alto, escudo.ancho, layka.x, layka.y, layka.ancho,
						layka.alto)) {
					items+=1;
					layka.cambiarImagen(escudito);
					EscudoDesactivado = false;
					escudo = null;
					escudo = new Escudo(12, 174, 20, 20);
					if (cont == 0) {
						escudo = new Escudo(400, 580, 20, 20);
						cont += 1;
					} else {
						escudo = new Escudo(12, 174, 20, 20);
						cont = 0;
					}

				}
			}
			// SIN VIDAS
			if (vidas == 0) {
				entorno.dibujarImagen(Fondo, 400, 295.5, anguloFondo);
				entorno.dibujarImagen(gameOver, 400, 295.5, anguloFondo, 0.8);
				layka = null;
			}
			// VIDAS LAYKA
			if (vidas != 0) {
				// COLISION CON PLANTAS
				if (planta1.colisionConLayka(layka.x, layka.y - 20, layka.alto, layka.ancho)
						|| planta2.colisionConLayka(layka.x, layka.y, layka.alto, layka.ancho)
						|| planta3.colisionConLayka(layka.x, layka.y, layka.alto, layka.ancho)
						|| planta4.colisionConLayka(layka.x, layka.y, layka.alto, layka.ancho)) {
					Herramientas.play("recursos/muerte.wav");
					muertesLayka+=1;
					muertesPorPlantas+=1;
					layka = null;
					vidas -= 1;
					layka = new Layka(720, 180, 40, 40);
				}
				// COLISION CON AUTO

				if (auto.colisionCaja(auto.x - 3, auto.y - 32, auto.alto, auto.ancho, layka.x, layka.y, layka.ancho,
						layka.alto)
						|| auto.colisionCaja(auto2.x - 32, auto2.y, auto2.alto, auto2.ancho, layka.x, layka.y,
								layka.ancho, layka.alto)) {
					muertesLayka+=1;
					muertesPorAuto+=1;
					choco = true;
					if (EscudoDesactivado) {
						layka = null;
						vidas -= 1;
						layka = new Layka(720, 180, 40, 40);
						layka.cambiarImagen(laykaOriginal);
					}
					if (EscudoDesactivado == false && choco == true) {
						choco = false;
						EscudoDesactivado = true;
						layka = null;
						layka = new Layka(720, 180, 40, 40);
						layka.cambiarImagen(laykaOriginal);
					}
				}
				// LAYKA COLISION CON JEFE
				if (boss.colisionConLayka(layka.x, layka.y, layka.alto, layka.ancho)) {
					Herramientas.play("recursos/muerte.wav");
					muertesLayka+=1;
					muertesPorJefe+=1;
					layka = null;
					vidas -= 1;
					layka = new Layka(720, 180, 40, 40);
				}
			}

			// AUTO FRENA CUANDO ESTA PASANDO EL OTRO
			if (auto.frenado(auto2.x, auto2.y, auto2.ancho, auto2.alto, auto2.angulo)) {
				auto2.empujar();
			}
			// AUTO FRENA CON LAS PLANTAS, No funciona
			if (auto.frenado(planta1.x, planta1.y, planta1.ancho, planta1.alto, planta1.angulo)
					|| auto.frenado(planta2.x, planta2.y, planta2.ancho, planta2.alto, planta2.angulo)
					|| auto.frenado(planta3.x, planta3.y, planta3.ancho, planta3.alto, planta3.angulo)
					|| auto.frenado(planta4.x, planta4.y, planta4.ancho, planta4.alto, planta4.angulo)) {
				auto.empujar();
			}
			if (auto2.frenado(planta1.x, planta1.y, planta1.ancho, planta1.alto, planta1.angulo)) {
				auto2.empujar();
			}

			
			// NIVEL 2
			if (puntos >= 30 && puntos <= 100) {
				// aumenta velocidad de los autos y plantas
				escudo.dibujarse(entorno);
				nivel = 2;
				auto.velocidad = 4;
				auto2.velocidad = 4;
				planta1.velocidad = 3;
				planta2.velocidad = 3;
				planta3.velocidad = 3;
				planta4.velocidad = 3;

			}
			// NIVEL 3
			if (puntos >= 105 && puntos <= 150) {
				// aumenta velocidad de los autos y plantas
				nivel = 3;
				auto.velocidad = 5;
				auto2.velocidad = 5;
				planta1.velocidad = 4;
				planta2.velocidad = 4;
				planta3.velocidad = 4;
				planta4.velocidad = 4;
			}
			// NIVEL 4
			if (puntos >= 155 && puntos <= 300) {
				// aumentar velocidad de los autos y plantas
				escudo.dibujarCaja(entorno);
				escudo.dibujarse(entorno);
				nivel = 4;
				auto.velocidad = 6;
				auto2.velocidad = 6;
				planta1.velocidad = 5;
				planta2.velocidad = 5;
				planta3.velocidad = 5;
				planta4.velocidad = 5;
				boss.dibujarse(entorno);
				boss.moverAdelante();
			}
			// GANAR
			if (puntos >= 305) {
				layka = null;
				proyectil = null;
				planta1 = null;
				planta2 = null;
				planta3 = null;
				planta4 = null;
				auto = null;
				auto2 = null;
				boss = null;
				entorno.dibujarImagen(Fondo, 400, 295.5, anguloFondo);
				entorno.dibujarImagen(winner, 400, 295.5, anguloFondo, 0.8);
				entorno.cambiarFont("Impact", 20, Color.white);
				entorno.escribirTexto("Points:" + puntos, 8, 20);
				entorno.escribirTexto("Level:" + nivel, 740, 580);
				entorno.escribirTexto("Plants:" + muertesPlantas, 8, 580);
				entorno.dibujarImagen(corazon, 780, 20, anguloFondo, 0.7);
				entorno.escribirTexto("" + vidas, 775, 25);
				gano = true;
			}

		} // TERMINA EL IF NULL LAYKA

		else {
			if (vidas <= 0 || gano == true) { // Si el jugador pierde todas sus vidas o gana

				Scanner teclado = new Scanner(System.in);
				String respuesta;
				System.out.println("¿Quiere seguir jugando?, S:si, para no: ingrese cualquier tecla,"
						+ " si desea ver el puntaje ingrese G.");
				respuesta = teclado.next();
				if (respuesta.equalsIgnoreCase("s")) { // VUELVE A COMENZAR EL JUEGO
					boss = new Boss(400, 560, 40, 40);
					layka = new Layka(720, 180, 40, 40);
					planta1 = new Planta(480, 575, 40, 40);
					planta1.setAngulo(Herramientas.radianes(0));
					planta2 = new Planta(22, 50, 40, 40);
					planta2.setAngulo(Herramientas.radianes(270));
					planta3 = new Planta(266, 318, 40, 40);
					planta3.setAngulo(Herramientas.radianes(270));
					planta4 = new Planta(100, 16, 40, 40);
					planta4.setAngulo(Herramientas.radianes(0));
					auto = new Auto(490, 300, 100, 40);
					auto.setAngulo(Herramientas.radianes(90));
					auto2 = new Auto(200, 280, 40, 100);
					auto2.setAngulo(Herramientas.radianes(0));
					proyectil = new Proyectil(-1000, -1000, 5, 5);
					puntos = 0;
					nivel = 1;
					muertesPlantas = 0;
					vidas = 5;
				} else {
					if (respuesta.equalsIgnoreCase("g")) { // Guardar puntos y usuario
						String PuntosS = String.valueOf(puntos);
						String usuario;
						System.out.println("Ingrese el nombre de usuario: ");
						usuario = teclado.next();
						entorno.dibujarImagen(Score, 400, 80, anguloFondo, 0.8);
						entorno.cambiarFont("Impact", 20, Color.white);
						entorno.escribirTexto("Jugador: "+usuario, 200, 180);
						entorno.escribirTexto("Puntos: "+PuntosS, 200, 200);
						entorno.escribirTexto("Plantas Eliminadas: "+muertesPlantas, 200, 220);
						entorno.escribirTexto("Muertes de Layka: "+muertesLayka, 200, 240);
						entorno.escribirTexto("Muertes del Boss: "+muertesJefe, 200, 260);
						entorno.escribirTexto("Muertes de Layka por Plantas: "+muertesPorPlantas, 200, 280);
						entorno.escribirTexto("Muertes de Layka por Autos: "+muertesPorAuto, 200, 300);
						entorno.escribirTexto("Muertes de Layka por Boss: "+muertesPorJefe, 200, 320);
						entorno.escribirTexto("Items utilizados: "+items, 200, 340);
					}
					else {
						System.exit(0);
					}

				}
			} else {
				layka = new Layka(720, 180, 40, 40);

			}
		}
	} // TERMINA EL TICK

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
