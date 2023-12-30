<script setup lang="ts">
import { ref, onMounted, provide } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import { saveAs } from "file-saver";

import HeaderNav from "./components/HeaderNav.vue";
import SideBar from "./components/SiderBar.vue";
import * as defaultData from "../assets/statics/default.json";

const currFile = ref(defaultData);
const mdText: string = currFile.value.context;
const mdTitle: string = currFile.value.fileName;
const vditor = ref();

const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo") || "{}"));

const titleValue = ref(mdTitle);
provide('titleValue', titleValue)

onMounted(() => {
  vditor.value = new Vditor("vditor", {
    mode: "wysiwyg",
    height: "100%",
    width: "100%",
    minWidth: "500px",
    toolbar: [
      'headings', 'bold', 'italic', 'strike', 'link', '|',
      'list', 'ordered-list', 'check', 'outdent', 'indent', '|',
      'quote', 'line', 'code', 'inline-code', '|',
      'upload', 'record', 'table', '|', 'undo', 'redo', '|',
      'edit-mode', 'both', 'preview', 'outline', 'export'],
    toolbarConfig: {
      pin: true,
    },
    preview: {
      delay: 100,
    },
    value: mdText,
    counter: {
      enable: true,
      max: 51200,
    },
    cache: {
      enable: false,
    },
  });
});

function saveMarkdown() {
  console.log("save markdown");
  const title = titleValue.value + '.md';
  const blob = new Blob([vditor.value.getValue()], {
    type: "text/plain;charset=utf-8",
  });
  saveAs(blob, title);
}

function saveHTML() {
  console.log("save html");
  const title = titleValue.value + '.html';
  const blob = new Blob([vditor.value.getHTML()], {
    type: "text/plain;charset=utf-8",
  });
  saveAs(blob, title);
}

function savePDF() {

}

function save() {
  console.log("save");
  if (Object.keys(userInfo.value).length === 0) {
    alert("请先登录");
    return;
  }
  const jsonData = {
    context: vditor.value.getValue(),
    fileName: titleValue.value,
    actionType: "Add",
    userName: userInfo.value.uname,
  };
  const jsonContent = JSON.stringify(jsonData);
  const title = titleValue.value + '.json';
  const blob = new Blob([jsonContent], {
    type: "text/plain;charset=utf-8",
  });
  saveAs(blob, title);
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <HeaderNav @onOutputMDClicked="saveMarkdown" @onOutputHTMLClicked="saveHTML" @onOutputPDFClicked="savePDF"
        @onSaveButtonClicked="save" />
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
