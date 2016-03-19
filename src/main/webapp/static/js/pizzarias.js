var data = function() {
	//Considering you are on a GMT-3 timezone and the input contains '2000-01-17 10:00'
	var localDate = picker.getLocalDate(); // localDate === 2000-01-17 07:00
	var utcDate = picker.getDate(); // utcDate === 2000-01-17 10:00
	//
	picker.setLocalDate(new Date(1998, 10, 11, 4, 30)); // input === 1998-10-11 07:30
	picker.setDate(new Date(Date.UTC(1998, 10, 11, 4, 30))); // input === 1998-10-11 04:30

	var picker = $('#datetimepicker').data('datetimepicker');
	picker.setLocalDate(null);
	// or
	picker.setDate(null);
	// or
	input.val('');
	input.change();

	el.on('changeDate', function(e) {
	  console.log(e.date.toString());
	  console.log(e.localDate.toString());
	});

	$.fn.datetimepicker.defaults = {
	  maskInput: true,           // disables the text input mask
	  pickDate: true,            // disables the date picker
	  pickTime: true,            // disables de time picker
	  pick12HourFormat: false,   // enables the 12-hour format time picker
	  pickSeconds: true,         // disables seconds in the time picker
	  startDate: -Infinity,      // set a minimum date
	  endDate: Infinity          // set a maximum date
	};
};

$(document).ready(function() {
	$('#datetimepicker').on('click', data);
});


