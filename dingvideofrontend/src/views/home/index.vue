#index.vue页面
<template>
  <h2>{{ msg }}</h2>
  <h2>{{ count }}</h2>
</template>
<script setup lang="ts">
import { ref } from 'vue'

const msg = ref('今天天气好晴朗')
const count = ref(710)
</script>

<style scoped>
</style>
