<script setup lang="ts">
import { ref, onMounted } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";

import HeaderNav from "./components/HeaderNav.vue";
import * as defaultData from "../assets/statics/default.json";
const DefaultText: string = defaultData.content;

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
      enable: true,
    },
  });
});
</script>

<template>
  <div class="common-layout">
    <el-container>
      <HeaderNav />
      <el-container>
        <el-aside width="200px">Aside</el-aside>
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
  .el-aside {
    position: fixed;
    top: 60px;
    left: 0;
    width: 200px;
    height: calc(100% - 60px);
    background-color: #fff;
    box-shadow: 0 2px 12px 2px rgba(0, 0, 0, 0.1);
    transition:
      border 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955),
      background 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955);
    z-index: 10;
  }

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
      margin: 0px auto;
    }
  }
}
</style>
