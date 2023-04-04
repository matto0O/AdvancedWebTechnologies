import { createApp } from 'vue'
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

// import 'bootstrap/dist/css/bootstrap.css'
// import 'bootstrap-vue/dist/bootstrap-vue.css'

import PersonForm from "@/components/PersonForm.vue";
import AuthorsForm from "@/components/authors/AuthorsForm.vue"
import AuthorsTable from "@/components/authors/AuthorsTable.vue"
import AuthorsUpdate from "@/components/authors/AuthorsUpdate.vue"
import Start from "@/components/Start.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: PersonForm,
        },
        {
            path: '/books',
            name: 'person-table',
            component: Start,
        },
        {
            path: '/book-form',
            component: PersonForm,
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
        {
            path: '/book-update/:id',
            name: 'bookUpdate',
            component: PersonForm,
        },
    ]
})

createApp(App).use(router).mount('#app')
