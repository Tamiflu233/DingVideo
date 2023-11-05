<template>
  <div class="nav-header-wrapper">
    <div class="search-input">
      <el-input
      v-model="inputText"
      placeholder="搜索您感兴趣的内容"
      class="input-with-select"
    >
    
      <template #append>
        <el-button :icon="Search" />
      </template>
    </el-input>
    </div>
    <div class="login-btn">
      <el-button color="#FF7542" type="login" :icon="User" @click="login">
        
        登录
      </el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { User,Search } from '@element-plus/icons-vue'
import { ref } from 'vue'
const inputText = ref('')
import { useRouter } from "vue-router";
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
    margin-right: 20px;
    .input-with-select .el-input-group__prepend {
      background-color: var(--el-fill-color-blank);
    }
  }
  .login-btn {

  }
}

.el-button--login {
  color: #fff;
}


</style>