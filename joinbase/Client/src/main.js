import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import axios from "axios";

const app = createApp(App);

app.use(createPinia());

axios.defaults.baseURL = "http://localhost:9000/v1/";
axios.interceptors.request.use((config) => {
    return config;
});

router.beforeEach((to, from, next) => {
    if (to.matched.some((record) => record.meta.requireAuth)) {
        const token = localStorage.getItem("token");
        console.log("------------" + token);
        if (token) {
            if (to.path === "/login") {
            } else {
                next();
            }
        } else {
            next({
                path: "/login",
            });
        }
    } else {
        next();
    }
});

app.use(router);
app.mount("#app");
