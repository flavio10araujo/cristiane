package br.ufpr.util;

import java.text.Normalizer;

public class Util {

	/*public static void main(String[] Args) {
		System.out.println(funcaoMaiuscula("Cadastros b�sicos do sistema"));
		System.out.println(funcaoMinuscula("Cadastros b�sicos do sistema"));
	}*/
	
	/**
	 * Fun��o para remover acentos, espa�os em branco, preposi��es e formar uma �nica palavra com as primeiras letras em mai�sculo.
	 * Ex.: Cadastros b�sicos do sistema -> CadastrosBasicosSistema
	 * 
	 * @param str
	 * @return
	 */
	public static String funcaoMaiuscula(String str) {
		if (str == null || "".equals(str)) {
			return "";
		}
		
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
	 * Fun��o para remover acentos, espa�os em branco, preposi��es e formar uma �nica palavra com as primeiras letras em mai�sculo.
	 * Por�m a primeira letra da primeira palavra deve ser min�scula.
	 * Ex.: Cadastros b�sicos do sistema -> cadastrosBasicosSistema
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
}