<template>
  <div class="wrapper">
      <image style='width:44;height:44;margin-left:30;' src='local:///back' @click='leftItemClick'></image>
      <image style='width:44;height:44;margin-right:30' src='local:///refresh' @click="rightItemClick"></image>
  </div>
</template>

<script>
export default {
  data(){
      return {

      }
  },

  created:function(){

  },
  methods:{
    leftItemClick:function(){
        var self = this;
        this.$emit('leftClick');
    },
    rightItemClick:function(){
        var self = this;
        this.$emit('rightClick');
    },
  }
}
</script>

<style scoped>
    .wrapper{
        height: 88;
        align-items: center;
        flex-direction: row;
        justify-content: space-between;
        background-color: #13b2ff;
    }
</style>
