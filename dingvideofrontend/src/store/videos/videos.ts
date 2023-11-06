import { defineStore } from 'pinia'

//导出仓库方法
export const videoStore = defineStore("videos", {
  state: () => ({
    videosBuffer: [],
  }),
  actions: {
    setVideosBuffer(videos:any) {
      this.videosBuffer = videos;
    },
    addVideos(videos:any) {
      this.videosBuffer.push(...(videos as Array<never>));
    },
    clearVideosBuffer() {
      this.videosBuffer = [];
    },
    
  },
  persist: {
    enabled: true,
    strategies: [
      {   
        key: 'videos',
        storage: localStorage,
        paths: ['videosBuffer']
      }
    ]
  },
  
},);
