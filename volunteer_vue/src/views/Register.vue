<script setup>

</script>

<template>
  <div style="height: 100vh; display: flex; align-items: center;justify-content: center;background-color: #B3C0D1" >
    <div style="display: flex;background-color: white;border-radius: 5px;width: 66%;overflow: hidden ;height: 60%">
      <div style="flex: 1">
        <img src="../assets/njupt2.jpg" alt="" style="width: 100%;height: 100%"/>
      </div>
      <div style="flex: 1;display: flex;align-items: center;justify-content: center">
        <el-form :model="user" style="width: 80%" :rules="rules" ref="registerInfo">
          <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">欢迎注册青志联后端平台用户</div>
          <el-form-item prop="number">
            <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="user.number"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="confirmPassword">
            <div style="display:flex;align-items: center">
              <el-input v-model="user.confirmPassword" show-password placeholder="请确认密码" prefix-icon="el-icon-lock" style="flex:1;"></el-input>
            </div>
          </el-form-item>
          <el-select v-model="user.department" filterable placeholder="请选择注册部门" style="margin-right: 5px;width:390px;margin-bottom: 5px;margin-top: 5px ">
            <el-option
                v-for="item in departs"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
          <el-form-item>
            <el-button type="success" style="width: 100%" @click="Register">注册</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">已有账号？请<span style="color: #2196f3;cursor: pointer" @click="$router.push('/login')">登录</span></div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  data() {


    //自定义确认密码
    var validatepassword = (rule, confirmPassword, callback) => {   //value即表单你传入的值
          if (confirmPassword == '') {
            callback(new Error('请确认密码'));
          } else if (confirmPassword !== this.user.password) {
              callback(new Error('两次输入密码不一致'));
            } else {
              callback();
            }
          };

          return {
            rules: {
              password: [
                {required: true, message: '密码不能为空'}
              ],
              number: [
                {required: true, message: '请输入账号', trigger: 'blur'},

              ],
              confirmPassword: [
                {required: true, message: '密码不能为空'},
                {validator: validatepassword, trigger: 'blur'}
              ]
            },
            user: {
              number: '',
              password: '',
              confirmPassword: '',
              name:'',
              isFirst:0,
              roleId:2,
              department:''
            },
            departs:[
              {
                value: '1',
                label: '宣传部'
              }, {
                value: '2',
                label: '校园组'
              }, {
                value: '3',
                label: '社区组'
              }, {
                value: '4',
                label: '讲解组'
              }, {
                value: '5',
                label: '助老组'
              },
              {
                value: '6',
                label: '助残组'
              },
              {
                value: '7',
                label: '邮书组'
              }
            ]
          }
        },

        methods: {
          Register(){
      let _this = this
      this.$refs["registerInfo"].validate((valid) => {
        if (valid) {
          this.user.isFirst=0
          axios.post(this.$httpUrl + "/web/register", this.user).then(function (resp) {
            console.log(resp)
            if (resp.data.code === 200) {
              _this.$router.push('/login')
              _this.$message.success(resp.data.message)

            } else {
              _this.$message.error(resp.data.message)
            }
          })
        }

      })


    }
  }
}

</script>
