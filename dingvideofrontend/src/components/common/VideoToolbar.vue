<template>
  <div class="video-tool-bar">
    <div class="control-bar">
      <el-icon class="prev-btn control-btn" @click="prevPage">
        <ArrowUpBold />
      </el-icon>
      <el-icon class="next-btn control-btn" @click="nextPage">
        <ArrowDownBold />
      </el-icon>
    </div>
    <div class="avatar">
      <el-avatar :size="70" :src="videoInfo.user?.avatar"></el-avatar>
    </div>
    <IconButton
   :is-initially-liked="false"
    iconSrc="mdi:heart-outline"
    icon-tgt="mdi:heart"
    colorSrc="white"
    color-tgt="#FE2C55"
    :count="videoStates.likeCount"
    @like="toggleLike"
   ></IconButton>
   <IconButton
   :is-initially-liked="false"
    iconSrc="ant-design:comment-outlined"
    icon-tgt="ant-design:comment-outlined"
    colorSrc="white"
    color-tgt="white"
    :count="videoStates.commentCount"
    @like="toggleComment"
   ></IconButton>
   <IconButton
   :is-initially-liked="false"
    iconSrc="ph:star"
    icon-tgt="ph:star-fill"
    colorSrc="white"
    color-tgt="#FFB802"
    :count="videoStates.collectCount"
    @like="toggleCollect"
   ></IconButton>
   <IconButton
   :is-initially-liked="false"
    iconSrc="uil:share"
    icon-tgt="uil:share"
    colorSrc="white"
    color-tgt="white"
    :count="videoStates.shareCount"
    @like="toggleShare"
   ></IconButton>
   <el-icon class="more">
    <MoreFilled></MoreFilled>
   </el-icon>
  </div>
</template>
<script setup lang="ts">
import { ref,reactive } from 'vue';
import { VideoDetail } from "@/types/videoInfo";
//  视频信息
const props = defineProps({
  videoInfo: {
    type: Object as PropType<VideoDetail>,
    required: true
  },
 
})
const videoStates = reactive({
  isLiked: false,
  isCommented: false,
  isCollected: false,
  isShared: false,
  likeCount: props.videoInfo.likes,
  commentCount: props.videoInfo.comments,
  collectCount: props.videoInfo.collections,
  shareCount: 0,
})
const emit = defineEmits(['prev', 'next','toggleLike','toggleComment','toggleCollect','toggleShare'])
/* 切换上一页，下一页 */
function prevPage() {
  emit('prev');
}
function nextPage() {
  emit('next');
}
const IconButton = defineAsyncComponent(() => import("@/components/common/IconButton.vue"))
function toggleLike(payload:Boolean) {
  let diff:number = payload ? 1 : -1;
  videoStates.likeCount = videoStates.likeCount + diff;
  emit('toggleLike', payload);
}
function toggleComment(payload:Boolean) {
  // let diff:number = payload ? 1 : -1;
  // videoStates.commentCount = videoStates.commentCount + diff;
  emit('toggleComment')
}
function toggleCollect(payload:Boolean) {
  let diff:number = payload ? 1 : -1;
  videoStates.collectCount = videoStates.collectCount + diff;
  emit('toggleCollect', payload);
}
function toggleShare(payload:Boolean) {
  // let diff:number = payload ? 1 : -1;
  // videoStates.shareCount = videoStates.shareCount + diff;
  emit('toggleShare')
}
</script>

<style lang="scss" scoped>
.video-tool-bar {
  .control-bar {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    margin: 0 auto 30px;
    height: 110px;
    width: 45px;
    border: 4px solid rgba($color: #fff, $alpha: 0.3);
    background-color: rgba($color: #000000, $alpha: 0.5);
    border-radius: 25px;
    box-sizing: content-box;
    cursor: pointer;
    .control-btn {
      margin: 0 auto;
      font-size: 28px;
      color: rgba($color: #fff, $alpha: 0.3);
      cursor: pointer;
      &:hover {
        color: #fff;
      }
    
    }
  }
  .avatar {
    margin-bottom: 20px;
    cursor: pointer;
    transform: scale(1);
    transition: transform 0.2s ease;

    &:hover {
      transform: scale(1.1);
    }

  }
  .more {
    color: #fff;
    font-size: 45px;
    cursor: pointer;
  }
}

</style>