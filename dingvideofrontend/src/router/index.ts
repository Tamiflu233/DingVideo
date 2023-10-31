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
      title: '登录',
      keepAlive: true,
    },
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/login.vue'),
    meta: {
      title: '登录',
      keepAlive: true,
    },
  },
  {

    path: '/test',
    name: 'test',
    component: () => import('@/views/videotest/index.vue'),
    meta: {
      title: '视频测试',
      keepAlive: true,
    },
  },
  {

    path: '/swipe',
    name: 'swipe',
    component: () => import('@/views/swipetest/index.vue'),
    meta: {
      title: '轮播测试',
      keepAlive: true,
    },
  }
]

const router = createRouter({
  history,
  routes
})

export default router
