Sincco = Sincco ||{}

Sincco.BotaoSubmit = (function(){
      function BotaoSubmit(){
            this.btnSalvarServico = $('.js-submit-btn');
            this.formularioServico = $('.js-formulario-servico');
      }
      
      BotaoSubmit.prototype.iniciar  = function(){
            this.btnSalvarServico.on('click',onSubmit.bind(this));
      }

      function onSubmit(evento){
            evento.preventDefault();
            var botaoClick =  $(evento.target);
            var acao = botaoClick.data('acao');
            var acaoInput = $('<input>');
            acaoInput.attr('name', acao);
            this.formularioServico.append(acaoInput);
            this.formularioServico.submit();
      }
      return BotaoSubmit;
}());

$(function(){
      var botaoSubmit=new Sincco.BotaoSubmit();
      botaoSubmit.iniciar();

});