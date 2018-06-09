var templateApp = {
	resizeField: function (target) {
		if (document.getElementById(target)) {
			$('#' + target).textfill();
		}
	}
}

$(function () {
	templateApp.resizeField('runnerName');
	templateApp.resizeField('estimate');
	templateApp.resizeField('game');
	templateApp.resizeField('platform');
	templateApp.resizeField('category');
});