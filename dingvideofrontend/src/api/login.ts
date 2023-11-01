import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 获取用户信息
import { LoginForm } from '@/types/login';
const baseUrl:string = "/api/user"
export const getLogin: requestModel = (data?: any) => request({
  url: `${baseUrl}/login`,
  method: 'post',
  data: {
    "username": data?.username,
    "password": data?.password
  }
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
