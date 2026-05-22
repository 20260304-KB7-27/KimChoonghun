<template>
  <h1>메모장이에용</h1>
  <TodoList :todo-list="memos"></TodoList>
  <RouterLink :to="{ name: 'memo/new' }">새 메모</RouterLink>
</template>

<script setup>
import TodoList from '@/components/todoList.vue';
import axios from 'axios';
import { ref } from 'vue';

const memos = ref([]);
async function getData() {
    const apiURL = 'http://localhost:3000/memo'
    const response = await axios.get(apiURL);
    console.log(response.data)

    memos.value = response.data;
}

getData();
</script>

<style scoped>



</style>