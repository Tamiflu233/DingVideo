#index.vue页面
<template>
  <h2>{{ msg }}</h2>
  <h2>{{ count }}</h2>
  <button @click="count++">点我count++</button>
    <router-link to="/calculate">
      <button>点我跳转到计算器</button>
    </router-link>
  </template>
<script setup lang="ts">
import { ref } from 'vue'

const msg = ref('10.26 祝程哥新婚快乐！祝崔里青生日快乐！')
const count = ref(710)
</script>

<style scoped>
</style>
