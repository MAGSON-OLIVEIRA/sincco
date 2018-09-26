package com.sincco.session;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sincco.model.Itens;


class TabelaItensServico{
	
	private String uuid;
	private List<Itens> itens = new ArrayList<>();
	
	public TabelaItensServico(String uuid){
		this.uuid = uuid;
	}

	public void adicionarItem(BigDecimal medida, BigDecimal moradore, BigDecimal dias) {
		Optional<Itens> itemOpcional = itens.stream().filter(i -> i.getDataCompetencia().equals(LocalDate.now())).findAny();
		if (itemOpcional.isPresent()) {
			System.out.println("Item com data de competencia já cadastrado.");
		} else {
			Itens iten = new Itens();
			iten.setContador(itens.size()+1);
			iten.setDataCompetencia(LocalDate.now());
			iten.setDiasMedido(dias);
			iten.setQuantidadeMoradores(moradore);
			iten.setMedida(medida);
			itens.add(0, iten);
		}

	}
	
	public void excluirItem(Integer cont){
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getContador().equals(cont))
				.findAny().getAsInt();
		itens.remove(indice);
	}

	public int total() {
		return itens.size();
	}
	public List<Itens> getItens() {
		return itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensServico other = (TabelaItensServico) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}


	
    public BigDecimal getTotalMedida(){
    	BigDecimal totalMediaMoradores = totalMedidaMoradores();
    	BigDecimal totalMediaDiaria = totalMedidaDiaria();
    	BigDecimal totalMedida = BigDecimal.ZERO;
    	if(itens.size() > 0){
          totalMedida = totalMediaDiaria.divide(totalMediaMoradores, MathContext.DECIMAL32);  
    	} 
    	return totalMedida;
    }

	private BigDecimal totalMedidaMoradores() {
		BigDecimal  totalMediaMoradores = itens.stream()
    			.map(Itens::getQuantidadeMoradores)
    			.reduce(BigDecimal::add)
    			.orElse(BigDecimal.ZERO);
		return totalMediaMoradores;
	}

	private BigDecimal totalMedidaDiaria() {
		BigDecimal  totalMediaDiaria  = itens.stream()
    			.map(Itens::getMedidaDiaria)
    			.reduce(BigDecimal::add)
    			.orElse(BigDecimal.ZERO);
		return totalMediaDiaria;
	}
	
	
	
	
}
