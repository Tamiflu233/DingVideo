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
                  <!-- 图片区 -->
                  <el-col :span="50">
                    <div class="banner">
                      <video ref="videoPlayer1" class="video-js vjs-big-play-button" style="margin: auto auto"></video>
                    </div>
                  </el-col>
<!--                  <el-col :span="50" style="box-shadow:inset 0 -3em 3em rgba(0, 0, 0, 0.05)">-->
                  <el-col :span="50">
                    <div class="info" style="width: 500px;margin-top: 10px;">
                      <!-- 卡片头部 -->
                      <el-row style="align-items: center;width: 500px;">
<!--                        <a :href="`/user/index/${detail.user.id}`">-->
                        <a>
<!--                          <el-avatar :src="detail.user.avatar" size="large"/>-->
                          <el-avatar src="https://cdn.jsdelivr.net/gh/Tamiflu233/AssetsRepo/img/Avatar.jpg" size="large"/>
                        </a>
<!--                        <div class="username">{{ }}</div>-->
                        <div class="username">111</div>
<!--                        <button @click="cancelFocusOn(detail.user.id)" class="focusOn" v-if="checkFollow(detail.user.id)">已关注-->
                        <button class="focusOn" v-if=true>已关注
                        </button>
                        <button class="focusOn" v-else @click="doFocusOn(detail.user.id)">关注</button>
                      </el-row>
                      <!-- 卡片头部结束 -->
                      <div class="main-content">
                        <!-- 卡片内容 -->
                        <el-row style="margin-top: 20px;">
<!--                          <h2>{{ detail.title }}</h2>-->
                          <h2>detail.title</h2>
                        </el-row>
                        <el-row>
<!--                          <div class="content">{{ detail.content }}</div>-->
                          <div class="content">detail.content</div>
                        </el-row>
                        <el-row>
<!--                          <time class="time">{{ detail.createTime }}</time>-->
                          <time class="time">detail.createTime</time>
                        </el-row>
                        <!-- 卡片内容结束 -->
                        <hr/>
<!--                         评论区 -->
                        <div class="comments" v-if="comments" v-infinite-scroll="load" :infinite-scroll-disabled="disabled">
                          <el-empty description="现在还没有评论" v-if="comments.length === 0"/>
                          <div v-else class="commentBox">
                            <div class="commentTitle" style="margin-bottom: 5px;">共{{ detail.commentCount }}条评论</div>
                            <div v-for="item in comments" :key="item.id">
                              <el-row :gutter="20">
                                <el-col :span="2.5">
                                  <a :href="`/user/index/${item.user.id}`">
                                    <el-avatar :src="item.user.avatar" :size="30"></el-avatar>
                                  </a>
                                </el-col>
                                <el-col :span="20" style="font-size: 14px">
                                  <div style="color:#33333399;">{{ item.user.username }}</div>
                                  <div style="color:#333333;margin-top: 2px;margin-bottom: 5px;">{{ item.content }}</div>
                                  <time class="time">{{ item.createTime }}</time>
                                  <el-icon style="float: right;font-size: medium" @click="commentMain(item)">
                                    <ChatRound/>
                                  </el-icon>
                                </el-col>
                                <el-col style="margin-top: 5px;">
                                  <div v-for="reply in item.replies" :key="reply.id" style="margin-left: 30px">
                                    <!-- 渲染子评论的内容 -->
                                    <el-row :gutter="20">
                                      <el-col :span="2.5">
                                        <a :href="`/user/index/${reply.user.id}`">
                                          <el-avatar :src="reply.user.avatar" :size="25"></el-avatar>
                                        </a>
                                      </el-col>
                                      <el-col :span="20" style="font-size: 12px">
                                        <div style="color:#33333399;">{{ reply.user.username }}</div>
                                        <div style="color:#333333;margin-top: 2px;margin-bottom: 10px;">{{ reply.content }}</div>
                                        <time class="time">{{ reply.createTime }}</time>
                                      </el-col>
                                    </el-row>
                                  </div>
                                  <div class="more" @click="loadReply(item)" v-if="item.replyCount > 0">展开{{
                                      item.replyCount
                                    }}条回复
                                  </div>
                                </el-col>
                              </el-row>
                              <el-divider/>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- 评论区结束 -->
                      <el-divider/>
                    </div>
                    <div class="bottomArea">
                      <div class="buttonArea">
                        <el-row>
                          <el-button link class="warp" @click="doSomething('like', detail)" :disabled="review">
                            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
                              <path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z"
                                    :fill="'#dbdbdb'" p-id="4335">
                              </path>
                            </svg>
<!--                            <el-text size="large" tag="b" type="info">{{ detail.likeCount }}</el-text>-->
                            <el-text size="large" tag="b" type="info" style="margin-left: 3px;margin-top: 3px">999</el-text>
                          </el-button>
                          <el-button link class="warp" @click="doSomething('collect', detail)" :disabled="review">
                            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
                              <path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24z"
                                    :fill="'#dbdbdb'" p-id="4085">
                              </path>
                            </svg>
                            <el-text size="large" tag="b" type="info" style="margin-left: 3px;margin-top: 3px">219</el-text>
                          </el-button>
                          <el-button link class="warp" @click="clearReply">
                            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
                              <path d="M512 85.333333c235.637333 0 426.666667 191.029333 426.666667 426.666667S747.637333 938.666667 512 938.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333z m-102.218667 549.76a32 32 0 1 0-40.917333 49.216A223.178667 223.178667 0 0 0 512 736c52.970667 0 103.189333-18.485333 143.104-51.669333a32 32 0 1 0-40.906667-49.216A159.189333 159.189333 0 0 1 512 672a159.189333 159.189333 0 0 1-102.218667-36.906667z"
                                    :fill="'#dbdbdb'" p-id="4537">
                              </path>
                            </svg>
<!--                            <el-text size="large" tag="b" type="info">{{ detail.commentCount }}</el-text>-->
                            <el-text size="large" tag="b" type="info">999</el-text>
                          </el-button>
                        </el-row>
                      </div>
                      <el-input
                          v-model="content" class="comment-input my" type="text" placeholder="说点什么..." ref="commentInput"
                          :prefix-icon="Edit" @keyup.enter="sendComment(detail, to)" clearable style="margin-top: 5px"
                          :disabled="review"
                      />
                    </div>
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
      playToggle: true,
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
      playToggle: true,
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

const props = defineProps({
  detail: {
    type: Object,
    required: true,
  },
  review: {
    type: Boolean,
    default: false
  }
})
const comments = ref([])
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


//新样式
.banner {
  height: 800px;
  width: 1000px;
  border-radius: 0.8rem;
  margin-top: 10px;
  margin-bottom: 10px;
}

.username {
  margin-left: 15px;
}

.focusOn {
  position: absolute;
  right: 10px;
  padding: 0.6rem 0.8rem;
  color: white;
  background-color: red;
  border: 0;
  border-radius: 0.8rem;
}

.focusOn:hover {
  background-color: #fd5656;
}
</style>
