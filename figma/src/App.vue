<template>
    <div class="canvas">
        <div class="MapContainer">
            <div class="TopFrame" style="CyanStrong">
                <div class="SearchBarContainer">
                    <span
                        class="material-icons"
                        style="
                            position: absolute;
                            color: #fff;
                            margin: 3% auto 3% 4%;
                            font-size: 1em;
                            font-weight: 600;
                        "
                        >arrow_back_ios</span
                    >
                    <div class="SearchBar1">
                        <span
                            class="material-icons"
                            style="
                                z-index: 2;
                                position: absolute;
                                color: #fff;
                                margin: 3% auto 3% 13%;
                                font-size: 1em;
                                font-weight: 600;
                            "
                            >search</span
                        ><input
                            id="SearchInput"
                            type="text"
                            onfocus="if (value =='          Ontario, ON'){value =''}"
                            onblur="if (value ==''){value='         Ontario, ON'}"
                            class="SearchBar"
                            placeholder="          Search"
                            style="
                                color: #fff;
                                border-color: rgba(0, 0, 0, 0);
                                /* Body1 */
                                font-family: 'Poppins';
                                font-style: normal;
                                font-weight: 500;
                                line-height: 24px;
                                font-size: 1em;
                                font-weight: 600;
                            "
                        /><Button
                            class="material-icons"
                            onclick="
                            var input = document.getElementById('SearchInput');
                            input.value = '';
                            "
                            style="
                                color: #fff;
                                position: absolute;
                                line-height: 100%;
                                text-align: center;
                                height: 100%;
                                right: 7em;
                                background-color: rgba(0, 0, 0, 0);
                                border-color: rgba(0, 0, 0, 0);
                                font-size: 1em;
                                font-weight: 600;
                            "
                            >cancel</Button
                        >
                    </div>

                    <span
                        style="
                            color: #fff;
                            position: absolute;
                            color: #fff;
                            margin: auto;
                            margin-top: 0.76em;
                            right: 1em;
                            /* Watch */
                            height: 2.5em;

                            /* Subtitle 1 */
                            font-family: 'Poppins';
                            font-style: normal;
                            font-weight: 600;
                            line-height: 21px;
                            font-size: 1em;
                            font-weight: 600;
                            /* identical to box height */

                            /* White */
                            color: #ffffff;
                        "
                        >Watch</span
                    >
                    <span
                        class="material-icons"
                        style="
                            color: #fff;
                            position: absolute;
                            margin: auto;
                            margin-top: 0.09em;
                            right: 2.5em;
                            font-size: 1em;
                            font-weight: 600;
                        "
                        >keyboard_command_key</span
                    >
                </div>
                <div class="MapMenuContainer">
                    <div id="MapMenu1">
                        <select
                            class="MapMenu"
                            style="
                            /* Menu */
                            width: 10em;
                            height: 1.8em;
                            bottom: 0.75em
                            left: 1em;
                            margin-left: 0.8em;
                            font-size: 1em;
                            line-height: 21px;
                        "
                        >
                            <option disabled value="">
                                All Property Types
                            </option>
                            <option>All Property Types</option>
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                        </select>
                        <!-- <span
                            class="material-icons"
                            style="
                                margin-left: 6.5em;
                                margin-top: 0.6em;
                                font-size: 1em;
                                font-weight: 600;
                            "
                            >expand_more</span
                        > -->
                    </div>
                    <div
                        class="MapMenu"
                        id="MapMenu2"
                        style="box-sizing: border-box;
                            width: 6em;
                            height: 1.8em;
                            bottom: 0.75em
                            left: 1.5em;
                            margin-left: 11.5em;
                            font-size: 1em;
                            line-height: 21px;
                            "
                    >
                        <input
                            type="radio"
                            id="3D"
                            value="3D"
                            style="margin-left: 0.5em"
                        />
                        <label for="3D">3D</label>

                        <input
                            type="radio"
                            id="2D"
                            value="2D"
                            style="margin-left: 0em"
                        />
                        <label for="2D">2D</label>
                        <span
                            class="material-icons"
                            style="
                                margin-left: 4.8em;
                                font-size: 1em;
                                font-weight: 600;
                            "
                            >expand_more</span
                        >
                    </div>
                    <MapButton
                        class="MapMenu"
                        @click="visible = true"
                        style="box-sizing: border-box;
                            width: 5em;
                            height: 1.8em;
                            bottom: 0.75em
                            left: 1.5em;
                            margin-left: 18em;
                            font-size: 1em;
                            line-height: 21px;
                            "
                        ><p
                            style="
                                font-family: 'Poppins';
                                font-style: normal;
                                font-weight: 600;
                                position: absolute;
                                margin-left: 10%;
                            "
                        >
                            Filters
                        </p>
                        <span
                            class="material-icons"
                            style="
                                margin-left: 3.5em;
                                font-size: 1em;
                                font-weight: 600;
                            "
                            >expand_more</span
                        ></MapButton
                    ><MapDialog
                        v-model:visible="visible"
                        title="Filters"
                        :ok="ok"
                        :cancel="cancel"
                    >
                        <div class="DialogMenu" id="DialogMenu1">
                            <span class="FilterText" style="margin-top: 0em"
                                >Price range</span
                            ><br /><span
                                class="FilterText"
                                style="height: 2em; margin-top: 1em"
                                >$700,000 - $4,000,000</span
                            ><br />
                            <!-- <MapSlider
                                :value-fun="Val"
                                :min="0"
                                :max="100"
                                style="height: 2em; margin-top: 2em"
                            ></MapSlider> -->
                            <div class="map-dialog-divider" />
                        </div>
                        <div class="DialogMenu" id="DialogMenu2">
                            <span
                                class="FilterText"
                                style="height: 2em; margin-top: 0em"
                                >Description Contains Keywords</span
                            >
                            <input
                                class="FilterText"
                                style="
                                    height: 2.5em;
                                    width: 90%;
                                    margin-top: 2em;
                                    mix-blend-mode: normal;
                                    opacity: 0.4;
                                    border: 1px solid #000000;
                                    border-radius: 5px;
                                "
                                placeholder="Waterfront, Pool, Fireplace..."
                            />
                        </div>
                        <div class="map-dialog-divider" />
                        <div class="DialogMenuSmall" id="DialogMenu1">
                            <span
                                class="FilterText"
                                style="height: 2em; margin-top: 0.5em"
                                >Bedrooms</span
                            >
                            <!--<FilterCheckBox
                                style="margin-top: 4em; color: aqua"
                            ></FilterCheckBox> -->
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-any1"
                                v-model="CheckedBedroomsAny"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-0"
                                v-model="CheckedBedrooms0"
                                style="margin-left: 4em"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-1"
                                v-model="CheckedBedrooms1"
                                style="margin-left: 8em"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-2"
                                v-model="CheckedBedrooms2"
                                style="margin-left: 12em"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-3"
                                v-model="CheckedBedrooms3"
                                style="margin-left: 16em"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-4"
                                v-model="CheckedBedrooms4"
                                style="margin-left: 20em"
                            />
                            <input
                                type="checkbox"
                                class="DialogMenuCheckBox"
                                id="checkbox-5"
                                v-model="CheckedBedrooms5"
                                style="margin-left: 24em"
                            />
                        </div>
                        <div class="map-dialog-divider" />
                        <div class="DialogMenuSmall" id="DialogMenu1">
                            <span
                                class="FilterText"
                                style="height: 2em; margin-top: 0.5em"
                                >Bathrooms</span
                            >
                            <!--<FilterCheckBox
                                style="margin-top: 4em; color: aqua"
                            ></FilterCheckBox> -->
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-any1"
                                value="true"
                                v-model="CheckedBathroomsAny"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-0"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 4em"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-1"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 8em"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-2"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 12em"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-3"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 16em"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-4"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 20em"
                            />
                            <input
                                type="radio"
                                class="DialogMenuCheckBox"
                                id="radio-5"
                                value="false"
                                v-model="CheckedBathrooms"
                                style="margin-left: 24em"
                            />
                        </div>
                    </MapDialog>
                </div>
            </div>
            <!-- <Map /> -->
        </div>
    </div>
</template>

<script lang="ts">
import Map from "./components/MapComponent.vue";
import MapButton from "./lib/MapButton/MapButton.vue";
import MapDialog from "./lib/MapDialog/MapDialog.vue";
import MapSlider from "./lib/MapSlider/MapSlider.vue";
import { useStore } from "./stores/Selection";
import { defineStore } from "pinia";
import { ref } from "vue";
export const userStore = defineStore("user", {
    state: () => {
        return {
            count: 1,
            arr: [],
        };
    },
    getters: {
        getCount: (state) => {
            return state.count;
        },
        getArr: (state) => {
            return state.arr;
        },
    },
    // setters: {
    //     setCount: (state, payload) => {
    //         state.count = payload;
    //     },
    //     setArr: (state, payload) => {
    //         state.arr = payload;
    //     },
    // },
});
export default {
    name: "App",
    components: {
        Map,
        MapButton,
        MapDialog,
        MapSlider,
    },
    setup() {
        const store = useStore();
        const CheckedBedrooms0 = false,
            CheckedBedrooms1 = false,
            CheckedBedrooms2 = false,
            CheckedBedrooms3 = false,
            CheckedBedrooms4 = false,
            CheckedBedrooms5 = false,
            CheckedBedroomsAny = true,
            CheckedBathrooms = false,
            CheckedBathroomsAny = true;
        const visible = ref(false);
        const ok = () => {
            return new Promise((resolve, reject) => {
                setTimeout(() => {
                    resolve(true);
                }, 1000);
            });
        };
        const cancel = () => {
            console.log("cancel");
        };
        return {
            visible,
            ok,
            cancel,
            CheckedBedrooms0,
            CheckedBedrooms1,
            CheckedBedrooms2,
            CheckedBedrooms3,
            CheckedBedrooms4,
            CheckedBedrooms5,
            CheckedBedroomsAny,
            CheckedBathrooms,
            CheckedBathroomsAny,
            store,
        };
    },
};
</script>

<style lang="scss">
.map-dialog-divider {
    border: 0.1em solid #e2e2e2;
    padding: 0;
    background: #000000;
    opacity: 0.5;
}
.DialogMenuCheckBox {
    display: flex;
    align-items: center;
    position: absolute;
    width: 4em;
    height: 2em;
    margin-top: 4em;
    padding: 7px 10px;
    gap: 10px;
    background: #28a3b3;
    border-radius: 4px;
    justify-content: center;
    flex-direction: row;
}
.DialogMenu {
    height: 6.5em;
}
.DialogMenuSmall {
    height: 6em;
}
.FilterText {
    left: 4.5%;
    width: 90%;
    position: absolute;
    font-family: "Poppins";
    font-style: normal;
    font-weight: 600;
    font-size: 18px;
    line-height: 27px;
    color: #000000;
    mix-blend-mode: normal;
}
.MapMenu {
    box-sizing: border-box;
    position: absolute;
    font-family: "Poppins";
    font-style: normal;
    font-weight: 600;
    display: flex;
    color: #ffffff;
    background-color: rgba(0, 0, 0, 0);
    border-radius: 66px;
    border: 1px solid #ffffff;
    align-items: center;
}
.material-icons {
    color: #fff;
    position: absolute;
    color: #fff;
    margin: auto;
}
.TopFrame {
    position: absolute;
    z-index: 1;
    top: 0px;
    left: 0;
    width: 100%;
    height: 20%;
    background: #28a3b3;
    box-shadow: 0px 5px 25px 1px rgba(55, 58, 64, 0.144475);
}
.SearchBarContainer {
    position: absolute;
    width: 100%;
    height: 20%;
    left: 0;
    top: 45%;
}
.SearchBar {
    position: absolute;
    width: 60%;
    height: 100%;
    margin-left: 10%;
    background: rgba(0, 0, 0, 0.2);
    mix-blend-mode: normal;
    border-radius: 10px;
}
.MapContainer {
    position: absolute;
    height: 100%;
    width: 100%;
    bottom: 0;
    left: 0;
    right: 0px;
}
.MapMenuContainer {
    position: absolute;
    width: 100%;
    height: 25%;
    bottom: 0;
}
</style>
