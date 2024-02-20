import Vue from 'vue'
import VueRouter from 'vue-router'
import '../assets/global.css'
import Add from '../views/Add.vue'
import Login from  '../views/Login.vue'
import Register from "@/views/Register.vue";
import Manager from "@/views/Manager.vue";
import EDIT from "@/views/EDIT.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: Manager,
    redirect:'/home',
    children:[
      {path:'home',name:'Home',component:()=>import('../views/Home.vue')},
      {path:'zero',name:'Zero',component:()=>import('../views/manager/Zero.vue')},
      {path:'one',name:'One',component:()=>import('../views/manager/One.vue')},
      {path:'two',name:'Two',component:()=>import('../views/manager/Two.vue')},
      {path:'person',name:'Person',component:()=>import('../views/manager/Person.vue')},
      {path:'password',name:'Password',component:()=>import('../views/manager/Password.vue')}
        ]
  },
  {path: '/edit',
    name: 'EDIT',
    component:EDIT
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/add',
    name: 'Add',
    component: Add
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
// router.beforeEach((to, from, next) =>{
//   let adminPath=['one','zero']
//   let user =window.localStorage.getItem('user')
//   if(user.roleId!=0&&adminPath.includes(to.path)){
//     next('403')
//   }
// } )
export default router
