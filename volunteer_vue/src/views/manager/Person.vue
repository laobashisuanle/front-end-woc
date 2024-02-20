
<template>
<div style="margin-top: 10px;margin-left: 10px;border-radius: 10px">
  <el-card style="width: 50%;">
    <el-form :model="user" label-width="80px" style="margin-right: 50px;margin-top: 30px">
      <el-form-item label="账号" prop="number">
       <el-input v-model="user.number" placeholder="请输入账号" disabled></el-input>
      </el-form-item>
      <el-form-item label="学号" prop="studentId">
        <el-input placeholder="请输入学号" v-model="user.studentId"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input placeholder="请输入姓名" v-model="user.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="user.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
        <el-form-item label="号码" prop="phone">
          <el-input placeholder="请输入号码" v-model="user.phone"></el-input>
        </el-form-item>
      <el-form-item label="志愿经历" prop="experience">
        <el-input type="textarea" placeholder="请输入你的志愿经历" v-model="user.experience"></el-input>
      </el-form-item>
      <el-form-item class="button">
        <el-button type="primary" @click="update">保存</el-button>
        <el-button @click="disEdit" style="margin-left:20px">离开</el-button>
      </el-form-item>
    </el-form>



  </el-card>
</div>
</template>

<style scoped>
 .button{
   display: flex;
   text-align: center;
   justify-content: center;
 }
/deep/.el-card__body{
  font-weight: bold;
}
</style>

<script>
  export default {
    name:'Person',
    data(){
      return{
        user: JSON.parse(window.localStorage.getItem("user")||'{}'),
        rules: {
          studentId: [
             {required: true, message: '学号不能为空'}
        ],
            name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
            phone: [
          {required: true, message: '号码不能为空'},
          {pattern:/^1[3|4|5|6|7|8|9][0-9]\d{8}$/,message: "请输入正确的格式",trigger: 'blur'}
        ]
      }
    }
    },
    methods:{
      disEdit(){
        this.$router.push('/home')
      },
      update(){
        axios.put(this.$httpUrl+'/user',this.user).then(res=>{
          if(res.data.code==200){

            this.$message.success('编辑成功')

            window.localStorage.setItem('user',JSON.stringify(this.user))
          }else{
            this.$message.error(res.data.message)
          }

        })
      }



    }
      }
</script>
