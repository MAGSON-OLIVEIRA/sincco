package com.sincco.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sincco.model.Itens;

@SessionScope
@Component
public class TabelasItensSession {
	
	private Set<TabelaItensServico> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, 
			BigDecimal medida, BigDecimal quantidadeMorador, BigDecimal diasMedido) {
			TabelaItensServico tabela = buscarTabelarPorUuid(uuid);
			tabela.adicionarItem(medida, quantidadeMorador, diasMedido);
			tabelas.add(tabela);
	}



	public void excluirItem(String uuid, Integer codigo) {
		TabelaItensServico tabela = buscarTabelarPorUuid(uuid);
		tabela.excluirItem(codigo);
	}

	public List<Itens> getItens(String uuid) {
		return buscarTabelarPorUuid(uuid).getItens();
	}
	public Object getTotalMedida(String uuid) {
		TabelaItensServico tabela = buscarTabelarPorUuid(uuid);
		return buscarTabelarPorUuid(uuid).getTotalMedida();
	}
	
	
	private TabelaItensServico buscarTabelarPorUuid(String uuid) {
		TabelaItensServico tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny().orElse(new TabelaItensServico(uuid));
		return tabela;
	}




	
		
}
