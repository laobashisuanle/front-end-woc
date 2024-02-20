
<template>
  <div style="height: 100vh; display: flex; align-items: center;justify-content: center;background-color: rgb(43 154 153)" >
    <div style="display: flex;background-color: white;border-radius: 5px;width: 66%;overflow: hidden ;height: 60%">
      <div style="flex: 1">
        <img src="../assets/njupt3.jpg" alt="" style="width: 100%;height: 100%"/>
      </div>
      <div style="flex: 1;display: flex;align-items: center;justify-content: center">
        <el-form :model="user" style="width: 80%" :rules="rules" ref="loginInfo">
          <div style="font-size: 20px;font-weight: bold;text-align: center;margin-bottom: 20px">欢迎登录青志联后端管理系统</div>
          <el-form-item prop="number">
            <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="user.number"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" show-password placeholder="请输入密码" v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item prop="code">
            <div style="display:flex;align-items: center">
              <el-input v-model="user.code" placeholder="请输入验证码" prefix-icon="el-icon-circle-check" style="flex:1;"></el-input>
              <div style="flex:1; margin-left: 10px;background-color:rgb(216 179 175 / 19%);border-radius: 5px">

                <ValidCode @input="getCode"></ValidCode>
              </div>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="loginIn">登录</el-button>
          </el-form-item>
          <div style="display: flex">
            <div style="flex: 1">还没有账号？请<span style="color: #2196f3;cursor: pointer" @click="$router.push('/register')">注册</span></div>
            <div style="flex: 1;text-align: right"><span style="color: #2196f3;cursor: pointer" @click="handleForgetPassword">忘记密码</span> </div>
          </div>
        </el-form>
      </div>
    </div>
    <el-dialog title="忘记密码" :visible.sync="forgetPassDialogVis" width="40%" style="margin-top:70px">
      <el-form :model="resetPasswordForm" label-width="80px" style="padding-right: 20px">
        <el-form-item label="账号" >
          <el-input v-model="resetPasswordForm.number" placeholder="请输入你的账号" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" >
          <el-input v-model="resetPasswordForm.phone" placeholder="请输入你的手机号" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="forgetPassDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="resetPassword">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ValidCode from "@/components/ValidCode.vue";

export default {
  code: '',   //验证码组件传出的值
  data() {

    //自定义校验验证码
    var validateCode = (rule, value, callback) => {   //value即表单你传入的值
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        if (value.toLowerCase() !== this.code.toLowerCase()) {
          callback(new Error('验证码输入有误'));
        }
        callback();
      }
    };
    return {
      forgetPassDialogVis:false,
      resetPasswordForm:{},
      rules: {
        password: [
          {required: true, message: '请输入密码'}
        ],
        number: [
          {required: true, message: '请输入账号', trigger: 'blur'},

        ],
        code: [
          {required: true, message: '请输入验证码'},
          {validator: validateCode, trigger: 'blur'}
        ]
      },
      user: {
        number: '',
        password: '',
        code: ''  //表单里面用户填入的code
      },
      user1:{}
    }
  },
  components: {
    ValidCode
  },
  methods: {
    getCode(code) {
      this.code = code;
    },
    resetPassword(){
     axios.put(this.$httpUrl+'/web/password',this.resetPasswordForm).then(resp=>{
     if(resp.data.code===200){
         this.$message.success(resp.data.message)
          this.forgetPassDialogVis=false
     }else{
       this.$message.error(resp.data.message)
     }
     })
    },
    handleForgetPassword(){
      this.resetPasswordForm={}
      this.forgetPassDialogVis=true
    },
    loginIn() {
      let _this = this
      this.$refs["loginInfo"].validate((valid) => {
        if (valid) {
          axios.post(this.$httpUrl + "/web/login", this.user).then(function (resp) {

            if (resp.data.code === 200) {
              console.log(resp.data.data)
              localStorage.setItem("user", JSON.stringify(resp.data.data))
              _this.$message.success(resp.data.message)
              _this.$router.push('/home')

              //判断是否初次登录
              if(resp.data.data.isFirst==0){
                _this.user1 =resp.data.data
                _this.user1.isFirst=1
                console.log(_this.user1)
                axios.put(_this.$httpUrl+'/user',_this.user1).then(res=>{
                  if(res.data.code==200) {
                    localStorage.setItem("user", JSON.stringify(_this.user1))
                  }
                })

                _this.$confirm('这是你的第一次登录，是否前往完善你的信息？','提示',{
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(()=>{
                  //系统检测到用户为第一次登录，提示跳转到个人信息页面
                  _this.$router.push("/person")
                }).catch(()=>{
                  _this.$message.info('取消跳转')
                })

              }
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