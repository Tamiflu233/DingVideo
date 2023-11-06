import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import {globalStore} from '@/store/global/global';
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
    children: [
      {
        path: '',
        name: 'RecommendVideos',
        component: () => import('@/views/home/RecommendVideos/RecommendVideos.vue'),
      },
      {
        path: 'category',
        name: 'CategoryVideos',
        component: () => import('@/views/home/Category/CategoryVideos.vue'),
      }
    ],
    meta: {
      title: '首页',
    },
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/login.vue'),
    meta: {
      title: '登录',
    },
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/login/register.vue'),
    meta: {
      title: '注册',
    },
  },
  {

    path: '/test',
    name: 'test',
    component: () => import('@/views/videotest/index.vue'),
    meta: {
      title: '视频测试',
    },
  },
  {

    path: '/swipe',
    name: 'swipe',
    component: () => import('@/views/swipetest/index.vue'),
    meta: {
      title: '轮播测试',
    },
  },
  {

    path: '/detail/:kind/:index',
    name: 'detail',
    component: () => import('@/views/detail/VideoDetail.vue'),
    meta: {
      title: '视频观看',
    },
  },
  {

    path: '/user/index/:id',
    name: 'user',
    component: () => import('@/views/user/index.vue'),
    meta: {
      title: '个人页面',
    },
  },
]

const router = createRouter({
  history,
  routes
})
router.beforeEach((to, from, next) => {
  const store = globalStore();
  document.title = to.meta.title as string
  // 根据当前url更新side menu的index
  store.updateSideBarIndexFromUrl(to.path,to.query);
  next();
})
export default router
