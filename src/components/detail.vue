<template>
  <div>
       <navheader @leftClick='onBack' @rightClick='onRefresh'></navheader>
       <web ref="webview" :src="url" class="webview" ></web>
  </div>
</template>

<script>
var utils = require('../utils/utils.js');
const webview = weex.requireModule('webview')

export default {
  data() {
    return {
        url:'http:www.baidu.com',
    }
  },
  components:{
    navheader:require('../modules/navheader.vue')
  },
  created:function(){
    var self = this;
    self.onLoad();
  },
  methods:{
    onLoad:function(){
        var self = this;
        
        self.url = utils.getUrlParam('url');
    },
    onBack:function(){
      var self = this;
      
      webview.goBack(self.$refs.webview);
      utils.log('onBack  '+JSON.stringify(self.$refs.webview));
    },
    onRefresh:function(){
      var self = this;
      utils.log('onRefresh');
      webview.reload(self.$refs.webview)
    }
  }
}
</script>

<style scoped>
    .webview{
        width: 750;
        height: 1200;
    }
</style>
