import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;


public class VentanaLaberinto implements ActionListener{
	Coordenada[] noValidas = new Coordenada[9];
	JDialog ventanita;
	JButton bot;
	//parta el tablero
	private JButton[][] Casilla;
	private int[] inicial;
	String Aux; //para guardar el valor de la casilla
	private JPanel panel; //panel principal
	private JFrame ventana; //ventana 
	//private int[] valorInicial = new int[4]; //valores iniciales
	private JButton salir, BFS, DFS; //botones
	private JMenu opciones; //menu de opciones
	private JMenuItem instrucciones; //menu de insrucciones
	private JMenuBar menu; //barra de menu
			
	private JButton aceptar; //boton aceptar
	private JDialog instruccion; //ventana de instrucciones
			
	int[] solucion; //soluciones
	private Timer timer;
	
	public VentanaLaberinto(){
		generaNovalidas(2); //genera coordenadas no validas
		IniciaComponentes();

		//se generan las pos a donde se encontrara la sol
		solucion = new int[2];
		solucion[0]=5;
		solucion[1]=5;
	}
	
	public void generaNovalidas(int prob){
		Random r = new Random();
		int i, j, k=0;
		for(i = 0; i < 6 && k<9; i++) {
			for(j=0; j < 6 && k<9; j++) {
				if(r.nextInt(9)<=prob && (j != 5 || i!=5)) noValidas[k++] = new Coordenada(i,j);
			}
		}
		Coordenada aux[] = noValidas;
		noValidas = new Coordenada[k];
		inicial = new int[2];
		for(i = 0; i < k; i++) {
			noValidas[i] = aux[i];
		}
		do {
			inicial[0]=r.nextInt(6);
			inicial[1]=r.nextInt(6);
		}while(!posValida(inicial[0], inicial[1]) || (inicial[0]==5 && inicial[1]==5)); // 5,5 es la meta no puede ser inicial tambien
	}
	
	boolean posValida(int coordX, int coordY) {
		for(int i=0; i<noValidas.length; i++) {
			if(noValidas[i].coordX == coordX && noValidas[i].coordY == coordY)
				return false;
		}
		return true;
	}
	
	public void IniciaComponentes(){
		//botones
		salir =  new JButton("Salir");
		BFS = new JButton("Resuelve BFS");
		DFS = new JButton("Resuelve DFS");
		 //para el menu de instrucciones
		menu = new JMenuBar();
		opciones= new JMenu();
		opciones.setText("Opciones");
		menu.add(opciones);
		instrucciones = new JMenuItem("Instrucciones");
		opciones.add(instrucciones);
		instrucciones.addActionListener(this);
					
		//se colocan los botones
		salir.setBounds(340,385,100,30);
		BFS.setBounds(65,385,120,30);
		DFS.setBounds(200,385,120,30);
					
		//se agregan los actionListeners de cada boton
		salir.addActionListener(this);
		BFS.addActionListener(this);
		DFS.addActionListener(this);
					
		//se crea la ventana
		ventana = new JFrame("Bambolis's Laberinto");
		ventana.setLayout(null);
		//fuente = new Font("Courier New", Font.BOLD, 20);
		//se agregan los componentes
		ventana.setJMenuBar(menu);
		//inicializamos la matriz que sera nuestro tablero
		Casilla =  new JButton[6][6];
		//declaramos el panel que contendra el tablero
		panel= new JPanel();
		//agregamos la fuente de letra al panel
		//panel.setFont(fuente);
		//hacemos el panel visible
		panel.setVisible(true);
		//dependiendo de las dimensiones del tablero el panel crece o decrece
		panel.setBorder(BorderFactory.createTitledBorder("Laberinto"));		
		panel.setBounds(35,20,470,350);
		panel.setLayout(new GridLayout(6,6));
		//se inicializa el tablero
		
		int j,i;
		for(j=0; j<6; j++) {
			for(i=0; i<6; i++) {
				Casilla[j][i]= new JButton();
				Casilla[j][i].setForeground(Color.BLACK);
				Casilla[j][i].setBackground(Color.WHITE);
				panel.add(Casilla[j][i]);
			}
		}
		Casilla[inicial[1]][inicial[0]].setIcon(new ImageIcon("pac.png"));
		for(i =0; i< noValidas.length; i++) {
			Casilla[noValidas[i].coordY][noValidas[i].coordX].setBackground(Color.BLACK); //celdas no validas
		}
		
		
		Casilla[5][5].setIcon(new ImageIcon("cereza.png")); //meta del laberinto
					
		//se agregan los botones a la ventana
		ventana.add(salir);
		ventana.add(BFS);
		ventana.add(DFS);
		
		ventana.getContentPane().add(panel,BorderLayout.CENTER);
		ventana.validate();
		ventana.setBounds(0,0,550,500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
								
	}
	
	public void comienzaSimulacion(int opcion) {
		Casilla[5][5].setIcon(new ImageIcon("cereza.png")); //se fija la meta
		
		if(opcion == 2) { //BFS
			NodoBFS nodoInicial = new NodoBFS(inicial);
			
			LaberintoBFS laberinto = new LaberintoBFS(nodoInicial, noValidas);
			NodoBFS nodoFin = laberinto.creaArbol(solucion);
			
			
			if(nodoFin !=null)
				System.out.println("Se encontró la solución con BFS: ");
			else
				noHaySol();
			
			LinkedList<NodoBFS> result = new LinkedList<>();
			while(nodoFin != null) {
				result.addFirst(nodoFin);
				nodoFin = nodoFin.padre;
			} 
			System.out.println("Los nodos generados con BFS son: " + result.size());
			
			timer = new Timer(500,  new ActionListener() {
			private int i=0;
			int tamano=result.size();
			public void actionPerformed(ActionEvent evt) {
				if(i>=tamano) {
					timer.stop();
					return;
				}
					int x=result.get(i).pos[0];
					int y=result.get(i).pos[1];
					System.out.println( x+ " " + y);
					Casilla[y][x].setIcon(new ImageIcon("pac.png"));
					if(i != 0) {
						int x1=result.get(i-1).pos[0];
						int y1=result.get(i-1).pos[1];
						Casilla[y1][x1].setIcon(new ImageIcon("rB.png"));
					}
				
				i++;
			}
		});
		timer.start();
		
		} else {//DFS
			LinkedList<NodoDFS> visitados = new LinkedList<>();
			NodoDFS nodoI = new NodoDFS(inicial);
			LaberintoDFS laberinto2 = new LaberintoDFS(nodoI, noValidas);
			NodoDFS nodoFinal = laberinto2.creaArbol(nodoI, solucion, visitados);
			
			if(nodoFinal != null)
				System.out.println("Con DFS la solucion es: ");
			else
				noHaySol();
				//System.out.println("No hay solución :( DFS)");
			LinkedList<NodoDFS> result2 = new LinkedList<>();
			while(nodoFinal != null) {
				result2.addFirst(nodoFinal);
				nodoFinal = nodoFinal.padre;
			} 
			System.out.println("Los nodos generados con DFS son: " + result2.size());
			timer = new Timer(500,  new ActionListener() {
				private int i=0;
				int tamano=result2.size();
				public void actionPerformed(ActionEvent evt) {
					if(i>=tamano) {
						timer.stop();
						return;
					}
						int x=result2.get(i).pos[0];
						int y=result2.get(i).pos[1];
						System.out.println( x+ " " + y);
						Casilla[y][x].setIcon(new ImageIcon("pac.png"));
						if(i != 0) {
							int x1=result2.get(i-1).pos[0];
							int y1=result2.get(i-1).pos[1];
							Casilla[y1][x1].setIcon(new ImageIcon("rB.png"));
						}
					
					i++;
				}
			});
			timer.start();
		}
	}

	//ventana de no hay solucion
	public void noHaySol() {
		ventanita = new JDialog();
		ventanita.setVisible(true);
		ventanita.setSize(100, 100); //tamanio
		ventanita.setLocationRelativeTo(null);
		JPanel pan= new JPanel();
		JLabel etiq = new JLabel("No hay solución");
		bot = new JButton("Aceptar");
		bot.addActionListener(this);
		
		pan.add(etiq);
		pan.add(bot);
		
		ventanita.add(pan);
	}
	
	public void ventanaInstrucciones() {
		//se crea la nueva vetana de opciones
		instruccion = new JDialog();
		instruccion.setVisible(true);
		instruccion.setTitle("Intrucciones"); //nombre
		instruccion.setSize(320, 165); //tamanio
		instruccion.setLocationRelativeTo(null);
			
		//dependiendo de las dimensiones del tablero el panel crece o decrece
		JPanel panelMini= new JPanel();
		panelMini.setBorder(BorderFactory.createTitledBorder("Intrucciones"));
		instruccion.add(panelMini);
		//se crean los botones
			
		
		aceptar =  new JButton("Aceptar");
		JLabel resolver =  new JLabel("**El Puzzle se puede resolver de 2 formas");
		//validos.setFont(fuente);
		JLabel izq = new JLabel("     1) con Búsqueda en Amplitud (BFS)");
		JLabel central = new JLabel("      2) con Búsqueda en Profundidad (DFS)");
		aceptar.addActionListener(this);
			
		//agrega cada uno de los elementos
		resolver.setBounds(25, 25, 200, 50);
		panelMini.add(resolver);
		izq.setBounds(75, 75, 200, 50);
		panelMini.add(izq);
		central.setBounds(75, 100, 200, 50);
		panelMini.add(central);
		panelMini.add(aceptar);
	}

	//para los action listeners de los botones
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salir) 
			System.exit(0); //salir del juego
		else if(e.getSource() == DFS)
			comienzaSimulacion(1); //simulacion por DFS
		else if(e.getSource() == BFS) 
			comienzaSimulacion(2); //sumulacion por BFS
		else if(e.getSource() == instrucciones)
			ventanaInstrucciones(); //ventana de instrcciones
		else if(e.getSource() == aceptar)
			instruccion.dispose();
		else if(e.getSource() == bot)
			ventanita.dispose();
	}

}
