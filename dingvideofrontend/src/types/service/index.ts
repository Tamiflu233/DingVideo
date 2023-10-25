// /src/types/service/index.ts

interface resModel {
  code:number
  msg:string
  data:any
  [propname:string]:any
}

export interface requestModel {
  <T>(data?: T): Promise<resModel>
}
