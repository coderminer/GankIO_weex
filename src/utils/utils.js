exports.getBaseURL = function() {
    var bundleUrl = weex.config.bundleUrl;
    var env = weex.config.env;
    var nativeBase;
    if (env.platform == 'android') {
        nativeBase = 'file://assets/dist/';

    } else if (env.platform == 'iOS') {
        nativeBase = bundleUrl.substring(0, bundleUrl.lastIndexOf('bundlejs') + 9);
    }
    return nativeBase;
};

exports.log = function(msg){
    nativeLog('========================  '+ msg);
};

//获取地址中的参数
exports.getUrlParam = function(key) {
    var url = weex.config.bundleUrl;
    var reg = new RegExp("(^|\\?|&)" + key + "=([^&]*)(\\s|&|$)", "i");
    if (reg.test(url)) return decodeURIComponent(RegExp.$2.replace(/\+/g, " "));
    return "";
};