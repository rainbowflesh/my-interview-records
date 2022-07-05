<template>
    <div class="editor">
        <h1>This is an editor page</h1>
        <div class="editorContainer">
            <div class="editorForm" :model="form" ref="form">
                <input
                    type="text"
                    prop="title"
                    v-model="form.title"
                    placeholder="title"
                />
                <br />
                <input
                    type="text"
                    prop="description"
                    v-model="form.description"
                    placeholder="description"
                />
                <br />
                <input
                    type="text"
                    prop="content"
                    v-model="form.content"
                    placeholder="content"
                />
                <br />
                {{ form.title }}
                {{ form.description }}
                {{ form.content }}
                <button @click="submitForm('form')">post</button>
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
    name: "EditorView",
    data() {
        const store = useStore();
        return {
            form: {
                title: "",
                description: "",
                content: "",
                user_id: "2",
            },
            store,
        };
    },
    methods: {
        // 提交新博客
        submitForm() {
            axios.post("/blog/post", this.form).then((res) => {
                console.log(res);
                router.push("/");
            });
        },
    },
};
</script>
<style>
@media (min-width: 1024px) {
    .editor {
        min-height: 100vh;
        display: flex;
        align-items: center;
    }
}
</style>
