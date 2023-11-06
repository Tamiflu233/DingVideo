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
  <div class="checkBox" @change="Toggle">
    <el-radio-group v-model="radio" size="large">
      <el-radio-button label="作品" name="post"/>
      <el-radio-button label="收藏" name="collect"/>
      <el-radio-button label="点赞" name="like"/>
    </el-radio-group>
  </div>
  <div style="margin-top: 30px; margin-left: 650px" v-if="userInfo">
    <div v-if="radio === '作品'">
      <div v-if="userPost?.length === 0">
        <el-empty description="现在还没有作品..."/>
      </div>
    </div>
    <div v-else-if="radio === '收藏'">
      <div v-if="userCollect?.length === 0">
        <el-empty description="现在还没有收藏..."/>
      </div>
    </div>
    <div v-else-if="radio === '点赞'">
      <div v-if="userLike?.length === 0">
        <el-empty description="现在还没有点赞..."/>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { getUserInfoById } from "@/api/login.js";
import {watch,onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {getVideoByUserIdAndType} from "@/api/video";

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

const radio = ref('作品')
const userPost = ref([])
const userCollect = ref([])
const userLike = ref([])
const disabled = ref(true); // 初始禁用滚动加载

const columns = ref(0)
const card_columns_posts = ref({})
const card_columns_like = ref({})
const card_columns_collect = ref({})
const arrHeight = ref([])
const Toggle = async () => {
  const userid:number = route.params.id
  const types = radio.value
  if (radio.value === '作品' && userPost.value.length === 0) {
    let type = 1
    const post = await getVideoByUserIdAndType({userid, type})
    userPost.value = post.info
    // waterFallInit(columns, card_columns_posts, arrHeight, userPost)
  } else if (radio.value === '收藏' && userCollect.value.length === 0) {
    let type = 2
    const post = await getVideoByUserIdAndType({userid, type})
    userCollect.value = post.info
    // waterFallInit(columns, card_columns_collect, arrHeight, userCollect)
  } else if (radio.value === '点赞' && userLike.value.length === 0) {
    let type = 3
    const post = await getVideoByUserIdAndType({userid, type})
    userLike.value = post.info
    // waterFallInit(columns, card_columns_like, arrHeight, userFavorite)
  }
  // disabled.value = false;
}
const resize = () => {
}
watch(
  () => route.params.id,
  async (newUserId) => {
    await getUserInfo()
    await Toggle()
    resize()
    
  },
  { immediate: true }
);
onMounted(async () => {
  await getUserInfo()
  await Toggle()
  resize()
})
</script>

<style scoped>
.userInfo {
  display: flex;
  justify-content: center;
  align-items: center;
  /*display: flex;*/
  /*align-items: center;*/
  /*justify-content: center;*/
  /*!*margin: auto;*!*/
  /*!*top: 0;*!*/
  /*!*bottom: 0;*!*/
  /*!*left: 0;*!*/
  /*!*right: 0;*!*/
  /*margin-left: ;*/
  margin-top: 20px;
  margin-left: 650px;
  
  
}

.checkBox {
  margin-top: 50px;
  position: relative;
  left: 25%;
}

.tagArea {
  width: 310px;
}

.tagArea .ml-2 {
  margin-right: 10px;
}

.focusOn {
  align-items: center;
  justify-content: center;
  width: 96px;
  height: 40px;
  line-height: 18px;
  font-weight: 600;
  font-size: 16px;
  cursor: pointer;
  background-color: red;
  border-radius: 1000px;
  color: #fff;
  border-color: transparent;
  margin-top: 1rem;
}

.focusOn:hover {
  background-color: #fd5656;
}
</style>