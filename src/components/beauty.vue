<template>
  <div>
      <list style='flex:1'>
          <cell>
              <div class="wrapper">
                <div style='margin-top:10' v-for="(item ,i) in rows" :key="i" :index="i" @click='onPreview(item.src)'>
                    <image class="img-wrapper" resize='cover' :src="item.src"></image>
                </div>
              </div>
          </cell>
          <loading class="refresh-view" :display="loading_display" @loading="onloading">
            <text class="refresh-arrow"
                v-if="loading_refresh_display">{{loadText}}
            </text>
          </loading>
      </list>
        <div class='dialog' v-if='showDialog' >
            <div class='dialog-backdrop' @click='closeDialog'></div>
            <div class="dialog-content" v-if="show">
                <image :src='preview' class='preview' resize='contain'></image>
            </div>
        </div>
  </div>
</template>
<script>
var api = require('../api/api.js');
var utils = require('../utils/utils.js');
export default {
  data(){
      return {
        rows:[],
        params:{
            page:1,
        },
        loadText:'加载更多',
        loading_display: 'hide',
        loading_refresh_display:false,
        nomore:false,
        showDialog:false,
        show:false,
        preview:'',
      }
  },



  created:function(){
    var self = this;
    self.onLoad();
  },
  methods:{
    onLoad:function(){
        var self = this;
        self.getData(self.params.page);
    },
    onappear:function(event){
        var self = this;
        utils.log('beauty appear');
        self.params.page = 1;
        self.rows = [];
        self.getData(self.params.page);
    },
    onloading:function(){
        var self = this;
        self.loading_display = 'show';
        self.loading_refresh_display = true;
        if(self.nomore){
            self.loadText = "亲，无更多数据!!";
            setTimeout(() => {
            self.loading_display = 'hide'
            self.loading_refresh_display = false;
            }, 1000);
            return;
        }else{
            self.getData(++self.params.page);
            setTimeout(() => {
            self.loading_display = 'hide'
            self.loading_refresh_display = false;
            }, 1000);
        }
    },

    getData:function(page){
        var self = this;
        self.params.page = page;
        api.beauty(self.params.page,function(data){
            if(!!data && data.length >0){
                data.forEach(function(item){
                    var d = {};
                    d.src = item.url;
                    self.rows.push(d);
                });
            }else{
                self.nomore = true;
            }
        });
    },
    onPreview:function(url){
        var self = this;
        utils.log('onPreview: '+url);
        self.show = self.showDialog = true;
        self.preview = url;
    },
    closeDialog:function(){
        var self = this;
        self.show = self.showDialog = false;
    }
  }
}
</script>
<style>
    .wrapper{
        flex-direction: row;
        flex-wrap:wrap;
        align-items:center;
    }
    .img-wrapper{
        width: 345;
        height: 345;
        border-radius: 4;
        margin-left:20;
    }
    .refresh-view {
        height: 100;
        width: 750;
        justify-content: center;
        align-items: center;
    }

    .refresh-arrow {
        font-size: 30;
        color: #45b5f0;
    }
  .dialog-backdrop {
    opacity: .8;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: #000000;
  }
  .dialog {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    justify-content: center;
    align-items: center;
  }

  .preview{
      width:720;
      height: 1100;
  }
</style>
