$(document).ready(function() {
	$('#costRange').on("change mousemove", function() {
		var range = $('#costRange').val();
		// alert(range);
		$('#costTooltip').text("Max cost: " + range);
	});
});
/*
 * // hide content div $(document).ready(function() {
 * $("a[id^='service']").click(function() { $(".content").hide(); //
 * alert($(this).attr("id")+"content"); $("#" + $(this).attr("id") +
 * "content").show(); }); });
 */

// show info about project
$(document).ready(function() {
	$("#aboutproject").click(function() {

		$(".frontimage").animate({
			height : 'toggle'
		});
	});
});

$(document).ready(function() {
	$("#rate_value").val($("#bank_kode").find(':selected').data('value-type'));
});

$(document).ready(function() {
	$("#bank_kode").change(function() {
		$("#rate_value").val($(this).find(':selected').data('value-type'));
	});
});

/*
// changes language
$(document).ready(function() {
	$("#langOption").change(function() {
		$(document).ajaxStop(function() {
			location.reload(true);
		});
		var langValue = $("#langOption").val();
		$.get("language", {
			lang : langValue
		});
	});
});*/

// refreshes captcha
$(document).ready(
		function() {
			$(".captchaImg").click(
					function() {
						$("#captchaId").attr("src",
								"captcha.jpg" + "?" + new Date().getTime());
					});
		});

// collects messages id to be deleted
$(document).ready(function() {
	$("#deleteForm").submit(function() {
		var allVals = [];
		$('.mytable :checked').each(function() {
			allVals.push($(this).val());
		});
		$("#delIds").val(allVals);
	});
});

/*
 * // shows replaying form $(document).ready(function() {
 * $("#rebtn").click(function() { $(".replayform").toggle(); }); });
 */

// calculates typed character number
$(document).ready(function() {
	$('#msgTextarea').keyup(function() {
		var val = $('#msgTextarea').val();
		var tab = val.match(/(\r\n|\n|\r)/g);
		var max = 200;
		var addition = 0;
		if (tab != null) {
			addition = tab.length;
		}
		var left = max - val.length - addition;
		if (left < 0) {
			$('#msgTextarea').val(val.substring(0, max + left));
			left = 0;
		}
		$('#counter').text('Characters left: ' + left);
	});
});

// calculates typed character number
$(document).ready(function() {
	$('#retext').keyup(function() {
		var val = $('#retext').val();
		var tab = val.match(/(\r\n|\n|\r)/g);
		var max = 500;
		var addition = 0;
		if (tab != null) {
			addition = tab.length;
		}
		var left = max - val.length - addition;
		if (left < 0) {
			$('#retext').val(val.substring(0, max + left));
			left = 0;
		}
		$('#counterRe').text('Characters left: ' + left);
	});
});

$(document).ready(
		function() {
			$("#slideshow > div:gt(0)").hide();

			setInterval(function() {
				$('#slideshow > div:first').fadeOut(1000).next().fadeIn(1000)
						.end().appendTo('#slideshow');
			}, 3000);
		});
$(document).ready(function() {
	$("#hotelRate").change(function() {
		var count = $("#hotelRate").val();
		var star = '&#x2605;';
		var stars = star.repeat(count);
		$("#starCount").html(stars);
	});
});

$(document).ready(function() {
	$("#nightNum").change(function() {
		var count = $("#nightNum").val();
		var star = '&#9788;';
		var stars = star.repeat(count);
		$("#nightCount").html(stars);
	});
});

$(document).ready(function() {
	$("#peopleNum").change(function() {
		var count = $("#peopleNum").val();
		var star = '&#9787;';
		var stars = star.repeat(count);
		$("#peopleCount").html(stars);
	});
});

$(document).ready(function() {
	$("img").error(function() {
		$(this).hide();
	});
});

function getRating(id, count) {
	var star = '&#x2605;';
	var stars = star.repeat(count);
	document.getElementById(id).innerHTML = stars;
};

$(document).ready(function() {
	$("#file").on('change', function() {
		if (typeof (FileReader) != "undefined") {
			var image_holder = $("#image-holder");
			image_holder.empty();
			var reader = new FileReader();
			reader.onload = function(e) {
				$("<img />", {
					"src" : e.target.result,
					"style":"width:160px; height:120px;"
				}).appendTo(image_holder);

			}
			image_holder.show();
			reader.readAsDataURL($(this)[0].files[0]);
		} else {
			alert("This browser does not support FileReader.");
		}
	});
});







/*
 * $(document) .ready( function() { $('#sdayfrom') .keyup( function() { var val =
 * $('#sdayfrom').val(); if (val
 * .match("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])")) {
 * alert(val); $("#tourFilter").submit(); } }); });
 * 
 * $(document).ready(function() {
 * 
 * $("form#tourFilter select").change(function() { $("#tourFilter").submit();
 * }); });
 */
/*
 * $(document).ready(function() { var n = new Date(); var y = n.getFullYear();
 * var m = n.getMonth() + 1; var d = n.getDate(); if (m < 10) { m = "0" + m; }
 * if (d < 10) { d = "0" + d; } $("#sdayfrom").val(y + "-" + m + "-" + d);
 * $("#sdayto").val(y + "-" + m + "-" + d); });
 */

