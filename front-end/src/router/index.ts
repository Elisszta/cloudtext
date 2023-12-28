import { createRouter, createWebHistory } from "vue-router";
import Mainpage from "../pages/mainpage.vue";
import Login from "../pages/login.vue";
import About from "../pages/about.vue";
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
    component: Mainpage,
    meta: { title: "Home" },
  },
  {
    path: "/login",
    name: "Login",
    component: () => Login,
  },
  {
    path: "/about",
    name: "About",
    component: () => About,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
