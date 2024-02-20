<script setup>

</script>

<template>
 <el-card style="width: 50%;margin-top: 10px;margin-left: 10px;border-radius: 10px">
   <el-form :model="user" :rules="rules" ref="formRef" label-width="100px" class="demo-ruleForm" style="margin-right: 20px">
     <el-form-item  label="原始密码"  prop="password">
       <el-input show-password v-model="user.password"></el-input>
     </el-form-item>
     <el-form-item label="新密码" prop="newPassword">
       <el-input show-password v-model="user.newPassword"  ></el-input>
     </el-form-item>
     <el-form-item label="确认密码" prop="confirmPassword">
       <el-input show-password v-model="user.confirmPassword"></el-input>
     </el-form-item>
     <el-form-item class="button">
       <el-button type="primary" @click="onEdit">确认修改</el-button>
     </el-form-item>
   </el-form>
 </el-card>
</template>

<style scoped>
.button{
  text-align: center;
  margin-right: 20px;
}

</style>

<script>
 export default {
   name: 'Password',
   data() {
     const validatePassword = (rule, value, callback) => {   //value即表单你传入的值
       if (value === '') {
         callback(new Error('请确认密码'));
       } else if (value !== this.user.newPassword) {
         callback(new Error('确认密码失败'));
       } else {
         callback();
       }
     };
     return {
       user: JSON.parse(window.localStorage.getItem("user") || '{}'),
       rules: {
         password: [
           {required: true, message: '原始密码不能为空', trigger: 'blur'}
         ],
         newPassword: [
           {required: true, message: '新密码不能为空', trigger: 'blur'}
         ],
         confirmPassword: [
           {validator: validatePassword,required:true, trigger: 'blur'},
         ],

       }
     }

   },
   created() {

   },
   methods:{
     onEdit(){
       this.$refs["formRef"].validate((valid)=>{
         if(valid){
           this.user.password=this.user.confirmPassword
           axios.put(this.$httpUrl+'/user',this.user).then(resp=>{
             if(resp.data.code==200){
               this.$message.success('修改密码成功')
               this.$router.push('/login')
             }else {
               this.$message.error('修改密码失败')
             }
           })
         }
       })

     }
   }
 }
</script>