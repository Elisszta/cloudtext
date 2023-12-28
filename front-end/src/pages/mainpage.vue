<script setup lang="ts">
import { ref, onMounted } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import { saveAs } from "file-saver";

import HeaderNav from "./components/HeaderNav.vue";
import SideBar from "./components/SiderBar.vue";
import * as defaultData from "../assets/statics/default.json";
const DefaultText: string = defaultData.content;
const PlaceHolder: string = defaultData.placeholder;

const vditor = ref();

onMounted(() => {
  vditor.value = new Vditor("vditor", {
    mode: "wysiwyg",
    height: "100%",
    width: "100%",
    minWidth: "500px",
    toolbarConfig: {
      pin: true,
    },
    preview: {
      delay: 100,
    },
    value: DefaultText,
    counter: {
      enable: true,
      max: 102400,
    },
    cache: {
      enable: false,
    },
  });
});

function saveMarkdown() {
  console.log("save markdown");
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <HeaderNav />
      <el-container>
        <SideBar />
        <el-main>
          <div id="vditor"></div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style lang="less">
@import "src/assets/styles/style";
.common-layout {
  .el-main {
    position: fixed;
    top: 60px;
    left: 200px;
    width: calc(100% - 200px);
    height: calc(100% - 60px);
    min-width: 600px;
    background-color: #fff;

    .vditor {
      height: calc(100vh - 100px);
      overflow: auto;
    }

    .vditor-toolbar {
      border-left: 1px solid #d1d5da;
      border-right: 1px solid #d1d5da;
    }

    .vditor-content {
      font-family: "Montserrat", sans-serif;
      height: auto;
      min-height: auto;
      border: 1px solid #d1d5da;
      border-top: none;
    }

    .vditor-reset {
      font-size: 14px;
    }

    .vditor-textarea {
      font-size: 14px;
      height: 100%;
    }
  }
}

@media (max-width: 960px) {
  .el-main {
    .vditor {
      height: calc(100vh - 60px);
      padding: 10px;
      margin: 0 auto;
    }
  }
}
</style>
