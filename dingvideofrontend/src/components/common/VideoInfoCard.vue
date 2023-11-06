<template>
  <div class="video-info-card" @click="handleClick">
    <el-card :body-style="{ padding: '0px', marginBottom: '1px'}">
          <div class="video-displayer" >
            <el-image
              fit="cover" 
              :src="videoInfo.coverUrl"
              >
            </el-image>

          </div>
          <div class="bottom-info">
            <div class="video-title">{{ videoInfo.title }}</div>
            <div class="bottom-card-header">

                  <div class="author">@ {{ videoInfo.user?.nickname }} </div>

                  <div class="date">{{ publishTime }} </div>
            </div>
          </div>
        </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { VideoDetail } from "@/types/videoInfo"
import { PropType } from 'vue';
import { useRouter } from "vue-router";
import { computed } from 'vue'
import timeUtil from '@/utils/date';
// 路由控制
const router = useRouter();

const props = defineProps({
  videoInfo: {
    type: Object as PropType<VideoDetail>,
    // 确保使用箭头函数
    default: () => ({
      coverUrl:"https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/snapshots/358d95a124ff421382624687fe348c0c-00001.jpg",
      videoUrl:"https://rl.shuishan.net.cn/ef3005a0bc2f71ed90a40764a0fd0102/d0cb4dd2d97c40c9919ebaf4caab2b6d-650718c1f29259fecaf43b6c46351a0b-ld.mp4",
      title:"测试视频",
      description: "测试",
      createTime: "2023-11-06T06:50:29.000+00:0"
    }),
    required: true
  },
  index: {
    type: Number
  },
  

})
/* 日期显示 */
const publishTime = computed(() => {
  return timeUtil(props.videoInfo.createTime)
})
function handleClick() {
  router.push({path: `/detail/${props.videoInfo.category}/${props.index}`})
}
</script>
<style lang="scss" scoped>
.video-info-card {
  &:hover {
          cursor: pointer;
        }
  .el-card {
    border-radius: 8px;
    .video-displayer {
      position: relative;
      background-color: #000;
      height: 280px;
      .el-image{
        height: 100%;
        width: 100%;
      }
      
   
    }
    .bottom-info {
      padding: 14px;
      .video-title {
        font-size: 15px;
        text-align: left;
        height: 45px;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        white-space: normal;
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