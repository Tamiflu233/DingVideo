import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'


const history = createWebHistory()
const routes: Array<RouteRecordRaw> = [
  {
  //路由初始指向

    path: '/',
    redirect: '/home',
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/home/index.vue'),
    meta: {
      title: '首页',
      keepAlive: true,
    },
  },
  {
    path: '/calculate',
    name: 'calculate',
    component: () => import('@/views/home/test.vue'),
    meta: {
      title: '计算器',
      keepAlive: true,
    },
  },
  {
    path: '/video',
    name: 'video',
    component: () => import('@/views/home/video.vue'),
    meta: {
      title: '视频播放',
      keepAlive: true,
    },
  }
]

const router = createRouter({
  history,
  routes
})

export default router
