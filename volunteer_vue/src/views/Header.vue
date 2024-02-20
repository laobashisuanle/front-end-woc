<script setup>

</script>

<template>
  <div style="display: flex; font-size: 20px;line-height: 60px">
    <div>
      <i class="el-icon-s-fold" @click="collapse" style="cursor: pointer"></i>
    </div>


    <div style="text-align: center;flex: 1">
      <span>欢迎来到青志联后端管理系统</span>
    </div>

  <el-dropdown>
    <span style="cursor: pointer">{{user.number}}</span> <i class="el-icon-arrow-down" style="margin-right: 15px;margin-left: 10px" ></i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="inUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
      <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
    </div>

</template>

<style scoped>

</style>
<script>
export default{
  name:'Header',
  data(){
    return{
      user:JSON.parse(localStorage.getItem("user"))
    }

  },
  prop:{
    icon:String
  },
  methods:{
    inUser(){
    this.$router.push('/person')
    },
    logout(){

      let _this=this
      this.$confirm('你确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center:true
      }).then(() => {
        _this.$message({
          type:"success",
          message:'退出登录成功'
        })
        localStorage.removeItem("user")   //清楚当前的token与用户数据
        this.$router.push('/login')
      }).catch(() => {
        _this.$message({
          type:"info",
          message:'取消退出登录'
        })
      });


    },
    collapse(){
      this.$emit('onCllapse')
    }


}


}
</script>

