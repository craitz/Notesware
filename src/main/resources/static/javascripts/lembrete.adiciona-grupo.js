$(function() {
	var botaoAdicionarGrupo = $('.js-adiciona-grupo');
	
	botaoAdicionarGrupo.on('click', onBotaoAdicionarGrupoClick);
	
	function onBotaoAdicionarGrupoClick() {
		var painelAdicionaGrupo = $('.panel-default');
		painelAdicionaGrupo.toggleClass('hidden');
	}
});