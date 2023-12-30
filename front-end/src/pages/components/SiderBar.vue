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
  </el-aside>
</template>

<script setup>
import { ref } from 'vue'
const userInfo = ref(JSON.parse(sessionStorage.getItem("userInfo") || "{}"));
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
}
</style>
