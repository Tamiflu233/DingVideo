#index.vue页面
<template>
  <div class="category-videos-wrapper">
    <el-row :gutter="20" v-if="videoList.length !== 0">
      <el-col class="video-info-item" :span="6" v-for="(item,index) in videoList">
        <VideoInfoCard :video-info="item" :index="index" />
      </el-col>
    </el-row>
    <el-empty @click.prevent class="show-empty" v-else description="暂无视频 QAQ" />

  </div>
</template>
<script setup lang="ts">
import {watch, onMounted, onUnmounted,ref} from 'vue'
const VideoInfoCard = defineAsyncComponent(() => import("@/components/common/VideoInfoCard.vue"))
import {getCategoryVideos} from '@/api/video';
import { useRoute } from 'vue-router';
import { videoStore } from "@/store/videos/videos";
const route = useRoute();
// 视频缓存池
const store = videoStore();
let category:string = "";
category = route.query.kind as string;
console.log(category);

/* 视频信息列表 */
const videoList = reactive([

])
/* 获取当前类别的视频信息 */
const getCurrentKindVideos = async (kind: String) => {
  const res = await getCategoryVideos({ kind });
  videoList.splice(0, videoList.length, ...(res.data as never[]));
  store.setVideosBuffer(videoList);
}
watch(
  () => route.query.kind,
  async (newKind) => {
    category = newKind as string;
    await getCurrentKindVideos(category);
  },
  { immediate: true }
);


</script>


<style lang="scss" scoped>
.category-videos-wrapper{
  width: 100%;
  height: 100%;
  .show-empty {

    position: relative;
    top: 50%;
    transform: translateY(-70%);
  }
}
.video-info-item{
  margin-bottom: 20px;
}

</style>
