import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Axios from "axios";
import Login from "@/components/Login";
import aaddStudent from "@/components/admin/aaddStudent";
import ashowAllStudent from "@/components/admin/ashowAllStudent";
import aupdateStudent from "@/components/admin/aupdateStudent";
import ushowAllStudent from "@/components/user/ushowAllStudent";
import uaddStudent from "@/components/user/uaddStudent";
import uupdateStudent from "@/components/user/uupdateStudent";
import aupdateClassInfo from "@/components/admin/aupdateClassInfo";
import aaddClassInfo from "@/components/admin/aaddClassInfo";

Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.prototype.$axios = Axios;
Axios.defaults.baseURL = "/api";

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/", name: "login", component: Login},
        //admin
        {path: "/ashowAllStudent", name: "ashowAllStudent", component: ashowAllStudent},
        {path: "/aaddStudent", name: "aaddStudent", component: aaddStudent},
        {path: "/aupdateStudent", name: "aupdateStudent", component: aupdateStudent},
        {path: "/aupdateClassInfo", name: "aupdateClassInfo", component: aupdateClassInfo},
        {path: "/aaddClassInfo", name: "aaddClassInfo", component: aaddClassInfo},
        //user
        {path: "/ushowAllStudent", name: "ushowAllStudent", component: ushowAllStudent},
        {path: "/uaddStudent", name: "uaddStudent", component: uaddStudent},
        {path: "/uupdateStudent", name: "uupdateStudent", component: uupdateStudent},
    ]
});

router.beforeEach(function (to, from, next) {
        if (window.sessionStorage.role && window.sessionStorage.id) {
            if (to.path === "/" && window.sessionStorage.role === "admin") {
                next({path: '/ashowAllStudent'});
            } else if (to.path === "/" && window.sessionStorage.role === "user") {
                next({path: '/ushowAllStudent'});
            } else if (to.path === "/ashowAllStudent" && window.sessionStorage.role === "user") {
                next({path: '/ushowAllStudent'});
            } else if (to.path === "/ushowAllStudent" && window.sessionStorage.role === "admin") {
                next({path: '/ashowAllStudent'});
            } else if ((to.path === "/aupdateClassInfo" || to.path === "/aaddClassInfo" || to.path === "aupdateStudent" || to.path === "aaddStudent") && window.sessionStorage.role === "user") {
                next({path: '/ushowAllStudent'});
            } else {
                next();
            }
        } else {
            if (to.path === '/') {
                next();
            } else {
                next({path: '/'});
            }
        }
    }
);

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
