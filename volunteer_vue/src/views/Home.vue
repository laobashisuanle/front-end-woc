<script setup>

</script>

<template>
 <div>
   <div>
     <el-input placeholder="请输入账号" @keyup.enter.native="getPageParam" v-model="no" suffix-icon="el-icon-search" style="margin-right: 5px;width:200px;margin-bottom: 5px;margin-top: 5px " ></el-input>
     <el-select v-model="depart"   filterable placeholder="请选择部门" style="margin-right: 5px;width:200px;margin-bottom: 5px;margin-top: 5px ">
       <el-option
           v-for="item in departs"
           :key="item.value"
           :label="item.label"
           :value="item.value">
       </el-option>
     </el-select>
     <el-button type="primary" size="small" @click="doSearch">查询</el-button>
     <el-button type="success" size="small" @click="reset">重置</el-button>
     <el-button type="danger" size="small" @click="getZero">只看主席</el-button>
     <el-button type="warning" size="small" @click="getOne">只看中干</el-button>
     <el-button type="info" size="small" @click="getTwo">只看干事</el-button>

   </div>
  <el-table :data="tableData" border :header-cell-style="{background:'#f0f9eb', fontFamily:'Helvetica',fontSize:'14px'}" style="line-height: 40px">
    <el-table-column prop="studentId" label="学号" width="150">
    </el-table-column>
    <el-table-column prop="number" label="账号" width="140">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="140">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="120">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.sex === '0' ? 'primary' : 'success'"
            disable-transitions>{{scope.row.sex === '0' ? '女' : '男'}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="phone" label="电话" width="160">
    </el-table-column>
    <el-table-column prop="department" label="部门" width="140">
      <template slot-scope="scope">
        <el-tag disable-transitions v-if="scope.row.roleId!=0">{{scope.row.department === '1'? '宣传部':(scope.row.department === '2'?'校园组':(scope.row.department === '3'?
            '社区组':(scope.row.department === '4'?'讲解组':(scope.row.department === '5'?'助老组':(scope.row.department === '6'?'助残组':'邮书组')))))}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="roleId" label="职位" width="140">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.roleId === 0 ? 'danger':(scope.row.roleId === 1?'success':'primary')"
            disable-transitions>{{scope.row.roleId === 0 ? '主席':(scope.row.roleId === 1?'中干':'干事')}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column class="checkex" prop="experience" label="志愿经历">
    </el-table-column>
  </el-table>
  <el-pagination
      style="padding-left: 200px"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[2, 5, 10, 20]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
  </el-pagination>
 </div>
</template>

<style scoped>

</style>

<script>

import qs from 'qs';
export default {
methods:{

  Add(){
    this.$router.push('/add')
  },
  doSearch(){
    this.getPageParam();
  },
  reset(){
    this.depart='',
        this.no=''
    this.getPageParam();
  },
  getZero(){
    let _this =this
    axios.post(this.$httpUrl+"/user/page", qs.stringify( {
      number:_this.no,
      department:_this.depart,
      pageSize:_this.pageSize,
      pageNum:_this.pageNum,
      roleId:0
    })).then(function (resp){
      let data = resp.data.data
      console.log(data)
      _this.total=data.totalCount
      _this.tableData=data.data
    })
  },
  getOne(){
    let _this =this
    axios.post(this.$httpUrl+"/user/page", qs.stringify( {
      number:_this.no,
      department:_this.depart,
      pageSize:_this.pageSize,
      pageNum:_this.pageNum,
      roleId:1
    })).then(function (resp){
      let data = resp.data.data
      console.log(data)
      _this.total=data.totalCount
      _this.tableData=data.data
    })
  },
  getTwo(){
    let _this =this
    axios.post(this.$httpUrl+"/user/page", qs.stringify( {
      number:_this.no,
      department:_this.depart,
      pageSize:_this.pageSize,
      pageNum:_this.pageNum,
      roleId:2
    })).then(function (resp){
      let data = resp.data.data
      console.log(data)
      _this.total=data.totalCount
      _this.tableData=data.data
    })
  },


  getPageParam(){
    let _this =this
    axios.post(this.$httpUrl+"/user/page", qs.stringify( {
      number:_this.no,
      department:_this.depart,
      pageSize:_this.pageSize,
      pageNum:_this.pageNum
    })).then(function (resp){
      console.log(resp)
      _this.total=resp.data.data.totalCount
     _this.tableData=resp.data.data.data
    })
  },

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum=1
      this.pageSize=val
      this.getPageParam()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val
      this.getPageParam();
    }

},
  beforeMount() {
    // this.getData();
    this.getPageParam();
  },
  data() {
    return {
      tableData:[],
      pageSize:5,
      pageNum:1,
      total:0,
      no:'',
      depart:'',
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
  }
}
</script>