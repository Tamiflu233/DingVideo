<template>
    <van-swipe ref="videoSwipe" class="my-swipe" :show-indicators="false" vertical :touchable="true" @drag-start="onDragStart" @drag-end="onDragEnd" @change="onChange">
      <van-swipe-item>
        <!-- <div class="video-wrapper"> -->
          <video ref="videoPlayer1" @play="video1Play" @pause="video1Pause" class="video-js" style="margin: auto auto" ></video>
          <VideoToolBar :videoInfo="video1Info" @judgeLogin="notLoginIn" @prev="prev" @next="next" @toggle-comment="onShowComment" class="tool-bar"></VideoToolBar>
          <div class="show-comment-btn" v-if="!isShowComment" @click="onShowComment">
            <el-icon class="show-comment-icon" >
              <ArrowLeftBold />
            </el-icon>
          </div>
          <div class="comment-drawer" :class="{ 'is-visible': isShowComment }">
            <div class="close-btn" @click.native="onCloseComment">
              <el-icon>
                <Close></Close>
              </el-icon>
            </div>
            <VideoCardRight
            :videoInfo="video1Info"
            ></VideoCardRight>
          </div>
          <VideoDescription class="video-description" :videoInfo="video1Info"></VideoDescription>

        <!-- </div> -->
      </van-swipe-item>
      <van-swipe-item>
        <video ref="videoPlayer2" @play="video2Play" @pause="video2Pause" class="video-js" style="margin: auto auto"></video>\
        <VideoToolBar :videoInfo="video2Info" @judgeLogin="notLoginIn" @prev="prev" @next="next" @toggle-comment="onShowComment" class="tool-bar"></VideoToolBar>
        <div class="show-comment-btn" v-if="!isShowComment" @click="onShowComment">
          <el-icon class="show-comment-icon">
            <ArrowLeftBold />
          </el-icon>
        </div>
        <div class="comment-drawer" :class="{ 'is-visible': isShowComment }">
          <div class="close-btn" @click="onCloseComment">
            <el-icon>
              <Close></Close>
            </el-icon>
          </div>
          <VideoCardRight
          :videoInfo="video2Info"
          ></VideoCardRight>
        </div>
        <VideoDescription class="video-description" :videoInfo="video2Info"></VideoDescription>

      </van-swipe-item>
      <van-swipe-item>
        <video ref="videoPlayer3" @play="video3Play" @pause="video3Pause" class="video-js" style="margin: auto auto"></video>
        <VideoToolBar :videoInfo="video3Info" @judgeLogin="notLoginIn" @prev="prev" @next="next" @toggle-comment="onShowComment" class="tool-bar"></VideoToolBar>
        <div class="show-comment-btn" v-if="!isShowComment" @click="onShowComment">
            <el-icon class="show-comment-icon">
              <ArrowLeftBold />
            </el-icon>
        </div>
        <div class="comment-drawer" :class="{ 'is-visible': isShowComment }">
          <div class="close-btn" @click="onCloseComment">
            <el-icon>
              <Close></Close>
            </el-icon>
          </div>
          <VideoCardRight
          :videoInfo="video3Info"
          ></VideoCardRight>
        </div>
        <VideoDescription class="video-description" :videoInfo="video3Info"></VideoDescription>

      </van-swipe-item>
    </van-swipe>
</template>

<script setup lang="ts">
import { watchEffect,watch, onMounted, onUnmounted,ref } from 'vue'
import { VideoDetail } from "@/types/videoInfo"
import { useRouter } from 'vue-router';
import videojs from "video.js"
import Player from 'video.js/dist/types/player'
import "video.js/dist/video-js.css"
const VideoToolBar = defineAsyncComponent(() => import("@/components/common/VideoToolbar.vue"))
const VideoCardRight = defineAsyncComponent(() => import("@/components/common/VideoCardRight.vue"))
const VideoDescription = defineAsyncComponent(() => import("@/components/common/VideoDescription.vue"))

// 路由跳转
const router = useRouter();
// 是否显示评论框
const isShowComment = ref(false)
function onShowComment() {
  isShowComment.value = true;
}
function onCloseComment() {

  
  isShowComment.value = false;
}
// 接受传入的视频列表和当前页号
const props = defineProps({
  videos: {
    type: Array as PropType<VideoDetail[]>,
    required: true
  },
  current : {
    type: Number,
    default: 0
  }
})
/* 三个播放器的暂停状态 */
const video1PauseShow = ref(true)
const video2PauseShow = ref(true)
const video3PauseShow = ref(true)
/* 三个播放器的视频信息，用于显示在工具栏、评论区等 */
const video1Info = ref()
const video2Info = ref()
const video3Info = ref()
/* 更新当前播放器视频信息 */
function setPlayerVideoInfo(playerId:number,videoIndex:number) {
  if(playerId == 1) {
    video1Info.value = videoQueue.queue[videoIndex]
  } else if(playerId == 2) {
    video2Info.value = videoQueue.queue[videoIndex]
  } else if(playerId == 3) {
    video3Info.value = videoQueue.queue[videoIndex]
  }
  
}
// 视频轮播器
const videoSwipe = ref();
// 视频列表
const videoQueue: { current: number; queue: VideoDetail[] } = reactive({
  current: props.current,
  queue: props.videos
})
function refreshVideos() {
  /* 设置播放器封面和播放地址 */
  myPlayer1.value.poster(videoQueue.queue[videoQueue.current % videoQueue.queue.length].coverUrl)
  myPlayer2.value.poster(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].coverUrl)
  myPlayer3.value.poster(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].coverUrl)
  
  myPlayer1.value.src(videoQueue.queue[videoQueue.current % videoQueue.queue.length].videoUrl)
  myPlayer2.value.src(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].videoUrl)
  myPlayer3.value.src(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].videoUrl)
  /* 设置视频信息 */
  setPlayerVideoInfo(1,videoQueue.current % videoQueue.queue.length)
  setPlayerVideoInfo(2,(videoQueue.current + 1) % videoQueue.queue.length)
  setPlayerVideoInfo(3,(videoQueue.current + 2) % videoQueue.queue.length)
  videoSwipe.value.swipeTo(0,{"immediate": true})
}
// 如果页面不销毁，需要监听props变化,即时刷新视频

watch(
  () => props.videos,
  async (newVideos) => {
    videoQueue.queue = newVideos;
    refreshVideos()
    
  },
  { immediate: true }
);
watch(
  () => props.current,
  async (newCurrent) => {
    videoQueue.current = newCurrent;
    refreshVideos()
    
  },
  { immediate: true }
);
// 视频播放器
const videoPlayer1 = ref()
const videoPlayer2 = ref()
const videoPlayer3 = ref()
const myPlayer1 = ref()
const myPlayer2 = ref()
const myPlayer3 = ref()
let oldcover:string = "";
/* 
  三个播放器的播放和暂停函数

*/
function video1Play(){

  if(video1PauseShow.value === true) {
    myPlayer1.value.play();
    setTimeout(() => {
      video1PauseShow.value = false;
    }, 100);
  }

}
function video1Pause(){

  if(video1PauseShow.value === false) {
    myPlayer1.value.pause();
    setTimeout(() => {
      video1PauseShow.value = true;
    }, 100);
  }

}
function video2Play(){

  if(video2PauseShow.value === true) {
    myPlayer2.value.play();
    setTimeout(() => {
      video2PauseShow.value = false;
    }, 100);
  }

}
function video2Pause(){

  if(video2PauseShow.value === false) {
    myPlayer2.value.pause();
    setTimeout(() => {
      video2PauseShow.value = true;
    }, 100);
  }

}
function video3Play(){

  if(video3PauseShow.value === true) {
    myPlayer3.value.play();
    setTimeout(() => {
      video3PauseShow.value = false;
    }, 100);
  }

}
function video3Pause(){

  if(video3PauseShow.value === false) {
    myPlayer3.value.pause();
    setTimeout(() => {
      video3PauseShow.value = true;
    }, 100);
  }

}

// 切换视频函数
const videoSet = (index:number,playerId:number) => {
  if(playerId === 1) {
    myPlayer1.value.poster(videoQueue.queue[index].coverUrl)
    myPlayer1.value.src(videoQueue.queue[index].videoUrl)
    setPlayerVideoInfo(1,index)
  } else if(playerId === 2) {
    myPlayer2.value.poster(videoQueue.queue[index].coverUrl)
    myPlayer2.value.src(videoQueue.queue[index].videoUrl)
    setPlayerVideoInfo(2,index)
  }
  else if(playerId === 3) {
    myPlayer3.value.poster(videoQueue.queue[index].coverUrl)
    myPlayer3.value.src(videoQueue.queue[index].videoUrl)
    setPlayerVideoInfo(3,index)
  }
  console.log(`视频1信息${video1Info.value.title}`);
  console.log(`视频2信息${video2Info.value.title}`);
  console.log(`视频3信息${video3Info.value.title}`);
  
}
// 开始页数
let startIndex: number = 0;
// 下一个页数
let nextIndex: number = 0;
function onChange(index: number) {
  isShowComment.value = false;

  if(index === 0) {
    video1Play()
    video2Pause()
    video3Pause()
  } else if (index === 1) {
    video1Pause()
    video2Play()
    video3Pause()
  } else if (index === 2) {
    video1Pause()
    video2Pause()
    video3Play()
  }
  
  
}
/* 开始拖拽轮播item */
function onDragStart(args:{index: number}) {

  startIndex = args.index;

}
/* 结束拖拽轮播item */
function onDragEnd(args:{index: number}) {
  
 
  /* 
    视频翻页修改
  
  */

  if (args.index === startIndex + 1 || (startIndex === 2 && args.index === 0)) {
    // 向下翻页
    if (videoQueue.current <= videoQueue.queue.length - 1) {
      videoQueue.current = (videoQueue.current + 1 + videoQueue.queue.length) % videoQueue.queue.length;

      if(args.index === 0) {
        myPlayer1.value.poster(videoQueue.queue[videoQueue.current % videoQueue.queue.length].coverUrl)
        myPlayer2.value.poster(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].coverUrl)
        myPlayer3.value.poster(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].coverUrl)
        
        myPlayer1.value.src(videoQueue.queue[videoQueue.current % videoQueue.queue.length].videoUrl)
        myPlayer2.value.src(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].videoUrl)
        myPlayer3.value.src(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].videoUrl)
        setPlayerVideoInfo(1,videoQueue.current % videoQueue.queue.length)
        setPlayerVideoInfo(2,(videoQueue.current + 1) % videoQueue.queue.length)
        setPlayerVideoInfo(3,(videoQueue.current + 2) % videoQueue.queue.length)
      }
    }
  } else if (args.index === startIndex - 1 || (startIndex === 0 && args.index === 2)) {
    
    // 向上翻页
    if (videoQueue.current >= 0) {
      videoQueue.current = (videoQueue.current - 1 + videoQueue.queue.length) % videoQueue.queue.length;
      
      if(args.index === 2) {
        myPlayer1.value.poster(videoQueue.queue[(videoQueue.current -2) % videoQueue.queue.length].coverUrl)
        myPlayer2.value.poster(videoQueue.queue[(videoQueue.current-1) % videoQueue.queue.length].coverUrl)
        myPlayer3.value.poster(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].coverUrl)
        myPlayer1.value.src(videoQueue.queue[(videoQueue.current - 2) % videoQueue.queue.length].videoUrl)
        myPlayer2.value.src(videoQueue.queue[(videoQueue.current - 1) % videoQueue.queue.length].videoUrl)
        myPlayer3.value.src(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].videoUrl)
        setPlayerVideoInfo(1,(videoQueue.current - 2) % videoQueue.queue.length)
        setPlayerVideoInfo(2,(videoQueue.current - 1) % videoQueue.queue.length)
        setPlayerVideoInfo(3,(videoQueue.current) % videoQueue.queue.length)
      }
    }
  }
 
}
let handleWheel:any;
let handleKeydown:any;

/* 没有登录跳转到登录界面 */
function notLoginIn(payload:Boolean) {
  if(!payload) {
    router.push("/login")
    ElMessage({
      message: '请先登录',
      type: 'warning'
    });
  }
}

/* 滚轮下/按键下切换下一页 */
function nextPage() {
    videoQueue.current = (videoQueue.current + 1 + videoQueue.queue.length) % videoQueue.queue.length;
    nextIndex = (startIndex + 1 + 3) % 3;

    if(startIndex == 2 && nextIndex === 0) {

      myPlayer1.value.poster(videoQueue.queue[videoQueue.current % videoQueue.queue.length].coverUrl)
      myPlayer2.value.poster(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].coverUrl)
      myPlayer3.value.poster(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].coverUrl)
      
      myPlayer1.value.src(videoQueue.queue[videoQueue.current % videoQueue.queue.length].videoUrl)
      myPlayer2.value.src(videoQueue.queue[(videoQueue.current + 1) % videoQueue.queue.length].videoUrl)
      myPlayer3.value.src(videoQueue.queue[(videoQueue.current + 2) % videoQueue.queue.length].videoUrl)
      setPlayerVideoInfo(1,videoQueue.current % videoQueue.queue.length)
      setPlayerVideoInfo(2,(videoQueue.current + 1) % videoQueue.queue.length)
      setPlayerVideoInfo(3,(videoQueue.current + 2) % videoQueue.queue.length)
    }
    videoSwipe.value.next();

    startIndex = nextIndex;

}
/* 滚轮上/按键上切换上一页 */
function prevPage() {
  videoQueue.current = (videoQueue.current - 1 + videoQueue.queue.length) % videoQueue.queue.length;
  nextIndex = (startIndex - 1 + 3) % 3;

  if (startIndex == 0 && nextIndex === 2) {

    myPlayer1.value.poster(videoQueue.queue[(videoQueue.current -2) % videoQueue.queue.length].coverUrl)
    myPlayer2.value.poster(videoQueue.queue[(videoQueue.current-1) % videoQueue.queue.length].coverUrl)
    myPlayer3.value.poster(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].coverUrl)
    myPlayer1.value.src(videoQueue.queue[(videoQueue.current - 2) % videoQueue.queue.length].videoUrl)
    myPlayer2.value.src(videoQueue.queue[(videoQueue.current - 1) % videoQueue.queue.length].videoUrl)
    myPlayer3.value.src(videoQueue.queue[(videoQueue.current) % videoQueue.queue.length].videoUrl)
    setPlayerVideoInfo(1,(videoQueue.current - 2) % videoQueue.queue.length)
    setPlayerVideoInfo(2,(videoQueue.current - 1) % videoQueue.queue.length)
    setPlayerVideoInfo(3,(videoQueue.current) % videoQueue.queue.length)
  }
  videoSwipe.value.prev();

  startIndex = nextIndex;
}
/* 按钮切换上下页 */
function prev() {
  prevPage();
}
function next() {
  nextPage();
}
onMounted(() => {
  console.log(`props:${props}`);
  
  console.log(`传入的current: ${props.current}`);
  console.log(`传入的videos${props.videos}`);
  
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
    videoSet(videoQueue.current,1)

    
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
    videoSet((videoQueue.current+1 + videoQueue.queue.length) % videoQueue.queue.length,2)


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
    videoSet((videoQueue.current+2 + videoQueue.queue.length) % videoQueue.queue.length,3)

  })
  /* 
    监听滚轮翻页
  
  */
  handleWheel = (event:any) => {
    if (event.deltaY < 0) {
      // 向上滚动
      prevPage();
       
    } else {
      // 向下滚动
      nextPage();
      
    }
  };
  /* 
    监听按键翻页
  
  */
  handleKeydown = (event:any) => {
    if (event.key === 'ArrowUp') {
      // 按上键
      prevPage();
    } else if (event.key === 'ArrowDown') {
      // 按下键
      nextPage();
      
    }
  };

  window.addEventListener('wheel', handleWheel);
  window.addEventListener('keydown', handleKeydown);
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
  window.removeEventListener('wheel', handleWheel);
  window.removeEventListener('keydown', handleKeydown);
})
</script>
<style lang="scss" scoped>

.my-swipe {
    height: 100%;
    width: 100%;
    border-radius: 12px;
    font-size: 1rem;
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.5); /* 添加阴影 */

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
.tool-bar {
  position: absolute;
  bottom: 10%;
  right: 3%;
}
.show-comment-btn {
  // width: 45px;
  // height: 60px;
  // background-color: red;
  position: absolute;
  right: 0;
  top: 32%;
  align-items: center;
  // background-color: unset;
  // border: 0;
  border: 4px solid rgba($color: #fff, $alpha: 0.3);
  background-color: rgba($color: #000000, $alpha: 0.5);
  border-right: none;
  border-radius: 25px 0 0 25px;
  cursor: pointer;
  display: flex;
  height: 45px;
  justify-content: space-around;
  transform-origin: right;
  transition: all .3s linear;
  width: 35px;
  &:hover {
    transform: scale(1.1);
  }
}
.show-comment-icon {
  margin: 0 auto;
  font-size: 22px;
  color: rgba($color: #fff, $alpha: 0.3);
  cursor: pointer;
  &:hover {
    color: #fff;
  }
    
}
.comment-drawer {
  position: relative;
  cursor: default;
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 550px; /* 你的抽屉宽度 */
  background-color: #fff;
  height: 100%; /* 使抽屉填充整个 swipe item */
  transform: translateX(100%); /* 初始状态：抽屉被隐藏在 swipe item 的右侧 */
  transition: transform 0.3s ease-out; /* 添加过渡效果 */
  // border: 2px solid rgba($color: red, $alpha: 0.3);
  border-left: 2px solid rgba($color: rgb(233, 233, 233), $alpha: 0.3);;
  border-radius: 0 12px 12px 0;
}
.close-btn {
  position: absolute;
  right: 10px;
  top: 10px;
  font-size: 25px;
  cursor: pointer;
  color: #bbb;
  z-index: 1; /* 设置 z-index 为 1，使 close-btn 位于其他元素之上 */
}
.comment-drawer.is-visible {
  transform: translateX(0); /* 可见状态：抽屉滑出来 */
  

}
.video-js {
    height: 100%;
    width: 100%;
    border-radius: 12px;
    font-size: 1rem;
    cursor: pointer;
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
      //.vjs-time-control {
      //  display: block;
      //}
      //.vjs-current-time {
      //  display: block;
      //}
      //.vjs-remaining-time {
      //  display: none;
      //}
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
.video-description {

  position: absolute;
  bottom: 70px;
  left: 15px;
  z-index: 999;
}
</style>