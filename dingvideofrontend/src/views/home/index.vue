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
                            <svg x="1689147877558" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                 xmlns="http://www.w3.org/2000/svg" p-id="3345" width="25" height="25">
<!--                              <path-->
<!--                                  d="M512 901.746939c-13.583673 0-26.122449-4.179592-37.093878-13.061225-8.881633-7.314286-225.697959-175.020408-312.424489-311.379592C133.746939 532.37551 94.040816 471.24898 94.040816 384.522449c0-144.718367 108.146939-262.269388 240.326531-262.269388 67.395918 0 131.657143 30.82449 177.632653 84.636735 45.453061-54.334694 109.191837-84.636735 177.110204-84.636735 132.702041 0 240.326531 117.55102 240.326531 262.269388 0 85.159184-37.093878 143.673469-67.395919 191.216327l-1.044898 1.567346c-86.726531 136.359184-303.542857 304.587755-312.424489 311.379592-10.44898 8.359184-22.987755 13.061224-36.571429 13.061225z"-->
<!--                                  :fill="!checkFavorite(detail.id)?'#cecccc':'#d81e06'" p-id="3346"></path>-->
                              <path
                                  d="M512 901.746939c-13.583673 0-26.122449-4.179592-37.093878-13.061225-8.881633-7.314286-225.697959-175.020408-312.424489-311.379592C133.746939 532.37551 94.040816 471.24898 94.040816 384.522449c0-144.718367 108.146939-262.269388 240.326531-262.269388 67.395918 0 131.657143 30.82449 177.632653 84.636735 45.453061-54.334694 109.191837-84.636735 177.110204-84.636735 132.702041 0 240.326531 117.55102 240.326531 262.269388 0 85.159184-37.093878 143.673469-67.395919 191.216327l-1.044898 1.567346c-86.726531 136.359184-303.542857 304.587755-312.424489 311.379592-10.44898 8.359184-22.987755 13.061224-36.571429 13.061225z"
                                  :fill="'#cecccc'" p-id="3346"></path>
                            </svg>
<!--                            <el-text size="large" tag="b" type="info">{{ detail.likeCount }}</el-text>-->
                            <el-text size="large" tag="b" type="info">999</el-text>
                          </el-button>
                          <el-button link class="warp" @click="doSomething('collect', detail)" :disabled="review">
                            <svg x="1689148085763" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                 style="margin-bottom: 4px;"
                                 xmlns="http://www.w3.org/2000/svg" p-id="4912" width="25" height="25">
<!--                              <path-->
<!--                                  d="M512.009505 25.054894l158.199417 320.580987 353.791078 51.421464L767.995248 646.579761l60.432101 352.365345-316.417844-166.354615-316.436854 166.354615 60.432101-352.365345L0 397.057345l353.791078-51.421464z"-->
<!--                                  :fill="!checkCollect(detail.id)?'#cecccc':'#f4ea2a'" p-id="4913"></path>-->
                              <path
                                  d="M512 901.746939c-13.583673 0-26.122449-4.179592-37.093878-13.061225-8.881633-7.314286-225.697959-175.020408-312.424489-311.379592C133.746939 532.37551 94.040816 471.24898 94.040816 384.522449c0-144.718367 108.146939-262.269388 240.326531-262.269388 67.395918 0 131.657143 30.82449 177.632653 84.636735 45.453061-54.334694 109.191837-84.636735 177.110204-84.636735 132.702041 0 240.326531 117.55102 240.326531 262.269388 0 85.159184-37.093878 143.673469-67.395919 191.216327l-1.044898 1.567346c-86.726531 136.359184-303.542857 304.587755-312.424489 311.379592-10.44898 8.359184-22.987755 13.061224-36.571429 13.061225z"
                                  :fill="'#f4ea2a'" p-id="3346"></path>
                            </svg>
<!--                            <el-text size="large" tag="b" type="info">{{ detail.collectCount }}</el-text>-->
                            <el-text size="large" tag="b" type="info">999</el-text>
                          </el-button>
                          <el-button link class="warp" @click="clearReply">
                            <svg x="1689148939874" class="icon" viewBox="0 0 1024 1024" version="1.1"
                                 xmlns="http://www.w3.org/2000/svg" p-id="6375" width="25" height="25">
                              <path
                                  d="M512 0C226.742857 0 0 197.485714 0 446.171429c0 138.971429 73.142857 270.628571 190.171429 351.085714L190.171429 1024l226.742857-138.971429c29.257143 7.314286 65.828571 7.314286 95.085714 7.314286 285.257143 0 512-197.485714 512-446.171429C1024 197.485714 797.257143 0 512 0zM256 512C219.428571 512 190.171429 482.742857 190.171429 446.171429S219.428571 380.342857 256 380.342857c36.571429 0 65.828571 29.257143 65.828571 65.828571S292.571429 512 256 512zM512 512C475.428571 512 446.171429 482.742857 446.171429 446.171429S475.428571 380.342857 512 380.342857c36.571429 0 65.828571 29.257143 65.828571 65.828571S548.571429 512 512 512zM768 512C731.428571 512 702.171429 482.742857 702.171429 446.171429s29.257143-65.828571 65.828571-65.828571c36.571429 0 65.828571 29.257143 65.828571 65.828571S804.571429 512 768 512z"
                                  p-id="6376" fill="#cecccc"></path>
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
