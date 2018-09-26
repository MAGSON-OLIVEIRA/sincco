package com.sincco.controller.converter;

import javax.swing.text.MaskFormatter;

import org.springframework.core.convert.converter.Converter;


public class CpfCnpjConverter implements Converter<String,String> {

	@Override
	public String convert(String numero) {
		String cpfCnpj = null;
		if (numero instanceof String) {
			cpfCnpj = (String) numero;
			if (cpfCnpj != null) {
				try {
					if (cpfCnpj.length() == 11) {
						MaskFormatter mf = new MaskFormatter("###.###.###-##");
						mf.setValueContainsLiteralCharacters(false);
						cpfCnpj = mf.valueToString(cpfCnpj);
						// cpfCnpj = cpfCnpj.substring(0, 3) + "."
						// + cpfCnpj.substring(3, 6) + "."
						// + cpfCnpj.substring(6, 9) + "-"
						// + cpfCnpj.substring(9);

					} else if (cpfCnpj.length() == 14) {
						MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
						mf.setValueContainsLiteralCharacters(false);
						cpfCnpj = mf.valueToString(cpfCnpj);
						// cpfCnpj = cpfCnpj.substring(0, 2) + "."
						// + cpfCnpj.substring(2, 5) + "."
						// + cpfCnpj.substring(5, 8) + "/"
						// + cpfCnpj.substring(8, 12) + "-"
						// + cpfCnpj.substring(12);
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cpfCnpj;
	}
}
