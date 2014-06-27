window.wlc = window.wlc || {};
window.wlc.config = window.wlc.config || {};
window.wlc.config.Facebook = (function() {

  /**
   * Facebook Social Plugins:
   * https://developers.facebook.com/docs/plugins
   */
  function Facebook() {
    this.config = {
      app: {
        id: '245151515509317',
        url: 'https://www.facebook.com/welovecoding'
      }
    };
  }

  Facebook.prototype.injectSDKElement = function() {
    $('body').append('<div id="fb-root"></div>');
  };

  Facebook.prototype.injectSDKScript = function() {
    var sdkUrl = this.getSDKUrl();

    var injectSDK = (function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id))
        return;

      js = d.createElement(s);
      js.id = id;
      js.src = sdkUrl;

      fjs.parentNode.insertBefore(js, fjs);
    });

    try {
      injectSDK(document, 'script', 'facebook-jssdk');
    } catch (error) {
      console.warn('Error with Facebook SDK: ' + error);
    }
  };

  Facebook.prototype.getSDKLocale = function() {
    var sdkLocale = 'en_US';

    var navigatorLanguage = navigator.language;
    var locale = navigatorLanguage.split('-');
    if (locale[0] === 'de') {
      sdkLocale = 'de_DE';
    }

    return sdkLocale;
  };

  Facebook.prototype.initCallback = function(onPluginRendered) {
    window.fbAsyncInit = function() {
      FB.Event.subscribe('xfbml.render', function(response) {
        onPluginRendered();
      });
    };
  };

  Facebook.prototype.getSDKUrl = function() {
    var locale = this.getSDKLocale();
    return "//connect.facebook.net/"
            + locale
            + "/sdk.js#xfbml=1&appId="
            + this.config.app.id + "&version=v2.0";
  };

  Facebook.prototype.applyCustomStyle = function() {
    $('.fb-like').toggleClass('off');
  };

  Facebook.prototype.loadSDK = function() {
    var self = this;
    this.initCallback(function() {
      self.applyCustomStyle();
    });
    this.injectSDKElement();
    this.injectSDKScript();
  };

  return Facebook;

})();

(function() {
  var facebook = new wlc.config.Facebook();
  facebook.loadSDK();
})();
