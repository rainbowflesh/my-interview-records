<template>
    <div class="slider" ref="mySlider">
        <div class="left-btn" :class="{ active: myPosition.isBtn == 2 }"></div>
        <div class="prop"></div>
        <div class="prop-bg"></div>
        <div class="right-btn" :class="{ active: myPosition.isBtn == 1 }"></div>
    </div>
</template>
<script type="text/javascript">
export default {
    name: "slider",
    props: {
        valueFun: {
            type: Function,
            required: true,
        },
        max: {
            type: Number,
            default: 100,
        },
        min: {
            type: Number,
            default: 0,
        },
    },
    data() {
        return {
            myPosition: {
                left: 0,
                right: 0,
                now: 0,
                isBtn: 0,
                propWidth: 0,
            },
            myDefault: null,
        };
    },
    methods: {},
    mounted() {
        let that = this;
        let mySlider = this.$refs.mySlider;
        let prop = mySlider.children[1];
        let rightBtn = mySlider.children[3];
        let leftBtn = mySlider.children[0];
        let myWidth = 0;

        const elementLeft = (e) => {
            var offset = e.offsetLeft;
            if (e.offsetParent != null) offset += elementLeft(e.offsetParent);
            return offset;
        };

        const myCount = () => {
            if (this.myPosition.right > this.myPosition.left) {
                this.myPosition.propWidth =
                    this.myPosition.right - this.myPosition.left;
                prop.style.width = this.myPosition.propWidth + "%";
                prop.style.left = this.myPosition.left + "%";
                this.valueFun(
                    parseInt(this.myPosition.left),
                    parseInt(this.myPosition.right),
                    parseInt(this.myPosition.propWidth)
                );
            } else if (this.myPosition.right < this.myPosition.left) {
                this.myPosition.propWidth =
                    this.myPosition.left - this.myPosition.right;
                prop.style.width = this.myPosition.propWidth + "%";
                prop.style.left = this.myPosition.right + "%";
                this.valueFun(
                    parseInt(this.myPosition.right),
                    parseInt(this.myPosition.left),
                    parseInt(this.myPosition.propWidth)
                );
            }
        };

        this.myDefault = () => {
            this.myPosition.right = this.max;
            this.myPosition.left = this.min;

            if (this.max > this.min) {
                this.myPosition.propWidth = this.max - this.min;
                prop.style.left = this.myPosition.left + "%";
            } else {
                this.myPosition.propWidth = this.min - this.max;
                prop.style.left = this.myPosition.right + "%";
            }

            prop.style.width = this.myPosition.propWidth + "%";
            leftBtn.style.left = this.myPosition.right + "%";
            rightBtn.style.left = this.myPosition.left + "%";
            this.valueFun(
                this.myPosition.left,
                this.myPosition.right,
                this.myPosition.propWidth
            );
        };

        let mySliderX = elementLeft(mySlider);

        mySlider.addEventListener("touchmove", (e) => {
            let pageX = e.touches[0].pageX - mySliderX;
            myWidth = (pageX / mySlider.offsetWidth) * 100;
            if (myWidth > 100) {
                myWidth = 100;
            } else if (myWidth < 0) {
                myWidth = 0;
            }

            if (this.myPosition.isBtn == 1) {
                this.myPosition.left = myWidth;
                rightBtn.style.left = myWidth + "%";
            } else if (this.myPosition.isBtn == 2) {
                this.myPosition.right = myWidth;
                leftBtn.style.left = myWidth + "%";
            }

            myCount();
            e.preventDefault();
        });

        mySlider.addEventListener("touchstart", (e) => {
            let touchX = e.touches[0].pageX - mySliderX;
            let btnWidth =
                (leftBtn.offsetWidth / mySlider.offsetWidth / 2) * 100;
            this.myPosition.now = (touchX / mySlider.offsetWidth) * 100;
            mySliderX = elementLeft(mySlider);
            if (
                this.myPosition.now <= this.myPosition.left + btnWidth &&
                this.myPosition.now >= this.myPosition.left - btnWidth
            ) {
                this.myPosition.isBtn = 1;
            } else if (
                this.myPosition.now <= this.myPosition.right + btnWidth &&
                this.myPosition.now >= this.myPosition.right - btnWidth
            ) {
                this.myPosition.isBtn = 2;
            } else {
                this.myPosition.isBtn = 0;
            }
        });

        this.myDefault();
    },
    watch: {
        min(New, old) {
            this.myDefault();
        },
        max(New, old) {
            this.myDefault();
        },
    },
};
</script>
<style lang="scss">
.slider {
    z-index: 40;
    width: 100%;
    position: relative;
    height: 2em;
    .left-btn,
    .right-btn {
        position: absolute;
        top: -0.34rem;
        transform: translate(-50%, 0);
        &:before {
            content: "";
            display: block;
            width: 0;
            height: 0.4rem;
            border-left: 0.17rem solid transparent;
            border-right: 0.17rem solid transparent;
            border-bottom: 0.17rem solid #b6b6b6;
        }
        &:after {
            content: "";
            display: block;
            height: 0.3rem;
            width: 0.34rem;
            background: #b6b6b6;
            border-bottom-left-radius: 0.1rem;
            border-bottom-right-radius: 0.1rem;
        }
    }
    .prop {
        width: 0%;
        height: 0.04rem;
        background: #ffd00d;
        position: absolute;
        top: 0;
        left: 0;
        z-index: 2;
    }
    .prop-bg {
        background: #b6b6b6;
        border-radius: 0.04rem;
        height: 0.04rem;
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: 1;
    }
    .active {
        &:before {
            border-bottom-color: #ff8c30;
        }
        &:after {
            background: #ff8c30;
        }
    }
}
</style>
