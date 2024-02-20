import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import ElementUI from 'element-ui'

Vue.prototype.$httpUrl="http://localhost:8081"
Vue.config.productionTip = false
Vue.use(ElementUI,{size: 'small'})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
