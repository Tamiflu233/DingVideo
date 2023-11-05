<template>
    <div class="login-wrapper" style="height:100vh; display: flex; align-items: center; justify-content: center; background-color: #76d7ea">
        <div style="display: flex; background-color: white; width: 60%; border-radius: 5px; overflow: hidden">
            <div @click.prevent style="flex: 1; display: flex; align-items: center; justify-content: center;">
                <img src="@/assets/img/register.png" alt="" style="width: 90%; height: 90%;">
            </div>
            <div style="flex: 1; display: flex; align-items: center; justify-content: center">
                <el-form :model="user" style="width: 80%" status-icon :rules="rules" :size="formSize" ref="ruleFormRef">
                    <div style="font-size: 25px; font-weight: bold; text-align: center; margin: 10px">欢迎注册DingVideo</div>
                    <el-form-item label-postion="left" label=" " prop="username">
                        <el-input autofocus=true size="medium" placeholder="请输入用户名" v-model="user.username" :prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item label=" " prop="password">
                        <el-input size="medium" show-password placeholder="请输入密码" v-model="user.password" :prefix-icon="Lock"></el-input>
                    </el-form-item>
                    <el-form-item label=" " prop="nickname">
                        <el-input size="medium" placeholder="请输入昵称" v-model="user.nickname" :prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item label=" " prop="email">
                        <el-input size="medium" placeholder="请输入邮箱" v-model="user.email" :prefix-icon="Message"></el-input>
                    </el-form-item>
                    <el-form-item label=" " prop="phone">
                        <el-input size="medium" placeholder="请输入手机号" v-model="user.phone" :prefix-icon="Iphone"></el-input>
                    </el-form-item>
                    <el-form-item label=" ">
                    <!-- <el-form-item label=" " prop="sidentifyMode" :rules="[{ required: false, message: '请输入验证码！', trigger: 'blur'}]"> -->
                        <div style="display: flex; margin-bottom: 10px;">
                            <el-input size="medium" style="flex: 1" placeholder="请输入验证码" v-model="sidentifyMode" clearable :prefix-icon="CircleCheck"></el-input>
                            <div style="flex: 1;" class="code" @click="refreshCode">
                            <ValidCode :identifyCode="identifyCode"></ValidCode>
                            </div>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="width: 100%; background-color: #76d7ea" @click="register">注 册</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { User } from '@element-plus/icons-vue'
import { Lock } from '@element-plus/icons-vue'
import { CircleCheck } from '@element-plus/icons-vue'
import { Message } from '@element-plus/icons-vue'
import { Iphone } from '@element-plus/icons-vue'
import { ElMessage, FormRules, FormInstance } from 'element-plus'
import { useRouter } from 'vue-router'
import { getRegister } from '@/api/login'
import { ref, onMounted, reactive } from 'vue'
import { RegisterForm } from '@/types/login'


// 获取路由器
let $router = useRouter()
// 输入框验证码
let sidentifyMode = ref('') 
// 图形验证码
let identifyCode = ref('') 
// 验证码出现的数字和字母
let identifyCodes = ref('1234567890abcdefjhijklinopqrsduvwxyz') 

// 组件挂载
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
 
// 注册
const register = () => {
    // 用户名或密码不能为空
    if(!user.username || !user.password || !user.nickname || !user.email || !user.phone) {
        ElMessage({ type: 'error', message: '标星的输入框不能为空！' })
        return
    } 
    // 验证验证码不为空
    if (!sidentifyMode.value) {
        ElMessage({ type: 'error', message: '验证码不能为空！' })
        return
    }
    // 验证验证码是否正确
    if (sidentifyMode.value != identifyCode.value) {
        ElMessage({ type: 'error', message: '验证码错误！' })
        refreshCode()
        return
    } else {
        getRegister(user).then((res) => {
            console.log(res);
            if(res.code == 200) {
                ElMessage({ type: 'success', message: '注册成功！' })           
                $router.push('/login')
            } else {
                ElMessage({ type: 'error', message: '用户名重复，请更换用户名~' })
            }
        }).catch((err) => {
            ElMessage({ type: 'error', message: err })
        })
        
    }
    
}


const user = reactive<RegisterForm>({
    username: '',
    password: '',
    nickname: '',
    email: '',
    phone: ''
})

const formSize = ref('small')
const ruleFormRef = ref<FormInstance>()

const rules = reactive<FormRules<RegisterForm>>({
  username: [
    {
        required: true, 
        message: '请输入用户名！', 
        trigger: 'blur',
    },
  ],
  password: [
    {
        required: true,
        message: '请输入密码！',
        trigger: 'blur',
    },
  ],
  nickname: [
    {
        required: true,
        message: '请输入昵称！',
        trigger: 'blur',
    },
  ],
  phone: [
    {
        required: true,
        message: '请输入手机号！',
        trigger: 'blur',
    },
    { pattern:/^1[3|4|5|8|9]{1}[0-9]{9}$/,min: 2, max: 15, message: "手机格式错误！", trigger: "blur" },
  ],  
  email: [
    {
        required: true,
        message: '请输入邮箱！',
        trigger: 'blur',
    },
    { pattern:/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,min: 2, max: 15, message: "邮箱格式错误！", trigger: "blur" },
  ],    
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
.el-form-item__error {
    font-size: 10px;
}
</style>
