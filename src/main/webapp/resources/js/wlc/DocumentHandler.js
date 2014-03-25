window.wlc = window.wlc || {};

wlc.DocumentHandler = (function($) {

  // Module
  function DocumentHandler(config) {
    $.extend(this, config);
  }

  /**
   * @see https://developer.mozilla.org/en-US/docs/Web/Guide/User_experience/Using_the_Page_Visibility_API
   * @returns {undefined}
   */
  DocumentHandler.prototype.handleVisibilityChange = function() {
    var favicon = document.createElement('link');
    favicon.id = this.id;
    favicon.rel = 'shortcut icon';

    if (document.visibilityState === 'visible') {
      favicon.href = this.favicons.active;
    } else {
      favicon.href = this.favicons.inactive;
    }

    $(document.getElementById(this.id)).remove();
    document.head.appendChild(favicon);
  };

  DocumentHandler.prototype.init = function() {
    var self = this;
    var hidden;
    var visibilityChange;

    if (typeof document.hidden !== "undefined") {
      hidden = "hidden";
      visibilityChange = "visibilitychange";
    } else if (typeof document.mozHidden !== "undefined") {
      hidden = "mozHidden";
      visibilityChange = "mozvisibilitychange";
    } else if (typeof document.msHidden !== "undefined") {
      hidden = "msHidden";
      visibilityChange = "msvisibilitychange";
    } else if (typeof document.webkitHidden !== "undefined") {
      hidden = "webkitHidden";
      visibilityChange = "webkitvisibilitychange";
    }

    if (typeof hidden !== "undefined") {
      $(document).bind(visibilityChange, function() {
        self.handleVisibilityChange();
      });
    }
  };

  return DocumentHandler;

})(jQuery);