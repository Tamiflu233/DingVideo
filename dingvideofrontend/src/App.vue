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

html,body,#app,.el-container{
padding: 0px;
margin: 0px;
height: 100%;
}
::-webkit-scrollbar {
    width: 6px;
}
::-webkit-scrollbar-thumb {
    background-color: #0003;
    border-radius: 10px;
    transition: all .2s ease-in-out;
}
::-webkit-scrollbar-track {
    border-radius: 10px;
}

</style>
