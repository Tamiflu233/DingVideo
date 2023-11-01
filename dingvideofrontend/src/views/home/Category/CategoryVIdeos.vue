#index.vue页面
<template>
  <div class="category-videos-wrapper">
    <el-row :gutter="20">
      <el-col class="video-info-item" :span="6" v-for="item in [1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7]">
        <VideoInfoCard :video-info="videoInfo" />
      </el-col>
    </el-row>
  </div>
</template>
<script setup lang="ts">
const VideoInfoCard = defineAsyncComponent(() => import("@/components/common/VideoInfoCard.vue"))
let videoInfo = reactive(
  {
  src:"https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/736edc0145b94189b2f5b44666cee183-fd719751718abcfad6f25c5f32b6fd63-ld.mp4",
  poster: "https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/snapshots/e9c559d852fb4bab85ae9e6f2f39a5e8-00005.jpg",
  title: "Count Sketch改进",
  author:"MGao",
  publishTime:"11月1日"
}
)


</script>


<style lang="scss" scoped>
.category-videos-wrapper{
  width: 100%;
  height: 100%;
}
.video-info-item{
  margin-bottom: 20px;
}
</style>
