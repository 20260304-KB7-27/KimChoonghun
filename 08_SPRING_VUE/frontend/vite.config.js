import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  server: {
    proxy: {
      '/api': {
        target: "http://localhost:8080",
      }
    }
  },
  build: {
    outDir: "C://kb_fullstack/08_SPRING_VUE/backend/src/main/webapp/resources",
    emptyOutDir: true, // 기존 파일 삭제 여부(true: 삭제)
  }
})
