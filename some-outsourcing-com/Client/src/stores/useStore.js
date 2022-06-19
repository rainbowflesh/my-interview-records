import { defineStore } from "pinia";

export const useStore = defineStore("main", {
    state: () => ({
        // const data = sessionStorage.getItem("userInfo");
        // let historyArr = [];
        // if (data) {
        //     historyArr = JSON.parse(data);
        // }
        // const states = {
        //     token: "",
        //     userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
        // };
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

// usage
// import { store } from '@/stores/counter'

// export default {
//   setup() {
//     const store = store()

//     return {
//       // you can return the whole store instance to use it in the template
//       store,
//     }
//   },
// }
// const store = store()

// // store.counter++
// import { useCounterStore } from '../stores/counterStore'

// export default {
//   setup() {
//     const counterStore = useCounterStore()

//     return { counterStore }
//   },
//   computed: {
//     tripleCounter() {
//       return counterStore.counter * 3
//     },
//   },
// }
