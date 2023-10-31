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
          <video ref="videoPlayer" class="video-js" style="margin: auto auto"></video>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import { onMounted, onUnmounted,ref } from 'vue'
import { MenuItem } from "@/types/sidemenu"
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
// 视频播放器
const videoPlayer = ref()
const myPlayer = ref()

onMounted(() => {
  myPlayer.value = videojs(videoPlayer.value, {
    poster: "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/snapshots/e13c8454edf740f383876e05249023ea-00005.jpg",
    autoplay: true,
    controls: true,
    sources: [
      {
        src: "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/0c66767d68cc45968eaae18050a753b8-9788c7569453b623fd6fa5d32d6214de-ld.mp4",
        type: 'video/mp4',
      }
    ],
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
    myPlayer.value.log("play.....")
  })
})

onUnmounted(() => {
  if (myPlayer.value) {
    myPlayer.value.dispose()
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
.video-js {
    height: 100%;
    width: 100%;
    border-radius: 12px;
    font-size: 1rem;
  }

  ::v-deep .video-js {
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

</style>
