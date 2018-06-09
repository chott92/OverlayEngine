var templateApp = {
	resizeField: function (target) {
		if (document.getElementById(target)) {
			$('#' + target).textfill();
		}
	},
	loadData: function () {
		$.get('/api/templateData', function (data) {
			console.log(data);
			templateApp.updateFields(data);
		});
	},
	updateFields: function (data) {
		var currentRunData = data.currentRun;
		templateApp.updateField('runnerName', currentRunData.runnerName);
		templateApp.updateField('estimate', currentRunData.estimate);
		templateApp.updateField('game', currentRunData.game);
		templateApp.updateField('runnerName', currentRunData.runnerName);
		templateApp.updateField('category', currentRunData.category);
	},
	updateField: function (divId, newValue) {
		if (document.getElementById(divId)) {
			var target = $('#' + divId);
			target.html('<span>' + newValue + '</span>');
			target.textfill();
		}
	}
}

$(function () {
	templateApp.resizeField('runnerName');
	templateApp.resizeField('estimate');
	templateApp.resizeField('game');
	templateApp.resizeField('platform');
	templateApp.resizeField('category');

	templateApp.loadData();
	setInterval(function () {
		templateApp.loadData()
	}, 10000);
});