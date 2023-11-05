<template>
  <div class="swipe-wrapper">
   <VideoCardRight
    :detail="detail"
   ></VideoCardRight>
  </div>
</template>
<script setup lang="ts">
const VideoCardRight = defineAsyncComponent(() => import("@/components/common/VideoCardRight.vue"))
// function toggleLike(payload:Boolean) {
//   let diff:number = payload ? 1 : -1;
//   count.value = count.value + diff;
// }
const count = ref(0)
const detail = reactive({
  username: "原神",
  title: "测试标题",
  content: "测试内容",
  createTime: "2023-11-5"
})

</script>

<style lang="css" scoped>

.swipe-wrapper{
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  position: absolute;
}
.my-swipe {
  width: 800px;
  height:400px;
}
.my-swipe .van-swipe-item {
    color: #fff;
    width:800px;
    height: 500px;
    font-size: 20px;
    line-height: 150px;
    text-align: center;
    background-color: #39a9ed;
  }
</style>