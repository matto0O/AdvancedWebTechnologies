import { createApp } from 'vue'
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'

import AuthorsForm from "@/components/authors/AuthorsForm.vue"
import AuthorsTable from "@/components/authors/AuthorsTable.vue"
import AuthorsUpdate from "@/components/authors/AuthorsUpdate.vue"

import ReadersTable from "@/components/readers/ReadersTable.vue"
import ReadersForm from "@/components/readers/ReadersForm.vue"
import ReadersUpdate from "@/components/readers/ReadersUpdate.vue"

import Start from "@/components/Start.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Start,
        },
        {
            path: '/readers',
            name: 'readers',
            component: ReadersTable,
        },
        {
            path: '/readers-update/:id',
            name: 'readersUpdate',
            component: ReadersUpdate,
        },
        {
            path: '/readers-form',
            component: ReadersForm,
        },
        {
            path: '/author-form',
            component: AuthorsForm,
        },
        {
            path: '/authors',
            name: 'authors',
            component: AuthorsTable,
        },
        {
            path: '/author-update/:id',
            name: 'authorsUpdate',
            component: AuthorsUpdate,
        },
    ]
})

createApp(App).use(router).mount('#app')
