<template>
    <div>
        <button @click="getAll()">getAll</button>
        <p>The End</p>
        <div class="tableContainer">
            <div class="filter">
                <p>筛选</p>
                <!-- TODO: 使用下拉菜单给SUMCOUNTAVGGROUP_BYMAXMIN选择目标字段, 而不是用选择器 -->
                <ul>
                    <li
                        v-for="(item, index) in checkboxList"
                        :key="item"
                        :class="checkbox.includes(index) ? 'active' : ''"
                        @click="check(index)"
                    >
                        {{ item }}
                    </li>
                </ul>
                <button @click="letsGetThisFuckingCheck">
                    {{ isCheckAll ? "取消全选" : "选择全部" }}
                </button>
                <button @click="checkOpposite">反选</button>
            </div>
            <div class="tableHeader">
                <div class="tableHeaderItem">
                    <span class="tableHeaderContent">id</span
                    ><span class="tableHeaderContent">dropoff_datetime</span
                    ><span class="tableHeaderContent">extra</span
                    ><span class="tableHeaderContent">rate_code</span
                    ><span class="tableHeaderContent">tip_amount</span
                    ><span class="tableHeaderContent">trip_distance</span>
                </div>
            </div>
            <div class="tableBody" v-for="ride in rides">
                <span class="tableBodyContent">{{ ride.id }}</span
                ><span class="tableBodyContent">{{
                    ride.dropoff_datetime
                }}</span
                ><span class="tableBodyContent">{{ ride.extra }}</span
                ><span class="tableBodyContent">{{ ride.rate_code }}</span
                ><span class="tableBodyContent">{{ ride.tip_amount }}</span
                ><span class="tableBodyContent">{{ ride.trip_distance }}</span>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { ref, reactive } from "vue";

export default {
    data() {
        return {
            rides: [],
            form: reactive({
                size: 10,
            }),
            ctedNum: "",
            radioList: [
                "LIMIT",
                "SUM",
                "COUNT",
                "AVG",
                "GROUP_BY",
                "MAX",
                "MIN",
            ],
            checkbox: [],
            checkboxList: [
                "LIMIT",
                "SUM",
                "COUNT",
                "AVG",
                "GROUP_BY",
                "MAX",
                "MIN",
            ],
        };
    },
    methods: {
        getAll() {
            axios.get("/all").then((res) => {
                console.log(res);
                this.rides = res.data;
            });
        },
        getLimit() {
            console.log(this.form);
            axios.post("/limit", this.form).then((res) => {
                console.log(res);
                this.rides = res.data;
            });
        },
        addTag(newTag) {
            const tag = {
                name: newTag,
                language:
                    newTag.substring(0, 2) +
                    Math.floor(Math.random() * 10000000),
            };
            this.options.push(tag);
            if (this.value) this.value = [];
            this.value.push(tag);
        }, //单选
        select(i) {
            this.selectedNum = i;
        },
        //多选
        check(i) {
            var idx = this.checkbox.indexOf(i);
            //如果已经选中了，那就取消选中，如果没有，则选中
            if (idx > -1) {
                this.checkbox.splice(idx, 1);
            } else {
                this.checkbox.push(i);
            }
        },

        letsGetThisFuckingCheck() {
            if (this.isCheckAll) {
                this.clearCheckbox();
            } else {
                this.checkAll();
            }
        },
        //选中全部
        checkAll() {
            var len = this.checkboxList.length;
            this.checkbox = [];
            for (var i = 0; i < len; i++) {
                this.checkbox.push(i);
            }
        },
        //清空选择
        clearCheckbox() {
            this.checkbox = [];
        },
        //反选
        checkOpposite() {
            console.log(1);
            var len = this.checkboxList.length;
            var idx;
            for (var i = 0; i < len; i++) {
                idx = this.checkbox.indexOf(i);
                //已经选中的删去，没选中的加进去
                if (idx > -1) {
                    this.checkbox.splice(idx, 1);
                } else {
                    this.checkbox.push(i);
                }
            }
        },
    },
    mounted: function () {},
    computed: {
        //判断是否全部选中
        isCheckAll() {
            if (this.checkbox.length == this.checkboxList.length) {
                return true;
            }
            return false;
        },
    },
};
</script>

<style lang="css">
.tableHeaderContent {
    margin-right: 10px;
    color: aqua;
    text-align: left;
}
.tableBodyContent {
    margin-right: 50px;
    color: mintcream;
    text-align: left;
}
li {
    display: inline-block;
    font-size: 16px;
    padding: 5px;
    background-color: #e6e6e6;
    margin: 5px 10px;
    cursor: pointer;
    color: #373737;
}
.active {
    border: 2px solid red;
}
</style>
