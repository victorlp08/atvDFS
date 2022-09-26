package grafoAtv04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class App {
	
	//Essas variaveis têm que serem astatic, devido o MAIN ser static.
	public static int time = 0, order, size, initialVtc;
	public static int i[], prev[], f[];
	public static String color[], line;
	
	public static LinkedList<LinkedList<Integer>> gra = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		
		lerArq("src\\\\pequenoG.txt");
		initialVtc = 0;

		System.out.println("---------------------------------------------------------------");
		System.out.println("Grafo Verticex: " + initialVtc);
		System.out.println("Verticex Inicial: " +initialVtc);
		DFSstart(gra, initialVtc);
		System.out.println("---------------------------------------------------------------");

		System.out.println();

		System.out.println("---------------------------------------------------------------");
		System.out.println("Grafo Final: \n");
		especVtc();
		System.out.println("---------------------------------------------------------------");

	}

	public static void lerArq(String caminho) throws FileNotFoundException, IOException {

		try (BufferedReader buffRead = new BufferedReader(new FileReader(caminho))) {
			line = buffRead.readLine();
			order = Integer.parseInt(line);

			line = buffRead.readLine();
			size = Integer.parseInt(line);

			for (int j = 0; j < order; ++j) {
				gra.add(new LinkedList<>());
			}

			color = new String[order];
			i = new int[order];
			prev = new int[order];
			f = new int[order];

			line = buffRead.readLine();

			while (line != null) {
				String vu[] = line.split(" ");

				int v = Integer.parseInt(vu[0]);
				int u = Integer.parseInt(vu[1]);

				gra.get(v).add(u);
				gra.get(u).add(v);

				line = buffRead.readLine();
			}
		}
	}
	
	public static void DFSstart(LinkedList<LinkedList<Integer>> g, int s) {
		for (int j = 0; j < g.size(); j++) {
			color[j] = "BRANCO";
			i[j] = f[j] = prev[j] = -1;
		}

		time = 1;
		DFSvisited(g, s);
	}

	public static void printPath(int v) {
		if (v == initialVtc) {
			System.out.println(v);
		}

		else {
			if (prev[v] == -1) {
				System.out.println("Caminho Inexistente");
			}

			else {
				printPath(prev[v]);
				System.out.println(v);
			}
		}
	}

	public static void DFSvisited(LinkedList<LinkedList<Integer>> g, int u) {

		color[u] = "CINZA";
		i[u] = time++;

		for (Integer v : g.get(u)) {
			if ("BRANCO".equals(color[v])) {
				prev[v] = u;
				especVtc();
				printPath(v);

				DFSvisited(g, v);
			}
		}

		color[u] = "PRETO";
		f[u] = time++;
	}

	public static void especVtc() {
		for (int j = 0; j < gra.size(); j++) {
			System.out.println("Vertex: " + j + " -> cor: " + color[j] + " -> (" + i[j] + "/" + f[j] + ")");
		}
	}
}
