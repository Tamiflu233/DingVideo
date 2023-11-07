<template>
  <div class="nav-header-wrapper">
    <div class="search-input">
      <el-input
      v-model="inputText"
      placeholder="搜索您感兴趣的内容"
      class="input-with-select"
    >
    
      <template #append>
        <el-button :icon="Search" style="color: #ffffff;"/>
      </template>
    </el-input>
    </div>
    <div class="login-btn">
      <el-button style="border-radius: 195px;height: 35px;" v-if="store.token == null" color="#06B0FB" type="login" :icon="User" @click="login">
        登录
      </el-button>
      <LoginAvatar v-else >
        
      </LoginAvatar>
    </div>
  </div>
</template>

<script setup lang="ts">
import { User,Search } from '@element-plus/icons-vue'
import { ref } from 'vue'
const inputText = ref('')
import { useRouter } from "vue-router";
import { loginStore } from '@/store/login/login.ts'
const LoginAvatar = defineAsyncComponent(() => import("@/components/common/LoginAvatar.vue"))
// 登录状态管理
const store = loginStore()
// 路由控制
const router = useRouter();
function login() {
  router.push("/login")
}
</script>
<style lang="scss" scoped>
.nav-header-wrapper {
  height: 65px;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  .search-input {
    height: 35px;
    border-radius: 25px;
    box-shadow: 5px 5px 10px 5px rgba(0, 0, 0, 0.06);
    // background-color: #d4e4f8;
    margin-right: 20px;
    .input-with-select .el-input-group__prepend {
      background-color: var(--el-fill-color-blank);
    }
  }
  .login-btn {

  }
}

:deep(.el-input__inner) {
  &::placeholder {
    font-size: 14px;
    line-height: 35px;
  }
}

:deep(.el-input__wrapper) {
  background-color: transparent;/*覆盖原背景颜色，设置成透明 */
  border-radius: 25px;
  border: 0;
  box-shadow: 0 0 0 0px;
}

:deep(.el-input-group__append) {
  height: 35px;
  // background: #87b8e8;
  background: #06b0fb;
  border-radius: 0 25px 25px 0;
  border: 0;
  box-shadow: 0 0 0 0px;
}

.el-button--login {
  color: #fff;
}


</style>