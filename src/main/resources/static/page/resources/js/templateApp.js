var templateApp = {
	currentDonationAmount: 0.0,
	incentiveFeed: {},
	resizeField: function (target) {
		if (document.getElementById(target)) {
			var target = $('#' + target);
			var options = {success: function () {
					let outerHeight = target.height();
					let child = target.children('span').first();
					let innerHeight = child.height();
					let padding = (outerHeight - innerHeight) / 2;
					let targetMargin = padding + 'px';
					target.css('padding-top', targetMargin);
					target.height(outerHeight - padding);
				}
			};
			target.textfill(options);
		}
	},
	initFields: function (data) {
		templateApp.toggleIncentiveFeed();
		templateApp.resizeField('incentives');
	},
	loadData: function (callback) {
		$.get('/api/templateData', function (data) {
			templateApp.incentiveFeed = JSON.parse(data.incentiveFeed);
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
		if (data.afterNextRun) {
			templateApp.updateField('afterNextRun', data.afterNextRun.game);
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
		var counter = new CountUp("donation", templateApp.currentDonationAmount, donationAmount, 2);
		counter.start(function () {
			templateApp.currentDonationAmount = donationAmount;
		});
	},
	toggleIncentiveFeed: function () {
		templateApp.displayIncentiveData(0);
		setTimeout(function () {
			templateApp.displayIncentiveData(1);
		}, 30000);
	},
	displayIncentiveData: function (index) {
		if (templateApp.incentiveFeed.length > index) {
			templateApp.updateField('incentives', templateApp.getIncentiveData(index));
		} else {
			templateApp.updateField('incentives', '');
		}
	},
	getIncentiveData: function (index) {
		var incentiveData = templateApp.incentiveFeed[index];
		if (incentiveData.targetAmount > 0) {
			return incentiveData.game + " - " + incentiveData.incentiveName
					+ ": " + incentiveData.values[0].value + "/"
					+ incentiveData.targetAmount + "€";
		} else {
			var incentiveDataString = incentiveData.game + " - " + incentiveData.incentiveName + ": ";
			for (var i = 0; i < incentiveData.values.length; i++) {
				var valueObject = incentiveData.values[i];
				var valueString = valueObject.name + ": " + valueObject.value + "€";
				if (i > 0) {
					incentiveDataString += ', ';
				}
				incentiveDataString += valueString;
			}
			return incentiveDataString;
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
	setInterval(function () {
		templateApp.toggleIncentiveFeed();
	}, 60000);
});