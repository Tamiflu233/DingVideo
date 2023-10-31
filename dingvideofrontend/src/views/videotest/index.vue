<template>
  <div>
    <h1>视频测试</h1>
    <div class="video-container">
      <MyVideo src="https://media.w3.org/2010/05/sintel/trailer.mp4" />
    </div>
  </div>
</template>

<script setup lang="ts">
import MyVideo from '@/components/MyVideo/index.vue'
</script>

<style scoped>
.video-container {
  width: 800px;
  height: 600px;
}
</style>