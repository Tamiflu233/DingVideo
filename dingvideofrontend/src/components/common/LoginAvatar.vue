<template>
  <div class="login-avatar">
    <el-dropdown @command="handleCommand">
      <el-avatar
        :src="store.userInfo.avatar"
      />
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="center">个人中心</el-dropdown-item>
        <el-dropdown-item command="exit">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  </div>
</template>

<script lang="ts" setup>
import 'iconify-icon';
import { ref } from 'vue';
import { loginStore } from '@/store/login/login.ts'
// 登录状态管理
const store = loginStore()
const props = defineProps({
  // iconSrc: {
  //   type: [String, Object],
  //   required: true,
  // },
  // iconTgt: {
  //   type: [String, Object],
  //   required: true,
  // },
  // colorSrc: {
  //   type: [String, Object],
  //   required: true,
  // },
  // colorTgt: {
  //   type: [String, Object],
  //   required: true,
  // },
  // isInitiallyLiked: {
  //   type: Boolean,
  //   default: false,
  // }
})
function handleCommand(payload:any){
  if(payload === "center") {

  } else if (payload === "exit") {
    store.clearToken()
    store.clearUserInfo()
    ElMessage({ type: 'success', message: '退出登录！' }) 

  }
  
}
const emit = defineEmits(['like'])








</script>

<style scoped>
.login-avatar {
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
</style>
