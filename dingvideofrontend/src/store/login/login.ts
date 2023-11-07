import { defineStore } from 'pinia'

//导出仓库方法
export const loginStore = defineStore("login", {
  state: () => ({
    userInfo: null,
    token: null,
  }),
  actions: {
    setUserInfo(userInfo:any) {
      this.userInfo = userInfo;
    },
    setToken(token:any) {
      this.token = token;
    },
    clearUserInfo() {
      this.userInfo = null;
    },
    clearToken() {
      this.token = null;
    },
    existToken() {
      return this.token !== null;
    }
  },
  persist: {
    enabled: true,
    strategies: [
      {   
        key: 'login',
        storage: localStorage,
        paths: ['userInfo', 'token']
      }
    ]
  },
  
},);
