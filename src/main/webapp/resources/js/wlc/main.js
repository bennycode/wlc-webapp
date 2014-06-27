$(document).ready(function() {
  var faviconDirectory = window.location.pathname + 'resources/images/favicon/';
  var activeFavicon = faviconDirectory + 'heart-16.ico?ver=1';
  var inactiveFavicon = faviconDirectory + 'heart-broken.ico?ver=1';
  
  var faviconConfig = {
    id: 'favicon',
    favicons: {
      active: activeFavicon,
      inactive: inactiveFavicon
    }
  };

  var documentHandler = new wlc.DocumentHandler(faviconConfig);
  documentHandler.init();
});