var Notesware = Notesware || {};

Notesware.AdicionaGrupo = (function() {
	
	function AdicionaGrupo() {
		this.botaoAdicionarGrupo = $('.js-adiciona-grupo');
		this.painelAdicionaGrupo = $('.panel-default');
	}
	
	AdicionaGrupo.prototype.enable = function() {
		this.botaoAdicionarGrupo.on('click', onBotaoAdicionarGrupoClick.bind(this));
	}
	
	function onBotaoAdicionarGrupoClick() {
		this.painelAdicionaGrupo.toggleClass('hidden');
	}
	
	return AdicionaGrupo;
	
})();

$(function() {
	var adicionaGrupo = new Notesware.AdicionaGrupo();
	adicionaGrupo.enable();
});