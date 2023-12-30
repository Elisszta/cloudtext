<template>
  <el-aside width="200px">Aside</el-aside>
</template>

<script>
const userInfo = JSON.parse(sessionStorage.getItem("userInfo") || "{}");
</script>

<style>
.el-aside {
  position: fixed;
  top: 60px;
  left: 0;
  height: calc(100% - 60px);
  background-color: #fff;
  box-shadow: 0 2px 12px 2px rgba(0, 0, 0, 0.1);
  transition:
    border 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955),
    background 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955);
  z-index: 10;
}
</style>
