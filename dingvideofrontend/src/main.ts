import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from "element-plus/es/locale/lang/zh-cn";//国际化
import pinia from '@/store/index';
import { Swipe, SwipeItem } from 'vant';
import '@vant/touch-emulator';
// 2. 引入组件样式
import 'vant/lib/index.css';
const app = createApp(App);



app.use(ElementPlus,{locale: zhCn}).use(router).use(Swipe).use(SwipeItem).use(pinia).mount('#app')

//全局注册图标组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
