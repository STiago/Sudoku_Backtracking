import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

public final class SudokuBacktracking 
{
	/**
	 * tablero almacena la información original de la plantilla
	 * (Utilizada para comprobar donde hay un elemento inamovible)
	 */
	private int[] tablero;
	
	/**
	 * Tablero es donde se calcula la solución al sudoku
	 */
	private int[] tableroTemp;
	
	/**
	 * nivel actual de avance de la solución
	 */
	private int nivel = 0;
	
	/**
	 * fin es establecerá a verdad cuando se resuelva el sudoku,
	 * lo que provocará el fin del algoritmo
	 */
	private boolean fin = false;
	
	
	public SudokuBacktracking(String entrada) throws ArrayIndexOutOfBoundsException 
	{
		if(entrada.length() < 81)
			throw new ArrayIndexOutOfBoundsException("La entrada no contiene un sudoku completo");
		
		tablero = new int[81];
		tableroTemp = new int[81];
		for(int i = 0; i < 81; i++) 
		{
			if(entrada.charAt(i) != '.') 
			{
				tableroTemp[i] = tablero[i] = Integer.parseInt(entrada.substring(i, i+1));
			}
		}
	}
	
	public void pintaTablero() 
	{
		PrintStream out = System.out;
		
		System.out.println("-----------------------");
		for(int i = 0; i < 9; i++) 
		{
			for(int j = 0; j < 9; j++) 
			{
				out.print(tableroTemp[(i * 9) + j]+" ");
				
				if((j + 1) % 3 == 0)
					out.print("| ");
			}
			
			if((i + 1) % 3 == 0)
				System.out.println("\n-----------------------");
			else
				System.out.println();
		}
		
		System.out.println();
	}
	
	public void resolverBacktracking() 
	{	
		/**
		 *  Elementos ya colocados por la plantilla
		 *  no son editables
		 */
		while(tablero[nivel] > 0)
			nivel++;
		
		do 
		{	
			generar();
			
			if(solucion())
				fin = true;
			else if(criterio())
			{
				incrementarNivel();
			}
			else
			{
				while(!masHermanos())
					retroceder();
			}
		}
		while(!fin);
	}
}
