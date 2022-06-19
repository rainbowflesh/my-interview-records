import { defineStore } from "pinia";

export const useStore = defineStore("main", {
    state: () => ({
        // 储存本地数据 (并没有)
        token: "",
        userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
    }),
    actions: {
        setToken(token) {
            this.$state.token = token;
            localStorage.setItem("token", token);
        },
        setUserInfo(userInfo) {
            this.$state.userInfo = userInfo;
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
        },
        clearUserInfo() {
            this.$state.token = "";
            this.$state.userInfo = {};
            localStorage.setItem("token", "");
            sessionStorage.setItem("userInfo", JSON.stringify(""));
        },
        SET_TOKEN: (state, token) => {
            state.token = token;
            localStorage.setItem("token", token);
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo;
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo));
        },
        REMOVE_INFO: (state) => {
            state.token = "";
            state.userInfo = {};
            localStorage.setItem("token", "");
            sessionStorage.setItem("userInfo", JSON.stringify(""));
        },
    },
    getters: {
        getUser: (state) => {
            return state.userInfo;
        },
    },
});
