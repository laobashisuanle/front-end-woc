<template>
  <el-container style="height:100%; border: 1px solid #eee">
    <el-aside :width="ch_width"  style="background-color: rgb(238, 241, 246);">
   <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>


    <el-container >
      <el-header style="text-align: right; font-size: 12px">
      <Header  @onCllapse="onCllapse" :icon="icon"></Header>
      </el-header>

      <el-main>
      <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<style>


.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 80px;
}
main.el-main{
  padding: 0px;
}

.el-aside {
  color: #333;
}
</style>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Aside from "@/views/Aside.vue";
import Main from "@/views/Home.vue";
import Header from "@/views/Header.vue";
import Home from "@/views/Home.vue";


export default {
  name: 'HomeView',

  components: {
    Home,
    HelloWorld,
    Header,
    Main,
    Aside
  },
  data(){
    return{
      isCollapse:false,
      ch_width:"250px",
      icon:"el-icon-s-fold"
    }

  },
  methods:{
    onCllapse(){
      this.isCollapse=!this.isCollapse
      if(!this.isCollapse){
        this.ch_width="250px"
        this.icon="el-icon-s-fold"
      }else{
        this.ch_width="64px"
        this.icon="el-icon-s-unfold"
      }
    }

  }

}
</script>
