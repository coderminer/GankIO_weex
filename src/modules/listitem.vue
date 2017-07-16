<template>
  <div class="wrapper">
    <image class='img-cover' resize='cover' :src='src' v-if='src'></image>
    <div class='des'>
        <text style='font-size:32;color:#fff;'>{{desc}}</text>
        <div class="bottom">
            <text style='font-size:24'>{{author}}</text>
            <text style='font-size:24;padding-left:20'>{{date}}</text>
        </div>
    </div>

  </div>
</template>
<style scoped>
    .wrapper{
        margin-left:40;
        margin-right:40;
        margin-top:20;
        background-color: #7c83db;
        border-radius: 4;
    }
    .img-cover{
        width: 670;
        height: 376;
        border-top-left-radius: 4;
        border-top-right-radius: 4;
    }
    .des{
        padding-left:20;
        padding-right:20;
        padding-top:20;
        padding-bottom:20;
        
    }
    .bottom{
        flex-direction:row;
        padding-left:20;
        padding-top: 20;
    }
</style>
<script>
export default {
    props:{
        src:{default:''},
        desc:{default:''},
        author:{default:''},
        date:{default:''},
    }

}
</script>
