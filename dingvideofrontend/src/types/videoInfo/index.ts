import {User} from '@/types/user';
export interface VideoInfo {
  poster: string
  src: string
  title?: string
  author?: string
  like?: number
  duration?: number
  publishTime?: string
}
export interface VideoDetail {
  videoId? : string
  videoUrl : string
  coverUrl : string
  title : string
  description: string
  category?: string
  likes?: number
  collections?: number
  comments?: number
  isLiked?: boolean
  useId?: boolean
  createTime: string
  updateTime?: string
  user?: User

}