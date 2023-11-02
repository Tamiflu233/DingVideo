<template>
  <div class="side-menu-wrapper">
    <div class="menu-icon">
      <el-image :src="logo"></el-image>
    </div>
    <div class="menu-item" v-for="(item, index) in items" :key="index" :class="{ 'selected': selected === index }" @click="selectItem(index)">
      <div class="menu-item-wrapper">
        <el-icon class="menu-item-icon" :size="25">
        <component :is="item.icon"></component>
        </el-icon >
        <div class="menu-item-title">
          {{item.title}}
        </div>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { MenuItem } from "@/types/sidemenu"
import { useRouter } from "vue-router";
// 路由控制
const router = useRouter();
// 应用图标链接：
const logo = ref("https://dasex101-random-learning.oss-cn-shanghai.aliyuncs.com/image/dingvideoLOGO.png")
// 接受自定义的分类按钮项
defineProps({
  items: {
    type: Array as PropType<MenuItem[]>,
    required: true
  }
})
// 当前选择的分类按钮项的索引
const selected = ref(0)
// 分类索引表
let cateMap:string[] = [
  'home',
  'recommend',
  'hot',
  'sports',
  'knowledge',
  'game',
  'entertainment',
  'food',
  'movie',
  'fashion',
  'animation'
]
// 获取当前点击的按钮索引
function selectItem(index: number) {
  selected.value = index
  if(index === 0) {
    // 跳转到首页
    router.push({path: '/'})
  } else {
    // 跳转到分类页面
    router.push({path: '/home/category',query:{"kind":cateMap[index]}})
  }
}
</script>
<style lang="scss" scoped>
.side-menu-wrapper {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
  justify-content: flex-start;
  align-items: center;
  height: 100%;
  overflow-y: scroll;
  // background-color: red;
  
  .menu-item{
    flex: 1;
    .menu-item-wrapper{
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      margin: 0 auto;
      height: 80px;
      width: 80px;
      border-radius:12px;
      &:hover {
			color: #06B0FB;
      background-color: rgba($color: #06B0FB, $alpha: 0.03);
      cursor: pointer;
		}
    .menu-item-title {
      user-select: none;
    }
    }
    
  }
  
}
// 选中的分类按钮项样式
.selected {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 0 auto;
  height: 80px;
  width: 80px;
  border-radius:12px;
  color: #06B0FB;
  background-color: rgba($color: #06B0FB, $alpha: 0.03);
  cursor: pointer;
}
/* 隐藏滚动条 */
.side-menu-wrapper::-webkit-scrollbar {
  width: 0;
  height: 0;
}
</style>