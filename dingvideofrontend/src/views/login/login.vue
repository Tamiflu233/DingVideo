<template>
    <div style="height:100vh; display: flex; align-items: center; justify-content: center; background-color: #76d7ea">
        <div style="display: flex; background-color: white; width: 50%; border-radius: 5px; overflow: hidden">
            <div style="flex: 1">
                <img src="@/assets/img/login.png" alt="" style="width: 90%">
            </div>
            <div style="flex: 1; display: flex; align-items: center; justify-content: center">
                <el-form :model="user" style="width: 80%">
                    <div style="font-size: 20px; font-weight: bold; text-align: center; margin: 20px">欢迎登录DingVideo</div>
                    <el-form-item prop="username">
                        <el-input size="medium" placeholder="请输入用户名" v-model="user.username" :prefix-icon="User"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input size="medium" show-password placeholder="请输入密码" v-model="user.password" :prefix-icon="Lock"></el-input>
                    </el-form-item>
                    <el-form-item prop="validCode">
                        <div style="display: flex">
                            <el-input size="medium" style="flex: 1" :prefix-icon="CircleCheck"></el-input>
                            <div style="flex: 1">
                            1234
                            </div>
                        </div>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="width: 100%; background-color: #76d7ea">登 录</el-button>
                    </el-form-item>
                    <div style="display: flex">
                        <div style="flex: 1">
                            还没有账号？请<span style="color: #76d7ea; cursor: pointer">注册</span>
                        </div>
                        <div style="flex: 1; text-align: right">
                            <span style="color: #76d7ea; cursor: pointer">忘记密码？</span>
                        </div>
                    </div>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { User } from '@element-plus/icons-vue'
import { Lock } from '@element-plus/icons-vue'
import { CircleCheck } from '@element-plus/icons-vue'

export default {
    name: 'Login',
    data() {
        return {
            user: {
                username: '',
                password: ''
            }
        }
    },
    created() {
        
    },
    methods: {
        login() {
            // TODO: Implement login functionality
        }
    }
}
</script>

<style>
/* Add your styles here */
</style>
