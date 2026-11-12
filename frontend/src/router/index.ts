import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Landing from "../views/Landing.vue";
import Profile from "../views/Profile.vue";
import About from "../views/About.vue";
import Events from "../views/Events.vue";
import Announcements from "../views/Announcements.vue";
import Contact from "../views/Contact.vue";
import Sermons from "../views/Sermons.vue";
import Login from "../views/Login.vue";
import Signup from "../views/Signup.vue";
import Prayer from "../views/Prayer.vue";
import Verses from "../views/Verses.vue";
import Outreach from "../views/OutreachProgram.vue";
import Donations from "../views/Donations.vue";
import Dashboard from "../views/Dashboard.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/landing",
    name: "landing",
    component: Landing,
  },
  {
    path: "/profile",
    name: "profile",
    component: Profile,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
    meta: { hideChrome: true },
  },
  {
    path: "/signup",
    name: "signup",
    component: Signup,
    meta: { hideChrome: true },
  },
  {
    path: "/sermons",
    name: "sermons",
    component: Sermons,
  },
  {
    path: "/events",
    name: "events",
    component: Events,
  },
  {
    path: "/announcements",
    name: "announcements",
    component: Announcements,
  },
  {
    path: "/prayer",
    name: "prayer",
    component: Prayer,
  },
  {
    path: "/verses",
    name: "verses",
    component: Verses,
  },
  {
    path: "/outreach",
    name: "outreach",
    component: Outreach,
  },
  {
    path: "/giving",
    name: "giving",
    component: Donations,
    alias: "/donations",
  },
  {
    path: "/dashboard",
    name: "dashboard",
    component: Dashboard,
  },
  {
    path: "/about",
    name: "about",
    component: About,
  },
  {
    path: "/contacts",
    name: "contacts",
    component: Contact,
  },
];

import { getUser } from "../util/auth";

const protectedRoutes = [
  "/events",
  "/announcements",
  "/sermons",
  "/prayer",
  "/verses",
  "/outreach",
  "/giving",
  "/donations",
  "/dashboard",
  "/profile",
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior() {
    return { top: 0 };
  },
});

router.beforeEach((to, from, next) => {
  const user = getUser();
  const requiresAuth = protectedRoutes.some(
    (p) => to.path === p || to.path.startsWith(`${p}/`)
  );

  if (requiresAuth && !user) {
    next({ path: "/login", query: { redirect: to.fullPath } });
  } else {
    next();
  }
});

export default router;
