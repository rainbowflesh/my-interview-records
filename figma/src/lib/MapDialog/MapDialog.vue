<template>
    <template v-if="visible">
        <teleport to="body">
            <div class="map-dialog-overlay" @click="close"></div>
            <div class="map-dialog">
                <header class="map-dialog-header" style="">
                    <div
                        id="header"
                        style="
                            /* Just Sold */
                            position: absolute;
                            height: 3em;
                            margin-left: 43.48%;
                            margin-right: 43.24%;
                            margin-top: 1em;
                            font-family: 'Poppins';
                            font-style: normal;
                            font-weight: 600;
                            font-size: 1em;
                            color: #000000;
                        "
                    >
                        {{ title }}
                    </div>

                    <span class="map-dialog-close" @click="close"></span>
                </header>
                <main class="map-dialog-main">
                    <slot></slot>
                </main>
                <footer class="map-dialog-footer">
                    <map-button
                        level="plain"
                        @click="close"
                        style="
                            /* Just Sold */
                            position: absolute;
                            margin-right: 3em;
                            font-family: 'Poppins';
                            font-style: normal;
                            font-weight: 600;
                            font-size: 1em;
                            line-height: 24px;
                            color: #28a3b3;
                            background-color: rgba(0, 0, 0, 0);
                            border-color: rgba(0, 0, 0, 0);
                        "
                        >Cancel</map-button
                    >
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <map-button
                        @click="task"
                        :loading="loading"
                        style="
                            /* Just Sold */
                            position: absolute;
                            margin-right: 1em;
                            font-family: 'Poppins';
                            font-style: normal;
                            font-weight: 600;
                            font-size: 1em;
                            line-height: 24px;
                            color: #28a3b3;
                            background-color: rgba(0, 0, 0, 0);
                            border-color: rgba(0, 0, 0, 0);
                        "
                        >Apply</map-button
                    >
                </footer>
            </div>
        </teleport>
    </template>
</template>

<script lang="ts">
import MapButton from "../MapButton/MapButton.vue";
import { ref, type SetupContext } from "vue";

declare const props: {
    visible: boolean;
    title: string;
    ok: () => boolean;
    cancel: () => boolean;
};
declare const context: SetupContext;

export default {
    install: function (Vue: {
        component: (
            arg0: string,
            arg1: {
                install: (Vue: any) => void;
                name: string;
                props: {
                    visible: { type: BooleanConstructor; default: boolean };
                    title: { type: StringConstructor; required: boolean };
                    ok: { type: FunctionConstructor; default: () => boolean };
                    cancel: {
                        type: FunctionConstructor;
                        default: () => boolean;
                    };
                };
                components: {
                    MapButton: {
                        install: (Vue: any) => void;
                        name: string;
                        props: {
                            theme: { type: StringConstructor; default: string };
                            level: { type: StringConstructor; default: string };
                            size: { type: StringConstructor; default: string };
                            color: { type: StringConstructor; default: string };
                            disabled: {
                                type: BooleanConstructor;
                                default: boolean;
                            };
                            loading: {
                                type: BooleanConstructor;
                                default: boolean;
                            };
                        };
                    };
                };
                setup(
                    props: any,
                    context: any
                ): {
                    // loading: SetupContext.Ref<boolean>;
                    close: () => void;
                    task: () => void;
                };
            }
        ) => void;
    }) {
        Vue.component(this.name, this);
    },
    name: "MapDialog",
    props: {
        visible: {
            type: Boolean,
            default: false,
        },
        title: {
            type: String,
            required: true,
        },
        ok: {
            type: Function,
            default: () => {
                return true;
            },
        },
        cancel: {
            type: Function,
            default: () => {
                return true;
            },
        },
    },
    components: {
        MapButton,
    },
    setup(
        props: { cancel: () => unknown; ok: () => unknown },
        context: { emit: (arg0: string, arg1: boolean) => void }
    ) {
        const loading = ref(false);
        const close = () => {
            if (loading.value) {
                return;
            }
            new Promise((resolve, reject) => {
                resolve(props.cancel());
            }).then((result) => {
                if (result !== false) {
                    context.emit("update:visible", false);
                }
            });
        };
        const task = () => {
            new Promise((resolve, reject) => {
                loading.value = true;
                resolve(props.ok());
            }).then((result) => {
                if (result === true) {
                    loading.value = false;
                    context.emit("update:visible", false);
                }
            });
        };
        return { loading, close, task };
    },
};
</script>

<style lang="scss">
.map-dialog-overlay {
    z-index: 20;
    position: fixed;
    margin-left: 0;
    right: 0;
    background: fade-out($color: #000000, $amount: 0.7);
    /* Combined Shape */
    width: 100%;
    height: 100%;

    background: rgba(0, 0, 0, 0.6);
    box-shadow: 0px -1px 15px rgba(0, 0, 0, 0.08);
    backdrop-filter: blur(27.1828px);

    /* Note: backdrop-filter has minimal browser support */
}
.map-dialog {
    z-index: 30;
    position: fixed;
    margin-left: 0;
    bottom: -1.5em;
    right: 0;
    background: fade-out($color: #000000, $amount: 0.7);
    /* Combined Shape */
    border-radius: 33px;
    width: 100%;
    height: 78%;

    background: #ffffff;
    box-shadow: 0px -1px 15px rgba(0, 0, 0, 0.08);
    backdrop-filter: blur(27.1828px);
    > * {
        padding: 8px;
    }
    > .map-dialog-divider {
        border: 0.1em solid #e2e2e2;
        padding: 0;
        background: #000000;
        opacity: 0.5;
    }
    > .map-dialog-header {
        display: flex;
        justify-content: space-between;

        > .map-dialog-close {
            position: relative;
            display: inline-block;
            width: 16px;
            height: 16px;
            cursor: pointer;
            &::before,
            &::after {
                content: "";
                position: absolute;
                height: 1px;
                background: black;
                width: 100%;
                top: 50%;
                left: 50%;
            }
            &::before {
                transform: translate(-50%, -50%) rotate(-45deg);
            }
            &::after {
                transform: translate(-50%, -50%) rotate(45deg);
            }
        }
    }
    > .map-dialog-main {
        flex-grow: 1;
        background: rgb(255, 255, 255);
    }
    > .map-dialog-footer {
        z-index: 30;
        display: flex;
        justify-content: flex-end;
        /* Rectangle */

        position: absolute;
        width: 100%;
        height: 20%;
        left: 0px;
        bottom: 0;
        right: 0;

        background: linear-gradient(
            180deg,
            rgba(255, 255, 255, 0) 0%,
            #ffffff 27.77%
        );
    }
}
</style>
