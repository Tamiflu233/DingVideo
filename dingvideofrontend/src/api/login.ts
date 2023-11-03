import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 基础路径
const baseUrl:string = "/api/user"

// 登录
export const getLogin: requestModel = (data?: any) => request({
  url: `${baseUrl}/login`,
  method: 'post',
  data: {
    "username": data?.username,
    "password": data?.password
  }
});

// 注册
export const getRegister: requestModel = (data?: any) => request({
  url: `${baseUrl}/register`,
  method: 'post',
  data: {
    "username": data?.username,
    "password": data?.password,
    "nickname": data?.nickname,
    "email": data?.email,
    "phone": data?.phone,
    "avatar": data?.avatar
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
