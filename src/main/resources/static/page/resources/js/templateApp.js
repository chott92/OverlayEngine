var templateApp = {
	donationURL: "",
	inventiveFeedURL: "",
	resizeField: function (target) {
		if (document.getElementById(target)) {
			$('#' + target).textfill();
		}
	},
	initFields: function (data) {
		console.log('fields are initiated')
		templateApp.donationURL = data.donationUrl;
		templateApp.inventiveFeedURL = data.incentiveFeedUrl;

		templateApp.resizeField('incentives');
	},
	loadData: function (callback) {
		$.get('/api/templateData', function (data) {
			console.log(data);
			if (callback) {
				callback(data);
			}
			templateApp.updateFields(data);
		});
	},
	updateFields: function (data) {
		var currentRunData = data.currentRun;
		templateApp.updateField('runnerName', currentRunData.runnerName);
		templateApp.updateField('estimate', currentRunData.estimate);
		templateApp.updateField('game', currentRunData.game);
		templateApp.updateField('category', currentRunData.category);
		templateApp.updateField('platform', currentRunData.platform);

		if (data.nextRun) {
			templateApp.updateField('nextRun', data.nextRun.game);
		}
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

	templateApp.loadData(templateApp.initFields);
	setInterval(function () {
		templateApp.loadData()
	}, 10000);
});