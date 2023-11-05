#index.vue页面
<template>
  <div class="recommend-video-layout">
    <VideoSwipe
      :current="current"
      :videos="queue"
    >

    </VideoSwipe>
  </div>
</template>
<script setup lang="ts">
import { VideoInfo } from "@/types/videoInfo"
const VideoSwipe = defineAsyncComponent(() => import("@/components/common/VideoSwipe.vue"))
let current:number =  0;
let queue:VideoInfo[] =  [
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

</script>


<style lang="scss" scoped>
.recommend-video-layout{
  width: 100%;
  height: 100%;
}

</style>
