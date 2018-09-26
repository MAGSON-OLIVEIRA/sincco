var Sincco = Sincco || {};

Sincco.PesquisaRapdidaTipologia = (function() {
	function PesquisaRapdidaTipologia() {
		this.pesquisaRapidaTipologiaModal = $('#pesquisaRapidaTipologias');
		this.descricaoInput = $('#descricaTipologiaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-tipologia-btn');
		
        this.containerTabelaPesquisaRapidaTipologia = $('#containerTabelaPesquisaRapidaTipologias');
        this.htmlTableaPesquisaRapidaTipologia = $('#tabela-pesquisa-rapida-tipologia').html();
        this.template = Handlebars.compile(this.htmlTableaPesquisaRapidaTipologia);
        this.mensagemErro = $('.js-mensagem-erro');

	}

	PesquisaRapdidaTipologia.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaTipologiaModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
    function onModalShow() {
        this.descricaoInput.focus();
    }

	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		$.ajax({
			url : this.pesquisaRapidaTipologiaModal.find('form').attr('action'),
			method : 'GET',
			contentType : 'application/json',
			data : {
				descricao : this.descricaoInput.val()
			},
			success : onPesquisaConcluida.bind(this),
			error : onErroPesquisa
		});
	}
	
    function onPesquisaConcluida(resultado){
        this.mensagemErro.addClass('hidden');
        var html = this.template(resultado);
        this.containerTabelaPesquisaRapidaTipologia.html(html);
        var tabelaTipologiaPesquisaRapida = new Sincco.TabelaTipologiaPesquisaRapida(this.pesquisaRapidaTipologiaModal);
        tabelaTipologiaPesquisaRapida.iniciar();
    }
	
    function onErroPesquisa(){
        this.mensagemErro.removeClass('hidden');
   } 

	function onErroPesquisa() {
		$('.js-mensagem-erro').removeClass('hidden');
	}
	return PesquisaRapdidaTipologia;

}());


Sincco.TabelaTipologiaPesquisaRapida = (function(){
    function TabelaTipologiaPesquisaRapida(modal){
          this.modalTipologia = modal;
          this.tipologia = $('.js-tipologia-pesquisa-rapida');
    }
    TabelaTipologiaPesquisaRapida.prototype.iniciar = function(){
          this.tipologia.on('click', onTipologiaSelecionado.bind(this));
    }
    function onTipologiaSelecionado(evento){
          this.modalTipologia.modal('hide');
          
          var tipologiaSelecionado = $(evento.currentTarget);
          $('#descricaoTipologia').val(tipologiaSelecionado.data('descricao'));
          $('#codigoTipologia').val(tipologiaSelecionado.data('id'));
    }
    return TabelaTipologiaPesquisaRapida;

}());

$(function() {
	var pesquisaRapdidaTipologia = new Sincco.PesquisaRapdidaTipologia();
	pesquisaRapdidaTipologia.iniciar();
});
