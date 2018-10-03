var templateApp = {
	donationURL: "",
	inventiveFeedURL: "",
	currentDonationAmount: 0.0,
	resizeField: function (target) {
		if (document.getElementById(target)) {
			var target = $('#' + target);
			var options = {success: function () {
					let outerHeight = target.height();
					let child = target.children('span').first();
					let innerHeight = child.height();
					let padding = (outerHeight - innerHeight) / 2;
					let targetMargin = padding + 'px';
					console.log(child);
					target.css('padding-top', targetMargin);
					target.height(outerHeight - padding);
				}
			};
			target.textfill(options);
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
			templateApp.updateDonationAmount(data.donationFeed)
		});
	},
	updateFields: function (data) {
		var currentRunData = templateApp.adjustRunnerNames(data.currentRun, data.runnerNamesSwapped);
		templateApp.updateField('runnerOne', currentRunData.runnerOne);
		templateApp.updateField('runnerTwo', currentRunData.runnerTwo);
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
	},
	updateDonationAmount: function (donationAmount) {
		var counter = new CountUp("donation", templateApp.currentDonationAmount, donationAmount, 2);
		counter.start(function () {
			templateApp.currentDonationAmount = donationAmount;
		});
	}
}

$(function () {
	templateApp.resizeField('runnerOne');
	templateApp.resizeField('runnerTwo');
	templateApp.resizeField('estimate');
	templateApp.resizeField('game');
	templateApp.resizeField('platform');
	templateApp.resizeField('category');

	templateApp.loadData(templateApp.initFields);
	setInterval(function () {
		templateApp.loadData()
	}, 10000);
});