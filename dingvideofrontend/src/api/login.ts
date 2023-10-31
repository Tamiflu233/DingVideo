import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 获取用户信息
export const getLogin: requestModel = () => request({
    url: '/login',
    method: 'get',
  });

  
// 获取用户信息
export const getUserInfo: requestModel = () => request({
  url: '/info',
  method: 'get',
  
});

// 退出登录
export const logout: requestModel = () => request({
  url: '/quit',
  method: 'post',
});
