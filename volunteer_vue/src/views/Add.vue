<script setup>

</script>

<template>
  <div style="width: 500px">
    <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
      <el-form-item label="学号" prop="studentId">
        <el-input v-model="form.studentId"  ></el-input>
      </el-form-item>
      <el-form-item label="账号" prop="number">
        <el-input v-model="form.number"  ></el-input>
      </el-form-item>
      <el-form-item  label="姓名"  prop="name" >
        <el-input v-model.number="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item  label="密码"  prop="password" >
        <el-input v-model.number="form.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item  label="号码"  prop="phone" >
        <el-input v-model.number="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item class="button">
        <el-button type="primary" @click="onSubmit('form')">添加</el-button>
        <el-button @click="disAdd">取消</el-button>
      </el-form-item>

    </el-form>
  </div>
</template>

<style scoped>
div {
  width: 500px;

  .button {
    text-align: center;
  }
}
</style>

<script>
export default {
  name:'Add',

  data() {

   // //验证年龄格式
   //  let checkAge =(rule,value,callback)=>{
   //    if(value>150){
   //      callback(new Error("年龄输入过大"));
   //    }else{
   //      callback();
   //    }
   //  }
   //



    //账号的唯一验证
    let checkDuplicate=(rule,value,callback)=>{
      if(this.form.id){
        callback();
      }

      axios.get(this.$httpUrl+"/user/getByNo?number="+this.form.number).then(function (resp){
        if(resp.data.code!==200){
          callback();
        }else {
          callback(new Error("账号已存在"))
        }
      })
    }
    return {
      form: {
        id:'',
        studentId:'',
        number:'',
        name: '',
       password:'',
        sex:'1',
        phone:'',
        roleId:2,
        isFirst:0
      },
      rules: {

        password: [
          {required: true, message: '密码不能为空'}
        ],
        studentId:[
          {required: true, message: '学号不能为空'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        number: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {Validator:checkDuplicate, trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '号码不能为空'},
          {type: 'number', message: '号码必须为数字值'},
          {pattern:/^1[3|4|5|6|7|8|9][0-9]\d{8}$/,message: "请输入正确的格式",trigger: 'blur'}
        ]
      }
    }
  },
  methods:{

    disAdd(){
      this.$router.push('/')
    },
    onSubmit(form) {
      let _this =this
      this.$refs[form].validate((valid) => {
        if (valid) {
          axios.post(this.$httpUrl+'/user',this.form).then(function (resp){
            if(resp.data.code){
              _this.$alert(  _this.form.number + '的信息已成功添加', '结果', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/')
                }
              });
            }else{
              alert(resp.data.message)
            }
          }
          )
        }

      });
    },
  }
}
</script>
