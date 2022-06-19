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
            <!-- <div class="searchBarContainer">
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
            </div> -->
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
export default {
    name: "App",
    data() {
        return {
            user: { username: "Login first" },
            hasLogin: false,
        };
    },
    methods: {
        logout() {
            const _this = this;
            _this.$axios
                .get("/logout", {
                    headers: {
                        Authorization: localStorage.getItem("token"),
                    },
                })
                .then((res) => {
                    _this.$store.commit("REMOVE_INFO");
                    _this.$router.push("/login");
                });
        },
    },
    setup() {
        if (this.$store.getters.getUser.username) {
            this.user.username = this.$store.getters.getUser.username;
            this.user.avatar = this.$store.getters.getUser.avatar;
            this.hasLogin = true;
        }
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
