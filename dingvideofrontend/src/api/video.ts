import request from '@/utils/request';
import { requestModel } from '@/types/service';
// 基础路径
const baseUrl:string = "/api/videos"
// 获取用户信息
export const getVideoDetail: requestModel = () => request({
    url: '/video',
    method: 'get',
});

export const getVideoByUserIdAndType: requestModel = (data?: any) => request({
    url: `${baseUrl}/person`,
    method: 'get',
    params: {
        userid: data.userid,
        type: data.type
    }
});
