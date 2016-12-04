/* SLIDE-IN PANEL */

jQuery(document).ready(function($){
    "use strict";
    /* IOS Fix */
    Modernizr.addTest('isios', function(){
        return navigator.userAgent.match(/(iPad|iPhone|iPod)/g) ? true : false;
    });
    if (Modernizr.isios) {
        $('#main').css('height','100%');
    }
    /* IOS Fix End*/
	var isLateralNavAnimating = false;
	$('.bw-nav-trigger').on('click', function(event){
		event.preventDefault();
		if( !isLateralNavAnimating ) {
			if($(this).parents('.csstransitions').length > 0 ) isLateralNavAnimating = true;			
			$('body').toggleClass('navigation-is-open');
			$('body').find('.bw-navigation-wrapper').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
				isLateralNavAnimating = false;
			});
		}
	});
});

var adjustPanelHeight = function () {
    "use strict";
    if (jQuery(window).width() > 1170) {
        var rightblock = jQuery('#bw-nav').find(".right-block").height();
        jQuery('#bw-nav').find(".left-block").css("min-height", rightblock + 120);
    }
};

jQuery(document).keyup(function(e) {
    "use strict";
    if (e.keyCode == 27) {
        jQuery('body').removeClass('navigation-is-open');
    }
});

/* MAIN MENU */

jQuery('#bw-nav').find("#mainmenu ul > li > a").on('click', function () {
    "use strict";
    var checkElement = jQuery(this).next();
    if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
        checkElement.slideUp(300);
        jQuery(this).removeClass("has-sub2");
        jQuery(this).addClass("has-sub");
        checkElement.removeClass("animated fadeIn");
        checkElement.addClass("animated fadeOut");
    }
    if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
        jQuery('#bw-nav').find('#mainmenu ul ul:visible').slideUp(300);
        checkElement.slideDown(300);
        jQuery('#bw-nav').find('#mainmenu > ul > li:has(ul) > a').removeClass("has-sub2");
        jQuery('#bw-nav').find('#mainmenu > ul > li:has(ul) > a').addClass("has-sub");
        jQuery(this).addClass("has-sub2");
        checkElement.removeClass("animated fadeOut");
        checkElement.addClass("animated fadeIn");
    }
    if (checkElement.is('ul')) {
        return false;
    } else {
        return true;
    }
});

/* BACK TO TOP */

jQuery(".back-to-top").on('click', function(event){
    "use strict";
    event.preventDefault();
    jQuery('html, body, #main').animate({
        scrollTop: 0,
    }, 500);
});

/* ADJUST IMAGE BOX NEGATIVE MARGINS */

var isSafari = navigator.vendor.indexOf("Apple")==0 && /\sSafari\//.test(navigator.userAgent); // detect safari browser

var adjustImgMargin = function () {
    "use strict";
    if ( !isSafari ) {
    if (jQuery(window).width() > 1024) {
        jQuery('#site-container').find(".bw-image-box").each(function () {
            var imgheight = jQuery(this).find('.bw-image img').height();
            jQuery(this).css('margin-top', -imgheight/5 - 60);
        });
    }
    }
};

/* IMAGE EFFECT */

jQuery('#site-container').find(".bw-image-box").on({
    mouseenter: function () {
        "use strict";
        jQuery(this).find('.bw-image').addClass('animatedmedium pulse');
    }, 
    mouseleave: function () {
        "use strict";
        jQuery(this).find('.bw-image').removeClass('animatedmedium pulse');
    }
});

/* PAGE TITLE ANIMATION */

var pageTitle = function () {
    "use strict";
    if (jQuery(window).width() > 480) {
        jQuery('#page-title-inner-container h1').fadeIn();
        jQuery('#page-title-inner-container h1').addClass('animated slideInDown');
    }
};

/* CAROUSEL FIX */

jQuery('#main').find('a').on('dragstart', function (event) {
    "use strict";
    event.preventDefault();
});

/* ACCORDION */

jQuery(document).ready(function () {
    "use strict";
    jQuery('#site-container').find('.accordion-header').toggleClass('inactive-header');
    jQuery('#site-container').find('.accordion-header').click(function () {
        if (jQuery(this).is('.inactive-header')) {
            jQuery('#site-container').find('.active-header').toggleClass('active-header').toggleClass('inactive-header').next().slideToggle().toggleClass('open-content');
            jQuery(this).toggleClass('active-header').toggleClass('inactive-header');
            jQuery(this).next().slideToggle().toggleClass('open-content');
        }
        else {
            jQuery(this).toggleClass('active-header').toggleClass('inactive-header');
            jQuery(this).next().slideToggle().toggleClass('open-content');
        }
    });
    return false;
});

/* EVENTS */

jQuery(window).load(function () {
    "use strict";
    adjustImgMargin();
    jQuery('#main').find(".owl-prev:empty").parent().hide();
    pageTitle();
});

jQuery(document).ready(function () {
    "use strict";
    adjustPanelHeight();
    jQuery('#bw-nav').find('#mainmenu > ul > li:has(ul) > a').addClass("has-sub");
    /* SLIDE-IN PANEL BACKGROUND IMAGE */
    if (jQuery(window).width() > 1024) {
        jQuery('#bw-nav').find('.bw-bg').backstretch("images/photos/1800x1000.gif");
    }
    /* SLIDE-IN PANEL BACKGROUND IMAGE END */
});

jQuery(window).on('resize orientationchange', function () {
    "use strict";
    adjustImgMargin();
    adjustPanelHeight();
    pageTitle();
});