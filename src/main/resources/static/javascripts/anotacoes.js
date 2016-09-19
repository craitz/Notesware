var Notesware = Notesware || {};

Notesware.MaskMoney = (function() {
	
	function MaskMoney() {
		this.ordem = $('.js-ordem');
	}
	
	MaskMoney.prototype.enable = function() {
		this.ordem.maskMoney({ precision: 0, thousands: '.', allowZero: true, allowNegative: true });
	}
	
	return MaskMoney;
})();

$(function() {
	var maskMoney = new Notesware.MaskMoney();
	maskMoney.enable();
});