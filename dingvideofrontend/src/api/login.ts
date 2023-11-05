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
    "phone": data?.phone
  }
});

  
// 由id获取用户信息
export const getUserInfoById: requestModel = (data?: any) => request({
  url: `${baseUrl}/info`,
  method: 'get',
  params: {
    id: data.id
  }
});

// 退出登录
export const logout: requestModel = () => request({
  url: '/quit',
  method: 'post',
});
