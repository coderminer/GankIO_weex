var stream = weex.requireModule('stream');


var url = {
    android:'http://gank.io/api/data/Android/10/',
    ios:'http://gank.io/api/data/iOS/10/',
    beauty:'http://gank.io/api/data/福利/10/',
    app:'http://gank.io/api/data/App/10/',
    random:'http://gank.io/api/random/data/福利/1',
};

function get(url,callback){
    nativeLog('url: '+url);
    stream.fetch({
        method:'GET',
        url:url,
        type: 'json',
    },function(ret){
        if(ret.status == 200){
            var data = ret.data;
            if(!data.error){
                callback(data.results);
            }
        }

    });
};


exports.android = function(params,callback){
    get(url.android+params,callback);
};

exports.ios = function(params,callback){
    get(url.ios + params, callback);
};

exports.beauty = function(params,callback){
    get(url.beauty + params, callback);
};

exports.app = function(params,callback){
    get(url.app + params,callback);
};

exports.random = function(callback){
    get(url.random, callback);
};

exports.getData = function(url,callback){
    get(url,callback);
}