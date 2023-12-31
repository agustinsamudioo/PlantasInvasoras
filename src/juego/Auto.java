package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import entorno2.Entorno;
import entorno2.Herramientas;

public class Auto {
	int ancho;
	int alto;
	int x;
	int y;
	double angulo;
	Image img1;
	Image img2;
	boolean motor;
	Color myColor = Color.blue;
	Rectangle caja = new Rectangle(x, y, ancho, alto);
	int velocidad = 2;

	public Auto(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		motor = false;
		img1 = Herramientas.cargarImagen("recursos/auto.png");
		img2 = Herramientas.cargarImagen("recursos/auto.png");
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void dibujarse(Entorno entorno) {

		entorno.dibujarImagen(img2, this.x, this.y, this.angulo - Math.PI / 2, 0.36);
	}

	public void girar(double modificador) {
		this.angulo = this.angulo + modificador;
		if (this.angulo < 0) {
			this.angulo += 2 * Math.PI;
		}
		if (this.angulo > 2 * Math.PI) {
			this.angulo -= 2 * Math.PI;
		}

	}

	public void moverAdelante() {

		this.x += Math.cos(this.angulo) * velocidad;
		this.y += Math.sin(this.angulo) * velocidad;

		if (this.y > 570) {
			this.angulo = Herramientas.radianes(270);
		}

		if (this.y < 25) {
			this.angulo = Herramientas.radianes(90);
		}
		if (this.x < 25) {
			this.angulo = Herramientas.radianes(0);
		}
		if (this.x > 780) {
			this.angulo = Herramientas.radianes(180);
		}

	}

	public void dibujarCaja(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, alto, ancho, 0, myColor);
	}

	public boolean colisionCaja(int x1, int y1, int ancho1, int alto1, double x2, double y2, int ancho2, int alto2) {
		if (x1 > x2 + ancho2 || y1 > y2 + alto2 || x2 > x1 + ancho1 || y2 > y1 + alto1) {
			return false;
		} else {
			return true;
		}

	}
	public boolean frenado(int x, int y, int ancho, int alto, double angulo) {
		if(this.y+this.alto/2>164 && this.y+this.alto/2<437 && x+ancho/2>=464 && x+ancho/2<=474  && angulo==Herramientas.radianes(0) ) 
			return true;
		if(this.y+this.alto/2>164 && this.y+this.alto/2<437 && x-ancho/2<=560 && x-ancho/2>=550 && angulo==Herramientas.radianes(180) )
			return true;
		if(this.y-this.alto/2<437 && this.y-this.alto/2>164 && x+ancho/2>=464 && x+ancho/2<=474  && angulo==Herramientas.radianes(0) ) 
			return true;
		if(this.y-this.alto/2<437 && this.y-this.alto/2>164 && x-ancho/2==560 && x-ancho/2>=550 && angulo==Herramientas.radianes(180) ) 
			return true;
		
		return false;
	}
	public void empujar() {
		this.x += Math.cos(this.angulo) * -velocidad;
		this.y += Math.sin(this.angulo) * -velocidad;
	}
}
