<template>
  <div class="userInfo">
    <el-row :gutter="10">
      <el-col :span="7" style="width: 250px;">
        <el-avatar :size="150" :src="userInfo.avatar"></el-avatar>
      </el-col>
      <el-col :span="7" style="width: 250px!important;">
        <h2>{{ userInfo.nickname }}</h2>
        <!-- <p>{{ userInfo.signature }}</p> -->
        <div class="tagArea">
          <el-tag class="ml-2" type="success" round>{{ userInfo.follows }} 关注</el-tag>
          <el-tag class="ml-2" type="info" round>{{ userInfo.fans }} 粉丝</el-tag>
          <el-tag class="ml-2" type="warning" round>{{ userInfo.videoCnts }} 作品</el-tag>
        </div>
      </el-col>
      <el-col :span="5" style="width: 100px;">
        <button class="focusOn">关注</button>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { getUserInfoById } from "@/api/login.js";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";

let userInfo = reactive({
  username:"",
  nickname:"",
  gender:"",
  email:"",
  phone:"",
  avatar:"",
  id: 0,
  follows: 0,
  fans: 0,
  videoCnts: 0
})
const route = useRoute()
const getUserInfo = async () => {
  console.log("元素");
  
  const id = route.params.id
  console.log(id)
  const res = await getUserInfoById({id})
  userInfo.username = res.data.userInfo.username
  userInfo.nickname = res.data.userInfo.nickname
  userInfo.gender = res.data.userInfo.gender
  userInfo.email = res.data.userInfo.email
  userInfo.phone = res.data.userInfo.phone
  userInfo.avatar = res.data.userInfo.avatar
  userInfo.id = res.data.userInfo.id
  userInfo.follows = res.data.userInfo.follows
  userInfo.fans = res.data.userInfo.fans
  userInfo.videoCnts = res.data.userInfo.videoCnts
  console.log(userInfo);
  
  document.title = userInfo.username + ' .DingVideo'
}
const Toggle = async () => {
  const user_id:number = route.params.id
  const offset:number  = 0
  // const types = radio.value
  // if (radio.value === '帖子' && userPost.value.length === 0) {
  //   const post = await queryUserPost({user_id, types, offset})
  //   userPost.value = post.info
  //   waterFallInit(columns, card_columns_posts, arrHeight, userPost)
  // } else if (radio.value === '收藏' && userCollect.value.length === 0) {
  //   const post = await queryUserPost({user_id, types, offset})
  //   userCollect.value = post.info
  //   waterFallInit(columns, card_columns_collect, arrHeight, userCollect)
  // } else if (radio.value === '点赞' && userFavorite.value.length === 0) {
  //   const post = await queryUserPost({user_id, types, offset})
  //   userFavorite.value = post.info
  //   waterFallInit(columns, card_columns_like, arrHeight, userFavorite)
  // }
  // disabled.value = false;
}
const resize = () => {
  // if (radio.value === '帖子') {
  //   resizeWaterFall(columns, card_columns_posts, arrHeight, userPost)
  // } else if (radio.value === '收藏') {
  //   resizeWaterFall(columns, card_columns_collect, arrHeight, userCollect)
  // } else if (radio.value === '点赞') {
  //   resizeWaterFall(columns, card_columns_like, arrHeight, userFavorite)
  // }
}
onMounted(async () => {
  await getUserInfo()
  await Toggle()
  resize()
})
</script>

<style scoped>
.userInfo {
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
  /* position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: red; */
}
</style>