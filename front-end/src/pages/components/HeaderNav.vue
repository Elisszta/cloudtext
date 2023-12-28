<template>
  <el-header>
    <router-link to="/" class="header-link">
      <img
        class="mark-markdown"
        src="/src/assets/icons/markdown-icon.svg"
        alt="CloudText"
      />
      <strong class="header-text">CloudText</strong>
    </router-link>
    <el-button-group>
      <span class="hint--bottom header-icon" aria-label="登录">
        <router-link to="/login">
          <img
            class="icon-button"
            src="/src/assets/icons/login.svg"
            alt="CloudText"
          />
        </router-link>
      </span>
      <el-dropdown>
        <span class="header-icon" aria-label="导出">
          <img
            class="icon-button"
            src="/src/assets/icons/download.svg"
            alt="CloudText"
          />
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              <img
                class="dropdown-icon"
                src="/src/assets/icons/pdf.svg"
                alt="CloudText"
              />
              <span class="dropdown-text">导出PDF</span>
            </el-dropdown-item>
            <el-dropdown-item>
              <img
                class="dropdown-icon"
                src="/src/assets/icons/markdown-icon.svg"
                alt="CloudText"
              />
              <span class="dropdown-text">导出MarkDown</span>
            </el-dropdown-item>
            <el-dropdown-item>
              <img
                class="dropdown-icon"
                src="/src/assets/icons/html.svg"
                alt="CloudText"
              />
              <span class="dropdown-text">导出HTML</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <span
        class="hint--bottom header-icon"
        @click="onSaveButtonClicked"
        aria-label="保存"
      >
        <img
          class="icon-button"
          src="/src/assets/icons/save.svg"
          alt="CloudText"
        />
      </span>

      <span
        class="hint--bottom header-icon"
        @click="onFullScreenClick"
        aria-label="全屏"
      >
        <img
          class="icon-button"
          src="/src/assets/icons/full-screen.svg"
          alt="CloudText"
        />
      </span>
    </el-button-group>
  </el-header>
</template>

<script>
import "hint.css";

export default {
  name: "HeaderNav",
  data() {
    return {};
  },
  methods: {
    launchFullScreen() {
      const element = document.getElementById("vditor");
      if (element.requestFullscreen) {
        element.requestFullscreen();
      } else if (element.msRequestFullscreen) {
        element.msRequestFullscreen();
      } else if (element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
      } else if (element.webkitRequestFullscreen) {
        element.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
      }
    },
    cancelFullScreen() {
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
      } else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen();
      }
    },
    onFullScreenClick() {
      const isFullScreen =
        document.fullscreenElement ||
        document.mozFullScreenElement ||
        document.msFullscreenElement ||
        document.webkitFullscreenElement;
      isFullScreen ? this.cancelFullScreen() : this.launchFullScreen();
    },
  },
};
</script>

<style lang="less">
@import "./../../assets/styles/style.less";

.el-dropdown-menu {
  margin: 0;

  .dropdown-icon {
    fill: @deep-black;
    vertical-align: middle;
    margin-right: 10px;
    height: 24px;
    width: 24px;
  }

  .dropdown-text {
    vertical-align: middle;
  }
}

.el-header {
  z-index: @hint-css-zindex;
  height: @header-height;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  background-color: #fff;
  box-shadow: 0 0 12px 2px rgba(0, 0, 0, 0.1);
  transition:
    border 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955),
    background 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955);

  .header-link {
    position: fixed;
    left: 20px;
    display: flex;
    align-items: center;
    color: inherit;

    .mark-markdown {
      width: 40px;
      height: 40px;
      margin-right: 10px;
    }
    .header-text {
      font-size: 20px;
      font-weight: 600;
      font-family: "Montserrat", sans-serif;
      text-decoration: none;
    }
  }

  .el-button-group {
    position: fixed;
    right: 20px;
    display: flex;

    .header-icon {
      margin: 0 10px;
      fill: @deep-black;
    }

    .icon-button {
      width: 32px;
      height: 32px;
    }

    span:hover {
      cursor: pointer;
    }
  }
}
</style>
