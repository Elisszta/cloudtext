import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 8888,
    cors: true, // 允许跨域
    host: "localhost",
    hmr: true,
  },
  base: "./", // 设置打包路径
});
