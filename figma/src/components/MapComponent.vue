<template>
    <GMapMap class="Map" :center="center" :zoom="10" map-type-id="terrain">
        <GMapCluster :zoomOnClick="true">
            <GMapMarker
                :key="index"
                v-for="(m, index) in markers"
                :position="m.position"
                :clickable="true"
                :draggable="true"
                @click="center = m.position"
            />
        </GMapCluster>
    </GMapMap>
</template>

<script lang="ts">
export default {
    data() {
        return {
            center: { lat: 49.2238816, lng: -122.9110533 },
            markers: [
                {
                    position: {
                        lat: 49.2036649,
                        lng: -122.9084825,
                    },
                },
                {
                    position: {
                        lat: 49.2079917,
                        lng: -122.9140894,
                    },
                },
                {
                    position: {
                        lat: 49.2080851,
                        lng: -122.9218253,
                    },
                },
            ],
        };
    },
};
</script>

<style>
.Map {
    width: 100%;
    height: 100%;
    margin-left: 0px;
    margin-top: 0px;
}

body {
    margin: 0;
}
</style>
