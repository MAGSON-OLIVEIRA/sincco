$(function() {
	
	var modal = $('#modalCadastroRapidoEstilo');
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	var form = modal.find('form');
	form.on('submit', function(event){event.preventDefault()});
	var url = form.attr('action');
	var inputNomeEstilo = $('#nomeEstilo');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	
	modal.on('hide.bs.modal', onModelClose);
	modal.on('shown.bs.modal', onModalShow);
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	function onModalShow(){
		inputNomeEstilo.focus();
	}
	function onModelClose(){
		inputNomeEstilo.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
		
	}
	function onBotaoSalvarClick(){
	// console.log('Nome', nomeEstilo);
		var nomeEstilo = inputNomeEstilo.val().trim();
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo }),
			error: onErroSalvandoEstilo,
			success: onEtiloSalvo
		});
	}
	
	
	function onErroSalvandoEstilo(obj){
		//console.log(arguments);
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>' );
		form.find('.form-group').addClass('has-error');
		
	}
	
	function onEtiloSalvo(estilo) {
		//console.log(arguments);
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		modal.modal('hide');
	}

});