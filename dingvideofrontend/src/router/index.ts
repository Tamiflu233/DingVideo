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
  }
]

const router = createRouter({
  history,
  routes
})

export default router
