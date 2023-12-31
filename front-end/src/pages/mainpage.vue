<script setup lang="ts">
import { ref, onMounted, provide } from "vue";
import Vditor from "vditor";
import "vditor/dist/index.css";
import { saveAs } from "file-saver";
import axios from "axios";

import HeaderNav from "./components/HeaderNav.vue";
import SideBar from "./components/SiderBar.vue";
import * as defaultData from "../assets/statics/default.json";

const currFile = ref(defaultData);
const mdContent = ref(currFile.value.context);
const titleValue = ref(currFile.value.fileName);
const vditor = ref();

const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo") || "{}"));

provide('titleValue', titleValue);

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
    value: mdContent.value,
    counter: {
      enable: true,
      max: 51200,
    },
    cache: {
      enable: true,
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
  alert("Work in progress, 请使用编辑器中导出菜单导出PDF");
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
  axios({
    url: "http://localhost:8080/file/save",
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    data: jsonContent,
  }).then((res) => {
    if (res.data.code === "0") {
      alert("保存成功");
    } else {
      alert("保存失败");
    }
    location.reload();
  });
}

function selectItem(title: String) {
  console.log("selectItem");
  console.log(title);
  axios({
    url: "http://localhost:8080/file/getContext",
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    data: {
      userName: userInfo.value.uname,
      fileName: title
    },
  }).then((res) => {
    if (res.data.code === "0") {
      console.log(res.data.data);
      mdContent.value = res.data.data.context;
      vditor.value.setValue(mdContent.value);
      titleValue.value = res.data.data.fileName;
    } else {
    }
  });
}

function deleteItem(title: String) {
  console.log("deleteItem");
  console.log(title);
  axios({
    url: "http://localhost:8080/file/rm",
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    data: {
      userName: userInfo.value.uname,
      fileName: title,
      context: ""
    },
  }).then(() => {
    location.reload();
  });
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <HeaderNav @onOutputMDClicked="saveMarkdown" @onOutputHTMLClicked="saveHTML" @onOutputPDFClicked="savePDF"
        @onSaveButtonClicked="save" />
      <el-container>
        <SideBar @onItemClicked="selectItem" @onDeleteClicked="deleteItem" />
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
