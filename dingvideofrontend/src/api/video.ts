import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 获取用户信息
export const getVideoDetail: requestModel = () => request({
    url: '/video',
    method: 'get',
});
