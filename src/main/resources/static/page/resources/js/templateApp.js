var templateApp = {
	resizeField: function (target) {
		if (document.getElementById(target)) {
			$('#' + target).textfill();
		}
	},
	loadData: function () {
		$.get('/api/templateData/current', function (data) {

		});
	}
}

$(function () {
	templateApp.resizeField('runnerName');
	templateApp.resizeField('estimate');
	templateApp.resizeField('game');
	templateApp.resizeField('platform');
	templateApp.resizeField('category');
});