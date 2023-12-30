<template>
    <div>
        <el-card class="box-card">
            <h2>登录</h2>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-position="left" label-width="70px"
                class="login-from">
                <el-form-item label="用户名" prop="uname">
                    <el-input v-model="ruleForm.uname"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div class="btnGroup">
                <el-button type="primary" @click="submitForm('ruleForm')" v-loading="loading">登录</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
                <el-button style="margin-left: 10px" @click="regist('ruleForm')" v-loading="loading">注册</el-button>
                <el-button style="margin-left: 10px" @click="regist('ruleForm')" v-loading="loading">
                    <router-link to="/" class="header-link">首页
                    </router-link></el-button>
            </div>
        </el-card>
    </div>
</template>
  
<script>

import axios from "axios";

export default {
    data() {
        return {
            ruleForm: {
                uname: "",
                password: "",
            },
            rules: {
                uname: [
                    { required: true, message: "用户名不能为空！", trigger: "blur" },
                ],
                password: [
                    { required: true, message: "密码不能为空！", trigger: "blur" },
                ],
            },
            loading: false,
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                console.log("hi");
                this.loading = true;
                if (valid) {
                    let _this = this;
                    axios({
                        url: "http://localhost:8080/user/login",
                        method: "post",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        data: {
                            uname: _this.ruleForm.uname,
                            password: _this.ruleForm.password,
                        },
                    }).then((res) => {
                        if (res.data.code === "0") {
                            sessionStorage.setItem("userInfo", JSON.stringify(res.data.data));
                            this.$router.push('/');
                            this.$message({
                                message: res.data.msg,
                                type: "success",
                            });
                        } else {
                            this.$message({
                                message: res.data.msg,
                                type: "warning",
                            });
                        }
                        _this.loading = false;
                        console.log(res);
                    });
                } else {
                    console.log("error submit!!");
                    this.loading = false;
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        regist(formName) {
            this.$refs[formName].validate((valid) => {
                console.log("hi");
                this.loading = true;
                if (valid) {
                    let _this = this;
                    axios({
                        url: "http://localhost:8080/user/register",
                        method: "post",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        data: {
                            uname: _this.ruleForm.uname,
                            userName: "hi",
                            fileName: "wahtr",
                            password: _this.ruleForm.password,
                        },
                    }).then((res) => {
                        if (res.data.code === "0") {
                            sessionStorage.setItem("userInfo", JSON.stringify(res.data.data));
                            this.$message({
                                message: res.data.msg,
                                type: "success",
                            });
                        } else {
                            this.$message({
                                message: res.data.msg,
                                type: "warning",
                            });
                        }
                        _this.loading = false;
                        console.log(res);
                    });
                } else {
                    console.log("error submit!!");
                    this.loading = false;
                    return false;
                }
            });
        }
    },
};
</script>
  
<style scoped>
.box-card {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    margin: 0;
    width: 400px;
}

.login-from {
    margin: auto auto;
}
</style>