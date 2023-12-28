import { createRouter, createWebHistory } from "vue-router";
import mainpage from "../pages/mainpage.vue";
import login from "../pages/login.vue";
import NotFound from "../pages/components/notfound.vue";

const routes = [
  {
    path: "/:catchAll(.*)",
    name: "NotFound",
    component: NotFound,
    meta: { title: "Oops, 404" },
  },
  {
    path: "/",
    name: "Home",
    component: mainpage,
    meta: { title: "Home" },
  },
  {
    path: "/login",
    name: "Login",
    component: login,
    meta: { title: "Login" },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
