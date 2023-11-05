<template>
  <div class="userInfo" v-if="userInfo.user">
    <el-row :gutter="10">
      <el-col :span="7" style="width: 250px;">
        <el-avatar :size="150" :src="userInfo.avatar"></el-avatar>
      </el-col>
      <el-col :span="7" style="width: 250px!important;">
        <h2>{{ userInfo.nickname }}</h2>
        <p>{{ userInfo.signature }}</p>
        <div class="tagArea">
          <el-tag class="ml-2" type="success" round>{{ userInfo.follows }} 关注</el-tag>
          <el-tag class="ml-2" type="info" round>{{ userInfo.fans }} 粉丝</el-tag>
          <el-tag class="ml-2" type="warning" round>{{ userInfo.videoCnts }} 作品</el-tag>
        </div>
      </el-col>
      <el-col :span="5" style="width: 100px;">
        <button class="focusOn" v-if="!checkFollow(userInfo.user.id)" @click="doFocusOn(userInfo.user.id)">关注</button>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { getUserInfoById } from "@/api/login.js";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";

const userInfo = ref({})
const route = useRoute()
const getUserInfo = async () => {
  const id = route.params.id
  console.log(id)
  const res = await getUserInfoById({id})
  userInfo.value = res.data
  document.title = res.data.username + ' .DingVideo'
}
const Toggle = async () => {
  const user_id = route.params.id
  const offset = 0
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
}
</style>