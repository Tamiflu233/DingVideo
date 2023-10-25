<script setup lang="ts">
</script>
<template>
  <router-view v-slot="slotProps">
    <keep-alive>
      <component :is="slotProps.Component"></component>
    </keep-alive>
  </router-view>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
