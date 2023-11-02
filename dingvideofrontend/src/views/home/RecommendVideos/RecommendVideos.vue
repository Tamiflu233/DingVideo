#index.vue页面
<template>
  <div class="recommend-video-layout">
    <van-swipe ref="videoSwipe" class="my-swipe" :show-indicators="false" vertical :touchable="true" @drag-start="onDragStart" @drag-end="onDragEnd" @change="onChange">
      <van-swipe-item>
        <video ref="videoPlayer1" class="video-js" style="margin: auto auto"></video>
      </van-swipe-item>
      <van-swipe-item>
        <video ref="videoPlayer2" class="video-js" style="margin: auto auto"></video>
      </van-swipe-item>
      <van-swipe-item>
        <video ref="videoPlayer3" class="video-js" style="margin: auto auto"></video>
      </van-swipe-item>
    </van-swipe>
  </div>
</template>
<script setup lang="ts">
import { onMounted, onUnmounted,ref } from 'vue'
import { VideoInfo } from "@/types/videoInfo"
import videojs from "video.js"


import Player from 'video.js/dist/types/player'
import "video.js/dist/video-js.css"

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
    playbackRates: [0.5, 1, 1.5, 2]
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
    playbackRates: [0.5, 1, 1.5, 2]
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
    playbackRates: [0.5, 1, 1.5, 2]
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
.recommend-video-layout{
  width: 100%;
  height: 100%;
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
.video-js {
    height: 100%;
    width: 100%;
    border-radius: 12px;
    font-size: 1rem;
  }

  :deep .video-js {
    .vjs-paused {
      .vjs-has-started {
        .vjs-big-play-button {
          display: block !important;
        }
      }
    }
    .vjs-control-bar {
      width: 98%;
      margin: 15px auto;
      border-radius: 12px;

      .vjs-menu-content,
      .vjs-volume-vertical {
        border-radius: 12px 12px 0 0;
      }
    }
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
