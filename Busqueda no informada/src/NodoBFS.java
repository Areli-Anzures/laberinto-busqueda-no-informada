
public class NodoBFS {
	public int[] pos = new int[2];
	public NodoBFS padre; //padre
	public NodoBFS[] hijos; //arreglo de hijos

	
	//cosntructor simple, solo recibe los datos del nodo 
	public NodoBFS(int[] pos) {
		this.pos=pos;
		this.padre=null;
		this.hijos=new NodoBFS[4];
	}

	//constructor que recibe datos y padre del nodo
	public NodoBFS(int[] pos, NodoBFS padre) {
		this.pos=pos;
		this.padre=padre;
		this.hijos=new NodoBFS[4];
	}
	
	//establece el hijo izq
	public void setHijoArriba(NodoBFS hijoArriba) {
		hijos[0]=hijoArriba;
	}

	//establece el hijo central
	public void setHijoAbajo(NodoBFS hijoAbajo) {
		hijos[1]=hijoAbajo;
	}

	//establece el hijo derecho
	public void setHijoDer(NodoBFS hijoDer) {
		hijos[2]=hijoDer;
	}
	
	public void setHijoIzq(NodoBFS hijoIzq) {
		hijos[3]=hijoIzq;
	}
	
	//funcion que obtiene si el nodo es igual al de la solucion
	public boolean esIgual(int[] solucion) {
		for(int i=0; i<2; i++) {
			if(pos[i] != solucion[i])
				return false;
		}
		return true;
	}

	public String toString() {
		return"[ " + String.valueOf(pos[0]) + " " + String.valueOf(pos[1]) + " ] ";
	}
}
