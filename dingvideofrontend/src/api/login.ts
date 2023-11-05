import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 获取用户信息
const baseUrl:string = "/api/user"
export const getLogin: requestModel = () => request({
    url: '/login',
    method: 'get',
  });

  
// 由id获取用户信息
export const getUserInfoById: requestModel = (id: bigint) => request({
  url: `${baseUrl}/info`,
  method: 'get',
  params: {
    id: id
  }
});

// 退出登录
export const logout: requestModel = () => request({
  url: '/quit',
  method: 'post',
});
