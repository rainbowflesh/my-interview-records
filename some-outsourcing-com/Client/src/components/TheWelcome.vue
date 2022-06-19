<template>
    <button @click="currenPage(1)">Force load</button>
    <br />
    <div class="commentContainer">
        发起你的锐评
        <div class="commentForm" :model="form" ref="form">
            <input
                type="text"
                prop="id"
                v-model="form.id"
                placeholder="文章 id"
            />
            <input
                type="text"
                prop="remark"
                v-model="form.remark"
                placeholder="remark"
            />
            你的锐评: {{ form.remark }}
            <br />
            <button @click="submitForm('form')">评论</button>
            <button @click="voteUp()">点赞</button>
        </div>
    </div>
    <div class="blogContainer" v-for="blog in blogs">
        <WelcomeItem>
            blog id: {{ blog.id }}
            <br />
            <template #icon>
                <DocumentationIcon />
            </template>
            <template #heading>标题: {{ blog.title }}</template>
            <a>简介: {{ blog.description }}</a
            ><br />
            内容: {{ blog.content }}
            <br />
            评论区: {{ blog.remark }}
            <br />
            👍: {{ blog.votes }}
        </WelcomeItem>
    </div>
    <WelcomeItem>
        <template #icon>
            <DocumentationIcon />
        </template>
        <template #heading>Documentation</template>

        Vue’s
        <a target="_blank" href="https://vuejs.org/">official documentation</a>
        provides you with all information you need to get started.
    </WelcomeItem>

    <WelcomeItem>
        <template #icon>
            <ToolingIcon />
        </template>
        <template #heading>Tooling</template>

        This project is served and bundled with
        <a href="https://vitejs.dev/guide/features.html" target="_blank">Vite</a
        >. The recommended IDE setup is
        <a href="https://code.visualstudio.com/" target="_blank">VSCode</a> +
        <a href="https://github.com/johnsoncodehk/volar" target="_blank"
            >Volar</a
        >. If you need to test your components and web pages, check out
        <a href="https://www.cypress.io/" target="_blank">Cypress</a> and
        <a href="https://on.cypress.io/component" target="_blank"
            >Cypress Component Testing</a
        >.

        <br />

        More instructions are available in <code>README.md</code>.
    </WelcomeItem>

    <WelcomeItem>
        <template #icon>
            <EcosystemIcon />
        </template>
        <template #heading>Ecosystem</template>

        Get official tools and libraries for your project:
        <a target="_blank" href="https://pinia.vuejs.org/">Pinia</a>,
        <a target="_blank" href="https://router.vuejs.org/">Vue Router</a>,
        <a target="_blank" href="https://test-utils.vuejs.org/"
            >Vue Test Utils</a
        >, and
        <a target="_blank" href="https://github.com/vuejs/devtools"
            >Vue Dev Tools</a
        >. If you need more resources, we suggest paying
        <a target="_blank" href="https://github.com/vuejs/awesome-vue"
            >Awesome Vue</a
        >
        a visit.
    </WelcomeItem>

    <WelcomeItem>
        <template #icon>
            <CommunityIcon />
        </template>
        <template #heading>Community</template>

        Got stuck? Ask your question on
        <a target="_blank" href="https://chat.vuejs.org">Vue Land</a>, our
        official Discord server, or
        <a
            target="_blank"
            href="https://stackoverflow.com/questions/tagged/vue.js"
            >StackOverflow</a
        >. You should also subscribe to
        <a target="_blank" href="https://news.vuejs.org">our mailing list</a>
        and follow the official
        <a target="_blank" href="https://twitter.com/vuejs">@vuejs</a>
        twitter account for latest news in the Vue world.
    </WelcomeItem>

    <WelcomeItem>
        <template #icon>
            <SupportIcon />
        </template>
        <template #heading>Support Vue</template>

        As an independent project, Vue relies on community backing for its
        sustainability. You can help us by
        <a target="_blank" href="https://vuejs.org/sponsor/"
            >becoming a sponsor</a
        >.
    </WelcomeItem>
</template>
<script>
import WelcomeItem from "./WelcomeItem.vue";
import DocumentationIcon from "./icons/IconDocumentation.vue";
import ToolingIcon from "./icons/IconTooling.vue";
import EcosystemIcon from "./icons/IconEcosystem.vue";
import CommunityIcon from "./icons/IconCommunity.vue";
import SupportIcon from "./icons/IconSupport.vue";
import axios from "axios";
import { ref, reactive } from "vue";
export default {
    name: "TheWelcome",
    components: {
        WelcomeItem,
        DocumentationIcon,
        ToolingIcon,
        EcosystemIcon,
        CommunityIcon,
        SupportIcon,
        axios,
    },
    data() {
        return {
            blogs: [],
            form: reactive({
                id: "",
                remark: "",
                votes: 0,
            }),
        };
    },
    methods: {
        submitForm() {
            axios.post("/blog/comment", this.form).then((res) => {
                console.log(res);
            });
        },
        voteUp() {
            axios.get("/blog/" + this.form.id).then((res) => {
                this.form.votes = res.data.data.votes + 1;
                axios.post("/blog/votes", this.form);
            });
        },
    },
    setup() {
        const blogs = ref([]);
        const page = ref(1);
        const total = ref(2);
        const pageSize = ref(3);
        const currenPage = () => {
            axios.get("/blogs?page=" + page.value).then((res) => {
                var count = 0;
                for (count in res.data.data.records) {
                    blogs.value.push({
                        id: res.data.data.records[count].id,
                        title: res.data.data.records[count].title,
                        description: res.data.data.records[count].description,
                        content: res.data.data.records[count].content,
                        remark: res.data.data.records[count].remark,
                        votes: res.data.data.records[count].votes,
                    });
                }
            });
        };
        return {
            blogs,
            page,
            total,
            pageSize,
            currenPage,
        };
    },
    onMounted() {
        currenPage();
    },
};
</script>
