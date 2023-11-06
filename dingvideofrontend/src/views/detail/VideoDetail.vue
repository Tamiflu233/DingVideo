<template>
  <div class="video-detail">
    <VideoSwipe
      :current="index"
      :videos="store.videosBuffer"
    >

    </VideoSwipe>
    <div class="close-btn" @click="handleClick">
      <iconify-icon class="close-icon" icon="mingcute:close-fill"></iconify-icon>
    </div>

  </div>
</template>

<script setup lang="ts">
import { VideoDetail } from "@/types/videoInfo"
import { useRoute,useRouter } from 'vue-router'
import {watch} from 'vue';
import { onMounted } from 'vue';
import {getCategoryVideos} from '@/api/video';
import { videoStore } from "@/store/videos/videos";
import 'iconify-icon';
const route = useRoute()
const router = useRouter()

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
function handleClick() {
  router.back()
}
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
.close-btn {
  background-color: rgba(0,0,0,.18);
  border: .5px solid hsla(0,0%,100%,.15);
  border-radius: 32px;
  height: 64px;
  width: 64px;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  top: 50px;
  left: 50px;
  cursor: pointer;
  color: hsla(0,0%,100%,.15);

  &:hover {
    color: #fff;
    background-color: rgba(0,0,0,.19);
  }
  .close-icon {
    
    font-size: 32px;
  }
}

</style>