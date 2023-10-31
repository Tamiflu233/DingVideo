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
    path: '/demo',
    name: 'demo',
    component: () => import('@/views/home/demo.vue'),
    meta: {
      title: '测试',
      keepAlive: true,
    },
  }
]

const router = createRouter({
  history,
  routes
})

export default router
