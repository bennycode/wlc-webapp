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
        id: '345838572194372',
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

  Facebook.prototype.initCallback = function(callback, phase) {
    window.fbAsyncInit = function() {
      FB.Event.subscribe(phase, function(response) {
        callback();
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

  Facebook.prototype.customizeLikeBox = function() {
    $('.fb-like').toggleClass('off');
  };

  Facebook.prototype.loadSDK = function(callback, phase) {
    this.initCallback(callback, phase);
    this.injectSDKElement();
    this.injectSDKScript();
  };

  Facebook.prototype.loadLikeBox = function() {
    var callback = this.customizeLikeBox();
    this.loadSDK(callback, 'xfbml.render');
  };

  Facebook.prototype.getShareUrl = function() {
    return document.URL.substr(0, document.URL.indexOf('#'));
  };

  Facebook.prototype.injectLikeButton = function() {
    var url = this.getShareUrl();
    var button = $('<div class="fb-like" data-href="' + url + '" data-layout="button" data-action="recommend" data-show-faces="true" data-share="false"></div>');
    console.log(button.get(0));
    $('.fb-share-button-wrapper').append(button);
  };

  Facebook.prototype.loadLikeButton = function() {
    this.injectLikeButton();
    this.loadSDK(function() {
      $('.fb-share-button-wrapper').addClass('on');
    }, 'xfbml.render');
  };

  return Facebook;

})();
