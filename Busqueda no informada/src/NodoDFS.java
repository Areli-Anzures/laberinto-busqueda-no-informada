
public class NodoDFS {
	public int[] pos = new int[2];
	public NodoDFS padre; //padre
	public NodoDFS[] hijos; //arreglo de hijos
	
	
	//cosntructor simple, solo recibe los datos del nodo 
	public NodoDFS(int[] pos) {
		this.pos=pos;
		this.padre=null;
		this.hijos=new NodoDFS[4];
	}

	//constructor que recibe datos y padre del nodo
	public NodoDFS(int[] pos, NodoDFS padre) {
		this.pos=pos;
		this.padre=padre;
		this.hijos=new NodoDFS[4];
	}
	
	//establece el hijo izq
		public void setHijoArriba(NodoDFS hijoArriba) {
			hijos[0]=hijoArriba;
		}

		//establece el hijo central
		public void setHijoAbajo(NodoDFS hijoAbajo) {
			hijos[1]=hijoAbajo;
		}

		//establece el hijo derecho
		public void setHijoDer(NodoDFS hijoDer) {
			hijos[2]=hijoDer;
		}
		
		public void setHijoIzq(NodoDFS hijoIzq) {
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
		return "[ " + String.valueOf(pos[0]) + " " + String.valueOf(pos[1]) + " ] ,  ";
	}
}
