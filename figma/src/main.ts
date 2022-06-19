import { createApp } from "vue";
import { createPinia } from "pinia";
// 
import App from "./App.vue";
import router from "./router";
import VueGoogleMaps from "@fawmi/vue-google-maps";
import 'material-design-icons-iconfont/dist/material-design-icons.css'
// 
const app = createApp(App);
const pinia = createPinia()
// 
app.use(pinia)
app.use(router);
app.use(VueGoogleMaps, {
    load: {
        key: "UrKeyHere"
    }
})
app.mount("#app");
