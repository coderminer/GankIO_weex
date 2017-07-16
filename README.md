
#### 概述  

前一段使用.we的方式做了一个项目，一直没有升级到Vue，感觉升级的过程中的坑会有很多暂没有升级  
自己就先写一个Demo测试一下，看看使用Vue2.x开发，有哪些变化  
项目基于Weexpack(0.4.2)版本，使用Vue2.x开发，只有Android版本，有兴趣的童鞋可以添加对用的iOS版本  
最新的weexpack版本支持Vue开发，之前的版本(0.3.7)  
不支持Vue开发(或者说需要自己配置才能支持)  

#### 开工  

具体的开发环境搭建的过程请参考 [搭建开发环境](https://weex.apache.org/cn/guide/set-up-env.html),这里不再累述。使用weexpack创建工程，并添加Android的工程  

在开发之前，请先参考 [Weex 和 Vue 2.x 的语法差异](https://weex.apache.org/cn/references/migration/difference.html)

然后就是编写Vue的组件，需要注意的是引入组件的方式  

Weex版本引入方式  

```
require('../modules/listitem.we')

使用
<listitem></listitem>
```

Vue2.x版本引入方式  

```
components:{
    listitem:require('../modules/listitem.vue'),
},

使用
<listitem></listitem>
```

具体的可以参考工程中的源码 [组件引入](https://github.com/coderminer/GankIO_weex/blob/master/src/components/content.vue)

在开发过程中使用到的组件   

```
tabbar
web
list
image
```

效果截图   

![效果图](http://upload-images.jianshu.io/upload_images/2972448-0a8debfa7654f42e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![效果图](http://upload-images.jianshu.io/upload_images/2972448-f7c62e506fafa1ea.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![效果图](http://upload-images.jianshu.io/upload_images/2972448-1ca729c0aef0d972.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
