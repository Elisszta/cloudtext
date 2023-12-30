<template>
  <el-aside>
    <div class="sidebar-login-status">
      <span class="name-text">
        <template v-if="userInfo && userInfo.uname">
          您好，{{ userInfo.uname }}
        </template>
        <template v-else>
          <router-link to="/login">
            登录
          </router-link>
        </template>
      </span>
    </div>
    <div class="fileListNav">
      <div v-for="(item, index) in fileList" :key="index" class="listIter">
        <span class="filename" @click="onItemClicked(item)">{{ item }}</span>
        <el-button @click="onDeleteClicked(item)">
          <img class="icon-button" src="/src/assets/icons/delete.svg" alt="Delete" />
        </el-button>
      </div>
    </div>
  </el-aside>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo") || "{}"));
const fileList = ref([]);
const emits = defineEmits([]);

onMounted(() => {
  axios({
    url: "http://localhost:8080/file/getFileList",
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    data: {
      userName: userInfo.value.uname,
      fileName: "Whatsoever"
    },
  }).then((res) => {
    if (res.data.code === "0") {
      fileList.value = res.data.data.list
      console.log(res.data.data.list);
    } else {
    }
  });
});

function onItemClicked(title) {
  console.log("onItemClicked");
  console.log(title);
  emits("onItemClicked", title)
}

function onDeleteClicked(title) {
  console.log("onDeleteClicked");
  console.log(title);
  emits("onDeleteClicked", title)
}
</script>

<style>
.el-aside {
  position: fixed;
  top: 60px;
  left: 0;
  height: calc(100% - 60px);
  --el-aside-width: 200px;
  background-color: #fff;
  box-shadow: 0 2px 12px 2px rgba(0, 0, 0, 0.1);
  transition:
    border 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955),
    background 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955);
  z-index: 10;

  .sidebar-login-status {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 55px;
    background-color: #fff;
    z-index: 20;
    box-shadow: 0 2px 12px 2px rgba(0, 0, 0, 0.1);
    transition:
      border 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955),
      background 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955);

    .name-text {
      text-align: center;
    }
  }

  .fileListNav {
    height: calc(100% - 55px);
    overflow: auto;

    .listIter {
      height: 50px;
      line-height: 50px;
      padding-left: 20px;
      border-bottom: 1px solid #ebeef5;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
      /* 将内容水平分散对齐 */
      align-items: center;
      /* 垂直居中对齐 */

      &:hover {
        background-color: #f5f7fa;
      }

      .filename {
        flex-grow: 1;
        white-space: nowrap;
        /* 不换行 */
        overflow: hidden;
        /* 溢出隐藏 */
        text-overflow: ellipsis;
        /* 显示省略号 */
      }

      .el-button {
        border: none;
      }
    }
  }
}
</style>
