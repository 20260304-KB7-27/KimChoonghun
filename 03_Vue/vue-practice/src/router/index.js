import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path:'/', name:'home', component: () => import('../pages/homePage.vue')},
    {path:'/memo/:id', name:'memo/id', component: () => import('../pages/memoDetail.vue')},
    {path:'/memo/new', name:'memo/new', component: () => import('../pages/newMemo.vue')}
  ],
})

export default router
