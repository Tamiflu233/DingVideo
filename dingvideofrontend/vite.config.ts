//vite.config.js中配置
import { defineConfig, UserConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import AutoImport from 'unplugin-auto-import/vite'    //自动引入api
import Components from 'unplugin-vue-components/vite'  //按需自动引入组件
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
// @ts-ignore
import fs from 'fs'
// @ts-ignore
import dotenv from 'dotenv'

//在plugins配置中配置
export default defineConfig(({ mode }: UserConfig): UserConfig => {
  // 根据环境变量加载环境变量文件
  const ASR_ENV = dotenv.parse(fs.readFileSync(`.env.${mode}`))
  return {
    base: ASR_ENV.VITE_PUBLIC_PATH,   // 环境路径
    server: {
      open: true, // 是否主动唤醒浏览器
      host: '0.0.0.0',
      port: 9527,// ASR_ENV.VITE_PROT,
      https: false,
      proxy: {
        [ASR_ENV.VITE_BASE_API]: {
          target: `${ASR_ENV.VITE_BASE_PATH}`,
          changeOrigin: true,
        },
      },
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src'),
        'assets': path.resolve(__dirname, 'src/assets'),
        'components': path.resolve(__dirname, 'src/components'),
        'config': path.resolve(__dirname, 'src/config'),
        'router': path.resolve(__dirname, 'src/router'),
        'tools': path.resolve(__dirname, 'src/tools'),
        'views': path.resolve(__dirname, 'src/views'),
        'plugins': path.resolve(__dirname, 'src/plugins'),
        'store': path.resolve(__dirname, 'src/store')
      }
    },
    plugins: [
      vue(),
      AutoImport({
        resolvers: [
          ElementPlusResolver(),
          // Auto import icon components
          // 自动导入图标组件
          IconsResolver({
            prefix: 'Icon',
          }),
        ],  //对于element puls的配置
        imports: [
          'vue',              //自动引入的vue的ref等方法
          'vue-router',       //引入useRoute等方法
          {                   //对于vue-router的type的扩展,配置后可以直接使用
            from: 'vue-router',
            imports: ['RouteLocationRaw',],
            type: true,
          },],
        dts: 'src/auto-imports.d.ts'
      }),
      Components({
        resolvers: [
          // Auto register icon components
          // 自动注册图标组件
          IconsResolver({
            enabledCollections: ['ep'],
          }),
          ElementPlusResolver()
        ],   //对于element puls的配置
      }),
      Icons({
        autoInstall: true,
      }),
    ],
    css: {
      preprocessorOptions: {
        scss: {
          charset: false,
        },
      },
    },
    build: {
      outDir: 'dist',           // 指定输出路径
      assetsDir: 'static',      // 指定生成静态资源的存放路径
      minify: 'terser',         // 混淆器,terser构建后文件体积更小 ,boolean | 'terser' | 'esbuild',默认使用esbuild
      sourcemap: false,         // 是否产出soucemap.json
      manifest: false,          // 是否产出maifest.json
      // reportCompressedSize: true,
      chunkSizeWarningLimit: 1500,
      terserOptions: {
        compress: {
          drop_console: true,   // 生产环境移除console
          drop_debugger: true   // 生产环境移除debugger
        }
      },
    },
  }
})

//使用:
//在此处配置element plus后, 可以直接使用,仁和地方都不用再引入,自动按需引入的,在components.d.ts中可以看到生成文件
//import {ref} from  "vue"这种引入也可省略, 直接使用即可, 具体引入的类型在src/auto-imports.d.ts文件中可见
//注意:修改vite.config.ts文件后需要重启