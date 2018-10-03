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
			templateApp.updateDonationAmount(data.donationFeed);
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
	},
	updateDonationAmount: function (donationAmount) {
		var counter = new CountUp("donationAmount", templateApp.currentDonationAmount, donationAmount, 2);
		counter.start(function () {
			originAmount = targetAmount;
		});
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