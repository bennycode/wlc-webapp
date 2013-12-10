jQuery(document).ready(function($) {

  function initHtmlBody() {
    var bodyClass = (navigator.userAgent.match(/(Android|iPad|iPhone|iPod|Windows Phone)/g) ? 'mobile' : 'desktop');
    $('body').addClass(bodyClass);
  }

  function initSidebarWidgets() {
    $('.sidebar-widget').on('touchstart', function() {
      $(this).toggleClass('hover');
    });
  }

  initHtmlBody();
  initSidebarWidgets();

});