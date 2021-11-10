import Vue from "vue";
import Router from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";

import UserList from "../views/user/List";
import Profile from "../views/user/Profile";
import MangerKind from "../views/user/MangerKind";

//404
import NotFound from "../views/NotFound";

Vue.use(Router);

// const VueRouterPush = Router.prototype.push
// Router.prototype.push = function push(to) {
//     return VueRouterPush.call(this, to).catch(err => err)
// }
export default new Router({
    mode: "history",
    routes: [{
            path: "/main",
            component: Main, //嵌套路由
            //props: true,
            children: [{
                    path: "/user/Profile/:id",
                    name: "UserP",
                    component: Profile,
                    props: true,
                },
                {
                    path: "/user/List",
                    component: UserList
                },
                {
                    path: "/user/MangerKind",
                    component: MangerKind,
                }
                //path,写的是服务端的网址，component中的才是真正要引入界面的地址
            ],
        },
        {
            path: "/",
            component: Login,
        },
        {
            //重定向
            path: "/home",
            redirect: "/main", //重定向到Main的请求上，走/main的path
        },
        {
            //404
            path: "*",
            component: NotFound,
        },
    ],
});