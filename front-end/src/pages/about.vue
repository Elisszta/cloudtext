<script setup>
import { ref, onMounted } from "vue";

import Vditor from "vditor";
import "vditor/dist/index.css";

import HeaderNav from "./components/HeaderNav.vue";

const vditor = ref();

onMounted(() => {
  vditor.value = new Vditor("vditor", {
    mode: "sv",
    height: "100%",
    width: "100%",
    minWidth: "500px",
    toolbarConfig: {
      pin: true,
    },
    preview: {
      delay: 100,
    },
  });
});
</script>

<template>
  <HeaderNav />
  <div id="vditor"></div>
</template>

<style></style>
