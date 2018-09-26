var Sincco = Sincco || {};
Sincco.AdicionarItemServico = (function() {   
      function AdicionarItemServico(){
            this.adicionaItensModal = $('#adicionaItens');
            
            this.mediacaoInput = $('#medicaoModal');
            this.mediacaoInput.maskMoney({ precision: 0, thousands: ''});

            this.qtdMoradoresModalInput = $('#qtdMoradoresModal');
            this.qtdMoradoresModalInput.maskMoney({ precision: 0, thousands: ''});

            this.diasMesModalInput = $('#diasMesModal');
            this.diasMesModalInput.maskMoney({ precision: 0, thousands: ''});
            
            this.pesquisaRapidaBtn = $('.js-adicionar-item-btn');
            this.tabelaItensServio = $('.js-tabela-itens-continer');
            
            this.mensagemErro = $('.js-mensagem-erro-add');
            this.uuid = $('#uuid');

            this.emitter = $({});
            this.on = this.emitter.on.bind(this.emitter);

      }
      AdicionarItemServico.prototype.iniciar = function(){
            this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
            this.adicionaItensModal.on('shown.bs.modal', onModalShow.bind(this));
            
            bindQuantidades.call(this);
            bindTabelaItem.call(this);
      }

      function onModalShow() {
            this.mediacaoInput.focus();
      }
      function onPesquisaRapidaClicado(event){
                var medida = this.mediacaoInput.val();
                var quantidadeMorador = this.qtdMoradoresModalInput.val();
                var diasMedido = this.diasMesModalInput.val();
               
                if(diasMedido > 31){
                      this.diasMesModalInput.val(31);
                     diasMedido = 31
                }
            
                if(quantidadeMorador > 99){
                      this.qtdMoradoresModalInput.val(99);
                      quantidadeMorador = 99
                }
                  event.preventDefault();
                  $.ajax({
                        url: this.adicionaItensModal.find('form').attr('action'),
                        method: 'GET',
                        contentType: 'application/json',
                        data: {
                              medida: this.mediacaoInput.val(),
                              quantidadeMorador: this.qtdMoradoresModalInput.val(),
                              diasMedido: this.diasMesModalInput.val(),
                              uuid: this.uuid.val()
                              
                        },
                        success: onPesquisaConcluida.bind(this)
                        ,error: onErroPesquisa.bind(this)
                  });
}

      function onPesquisaConcluida(html){
            //console.log('retorno',resultado);
    	  	this.mensagemErro.addClass('hidden');
            this.tabelaItensServio.html(html);    
            bindQuantidades.call(this);
            var tabelaItem = bindTabelaItem.call(this);
            this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('total-medida'));

      }
      function onErroPesquisa(){
            this.mensagemErro.removeClass('hidden');
      }  
      
      function onDoubleClick(evento){
          var item = $(evento.currentTarget);
          item.toggleClass('solicitando-exclusao');
    }
      
      function onExclusaoItemClick(evento){
    	  var codigo = $(evento.target).data('contador-itens');
    	  var resposta = $.ajax({
    		  url: 'item/' + codigo + '/' + this.uuid.val(),
    		  method: 'DELETE'
    	  });
    	  resposta.done(onPesquisaConcluida.bind(this));
      }
      
      function bindQuantidades(){
          this.mediacaoInput.val('');
          this.qtdMoradoresModalInput.val('');
          this.diasMesModalInput.val('');
          this.adicionaItensModal.modal('hide');
      }
      
      function bindTabelaItem(){
          var tabelaItem = $('.js-tabela-item');
          tabelaItem.on('dblclick', onDoubleClick);
          $('.js-excluir-item-btn').on('click', onExclusaoItemClick.bind(this));
          return tabelaItem;
      }
      
      
      return AdicionarItemServico;
}());

Sincco.Venda = (function() {
    function Venda(adicionarItemServico) {
          this.adicionarItemServico = adicionarItemServico;
          this.valorTotalBox = $('.js-resulta-total-media');
          this.valorTotalItens = 0;
    }

    Venda.prototype.iniciar = function() {
          this.adicionarItemServico.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
          this.adicionarItemServico.on('tabela-itens-atualizada', onValoresAlterados.bind(this));
    }

    function onTabelaItensAtualizada(evento, valorTotalItens) {
          this.valorTotalItens = valorTotalItens == null ? 0 : valorTotalItens;
    }

    function onValoresAlterados() {
            var valorTotal = this.valorTotalItens.toFixed(2).toString().replace(".", ",");
            this.valorTotalBox.html(valorTotal);
     }

    return Venda;
}());

$(function() {
    var adicionarItemServico = new Sincco.AdicionarItemServico();
    adicionarItemServico.iniciar();
    var venda = new Sincco.Venda(adicionarItemServico);
    venda.iniciar();

});

 

 