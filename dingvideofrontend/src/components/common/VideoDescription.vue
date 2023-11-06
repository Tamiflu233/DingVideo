<template>
  <div class="video-description-wrapper">
    <div class="desc-head">
      <div class="nickname">@{{ videoInfo.user?.nickname}}</div>
      <div class="publish-time">· {{time}}</div>
    </div>
    <div class="title">
      {{videoInfo.title}}
    </div>
  </div>
</template>

<script lang="ts" setup>
import timeUtil from '@/utils/date';
import { VideoDetail } from "@/types/videoInfo";
const props = defineProps({
  videoInfo: {
    type: Object as PropType<VideoDetail>,
    required: true
  },
 
})
// const props = defineProps({
  
//   nickname: {
//     type: [String, Object],
//     required: true,
//   },
//   publishTime: {
//     type: [String, Object],
//     required: true,
//   },
//   title: {
//     type: [String, Object],
//     required: true,
//   },

  
// })

/* 日期显示 */
const time = computed(() => {
  return timeUtil(props.videoInfo.createTime as string)
})


</script>

<style lang="scss" scoped>
.video-description-wrapper {
  padding: 16px 95px 16px 16px;
  width: 100%;
  z-index: 2;
  text-shadow: 0 1px 1px rgb(0 0 0 / 20%);

  // background-color: #000;
  .desc-head {
    display: flex;
    line-height: 1.8055555556vw;
    .nickname {
      color: #fff;
      font-family: PingFang SC,DFPKingGothicGB-Medium,sans-serif;
      font-weight: 500;
      font-size: 1.25vw;
    }
    .publish-time {
      color: hsla(0,0%,100%,.9);
      font-family: PingFang SC,DFPKingGothicGB-Regular,sans-serif;
      font-size: .8333333333vw;
      margin-left: 5px;
    }
  }
  .title {
    display: flex;
    color: #fff;
    font-family: PingFang SC,DFPKingGothicGB-Medium,sans-serif;
    font-size: .9722222222vw;
    line-height: 1.5277777778vw;
  }
}
</style>
