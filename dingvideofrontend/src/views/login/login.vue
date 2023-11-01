<template>
    <div class="login-wrapper" style="height:100vh; display: flex; align-items: center; justify-content: center; background-color: #76d7ea">
        <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
            <div style="flex: 1; display: flex; align-items: center; justify-content: center;">
                <img src="@/assets/img/login.png" alt="" style="width: 90%; height: 90%;">
            </div>
            <div style="flex: 1; display: flex; align-items: center; justify-content: center">
                <el-form :model="user" style="width: 80%">
                    <div style="font-size: 20px; font-weight: bold; text-align: center; margin: 10px">欢迎登录DingVideo</div>
                    <el-form-item prop="username">
                        <el-input size="medium" placeholder="请输入用户名" v-model="user.username" :prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" show-password placeholder="请输入密码" v-model="user.password" :prefix-icon="Lock"></el-input>
                    </el-form-item>
                    <el-form-item prop="validCode">
                        <div style="display: flex">
                            <el-input size="medium" style="flex: 1" placeholder="请输入验证码" v-model="sidentifyMode" clearable :prefix-icon="CircleCheck"></el-input>
                            <div style="flex: 1;" class="code" @click="refreshCode">
                            <ValidCode :identifyCode="identifyCode"></ValidCode>
                            </div>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="width: 100%; background-color: #76d7ea" @click="login">登 录</el-button>
                    </el-form-item>
                    <div style="display: flex">
                        <div style="flex: 1; font-size: 10px; text-align: left;">
                            还没有账号？请<span style="color: #76d7ea; cursor: pointer">注册</span>
                        </div>
                        <div style="flex: 1; text-align: right; font-size: 10px;">
                            <span style="color: #76d7ea; cursor: pointer">忘记密码？</span>
                        </div>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { User } from '@element-plus/icons-vue'
import { Lock } from '@element-plus/icons-vue'
import { CircleCheck } from '@element-plus/icons-vue'
import ValidCode from '@/components/common/ValidCode.vue'
import { ElMessage } from 'element-plus'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getLogin } from '@/api/login'

//获取路由器
let $router = useRouter()
let sidentifyMode = ref('') //输入框验证码
let identifyCode = ref('') //图形验证码
let identifyCodes = ref('1234567890abcdefjhijklinopqrsduvwxyz') //验证码出现的数字和字母
 
//组件挂载
onMounted(() => {
  identifyCode.value = ''
  makeCode(identifyCodes.value, 4)
})
 
// 生成随机数
const randomNum = (min:number, max:number) => {
  max = max + 1
  return Math.floor(Math.random() * (max - min) + min)
}
// 随机生成验证码字符串
const makeCode = (o:String, l:number) => {
  for (let i = 0; i < l; i++) {
    identifyCode.value += o[randomNum(0, o.length)]
  }
}
// 更新验证码
const refreshCode = () => {
  identifyCode.value = ''
  makeCode(identifyCodes.value, 4)
}
//登录
const login = () => {
    // 用户名或密码不能为空
    if(!user.username || !user.password) {
        ElMessage({ type: 'error', message: '用户名或密码不能为空！' })
        return
    }
    //验证验证码不为空
    if (!sidentifyMode.value) {
        ElMessage({ type: 'error', message: '验证码不能为空！' })
        return
    }
    //验证验证码是否正确
    if (sidentifyMode.value != identifyCode.value) {
        ElMessage({ type: 'error', message: '验证码错误！' })
        refreshCode()
        return
    } else {
        getLogin(user).then((res) => {
            console.log(res);
            if(res.code == 200) {
                ElMessage({ type: 'success', message: '登录成功！' })           
                localStorage.setItem('token', res.data.token)
                $router.push('/home')
            } else {
                ElMessage({ type: 'error', message: '用户名或密码错误！' })
            }
            

        }).catch((err) => {
            ElMessage({ type: 'error', message: err })
            
        })
        
    }
    
}



const user = reactive({
    username: '',
    password: '',
    validCode: ''
})


</script>

<style>
.login-wrapper {
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  position: absolute;
}
.el-input__inner::placeholder {
    font-size: 10px;
}
.code {
    width: 100%;
    height: 32px;
}
</style>
