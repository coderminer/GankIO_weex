<template>
  <div style="flex-direction: column;">
    <tabbar :tabItems='tabItems'></tabbar>
  </div>
</template>

<style scoped>

</style>

<script>
  export default {
    components:{
      tabbar: require('./modules/tabbar.vue')
    },
    data: function() {
      return{
        logoUrl: 'http://img1.vued.vanthink.cn/vued08aa73a9ab65dcbd360ec54659ada97c.png',
        target: 'World',
        baseURL:'',
        tabItems: [
          {
            index: 0,
            title: 'Android',
            titleColor: '#FF0000',
            icon:'',
            src: 'components/content.js?s=0',
            visibility: 'visible',
          },
          {
            index: 1,
            title: 'iOS',
            titleColor: '#FF0000',
            src: 'components/content.js?s=1',
            visibility: 'hidden',
          },
          {
            index: 2,
            title: '福利',
            titleColor: '#FF0000',
            src: 'components/beauty.js',
            visibility: 'hidden',
          },
          {
            index: 3,
            title: 'App',
            titleColor: '#FF0000',
            src: 'components/content.js?s=2',
            visibility: 'hidden',
          }
        ],
      }

    },

    created:function(){
      var self = this;
      self.baseURL = 'file://assets/dist/';
      for(var i = 0; i < self.tabItems.length; i++) {
          var tabItem = self.tabItems[i];
          tabItem.src = self.baseURL + tabItem.src;
      }
    },
    methods: {

    }
  }
</script>