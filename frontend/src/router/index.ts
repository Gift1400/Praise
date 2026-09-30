import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import About from "../views/About.vue";
import Events from "../views/Events.vue";
import Announcements from "../views/Announcements.vue";
import Contact from "../views/Contact.vue";
import Sermons from "../views/Sermons.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/sermons",
    name: "sermons",
    component: Sermons,
  },
  {
    path: "/announcements",
    name: "announcements",
    component: Announcements,
  },
  {
    path: "/contacts",
    name: "contacts",
    component: Contact,
  },
  {
    path: "/about",
    name: "about",
    component: About,
  },
  {
    path: "/events",
    name: "events",
    component: Events,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
