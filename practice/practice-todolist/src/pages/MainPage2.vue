<script setup>
import TodoLists from '@/components/todoLists2.vue';
import NewTodo from '../components/NewTodo2.vue';
import { useTodoListStore } from '../stores/todolist';
import {computed, ref} from 'vue';

const ts = useTodoListStore();

ts.fetchTodoLists();

const todoList = computed(() => ts.todoList);
const totalFinishNum = computed(() => ts.totalFinishNum);
const totalItemsNum = computed(() => ts.totalItemsNum);
</script>

<template>
  <div class="todoList-bg">
    <h1>TodoLists for 모듈평가</h1>
    <NewTodo @add="ts.addTodoItem"></NewTodo>
    <p>완료 / 전체 : {{ totalFinishNum }} / {{ totalItemsNum }}</p>
    <TodoLists :todo-lists="todoList" @del="ts.deleteTodoItem", @tog="ts.toggleTodoItem"></TodoLists>
  </div>
</template>

<style scoped>
.todoList-bg {
    display: flex;
    flex-direction: column;
    text-align: center;
    margin: 0 auto;
    width: 60%;
}

.todoList-bg > h1 {
    padding: 10px;
    border: 2px black solid;
}
</style>
