<template>
  <div class="video-detail">
    <VideoSwipe
      :current="index"
      :videos="store.videosBuffer"
    >

    </VideoSwipe>

  </div>
</template>

<script setup lang="ts">
import { VideoDetail } from "@/types/videoInfo"
import { useRoute } from 'vue-router'
import {watch} from 'vue';
import { onMounted } from 'vue';
import {getCategoryVideos} from '@/api/video';
import { videoStore } from "@/store/videos/videos";
const route = useRoute()

const store = videoStore();
const index = ref(parseInt(route.params.index as string))
const kind = ref(route.params.kind as String)
const VideoSwipe = defineAsyncComponent(() => import("@/components/common/VideoSwipe.vue"))

// // 接受传入的视频列表和当前页号
// const props = defineProps({
//   videos: {
//     type: Array as PropType<VideoDetail[]>,
//     required: true
//   },
//   current : {
//     type: Number,
//     default: 0
//   }
// })

watch(
  () => route.params.kind,
  async (newKind) => {
    
    kind.value = newKind as string;
  },
  { immediate: true }
);
watch(
  () => route.params.index,
  async (newIndex) => {
    
    index.value = parseInt(newIndex as string) 
  },
  { immediate: true }
);
onMounted(async () =>{
  index.value = parseInt(route.params.index as string) 
  kind.value = route.params.kind as String
  
});


</script>
<style lang="scss" scoped>
.video-detail{
  position:absolute;
  top:0;
  bottom:0;
  left:0;
  right:0;
}


</style>