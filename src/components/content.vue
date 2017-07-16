<template>
    <div class='wrapper'>
        <list style='flex:1'>
            <cell v-for="(item,i) in rows" :key="i" :index="i" @click="onItemClick(item.url)">
                <listitem :src="item.src" :desc="item.desc" :author="item.author" :date="item.date"></listitem>
            </cell>
            <loading class="refresh-view" :display="loading_display" @loading="onloading">
                <text class="refresh-arrow"
                    v-if="loading_refresh_display">{{loadText}}
                </text>
            </loading>
        </list>
    </div>
</template>
<script>
    var api = require('../api/api.js');
    var utils = require('../utils/utils.js');
    const event = weex.requireModule('event');
    export default {
        data() {
            return {
                baseURL:'',
                rows:[],
                params:{
                    page:1,
                },
                loadText:'加载更多',
                refresh_display: 'hide',
                loading_display: 'hide',
                loading_refresh_display:false,
                nomore:false,
                url:'',
                android:'http://gank.io/api/data/Android/10/',
                ios:'http://gank.io/api/data/iOS/10/',
                app:'http://gank.io/api/data/App/10/',
            }
        },
        components:{
            listitem:require('../modules/listitem.vue'),
        },
        created:function(){
            var self = this;
            self.baseURL = utils.getBaseURL();
            utils.log('base url: '+self.baseURL);
            self.onLoad();
        },

        methods:{
            onLoad:function(){
                var self = this;
                var s = utils.getUrlParam('s');
                self.params.page = 1;
                switch(s){
                    case '0':
                    self.url = self.android;
                    break;
                    case '1':
                    self.url = self.ios;
                    break;
                    case '2':
                    self.url = self.app;
                    break;
                }
                self.getList(self.params.page);
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
                    self.getList(++self.params.page);
                    setTimeout(() => {
                    self.loading_display = 'hide'
                    self.loading_refresh_display = false;
                    }, 1000);
                }
            },

            getList:function(page){
                var self = this;
                self.params.page = page;
                api.getData(self.url + self.params.page,function(data){
                    //utils.log('data: '+JSON.stringify(data));
                    if(!!data && data.length > 0){
                        data.forEach(function(element) {
                         var item = {};
                         if(!!element.images && element.images.length>0){
                             item.src = element.images[0];
                         } 
                         item.url = element.url;
                         item.desc = element.desc;
                         item.author = element.who;
                         item.date = new Date(element.publishedAt).toDateString();
                         self.rows.push(item);
                        });
                    }else{
                        self.nomore = true;
                    }
                });
            },
            onItemClick:function(url){
                var self = this;
                event.openURL(self.baseURL + 'components/detail.js?url='+url);
                
            }
        }
    }
</script>
<style scoped>
    .wrapper{
        background-color:#e3e3e9;
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
</style>
