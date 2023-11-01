<script setup lang="ts">
import { computed, CSSProperties, onMounted, ref } from 'vue'
import videojs from 'video.js'
import type { VideoJsPlayerOptions } from 'video.js'
import 'video.js/dist/video-js.min.css'
type MyVideoProps = {
  /** 视频地址 */
  src: string
  width?: string
  height?: string
}
const props = withDefaults(defineProps<MyVideoProps>(), {})
// video标签
const videoRef = ref<HTMLElement | null>(null)
// video实例对象
let videoPlayer: videojs.Player | null = null
const videoWrapStyles = computed<CSSProperties>(() => {
  return {
    width: props.width || '100%',
    height: props.height || '100%'
  }
})
// 初始化videojs
const initVideo = () => {
  // https://gitcode.gitcode.host/docs-cn/video.js-docs-cn/docs/guides/options.html
  const options: VideoJsPlayerOptions = {
    language: 'zh-CN', // 设置语言
    controls: true, // 是否显示控制条
    preload: 'auto', // 预加载
    autoplay: true, // 是否自动播放
    fluid: false, // 自适应宽高
    playbackRates: [0.5, 0.75, 1.0, 1.5, 2.0],
    controlBar: {
      volumePanel: {inline: false}
    },
    src: props.src // 要嵌入的视频源的源 URL
  }
  if (videoRef.value) {
    // 创建 video 实例
    videoPlayer = videojs(videoRef.value, options, onPlayerReady)
  }
}
// video初始化完成的回调函数
const onPlayerReady = () => {}
onMounted(() => {
  initVideo()
})
</script>
<template>
  <div :style="videoWrapStyles">
    <video id="my-player" ref="videoRef" class="video-js w-full h-full vjs-big-play-centered vjs-big-play-button">
      <source :src="src" />
    </video>
  </div>
</template>
<style lang="scss" scoped>
.w-full {
  width: 100%;
}
.h-full {
  height: 100%;
}
</style>
<style>
.vjs-paused .vjs-big-play-button,
.vjs-paused.vjs-has-started .vjs-big-play-button {
  display: block !important;
}
.video-js .vjs-big-play-button{
  font-size: 2.5em !important;
  line-height: 2.3em !important;
  height: 2.5em !important;
  width: 2.5em !important;
  -webkit-border-radius: 2.5em !important;
  -moz-border-radius: 2.5em !important;
  border-radius: 2.5em !important;
  background-color: #73859f;
  background-color: rgba(115, 133, 159, 0.5) !important;
  border-width: 0.15em !important;
  margin-top: -1.25em !important;
  margin-left: -1.75em !important;
}
/*导致controlBar的零件超出的原因 */
/*!* 中间的播放箭头 *!*/
/*.vjs-big-play-button .vjs-icon-placeholder {*/
/*  font-size: 1.23em !important;*/
/*}*/
.video-js .vjs-time-control {
  display: block !important;
}
.video-js .vjs-remaining-time {
  display: none !important;
}
</style>
