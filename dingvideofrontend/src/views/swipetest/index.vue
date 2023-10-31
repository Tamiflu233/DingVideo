<template>
  <div class="swipe-wrapper">
    <van-swipe class="my-swipe" :loop="false" :show-indicators="false" vertical :touchable="true">
      <van-swipe-item>1</van-swipe-item>
      <van-swipe-item>2</van-swipe-item>
      <van-swipe-item>3</van-swipe-item>
      <van-swipe-item>4</van-swipe-item>
    </van-swipe>
  </div>
</template>
<script setup lang="ts">

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