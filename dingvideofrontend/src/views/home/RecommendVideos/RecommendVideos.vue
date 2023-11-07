#index.vue页面
<template>
  <div class="recommend-video-layout">
    <VideoSwipe
      :current="0"
      :videos="store.videosBuffer"
    >

    </VideoSwipe>
  </div>
</template>
<script setup lang="ts">
import {watchEffect,onMounted,onUnmounted} from 'vue'
import { VideoDetail } from "@/types/videoInfo"
const VideoSwipe = defineAsyncComponent(() => import("@/components/common/VideoSwipe.vue"))
import {getHomeVideos} from '@/api/video';
import { videoStore } from "@/store/videos/videos";
import {useRoute} from 'vue-router'
// 视频缓存池
const store = videoStore();
const route = useRoute()
let queue:VideoDetail[] =  [
  {
    "coverUrl": "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/snapshots/e13c8454edf740f383876e05249023ea-00005.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/0c66767d68cc45968eaae18050a753b8-9788c7569453b623fd6fa5d32d6214de-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  },
  {
    "coverUrl": "https://rl.shuishan.net.cn/59ca8d30b25e71edad487035d0b20102/snapshots/ad6b5d6478094805b28dcdb82ef0dd4f-00001.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/59ca8d30b25e71edad487035d0b20102/264badc5eb534f0da5c73eb1232a2f60-8845e77acf1958539faeb69cff740d0f-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  },
  {
    "coverUrl": "https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/snapshots/e9c559d852fb4bab85ae9e6f2f39a5e8-00005.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/a6b598106d8971eebfff4531858c0102/736edc0145b94189b2f5b44666cee183-fd719751718abcfad6f25c5f32b6fd63-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  },
  {
    "coverUrl": "https://rl.shuishan.net.cn/bd9771706d8971ee965a6733a78e0102/snapshots/db805ca49bbd49b7a4889ef6e5860598-00005.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/bd9771706d8971ee965a6733a78e0102/c3b8607eab484eb59787b00a6ff40609-87f8ea8c709ea926373cef3f2c2da815-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  },
  {
    "coverUrl": "https://rl.shuishan.net.cn/8c1488406d9371eebfe15017e1f90102/snapshots/60b85da06ccc4b788b12c96fd9a90afa-00005.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/8c1488406d9371eebfe15017e1f90102/6960162bee8144d88816bfe816f0d859-1a89b9323b4bd723eefc7b534371b63f-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  },
  {
    "coverUrl": "https://rl.shuishan.net.cn/8b9836e06d9471eebf885017f0e90102/snapshots/aa6c21a95e284b738224d8a3e55bae12-00005.jpg",
    "videoUrl":"https://rl.shuishan.net.cn/8b9836e06d9471eebf885017f0e90102/6d988a40a5304dc89c3f35ff86e9e26e-8846bfb028fa46fb733eba4d15cff82b-ld.mp4",
    "createTime":"2023-11-04 16:20:26.263000",
    "title":"好了",
    "description": "aa",
    "category": "sports",
			"likes": 0,
			"collections": 0,
			"comments": 0,
			"isLiked": null,
			"useId": 4,
			"user": {
				"id": 4,
				"createTime": "2023-11-04T08:20:26.263+00:00",
				"updateTime": "2023-11-04T08:20:26.263+00:00",
				"remark": null,
				"username": "111",
				"nickname": "333",
				"phone": "18256537198",
				"password": "$2a$10$gnjgiSRGsmODQtf6TBCndupHFZeyRXDvXlKNycWQ89O611rTSBgeC",
				"email": "51255903096@stu.ecnu.edu.cn",
				"age": null,
				"gender": null,
				"avatar": "https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/tamiflu233.jpg",
				"enabled": false,
				"accountNonLocked": false,
				"accountNonExpired": false,
				"credentialsNonExpired": false,
				"authorities": null
			},
			"createTime": "2023-11-06T06:50:29.000+00:00",
			"updateTime": "2023-11-06T06:50:31.000+00:00"
  }
]
/* 视频信息列表 */
const videoList = reactive([

])
/* 获取当前类别的视频信息 */
const getCurrentVideos = async () => {

  const res = await getHomeVideos();
  videoList.splice(0, videoList.length, ...(res.data as never[]));
	console.log(`共${videoList.length}个视频`);

	
  store.setVideosBuffer(videoList);
}

const stopWatch = watchEffect(async () => {
	console.log(`路由${route.name}`);
	
      if (route.name === 'RecommendVideos') {
				
        await getCurrentVideos()
      }
})
onUnmounted(() => {
	stopWatch()
})
</script>


<style lang="scss" scoped>
.recommend-video-layout{
  width: 100%;
  height: 100%;
}

</style>
