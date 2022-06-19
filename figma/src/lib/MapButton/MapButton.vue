<template>
    <button
        class="map-button"
        :theme="theme"
        :level="level"
        :size="size"
        :style="{ '--color': color }"
        :disabled="disabled"
        :loading="loading"
    >
        <div class="map-button-mask"></div>
        <span class="map-button-loadingIndicator" v-if="loading"></span>
        <slot></slot>
    </button>
</template>

<script lang="ts">
declare const props: {
    theme?: "button" | "link" | "text";
    level?:
        | "default"
        | "plain"
        | "primary"
        | "success"
        | "info"
        | "warning"
        | "danger";
    size?: "middle" | "small" | "large";
    color: string;
    disabled: boolean;
    loading: boolean;
};

export default {
    install: function (Vue: {
        component: (
            arg0: string,
            arg1: {
                install: (Vue: any) => void;
                name: string;
                props: {
                    theme: { type: StringConstructor; default: string };
                    level: { type: StringConstructor; default: string };
                    size: { type: StringConstructor; default: string };
                    color: { type: StringConstructor; default: string };
                    disabled: { type: BooleanConstructor; default: boolean };
                    loading: { type: BooleanConstructor; default: boolean };
                };
            }
        ) => void;
    }) {
        Vue.component(this.name, this);
    },
    name: "MapButton",
    props: {
        theme: {
            type: String,
            default: "button",
        },
        level: {
            type: String,
            default: "default",
        },
        size: {
            type: String,
            default: "middle",
        },
        color: {
            type: String,
            default: "#8c6fef",
        },
        disabled: {
            type: Boolean,
            default: false,
        },
        loading: {
            type: Boolean,
            default: false,
        },
    },
};
</script>

<style></style>
