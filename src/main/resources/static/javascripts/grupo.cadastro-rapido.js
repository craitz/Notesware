var Notesware = Notesware || {};

Notesware.GrupoCadastroRapido = (function() {
	
	function GrupoCadastroRapido() {
		this.modal = $('#modalCadastroRapidoGrupo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-grupo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeGrupo = $('#nomeGrupo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-grupo');
	}

	GrupoCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeGrupo.focus();
	}

	function onModalClose() {
		this.inputNomeGrupo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeGrupo = this.inputNomeGrupo.val().trim();
		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				nome : nomeGrupo
			}),
			error : onErroSalvandoGrupo.bind(this),
			success : onGrupoSalvo.bind(this)
		});
	}
	
	function onErroSalvandoGrupo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}

	function onGrupoSalvo(grupo) {
		var comboGrupo = $('#grupo');
		comboGrupo.append('<option value=' + grupo.codigo + '>' + grupo.nome
				+ '</option>');
		comboGrupo.val(grupo.codigo);
		this.modal.modal('hide');
	}
	
	return GrupoCadastroRapido;

})();


$(function() {
	var grupoCadastroRapido = new Notesware.GrupoCadastroRapido();
	grupoCadastroRapido.iniciar();
});
