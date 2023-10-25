import { defineStore } from 'pinia'
//导出仓库方法
// export const loginStore = defineStore("login", {
//   state: () => ({
//     count: 0,
//   }),
//   getters: {

//   },
//   actions: {
//     //修改vccode的值
//     changeCount(value:number){
//         console.log('---loginStore---', value);
//         this.count=value
//     },
//   },
//   //持久化
//   persist:{
//     enabled:true,
//     strategies:[
//         {   
//             key:'login',//存储的key值
//             storage:localStorage,//存储的位置
//             paths:['count'] //默认持久化state的全部，paths指定持久化的对象
//         }
//     ]
//   },
// })
