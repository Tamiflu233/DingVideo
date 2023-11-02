<template>
  <div class="video-info-card">
    <el-card :body-style="{ padding: '0px', marginBottom: '1px'}">
          <div class="video-displayer" >
            <el-image
              fit="cover" 
              :src="videoInfo.poster"
              @click="()=>handleClick(videoInfo)"
              >
            </el-image>

          </div>
          <div class="bottom-info">
            <div class="video-title">{{ videoInfo.title }}</div>
            <div class="bottom-card-header">

                  <div class="author">@ {{ videoInfo.author }} </div>

                  <div class="date">{{ videoInfo.publishTime }} </div>
            </div>
          </div>
        </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { VideoInfo } from "@/types/videoInfo"
import { PropType } from 'vue';
import { useRouter } from "vue-router";
// 路由控制
const router = useRouter();

defineProps({
  videoInfo: {
    type: Object as PropType<VideoInfo>,
    // 确保使用箭头函数
    default: () => ({
      poster:"https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/snapshots/358d95a124ff421382624687fe348c0c-00001.jpg",
      src:"https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/d0cb4dd2d97c40c9919ebaf4caab2b6d-650718c1f29259fecaf43b6c46351a0b-ld.mp4"
    }),
    required: true
  }

})
function handleClick(videoInfo:VideoInfo) {
  router.push({path: '/detail'})
}
</script>
<style lang="scss" scoped>
.video-info-card {
  .el-card {
    border-radius: 8px;
    .video-displayer {
      position: relative;
      .el-image {
        &:hover {
          cursor: pointer;
        }
      }
   
    }
    .bottom-info {
      padding: 14px;
      .video-title {
        font-size: 15px;
        text-align: left;
      }
      .bottom-card-header {
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
        .author {
          font-size: 12px;
          color: #999;
        }
        .date {
          font-size: 12px;
          color: #999;
        }
      }
    }
  }
}



</style>