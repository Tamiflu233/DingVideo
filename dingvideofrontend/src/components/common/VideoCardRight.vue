<template>
  <div class="comment-card">
    <div class="info" style="width: 500px;margin-top: 10px;">
      <!-- 卡片头部 -->
      <el-row style="align-items: center;width: 500px;">
        <a :href="`/user/index/${videoInfo.user?.id}`">
          <el-avatar :size="70" :src="videoInfo.user?.avatar" size="large"></el-avatar>
        </a>
        <!--                        <div class="username">{{ }}</div>-->
        <div class="username">{{ videoInfo.user.username }}</div>
        <!--                        <button @click="cancelFocusOn(detail.user.id)" class="focusOn" v-if="checkFollow(detail.user.id)">已关注-->
        <button class="focusOn"  v-if="isfocused" @click="doFocus">已关注
        </button>
        <button class="focusOn" v-else @click="doFocus">关注</button>
      </el-row>
      <!-- 卡片头部结束 -->
      <div class="main-content">
        <!-- 卡片内容 -->
        <el-row style="margin-top: 20px;">
          <h2>{{ videoInfo.title }}</h2>
          <!-- <h2>22</h2> -->
        </el-row>
        <el-row>
          <div class="content">{{ videoInfo.description }}</div>
          <!-- <div class="content">33</div> -->
        </el-row>
        <el-row>
          <time class="time">{{ publishTime }}</time>
          <!-- <time class="time">44</time> -->
        </el-row>
        <!-- 卡片内容结束 -->
        <hr class="sperator"/>
        <!--                         评论区 -->
        <div class="comments" v-if="comments" v-infinite-scroll="load" :infinite-scroll-disabled="disabled">
          <el-empty class="empty-hint" description="现在还没有评论" v-if="comments.length === 0"/>
          <div v-else class="commentBox">
            <div class="commentTitle" style="margin-bottom: 5px;">共{{ detail.commentCount }}条评论</div>
            <div v-for="item in comments" :key="item.id">
              <el-row :gutter="20">
                <el-col :span="2.5">
                  <a :href="`/user/index/${item.user.id}`">
                    <el-avatar :src="item.user.avatar" :size="30"></el-avatar>
                  </a>
                </el-col>
                <el-col :span="20" style="font-size: 14px">
                  <div style="color:#33333399;">{{ item.user.username }}</div>
                  <div style="color:#333333;margin-top: 2px;margin-bottom: 5px;">{{ item.content }}</div>
                  <time class="time">{{ item.createTime }}</time>
                  <el-icon style="float: right;font-size: medium" @click="commentMain(item)">
                    <ChatRound/>
                  </el-icon>
                </el-col>
                <el-col style="margin-top: 5px;">
                  <div v-for="reply in item.replies" :key="reply.id" style="margin-left: 30px">
                    <!-- 渲染子评论的内容 -->
                    <el-row :gutter="20">
                      <el-col :span="2.5">
                        <a :href="`/user/index/${reply.user.id}`">
                          <el-avatar :src="reply.user.avatar" :size="25"></el-avatar>
                        </a>
                      </el-col>
                      <el-col :span="20" style="font-size: 12px">
                        <div style="color:#33333399;">{{ reply.user.username }}</div>
                        <div style="color:#333333;margin-top: 2px;margin-bottom: 10px;">{{ reply.content }}</div>
                        <time class="time">{{ reply.createTime }}</time>
                      </el-col>
                    </el-row>
                  </div>
                  <div class="more" @click="loadReply(item)" v-if="item.replyCount > 0">展开{{
                      item.replyCount
                    }}条回复
                  </div>
                </el-col>
              </el-row>
              <el-divider/>
            </div>
          </div>
        </div>
      </div>
      
    </div>
    <div class="bottomArea">
      <!-- 评论区结束 -->
      <el-divider/>
      <div class="buttonArea">
        <el-row class="button-row">
          <el-button link class="warp" @click="doSomething('like', videoInfo)" :disabled="review">
            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
              <path d="M621.674667 408.021333c16.618667-74.24 28.224-127.936 34.837333-161.194666C673.152 163.093333 629.941333 85.333333 544.298667 85.333333c-77.226667 0-116.010667 38.378667-138.88 115.093334l-0.586667 2.24c-13.728 62.058667-34.72 110.165333-62.506667 144.586666a158.261333 158.261333 0 0 1-119.733333 58.965334l-21.909333 0.469333C148.437333 407.808 106.666667 450.816 106.666667 503.498667V821.333333c0 64.8 52.106667 117.333333 116.394666 117.333334h412.522667c84.736 0 160.373333-53.568 189.12-133.92l85.696-239.584c21.802667-60.96-9.536-128.202667-70.005333-150.186667a115.552 115.552 0 0 0-39.488-6.954667H621.674667z"
                    :fill="'#dbdbdb'" p-id="4335">
              </path>
            </svg>
            <!--                            <el-text size="large" tag="b" type="info">{{ detail.likeCount }}</el-text>-->
            <el-text size="large" tag="b" type="info" style="margin-left: 3px;margin-top: 3px">999</el-text>
          </el-button>
          <el-button link class="warp" @click="doSomething('collect', videoInfo)" :disabled="review">
            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
              <path d="M335.008 916.629333c-35.914667 22.314667-82.88 10.773333-104.693333-25.557333a77.333333 77.333333 0 0 1-8.96-57.429333l46.485333-198.24a13.141333 13.141333 0 0 0-4.021333-12.864l-152.16-132.586667c-31.605333-27.52-35.253333-75.648-8.234667-107.733333a75.68 75.68 0 0 1 51.733333-26.752L354.848 339.2c4.352-0.362667 8.245333-3.232 10.026667-7.594667l76.938666-188.170666c16.032-39.2 60.618667-57.92 99.52-41.461334a76.309333 76.309333 0 0 1 40.832 41.461334l76.938667 188.16c1.781333 4.373333 5.674667 7.253333 10.026667 7.605333l199.712 16.277333c41.877333 3.413333 72.885333 40.458667 69.568 82.517334a76.938667 76.938667 0 0 1-26.08 51.978666l-152.16 132.586667c-3.541333 3.082667-5.141333 8.074667-4.021334 12.853333l46.485334 198.24c9.621333 41.013333-15.36 82.336-56.138667 92.224a75.285333 75.285333 0 0 1-57.525333-9.237333l-170.976-106.24a11.296 11.296 0 0 0-12.010667 0l-170.986667 106.24z"
                    :fill="'#dbdbdb'" p-id="4085">
              </path>
            </svg>
            <el-text size="large" tag="b" type="info" style="margin-left: 3px;margin-top: 3px">219</el-text>
          </el-button>
          <el-button link class="warp" @click="clearReply">
            <svg t="1698833950122" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4084" width="30" height="30">
              <path d="M512 85.333333c235.637333 0 426.666667 191.029333 426.666667 426.666667S747.637333 938.666667 512 938.666667a424.778667 424.778667 0 0 1-219.125333-60.501334 2786.56 2786.56 0 0 0-20.053334-11.765333l-104.405333 28.48c-23.893333 6.506667-45.802667-15.413333-39.285333-39.296l28.437333-104.288c-11.008-18.688-18.218667-31.221333-21.802667-37.909333A424.885333 424.885333 0 0 1 85.333333 512C85.333333 276.362667 276.362667 85.333333 512 85.333333z m-102.218667 549.76a32 32 0 1 0-40.917333 49.216A223.178667 223.178667 0 0 0 512 736c52.970667 0 103.189333-18.485333 143.104-51.669333a32 32 0 1 0-40.906667-49.216A159.189333 159.189333 0 0 1 512 672a159.189333 159.189333 0 0 1-102.218667-36.906667z"
                    :fill="'#dbdbdb'" p-id="4537">
              </path>
            </svg>
            <!--                            <el-text size="large" tag="b" type="info">{{ detail.commentCount }}</el-text>-->
            <el-text size="large" tag="b" type="info">999</el-text>
          </el-button>
        </el-row>
      </div>
      <el-input
          v-model="content" class="comment-input my" type="text" placeholder="说点什么..." ref="commentInput"
          :prefix-icon="Edit" @keyup.enter="sendComment(videoInfo, to)" clearable style="margin-top: 5px"
          :disabled="review"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, onUnmounted,ref } from 'vue'
import { VideoDetail } from "@/types/videoInfo";
import timeUtil from '@/utils/date';
/* 日期显示 */
const publishTime = computed(() => {
  return timeUtil(props.videoInfo.createTime)
})
const isfocused = ref(false)
function doFocus() {
  isfocused.value = !isfocused.value
}
const props = defineProps({
  review: {
    type: Boolean,
    default: false
  },
  videoInfo: {
    type: Object as PropType<VideoDetail>,
    required: true
  },
})

onMounted(() => {
})
const comments = ref([])
// 加载评论
const disabled = ref(true)
const load = async () => {
  // disabled.value = true
  // const offset = comments.value.length
  // const id = props.detail.id
  // const res1 = await getComment({id, offset})
  // const data = res1.info
  // if (data.length !== 0) {
  //   disabled.value = false
  //   comments.value = [...comments.value, ...data]
  // } else {
  //   disabled.value = true
  // }
}
</script>

<style scoped>
.comment-card {
  position: relative;
  height: 100%;
  width: 500px;
  padding: 20px;
}
.banner {
  height: 800px;
  width: 1000px;
  border-radius: 0.8rem;
  margin-top: 10px;
  margin-bottom: 10px;
}


.username {
  margin-left: 20px;
  color: rgb(136, 136, 136);
  font-size: 18px;
}
.focusOn {
  width: 90px;
  height: 40px;
  position: absolute;
  right: 10px;
  padding: 0.6rem 0.8rem;
  color: white;
  background-color: red;
  border: 0;
  border-radius: 2.0rem;
}

.focusOn:hover {
  background-color: #fd5656;
}
.time {
  margin: 20px 0 10px 0px;
  font-size: 14px;
  line-height: 120%;
  color: #9F9F9F;
}
.sperator {
  border-color: rgba(228, 225, 225, 0.2);
}
.button-row {
  margin-bottom: 10px;
}
.empty-hint {
  
}
.bottomArea {
  position: absolute;
  bottom: 60px;
  left: 20px;
  right: 20px;
}
</style>