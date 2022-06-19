<template>
    <header>
        <img
            alt="logo"
            class="logo"
            src="./assets/logo.ico"
            width="125"
            height="125"
        />
        {{ user.username }}
        <button @click="logout()">logout</button>
        <div class="wrapper">
            <HelloWorld msg="你好啊" />
            <!-- TODO: 全文搜索 -->
            <div class="searchBarContainer">
                <div class="searchBarForm" :model="ruleForm" ref="ruleForm">
                    <input
                        type="text"
                        prop="searchKeyword"
                        v-model="ruleForm.searchKeyword"
                        placeholder="search something..."
                    />
                    {{ ruleForm.searchKeyword }}
                    <button @click="submitForm('ruleForm')">search</button>
                </div>
            </div>
            <div class="searchResult">搜索结果: {{ searchResult }}</div>
            <nav>
                <RouterLink class="link" to="/">Blogs</RouterLink>
                <RouterLink class="link" to="/login">Login</RouterLink>
                <RouterLink class="link" to="/editor">Post</RouterLink>
            </nav>
        </div>

        <RouterView />
    </header>
</template>
<script setup>
import { RouterLink, RouterView } from "vue-router";
import HelloWorld from "./components/HelloWorld.vue";
</script>
<script>
import axios from "axios";
import { useStore } from "@/stores/useStore";
import router from "@/router";
export default {
    name: "App",
    components: {
        axios,
    },
    data() {
        const store = useStore();
        return {
            user: { username: "Login first" },
            hasLogin: false,
            ruleForm: {
                searchKeyword: "",
                title: "",
                content: "",
                description: "",
            },
            searchResult: "搜搜",
            store,
        };
    },
    methods: {
        // 登出 (没用)
        logout() {
            axios
                .get("/logout", {
                    headers: {
                        Authorization: localStorage.getItem("token"),
                    },
                })
                .then((res) => {
                    store.commit("REMOVE_INFO");
                    router.push("/login");
                });
        },
        // 搜索表单
        submitForm() {
            this.ruleForm.content =
                this.ruleForm.description =
                this.ruleForm.title =
                    this.ruleForm.searchKeyword;
            axios.post("/blog/search", this.ruleForm).then((res) => {
                if (res.data.data != null) {
                    this.$data.searchResult = res.data.data;
                } else {
                    this.$data.searchResult = "naidesu";
                }
            });
        },
    },
    setup() {
        if (store.getters.getUser.username) {
            this.user.username = store.getters.getUser.username;
            this.user.avatar = store.getters.getUser.avatar;
            this.hasLogin = true;
        }
        return {};
    },
};
</script>
<style>
@import "./assets/base.css";
.link {
    margin: 1rem 1rem 1rem 1rem;
}
nav {
    text-align: center;
}
#app {
    max-width: 1280px;
    margin: 0 auto;
    padding: 2rem;

    font-weight: normal;
}

header {
    line-height: 1.5;
}

.logo {
    display: block;
    margin: 0 auto 2rem;
}

a,
.green {
    text-decoration: none;
    color: hsla(160, 100%, 37%, 1);
    transition: 0.4s;
}

@media (hover: hover) {
    a:hover {
        background-color: hsla(160, 100%, 37%, 0.2);
    }
}

@media (min-width: 1024px) {
    body {
        display: flex;
        place-items: center;
    }

    #app {
        display: grid;
        grid-template-columns: 1fr 1fr;
        padding: 0 2rem;
    }

    header {
        display: flex;
        place-items: center;
        padding-right: calc(var(--section-gap) / 2);
    }

    header .wrapper {
        display: flex;
        place-items: flex-start;
        flex-wrap: wrap;
    }

    .logo {
        margin: 0 2rem 0 0;
    }
}
</style>
