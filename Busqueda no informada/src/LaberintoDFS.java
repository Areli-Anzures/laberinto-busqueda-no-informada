import java.util.LinkedList;

public class LaberintoDFS {
	//public LinkedList<NodoDFS> visitados; //lista de nodos visitados
	public NodoDFS nodoActual, nodoHijo; //nodos hijo y actual
	public Coordenada[] noValidas = new Coordenada[9];
	
	//cosntructor
	public LaberintoDFS(NodoDFS nodoActual, Coordenada[] noValidas) {
		//this.nodoActual=nodoActual;
		//se crean la cola y lista a utilizar
		//visitados = new LinkedList<>();
		this.noValidas=noValidas;
	}
	
	//funcion que crea el arbol de busqueda
	public NodoDFS creaArbol(NodoDFS nodo, int[] solucion, LinkedList<NodoDFS> visitados) {
		//this.nodoActual=nodo;
		NodoDFS actual = nodo;
		visitados.add(actual);
			//comienza a extraer nodos
		if(actual.esIgual(solucion)) {
			//encontro la solucion 
			return actual;
		} else {
//			CreaHijos(); //crea los hijos del nodo
			CreaHijos(actual); //crea los hijos del nodo
			for(int i=0; i<actual.hijos.length; i++) {
				nodoHijo=actual.hijos[i]; //agrega los hijos a nodos frontera
				if(nodoHijo != null) {
					if(!noVisitados(nodoHijo, visitados)) { //si no esta en visitados y en la cola
						NodoDFS S =creaArbol(nodoHijo, solucion, visitados);
						if(S != null)
							return S;
					}
				}
			}
			
		}
		return null;
	}
	
	//crea o hijos en base al nodoActual
	public void CreaHijos(NodoDFS actual) {
		int[] pos;
		
//		System.out.println("Los valores de pos son X: " + actual.pos[0] + " Y: " + actual.pos[1]);
		//para hijo arriba
		if(actual.pos[1] == 0) {
			actual.setHijoArriba(null);
		} else {
			pos= new int[2];
			pos[0]=actual.pos[0];
			pos[1]=actual.pos[1]-1;
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, actual);
				actual.setHijoArriba(nodoHijo);
			} else
				actual.setHijoArriba(null);
		}
		
		//para hijo izq
		if(actual.pos[0] == 0) {
			actual.setHijoIzq(null);
		} else {
			pos= new int[2];
			pos[0]=actual.pos[0]-1;
			pos[1]=actual.pos[1];
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, actual);
				actual.setHijoIzq(nodoHijo);
			} else
				actual.setHijoIzq(null);
		}
		
		//para hijo der
		if(actual.pos[0] == 5) {
			actual.setHijoDer(null);
		} else {
			pos= new int[2];
			pos[0]=actual.pos[0]+1; 
			pos[1]=actual.pos[1];
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, actual);
				actual.setHijoDer(nodoHijo);
			} else
				actual.setHijoDer(null);
		}
		
		//para hijo abajo
		if(actual.pos[1] == 5) {
			actual.setHijoAbajo(null);
		} else {
			pos= new int[2];
			pos[0]=actual.pos[0];
			pos[1]=actual.pos[1]+1;
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, actual);
				actual.setHijoAbajo(nodoHijo);
			} else
				actual.setHijoAbajo(null);
		}
	}
	
	//crea o hijos en base al nodoActual
	public void CreaHijos() {
		int[] pos;
		
		System.out.println("Los valores de pos son X: " + nodoActual.pos[0] + " Y: " + nodoActual.pos[1]);
		//para hijo arriba
		if((nodoActual.pos[0] >= 0 || nodoActual.pos[0] <= 5) && nodoActual.pos[1] == 0) {
			nodoActual.setHijoArriba(null);
		} else {
			pos= new int[2];
			pos[0]=nodoActual.pos[0];
			pos[1]=nodoActual.pos[1]-1;
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, nodoActual);
				nodoActual.setHijoArriba(nodoHijo);
			} else
				nodoActual.setHijoArriba(null);
		}
		
		//para hijo izq
		if((nodoActual.pos[1] >= 0 || nodoActual.pos[1] <= 5) && nodoActual.pos[0] == 0) {
			nodoActual.setHijoIzq(null);
		} else {
			pos= new int[2];
			pos[0]=nodoActual.pos[0]-1;
			pos[1]=nodoActual.pos[1];
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, nodoActual);
				nodoActual.setHijoIzq(nodoHijo);
			} else
				nodoActual.setHijoIzq(null);
		}
		
		//para hijo der
		if((nodoActual.pos[1] >= 0 || nodoActual.pos[1] <= 5) && nodoActual.pos[0] == 5) {
			nodoActual.setHijoDer(null);
		} else {
			pos= new int[2];
			pos[0]=nodoActual.pos[0]+1; 
			pos[1]=nodoActual.pos[1];
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, nodoActual);
				nodoActual.setHijoDer(nodoHijo);
			} else
				nodoActual.setHijoDer(null);
		}
		
		//para hijo abajo
		if((nodoActual.pos[0] >= 0 || nodoActual.pos[0] <= 5) && nodoActual.pos[1] == 5) {
			nodoActual.setHijoAbajo(null);
		} else {
			pos= new int[2];
			pos[0]=nodoActual.pos[0];
			pos[1]=nodoActual.pos[1]+1;
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoDFS(pos, nodoActual);
				nodoActual.setHijoAbajo(nodoHijo);
			} else
				nodoActual.setHijoAbajo(null);
		}
	}
	
	
	public boolean noVisitados(NodoDFS hijo, LinkedList<NodoDFS> visitados) {
		int i, j;
		boolean presente = false;
		// Mientras no se encuentre un nodo igual, revisar cada uno
		for(i=0; i<visitados.size() && !presente; i++) {
			presente = true;
			for(j=0; j<2; j++) {
				presente = presente && (visitados.get(i).pos[j] == hijo.pos[j]);
			}
		}
		return presente;
	}
	
	public boolean posValida(int coordX, int coordY) {
		for(int i=0; i<noValidas.length; i++) {
			if(noValidas[i].coordX == coordX && noValidas[i].coordY == coordY)
				return false;
		}
		return true;
	}
}
