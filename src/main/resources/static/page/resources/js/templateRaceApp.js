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
		var currentRunData = templateApp.adjustRunnerNames(data.currentRun, data.runnerNamesSwapped);
		templateApp.updateField('runnerOne', currentRunData.runnerOne);
		templateApp.updateField('runnerTwo', currentRunData.runnerTwo);
		templateApp.updateField('estimate', currentRunData.estimate);
		templateApp.updateField('game', currentRunData.game);
		templateApp.updateField('runnerName', currentRunData.runnerName);
		templateApp.updateField('category', currentRunData.category);
		templateApp.updateField('platform', currentRunData.platform);
	},
	updateField: function (divId, newValue) {
		if (document.getElementById(divId)) {
			var target = $('#' + divId);
			target.html('<span>' + newValue + '</span>');
			target.textfill();
		}
	},
	adjustRunnerNames: function (runData, isNamesSwapped) {
		var splitNames = runData.runnerName.split(',');
		if (splitNames.length < 2) {
			splitNames = [runData.runnerName, ''];
		}
		if (isNamesSwapped) {
			runData.runnerOne = splitNames[1].trim();
			runData.runnerTwo = splitNames[0].trim();
		} else {
			runData.runnerOne = splitNames[0].trim();
			runData.runnerTwo = splitNames[1].trim();
		}

		return runData;
	}
}

$(function () {
	templateApp.resizeField('runnerOne');
	templateApp.resizeField('runnerTwo');
	templateApp.resizeField('estimate');
	templateApp.resizeField('game');
	templateApp.resizeField('platform');
	templateApp.resizeField('category');

	templateApp.loadData();
	setInterval(function () {
		templateApp.loadData()
	}, 10000);
});