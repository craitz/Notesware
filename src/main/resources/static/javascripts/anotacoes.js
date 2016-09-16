$(function() {
	var ordem = $('.js-ordem');
	ordem.maskMoney({ precision: 0, thousands: '.', allowZero: true, allowNegative: true });
});