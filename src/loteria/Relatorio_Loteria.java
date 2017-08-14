package loteria;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.IOException;

public class Relatorio_Loteria {
	public static void main(String[] args) throws IOException {
		Sorteio sorteio = new Sorteio();
		BufferedReader inputStream = null;
		PrintWriter outputStream = null;
		int contaTABLE = 0;
		int contaTR = 0;
		int contaTD = 0;
		String valor = new String();
		int Numeros[] = new int[26];
		try {
			inputStream = new BufferedReader(new FileReader("D_LOTFAC.HTM"));
			outputStream = new PrintWriter(new FileWriter("relatorio_loteria.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				if (l.indexOf("table") != -1) {
					contaTR = 0;
					contaTABLE++;
				}
				if (contaTABLE == 1) {

					if (l.indexOf("<tr") != -1) {
						contaTD = 0;
						contaTR++;
					}
					if (contaTR > 1) {
						if (l.indexOf("rowspan") != -1 && l.indexOf("<td") != -1 && contaTD <= 17) {
							contaTD++;
							valor = new String();
							for (int i = (l.indexOf(">")) + 1; l.charAt(i) != ('<'); i++) {
								String aux = String.valueOf(l.charAt(i));
								valor = valor.concat(aux);
							}
							switch (contaTD) {
							case 1:
								sorteio.NumeroSorteio = Integer.parseInt(valor);
								Numeros[0] += 15;
								break;
							case 2:
								sorteio.DataSorteio = valor;
								break;
							case 3:
								sorteio.Numero1 = Integer.parseInt(valor);
								Numeros[sorteio.Numero1]++;
								break;
							case 4:
								sorteio.Numero2 = Integer.parseInt(valor);
								Numeros[sorteio.Numero2]++;
								break;
							case 5:
								sorteio.Numero3 = Integer.parseInt(valor);
								Numeros[sorteio.Numero3]++;
								break;
							case 6:
								sorteio.Numero4 = Integer.parseInt(valor);
								Numeros[sorteio.Numero4]++;
								break;
							case 7:
								sorteio.Numero5 = Integer.parseInt(valor);
								Numeros[sorteio.Numero5]++;
								break;
							case 8:
								sorteio.Numero6 = Integer.parseInt(valor);
								Numeros[sorteio.Numero6]++;
								break;
							case 9:
								sorteio.Numero7 = Integer.parseInt(valor);
								Numeros[sorteio.Numero7]++;
								break;
							case 10:
								sorteio.Numero8 = Integer.parseInt(valor);
								Numeros[sorteio.Numero8]++;
								break;
							case 11:
								sorteio.Numero9 = Integer.parseInt(valor);
								Numeros[sorteio.Numero9]++;
								break;
							case 12:
								sorteio.Numero10 = Integer.parseInt(valor);
								Numeros[sorteio.Numero10]++;
								break;
							case 13:
								sorteio.Numero11 = Integer.parseInt(valor);
								Numeros[sorteio.Numero11]++;
								break;
							case 14:
								sorteio.Numero12 = Integer.parseInt(valor);
								Numeros[sorteio.Numero12]++;
								break;
							case 15:
								sorteio.Numero13 = Integer.parseInt(valor);
								Numeros[sorteio.Numero13]++;
								break;
							case 16:
								sorteio.Numero14 = Integer.parseInt(valor);
								Numeros[sorteio.Numero14]++;
								break;
							case 17:
								sorteio.Numero15 = Integer.parseInt(valor);
								Numeros[sorteio.Numero15]++;
								outputStream.write(sorteio.toString());
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(valor);
		} finally {
			outputStream.write("=====================Estatisticas=====================\n");
			for (int i = 1; i < Numeros.length; i++) {
				double percent =(double)Numeros[i] * 100 / Numeros[0];
				BigDecimal bd = new BigDecimal(percent).setScale(3, RoundingMode.HALF_EVEN);
				outputStream.write("Numero = " + i + "Total de vezes sorteado = "+Numeros[i]+" porcentagem = " + bd + "%\n");
			}
			if (inputStream != null) {
				inputStream.close();
				if (outputStream != null) {
					outputStream.close();
				}
			}
		}
	}
}