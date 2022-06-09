import java.util.LinkedList;

public class LaberintoBFS {
	public LinkedList<NodoBFS> nodosFrontera; //cola de nodos frontera
	public LinkedList<NodoBFS> visitados; //lista de nodos visitados
	public NodoBFS nodoActual, nodoHijo; //nodos hijo y actual
	public Coordenada[] noValidas = new Coordenada[9];
	
	//cosntructor
	public LaberintoBFS(NodoBFS nodoActual, Coordenada[] noValidas) {
		this.nodoActual=nodoActual;
		//se crean la cola y lista a utilizar
		nodosFrontera= new LinkedList<>();
		nodosFrontera.add(nodoActual); //se agrega el nodo actual
		visitados = new LinkedList<>();
		this.noValidas=noValidas;
		
	}
	
	//funcion que crea el arbol de busqueda
	public NodoBFS creaArbol(int[] solucion) {
		while(!nodosFrontera.isEmpty()) {
			//comienza a extraer nodos
			this.nodoActual=nodosFrontera.removeFirst();
			if(nodoActual.esIgual(solucion)) {
				//encontro la solucion 
				return nodoActual;
			}
			
			//si no fue la solucion
			visitados.add(nodoActual);
			CreaHijos(); //crea los hijos del nodo
			for(int i=0; i<nodoActual.hijos.length; i++) {
				nodoHijo=nodoActual.hijos[i]; //agrega los hijos a nodos frontera
				if(nodoHijo != null) {	
					if(!agregarAfrontera(nodoHijo)) { //si no esta en visitados y en la cola
						nodosFrontera.add(nodoHijo);
					}
				}
			}
			
		}
		return null;
	}
	
	//crea o hijos en base al nodoActual
	public void CreaHijos() {
		int[] pos;
		
		if((nodoActual.pos[0] >= 0 || nodoActual.pos[0] <= 5) && nodoActual.pos[1] == 0) {
			nodoActual.setHijoArriba(null);
		} else {
			pos= new int[2];
			pos[0]=nodoActual.pos[0];
			pos[1]=nodoActual.pos[1]-1;
			if(posValida(pos[0], pos[1])) {
				nodoHijo = new NodoBFS(pos, nodoActual);
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
				nodoHijo = new NodoBFS(pos, nodoActual);
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
				nodoHijo = new NodoBFS(pos, nodoActual);
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
				nodoHijo = new NodoBFS(pos, nodoActual);
				nodoActual.setHijoAbajo(nodoHijo);
			} else
				nodoActual.setHijoAbajo(null);
		}
	}
	
	
	public boolean agregarAfrontera(NodoBFS hijo) {
		int i, j;
		boolean presente = false;
		// Mientras no se encuentre un nodo igual, revisar cada uno
		for(i=0; i<nodosFrontera.size() && !presente; i++) {
			presente = true; // Suponemos que son iguales hasta que un digito sea distinto
			for(j=0; j<2 && presente; j++) {
				presente = presente && (nodosFrontera.get(i).pos[j] == hijo.pos[j]);
			}
		}
		
		for(i=0; i<visitados.size() && !presente; i++) {
			presente = true;
			for(j=0; j<2; j++) {
				presente = presente && (visitados.get(i).pos[j] == hijo.pos[j]);
			}
		}
		return presente;
	}
	
	public boolean posValida(int coordX, int coordY) {
		for(int i=0; i< noValidas.length; i++) {
			if(noValidas[i].coordX == coordX && noValidas[i].coordY == coordY)
				return false;
		}
		return true;
	}
}
