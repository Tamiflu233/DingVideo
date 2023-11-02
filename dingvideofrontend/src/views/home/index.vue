#index.vue页面
<template>
  <div class="home-layout">
    <el-container class="home-content">
      <el-aside class="cate-sidebar" width="100px">
        <SideMenu :items="menuItems"  />
      </el-aside>
      <el-container class="main-part">
        <el-header class="head-bar">
          <NavHeader />
        </el-header>
        <el-main class="video-pageview">
          <van-swipe ref="videoSwipe" class="my-swipe" :show-indicators="false" vertical :touchable="true" @drag-start="onDragStart" @drag-end="onDragEnd" @change="onChange">
            <van-swipe-item>
              <div style="border-radius: 0.8rem;background-color:#ffffff;">
                <el-row :gutter="50">
                  <!-- 视频 -->
                  <el-col :span="50">
                    <div class="banner">
                      <video ref="videoPlayer1" class="video-js vjs-big-play-button" style="margin: auto auto"></video>
                    </div>
                  </el-col>
<!--                  <el-col :span="50" style="box-shadow:inset 0 -3em 3em rgba(0, 0, 0, 0.05)">-->
                  <!-- 视频右侧 -->
                  <el-col :span="50">
                    <VideoCardRight :detail="detail" :review="review"/>
                  </el-col>
                </el-row>
              </div>
            </van-swipe-item>
            <van-swipe-item>
              <video ref="videoPlayer2" class="video-js" style="margin: auto auto"></video>
            </van-swipe-item>
            <van-swipe-item>
              <video ref="videoPlayer3" class="video-js" style="margin: auto auto"></video>
            </van-swipe-item>
          </van-swipe>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import { onMounted, onUnmounted,ref } from 'vue'
import { MenuItem } from "@/types/sidemenu"
import { VideoInfo } from "@/types/videoInfo"
import videojs from "video.js"


// import Player from 'video.js/dist/types/player'
import "video.js/dist/video-js.css"
// 侧边菜单和顶部导航栏
const SideMenu = defineAsyncComponent(() => import("@/components/common/SideMenu.vue"))
const NavHeader = defineAsyncComponent(() => import("@/components/common/NavHeader.vue"))
// 卡片中视频的右侧区域
import VideoCardRight from "@/components/common/VideoCardRight.vue"
import {controlDetail} from "@/store/controlDetail"
const Detail = controlDetail();
const detail = Detail.detail
// 评论内容
const content = Detail.content
// const getDetail = () => Detail.getDetail(route.params.id)
const getDetail = () => Detail.getDetail(1)
const afterDoComment = (comment) => Detail.afterDoComment(comment)
const review = Boolean
// 左侧菜单分类按钮列表,是对象数组，每个对象包含icon和title两个属性
const menuItems = ref<MenuItem[]>([
  {
    icon: "house",
    title: "首页",
  },
  {
    icon: "star",
    title: "推荐",
  },
  {
    icon: "trophy",
    title: "热门",
  },
  {
    icon: "football",
    title: "体育",
  },
  {
    icon: "reading",
    title: "知识",
  },
  {
    icon: "SwitchFilled",
    title: "游戏",
  },
  {
    icon: "MagicStick",
    title: "娱乐",
  },
  {
    icon: "food",
    title: "美食",
  },
  {
    icon: "VideoCameraFilled",
    title: "电影",
  },
  {
    icon: "ShoppingBag",
    title: "时尚",
  },
  {
    icon: "promotion",
    title: "动漫",
  },
]);
// 视频轮播器
const videoSwipe = ref();
// 视频列表
const videoQueue: { current: number; queue: VideoInfo[] } = reactive({
  current: 0,
  queue: [
    {
      "poster": "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/snapshots/e13c8454edf740f383876e05249023ea-00005.jpg",
      "src":"https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/0c66767d68cc45968eaae18050a753b8-9788c7569453b623fd6fa5d32d6214de-ld.mp4"
    },
    {
      "poster": "https://rl.shuishan.net.cn/59ca8d30b25e71edad487035d0b20102/snapshots/ad6b5d6478094805b28dcdb82ef0dd4f-00001.jpg",
      "src":"https://rl.shuishan.net.cn/59ca8d30b25e71edad487035d0b20102/264badc5eb534f0da5c73eb1232a2f60-8845e77acf1958539faeb69cff740d0f-ld.mp4"
    },
    {
      "poster": "https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/snapshots/e9c559d852fb4bab85ae9e6f2f39a5e8-00005.jpg",
      "src":"https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/736edc0145b94189b2f5b44666cee183-fd719751718abcfad6f25c5f32b6fd63-ld.mp4"
    },
    {
      "poster": "https://rl.shuishan.net.cn/bd9771706d8971ee965a6733a78e0102/snapshots/db805ca49bbd49b7a4889ef6e5860598-00005.jpg",
      "src":"https://rl.shuishan.net.cn/bd9771706d8971ee965a6733a78e0102/c3b8607eab484eb59787b00a6ff40609-87f8ea8c709ea926373cef3f2c2da815-ld.mp4"
    },
    {
      "poster": "https://rl.shuishan.net.cn/8c1488406d9371eebfe15017e1f90102/snapshots/60b85da06ccc4b788b12c96fd9a90afa-00005.jpg",
      "src":"https://rl.shuishan.net.cn/8c1488406d9371eebfe15017e1f90102/6960162bee8144d88816bfe816f0d859-1a89b9323b4bd723eefc7b534371b63f-ld.mp4"
    },
    {
      "poster": "https://rl.shuishan.net.cn/8b9836e06d9471eebf885017f0e90102/snapshots/aa6c21a95e284b738224d8a3e55bae12-00005.jpg",
      "src":"https://rl.shuishan.net.cn/8b9836e06d9471eebf885017f0e90102/6d988a40a5304dc89c3f35ff86e9e26e-8846bfb028fa46fb733eba4d15cff82b-ld.mp4"
    }
  ]
})
// 视频播放器
const videoPlayer1 = ref()
const videoPlayer2 = ref()
const videoPlayer3 = ref()
const myPlayer1 = ref()
const myPlayer2 = ref()
const myPlayer3 = ref()
let oldcover:string = "";
// 切换视频函数
const videoSet = (index:number,playerId:number) => {
  if(playerId === 1) {
    myPlayer1.value.poster(videoQueue.queue[index].poster)
    myPlayer1.value.src(videoQueue.queue[index].src)
  } else if(playerId === 2) {
    myPlayer2.value.poster(videoQueue.queue[index].poster)
    myPlayer2.value.src(videoQueue.queue[index].src)
  }
  else if(playerId === 3) {
    myPlayer3.value.poster(videoQueue.queue[index].poster)
    myPlayer3.value.src(videoQueue.queue[index].src)
  }
}
// 开始页数
let startIndex: number = 0;
function onChange(index: number) {
  console.log("参数是"+index);

  let currentVideoPlayer = myPlayer1
  if(index == 1) {
    currentVideoPlayer = myPlayer2
    myPlayer1.value.pause()
    myPlayer2.value.pause()
    myPlayer3.value.pause()
    console.log(myPlayer1.value.paused())
    console.log(myPlayer2.value.paused())
    console.log(myPlayer3.value.paused())
  }
  else if(index == 2) {
    currentVideoPlayer = myPlayer3
    myPlayer1.value.pause()
    myPlayer2.value.pause()
  }
  else {
    myPlayer2.value.pause()
    myPlayer3.value.pause()
  }

  // 将当前视频播放器回到起始位置
  if (currentVideoPlayer) {
    currentVideoPlayer.value.currentTime(0);
    currentVideoPlayer.value.play();
  }
}
function onDragStart(args:{index: number}) {
  console.log("DragStart"+args.index);
  startIndex = args.index;
  if(args.index === 2) {

  }
  if(args.index === 0) {

  }
  
  
  
}
function onDragEnd(args:{index: number}) {
  
 
  /* 
    视频翻页修改
  
  */
  console.log("DragEnd"+args.index);
  if (args.index === startIndex + 1 || (startIndex === 2 && args.index === 0)) {
    // 向下翻页
    if (videoQueue.current <= videoQueue.queue.length - 1) {
      videoQueue.current = (videoQueue.current + 1 + videoQueue.queue.length) % videoQueue.queue.length;

      if(args.index === 0) {
        myPlayer1.value.poster(videoQueue.queue[videoQueue.current % videoQueue.queue.length].poster)
        myPlayer2.value.poster(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].poster)
        myPlayer3.value.poster(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].poster)
        
        myPlayer1.value.src(videoQueue.queue[videoQueue.current % videoQueue.queue.length].src)
        myPlayer2.value.src(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].src)
        myPlayer3.value.src(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].src)
      }
    }
  } else if (args.index === startIndex - 1 || (startIndex === 0 && args.index === 2)) {
    
    // 向上翻页
    if (videoQueue.current >= 0) {
      videoQueue.current = (videoQueue.current - 1 + videoQueue.queue.length) % videoQueue.queue.length;
      
      if(args.index === 2) {
        myPlayer1.value.poster(videoQueue.queue[(videoQueue.current -2) % videoQueue.queue.length].poster)
        myPlayer2.value.poster(videoQueue.queue[(videoQueue.current-1) % videoQueue.queue.length].poster)
        myPlayer3.value.poster(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].poster)
        myPlayer1.value.src(videoQueue.queue[(videoQueue.current - 2) % videoQueue.queue.length].src)
        myPlayer2.value.src(videoQueue.queue[(videoQueue.current - 1) % videoQueue.queue.length].src)
        myPlayer3.value.src(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].src)
      }
    }
  }
  console.log("页号"+videoQueue.current)
    /* 
      临时修改视频封面
    
    */
  if(args.index === 0) {
    oldcover = myPlayer3.value.poster();

  } else if(args.index === 2) {
    oldcover = myPlayer1.value.poster();
    console.log("新封面"+videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].poster);
    
    
  }
  console.log("封面:"+oldcover);

  
}

onMounted(() => {
  myPlayer1.value = videojs(videoPlayer1.value, {
    // autoplay: true,
    controls: true,
    controlBar: {
      //音量条竖直
      volumePanel: {
          inline: false,
          CurrentTimeDisplay: true
        }
        // // 暂停按钮隐藏
    },
    playbackRates: [0.5, 0.75, 1, 1.5, 2]
  }, () => {
    // myPlayer.value.poster("https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/snapshots/358d95a124ff421382624687fe348c0c-00001.jpg")
    videoSet(0,1)
    console.log("启动1！");
    
  })
  myPlayer2.value = videojs(videoPlayer2.value, {
    // autoplay: true,
    controls: true,
    controlBar: {
      //音量条竖直
      volumePanel: {
          inline: false,
          CurrentTimeDisplay: true
        }
        // // 暂停按钮隐藏
    },
    playbackRates: [0.5, 0.75, 1, 1.5, 2]
  }, () => {
    // myPlayer.value.poster("https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/snapshots/358d95a124ff421382624687fe348c0c-00001.jpg")
    videoSet(1,2)
    console.log("启动2！");
  })
  myPlayer3.value = videojs(videoPlayer3.value, {
    // autoplay: true,
    controls: true,
    controlBar: {
      //音量条竖直
      volumePanel: {
          inline: false,
          CurrentTimeDisplay: true
        }
        // // 暂停按钮隐藏
    },
    playbackRates: [0.5, 0.75, 1, 1.5, 2]
  }, () => {
    // myPlayer.value.poster("https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/snapshots/358d95a124ff421382624687fe348c0c-00001.jpg")
    videoSet(2,3)
    console.log("启动3！");
  })
})

onUnmounted(() => {
  if (myPlayer1.value) {
    myPlayer1.value.dispose()
  }
  if (myPlayer2.value) {
    myPlayer2.value.dispose()
  }
  if (myPlayer3.value) {
    myPlayer3.value.dispose()
  }
})

</script>


<style lang="scss" scoped>
.home-layout{
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  position: absolute;
  height: 100%;
  .home-content{
    height: 100%;
  .cate-sidebar {
  // background-color: gray;
  }

  .head-bar {
    // background-color: blue;
  }
}
}
.my-swipe {
  height: 100%;
    width: 100%;
    border-radius: 12px;
    font-size: 1rem;
}
.my-swipe .van-swipe-item {
    // color: #fff;
    // width:800px;
    // height: 500px;
    // font-size: 20px;
    // line-height: 150px;
    // text-align: center;
    // background-color: #39a9ed;
  }
.vjs-paused .vjs-big-play-button,
.vjs-paused.vjs-has-started .vjs-big-play-button {
  display: block;
}
.video-js .vjs-big-play-button{
  font-size: 2.5em !important;
  line-height: 2.3em !important;
  height: 2.5em !important;
  width: 2.5em !important;
  -webkit-border-radius: 2.5em !important;
  -moz-border-radius: 2.5em !important;
  border-radius: 2.5em !important;
  background-color: #73859f;
  background-color: rgba(115, 133, 159, 0.5) !important;
  border-width: 0.15em !important;
  margin-top: -1.25em !important;
  margin-left: -1.75em !important;
}
.video-js .vjs-time-control {
  display: block !important;
}
.video-js .vjs-remaining-time {
  display: none !important;
}
.video-js {
    height: 100%;
    width: 80%;
    border-radius: 12px;
    font-size: 1rem;
  }

  :deep .video-js {
    //.vjs-paused {
    //  .vjs-has-started {
    //    .vjs-big-play-button {
    //      display: block !important;
    //    }
    //  }
    //}
    .vjs-control-bar {
      width: 98%;
      margin: 15px auto;
      border-radius: 12px;

      .vjs-menu-content,
      .vjs-volume-vertical {
        border-radius: 12px 12px 0 0;
      }
      .vjs-big-play-centered {

      }
    }
    //.vjs-big-play-button{
    //font-size: 2.5em !important;
    //line-height: 2.3em !important;
    //height: 2.5em !important;
    //width: 2.5em !important;
    //-webkit-border-radius: 2.5em !important;
    //-moz-border-radius: 2.5em !important;
    //border-radius: 2.5em !important;
    //background-color: #73859f;
    //background-color: rgba(115, 133, 159, 0.5) !important;
    //border-width: 0.15em !important;
    //margin-top: -1.25em !important;
    //margin-left: -1.75em !important;
    //}
  }

  :deep .video-js {
    .vjs-poster {
      pointer-events: none;
    }

    .vjs-big-play-button {
      font-size: 2.5em;
      line-height: 2.3em;
      height: 2.5em;
      width: 2.5em;
      -webkit-border-radius: 2.5em;
      -moz-border-radius: 2.5em;
      border-radius: 2.5em;
      background-color: #73859f;
      background-color: rgba(115,133,159,.5);
      border-width: 0.15em;
      margin-top: -1.25em;
      margin-left: -1.75em;
      .vjs-icon-placeholder {
        font-size: 1.63em;
      }
    }
  }
</style>
