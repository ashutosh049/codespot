$(document).ready(function () {

	//================================================================
	//			 		animsition
	//================================================================
	"use strict";
	$(".animsition").animsition({
		inClass: 'fade-in',
		outClass: 'fade-out',
		inDuration: 1500,
		outDuration: 800,
		loading: true,
		loadingParentElement: '#content',
		loadingClass: 'animsition-loading',
		loadingInner: '',
		timeout: false,
		timeoutCountdown: 5000,
		onLoadEvent: true,
		browser: ['animation-duration', '-webkit-animation-duration'],
		overlay: false,
		overlayClass: 'animsition-overlay-slide',
		overlayParentElement: 'body',
		transition: function (url) {
			window.location.href = url;
		}
	});
	//================================================================
	//					animsition
	//================================================================
});

function loadAllContatcs(url) {
	$('#AllAddable').html('');
	var spinner = '<div class="col-lg-12 text-center loading text-center"><img id="page-loading" src="' + contextPath + '/resources/img/loading/loading.gif" alt="loading"/></div>';
	var no_more_contacts = '<div class="col-lg-12 text-center" id="no_more_contacts"> <h3><span class="text-xl text-light"> No more users found..  <i class="fa fa-search-minus text-primary fa-3x"></i></span></h3> <h2 class="text-light">Try refershing the list</h2> </div>';

	$('#AllAddable').append(spinner);

	var card = $('#contact-card-pane');

	$.get(url).done(function (contactcard) {
		console.log(contactcard);

		if(contactcard.totalElm>0){
		
			$.each(contactcard.userList, function (key, value) {
				var data = "<div class='col-md-4 list-results list-results-underlined' id='contact_data'> "
					 + "<div class='card' style='padding-top: 0px; padding-bottom: 0px;' id='contact-card'> "
					 + "<div class='card-head'> "
					 + "<header> "
					 + "<div class='hbox-column' style='width: 60px;'> "
					 + "<img class='img-circle img-responsive pull-left' src='" + contextPath + "/resources/img/avtars/a (" + key + ").jpg' alt='' /> "
					 + "</div> "
					 + "<a class='text-sm text-faded' href='#'>" + value[3] + "</a> "
					 + "</header> "
					 + "<div class='tools'> "
					 + "<div class='btn-group'> "
					 + "<a class='btn btn-icon-toggle btn-close' href='javascript:handleContactClose();' > "
					 + "<i class='md md-close'> </i> "
					 + "</a> "
					 + "</div> "
					 + "</div> "
					 + "</div> "
					 + "<div class='card-body'> "
					 + "<div class='clearfix opacity-75'> "
					 + "<span class='glyphicon glyphicon-envelope text-sm'></span> "
					 + "&nbsp;" + value[2] + " <p>Etiam porta sem malesuada magna mollis euismod</p> "
					 + "</div> "
					 + "</div> "
					 + "<div class='card-actionbar'> "
					 + "<div class='card-actionbar-row'> "
					 + "<button type='button' class='btn btn-default-light ink-reaction' onClick='sendFR(event," + value[0] + ");'> "
					 + "<i class='fa fa-user-plus text-success'></i> "
					 + "connect "
					 + "</button> "
					 + "</div> "
					 + "</div> "
					 + "</div> "
					 + "</div>";

				$('#AllAddable').append(data);
				$('#page-loading').remove();
			});

			var firstUrl = "&apos;" + contextPath + "/searchusers/AllAddable?pageNo=1&apos;";
			var lastUrl = "&apos;" + contextPath + "/searchusers/AllAddable?pageNo=" + contactcard.totalPages + "&apos;";
			var prevUrl = "&apos;" + contextPath + "/searchusers/AllAddable?pageNo=" + (contactcard.currentIndex - 1) + "&apos;";
			var nextUrl = "&apos;" + contextPath + "/searchusers/AllAddable?pageNo=" + (contactcard.currentIndex + 1) + "&apos;";
			var t1 = "<div class='row' id='pagination_data'> " + "<div class='col-lg-12 text-center'> " + "<ul class='pagination'>";
			var t2 = "";
			var t4 = "";
			var t5 = "";
			if (contactcard.currentIndex == 1) {
				t2 = "<li class='disabled'>" + "<a href='#'>&lt;&lt;</a>" + "</li> "
					 + "<li class='disabled'>" + "<a href='#'>&lt;</a>" + "</li> ";
			} else {
				t2 = "<li>" + "<a href='javascript:loadAllContatcs(" + firstUrl + ");'>&lt;&lt;</a>" + "</li> "
					 + "<li>" + "<a href='javascript:loadAllContatcs(" + prevUrl + ");'>&lt;</a>" + "</li> ";
			}

			for (i = contactcard.beginIndex; i <= contactcard.endIndex; i++) {
				var pageUrl = "&apos;" + contextPath + "/searchusers/AllAddable?pageNo=" + i + "&apos;";
				if (contactcard.currentIndex == i) {
					t4 = t4 + "<li class='active'>" + "<a href='javascript:loadAllContatcs(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
				} else {
					t4 = t4 + "<li>" + "<a href='javascript:loadAllContatcs(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
				}

			}
			if (contactcard.currentIndex == contactcard.totalPages) {
				t5 = "<li class='disabled'>" + "<a href='#'>&gt;</a>" + "</li> " + "<li class='disabled'>" + "<a href='#'>&gt;&gt;</a>" + "</li> ";
			} else {
				t5 = "<li>" + "<a href='javascript:loadAllContatcs(" + nextUrl + ");'>&gt;</a>" + "</li> " + "<li>" + "<a href='javascript:loadAllContatcs(" + lastUrl + ");'>&gt;&gt;</a>" + "</li> ";
			}

			var t6 = "</ul> " + "</div> " + "</div>";

			$('#AllAddable').append(t1.concat(t2).concat(t4).concat(t5).concat(t6));
			
		}else{
			setTimeout(function () {
				$('#page-loading').remove();
				$('#AllAddable').append(no_more_contacts);
			}, 1000);
		}
		


	}).fail(function () {
		setTimeout(function () {
			$('#page-loading').remove();
			$('#AllAddable').append(no_more_contacts);
		}, 1000);
	});
}

//================================================================
//				All Addable Sent
//================================================================

function loadAllAllAddableSent(url) {
	$('#AllAddableSent').html('');
	var spinner = '<div class="col-lg-12 text-center loading text-center"><img id="page-loading" src="' + contextPath + '/resources/img/loading/loading.gif" alt="loading"/></div>';
	var no_more_contacts = '<div class="col-lg-12 text-center" id="no_more_contacts"> <h3><span class="text-xl text-light"> No more users found..  <i class="fa fa-search-minus text-primary fa-3x"></i></span></h3> <h2 class="text-light">Try refershing the list</h2> </div>';

	$('#AllAddableSent').append(spinner);

	var card = $('#contact-card-pane');

	$.get(url).done(function (contactcard) {
		$.each(contactcard.userList, function (key, value) {
			var data = "<div class='col-md-4 list-results list-results-underlined' id='contact_data'> "
				 + "<div class='card' style='padding-top: 0px; padding-bottom: 0px;' id='contact-card'> "
				 + "<div class='card-head'> "
				 + "<header> "
				 + "<div class='hbox-column' style='width: 60px;'> "
				 + "<img class='img-circle img-responsive pull-left' src='" + contextPath + "/resources/img/avtars/a (" + key + ").jpg' alt='' /> "
				 + "</div> "
				 + "<a class='text-sm text-faded' href='#'>" + value[3] + "</a> "
				 + "</header> "
				 + "<div class='tools'> "
				 + "<div class='btn-group'> "
				 + "<a class='btn btn-icon-toggle btn-close' href='javascript:handleContactClose();' > "
				 + "<i class='md md-close'> </i> "
				 + "</a> "
				 + "</div> "
				 + "</div> "
				 + "</div> "
				 + "<div class='card-body'> "
				 + "<div class='clearfix opacity-75'> "
				 + "<span class='glyphicon glyphicon-envelope text-sm'></span> "
				 + "&nbsp;" + value[2] + " <p>Etiam porta sem malesuada magna mollis euismod</p> "
				 + "</div> "
				 + "</div> "
				 + "<div class='card-actionbar'> "
				 + "<div class='card-actionbar-row'> "
				 + "<button type='button' class='btn btn-default-light ink-reaction text-warning' onClick='sendFR(event," + value[0] + ");'> "
				 + "<i class='fa fa-frown-o text-warning fa-lg'></i> "
				 + "Withdraw "
				 + "</button> "
				 + "</div> "
				 + "</div> "
				 + "</div> "
				 + "</div>";

			$('#AllAddableSent').append(data);
			$('#page-loading').remove();
		});

		var firstUrl = "&apos;" + contextPath + "/searchusers/AllAddableSent?pageNo=1&apos;";
		var lastUrl = "&apos;" + contextPath + "/searchusers/AllAddableSent?pageNo=" + contactcard.totalPages + "&apos;";
		var prevUrl = "&apos;" + contextPath + "/searchusers/AllAddableSent?pageNo=" + (contactcard.currentIndex - 1) + "&apos;";
		var nextUrl = "&apos;" + contextPath + "/searchusers/AllAddableSent?pageNo=" + (contactcard.currentIndex + 1) + "&apos;";
		var t1 = "<div class='row' id='pagination_data'> " + "<div class='col-lg-12 text-center'> " + "<ul class='pagination'>";
		var t2 = "";
		var t4 = "";
		var t5 = "";
		if (contactcard.currentIndex == 1) {
			t2 = "<li class='disabled'>" + "<a href='#'>&lt;&lt;</a>" + "</li> "
				 + "<li class='disabled'>" + "<a href='#'>&lt;</a>" + "</li> ";
		} else {
			t2 = "<li>" + "<a href='javascript:loadAllAllAddableSent(" + firstUrl + ");'>&lt;&lt;</a>" + "</li> "
				 + "<li>" + "<a href='javascript:loadAllAllAddableSent(" + prevUrl + ");'>&lt;</a>" + "</li> ";
		}

		for (i = contactcard.beginIndex; i <= contactcard.endIndex; i++) {
			var pageUrl = "&apos;" + contextPath + "/searchusers/AllAddableSent?pageNo=" + i + "&apos;";
			if (contactcard.currentIndex == i) {
				t4 = t4 + "<li class='active'>" + "<a href='javascript:loadAllAllAddableSent(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
			} else {
				t4 = t4 + "<li>" + "<a href='javascript:loadAllAllAddableSent(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
			}

		}
		if (contactcard.currentIndex == contactcard.totalPages) {
			t5 = "<li class='disabled'>" + "<a href='#'>&gt;</a>" + "</li> " + "<li class='disabled'>" + "<a href='#'>&gt;&gt;</a>" + "</li> ";
		} else {
			t5 = "<li>" + "<a href='javascript:loadAllAllAddableSent(" + nextUrl + ");'>&gt;</a>" + "</li> " + "<li>" + "<a href='javascript:loadAllAllAddableSent(" + lastUrl + ");'>&gt;&gt;</a>" + "</li> ";
		}

		var t6 = "</ul> " + "</div> " + "</div>";

		$('#AllAddableSent').append(t1.concat(t2).concat(t4).concat(t5).concat(t6));

	}).fail(function () {
		setTimeout(function () {
			$('#page-loading').remove();
			$('#AllAddableSent').append(no_more_contacts);
		}, 1000);
	});
}


//================================================================
//					All Added
//================================================================
function loadAllAdded(url) {
	$('#AllAdded').html('');
	var spinner = '<div class="col-lg-12 text-center loading text-center"><img id="page-loading" src="' + contextPath + '/resources/img/loading/loading.gif" alt="loading"/></div>';
	var no_more_contacts = '<div class="col-lg-12 text-center" id="no_more_contacts"> <h3><span class="text-xl text-light"> No more users found..  <i class="fa fa-search-minus text-primary fa-3x"></i></span></h3> <h2 class="text-light">Try refershing the list</h2> </div>';

	$('#AllAdded').append(spinner);

	var card = $('#contact-card-pane');

	$.get(url).done(function (contactcard) {
		$.each(contactcard.userList, function (key, value) {
			var data = "<div class='col-md-4 list-results list-results-underlined' id='contact_data'> "
				 + "<div class='card' style='padding-top: 0px; padding-bottom: 0px;' id='contact-card'> "
				 + "<div class='card-head'> "
				 + "<header> "
				 + "<div class='hbox-column' style='width: 60px;'> "
				 + "<img class='img-circle img-responsive pull-left' src='" + contextPath + "/resources/img/avtars/a (" + key + ").jpg' alt='' /> "
				 + "</div> "
				 + "<a class='text-sm text-faded' href='#'>" + value[3] + "</a> "
				 + "</header> "
				 + "<div class='tools'> "
				 + "<div class='btn-group'> "
				 + "<a class='btn btn-icon-toggle btn-close' href='javascript:handleContactClose();' > "
				 + "<i class='md md-close'> </i> "
				 + "</a> "
				 + "</div> "
				 + "</div> "
				 + "</div> "
				 + "<div class='card-body'> "
				 + "<div class='clearfix opacity-75'> "
				 + "<span class='glyphicon glyphicon-envelope text-sm'></span> "
				 + "&nbsp;" + value[2] + " <p>Etiam porta sem malesuada magna mollis euismod</p> "
				 + "</div> "
				 + "</div> "
				 + "<div class='card-actionbar'> "
				 + "<div class='card-actionbar-row'> "
				 + "<button type='button' class='btn btn-default-light ink-reaction text-warning' onClick='sendFR(event," + value[0] + ");'> "
				 + "<i class='fa fa-warning text-warning fa-lg'></i> "
				 + "Remove Contact "
				 + "</button> "
				 + "</div> "
				 + "</div> "
				 + "</div> "
				 + "</div>";

			$('#AllAdded').append(data);
			$('#page-loading').remove();
		});

		var firstUrl = "&apos;" + contextPath + "/searchusers/AllAdded?pageNo=1&apos;";
		var lastUrl = "&apos;" + contextPath + "/searchusers/AllAdded?pageNo=" + contactcard.totalPages + "&apos;";
		var prevUrl = "&apos;" + contextPath + "/searchusers/AllAdded?pageNo=" + (contactcard.currentIndex - 1) + "&apos;";
		var nextUrl = "&apos;" + contextPath + "/searchusers/AllAdded?pageNo=" + (contactcard.currentIndex + 1) + "&apos;";
		var t1 = "<div class='row' id='pagination_data'> " + "<div class='col-lg-12 text-center'> " + "<ul class='pagination'>";
		var t2 = "";
		var t4 = "";
		var t5 = "";
		if (contactcard.currentIndex == 1) {
			t2 = "<li class='disabled'>" + "<a href='#'>&lt;&lt;</a>" + "</li> "
				 + "<li class='disabled'>" + "<a href='#'>&lt;</a>" + "</li> ";
		} else {
			t2 = "<li>" + "<a href='javascript:loadAllAdded(" + firstUrl + ");'>&lt;&lt;</a>" + "</li> "
				 + "<li>" + "<a href='javascript:loadAllAdded(" + prevUrl + ");'>&lt;</a>" + "</li> ";
		}

		for (i = contactcard.beginIndex; i <= contactcard.endIndex; i++) {
			var pageUrl = "&apos;" + contextPath + "/searchusers/AllAdded?pageNo=" + i + "&apos;";
			if (contactcard.currentIndex == i) {
				t4 = t4 + "<li class='active'>" + "<a href='javascript:loadAllAdded(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
			} else {
				t4 = t4 + "<li>" + "<a href='javascript:loadAllAdded(" + pageUrl + ");'>" + i + "</a>" + "</li> ";
			}

		}
		if (contactcard.currentIndex == contactcard.totalPages) {
			t5 = "<li class='disabled'>" + "<a href='#'>&gt;</a>" + "</li> " + "<li class='disabled'>" + "<a href='#'>&gt;&gt;</a>" + "</li> ";
		} else {
			t5 = "<li>" + "<a href='javascript:loadAllAdded(" + nextUrl + ");'>&gt;</a>" + "</li> " + "<li>" + "<a href='javascript:loadAllAdded(" + lastUrl + ");'>&gt;&gt;</a>" + "</li> ";
		}

		var t6 = "</ul> " + "</div> " + "</div>";

		$('#AllAdded').append(t1.concat(t2).concat(t4).concat(t5).concat(t6));

	}).fail(function () {
		setTimeout(function () {
			$('#page-loading').remove();
			$('#AllAdded').append(no_more_contacts);
		}, 1000);
	});
}


//================================================================
//stomp + sock
//================================================================

var stompClient = null;
var socket = null;
$(document).ready(function () {
	socket = new SockJS(contextPath + '/frEP');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe('/user/queue/addFriend', function (userInContext) {
			if (userInContext != null)
				showResult(JSON.parse(userInContext.body));
		});
	}, function (error) {
		console.log("error connecting frEP...\n" + error);
	});
});

function sendFR(e, addUserId) {
	var card = $(e.currentTarget).closest('.card');
	materialadmin.AppCard.addCardLoader(card);
	setTimeout(function () {
		materialadmin.AppCard.removeCard(card);
	}, 1500);
	var tx = stompClient.begin();
	var destination = "/app/frEP";
	stompClient.send(destination, {}, JSON.stringify(addUserId));
	tx.commit();
	stompClient.debug = function (str) {
		console.log("debug :\n" + str + "\n");
	};
}

function showResult(userInContext) {
	var notificationFR = "<li> "
		 + "<a class='alert alert-callout alert-warning' href='javascript:void(0);'> "
		 + "<img class='pull-right img-circle dropdown-avatar' src='#' alt='' /> "
		 + "<strong>" + userInContext.userName + "</strong><br /> "
		 + "<small>Sent you friend request</small> "
		 + "</a> "
		 + "</li>";
	$('#userNotification').append(notificationFR);
	$('#notify').effect('shake', 10, 3);

}

function handleContactClose() {
	document.getElementById("contact-card").remove();
}
