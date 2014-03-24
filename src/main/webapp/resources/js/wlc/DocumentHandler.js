window.wlc = window.wlc || {};

wlc.DocumentHandler = (function() {

  // Module
  function DocumentHandler() {
    this.originalTitle = '';
  }

  /**
   * TODO: Change to a broken heart as favicon:
   * favicon.change("/favicon.ico");
   * 
   * @see https://developer.mozilla.org/en-US/docs/Web/Guide/User_experience/Using_the_Page_Visibility_API
   * @returns {undefined}
   */
  DocumentHandler.prototype.handleVisibilityChange = function() {
    if (document.visibilityState === 'visible') {
      document.title = this.originalTitle;
    } else {
      document.title = 'We miss you ♥ | ' + this.originalTitle;
    }
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
      this.originalTitle = document.title;
      document.addEventListener(visibilityChange, function() {
        self.handleVisibilityChange();
      }, false);
    }
  };

  return DocumentHandler;

})();