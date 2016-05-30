package br.ufpr.util;

import java.text.Normalizer;

public class Util {

	/*public static void main(String[] Args) {
		//System.out.println(funcaoMaiuscula("01_Cadastros básicos do sistema"));
		//System.out.println(funcaoMinuscula("01_Cadastros básicos do sistema"));
		System.out.println(funcaoForImportRecords("[1, Procedimentos Cirurgicos]"));
		System.out.println(funcaoForImportRecords("[1,  Procedimentos Cirurgicos]"));
		System.out.println(funcaoForImportRecords("[1,Procedimentos Cirurgicos]"));
	}*/
	
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
		str = str.replaceAll("_", "");
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
	public static String funcaoForImportRecords(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		
		str = str.replaceAll("\\[", "").replaceAll("\\]","").replaceAll(",", " ");
		
		String[] strArr = str.split(" ");
		str = "";
		
		for (int i = 0; i < strArr.length; i++) {
			if ("".equals(strArr[i].trim())) {
				continue;
			}
			
			try {
				Integer.parseInt(strArr[i]);
				str += "_" + strArr[i].trim();
			}
			catch (NumberFormatException e) {
				str += "_" + funcaoMaiuscula(strArr[i].trim());
			}
		}
		
		return str;
	}
}