window.wlc = window.wlc || {};

wlc.DocumentHandler = (function() {

  // Module
  function DocumentHandler(config) {
    this.id = config.id;
    this.favicons = config.favicons;
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

    if (document.getElementById(this.id)) {
      document.head.removeChild(document.getElementById(this.id));
    }
    document.head.appendChild(favicon);
  };

  DocumentHandler.prototype.init = function() {
    var self = this;
    var hidden, visibilityChange;
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

    if (typeof document.addEventListener !== "undefined" && typeof hidden !== "undefined") {
      document.addEventListener(visibilityChange, function() {
        self.handleVisibilityChange();
      }, false);
    }
  };

  return DocumentHandler;

})();