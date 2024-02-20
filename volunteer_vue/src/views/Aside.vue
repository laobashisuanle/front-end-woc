<script setup>

</script>

<template>
  <div>
  <div style="display: flex;background-color:#545c64;height: 56px;align-items: center;justify-content: center ">
    <img class="pic" style="border-radius: 50%; width:45px;height:45px" src="../assets/njupt4.jpeg" alt="">
    <div class="logo-title"  v-show="!isCollapse" >青志联family</div>
  </div>

<el-menu router
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
         :collapse-transition="false"
    :default-active="$router.path"
    style="height:200vh;width: 250px"
     :collapse="isCollapse">

  <el-menu-item index="/home">
      <i class="el-icon-s-home"></i>
      <span slot="title">系统首页</span>
  </el-menu-item>

  <el-submenu v-if="user.roleId!==2">
    <template slot="title">
      <i class="el-icon-setting"></i>
      <span slot="title">用户管理</span>
    </template>
    <el-menu-item index="zero" v-if="user.roleId===0">
      <template slot="title">
        <i class="el-icon-s-custom"></i>
        <span slot="title">主席管理系统</span>
      </template>
    </el-menu-item>
    <el-menu-item index="one" v-if="user.roleId===0">
      <template slot="title">
        <i class="el-icon-user-solid"></i>
        <span slot="title">中干管理系统</span>
      </template>
    </el-menu-item>
    <el-menu-item index="two" v-if="user.roleId!==2">
      <template slot="title">
        <i class="el-icon-user"></i>
        <span slot="title">干事管理系统</span>
      </template>
    </el-menu-item>
  </el-submenu>
    <el-menu-item index="three">
        <i class="el-icon-position"></i>
        <span slot="title">志愿活动管理</span>
    </el-menu-item>
    <el-menu-item index="four">
        <i class="el-icon-edit-outline"></i>
        <span slot="title">志愿时长登记</span>
    </el-menu-item>

  <el-menu-item index="five">
    <i class="el-icon-chat-square"></i>
    <span slot="title">志愿分享论坛</span>
  </el-menu-item>

</el-menu>
  </div>
</template>

<style scoped>
.logo-title{
  font-size: 14px;
  color: white;
  margin-left: 10px;
  transition: all .3s !important;
}

</style>
<script>

export default{

  props:{
  isCollapse:Boolean
},
  data(){
    return{
  user:JSON.parse(window.localStorage.getItem('user')||'{}')
    }

  }
}
</script>