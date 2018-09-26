package com.sincco.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sincco.model.Servico;
import com.sincco.repository.Servicos;

@Service
public class CadastroServicoService {
	@Autowired
	private Servicos servicos;
	
	@Transactional
	public void salvar(Servico servico){
		if(servico.isNova()){
			servico.setDataInicio(LocalDate.now());
		}
		servicos.save(servico);
	}
	 
	@Transactional
	public List<Servico> lista(){
		return servicos.findAll();
	}

	
	public Servico pesquisaPorId(Long id){
		return servicos.findOne(id);
	}
	
    @Transactional
    public void ativar(Servico servico) {
          if(servico.isNova()){
                servico.setDataInicio(LocalDate.now());
          }
          servico.setAtivo(true);
          servicos.save(servico);

    }
	
}

