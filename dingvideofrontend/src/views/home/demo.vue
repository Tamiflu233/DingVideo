<template>
  <div class="video-list" ref="videoList" @touchstart="onTouchStart" @touchmove="onTouchMove" @touchend="onTouchEnd">
    <div class="video-item" v-for="(video, index) in videos" :key="index" :style="{ transform: `translateY(${(index - currentIndex) * 100}%)` }">
      <!-- <video ref="videoPlayer" :src="video.src" class="video-js"></video> -->
      <div class="video-big">
          {{ video }}
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
// import videojs from "video.js"
// // import Player from 'video.js/dist/types/player'
// import "video.js/dist/video-js.css"
// const videos = ref([
//   { src: 'https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/0c66767d68cc45968eaae18050a753b8-9788c7569453b623fd6fa5d32d6214de-ld.mp4' },
//   { src: 'https://rl.shuishan.net.cn/3920a42dccaa4379b1f78cd1a0c57d28/d8ca6a44d0804c69a2b6f297f2399519-117084ee8fd7eee3d8b2774256fa0953-ld.mp4' },
//   { src: 'https://rl.shuishan.net.cn/f5dbc920bc2f71ed80417035d0b20102/0db1ab7c04bd455eb20a013cab58db47-7c623a77479b7488fd2fdb2424110d17-ld.mp4' },
// ])
const videos = [
  "1",
  "2",
  "3"
]
const currentIndex = ref(0)
const startY = ref(0)
const endY = ref(0)

function onTouchStart(event: TouchEvent) {
  startY.value = event.touches[0].clientY
}

function onTouchMove(event: TouchEvent) {
  endY.value = event.touches[0].clientY
}

function onTouchEnd() {
  const deltaY = endY.value - startY.value
  if (deltaY > 50 && currentIndex.value > 0) {
    currentIndex.value--
  } else if (deltaY < -50 && currentIndex.value < videos.value.length - 1) {
    currentIndex.value++
  }
  startY.value = 0
  endY.value = 0
}
// 视频播放器
// const videoPlayer = ref()
// const myPlayer = ref()

// onMounted(() => {
//   myPlayer.value = videojs(videoPlayer.value, {
//     autoplay: true,
//     controls: true,
//     controlBar: {
//       //音量条竖直
//       volumePanel: {
//           inline: false,
//           CurrentTimeDisplay: true
//         }
//         // // 暂停按钮隐藏
//     },
//     playbackRates: [0.5, 1, 1.5, 2]
//   }, () => {
//     myPlayer.value.log("play.....")
//   })
// })

// onUnmounted(() => {
//   if (myPlayer.value) {
//     myPlayer.value.dispose()
//   }
// })
</script>

<style lang="scss" scoped>
.video-list {
  height: 100%;
  overflow: hidden;
  position: relative;
  background-color: rebeccapurpleblue;
}

.video-item {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  transition: transform 0.3s ease-in-out;
}

.video-js {
  height: 100%;
  width: 100%;
  object-fit: cover;
}
.video-big{
  width: 500px;
  height: 500px;
  background-color: red;
}
</style>