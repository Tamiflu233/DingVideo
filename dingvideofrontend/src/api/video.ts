import request from '@/utils/request';
import { requestModel } from '@/types/service';

// 基础路径
const baseUrl:string = "/api/videos"

// 获取分类视频
export const getCategoryVideos: requestModel = (data?: any) => request({
    url: `${baseUrl}/category`,
    method: 'get',
    params: {
        kind: data.kind
    }
});
// 获取推荐视频
export const getHomeVideos: requestModel = (data?: any) => request({
    url: `${baseUrl}/home`,
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
