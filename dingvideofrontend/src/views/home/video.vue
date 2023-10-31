<template>
    <div class="small-videl-detail">
        <div class="van_swipe">
            <!--vant van-swipe 滑动组件 -->
            <van-swipe :autoplay="400000" :show-indicators="false" @change="onChange" vertical>
                <van-swipe-item v-for="(item, index) in videoList" :key="index" class="product_swiper">
                    <div class="video_container">
                        <!--video属性
                            webkit-playsinline ios 小窗播放，使视频不脱离文本流，安卓则无效
                            微信内置x5内核，
                            x5-video-player-type="h5" 启用H5播放器,是wechat安卓版特性，添加此属性，微信浏览器会自动将视频置为全屏
                            x5-video-player-fullscreen="true" 全屏设置，设置为 true 是防止横屏
                            x5-video-orientation 控制横竖屏 landscape 横屏，portrain竖屏； 默认portrain
                            x5-playsinline="" 使安卓实现h5同层播放，实现与ios同样效果，但兼容部分机型
                            poster：封面
                            src：播放地址
                          -->
                        <video class="video_box" width="100%" height="100%" webkit-playsinline="true" x5-playsinline=""
                               playsinline preload="auto" :poster="item.url" :src="item.cover" :playOrPause="playOrPause"
                               x-webkit-airplay="allow" x5-video-orientation="portrait" @click="pauseVideo" @ended="onPlayerEnded($event)"
                               loop="loop">
                        </video>
                        <!-- 封面 -->
                        <img v-show="isVideoShow" class="play" @click="playvideo" :src="item.url" />
                        <!-- 播放暂停按钮 -->
                        <van-icon name="pause-circle-o" color="white" v-show="iconPauseShow" size="90" class="icon_play" @click="pauseVideo"/>
                        <van-icon name="play-circle-o" color="white"  v-show="iconPlayShow" size="90" class="icon_play" @click="playvideo"/>
                    </div>
                </van-swipe-item>
            </van-swipe>
            <!--  右侧标签 -->
            <div class="right_tabs">
                <div>
                    <van-icon :name="likeName" color="#ee0a24"  size="40" badge="100+" @click="likeIt"/>
                    <p></p>
                    <van-icon name="chat" size="40" color="white"   badge="48" @click="show = true"/>
                    <p></p>
                    <van-icon name="share" color="white"  size="40" @click="share = true"/>
                </div>
 
            </div>
 
            <!--  下部信息 -->
            <div class="botton_tabs">
                <p class="video_info_title">
                    @张先生
                </p>
                <p class="video_info_content">
                    这车性能真的不错，底盘低，加速快，开车体验真好啊，高速上开车平稳，超车也很容易！
                </p>
            </div>
 
        </div>
        <!-- 评论面板 -->
        <van-action-sheet v-model:show="show" @select="onSelectPingLun" >
            <div class="pinglun_content" id="pinglun_content" ref="pinglun_content">
                <van-cell-group>
                    <van-cell title="热门评论" class="article-title">
                        <!-- 展示五条 -->
                        <template #label>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                        </template>
                    </van-cell>
                    <van-cell title="最新评论" class="article-title">
                        <template #label>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                            <van-cell title="徐女士"  >
                                <!-- 使用 icon 插槽来自定义左侧图标 -->
                                <template #icon>
                                    <div class="" style="margin-right: .23rem">
                                        <van-image
                                                width="2rem"
                                                height="2rem"
                                                fit="fill"
                                                round
                                                src="https://img.yzcdn.cn/vant/cat.jpeg"
                                        />
                                    </div>
                                </template>
                                <template #label>
                                    <div class="ping_lun_info">
                                        <div class="pin_lun_detail">
                                            这仅仅是规定，还是出台一个法律保护企业微妙，否则一旦出现问题，肯定第一就找企业。
                                        </div>
                                        <div class="ping_lun_other">
                                            <span class="pnglun_date">3天前-发布</span>
                                            <van-icon name="good-job-o" class="pinglun_label" size="1.2rem" badge="99+"  color="#ee0a24"/>
                                            <van-tag plain class="pinglun_label" round color="#7232dd"  @click="detailShow = true">评论 12</van-tag>
                                        </div>
                                    </div>
                                </template>
                            </van-cell>
                        </template>
                    </van-cell>
                </van-cell-group>
            </div>
        </van-action-sheet>
 
        <!-- 评论框 -->
        <van-action-sheet v-model:show="detailShow" @select="onSelectPingLun" >
            <div class="content">
                <van-form @submit="onSubmit">
                    <van-cell-group inset>
                        <van-field
                                v-model="message"
                                rows="8"
                                size="large"
                                type="textarea"
                                maxlength="100"
                                placeholder="您的评论..."
                                show-word-limit
                        />
                    </van-cell-group>
                    <div style="margin: 16px;">
                        <van-button round block color="linear-gradient(to right, #7232dd, #ee0a24)" native-type="submit">
                            提交
                        </van-button>
                    </div>
                </van-form>
            </div>
        </van-action-sheet>
 
       <!-- 分享面板 -->
        <van-share-sheet
                v-model:show="share"
                title="立即分享给好友"
                :options="options"
                @select="onSelect"
        />
    </div>
</template>

<script>

    import { Toast } from 'vant';
    export default {
        name: "SmallVideoDetail",
        data() {
            return {
                share: false,
                show: false,
                detailShow: false,
                dianzhan: false,
                likeName: 'like',
                current: 0,
                options: [
                    [
                        { name: '微信', icon: 'wechat' },
                        { name: '朋友圈', icon: 'wechat-moments' },
                    ],
                    [
                        { name: '复制链接', icon: 'link' },
                        { name: '二维码', icon: 'qrcode' },
                        { name: '小程序码', icon: 'weapp-qrcode' },
                    ],
                ],
                videoList: [{
                    url: "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/snapshots/e13c8454edf740f383876e05249023ea-00005.jpg",
                    cover: "https://rl.shuishan.net.cn/65c00ff5d52643ada373ae67fc5258a7/0c66767d68cc45968eaae18050a753b8-9788c7569453b623fd6fa5d32d6214de-ld.mp4",
                }, {
                    url: "https://rl.shuishan.net.cn/3ac7553ac507484aab7802212d8d4e1b/snapshots/7f997b02d8504514a3bc764f0a5eac91-00005.jpg",
                    cover: "https://rl.shuishan.net.cn/3ac7553ac507484aab7802212d8d4e1b/4419efa61cdb4df5aa015a3b4f83de75-6756b59c52deaf078f41293877dda64d-ld.mp4",
                },],
                isVideoShow: true,
                playOrPause: true,
                video: null,
                iconPlayShow: true,
                iconPauseShow: false
            }
        },
        methods: {
            onSelect (option) {
                this.share = false;
            },
            likeIt(event) {
              if (this.dianzhan) {
                  Toast.success('点赞成功');
                  this.dianzhan = false;
                  this.likeName = 'like'
              }  else {
                  Toast.success('取消成功');
                  this.dianzhan = true;
                  this.likeName = 'like-o'
              }
            },
            onChange(index) {
                //改变的时候 暂停当前播放的视频
                let video = document.querySelectorAll('video')[this.current];
                video.pause();
                this.playOrPause = false;
                this.current = index;
                //重置显示封面。图标等
                this.isVideoShow = true;
                this.iconPlayShow = true
                this.iconPauseShow = false
            },
            playvideo(event) {
                let video = document.querySelectorAll('video')[this.current];
                console.log("playvideo：" + this.current);
                this.isVideoShow = false;
                this.iconPlayShow = false
                this.iconPauseShow = true
                video.play();
                this.displayBtn()
            },
            pauseVideo(event) { //暂停\播放
                let video = document.querySelectorAll('video')[this.current]
                console.log("pauseVideo" + this.current)
                if (this.playOrPause) {
                    video.pause()
                    this.iconPlayShow = true
                    this.iconPauseShow = false
                } else {
                    video.play()
                    this.iconPlayShow = false
                    this.iconPauseShow = true
                }
                this.playOrPause = !this.playOrPause
                this.displayBtn()
            },
            displayBtn() {
                const secounds = 1
                let num = 0
                const timer = setInterval(() => {
                    if(num < secounds) {
                        num++
                    }else {
                        clearInterval(timer)
                        this.iconPlayShow = false
                        this.iconPauseShow = false
                    }
                }, 1000)// 这里的时间不要忘记，否则没有效果
            },
            onPlayerEnded(player) { //视频结束
                this.isVideoShow = true
                this.current += this.current
            },
        }
    }
</script>
<style scoped>
    .botton_tabs {
        margin-left: .6rem;
        position: absolute;
        bottom: 5%;
        left: 0%;
        width: 83%;
        z-index: 999;
    }
    .botton_tabs .video_info_title {
        font-size: 1.03rem;
        font-weight: 600;
        color: white;
    }
    .botton_tabs .video_info_content {
        color: white;
        font-size: .8rem;
    }

/*    /deep/.van-cell-group {
        background-color: transparent;
    }
    /deep/.van-cell-group .van-cell {
        background-color: transparent;
    }
    /deep/.van-cell__title span {
        font-size: 1.03rem;
        font-weight: 600;
        color: white;
    }
    /deep/.van-cell__title .van-cell__label {
        color: white;
        font-weight: 600;
    }*/
    .hide {
        display: none;
    }
    .show{
        display: block;
    }

    .video-player {
        height: 100vh;
        width: 100vw;
    }

    .product_swiper{
        width: 100vw;
        height: 100vh;
    }

    .van_swipe {
        width: 100vw;
        height: 100vh;
    }

    .van-swipe {
        width: 100%;
        height: 100%;
    }

    .container_box {
        width: 100vw;
        height: 100vh;
    }

    .video_box {
        object-fit: fill !important;
        z-index: 999;
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
        overflow: hidden;
    }

    video {
        object-position: 0 0;
    }

    .icon_play {
        position: absolute;
        top: 44%;
        right: 40%;
        z-index: 999;
    }

    .right_tabs {
        position: absolute;
        top: 62%;
        right: 6%;
        z-index: 999;
    }
    .play,
    .platStart {
        position: absolute;
        margin: auto;
        top: 0;
        left: 0;
        z-index: 999;
        width: 100%;
        height: 100%;
    }
    /*评论*/
    .ping_lun_info {

    }
    .article-other {
        margin-top: .75rem;
    }
    .ping_lun_info .ping_lun_other {
        margin-top: .5rem;
    }
    .ping_lun_info .ping_lun_other .pnglun_date {
        font-weight: 600;
    }
    .ping_lun_info .ping_lun_other .pinglun_label {
        margin-left: 1.5rem;
    }
</style>