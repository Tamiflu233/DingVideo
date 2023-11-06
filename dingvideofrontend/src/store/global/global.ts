import { defineStore } from 'pinia'

//全局控制变量
export const globalStore = defineStore("global", {
  state: () => ({
    sideBarIndex: 0,
  }),
  actions: {
    setSideBarIndex(index:number) {
      this.sideBarIndex = index;
    },
    
    initSideBarIndex() {
      this.sideBarIndex = 0;
    },
    updateSideBarIndexFromUrl(url: string,query: Object) {
      // 根据 URL 更新 sideBarIndex
      // 这里需要你自己实现逻辑
      if(url ==="/home") {
        this.setSideBarIndex(0);
      } else if(url === "/home/category") {
        let kind:string = query["kind"] as string;
        if(kind !== null) {
          if(kind === "recommend") {
            this.setSideBarIndex(1);
          } else if(kind === "hot") {
            this.setSideBarIndex(2);
          }else if(kind === "sports") {
            this.setSideBarIndex(3);
          }else if(kind === "knowledge") {
            this.setSideBarIndex(4);
          }else if(kind === "game") {
            this.setSideBarIndex(5);
          }else if(kind === "entertainment") {
            this.setSideBarIndex(6);
          }else if(kind === "food") {
            this.setSideBarIndex(7);
          }else if(kind === "movie") {
            this.setSideBarIndex(8);
          }else if(kind === "fashion") {
            this.setSideBarIndex(9);
          }else if(kind === "animation") {
            this.setSideBarIndex(10);
          }
        }
      }
      
    },
    
  },
  persist: {
    enabled: true,
    strategies: [
      {   
        key: 'global',
        storage: localStorage,
        paths: ['sideBarIndex']
      }
    ]
  },
  
},);
