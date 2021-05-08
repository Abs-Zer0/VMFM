import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/components/Home'
import Auth from '@/components/Auth'
import Login from '@/components/Login'

Vue.use(VueRouter)

export default new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/auth',
            name: 'Authorization',
            component: Auth
        }
    ]
})