import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';
import store from './store';
import axios from 'axios'
import VueAxios from "vue-axios";

Vue.use(VueAxios, axios)

Vue.use(ElementUI);
Vue.config.productionTip = true

new Vue({
    el: "#app",
    router,
    store,
    render: h => h(App)
})