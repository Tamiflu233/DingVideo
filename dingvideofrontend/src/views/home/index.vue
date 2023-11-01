#index.vue页面
<template>
  <div class="home-layout">
    <el-container class="home-content">
      <el-aside class="cate-sidebar" width="100px">
        <SideMenu :items="menuItems"  />
      </el-aside>
      <el-container class="main-part">
        <el-header class="head-bar">
          <NavHeader />
        </el-header>
        <el-main class="video-pageview">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">

import { MenuItem } from "@/types/sidemenu"

// 侧边菜单和顶部导航栏
const SideMenu = defineAsyncComponent(() => import("@/components/common/SideMenu.vue"))
const NavHeader = defineAsyncComponent(() => import("@/components/common/NavHeader.vue"))
// 左侧菜单分类按钮列表,是对象数组，每个对象包含icon和title两个属性
const menuItems = ref<MenuItem[]>([
  {
    icon: "house",
    title: "首页",
  },
  {
    icon: "star",
    title: "推荐",
  },
  {
    icon: "trophy",
    title: "热门",
  },
  {
    icon: "football",
    title: "体育",
  },
  {
    icon: "reading",
    title: "知识",
  },
  {
    icon: "SwitchFilled",
    title: "游戏",
  },
  {
    icon: "MagicStick",
    title: "娱乐",
  },
  {
    icon: "food",
    title: "美食",
  },
  {
    icon: "VideoCameraFilled",
    title: "电影",
  },
  {
    icon: "ShoppingBag",
    title: "时尚",
  },
  {
    icon: "promotion",
    title: "动漫",
  },
]);


</script>


<style lang="scss" scoped>
.home-layout{
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  position: absolute;
  height: 100%;
  .home-content{
    height: 100%;
  .cate-sidebar {
  // background-color: gray;
  }

  .head-bar {
    // background-color: blue;
  }
}
}

</style>
