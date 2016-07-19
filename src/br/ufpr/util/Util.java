package br.ufpr.util;

import java.text.Normalizer;

public class Util {

	public static void main(String[] Args) {
		/*System.out.println(funcaoMaiuscula("01_Cadastros básicos do sistema"));
		System.out.println(funcaoMinuscula("01_Cadastros básicos do sistema"));
		System.out.println(funcaoMaiuscula("R"));
		System.out.println(funcaoMaiuscula("nm_usuario"));
		System.out.println(funcaoMaiuscula("dt_alteração"));*/
				
		System.out.println(functionForImportRecords("[1, Procedimentos Cirurgicos]"));
		System.out.println(functionForImportRecords("[1,  Procedimentos Cirurgicos]"));
		System.out.println(functionForImportRecords("[1,Procedimentos Cirurgicos]"));
		System.out.println(functionForImportRecords("[2, Procedimentos de urgencia]"));
		System.out.println(functionForImportRecords("[331,345,DOCUMENTACAO ORTODONTICA \"A\"]"));

		//System.out.println(functionForInverseObjectProperties("temRestricaoSexo"));

		/*System.out.println(cleanDataType("number"));
		System.out.println(cleanDataType("varchar2"));
		System.out.println(cleanDataType("number(15,2)"));
		System.out.println(cleanDataType("varchar2 (100)"));
		System.out.println(cleanDataType("CHAR"));*/
	}

	/**
	 * Função para remover acentos, espaços em branco, preposições e formar uma única palavra com as primeiras letras em maiúsculo.
	 * Ex.: Cadastros básicos do sistema -> CadastrosBasicosSistema
	 * Alteração: remover também os números e sinal de underline.
	 * Ex.: 01_Cadastros básicos do sistema -> CadastrosBasicosSistema
	 * 
	 * @param str
	 * @return
	 */
	public static String funcaoMaiuscula(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		
		if (str.length() <= 2) {
			return funcaoMaiusculaSizeLE2(str);
		}

		str = removeNumbersAndOthers(str);

		String[] chunks = str.toLowerCase().split(" ");
		StringBuilder retorno = new StringBuilder("");

		for (int i = 0; i < chunks.length; i++) {
			if (chunks[i].length() <= 2) {
				continue;
			}

			retorno.append(toCamelCase(removeAccents(chunks[i].trim())));
		}

		return retorno.toString();
	}
	
	
	public static String funcaoMaiusculaSizeLE2(String str) {
		str = toCamelCase(removeAccents(removeNumbersAndOthers(str)));
		return str;
	}

	/**
	 * Função para remover acentos, espaços em branco, preposições e formar uma única palavra com as primeiras letras em maiúsculo.
	 * Porém a primeira letra da primeira palavra deve ser minúscula.
	 * Ex.: Cadastros básicos do sistema -> cadastrosBasicosSistema
	 * 
	 * @param str
	 * @return
	 */
	public static String funcaoMinuscula(String str) {
		String retorno = funcaoMaiuscula(str);

		if ("".equals(retorno) || retorno.length() == 1) {
			return retorno.toLowerCase();
		}

		return (retorno.substring(0, 1).toLowerCase()) + retorno.substring(1);
	}

	/**
	 * 
	 * @param init
	 * @return
	 */
	public static String toCamelCase(final String init) {
		if (init == null || "".equals(init)) {
			return "";
		}

		final StringBuilder ret = new StringBuilder(init.length());

		for (final String word : init.split(" ")) {
			if (!word.isEmpty()) {
				ret.append(word.substring(0, 1).toUpperCase());
				ret.append(word.substring(1).toLowerCase());
			}
			if (!(ret.length()==init.length()))
				ret.append(" ");
		}

		return ret.toString();
	}

	/**
	 * Remove acentos.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeAccents(String string) {
		StringBuilder sb = new StringBuilder(string.length());
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		for (char c : string.toCharArray()) {
			if (c <= '\u007F') sb.append(c);
		}
		return sb.toString();
	}

	public static String removeNumbersAndOthers(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}

		//str.replaceAll("[^A-Za-z]", ""); // Only letters.

		str = str.replaceAll("[0-9]", ""); // Remove all the numbers.
		str = str.replaceAll("_", " ");
		
		str = str.replaceAll("[(*¨%&!?´`#$%¨*&@]", "")//Remove all special Characters
			       .replaceAll("[()={}\\[\\]~^|<>]", "").replaceAll("[-_+'ªº/¬]", "");
		return str;
	}

	/**
	 * Função específica para o método RdbToOntoBO.importRecords.
	 * Deve alterar uma String conforme o exemplo:
	 * De: [1, Procedimentos Cirurgicos]
	 * Para: _1_Procedimentos_Cirurgicos
	 * 
	 * @param str
	 * @return
	 */
	public static String functionForImportRecords(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}

		str = str.replaceAll("\\[", "").replaceAll("\\]","").replaceAll(",", " ").replaceAll("\"", "");

		String[] strArr = str.split(" ");
		str = "";
		String aux = "";

		for (int i = 0; i < strArr.length; i++) {
			if ("".equals(strArr[i].trim())) {
				continue;
			}

			try {
				Integer.parseInt(strArr[i]);
				str += "_" + strArr[i].trim();
			}
			catch (NumberFormatException e) {
				aux = funcaoMaiuscula(strArr[i].trim());

				if (!"".equals(aux)) {
					str += "_" + aux;
				}
			}
		}

		return str;
	}

	/**
	 * Função específica para o método DownloadOWLFileBO.setObjectProperty.
	 * Deve apterar uma String conforme o exemplo:
	 * De: temRestricaoSexo
	 * Para: eRestricaoSexoDe
	 * 
	 * @param str
	 * @return
	 */
	public static String functionForInverseObjectProperties(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}

		if ("Tem".equals(str.substring(3))) {
			str = "e" + str.substring(3) + "De";
		} else {
			str = "e" + str.substring(2) + "De";
		}
		

		return str;
	}

	/**
	 * Se o objeto for null, esta função retorna vazio.
	 * 
	 * @param obj
	 * @return
	 */
	public static String getNullText(Object obj) {
		String s = "" + obj;
		if (s.equals("null")) {
			s = "";
		}

		return s.trim();
	}

	/**
	 * Função usada para limpar um valor obtido da T018 para ficar conforme algum dos registros da tb_type.
	 * Ex.:
	 * number(15,2) => number
	 * varchar2 (100) => varchar2
	 * 
	 * @param str
	 * @return
	 */
	public static String cleanDataType(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		
		// Deixar tudo minúsculo.
		str = str.toLowerCase();
		
		// Tirar todo o conteúdo que aparece após o parêntese.
		int parenthesis = str.indexOf("(");
		
		if (parenthesis > 0) {
			str = str.substring(0, parenthesis);
		}
		
		// Tirar os espaços que podem ter ficado sobrando no começo e/ou no final.
		str = str.trim();
		
		return str;
	}
		
}