Sincco = Sincco || {};

Sincco.PesquisaRapidaCep = (function() {
	
	function PesquisaRapidaCep(){
		this.pesquisaRapidaCepModal = $('#pesquisaRapidaCepsServicos');
		this.cepServicoInput = $('#cepServicoModal');
		this.pesquisaRapidaCepBtn = $('.js-pesquisa-rapida-cep-btn');
		
		this.containerTabelaPesquisaRapidaCep = $('#containerTabelaPesquisaRapidaCep');
		this.htmalTabelaPesquisaRapidaCep = $('#tabela-pesquisa-rapida-cep').html();
		
		this.templatePesquisaCep = Handlebars.compile(this.htmalTabelaPesquisaRapidaCep);
		
		this.msnErro = $('.js-mensagem-erro2');
	}
	
	PesquisaRapidaCep.prototype.iniciar = function(){
		this.pesquisaRapidaCepBtn.on('click', onPesquisaRapidaCepClicado.bind(this));
	}
	
	function onPesquisaRapidaCepClicado(event){
		event.preventDefault();
		$.ajax({
			url : this.pesquisaRapidaCepModal.find('form').attr('action'),
			method :  'GET',
			contentType : 'application/json', 
			data : {
				cep : this.cepServicoInput.val()
			}, 
			success : onPesquisaCepConcluida.bind(this), 
			error : onErroPesquisaCep
		
		});
	}
	
	function onPesquisaCepConcluida(resultado){
		this.msnErro.addClass('hidden');
		var html = this.templatePesquisaCep(resultado);
		this.containerTabelaPesquisaRapidaCep.html(html);
		var tabelaPesquisaRapidaCep = new Sincco.TabelaPesquisaRapidaCep(this.pesquisaRapidaCepModal);
		
		tabelaPesquisaRapidaCep.iniciar();
	}
	
	function onErroPesquisaCep(){
		this.msnErro.removeClass('hidden');
	}
	function onErroPesquisaCep() {
		$('.js-mensagem-erro2').removeClass('hidden');
	}
	
	return PesquisaRapidaCep;
}());

Sincco.TabelaPesquisaRapidaCep = (function(){
	function TabelaPesquisaRapidaCep(modal){
		this.modalCep = modal;
		this.cepServico = $('.js-cep-pesquisa-rapida');
	}
	TabelaPesquisaRapidaCep.prototype.iniciar = function(){
		this.cepServico.on('click', onCepSelecionado.bind(this));
	}
	function onCepSelecionado(event){
		this.modalCep.modal('hide');
		var cepSelecionado = $(event.currentTarget);
		$('#codigoCep').val(cepSelecionado.data('id'));
		$('#nomeCep').val(cepSelecionado.data('cep'));
	}
	return TabelaPesquisaRapidaCep;
}());

$(function(){
	var pesquisaRapidaCep = new Sincco.PesquisaRapidaCep();
	pesquisaRapidaCep.iniciar();
});
