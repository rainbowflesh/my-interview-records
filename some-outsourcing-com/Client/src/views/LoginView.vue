<template>
    <div class="login">
        <h1>This is an login page</h1>
        <div class="loginContainer">
            <div class="loginForm" :model="ruleForm" ref="ruleForm">
                <input
                    type="text"
                    prop="username"
                    v-model="ruleForm.username"
                    placeholder="Username"
                />
                <br />
                <input
                    type="password"
                    prop="password"
                    v-model="ruleForm.password"
                    placeholder="Password"
                />
                <br />
                {{ ruleForm.username }}
                {{ ruleForm.password }}
                <button @click="submitForm('ruleForm')">Login</button>
                <button>Register</button>
            </div>
        </div>
    </div>
</template>
<script>
import axios from "axios";
import { useStore } from "@/stores/useStore";
import router from "@/router";

export default {
    component: {
        axios,
    },
    name: "LoginView",
    data() {
        const store = useStore();
        return {
            ruleForm: {
                username: "",
                password: "",
            },
            store,
        };
    },
    methods: {
        submitForm() {
            axios.post("/login", this.ruleForm).then((res) => {
                // console.log(res.data);
                // console.log("password=" + this.ruleForm.password);
                const jwt = res.headers["authorization"];
                const userInfo = res.data.data;
                this.$data.store.setToken(jwt);
                // useStore.commit("SET_TOKEN", jwt);
                // useStore.commit("SET_USERINFO", userInfo);
                console.log("user?" + useStore.getUser);
                router.push("/");
            });
        },
    },
};
</script>
<style>
@media (min-width: 1024px) {
    .login {
        min-height: 100vh;
        display: flex;
        align-items: center;
    }
}
.loginContainer .loginForm {
    display: flex;
    flex-direction: column;
    max-width: 70%;
    margin: 10%;
}
</style>
